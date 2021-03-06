package com.searchpoint.dao;

import java.util.Collection;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.apache.log4j.Logger;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;


/**
 * Common data access object.
 * 
 * @author guligo
 */
@Component
@Transactional
public class CommonDAO {
	
	private final static Logger log = Logger.getLogger(CommonDAO.class);
	
	@PersistenceContext
	private EntityManager entityManager;

	public void save(PersistentEntity object) {
		if (object.getId() == null) {
			entityManager.persist(object);
		} else {
			entityManager.merge(object);
		}
	}
	
	public void saveAll(Collection<? extends PersistentEntity> objects) {
		for (PersistentEntity object: objects){
			save(object);
		}
	}

	public <T extends PersistentEntity> T getById(Class<T> clazz, Long id) {
		return entityManager.find(clazz, id);
	}
	
	@SuppressWarnings("unchecked")
	public <T extends PersistentEntity> List<T> findAll(Class<T> clazz) {
		String entity = clazz.getSimpleName();
		return entityManager.createQuery("select e FROM " + entity + " e").getResultList();
	}

	public void delete(PersistentEntity object) {
		entityManager.remove(object);
	}
	
	public void cleanupDB(){		
		for (PersistentEntityType clazz: PersistentEntityType.values()){
			List<? extends PersistentEntity> objects = findAll(clazz.getObjectClass());
			for (PersistentEntity o: objects){
				delete(o);
			}
		}		
		log.debug("Database is cleaned");
	}

	public EntityManager getEntityManager() {
		return entityManager;
	}
	
}
