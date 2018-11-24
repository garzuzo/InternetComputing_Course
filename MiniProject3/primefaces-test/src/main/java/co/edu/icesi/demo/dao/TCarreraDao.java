package co.edu.icesi.demo.dao;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import co.edu.icesi.demo.model.TCarrera;

public class TCarreraDao{

	public void save(EntityManager entityManager, TCarrera entity) {
		entityManager.persist(entity);		
		
	}

	public void update(EntityManager entityManager, TCarrera entity) {
		entityManager.merge(entity);		
		
	}

	public void delete(EntityManager entityManager, TCarrera entity) {
		entityManager.remove(entity);		
		
	}

	public TCarrera findById(EntityManager entityManager, String codigo) {
		return entityManager.find(TCarrera.class, codigo);		
	}

	public List<TCarrera> findAll(EntityManager entityManager) {
		String jpql = "Select a from TCarrera a";
		return 	entityManager.createQuery(jpql).getResultList();	
	}
	
}
