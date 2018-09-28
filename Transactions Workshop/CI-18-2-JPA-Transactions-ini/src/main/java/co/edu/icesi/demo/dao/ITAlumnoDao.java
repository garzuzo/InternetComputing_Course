package co.edu.icesi.demo.dao;

import java.util.List;

import co.edu.icesi.demo.model.TAlumno;

public interface ITAlumnoDao {

	public void save(TAlumno entity);
	public void update(TAlumno entity);
	public void delete(TAlumno entity);
	public TAlumno findById(String codigo);
	public List<TAlumno> findAll();
	
}
