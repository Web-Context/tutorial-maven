package com.webcontext.apps.sparkweb.dao;

import java.io.Serializable;
import java.lang.reflect.ParameterizedType;
import java.util.List;
import java.util.Map;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.TypedQuery;

import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.criterion.Criterion;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Projections;

/**
 * AbstractDAO providing base of all database operation on entity T.
 * 
 * largely inspired by the following GIST: https://gist.github.com/1261256
 * Thanks to augusto for providing such a cool implementation.
 * 
 * @author Frédéric Delorme <frederic.delorme@gmail.com>
 * 
 * @param <T>
 * @param <PK>
 */
public class GenericDao<T extends Serializable, PK extends Serializable> {

	/**
	 * Entity managed by this DAO.
	 */
	protected Class<T> entityClass;

	protected EntityManagerFactory emf;

	/**
	 * Where is the database.
	 */
	protected EntityManager em;

	/**
	 * Default constructor.
	 */
	public GenericDao() {
		ParameterizedType genericSuperClass = (ParameterizedType) getClass()
				.getGenericSuperclass();
		@SuppressWarnings("unchecked")
		Class<T> class1 = (Class<T>) genericSuperClass.getActualTypeArguments()[0];

		this.entityClass = class1;
	}

	/**
	 * Initialize DAO on <code>unitName</code> persistence unit..
	 */
	public GenericDao(String unitName) {
		this();
		this.emf = Persistence.createEntityManagerFactory(unitName);
		this.em = emf.createEntityManager();
	}

	/**
	 * Persist T <code>entity</code> to database.
	 * 
	 * @param entity
	 * @return
	 */
	public T save(T entity) {
		this.em.persist(entity);
		return entity;
	}

	/**
	 * Retrieve a <code>T</code> entity for <code>id</code>.
	 * 
	 * @param id
	 * @return
	 */
	public T findById(PK id) {
		return this.em.find(entityClass, id);
	}

	/**
	 * Retrieve all <code>T</code> entity.
	 * 
	 * @return
	 */
	public List<T> findAll() {
		return findByCriteria();
	}

	/**
	 * Retrieve <code>T</code> entity based on named query <code>name</code>
	 * with <code>params</code>.
	 * 
	 * @param name
	 * @param params
	 * @return
	 */
	public List<T> findByNamedQuery(final String name, Object... params) {
		TypedQuery<T> query = em.createNamedQuery(name, entityClass);

		for (int i = 0; i < params.length; i++) {
			query.setParameter(i + 1, params[i]);
		}

		return (List<T>) query.getResultList();
	}

	/**
	 * Map String <code>params</code> name and there values to call and execute
	 * a namedQuery <code>name</code>.
	 * 
	 * @param name
	 * @param params
	 * @return
	 */
	public List<T> findByNamedQueryAndNamedParams(final String name,
			final Map<String, ?> params) {
		TypedQuery<T> query = em.createNamedQuery(name, entityClass);

		for (final Map.Entry<String, ?> param : params.entrySet()) {
			query.setParameter(param.getKey(), param.getValue());
		}

		return query.getResultList();
	}

	/**
	 * count entities T.
	 * 
	 * @return
	 */
	public int countAll() {
		return countByCriteria();
	}

	/**
	 * merge T entity.
	 * 
	 * @param entity
	 * @return
	 */
	public T merge(T entity) {
		return this.em.merge(entity);
	}

	/**
	 * delete T entity from persistence.
	 * 
	 * @param entity
	 */
	public void delete(T entity) {
		this.em.remove(entity);
	}

	public T deleteById(PK key) {
		T entity = this.em.find(entityClass, key);
		this.em.remove(entity);
		return entity;
	}

	protected List<T> findByCriteria(final Criterion... criterion) {
		return findByCriteria(-1, -1, null, criterion);
	}

	protected List<T> findByCriteria(final int firstResult,
			final int maxResults, final Order order,
			final Criterion... criterion) {
		Session session = (Session) em.getDelegate();
		Criteria crit = session.createCriteria(entityClass);

		for (final Criterion c : criterion) {
			if (c != null) {
				crit.add(c);
			}
		}

		if (order != null) {
			crit.addOrder(order);
		}

		if (firstResult > 0) {
			crit.setFirstResult(firstResult);
		}

		if (maxResults > 0) {
			crit.setMaxResults(maxResults);
		}

		return crit.list();
	}

	protected int countByCriteria(Criterion... criterion) {
		Session session = (Session) em.getDelegate();
		Criteria crit = session.createCriteria(entityClass);
		crit.setProjection(Projections.rowCount());

		for (final Criterion c : criterion) {
			if (c != null) {
				crit.add(c);
			}
		}

		return ((Long) crit.list().get(0)).intValue();
	}
}