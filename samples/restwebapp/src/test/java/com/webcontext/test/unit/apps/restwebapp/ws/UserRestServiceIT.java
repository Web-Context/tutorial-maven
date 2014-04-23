/**
 * 
 */
package com.webcontext.test.unit.apps.restwebapp.ws;

import java.io.IOException;
import java.net.URL;

import org.jboss.arquillian.container.test.api.Deployment;
import org.jboss.arquillian.container.test.api.RunAsClient;
import org.jboss.arquillian.junit.Arquillian;
import org.jboss.arquillian.test.api.ArquillianResource;
import org.jboss.shrinkwrap.api.ShrinkWrap;
import org.jboss.shrinkwrap.api.asset.EmptyAsset;
import org.jboss.shrinkwrap.api.spec.WebArchive;
import org.junit.FixMethodOrder;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.MethodSorters;

import com.jayway.restassured.RestAssured;

/**
 * Integration Test for User Service.
 * 
 * @author Frédéric Delorme<frederic.delorme@web-context.com>
 * 
 */
@RunWith(Arquillian.class)
@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class UserRestServiceIT {
	@Deployment
	public static WebArchive deploy() {
		return ShrinkWrap
				.create(WebArchive.class, "test.war")
					.addPackage("com.webcontext.apps.zelibraries")
					.addAsResource("META-INF/test-persistence.xml","META-INF/persistence.xml")
					.addAsWebInfResource("WEB-INF/web.xml")
					.addAsManifestResource(EmptyAsset.INSTANCE, "beans.xml");
	}
	
	@Test
	@RunAsClient
	public void test_1_findAll(@ArquillianResource URL baseURL)
			throws IOException {
		
		System.out.println("base URL="+baseURL.toString());
		
		RestAssured.expect().statusCode(200).when()
				.get(baseURL.toString() + "/rest/users");
	}

}
