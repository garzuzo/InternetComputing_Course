package co.edu.icesi.mio.bean;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.List;

import javax.ejb.EJB;
import javax.faces.view.ViewScoped;
import javax.inject.Named;

import co.edu.icesi.mio.logic.ITmioBusesLogicRemota;
import co.edu.icesi.mio.logic.ITmioRutasLogicRemota;
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

	// bd
	private String diaFin;

	private String diaInicio;

	private String horaFin;

	private String horaInicio;

	private String numero;

	private String activa;

	private List<Tmio1Servicio> tmio1Servicio;
	private List<Tmio1ServiciosSitio> tmio1ServiciosSitio;
	private List<Tmio1SitiosRuta> tmio1SitiosRuta;

	public void crearRuta() {

		Tmio1Ruta ruta = new Tmio1Ruta();

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

		rutaLogic.add(ruta);
	}

	public void actualizarRuta() {
		Tmio1Ruta ruta = new Tmio1Ruta();
		

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
		
		
		rutaLogic.update(ruta);

	}

	public void borrarRuta() {
		Tmio1Ruta ruta = new Tmio1Ruta();
		rutaLogic.delete(ruta);
	}

	public void findByRangoDias() {
		Tmio1Ruta ruta = new Tmio1Ruta();
		rutaLogic.findByRangoDias(null, null);
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

	public String getDiaFin() {
		return diaFin;
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
