package co.edu.icesi.mio.bean;

import java.io.Serializable;
import java.util.Date;

import javax.ejb.EJB;
import javax.faces.view.ViewScoped;
import javax.inject.Named;

import co.edu.icesi.mio.logic.ITmioBusesLogicRemota;
import co.edu.icesi.mio.logic.ITmioConductoresLogicRemota;
import co.edu.icesi.mio.model.Tmio1Conductore;

@Named
@ViewScoped
public class Conductor implements Serializable {
//	@EJB(lookup = "java:global/primefaces-test-1.0-SNAPSHOT/EstudiantesLogic!co.edu.icesi.demo.logic.IEstudianteLogicRemota")
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

	
	public void crearConductor() {
Tmio1Conductore conductor=new Tmio1Conductore();
conductorLogic.createConductor(conductor);
	}

	public void actualizarConductor() {
		Tmio1Conductore conductor=new Tmio1Conductore();
		conductorLogic.updateConductor(conductor);
	}

	public void borrarConductor() {
		Tmio1Conductore conductor=new Tmio1Conductore();
		conductorLogic.deleteConductor(conductor);
	}
	public void findByNombre() {
		Tmio1Conductore conductor=new Tmio1Conductore();
		conductorLogic.findByName(null);
	}

	public void findByApellidos() {
		Tmio1Conductore conductor=new Tmio1Conductore();
		conductorLogic.findByLastname(null);
	}

	public void findByCedula() {
		Tmio1Conductore conductor=new Tmio1Conductore();
		conductorLogic.findByCedula(null);
	}
}
