/**
 * 
 */
package com.webcontext.libs.zelibrairie;

import java.util.HashMap;
import java.util.Map;

import com.webcontext.libs.zelibrairie.model.User;

/**
 * Service to manage users. Can add update, delete and retrieve any users.
 * 
 * @author Frédéric Delorme<frederic.delorme@web-context.com>
 * 
 */
public class UserService {

	Map<String, User> users = new HashMap<String, User>();

	/**
	 * add a new User to the system.
	 * 
	 * @param user
	 * @return
	 */
	public User add(User user) {
		return user;
	}

}
