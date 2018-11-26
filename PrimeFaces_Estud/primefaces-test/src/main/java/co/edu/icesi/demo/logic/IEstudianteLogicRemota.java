package co.edu.icesi.demo.logic;

import java.util.List;

import co.edu.icesi.demo.model.TAlumno;
import co.edu.icesi.demo.model.TProgAlumno;
import co.edu.icesi.demo.model.TPrograma;

public interface IEstudianteLogicRemota {

	public void createAlumno(TAlumno talumno);

	public List<String> listaProgramas();

	public TAlumno consultarEstudiante(String codigo);

	public void editarAlumno(TAlumno talumno);

	public TPrograma consultarPrograma(String programa);

	public void borrarAlumno(TAlumno talumno);

}
