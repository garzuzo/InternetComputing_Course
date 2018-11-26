package co.edu.icesi.mio.bean;

import java.io.Serializable;

import javax.ejb.EJB;
import javax.faces.view.ViewScoped;
import javax.inject.Named;

import co.edu.icesi.mio.logic.ITmioBusesLogicRemota;
import co.edu.icesi.mio.logic.ITmioRutasLogicRemota;
import co.edu.icesi.mio.model.Tmio1Ruta;

@Named
@ViewScoped
public class Ruta implements Serializable {

	//@EJB(lookup = "java:global/primefaces-test-1.0-SNAPSHOT/EstudiantesLogic!co.edu.icesi.demo.logic.IEstudianteLogicRemota")
	private ITmioRutasLogicRemota rutaLogic;
	/**
	 * 
	 */
	private static final long serialVersionUID = -8627869989734609391L;

	private String descripcion;

	// bd
	private String diaFin;

	private String diaInicio;

	private String horaFin;

	private String horaInicio;

	private String numero;

	public void crearRuta() {

		Tmio1Ruta ruta=new Tmio1Ruta();
		rutaLogic.add(ruta);
	}

	public void actualizarRuta() {
		Tmio1Ruta ruta=new Tmio1Ruta();
		rutaLogic.update(ruta);
		
	}

	public void borrarRuta() {
		Tmio1Ruta ruta=new Tmio1Ruta();
		rutaLogic.delete(ruta);
	}
	public void findByRangoDias() {
		Tmio1Ruta ruta=new Tmio1Ruta();
		rutaLogic.findByRangoDias(null, null);
	}

	
}
