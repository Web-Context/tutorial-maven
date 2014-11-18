package com.webcontext.apps.restwebapp.managers;

import java.util.ArrayList;
import java.util.List;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import com.webcontext.apps.restwebapp.exceptions.DaoException;
import com.webcontext.apps.restwebapp.model.User;

/**
 * Manager to maintains {@link User} entity list in the persistence system.
 * 
 * @author Frédéric Delorme<frederic.delorme@gmail.com>
 */
@Stateless(name = "UserServiceManager")
public class UserManager {

	@PersistenceContext(unitName = "defaultPersistenceUnit")
	private EntityManager em;

	/**
	 * Retrieve <code>pageSize</code> users from persistence from
	 * <code>offset</code> position.
	 * 
	 * @param offset
	 *            start index to retrieve data
	 * @param pageSize
	 *            number of item to bne returned.
	 * @return a List of {@link User}
	 */
	@SuppressWarnings("unchecked")
	public List<User> findAll(int offset, int pageSize) {
		final Query query = em.createNamedQuery("findAll", User.class);
		if (pageSize != 0 || offset != 0) {
			if (offset > 0) {
				query.setFirstResult(offset);
			}
			if (pageSize != 0) {
				query.setMaxResults(pageSize);
			}
		}
		List<User> users = query.getResultList();
		if (users == null) {
			users = new ArrayList<User>();
		}
		return users;
	}

	/**
	 * Retrieve all Users.
	 * 
	 * @return a List of {@link User}.
	 */
	public List<User> findAll() {
		return findAll(0, 0);
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
	 * Save a {@link User} <code>user</code>
	 * 
	 * @param user
	 *            the {@link User} entity to be saved.
	 * @return the return the saved user.
	 */
	public User save(final User user) {
		em.persist(user);
		return user;
	}

	/**
	 * Delete the {@link User} <code>user</code>.
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
	 * Delete the {@link User} <code>user</code>.
	 * 
	 * @param user
	 *            the user to be deleted.
	 */
	public void delete(User user) {
		user = em.merge(user);
		em.remove(user);
	}

	/**
	 * Return number of occurrences for {@link User}.
	 * 
	 * @return a long value containing the number of occurence or {@link User}
	 *         in the persistence system.
	 */
	public long count() {
		final Query query = em.createNamedQuery("count");
		long count = ((Number) query.getSingleResult()).longValue();
		return count;
	}

	/**
	 * Retrieve one {@link User} on its <code>userId</code>.
	 * 
	 * @param userId
	 *            the unique internal ID for a user.
	 * @return the {@link User} entity corresponding to the <code>userId</code>.
	 */
	public User findById(Long userId) {
		final Query query = em.createNamedQuery("findById", User.class);
		User user = (User) query.getSingleResult();
		return user;
	}

}