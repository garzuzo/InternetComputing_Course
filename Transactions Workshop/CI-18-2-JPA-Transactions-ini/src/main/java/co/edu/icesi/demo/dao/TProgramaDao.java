package co.edu.icesi.demo.dao;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Repository;

import co.edu.icesi.demo.model.TPrograma;

@Repository
@Scope("singleton")
public class TProgramaDao implements ITProgramaDao{

	@PersistenceContext
	private EntityManager entityManager;
	
	@Override
	public void save(TPrograma entity) {
		entityManager.persist(entity);		
		
	}

	@Override
	public void update(TPrograma entity) {
		entityManager.merge(entity);		
		
	}

	@Override
	public void delete(TPrograma entity) {
		entityManager.remove(entity);		
		
	}

	@Override
	public TPrograma findById(String codigo) {
		return entityManager.find(TPrograma.class, codigo);		
	}

	@Override
	public List<TPrograma> findAll() {
		String jpql = "Select a from TPrograma a";
		return 	entityManager.createQuery(jpql).getResultList();	
	}
	
}
