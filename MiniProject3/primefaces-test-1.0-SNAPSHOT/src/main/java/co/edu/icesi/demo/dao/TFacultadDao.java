package co.edu.icesi.demo.dao;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import co.edu.icesi.demo.model.TFacultad;

public class TFacultadDao{

	public void save(EntityManager entityManager, TFacultad entity) {
		entityManager.persist(entity);		
		
	}

	public void update(EntityManager entityManager, TFacultad entity) {
		entityManager.merge(entity);		
		
	}

	public void delete(EntityManager entityManager, TFacultad entity) {
		entityManager.remove(entity);		
		
	}

	public TFacultad findById(EntityManager entityManager, String codigo) {
		return entityManager.find(TFacultad.class, codigo);		
	}

	public List<TFacultad> findAll(EntityManager entityManager) {
		String jpql = "Select a from TFacultad a";
		return 	entityManager.createQuery(jpql).getResultList();	
	}
	
}
