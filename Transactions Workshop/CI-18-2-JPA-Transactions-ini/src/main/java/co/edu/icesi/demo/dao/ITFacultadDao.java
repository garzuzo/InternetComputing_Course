package co.edu.icesi.demo.dao;

import java.util.List;

import javax.persistence.EntityManager;

import co.edu.icesi.demo.model.TFacultad;

public interface ITFacultadDao {

	public void save(TFacultad entity);
	public void update(TFacultad entity);
	public void delete(TFacultad entity);
	public TFacultad findById(String codigo);
	public List<TFacultad> findAll();
	public EntityManager getEntityManager() ;
	public void setEntityManager(EntityManager entityManager) ;
}
