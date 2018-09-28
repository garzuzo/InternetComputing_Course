package co.edu.icesi.demo.dao;

import java.util.List;

import co.edu.icesi.demo.model.TCarrera;

public interface ITCarreraDao {

	public void save(TCarrera entity);
	public void update(TCarrera entity);
	public void delete(TCarrera entity);
	public TCarrera findById(String codigo);
	public List<TCarrera> findAll();
	
}
