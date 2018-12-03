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

	private String valorDialog;
	private List<Tmio1Servicio> tmio1Servicio;
	private List<Tmio1ServiciosSitio> tmio1ServiciosSitio;
	private List<Conductor> dt = new ArrayList<Conductor>();

	public void prueba() {

	}

	public String crearConductor() {
		Tmio1Conductore conductor = new Tmio1Conductore();

		conductor.setCedula(cedula);
		conductor.setApellidos(apellidos);
		conductor.setFechaContratacion(fechaContratacion);
		conductor.setFechaNacimiento(fechaNacimiento);
		conductor.setNombre(nombre);
		conductor.setTmio1Servicios(tmio1Servicio);
		conductor.setTmio1ServiciosSitios(tmio1ServiciosSitio);
		cleanValues();
		return conductorLogic.createConductor(conductor);

	}

	public String actualizarConductor() {
		Tmio1Conductore conductor = conductorLogic.findByCedula(cedula);

		conductor.setApellidos(apellidos);
		conductor.setFechaContratacion(fechaContratacion);
		conductor.setFechaNacimiento(fechaNacimiento);
		conductor.setNombre(nombre);
		conductor.setTmio1Servicios(tmio1Servicio);
		conductor.setTmio1ServiciosSitios(tmio1ServiciosSitio);
		cleanValues();
		return conductorLogic.updateConductor(conductor);

	}

	public String borrarConductor() {
		Tmio1Conductore conductor = conductorLogic.findByCedula(cedula);
		cleanValues();
		return conductorLogic.deleteConductor(conductor);

	}

	public void findByNombre() {

		List<Tmio1Conductore> ret = conductorLogic.findByName(nombre);

		dt = new ArrayList<Conductor>();
if(ret!=null) {
		for (int i = 0; i < ret.size(); i++) {
			Conductor act = new Conductor();
			Tmio1Conductore finded = ret.get(i);
			if (finded != null) {
				act.cedula = finded.getCedula();
				act.apellidos = finded.getApellidos();
				act.fechaContratacion = finded.getFechaContratacion();
				act.fechaNacimiento = finded.getFechaNacimiento();
				act.nombre = finded.getNombre();
				dt.add(act);
			}
		}
}
	}

	public void findByApellidos() {
		// Tmio1Conductore conductor = new Tmio1Conductore();
		List<Tmio1Conductore> ret = conductorLogic.findByLastname(apellidos);

		dt = new ArrayList<Conductor>();
		if(ret!=null) {
		for (int i = 0; i < ret.size(); i++) {
			Conductor act = new Conductor();
			Tmio1Conductore finded = ret.get(i);
			if (finded != null) {
				act.cedula = finded.getCedula();
				act.apellidos = finded.getApellidos();
				act.fechaContratacion = finded.getFechaContratacion();
				act.fechaNacimiento = finded.getFechaNacimiento();
				act.nombre = finded.getNombre();
				dt.add(act);
			}
		}
		}
	}

	public void findByCedula() {
		// Tmio1Conductore conductor = new Tmio1Conductore();
		Tmio1Conductore ret = conductorLogic.findByCedula(cedula);

		dt = new ArrayList<Conductor>();
		if(ret!=null) {
		Conductor act = new Conductor();
		Tmio1Conductore finded = ret;
		if (finded != null) {
			act.cedula = finded.getCedula();
			act.apellidos = finded.getApellidos();
			act.fechaContratacion = finded.getFechaContratacion();
			act.fechaNacimiento = finded.getFechaNacimiento();
			act.nombre = finded.getNombre();
			dt.add(act);
		}
		}
	}

	public void cleanValues() {

		cedula = "";

		apellidos = "";

		fechaContratacion = null;

		fechaNacimiento = null;

		nombre = "";

	}

//	public List<Conductor> datos() {
//		return dt;
//
//	}

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

	public String getValorDialog() {
		return valorDialog;
	}

	public void setValorDialog(String valorDialog) {
		this.valorDialog = valorDialog;
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
