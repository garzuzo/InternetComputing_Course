package co.edu.icesi.demo.logic;

import java.util.List;

import javax.ejb.Remote;

import co.edu.icesi.demo.model.TAlumno;
import co.edu.icesi.demo.model.TProgAlumno;
import co.edu.icesi.demo.model.TPrograma;
@Remote
public interface IEstudianteLogicRemota {

	public void createAlumno(TAlumno talumno);

	public List<String> listaProgramas();
	
	public TPrograma consultarPrograma(String programa);
	public TAlumno consultarAlumno(String codigo);
	public void updateAlumno(TAlumno talumno);
	public void deleteAlumno(TAlumno alumno);
	
}
