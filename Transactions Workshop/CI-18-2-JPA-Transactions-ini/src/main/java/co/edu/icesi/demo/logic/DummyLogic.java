package co.edu.icesi.demo.logic;

import java.util.ArrayList;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import co.edu.icesi.demo.dao.ITAlumnoDao;
import co.edu.icesi.demo.dao.ITCarreraDao;
import co.edu.icesi.demo.dao.ITFacultadDao;
import co.edu.icesi.demo.dao.ITProgAlumnoDao;
import co.edu.icesi.demo.dao.ITProgramaDao;
import co.edu.icesi.demo.model.TAlumno;
import co.edu.icesi.demo.model.TCarrera;
import co.edu.icesi.demo.model.TFacultad;
import co.edu.icesi.demo.model.TProgAlumno;
import co.edu.icesi.demo.model.TProgAlumnoPK;
import co.edu.icesi.demo.model.TPrograma;

//el service lo define como un bean
@Service
public class DummyLogic implements IDummyLogic {

	@Autowired
	private ITCarreraDao carreraDao;

	@Autowired
	private ITFacultadDao facultadDao;

	@Autowired
	private ITAlumnoDao alumnoDao;

	@Autowired
	private ITProgAlumnoDao progAlumnoDao;

	@Autowired
	private ITProgramaDao programaDao;

	@Transactional
	public void createAlumno() {

		TCarrera tcarrera = new TCarrera();
		tcarrera.setCodigo("18");
		tcarrera.setDescripcion("Ingenieria Sistemas");
		tcarrera.setTProgramas(new ArrayList<TPrograma>());

		TFacultad tfacultad = new TFacultad();
		tfacultad.setCodigo("z1");
		tfacultad.setDescripcion("facultad personalizada");
		tfacultad.setTCarreras(new ArrayList<TCarrera>());

		tcarrera.setTFacultad(tfacultad);
		tfacultad.getTCarreras().add(tcarrera);

		facultadDao.save(tfacultad);
		carreraDao.save(tcarrera);

		TPrograma tprograma = new TPrograma();
		tprograma.setCodigo("18");
		tprograma.setAlias("SIS");
		tprograma.setDescripcion("Ingenieria Sistemas");
		tprograma.setTProgAlumnos(new ArrayList<TProgAlumno>());
		tprograma.setTCarrera(tcarrera);
		programaDao.save(tprograma);
		TAlumno talumno = new TAlumno();
		talumno.setNombre("Jack");
		talumno.setApellidos("Bauer");
		talumno.setCodigo("a174815");
		talumno.setSexo("M");
		talumno.setTipo("E");
		talumno.setTProgAlumnos(new ArrayList<TProgAlumno>());

		alumnoDao.save(talumno);

		TProgAlumnoPK tprogalumnopk = new TProgAlumnoPK();
		tprogalumnopk.setAlumnoCodigo(talumno.getCodigo());
		tprogalumnopk.setPeriodoAcad("182");
		tprogalumnopk.setPrincipal("S");
		tprogalumnopk.setProgramaCodigo(tprograma.getCodigo());

		TProgAlumno tprogalumno = new TProgAlumno();
		tprogalumno.setSemestre("1");
		tprogalumno.setCohorte("182");
		tprogalumno.setId(tprogalumnopk);
		tprogalumno.setTAlumno(talumno);
		tprogalumno.setTPrograma(tprograma);

		progAlumnoDao.save(tprogalumno);

	}
}
