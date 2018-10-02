package co.icesi.edu.demo.testlogic;


import static org.junit.Assert.*;

import java.util.ArrayList;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import co.edu.icesi.demo.dao.ITAlumnoDao;
import co.edu.icesi.demo.dao.ITProgramaDao;
import co.edu.icesi.demo.logic.IEstudianteLogic;
import co.edu.icesi.demo.model.TAlumno;
import co.edu.icesi.demo.model.TProgAlumno;
import co.edu.icesi.demo.model.TProgAlumnoPK;
import co.edu.icesi.demo.model.TPrograma;


@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration("/applicationContext.xml")
@Rollback(false)
public class TestEstudianteLogic {

	@Autowired
	private IEstudianteLogic alumnoLogic;
	
	@Autowired
	private ITProgramaDao programaDao;
	
@Test
	public void testCrearAlumno() {

		assertNotNull(alumnoLogic);
		
		TAlumno talumno=new TAlumno();
		TProgAlumno tprogalumno=new TProgAlumno();
		TPrograma tprograma = programaDao.findAll().get(0);

		TProgAlumnoPK tprogalumnopk = new TProgAlumnoPK();
		tprogalumnopk.setAlumnoCodigo(talumno.getCodigo());
		tprogalumnopk.setPeriodoAcad("182");
		tprogalumnopk.setPrincipal("S");
		tprogalumnopk.setProgramaCodigo(tprograma.getCodigo());
		
		tprogalumno.setCohorte("182");
		tprogalumno.setId(tprogalumnopk);
		tprogalumno.setSemestre("2");
		
		tprogalumno.setTPrograma(tprograma);

		talumno.setNombre("Joe");
		talumno.setApellidos("Doe");
		talumno.setCodigo("a1748219");
		talumno.setSexo("M");
		talumno.setTipo("E");
		talumno.setTProgAlumnos(new ArrayList<TProgAlumno>());
		talumno.getTProgAlumnos().add(tprogalumno);
		tprogalumno.setTAlumno(talumno);
		tprogalumnopk.setAlumnoCodigo(talumno.getCodigo());
		alumnoLogic.createAlumno(talumno, tprogalumno);
		
	}
	
}
