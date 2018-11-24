package co.edu.icesi.mio.bean;

import java.io.Serializable;

import javax.ejb.EJB;
import javax.faces.view.ViewScoped;
import javax.inject.Named;

import co.edu.icesi.mio.logic.ITmioBusesLogicRemota;
import co.edu.icesi.mio.logic.ITmioConductoresLogicRemota;

@Named
@ViewScoped
public class Conductor implements Serializable {
	@EJB(lookup = "java:global/primefaces-test-1.0-SNAPSHOT/EstudiantesLogic!co.edu.icesi.demo.logic.IEstudianteLogicRemota")
	private ITmioConductoresLogicRemota conductorLogic;
	/**
	 * 
	 */
	private static final long serialVersionUID = 2242821910375315667L;

}
