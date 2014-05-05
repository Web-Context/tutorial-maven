package com.webcontext.apps.restwebapp.services;

import java.util.Collection;
import java.util.List;

import javax.ejb.Stateless;
import javax.inject.Inject;

import com.webcontext.apps.restwebapp.exception.EntityAlreadyExistsException;
import com.webcontext.apps.restwebapp.managers.UserManager;
import com.webcontext.apps.restwebapp.model.User;

/**
 * Service to manage users. Can add update, delete and retrieve any users.
 * 
 * @author Frédéric Delorme<frederic.delorme@web-context.com>
 * 
 */
@Stateless
public class UserService {

	@Inject
	private UserManager userEjb;

	/**
	 * Add a new User to the system.
	 * 
	 * @param user
	 *            Add a new user.
	 * @return
	 */
	public User add(User user) throws EntityAlreadyExistsException {
		if (userEjb.findByUsername(user.getUsername()) == null) {
			userEjb.save(user);
		} else {
			throw new EntityAlreadyExistsException("User " + user.getUsername()
				+ " already exixts");
		}
		return user;
	}

	/**
	 * Add a collection of User entities to the service.
	 * 
	 * @param users
	 *            List of users to add to the service.
	 * @throws EntityAlreadyExistsException
	 */
	public void add(Collection<User> newUsers)
	throws EntityAlreadyExistsException {
		for (User user : newUsers) {
			add(user);
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
		userEjb.save(user);
		return user;
	}

	/**
	 * Delete the occurrence of the <code>user</code> object.
	 * 
	 * @param user
	 *            the user to be deleted.
	 * @return
	 */
	public void delete(User user) {
		userEjb.delete(user);
	}

	/**
	 * Find a <code>User</code> object for the <code>username</code>.
	 * 
	 * @param username
	 *            this is the username to search for.
	 * @return
	 */
	public User findByUsername(String username) {
		return userEjb.findByUsername(username);
	}

	/**
	 * Retrieve all <code>User</code> entities managed by service
	 * 
	 * @return
	 */
	public List<User> findAll() {
		return userEjb.findAll();
	}

	/**
	 * return the size of the managed <code>User</code> list.
	 * 
	 * @return
	 */
	public long count() {
		return userEjb.count();
	}

}