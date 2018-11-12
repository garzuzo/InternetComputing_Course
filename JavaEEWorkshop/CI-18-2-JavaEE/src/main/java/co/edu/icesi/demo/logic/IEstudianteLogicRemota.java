package co.edu.icesi.demo.logic;

import java.util.List;

import co.edu.icesi.demo.model.TAlumno;
import co.edu.icesi.demo.model.TProgAlumno;

public interface IEstudianteLogicRemota {

	public void createAlumno(TAlumno talumno);

	public List<String> listaProgramas();
}
