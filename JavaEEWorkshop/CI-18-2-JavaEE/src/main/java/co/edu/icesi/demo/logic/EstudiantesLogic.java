package co.edu.icesi.demo.logic;

import java.util.ArrayList;
import java.util.List;

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
import co.edu.icesi.demo.exceptions.LogicException;
import co.edu.icesi.demo.model.TAlumno;
import co.edu.icesi.demo.model.TCarrera;
import co.edu.icesi.demo.model.TFacultad;
import co.edu.icesi.demo.model.TProgAlumno;
import co.edu.icesi.demo.model.TProgAlumnoPK;
import co.edu.icesi.demo.model.TPrograma;

@Stateless(name = "EstudiantesLogic", mappedName = "EstudiantesLogic")
@TransactionAttribute(TransactionAttributeType.REQUIRED)
@TransactionManagement(TransactionManagementType.CONTAINER)
@Local(IEstudianteLogicLocal.class)
@Remote(IEstudianteLogicRemota.class)
public class EstudiantesLogic implements IEstudianteLogicRemota, IEstudianteLogicLocal {

	@PersistenceContext
	private EntityManager entityManager;

	private TAlumnoDao talumnoDao;

	private TCarreraDao tcarreraDao;

	private TProgramaDao tprogramaDao;

	private TFacultadDao tfacultadDao;

	private TProgAlumnoDao tprogAlumnoDao;

	private String programa;

	public TFacultad getFacultad(String codigo) throws LogicException {
		TFacultad tfacultad = tfacultadDao.findById(entityManager, codigo);
		if (tfacultad == null)
			throw new LogicException();
		return tfacultad;
	}

	public TCarrera getCarrera(String codigo) throws LogicException {
		TCarrera tcarrera = tcarreraDao.findById(entityManager, codigo);
		if (tcarrera == null)
			throw new LogicException();
		return tcarrera;
	}

	public TPrograma getPrograma(String codigo) throws LogicException {
		TPrograma tprograma = tprogramaDao.findById(entityManager, codigo);
		if (tprograma == null)
			throw new LogicException();
		return tprograma;
	}

	public void createAlumno(TAlumno talumno) {

		talumnoDao = new TAlumnoDao();
		tprogAlumnoDao = new TProgAlumnoDao();
		TProgAlumno tprogAlumno = new TProgAlumno();

		// TODO validar los datos antes de insertar e insertar también el TProgAlumno.
		talumnoDao.save(entityManager, talumno);

		List<TProgAlumno> lista = talumno.getTProgAlumnos();
		for (TProgAlumno act : lista)
			tprogAlumnoDao.save(entityManager, act);

	}

	public void deleteAlumno(TAlumno alumno) {
		talumnoDao = new TAlumnoDao();
		deleteProgAlumno(alumno.getTProgAlumnos());
		talumnoDao.delete(entityManager, alumno);
	}

	public void deleteProgAlumno(List<TProgAlumno> tpa) {
		tprogAlumnoDao = new TProgAlumnoDao();
		for (TProgAlumno act : tpa)
			tprogAlumnoDao.delete(entityManager, act);
	}

	public void updateAlumno(TAlumno talumno) {

		talumnoDao = new TAlumnoDao();
		tprogAlumnoDao = new TProgAlumnoDao();
		TProgAlumno tprogAlumno = new TProgAlumno();

		// TODO validar los datos antes de insertar e insertar también el TProgAlumno.
		talumnoDao.update(entityManager, talumno);

		List<TProgAlumno> lista = talumno.getTProgAlumnos();
		for (TProgAlumno act : lista)
			tprogAlumnoDao.save(entityManager, act);

	}

	public TAlumno consultarAlumno(String codigo) {

		talumnoDao = new TAlumnoDao();
		return talumnoDao.findById(entityManager, codigo);

	}

	public TPrograma consultarPrograma(String programa) {
		tprogramaDao = new TProgramaDao();
		return tprogramaDao.findById(entityManager, programa);
	}

	@Override
	public List<String> listaProgramas() {
		TProgramaDao tprogdao = new TProgramaDao();
		List<TPrograma> tprogramas = tprogdao.findAll(entityManager);
		List<String> lprogramas = new ArrayList<String>();
		for (TPrograma tprograma : tprogramas) {
			lprogramas.add(tprograma.getCodigo() + "-" + tprograma.getDescripcion());
		}

		return lprogramas;
	}

}
