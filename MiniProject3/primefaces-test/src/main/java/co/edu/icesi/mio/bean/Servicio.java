package co.edu.icesi.mio.bean;

import java.io.Serializable;

import javax.ejb.EJB;
import javax.faces.view.ViewScoped;
import javax.inject.Named;

import co.edu.icesi.mio.logic.ITmioRutasLogicRemota;
import co.edu.icesi.mio.logic.ITmioServiciosLogicRemota;
import co.edu.icesi.mio.model.Tmio1Servicio;

@Named
@ViewScoped
public class Servicio implements Serializable {

	//@EJB(lookup = "java:global/primefaces-test-1.0-SNAPSHOT/EstudiantesLogic!co.edu.icesi.demo.logic.IEstudianteLogicRemota")
	private ITmioServiciosLogicRemota servicioLogic;

	/**
	 * 
	 */
	private static final long serialVersionUID = -3364962522222536126L;

	private String tmio1Bus;

	private String tmio1Conductore;

	private String tmio1Ruta;

	// Debe permitir seleccionar el conductor, ruta y bus a
	// asociar el servicio de los existentes.
	public void crearServicio() {
		Tmio1Servicio servicio = new Tmio1Servicio();
		servicioLogic.createServicio(servicio);
	}

	public void actualizarServicio() {
		Tmio1Servicio servicio = new Tmio1Servicio();
		servicioLogic.updateServicio(servicio);
	}

	public void borrarServicio() {
		Tmio1Servicio servicio = new Tmio1Servicio();
		servicioLogic.deleteServicio(servicio);
	}

	public void findByRangoFechas() {
		Tmio1Servicio servicio = new Tmio1Servicio();
		servicioLogic.findByRangeOfDates(null, null);
	}

}
