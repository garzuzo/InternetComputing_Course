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
import co.edu.icesi.demo.logic.IEstudianteLogic;
import co.edu.icesi.demo.model.TAlumno;
import co.edu.icesi.demo.model.TProgAlumno;



public class TestEstudianteLogic {

	@Autowired
	private IEstudianteLogic alumnoLogic;
	

	public void testCrearAlumno() {

		assertNotNull(alumnoLogic);
		
		
	}
	
}
