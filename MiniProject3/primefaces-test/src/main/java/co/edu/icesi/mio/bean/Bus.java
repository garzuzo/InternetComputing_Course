package co.edu.icesi.mio.bean;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import javax.ejb.EJB;
import javax.faces.view.ViewScoped;
import javax.inject.Named;

import org.primefaces.component.datatable.DataTable;

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
	private String valorDialog;
	private String id;

	DataTable dtBus;
	private List<Tmio1Servicio> tmio1Servicios;
	private List<Tmio1ServiciosSitio> tmio1ServiciosSitio;
	private List<Bus> dt = new ArrayList<Bus>();

	public String crearBus() {
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

		String answ = busLogic.add(bus);
		// cleanValues();
		valorDialog = answ;
		return answ;
	}

	public void cleanValues() {
		capacidad = "";

		marca = "";

		modelo = "";

		placa = "";

		tipo = "";

		id = "";

	}

	public String actualizarBus() {
		Tmio1Bus bus = new Tmio1Bus();

		bus = busLogic.findById(Integer.parseInt(id));

		bus.setCapacidad(new BigDecimal(capacidad));

		bus.setMarca(marca);
		bus.setModelo(new BigDecimal(modelo));
		bus.setPlaca(placa);
		bus.setTipo(tipo);
		bus.setTmio1Servicios(tmio1Servicios);
		bus.setTmio1ServiciosSitios(tmio1ServiciosSitio);

		String answ = busLogic.update(bus);
		cleanValues();
		valorDialog = answ;
		return answ;
	}

	public String borrarBus() {
		Tmio1Bus bus = busLogic.findById(Integer.parseInt(id));

		cleanValues();
		String answ = busLogic.delete(bus);
		valorDialog = answ;
		return answ;
	}

	public void findByModelo() {
		List<Tmio1Bus> ret = busLogic.findByModelo(new BigDecimal(modelo));

		dt = new ArrayList<Bus>();
		if (ret != null) {
			for (int i = 0; i < ret.size(); i++) {
				Bus act = new Bus();
				Tmio1Bus finded = ret.get(i);
				if (finded != null) {
					act.id = finded.getId() + "";
					act.capacidad = finded.getCapacidad() + "";
					act.marca = finded.getMarca();
					act.modelo = finded.getModelo() + "";
					act.placa = finded.getPlaca();
					act.tipo = finded.getTipo();
					dt.add(act);
				}
			}
		}
	}

	public void findByTipo() {
		dt = new ArrayList<Bus>();
		if (tipo != null) {
			List<Tmio1Bus> ret = busLogic.findByTipo(tipo);

		

			for (int i = 0; i < ret.size(); i++) {
				Bus act = new Bus();
				Tmio1Bus finded = ret.get(i);
				if (finded != null) {
					act.id = finded.getId() + "";
					act.capacidad = finded.getCapacidad() + "";
					act.marca = finded.getMarca();
					act.modelo = finded.getModelo() + "";
					act.placa = finded.getPlaca();
					act.tipo = finded.getTipo();
					dt.add(act);
				}
			}

			// cleanValues();
		}
	}

	public void findByCapacidad() {
		List<Tmio1Bus> ret = busLogic.findByCapacidad(new BigDecimal(capacidad));
		dt = new ArrayList<Bus>();
		if (ret != null) {

			for (int i = 0; i < ret.size(); i++) {
				Bus act = new Bus();
				Tmio1Bus finded = ret.get(i);
				if (finded != null) {
					act.id = finded.getId() + "";
					act.capacidad = finded.getCapacidad() + "";
					act.marca = finded.getMarca();
					act.modelo = finded.getModelo() + "";
					act.placa = finded.getPlaca();
					act.tipo = finded.getTipo();
					dt.add(act);
				}
			}
		}

		// datos();
		// dtBus.reset();

		// cleanValues();
	}

	public DataTable getDtBus() {
		return dtBus;
	}

	public void setDtBus(DataTable dtBus) {
		this.dtBus = dtBus;
	}

	public List<Bus> datos() {
		dtBus.reset();
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

	public String getValorDialog() {
		return valorDialog;
	}

	public void setValorDialog(String valorDialog) {
		this.valorDialog = valorDialog;
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
