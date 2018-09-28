package co.edu.icesi.demo.dao;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Repository;

import co.edu.icesi.demo.model.TAlumno;
import co.edu.icesi.demo.model.TProgAlumno;

@Repository
@Scope("singleton")
public class TProgAlumnoDao implements ITProgAlumnoDao{

	@PersistenceContext
	private EntityManager entityManager;
	
	@Override
	public void save(TProgAlumno entity) {
		entityManager.persist(entity);		
		
	}

	@Override
	public void update(TProgAlumno entity) {
		entityManager.merge(entity);		
		
	}

	@Override
	public void delete(TProgAlumno entity) {
		entityManager.remove(entity);		
		
	}

	@Override
	public TProgAlumno findById(String codigo) {
		return entityManager.find(TProgAlumno.class, codigo);		
	}

	@Override
	public List<TProgAlumno> findAll() {
		String jpql = "Select a from TProgAlumno a";
		return 	entityManager.createQuery(jpql).getResultList();	
	}
	
}
