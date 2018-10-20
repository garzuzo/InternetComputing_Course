package co.edu.icesi.demo.dao;

import java.util.List;

import co.edu.icesi.demo.model.TAlumno;
import co.edu.icesi.demo.model.TProgAlumno;

public interface ITProgAlumnoDao {

	public void save(TProgAlumno entity);
	public void update(TProgAlumno entity);
	public void delete(TProgAlumno entity);
	public TProgAlumno findById(String codigo);
	public List<TProgAlumno> findAll();
	
}
