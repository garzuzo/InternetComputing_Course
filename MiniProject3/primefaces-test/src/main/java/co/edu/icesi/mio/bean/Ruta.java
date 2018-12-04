package co.edu.icesi.mio.bean;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import javax.ejb.EJB;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.faces.view.ViewScoped;
import javax.inject.Named;

import co.edu.icesi.mio.logic.ITmioBusesLogicRemota;
import co.edu.icesi.mio.logic.ITmioRutasLogicRemota;
import co.edu.icesi.mio.model.Tmio1Conductore;
import co.edu.icesi.mio.model.Tmio1Ruta;
import co.edu.icesi.mio.model.Tmio1Servicio;
import co.edu.icesi.mio.model.Tmio1ServiciosSitio;
import co.edu.icesi.mio.model.Tmio1SitiosRuta;

@Named
@ViewScoped
public class Ruta implements Serializable {

	@EJB(lookup = "java:global/primefaces-test-1.0-SNAPSHOT/TmioRutasLogic!co.edu.icesi.mio.logic.ITmioRutasLogicRemota")
	private ITmioRutasLogicRemota rutaLogic;
	/**
	 * 
	 */
	private static final long serialVersionUID = -8627869989734609391L;

	private String descripcion;

	private String id;

	// bd
	private String diaFin;

	private String diaInicio;

	private String horaFin;

	private String horaInicio;

	private String numero;

	private String activa;

	private String valorDialog;
	private List<Tmio1Servicio> tmio1Servicio;
	private List<Tmio1ServiciosSitio> tmio1ServiciosSitio;
	private List<Tmio1SitiosRuta> tmio1SitiosRuta;
	private List<Ruta> dt = new ArrayList<Ruta>();

	public String crearRuta() {

		Tmio1Ruta ruta = new Tmio1Ruta();

		ruta.setActiva(activa);
		ruta.setDescripcion(descripcion);
		ruta.setDiaFin(new BigDecimal(diaFin));
		ruta.setDiaInicio(new BigDecimal(diaInicio));
		ruta.setHoraFin(new BigDecimal(horaFin));
		ruta.setHoraInicio(new BigDecimal(horaInicio));
		ruta.setNumero(numero);

		tmio1Servicio = new ArrayList<Tmio1Servicio>();
		tmio1ServiciosSitio = new ArrayList<Tmio1ServiciosSitio>();
		tmio1SitiosRuta = new ArrayList<Tmio1SitiosRuta>();
		ruta.setTmio1Servicios(tmio1Servicio);
		ruta.setTmio1ServiciosSitios(tmio1ServiciosSitio);
		ruta.setTmio1SitiosRutas1(tmio1SitiosRuta);

		String ret = rutaLogic.add(ruta);
		
		if (ret.equals("Se agregó correctamente la ruta")) {
			cleanValues();
			FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Info", ret));
			
		}else {
			FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_FATAL, "error", ret));

	
			
		}
		return ret;
	}

	public String actualizarRuta() {
		Tmio1Ruta ruta = rutaLogic.findById(Integer.parseInt(id));

		ruta.setActiva(activa);
		ruta.setDescripcion(descripcion);
		ruta.setDiaFin(new BigDecimal(diaFin));
		ruta.setDiaInicio(new BigDecimal(diaInicio));
		ruta.setHoraFin(new BigDecimal(horaFin));
		ruta.setHoraInicio(new BigDecimal(horaInicio));
		ruta.setNumero(numero);
		ruta.setTmio1Servicios(tmio1Servicio);
		ruta.setTmio1ServiciosSitios(tmio1ServiciosSitio);
		ruta.setTmio1SitiosRutas1(tmio1SitiosRuta);

		String ret = rutaLogic.update(ruta);
			if (ret.equals("Se actualizó la ruta correctamente")) {
			cleanValues();
			FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Info", ret));
			
		}else {
			
			FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_FATAL, "error", ret));
			
		}

		return ret;
	}

	public String borrarRuta() {
		Tmio1Ruta ruta = rutaLogic.findById(Integer.parseInt(id));
		
		String ret = rutaLogic.delete(ruta);
			if (ret.equals("Se eliminó correctamente la ruta")) {
			cleanValues();
			FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Info", ret));
			
		}else {
			FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_FATAL, "error", ret));
				
		}
		return ret;
	}

	public void findByRangoDias() {
		Tmio1Ruta ruta = new Tmio1Ruta();
		List<Tmio1Ruta> ret = rutaLogic.findByRangoDias(new BigDecimal(diaInicio), new BigDecimal(diaFin));

		dt = new ArrayList<Ruta>();
		if (ret != null) {
			for (int i = 0; i < ret.size(); i++) {
				Ruta act = new Ruta();
				Tmio1Ruta finded = ret.get(i);
				if (finded != null) {
					act.activa = finded.getActiva();
					act.id = finded.getId() + "";
					act.descripcion = finded.getDescripcion();
					act.diaFin = finded.getDiaFin() + "";
					act.diaInicio = finded.getDiaInicio() + "";
					act.numero = finded.getNumero();
					act.horaFin = finded.getHoraFin() + "";
					act.horaInicio = finded.getHoraInicio() + "";
					dt.add(act);
				}
			}
		}else {
			FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Info", "No se encontró a ruta"));

		}

	}

	public void cleanValues() {

		descripcion = "";

		id = "";

		diaFin = "";

		diaInicio = "";

		horaFin = "";

		horaInicio = "";

		numero = "";

		activa = "";

	}

	public List<Ruta> datos() {
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

	public List<Tmio1SitiosRuta> getTmio1SitiosRuta() {
		return tmio1SitiosRuta;
	}

	public void setTmio1SitiosRuta(List<Tmio1SitiosRuta> tmio1SitiosRuta) {
		this.tmio1SitiosRuta = tmio1SitiosRuta;
	}

	public ITmioRutasLogicRemota getRutaLogic() {
		return rutaLogic;
	}

	public void setRutaLogic(ITmioRutasLogicRemota rutaLogic) {
		this.rutaLogic = rutaLogic;
	}

	public String getDescripcion() {
		return descripcion;
	}

	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}

	public List<Ruta> getDt() {
		return dt;
	}

	public void setDt(List<Ruta> dt) {
		this.dt = dt;
	}

	public String getDiaFin() {
		return diaFin;
	}

	public String getId() {
		return id;
	}

	public String getValorDialog() {
		return valorDialog;
	}

	public void setValorDialog(String valorDialog) {
		this.valorDialog = valorDialog;
	}

	public void setId(String id) {
		this.id = id;
	}

	public void setDiaFin(String diaFin) {
		this.diaFin = diaFin;
	}

	public String getDiaInicio() {
		return diaInicio;
	}

	public void setDiaInicio(String diaInicio) {
		this.diaInicio = diaInicio;
	}

	public String getHoraFin() {
		return horaFin;
	}

	public void setHoraFin(String horaFin) {
		this.horaFin = horaFin;
	}

	public String getHoraInicio() {
		return horaInicio;
	}

	public void setHoraInicio(String horaInicio) {
		this.horaInicio = horaInicio;
	}

	public String getNumero() {
		return numero;
	}

	public void setNumero(String numero) {
		this.numero = numero;
	}

	public String getActiva() {
		return activa;
	}

	public void setActiva(String activa) {
		this.activa = activa;
	}

}
