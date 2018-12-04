package co.edu.icesi.mio.bean;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.faces.view.ViewScoped;
import javax.inject.Named;

import org.primefaces.event.RowEditEvent;

import co.edu.icesi.mio.dao.Tmio1_Buses_DAO;
import co.edu.icesi.mio.dao.Tmio1_Conductores_DAO;
import co.edu.icesi.mio.dao.Tmio1_Rutas_DAO;
import co.edu.icesi.mio.logic.ITmioRutasLogicRemota;
import co.edu.icesi.mio.logic.ITmioServiciosLogicRemota;
import co.edu.icesi.mio.model.Tmio1Bus;
import co.edu.icesi.mio.model.Tmio1Conductore;
import co.edu.icesi.mio.model.Tmio1Ruta;
import co.edu.icesi.mio.model.Tmio1Servicio;
import co.edu.icesi.mio.model.Tmio1ServicioPK;

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
	private String valorDialog;
	private List<Servicio> dt = new ArrayList<Servicio>();
	private List<String> buses = new ArrayList<String>();
	private List<String> conductores = new ArrayList<String>();
	private List<String> rutas = new ArrayList<String>();
	private List<Servicio> servicios = new ArrayList<Servicio>();
	private int pos;

	@PostConstruct
	public void iniciarListar() {
		getListaBuses();
		getListaRutas();
		getListaConductores();

	}

	// Debe permitir seleccionar el conductor, ruta y bus a
	// asociar el servicio de los existentes.
	public String crearServicio() {
		Tmio1Servicio servicio = new Tmio1Servicio();
		Tmio1Ruta rAct = servicioLogic.findByIdRuta(Integer.parseInt(ruta));
		Tmio1Bus bAct = servicioLogic.findById(Integer.parseInt(bus));
		Tmio1Conductore cAct = servicioLogic.findByCedula(conductor);

		servicio.setTmio1Bus(bAct);
		servicio.setTmio1Conductore(cAct);
		servicio.setTmio1Ruta(rAct);

		Tmio1ServicioPK pkAct = new Tmio1ServicioPK();
		pkAct.setCedulaConductor(conductor);
		pkAct.setIdBus(Integer.parseInt(bus));
		pkAct.setIdRuta(Integer.parseInt(ruta));
		pkAct.setFechaFin(fechaFin);
		pkAct.setFechaInicio(fechaInicio);

		servicio.setId(pkAct);

		String ret = servicioLogic.createServicio(servicio);

		if (ret.equals("Servicio creado exitosamente")) {
			cleanValues();
			FacesContext.getCurrentInstance().addMessage(null,
					new FacesMessage(FacesMessage.SEVERITY_INFO, "Info", ret));

		} else {
			FacesContext.getCurrentInstance().addMessage(null,
					new FacesMessage(FacesMessage.SEVERITY_FATAL, "error", ret));

		}
		return ret;
	}

	public void editarEliminarServicio() {
		List<Tmio1Servicio> lista = servicioLogic.getListaServicios(Integer.parseInt(bus), Integer.parseInt(conductor),
				Integer.parseInt(ruta));
		servicios = new ArrayList<Servicio>();
		for (int i = 0; i < lista.size(); i++) {
			Tmio1Servicio servAct = lista.get(i);
			Servicio sAct = new Servicio();
			sAct.bus = servAct.getTmio1Bus().getId() + "";
			sAct.conductor = servAct.getTmio1Conductore().getCedula();
			sAct.ruta = servAct.getTmio1Ruta().getId() + "";
			sAct.fechaFin = servAct.getId().getFechaFin();
			sAct.fechaInicio = servAct.getId().getFechaInicio();
			sAct.pos = (i + 1);
			servicios.add(sAct);
		}

	}

	public void onRowEdit(RowEditEvent event) {
		FacesMessage msg = new FacesMessage("Servicio Editado");

		String b = ((Servicio) event.getObject()).getBus();
		String r = ((Servicio) event.getObject()).getRuta();
		String c = ((Servicio) event.getObject()).getConductor();
		Date fi = ((Servicio) event.getObject()).getFechaInicio();
		Date ff = ((Servicio) event.getObject()).getFechaFin();
		int pos = ((Servicio) event.getObject()).getPos();
		actualizarServicio(pos,b);
		FacesContext.getCurrentInstance().addMessage(null, msg);
	}

	public void onRowCancel(RowEditEvent event) {
		FacesMessage msg = new FacesMessage("Se canceló la edición");
		FacesContext.getCurrentInstance().addMessage(null, msg);
	}

	public void onRowDelete(RowEditEvent event) {

		FacesMessage msg = new FacesMessage("Se canceló la edición");
		FacesContext.getCurrentInstance().addMessage(null, msg);
		int pos = ((Servicio) event.getObject()).getPos();
		borrarServicio(pos);
	}

	public String actualizarServicio(int pos, String b) {

		List<Tmio1Servicio> lista = servicioLogic.getListaServicios(Integer.parseInt(bus), Integer.parseInt(conductor),
				Integer.parseInt(ruta));

		Tmio1Servicio servAct = lista.get(pos - 1);

		servAct.setTmio1Bus(servicioLogic.findById(Integer.parseInt(b)));
		servAct.setTmio1Conductore(servicioLogic.findByCedula(conductor));
		servAct.setTmio1Ruta(servicioLogic.findByIdRuta(Integer.parseInt(ruta)));

		servAct.getId().setCedulaConductor(conductor);
		servAct.getId().setFechaFin(fechaFin);
		servAct.getId().setFechaInicio(fechaInicio);
		servAct.getId().setIdBus(Integer.parseInt(bus));
		servAct.getId().setIdRuta(Integer.parseInt(ruta));

		String ret = servicioLogic.updateServicio(servAct);

		if (ret.equals("Servicio actualizado exitosamente")) {
			cleanValues();
			FacesContext.getCurrentInstance().addMessage(null,
					new FacesMessage(FacesMessage.SEVERITY_INFO, "Info", ret));

		} else {

			FacesContext.getCurrentInstance().addMessage(null,
					new FacesMessage(FacesMessage.SEVERITY_FATAL, "error", ret));

		}
		return ret;
	}

	public String borrarServicio(int pos) {

		List<Tmio1Servicio> lista = servicioLogic.getListaServicios(Integer.parseInt(bus), Integer.parseInt(conductor),
				Integer.parseInt(ruta));

		String ret = servicioLogic.deleteServicio(lista.get(pos - 1));

		if (ret.equals("El servicio se eliminó correctamente")) {

			cleanValues();
			FacesContext.getCurrentInstance().addMessage(null,
					new FacesMessage(FacesMessage.SEVERITY_INFO, "Info", ret));

		} else {
			FacesContext.getCurrentInstance().addMessage(null,
					new FacesMessage(FacesMessage.SEVERITY_FATAL, "error", ret));

		}
		return ret;
	}

	public List<String> getListaBuses() {
		List<Tmio1Bus> busesAct = servicioLogic.getListaBuses();
		buses = new ArrayList<String>();
		for (int i = 0; i < busesAct.size(); i++) {

			String act = busesAct.get(i).getId() + "";
			buses.add(act);
		}
		return buses;
	}

	public List<String> getListaRutas() {

		List<Tmio1Ruta> busesAct = servicioLogic.getListaRutas();
		rutas = new ArrayList<String>();
		for (int i = 0; i < busesAct.size(); i++) {

			String act = busesAct.get(i).getId() + "";
			rutas.add(act);
		}
		return rutas;
	}

	public List<String> getListaConductores() {

		List<Tmio1Conductore> busesAct = servicioLogic.getListaConductores();
		conductores = new ArrayList<String>();
		for (int i = 0; i < busesAct.size(); i++) {

			String act = busesAct.get(i).getCedula();
			conductores.add(act);
		}
		return conductores;
	}

	public void findByRangoFechas() {
		Tmio1Servicio servicio = new Tmio1Servicio();
		Calendar fi = Calendar.getInstance();
		fi.setTime(fechaInicio);
		Calendar ff = Calendar.getInstance();
		ff.setTime(fechaFin);
		
		Date dAct=fi.getTime();
		Date dAct1=ff.getTime();
		int s1=ff.get(Calendar.YEAR);
		int s2=ff.get(Calendar.MONTH);
	int s3=ff.get(Calendar.DAY_OF_MONTH);
		List<Tmio1Servicio> ret = servicioLogic.findByRangeOfDates(fi, ff);
		dt = new ArrayList<Servicio>();
		if (ret != null) {
			for (int i = 0; i < ret.size(); i++) {
				Servicio act = new Servicio();
				Tmio1Servicio finded = ret.get(i);
				if (finded != null) {
					act.bus = finded.getTmio1Bus().getId() + "";
					act.conductor = finded.getTmio1Conductore().getCedula() + "";
					act.fechaFin = finded.getId().getFechaFin();
					act.fechaInicio = finded.getId().getFechaInicio();
					act.ruta = finded.getTmio1Ruta().getId() + "";

					dt.add(act);
				}
			}
		} else {
			FacesContext.getCurrentInstance().addMessage(null,
					new FacesMessage(FacesMessage.SEVERITY_WARN, "Info", "No se encontró el servicio"));

		}

	}

	public List<String> getBuses() {
		return buses;
	}

	public void setBuses(List<String> buses) {
		this.buses = buses;
	}

	public List<String> getConductores() {
		return conductores;
	}

	public void setConductores(List<String> conductores) {
		this.conductores = conductores;
	}

	public List<String> getRutas() {
		return rutas;
	}

	public void setRutas(List<String> rutas) {
		this.rutas = rutas;
	}

	public List<Servicio> getServicios() {
		return servicios;
	}

	public void setServicios(List<Servicio> servicios) {
		this.servicios = servicios;
	}

	public int getPos() {
		return pos;
	}

	public void setPos(int pos) {
		this.pos = pos;
	}

	public List<Servicio> datos() {
		return dt;
	}

	public List<Servicio> getDt() {
		return dt;
	}

	public void setDt(List<Servicio> dt) {
		this.dt = dt;
	}

	public void cleanValues() {

		bus = "";

		conductor = "";

		ruta = "";
		fechaInicio = null;
		fechaFin = null;

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

	public String getValorDialog() {
		return valorDialog;
	}

	public void setValorDialog(String valorDialog) {
		this.valorDialog = valorDialog;
	}

	public String getRuta() {
		return ruta;
	}

	public void setRuta(String ruta) {
		this.ruta = ruta;
	}

	public ITmioServiciosLogicRemota getServicioLogic() {
		return servicioLogic;
	}

	public void setServicioLogic(ITmioServiciosLogicRemota servicioLogic) {
		this.servicioLogic = servicioLogic;
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
