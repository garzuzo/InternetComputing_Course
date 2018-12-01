package co.edu.icesi.demo.logic;

import java.util.ArrayList;
import javax.ejb.Local;
import javax.ejb.Remote;
import javax.ejb.Stateless;
import javax.ejb.TransactionAttribute;
import javax.ejb.TransactionAttributeType;
import javax.ejb.TransactionManagement;
import javax.ejb.TransactionManagementType;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import co.edu.icesi.demo.dao.TAlumnoDao;
import co.edu.icesi.demo.dao.TCarreraDao;
import co.edu.icesi.demo.dao.TFacultadDao;
import co.edu.icesi.demo.dao.TProgAlumnoDao;
import co.edu.icesi.demo.dao.TProgramaDao;
import co.edu.icesi.demo.model.TAlumno;
import co.edu.icesi.demo.model.TCarrera;
import co.edu.icesi.demo.model.TFacultad;
import co.edu.icesi.demo.model.TProgAlumno;
import co.edu.icesi.demo.model.TProgAlumnoPK;
import co.edu.icesi.demo.model.TPrograma;

public class DummyLogic implements IDummyLogicLocal, IDummyLogicRemota {
	

	private EntityManager entityManager;
	
	private TFacultadDao tfacultadDao;

	private TCarreraDao tcarreraDao;
	
	private TProgramaDao tprogramaDao;
		
	private TAlumnoDao talumnoDao;
	
	private TProgAlumnoDao tproalumnoDao;
	
	public void createAlumno() {

		tfacultadDao = new TFacultadDao ();

		tcarreraDao = new TCarreraDao();
		
		tprogramaDao = new TProgramaDao();
			
		talumnoDao = new TAlumnoDao();
		
		tproalumnoDao = new TProgAlumnoDao();
		
		TFacultad tfacultad = tfacultadDao.findById(entityManager,"01");
		
		TCarrera tcarrera = new TCarrera();
		tcarrera.setCodigo("18");
		tcarrera.setDescripcion("Ingenieria Sistemas");
		tcarrera.setTFacultad(tfacultad);
		tcarrera.setTProgramas(new ArrayList<TPrograma>());

		tfacultad.setTCarreras(new ArrayList<TCarrera>());
		tfacultad.addTCarrera(tcarrera);
		
		TPrograma tprograma = new TPrograma();
		tprograma.setCodigo("18");
		tprograma.setAlias("SIS");
		tprograma.setDescripcion("Ingenieria Sistemas");
		tprograma.setTCarrera(tcarrera);
		tprograma.setTProgAlumnos(new ArrayList<TProgAlumno>());

		tcarrera.addTPrograma(tprograma);

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
		tprogalumno.setTAlumno(talumno);
		tprogalumno.setTPrograma(tprograma);
		tprogalumno.setId(tprogalumnopk);
		

		tprograma.addTProgAlumno(tprogalumno);
		talumno.addTProgAlumno(tprogalumno);

		tcarreraDao.save(entityManager,tcarrera);
		tprogramaDao.save(entityManager, tprograma);
		talumnoDao.save(entityManager, talumno);
		tproalumnoDao.save(entityManager, tprogalumno);

	}
}
