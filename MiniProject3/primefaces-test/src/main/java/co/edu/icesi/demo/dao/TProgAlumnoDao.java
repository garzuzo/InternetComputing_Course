package co.edu.icesi.demo.dao;

import java.util.List;

import javax.persistence.EntityManager;

import co.edu.icesi.demo.model.TProgAlumno;

public class TProgAlumnoDao{

	public void save(EntityManager entityManager, TProgAlumno entity) {
		entityManager.persist(entity);		
		
	}

	public void update(EntityManager entityManager, TProgAlumno entity) {
		entityManager.merge(entity);		
		
	}

	public void delete(EntityManager entityManager, TProgAlumno entity) {
		entityManager.remove(entity);		
		
	}

	public TProgAlumno findById(EntityManager entityManager, String codigo) {
		return entityManager.find(TProgAlumno.class, codigo);		
	}

	public List<TProgAlumno> findAll(EntityManager entityManager) {
		String jpql = "Select a from TProgAlumno a";
		return 	entityManager.createQuery(jpql).getResultList();	
	}
	
}
