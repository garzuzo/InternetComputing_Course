package co.icesi.edu.demo.testlogic;


import static org.junit.Assert.*;

import java.util.ArrayList;

import org.junit.Test;
import co.edu.icesi.demo.logic.IEstudianteLogicRemota;
import co.edu.icesi.demo.model.TAlumno;
import co.edu.icesi.demo.model.TProgAlumno;

public class TestAlumnoLogic {

	private IEstudianteLogicRemota alumnoLogic;
	
	@Test
	public void testCrearAlumno() {

		assertNotNull(alumnoLogic);
		
		TAlumno talumno = new TAlumno();
		talumno.setNombre("Jack");
		talumno.setApellidos("Bauer");
		talumno.setCodigo("a154815");
		talumno.setSexo("M");
		talumno.setTipo("E");
		talumno.setTProgAlumnos(new ArrayList<TProgAlumno>());
		
	//	alumnoLogic.createAlumno(talumno);
		
	}
	
}
