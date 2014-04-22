package com.webcontext.test.unit.libs.zelibraririe;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;
import static org.junit.Assert.fail;

import java.util.HashMap;
import java.util.Map;

import org.junit.After;
import org.junit.Before;
import org.junit.FixMethodOrder;
import org.junit.Test;
import org.junit.runners.MethodSorters;

import com.webcontext.libs.zelibrairie.exception.EntityAlreadyExistsException;
import com.webcontext.libs.zelibrairie.model.User;
import com.webcontext.libs.zelibrairie.services.UserService;

/**
 * Unit Test for class UserService.
 * 
 * @author Frédéric Delorme<frederic.delorme@web-context.com>
 * 
 */

@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class UserServiceTest {

	private static UserService userService = new UserService();

	private static Map<String, User> userstest = new HashMap<String, User>();

	/**
	 * @throws java.lang.Exception
	 */
	@Before
	public void setUp() throws Exception {
		userstest = new HashMap<String, User>();
		// add some user
		userstest.put("user1", new User("user1", "User", "User1",
				"user1@mail.com", "password"));
		userstest.put("user2", new User("user2", "User", "User2",
				"user2@mail.com", "password"));
		userstest.put("user3", new User("user3", "User", "User3",
				"user3@mail.com", "password"));
		userstest.put("user4", new User("user4", "User", "User4",
				"user4@mail.com", "password"));
	}

	/**
	 * @throws java.lang.Exception
	 */
	@After
	public void tearDown() throws Exception {
		userService = null;
		userstest = null;
	}

	@Test
	public void test_1_add() {
		User usernew = new User("usernew", "Usernew", "Newuser",
				"new.user@mail.com", "password");
		try {
			userService.add(usernew);
		} catch (EntityAlreadyExistsException e) {
			fail("Unable to insert data");
		}
		assertEquals("Size of collection is not the right one.", 1,
				userService.count());
	}

	@Test
	public void test_2_AddListUserandFindByUsername() {
		userService.add(userstest.values());
		assertEquals("All users was not inserted !", 5, userService.count());

		User user1 = userService.findByUsername("user1");
		assertTrue("User not found", user1 != null);
		assertEquals("User not the right one.", "User1", user1.getLastname());
	}

	@Test
	public void test_3_AddListUserAndDelete() {

		userService.add(userstest.values());

		User user1 = userService.findByUsername("user1");
		userService.delete(user1);
		User isUserDeleted = userService.findByUsername("user1");
		assertTrue("User has not bean deleted", isUserDeleted == null);
	}

	@Test
	public void test_4_update() {
		User userupdate = new User("userupdate", "Usernew", "Newuser",
				"new.user@mail.com", "password");
		try {
			userService.add(userupdate);
		} catch (EntityAlreadyExistsException e) {
			fail("Unable to insert data");
		}
		User last = userService.findByUsername("usernew");
		last.setFirstname("Toto");
		User modified = userService.update(last);

		assertEquals("USer was not modified", "Toto", modified.getFirstname());
	}

}
