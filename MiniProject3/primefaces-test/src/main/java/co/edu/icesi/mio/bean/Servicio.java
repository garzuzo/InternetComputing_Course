package co.edu.icesi.mio.bean;

import java.io.Serializable;

import javax.ejb.EJB;
import javax.faces.view.ViewScoped;
import javax.inject.Named;

import co.edu.icesi.mio.logic.ITmioRutasLogicRemota;
import co.edu.icesi.mio.logic.ITmioServiciosLogicRemota;

@Named
@ViewScoped
public class Servicio implements Serializable {

	
	@EJB(lookup = "java:global/primefaces-test-1.0-SNAPSHOT/EstudiantesLogic!co.edu.icesi.demo.logic.IEstudianteLogicRemota")
	private ITmioServiciosLogicRemota servicioLogic;
	
	
	/**
	 * 
	 */
	private static final long serialVersionUID = -3364962522222536126L;

}
