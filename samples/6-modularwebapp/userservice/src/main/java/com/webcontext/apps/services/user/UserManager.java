package com.webcontext.apps.services.user;

import java.util.ArrayList;
import java.util.List;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import com.webcontext.apps.services.user.exceptions.DaoException;
import com.webcontext.apps.services.user.model.User;

/**
 * User Enterprise Java Bean to manage User entity.
 * 
 * @author Frédéric Delorme<frederic.delorme@gmail.com>
 */
@Stateless
public class UserManager {

	@PersistenceContext(unitName = "defaultPersistenceUnit")
	private EntityManager em;

	/**
	 * Retrieve all users from persistence.
	 */
	@SuppressWarnings("unchecked")
	public List<User> findAll() {
		final Query query = em.createNamedQuery("findAll", User.class);
		List<User> users = query.getResultList();
		if (users == null) {
			users = new ArrayList<User>();
		}
		return users;
	}

	/**
	 * Retrieve the User on its <code>username</code>.
	 * 
	 * @param username
	 *            the <code>username</code> to search for.
	 * @return the matching User.
	 * 
	 */
	public User findByUsername(String username) {
		final Query query = em.createNamedQuery("findByUsername", User.class);
		em.setProperty("username", username);
		User user = (User) query.getSingleResult();

		return user;
	}

	/**
	 * Save a User <code>user</code>
	 * 
	 * @param user
	 *            the User entity to be saved.
	 * @return the return the saved user.
	 */
	public User save(final User user) {
		em.persist(user);
		return user;
	}

	/**
	 * Delete the User <code>user</code>.
	 * 
	 * @param user
	 *            the user to be deleted.
	 * @throws DaoException
	 */
	public void delete(String username) throws DaoException {
		User user = findByUsername(username);

		if (user != null) {
			em.remove(user);
		} else {
			throw new DaoException("User with name username='" + username
					+ "' does not exist.");
		}
	}

	/**
	 * Delete the User <code>user</code>.
	 * 
	 * @param user
	 *            the user to be deleted.
	 */
	public void delete(User user) {
		user = em.merge(user);
		em.remove(user);
	}

	/**
	 * Return number of occurrences for User.
	 * 
	 * @return
	 */
	public long count() {
		final Query query = em.createNamedQuery("count");
		long count = ((Number) query.getSingleResult()).longValue();
		return count;
	}

}