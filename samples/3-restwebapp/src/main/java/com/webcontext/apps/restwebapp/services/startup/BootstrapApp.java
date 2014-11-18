/**
 * 
 */
package com.webcontext.apps.restwebapp.services.startup;

import javax.annotation.PostConstruct;
import javax.inject.Inject;
import javax.inject.Singleton;

import org.apache.log4j.Logger;

import com.webcontext.apps.restwebapp.exceptions.EntityAlreadyExistsException;
import com.webcontext.apps.restwebapp.model.User;
import com.webcontext.apps.restwebapp.services.business.UserService;

/**
 * This is a bootstrap class to intialize default data.
 * 
 * @author Frederic Delorme<frederic.delorme@gmail.com>
 *
 */
@Singleton
@Startup
public class BootstrapApp {
	private static final Logger logger = Logger.getLogger(BootstrapApp.class);
	@Inject
	UserService userService;

	@PostConstruct
	void init() {

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
