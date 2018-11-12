package co.icesi.edu.demo.testdao;


import static org.junit.Assert.*;

import javax.persistence.EntityManager;

import org.junit.Test;
import co.edu.icesi.demo.dao.TAlumnoDao;
import co.edu.icesi.demo.model.TAlumno;

public class TestTAlumnoDAO {
	EntityManager entityManager;

	private TAlumnoDao talumnoDao;
	
	@Test
	public void aTest() {

		
		TAlumno talumno = new TAlumno();
		talumno.setNombre("Jack");
		talumno.setApellidos("Bauer");
		talumno.setCodigo("abcss");
		talumno.setSexo("M");
		talumno.setTipo("E");
		
		talumnoDao.save(entityManager,talumno);
		
	}
	
	@Test
	public void bTest() {

		
		TAlumno alumno = talumnoDao.findById(entityManager,"abcss");
		assertNotNull("Code not found", alumno);
		alumno.setApellidos("JK");
		talumnoDao.update(entityManager,alumno);
		
	}
	
	@Test
	public void cTest() {

		TAlumno talumno = talumnoDao.findById(entityManager,"abcss");
		assertNotNull("Code not found", talumno);
		talumnoDao.delete(entityManager,talumno);
		
	}

}
