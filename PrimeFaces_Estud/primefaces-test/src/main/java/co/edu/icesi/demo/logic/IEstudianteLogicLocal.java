package co.edu.icesi.demo.logic;

import java.util.List;

import co.edu.icesi.demo.model.TAlumno;
import co.edu.icesi.demo.model.TProgAlumno;

public interface IEstudianteLogicLocal {

	public void createAlumno(TAlumno talumno);
	
	public List<String> listaProgramas();

	public TAlumno consultarEstudiante(String codigo);
	
	public void editarAlumno(TAlumno talumno);
}
