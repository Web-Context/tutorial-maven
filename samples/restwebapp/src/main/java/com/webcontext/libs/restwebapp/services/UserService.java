package com.webcontext.libs.restwebapp.services;

import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.webcontext.libs.restwebapp.exception.EntityAlreadyExistsException;
import com.webcontext.libs.restwebapp.model.User;

/**
 * Service to manage users. Can add update, delete and retrieve any users.
 * 
 * @author Frédéric Delorme<frederic.delorme@web-context.com>
 * 
 */
public class UserService {

	/**
	 * list of users.
	 */
	private static Map<String, User> users = new HashMap<String, User>();

	/**
	 * Add a new User to the system.
	 * 
	 * @param user
	 *            Add a new user.
	 * @return
	 */
	public User add(User user) throws EntityAlreadyExistsException{
		if(!users.containsKey(user.getUsername())){
			users.put(user.getUsername(), user);
		}else{
			throw new EntityAlreadyExistsException("User "+user.getUsername()+" already exixts");
		}
		return user;
	}

	/**
	 * Add a collection of User entities to the service.
	 * 
	 * @param users
	 *            List of users to add to the service.
	 */
	public void add(Collection<User> newUsers) {
		for (User user : newUsers) {
			users.put(user.getUsername(), user);
		}
	}

	/**
	 * Update some data on the <code>user</code> object.
	 * 
	 * @param user
	 *            update the user and persist.
	 * @return
	 */
	public User update(User user) {
		users.put(user.getUsername(), user);
		return user;
	}

	/**
	 * Delete the occurence of the <code>user</code> object.
	 * 
	 * @param user
	 *            the user to be deleted.
	 * @return
	 */
	public User delete(User user) {
		users.remove(user.getUsername());
		return user;
	}

	/**
	 * Find a <code>User</code> object for the <code>username</code>.
	 * 
	 * @param username
	 *            this is the username to search for.
	 * @return
	 */
	public User findByUsername(String username) {
		return users.get(username);
	}

	/**
	 * Retrieve all <code>User</code> entities managed by service
	 * 
	 * @return
	 */
	public List<User> findAll() {
		List<User> rtusers = (List<User>) users.values();
		return rtusers;
	}

	/**
	 * return the size of the managed <code>User</code> list.
	 * 
	 * @return
	 */
	public int count() {
		return users.size();
	}

}