package com.webcontext.test.unit.apps.restwebapp.managers;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import java.util.List;

import javax.inject.Inject;

import org.jboss.arquillian.container.test.api.Deployment;
import org.jboss.arquillian.junit.Arquillian;
import org.jboss.shrinkwrap.api.ShrinkWrap;
import org.jboss.shrinkwrap.api.asset.EmptyAsset;
import org.jboss.shrinkwrap.api.spec.JavaArchive;
import org.junit.FixMethodOrder;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.MethodSorters;

import com.webcontext.apps.restwebapp.ejb.exceptions.DaoException;
import com.webcontext.apps.restwebapp.managers.UserManager;
import com.webcontext.apps.restwebapp.model.User;

/**
 * Unit testing for UserEJB.
 * 
 * @author Frédéric Delorme<frederic.delorme@gmail.com>
 * 
 */
@RunWith(Arquillian.class)
@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class UserManagerTest {
	@Inject
	private UserManager users;

	@Deployment
	public static JavaArchive createArchiveAndDeploy() {
		return ShrinkWrap
			.create(JavaArchive.class)
			.addClasses(UserManager.class, User.class)
			.addAsResource("META-INF/test-persistence.xml",
					"META-INF/persistence.xml")
			.addAsManifestResource(EmptyAsset.INSTANCE, 
					"WEB-INF/beans.xml");
	}

	@Test
	public void test_01_FindAll() {
		List<User> list = users.findAll();
		assertTrue("UserEJB does not retrieve any User entity.", list != null
				&& list.size() > 0);

	}

	@Test
	public void test_02_FindByUsername() {
		User user = users.findByUsername("tes01");
		assertEquals("Unable to find the user 'test01'","test01",user.getUsername());
	}

	@Test
	public void test_03_Save() {
		User user = new User("test01","test01","Test01","test01@domain.test","");
		users.save(user);
		User userLoad = users.findByUsername("test01");
		assertEquals("User was not saved","test01",userLoad.getUsername());
	}

	@Test
	public void test_04_Delete() throws DaoException {
		users.delete("test01");
		assertTrue("",users.findByUsername("test01")!=null);
	}

	@Test
	public void test_05_Count() {
		long nbUsers = users.count();
		
		assertEquals("Not found the right number of users",0,nbUsers);
	}

}
