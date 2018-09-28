package co.edu.icesi.demo.dao;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Repository;

import co.edu.icesi.demo.model.TCarrera;

@Repository
@Scope("singleton")
public class TCarreraDao implements ITCarreraDao{

	@PersistenceContext
	private EntityManager entityManager;
	
	@Override
	public void save(TCarrera entity) {
		entityManager.persist(entity);		
		
	}

	@Override
	public void update(TCarrera entity) {
		entityManager.merge(entity);		
		
	}

	@Override
	public void delete(TCarrera entity) {
		entityManager.remove(entity);		
		
	}

	@Override
	public TCarrera findById(String codigo) {
		return entityManager.find(TCarrera.class, codigo);		
	}

	@Override
	public List<TCarrera> findAll() {
		String jpql = "Select a from TCarrera a";
		return 	entityManager.createQuery(jpql).getResultList();	
	}
	
}
