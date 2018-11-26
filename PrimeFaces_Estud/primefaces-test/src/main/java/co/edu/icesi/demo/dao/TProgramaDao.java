package co.edu.icesi.demo.dao;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import co.edu.icesi.demo.model.TPrograma;

public class TProgramaDao{

	public void save(EntityManager entityManager, TPrograma entity) {
		entityManager.persist(entity);		
		
	}

	public void update(EntityManager entityManager, TPrograma entity) {
		entityManager.merge(entity);		
		
	}

	public void delete(EntityManager entityManager, TPrograma entity) {
		entityManager.remove(entity);		
		
	}

	public TPrograma findById(EntityManager entityManager, String codigo) {
		return entityManager.find(TPrograma.class, codigo);		
	}

	public List<TPrograma> findAll(EntityManager entityManager) {
		String jpql = "Select a from TPrograma a";
		return 	entityManager.createQuery(jpql).getResultList();	
	}
	
}
