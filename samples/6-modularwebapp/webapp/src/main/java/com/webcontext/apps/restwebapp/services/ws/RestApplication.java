package com.webcontext.apps.restwebapp.services.ws;

import java.util.HashSet;
import java.util.Set;

import javax.ws.rs.Path;
import javax.ws.rs.core.Application;

import org.apache.log4j.Logger;

import com.webcontext.apps.restwebapp.exceptions.EntityAlreadyExistsException;
import com.webcontext.apps.restwebapp.services.UserService;
import com.webcontext.apps.services.user.model.User;

@Path("/rest")
public class RestApplication extends Application {
	private static Logger logger = Logger.getLogger(RestApplication.class);

	/*
	 * (non-Javadoc)
	 * 
	 * @see javax.ws.rs.core.Application#getClasses()
	 */
	public Set<Class<?>> getClasses() {

		initializeData();

		Set<Class<?>> services = new HashSet<Class<?>>();
		services.add(UserRestService.class);

		return services;
	}

	/**
	 * First initialization of data set.
	 */
	private void initializeData() {
		UserService userService = new UserService();

		if (userService.count() == 0) {
			try {
				userService.add(new User("admin", "Admin", "Istrator",
						"admin@home", "pwd"));
			} catch (EntityAlreadyExistsException e) {
				logger.fatal("Error during ata initialization", e);
			}
		}
	}
}
