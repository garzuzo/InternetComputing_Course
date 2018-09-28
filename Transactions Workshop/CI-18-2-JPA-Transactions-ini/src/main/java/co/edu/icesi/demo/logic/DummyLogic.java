package co.edu.icesi.demo.logic;

import java.util.ArrayList;
import co.edu.icesi.demo.model.TAlumno;
import co.edu.icesi.demo.model.TCarrera;
import co.edu.icesi.demo.model.TProgAlumno;
import co.edu.icesi.demo.model.TProgAlumnoPK;
import co.edu.icesi.demo.model.TPrograma;

public class DummyLogic implements IDummyLogic {
	

	public void createAlumno() {

		
		TCarrera tcarrera = new TCarrera();
		tcarrera.setCodigo("18");
		tcarrera.setDescripcion("Ingenieria Sistemas");
		tcarrera.setTProgramas(new ArrayList<TPrograma>());

		TPrograma tprograma = new TPrograma();
		tprograma.setCodigo("18");
		tprograma.setAlias("SIS");
		tprograma.setDescripcion("Ingenieria Sistemas");
		tprograma.setTProgAlumnos(new ArrayList<TProgAlumno>());

		TAlumno talumno = new TAlumno();
		talumno.setNombre("Jack");
		talumno.setApellidos("Bauer");
		talumno.setCodigo("a174815");
		talumno.setSexo("M");
		talumno.setTipo("E");		
		talumno.setTProgAlumnos(new ArrayList<TProgAlumno>());

		TProgAlumnoPK tprogalumnopk = new TProgAlumnoPK ();
		tprogalumnopk.setAlumnoCodigo(talumno.getCodigo());
		tprogalumnopk.setPeriodoAcad("182");
		tprogalumnopk.setPrincipal("S");
		tprogalumnopk.setProgramaCodigo(tprograma.getCodigo());
		
		TProgAlumno tprogalumno = new TProgAlumno();
		tprogalumno.setSemestre("1");
		tprogalumno.setCohorte("182");
		tprogalumno.setId(tprogalumnopk);		

	}
}
