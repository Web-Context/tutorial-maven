/**
 * 
 */
package com.webcontext.test.unit.libs.zelibraririe.ws;

import java.io.IOException;
import java.net.URL;

import org.jboss.arquillian.container.test.api.Deployment;
import org.jboss.arquillian.container.test.api.RunAsClient;
import org.jboss.arquillian.junit.Arquillian;
import org.jboss.arquillian.test.api.ArquillianResource;
import org.jboss.shrinkwrap.api.ShrinkWrap;
import org.jboss.shrinkwrap.api.asset.EmptyAsset;
import org.jboss.shrinkwrap.api.spec.WebArchive;
import org.junit.Test;
import org.junit.runner.RunWith;

import com.jayway.restassured.RestAssured;

/**
 * 
 * 
 * @author Frédéric Delorme<frederic.delorme@web-context.com>
 * 
 */
@RunWith(Arquillian.class)
public class UserRestServiceIntTest {
	@Deployment
	public static WebArchive deploy() {
		return ShrinkWrap.create(WebArchive.class)
				.addPackage("com.webcontext.libs.zelibraries")
				.addAsManifestResource(EmptyAsset.INSTANCE, "beans.xml");
	}

	@Test
	@RunAsClient
	public void test_1_findUser(@ArquillianResource URL baseURL)
			throws IOException {
		RestAssured.expect().statusCode(200).when()
				.get(baseURL.toString() + "/rest/users");
	}

}
