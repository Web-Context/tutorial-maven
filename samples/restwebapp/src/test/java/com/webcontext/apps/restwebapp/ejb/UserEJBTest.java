package com.webcontext.apps.restwebapp.ejb;

import static org.junit.Assert.assertTrue;
import static org.junit.Assert.fail;

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

import com.webcontext.apps.restwebapp.model.User;

/**
 * Unit testing for UserEJB.
 * 
 * @author Frédéric Delorme<frederic.delorme@gmail.com>
 * 
 */
@RunWith(Arquillian.class)
@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class UserEJBTest {
	@Inject
	private UserEJB users;

	@Deployment
	public static JavaArchive createArchiveAndDeploy() {
		return ShrinkWrap
				.create(JavaArchive.class)
				.addClasses(UserEJB.class, User.class)
				.addAsResource("META-INF/test-persistence.xml",
						"META-INF/persistence.xml")
				.addAsManifestResource(EmptyAsset.INSTANCE, "WEB-INF/beans.xml");
	}



	@Test
	public void testFindAll() {
		List<User> list = users.findAll();
		assertTrue("UserEJB does not retrieve any User entity.", list != null
				&& list.size() > 0);

	}

	@Test
	public void testFindByUsername() {
		 // TODO
	}

	@Test
	public void testSave() {
		 // TODO
	}

	@Test
	public void testDelete() {
		// TODO
	}

	@Test
	public void testCount() {
		 // TODO
	}

}
