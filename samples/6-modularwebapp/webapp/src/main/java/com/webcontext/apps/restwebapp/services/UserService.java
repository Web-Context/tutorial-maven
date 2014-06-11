package com.webcontext.apps.restwebapp.services;

import java.util.Collection;
import java.util.List;

import javax.ejb.EJB;
import javax.ejb.Stateless;

import com.webcontext.apps.restwebapp.exceptions.EntityAlreadyExistsException;
import com.webcontext.apps.services.user.UserManager;
import com.webcontext.apps.services.user.model.User;

/**
 * Service to manage users. Can add update, delete and retrieve any users.
 * 
 * @author Frédéric Delorme<frederic.delorme@web-context.com>
 * 
 */
@Stateless
public class UserService {

	@EJB
	private UserManager userMgr;

	/**
	 * Add a new User to the system.
	 * 
	 * @param user
	 *            Add a new user.
	 * @return
	 */
	public User add(User user) throws EntityAlreadyExistsException {
		if (userMgr.findByUsername(user.getUsername()) == null) {
			userMgr.save(user);
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
		userMgr.save(user);
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
		userMgr.delete(user);
	}

	/**
	 * Find a <code>User</code> object for the <code>username</code>.
	 * 
	 * @param username
	 *            this is the username to search for.
	 * @return
	 */
	public User findByUsername(String username) {
		return userMgr.findByUsername(username);
	}

	/**
	 * Retrieve all <code>User</code> entities managed by service
	 * 
	 * @return
	 */
	public List<User> findAll() {
		return userMgr.findAll();
	}

	/**
	 * return the size of the managed <code>User</code> list.
	 * 
	 * @return
	 */
	public long count() {
		return userMgr.count();
	}

}