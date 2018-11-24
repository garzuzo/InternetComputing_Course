package co.edu.icesi.mio.bean;

import java.io.Serializable;

import javax.ejb.EJB;
import javax.faces.view.ViewScoped;
import javax.inject.Named;

import co.edu.icesi.mio.logic.ITmioBusesLogicRemota;
import co.edu.icesi.mio.logic.ITmioRutasLogicRemota;

@Named
@ViewScoped
public class Ruta implements Serializable {

	@EJB(lookup = "java:global/primefaces-test-1.0-SNAPSHOT/EstudiantesLogic!co.edu.icesi.demo.logic.IEstudianteLogicRemota")
	private ITmioRutasLogicRemota rutaLogic;
	/**
	 * 
	 */
	private static final long serialVersionUID = -8627869989734609391L;

}
