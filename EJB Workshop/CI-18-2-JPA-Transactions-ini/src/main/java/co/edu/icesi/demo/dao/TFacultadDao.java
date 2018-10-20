package co.edu.icesi.demo.dao;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Repository;

import co.edu.icesi.demo.model.TFacultad;

@Repository
@Scope("singleton")
public class TFacultadDao implements ITFacultadDao{

	@PersistenceContext
	private EntityManager entityManager;
	
	public EntityManager getEntityManager() {
		return entityManager;
	}

	public void setEntityManager(EntityManager entityManager) {
		this.entityManager = entityManager;
	}

	@Override
	public void save(TFacultad entity) {
		entityManager.persist(entity);		
		
	}

	@Override
	public void update(TFacultad entity) {
		entityManager.merge(entity);		
		
	}

	@Override
	public void delete(TFacultad entity) {
		entityManager.remove(entity);		
		
	}

	@Override
	public TFacultad findById(String codigo) {
		return entityManager.find(TFacultad.class, codigo);		
	}

	@Override
	public List<TFacultad> findAll() {
		String jpql = "Select a from TFacultad a";
		return 	entityManager.createQuery(jpql).getResultList();	
	}
	
}
