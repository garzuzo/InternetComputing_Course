package co.edu.icesi.demo.logic;

import co.edu.icesi.demo.exceptions.LogicException;
import co.edu.icesi.demo.model.TAlumno;
import co.edu.icesi.demo.model.TCarrera;
import co.edu.icesi.demo.model.TFacultad;
import co.edu.icesi.demo.model.TProgAlumno;
import co.edu.icesi.demo.model.TPrograma;

public class EstudiantesLogic implements IEstudianteLogic {

	
	public TFacultad getFacultad (String codigo) throws LogicException
	{
		return null;
	}

	public TCarrera getCarrera (String codigo) throws LogicException
	{
		return null;
	}
	
	public TPrograma getPrograma (String codigo) throws LogicException
	{
		return null;
	}
	
	public void createAlumno(TAlumno talumno, TProgAlumno tprogalumno) {
		//TODO validate the data is correct and persist the two entities.		
	}

}
