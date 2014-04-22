/**
 * 
 */
package com.webcontext.test.unit.libs.restwebapp.ws;

import com.jayway.restassured.RestAssured;

import java.io.IOException;
import java.net.URL;

import javax.inject.Inject;

import org.jboss.arquillian.container.test.api.Deployment;
import org.jboss.arquillian.container.test.api.RunAsClient;
import org.jboss.arquillian.junit.Arquillian;
import org.jboss.arquillian.test.api.ArquillianResource;
import org.jboss.shrinkwrap.api.ShrinkWrap;
import org.jboss.shrinkwrap.api.asset.EmptyAsset;
import org.jboss.shrinkwrap.api.spec.WebArchive;
import org.junit.FixMethodOrder;
import org.junit.Test;
import org.junit.runners.MethodSorters;
import org.junit.runner.RunWith;

/**
 * Integration Test for User Service.
 * 
 * @author Fr�d�ric Delorme<frederic.delorme@web-context.com>
 * 
 */
@RunWith(Arquillian.class)
@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class UserRestServiceIT {

	@Deployment
	public static WebArchive deploy() {
		return ShrinkWrap.create(WebArchive.class,"test.war")
				.addPackage("com.webcontext.libs.zelibraries")
				.addAsWebInfResource("web.xml")
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
