package co.edu.icesi.demo.logic;

import java.util.ArrayList;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import co.edu.icesi.demo.dao.ITAlumnoDao;
import co.edu.icesi.demo.dao.ITProgAlumnoDao;
import co.edu.icesi.demo.dao.ITProgramaDao;
import co.edu.icesi.demo.exceptions.LogicException;
import co.edu.icesi.demo.model.TAlumno;
import co.edu.icesi.demo.model.TCarrera;
import co.edu.icesi.demo.model.TFacultad;
import co.edu.icesi.demo.model.TProgAlumno;
import co.edu.icesi.demo.model.TProgAlumnoPK;
import co.edu.icesi.demo.model.TPrograma;

@Service
public class EstudiantesLogic implements IEstudianteLogic {

	

	@Autowired
	private ITAlumnoDao alumnoDao;

	@Autowired
	private ITProgAlumnoDao progAlumnoDao;

	public TFacultad getFacultad(String codigo) throws LogicException {

		return null;
	}

	public TCarrera getCarrera(String codigo) throws LogicException {
		return null;
	}

	public TPrograma getPrograma(String codigo) throws LogicException {

		// String psql="SELECT p FROM TPrograma p ";
		return null;
	}

	@Transactional
	public void createAlumno(TAlumno talumno, TProgAlumno tprogalumno) {
		// TODO validate the data is correct and persist the two entities.

		alumnoDao.save(talumno);
		progAlumnoDao.save(tprogalumno);

	}

}
