package co.edu.icesi.mio.bean;

import java.io.Serializable;

import javax.ejb.EJB;
import javax.faces.view.ViewScoped;
import javax.inject.Named;

import co.edu.icesi.demo.logic.IEstudianteLogicRemota;
import co.edu.icesi.mio.logic.ITmioBusesLogicRemota;

@Named
@ViewScoped
public class Bus implements Serializable {

	
//cambiar todos...
	@EJB(lookup = "java:global/primefaces-test-1.0-SNAPSHOT/EstudiantesLogic!co.edu.icesi.demo.logic.IEstudianteLogicRemota")
	private ITmioBusesLogicRemota busLogic;
	
	
	/**
	 * 
	 */
	private static final long serialVersionUID = -798144091678030958L;

}
