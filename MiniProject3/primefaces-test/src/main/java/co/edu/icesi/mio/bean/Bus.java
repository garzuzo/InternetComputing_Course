package co.edu.icesi.mio.bean;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import javax.ejb.EJB;
import javax.faces.view.ViewScoped;
import javax.inject.Named;

import co.edu.icesi.demo.logic.IEstudianteLogicRemota;
import co.edu.icesi.mio.logic.ITmioBusesLogicRemota;
import co.edu.icesi.mio.model.Tmio1Bus;
import co.edu.icesi.mio.model.Tmio1Conductore;
import co.edu.icesi.mio.model.Tmio1Servicio;
import co.edu.icesi.mio.model.Tmio1ServiciosSitio;

@Named
@ViewScoped
public class Bus implements Serializable {

//cambiar todos...
	@EJB(lookup = "java:global/primefaces-test-1.0-SNAPSHOT/TmioBusesLogic!co.edu.icesi.mio.logic.ITmioBusesLogicRemota")
	private ITmioBusesLogicRemota busLogic;

	/**
	 * 
	 */
	private static final long serialVersionUID = -798144091678030958L;

	private String capacidad;

	private String marca;

	private String modelo;

	private String placa;

	private String tipo;

	private String id;

	private List<Tmio1Servicio> tmio1Servicios;
	private List<Tmio1ServiciosSitio> tmio1ServiciosSitio;
	private List<Bus> dt = new ArrayList<Bus>();

	public void crearBus() {
		Tmio1Bus bus = new Tmio1Bus();

		bus.setCapacidad(new BigDecimal(capacidad));
		tmio1Servicios = new ArrayList<Tmio1Servicio>();
		tmio1ServiciosSitio = new ArrayList<Tmio1ServiciosSitio>();
		bus.setMarca(marca);
		bus.setModelo(new BigDecimal(modelo));
		bus.setPlaca(placa);
		bus.setTipo(tipo);
		bus.setTmio1Servicios(tmio1Servicios);
		bus.setTmio1ServiciosSitios(tmio1ServiciosSitio);

		busLogic.add(bus);
		cleanValues();
	}

	public void cleanValues() {
		capacidad = "";

		marca = "";

		modelo = "";

		placa = "";

		tipo = "";

		id = "";

	}

	public void actualizarBus() {
		Tmio1Bus bus = new Tmio1Bus();

		bus = busLogic.findById(Integer.parseInt(id));

		bus.setCapacidad(new BigDecimal(capacidad));

		bus.setMarca(marca);
		bus.setModelo(new BigDecimal(modelo));
		bus.setPlaca(placa);
		bus.setTipo(tipo);
		bus.setTmio1Servicios(tmio1Servicios);
		bus.setTmio1ServiciosSitios(tmio1ServiciosSitio);

		busLogic.update(bus);
		cleanValues();
	}

	public void borrarBus() {
		Tmio1Bus bus = busLogic.findById(Integer.parseInt(id));

		busLogic.delete(bus);

	}

	public void findByModelo() {
		List<Tmio1Bus> ret = busLogic.findByModelo(new BigDecimal(modelo));

		List<Bus> m = new ArrayList<Bus>();

		for (int i = 0; i < ret.size(); i++) {
			Bus act = new Bus();
			Tmio1Bus finded = ret.get(i);
			act.id = finded.getId() + "";
			act.capacidad = finded.getCapacidad() + "";
			act.marca = finded.getMarca();
			act.modelo = finded.getModelo() + "";
			act.placa = finded.getPlaca();

			m.add(act);
		}
		dt = m;
	}

	public void findByTipo() {
		List<Tmio1Bus> ret = busLogic.findByTipo(tipo);

		List<Bus> m = new ArrayList<Bus>();

		for (int i = 0; i < ret.size(); i++) {
			Bus act = new Bus();
			Tmio1Bus finded = ret.get(i);
			act.id = finded.getId() + "";
			act.capacidad = finded.getCapacidad() + "";
			act.marca = finded.getMarca();
			act.modelo = finded.getModelo() + "";
			act.placa = finded.getPlaca();

			m.add(act);
		}
		dt = m;
		cleanValues();
	}

	public void findByCapacidad() {
		List<Tmio1Bus> ret = busLogic.findByCapacidad(new BigDecimal(capacidad));

		List<Bus> m = new ArrayList<Bus>();
		for (int i = 0; i < ret.size(); i++) {
			Bus act = new Bus();
			Tmio1Bus finded = ret.get(i);
			act.id = finded.getId() + "";
			act.capacidad = finded.getCapacidad() + "";
			act.marca = finded.getMarca();
			act.modelo = finded.getModelo() + "";
			act.placa = finded.getPlaca();

			m.add(act);
		}
		dt = m;
		cleanValues();
	}

	public List<Bus> datos() {

		return dt;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public List<Bus> getDt() {
		return dt;
	}

	public void setDt(List<Bus> dt) {
		this.dt = dt;
	}

	public List<Tmio1Servicio> getTmio1Servicios() {
		return tmio1Servicios;
	}

	public void setTmio1Servicios(List<Tmio1Servicio> tmio1Servicios) {
		this.tmio1Servicios = tmio1Servicios;
	}

	public List<Tmio1ServiciosSitio> getTmio1ServiciosSitio() {
		return tmio1ServiciosSitio;
	}

	public void setTmio1ServiciosSitio(List<Tmio1ServiciosSitio> tmio1ServiciosSitio) {
		this.tmio1ServiciosSitio = tmio1ServiciosSitio;
	}

	public ITmioBusesLogicRemota getBusLogic() {
		return busLogic;
	}

	public void setBusLogic(ITmioBusesLogicRemota busLogic) {
		this.busLogic = busLogic;
	}

	public String getCapacidad() {
		return capacidad;
	}

	public void setCapacidad(String capacidad) {
		this.capacidad = capacidad;
	}

	public String getMarca() {
		return marca;
	}

	public void setMarca(String marca) {
		this.marca = marca;
	}

	public String getModelo() {
		return modelo;
	}

	public void setModelo(String modelo) {
		this.modelo = modelo;
	}

	public String getPlaca() {
		return placa;
	}

	public void setPlaca(String placa) {
		this.placa = placa;
	}

	public String getTipo() {
		return tipo;
	}

	public void setTipo(String tipo) {
		this.tipo = tipo;
	}

}
