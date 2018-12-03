package co.edu.icesi.mio.bean;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.ejb.EJB;
import javax.faces.view.ViewScoped;
import javax.inject.Named;

import co.edu.icesi.mio.logic.ITmioBusesLogicRemota;
import co.edu.icesi.mio.logic.ITmioConductoresLogicRemota;
import co.edu.icesi.mio.model.Tmio1Conductore;
import co.edu.icesi.mio.model.Tmio1Servicio;
import co.edu.icesi.mio.model.Tmio1ServiciosSitio;

@Named
@ViewScoped
public class Conductor implements Serializable {
	@EJB(lookup = "java:global/primefaces-test-1.0-SNAPSHOT/TmioConductoresLogic!co.edu.icesi.mio.logic.ITmioConductoresLogicRemota")
	private ITmioConductoresLogicRemota conductorLogic;
	/**
	 * 
	 */
	private static final long serialVersionUID = 2242821910375315667L;

	private String cedula;

	private String apellidos;

	private Date fechaContratacion;

	private Date fechaNacimiento;

	private String nombre;

	private List<Tmio1Servicio> tmio1Servicio;
	private List<Tmio1ServiciosSitio> tmio1ServiciosSitio;
	private List<Conductor> dt = new ArrayList<Conductor>();

	public void prueba() {

	}

	public void crearConductor() {
		Tmio1Conductore conductor = new Tmio1Conductore();

		conductor.setCedula(cedula);
		conductor.setApellidos(apellidos);
		conductor.setFechaContratacion(fechaContratacion);
		conductor.setFechaNacimiento(fechaNacimiento);
		conductor.setNombre(nombre);
		conductor.setTmio1Servicios(tmio1Servicio);
		conductor.setTmio1ServiciosSitios(tmio1ServiciosSitio);

		conductorLogic.createConductor(conductor);
		cleanValues();
	}

	public void actualizarConductor() {
		Tmio1Conductore conductor = conductorLogic.findByCedula(cedula);

		conductor.setApellidos(apellidos);
		conductor.setFechaContratacion(fechaContratacion);
		conductor.setFechaNacimiento(fechaNacimiento);
		conductor.setNombre(nombre);
		conductor.setTmio1Servicios(tmio1Servicio);
		conductor.setTmio1ServiciosSitios(tmio1ServiciosSitio);

		conductorLogic.updateConductor(conductor);
		cleanValues();
	}

	public void borrarConductor() {
		Tmio1Conductore conductor = conductorLogic.findByCedula(cedula);
		conductorLogic.deleteConductor(conductor);
		cleanValues();
	}

	public void findByNombre() {

		List<Tmio1Conductore> ret = conductorLogic.findByName(nombre);

		List<Conductor> m = new ArrayList<Conductor>();

		for (int i = 0; i < ret.size(); i++) {
			Conductor act = new Conductor();
			Tmio1Conductore finded = ret.get(i);
			act.cedula = finded.getCedula();
			act.apellidos = finded.getApellidos();
			act.fechaContratacion = finded.getFechaContratacion();
			act.fechaNacimiento = finded.getFechaNacimiento();
			act.nombre = finded.getNombre();
			m.add(act);
		}
		dt = m;

	}

	public void findByApellidos() {
		// Tmio1Conductore conductor = new Tmio1Conductore();
		List<Tmio1Conductore> ret = conductorLogic.findByLastname(apellidos);

		List<Conductor> m = new ArrayList<Conductor>();

		for (int i = 0; i < ret.size(); i++) {
			Conductor act = new Conductor();
			Tmio1Conductore finded = ret.get(i);
			act.cedula = finded.getCedula();
			act.apellidos = finded.getApellidos();
			act.fechaContratacion = finded.getFechaContratacion();
			act.fechaNacimiento = finded.getFechaNacimiento();
			act.nombre = finded.getNombre();
			m.add(act);
		}
		dt = m;
	}

	public void findByCedula() {
		// Tmio1Conductore conductor = new Tmio1Conductore();
		Tmio1Conductore ret = conductorLogic.findByCedula(cedula);

		List<Conductor> m = new ArrayList<Conductor>();

		Conductor act = new Conductor();
		Tmio1Conductore finded = ret;
		act.cedula = finded.getCedula();
		act.apellidos = finded.getApellidos();
		act.fechaContratacion = finded.getFechaContratacion();
		act.fechaNacimiento = finded.getFechaNacimiento();
		act.nombre = finded.getNombre();
		m.add(act);

		dt = m;

	}

	public void cleanValues() {

		cedula = "";

		apellidos = "";

		fechaContratacion = null;

		fechaNacimiento = null;

		nombre = "";

	}

	public List<Conductor> datos() {
		return dt;

	}

	public List<Tmio1Servicio> getTmio1Servicio() {
		return tmio1Servicio;
	}

	public void setTmio1Servicio(List<Tmio1Servicio> tmio1Servicio) {
		this.tmio1Servicio = tmio1Servicio;
	}

	public List<Tmio1ServiciosSitio> getTmio1ServiciosSitio() {
		return tmio1ServiciosSitio;
	}

	public void setTmio1ServiciosSitio(List<Tmio1ServiciosSitio> tmio1ServiciosSitio) {
		this.tmio1ServiciosSitio = tmio1ServiciosSitio;
	}

	public ITmioConductoresLogicRemota getConductorLogic() {
		return conductorLogic;
	}

	public void setConductorLogic(ITmioConductoresLogicRemota conductorLogic) {
		this.conductorLogic = conductorLogic;
	}

	public List<Conductor> getDt() {
		return dt;
	}

	public void setDt(List<Conductor> dt) {
		this.dt = dt;
	}

	public String getCedula() {
		return cedula;
	}

	public void setCedula(String cedula) {
		this.cedula = cedula;
	}

	public String getApellidos() {
		return apellidos;
	}

	public void setApellidos(String apellidos) {
		this.apellidos = apellidos;
	}

	public Date getFechaContratacion() {
		return fechaContratacion;
	}

	public void setFechaContratacion(Date fechaContratacion) {
		this.fechaContratacion = fechaContratacion;
	}

	public Date getFechaNacimiento() {
		return fechaNacimiento;
	}

	public void setFechaNacimiento(Date fechaNacimiento) {
		this.fechaNacimiento = fechaNacimiento;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

}
