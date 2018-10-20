package co.edu.icesi.demo.dao;

import java.util.List;

import co.edu.icesi.demo.model.TPrograma;

public interface ITProgramaDao {

	public void save(TPrograma entity);
	public void update(TPrograma entity);
	public void delete(TPrograma entity);
	public TPrograma findById(String codigo);
	public List<TPrograma> findAll();
	
}
