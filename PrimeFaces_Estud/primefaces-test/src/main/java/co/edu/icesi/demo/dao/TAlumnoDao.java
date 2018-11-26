package co.edu.icesi.demo.dao;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import co.edu.icesi.demo.model.TAlumno;


public class TAlumnoDao{

	
	public void save(EntityManager entityManager, TAlumno entity) {
		entityManager.persist(entity);		
		
	}

	public void update(EntityManager entityManager, TAlumno entity) {
		entityManager.merge(entity);		
		
	}

	public void delete(EntityManager entityManager, TAlumno entity) {
		entityManager.remove(entity);		
		
	}

	public TAlumno findById(EntityManager entityManager, String codigo) {
		return entityManager.find(TAlumno.class, codigo);		
	}

	public List<TAlumno> findAll(EntityManager entityManager) {
		String jpql = "Select a from TAlumno a";
		return 	entityManager.createQuery(jpql).getResultList();	
	}
	
}
