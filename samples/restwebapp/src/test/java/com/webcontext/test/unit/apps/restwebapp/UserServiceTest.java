package com.webcontext.test.unit.apps.restwebapp;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;
import static org.junit.Assert.fail;

import java.util.HashMap;
import java.util.Map;

import javax.inject.Inject;

import org.jboss.arquillian.container.test.api.Deployment;
import org.jboss.shrinkwrap.api.ShrinkWrap;
import org.jboss.shrinkwrap.api.asset.EmptyAsset;
import org.jboss.shrinkwrap.api.spec.JavaArchive;
import org.junit.After;
import org.junit.Before;
import org.junit.FixMethodOrder;
import org.junit.Test;
import org.junit.runners.MethodSorters;

import com.webcontext.apps.restwebapp.exception.EntityAlreadyExistsException;
import com.webcontext.apps.restwebapp.model.User;
import com.webcontext.apps.restwebapp.services.UserService;

/**
 * Unit Test for class UserService.
 * 
 * @author Frédéric Delorme<frederic.delorme@web-context.com>
 * 
 */
@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class UserServiceTest {

	private static Map<String, User> usersTest;

	@Inject
	private UserService userService;

	@Deployment
	public static JavaArchive createArchiveAndDeploy() {
		return ShrinkWrap
				.create(JavaArchive.class)
				.addClasses(UserService.class, User.class)
				.addAsResource("META-INF/test-persistence.xml",
						"persistence.xml")
				.addAsManifestResource(EmptyAsset.INSTANCE, "beans.xml");
	}

	/**
	 * @throws java.lang.Exception
	 */
	@Before
	public void setUp() throws Exception {
		usersTest = new HashMap<String, User>();
		// add some user
		usersTest.put("user1", new User("user1", "User", "User1",
				"user1@mail.com", "password"));
		usersTest.put("user2", new User("user2", "User", "User2",
				"user2@mail.com", "password"));
		usersTest.put("user3", new User("user3", "User", "User3",
				"user3@mail.com", "password"));
		usersTest.put("user4", new User("user4", "User", "User4",
				"user4@mail.com", "password"));

		userService.add(usersTest.values());
	}

	/**
	 * @throws java.lang.Exception
	 */
	@After
	public void tearDown() throws Exception {
		usersTest = null;
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
	public void test_2_AddListUserandFindByUsername()
			throws EntityAlreadyExistsException {

		userService.add(usersTest.values());

		assertEquals("All users was not inserted !", 5, userService.count());

		User user1 = userService.findByUsername("user1");
		assertTrue("User not found", user1 != null);
		assertEquals("User not the right one.", "User1", user1.getLastname());
	}

	@Test
	public void test_3_AddListUserAndDelete()
			throws EntityAlreadyExistsException {

		userService.add(usersTest.values());

		User user1 = userService.findByUsername("user1");
		userService.delete(user1);
		User isUserDeleted = userService.findByUsername("user1");
		assertTrue("User has not bean deleted", isUserDeleted == null);
	}

	@Test
	public void test_4_update() throws EntityAlreadyExistsException {

		User userupdate = new User("userupdate", "userupdate", "userupdate",
				"user.update@mail.com", "password");
		userService.add(userupdate);
		User last = userService.findByUsername("userupdate");
		last.setFirstname("Toto");
		User modified = userService.update(last);

		assertEquals("USer was not modified", "Toto", modified.getFirstname());
	}

}
