package com.webcontext.apps.restwebapp.services.ws;

import java.util.HashSet;
import java.util.Set;

import javax.inject.Inject;
import javax.ws.rs.ApplicationPath;
import javax.ws.rs.core.Application;

import org.apache.log4j.Logger;

import com.webcontext.apps.restwebapp.exceptions.EntityAlreadyExistsException;
import com.webcontext.apps.restwebapp.model.User;
import com.webcontext.apps.restwebapp.services.business.UserService;

@ApplicationPath("/rest")
public class RestApplication extends Application {
	private static Logger logger = Logger.getLogger(RestApplication.class);

	@Inject
	UserService userService;

	/*
	 * (non-Javadoc)
	 * 
	 * @see javax.ws.rs.core.Application#getClasses()
	 */
	public Set<Class<?>> getClasses() {

		// initializeData();

		Set<Class<?>> services = new HashSet<Class<?>>();
		services.add(UserRestService.class);

		return services;
	}

}
