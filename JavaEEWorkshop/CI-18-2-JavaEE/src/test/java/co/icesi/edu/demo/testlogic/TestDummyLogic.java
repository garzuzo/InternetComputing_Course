package co.icesi.edu.demo.testlogic;


import static org.junit.Assert.*;
import org.junit.Test;
import org.junit.runner.RunWith;

import co.edu.icesi.demo.logic.IDummyLogicLocal;

public class TestDummyLogic {

	private IDummyLogicLocal dummyLogic;
	
	@Test
	public void aTest() {

		assertNotNull(dummyLogic);
		
		dummyLogic.createAlumno();
		
	}
	
}
