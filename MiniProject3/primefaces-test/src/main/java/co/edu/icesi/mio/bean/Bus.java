package co.edu.icesi.mio.bean;

import java.io.Serializable;

import javax.ejb.EJB;
import javax.faces.view.ViewScoped;
import javax.inject.Named;

import co.edu.icesi.demo.logic.IEstudianteLogicRemota;
import co.edu.icesi.mio.logic.ITmioBusesLogicRemota;
import co.edu.icesi.mio.model.Tmio1Bus;

@Named
@ViewScoped
public class Bus implements Serializable {

//cambiar todos...
	//@EJB(lookup = "java:global/primefaces-test-1.0-SNAPSHOT/EstudiantesLogic!co.edu.icesi.demo.logic.IEstudianteLogicRemota")
	private ITmioBusesLogicRemota busLogic;

	/**
	 * 
	 */
	private static final long serialVersionUID = -798144091678030958L;
//bd
	private String capacidad;

	private String marca;
	// bd
	private String modelo;

	private String placa;

	private String tipo;

	public void crearBus() {
		Tmio1Bus bus=new Tmio1Bus();
		
//		bus.setCapacidad(capacidad);
//		bus.setId(id);
//		bus.setMarca(marca);
//		bus.setModelo(modelo);
//		bus.setPlaca(placa);
//		bus.setTipo(tipo);
//		bus.setTmio1Servicios(tmio1Servicios);
//		bus.setTmio1ServiciosSitios(tmio1ServiciosSitios);
		
		busLogic.add(bus);
	}

	public void actualizarBus() {
		Tmio1Bus bus=new Tmio1Bus();
		
		busLogic.update(bus);
	}

	public void borrarBus() {
		Tmio1Bus bus=new Tmio1Bus();
		busLogic.delete(bus);
		
	}

	public void findByModelo() {
		busLogic.findByModelo(null);
	}

	public void findByTipo() {
		busLogic.findByTipo(null);
	}

	public void findByCapacidad() {
		busLogic.findByCapacidad(null);
	}
}
