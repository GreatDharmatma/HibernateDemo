package org.rapideagle.tutorial.hibernatedemo.entities;

// Generated 1 Jun, 2014 6:52:00 PM by Hibernate Tools 4.0.0

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

/**
 * Home object for domain model class Employee.
 * @see org.rapideagle.tutorial.hibernatedemo.entities.Employee
 * @author Hibernate Tools
 */
@Stateless
public class EmployeeHome {

	private static final Log log = LogFactory.getLog(EmployeeHome.class);

	@PersistenceContext
	private EntityManager entityManager;

	public void persist(Employee transientInstance) {
		log.debug("persisting Employee instance");
		try {
			entityManager.persist(transientInstance);
			log.debug("persist successful");
		} catch (RuntimeException re) {
			log.error("persist failed", re);
			throw re;
		}
	}

	public void remove(Employee persistentInstance) {
		log.debug("removing Employee instance");
		try {
			entityManager.remove(persistentInstance);
			log.debug("remove successful");
		} catch (RuntimeException re) {
			log.error("remove failed", re);
			throw re;
		}
	}

	public Employee merge(Employee detachedInstance) {
		log.debug("merging Employee instance");
		try {
			Employee result = entityManager.merge(detachedInstance);
			log.debug("merge successful");
			return result;
		} catch (RuntimeException re) {
			log.error("merge failed", re);
			throw re;
		}
	}

	public Employee findById(Long id) {
		log.debug("getting Employee instance with id: " + id);
		try {
			Employee instance = entityManager.find(Employee.class, id);
			log.debug("get successful");
			return instance;
		} catch (RuntimeException re) {
			log.error("get failed", re);
			throw re;
		}
	}
}
