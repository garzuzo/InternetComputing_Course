package co.edu.icesi.mio.bean;

import java.io.Serializable;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import javax.ejb.EJB;
import javax.faces.view.ViewScoped;
import javax.inject.Named;

import co.edu.icesi.mio.logic.ITmioRutasLogicRemota;
import co.edu.icesi.mio.logic.ITmioServiciosLogicRemota;
import co.edu.icesi.mio.model.Tmio1Servicio;

@Named
@ViewScoped
public class Servicio implements Serializable {

	@EJB(lookup = "java:global/primefaces-test-1.0-SNAPSHOT/TmioServiciosLogic!co.edu.icesi.mio.logic.ITmioServiciosLogicRemota")
	private ITmioServiciosLogicRemota servicioLogic;

	/**
	 * 
	 */
	private static final long serialVersionUID = -3364962522222536126L;

	private String bus;

	private String conductor;

	private String ruta;
	private Date fechaInicio;
	private Date fechaFin;

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

	public List<Tmio1Servicio> findByRangoFechas() {
		Tmio1Servicio servicio = new Tmio1Servicio();
		Calendar fi=Calendar.getInstance();
		fi.setTime(fechaInicio);
		Calendar ff=Calendar.getInstance();
		ff.setTime(fechaFin);
		return servicioLogic.findByRangeOfDates(fi,ff);
	}
	
	
	public String getBus() {
		return bus;
	}

	public void setBus(String bus) {
		this.bus = bus;
	}

	public String getConductor() {
		return conductor;
	}

	public void setConductor(String conductor) {
		this.conductor = conductor;
	}

	public String getRuta() {
		return ruta;
	}

	public void setRuta(String ruta) {
		this.ruta = ruta;
	}

	public Date getFechaInicio() {
		return fechaInicio;
	}

	public void setFechaInicio(Date fechaInicio) {
		this.fechaInicio = fechaInicio;
	}

	public Date getFechaFin() {
		return fechaFin;
	}

	public void setFechaFin(Date fechaFin) {
		this.fechaFin = fechaFin;
	}


}
