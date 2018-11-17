package co.edu.icesi.demo.logic;

import java.util.List;

import javax.ejb.Local;

import co.edu.icesi.demo.model.TAlumno;
import co.edu.icesi.demo.model.TProgAlumno;
@Local
public interface IEstudianteLogicLocal {

	public void createAlumno(TAlumno talumno);
	
	public List<String> listaProgramas();
}
