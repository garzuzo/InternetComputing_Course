package co.icesi.edu.demo.testlogic;


import static org.junit.Assert.*;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import co.edu.icesi.demo.logic.IDummyLogic;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration("/applicationContext.xml")
@Rollback(false)
public class TestDummyLogic {

	@Autowired
	private IDummyLogic dummyLogic;
		
	@Test
	public void aTest() {

		assertNotNull(dummyLogic);
		
		dummyLogic.createAlumno();
		
	}
	
}
