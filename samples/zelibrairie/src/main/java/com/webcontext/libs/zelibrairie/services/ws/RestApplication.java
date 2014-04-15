package com.webcontext.libs.zelibrairie.services.ws;

import java.util.HashSet;
import java.util.Set;

import javax.ws.rs.Path;
import javax.ws.rs.core.Application;

import com.webcontext.libs.zelibrairie.model.User;
import com.webcontext.libs.zelibrairie.services.UserService;

@Path("/rest")
public class RestApplication extends Application {

	/*
	 * (non-Javadoc)
	 * 
	 * @see javax.ws.rs.core.Application#getClasses()
	 */
	@Override
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

		if(userService.count()==0){
			userService.add(new User("admin","Admin","Istrator","admin@localhost.home","psswd"));
		}
	}
}


