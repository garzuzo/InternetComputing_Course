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
import co.edu.icesi.demo.dao.TProgramaDao;
import co.edu.icesi.demo.exceptions.LogicException;
import co.edu.icesi.demo.model.TAlumno;
import co.edu.icesi.demo.model.TCarrera;
import co.edu.icesi.demo.model.TFacultad;
import co.edu.icesi.demo.model.TProgAlumno;
import co.edu.icesi.demo.model.TPrograma;

@Stateless(name="EstudiantesLogic", mappedName="EstudiantesLogic")
@TransactionAttribute(TransactionAttributeType.REQUIRED)
@TransactionManagement(TransactionManagementType.CONTAINER)
@Local(IEstudianteLogicLocal.class)
@Remote(IEstudianteLogicRemota.class)
public class EstudiantesLogic implements IEstudianteLogicRemota, IEstudianteLogicLocal  {


	@PersistenceContext
	private EntityManager entityManager;
	
	private TAlumnoDao talumnoDao;
	
	private TCarreraDao tcarreraDao;

	private TProgramaDao tprogramaDao;
	
	private TFacultadDao tfacultadDao;

	
	public TFacultad getFacultad (String codigo) throws LogicException
	{
		TFacultad tfacultad = tfacultadDao.findById(entityManager, codigo);
		if (tfacultad == null)
			throw new LogicException();
		return tfacultad;
	}

	public TCarrera getCarrera (String codigo) throws LogicException
	{
		TCarrera tcarrera = tcarreraDao.findById(entityManager, codigo);
		if (tcarrera == null)
			throw new LogicException();
		return tcarrera;
	}
	
	public TPrograma getPrograma (String codigo) throws LogicException
	{
		TPrograma tprograma = tprogramaDao.findById(entityManager, codigo);
		if (tprograma == null)
			throw new LogicException();
		return tprograma;
	}
	
	public void createAlumno(TAlumno talumno) {


		talumnoDao = new TAlumnoDao();

		//TODO validar los datos antes de insertar e insertar también el TProgAlumno.
				
		talumnoDao.save(entityManager, talumno);
	}

	@Override
	public List<String> listaProgramas() {
		TProgramaDao tprogdao = new TProgramaDao();
		List <TPrograma> tprogramas = tprogdao.findAll(entityManager);
		List<String> lprogramas = new ArrayList<String>();
		for (TPrograma tprograma:tprogramas)
		{
			lprogramas.add(tprograma.getCodigo() + "-" + tprograma.getDescripcion());
		}
		
		return lprogramas;
	}

}
