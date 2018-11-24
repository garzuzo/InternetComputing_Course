package co.edu.icesi.mio.logic;

import java.math.BigDecimal;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.HashSet;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import co.edu.icesi.mio.dao.ITmio1_Buses_DAO;
import co.edu.icesi.mio.dao.ITmio1_Conductores_DAO;
import co.edu.icesi.mio.dao.ITmio1_Rutas_DAO;
import co.edu.icesi.mio.dao.ITmio1_Servicios_DAO;
import co.edu.icesi.mio.model.Tmio1Bus;
import co.edu.icesi.mio.model.Tmio1Conductore;
import co.edu.icesi.mio.model.Tmio1Ruta;
import co.edu.icesi.mio.model.Tmio1Servicio;
import co.edu.icesi.mio.model.Tmio1ServicioPK;

@Service
public class TmioServiciosLogic implements ITmioServiciosLogic {

	@Autowired
	private ITmio1_Servicios_DAO servicioDAO;

	@Autowired
	private ITmio1_Conductores_DAO conductorDAO;

	@Autowired
	private ITmio1_Buses_DAO busDAO;

	@Autowired
	private ITmio1_Rutas_DAO rutaDAO;

	@PersistenceContext
	private EntityManager em;

	@Transactional
	public void createServicio(Tmio1Servicio servicio) {
		if (servicio != null
				&& validacionLlavesForaneas(servicio.getTmio1Bus(), servicio.getTmio1Conductore(),
						servicio.getTmio1Ruta())
				&& validacionBusesYConductoresDisponibles(servicio.getTmio1Bus(), servicio.getTmio1Ruta(),
						servicio.getTmio1Conductore())
				&& validacionFechaInicioFinal(servicio.getId().getFechaInicio(), servicio.getId().getFechaFin())) {
			servicioDAO.save(em, servicio);
		}
	}

	@Transactional
	public void updateServicio(Tmio1Servicio servicio) {
		if (servicio != null && getServicio(servicio.getId()) != null
				&& validacionLlavesForaneas(servicio.getTmio1Bus(), servicio.getTmio1Conductore(),
						servicio.getTmio1Ruta())
				&& validacionBusesYConductoresDisponibles(servicio.getTmio1Bus(), servicio.getTmio1Ruta(),
						servicio.getTmio1Conductore())
				&& validacionFechaInicioFinal(servicio.getId().getFechaInicio(), servicio.getId().getFechaFin())) {
			servicioDAO.update(em, servicio);
		}
	}

	@Transactional
	public void deleteServicio(Tmio1Servicio servicio) {
		if (servicio != null && getServicio(servicio.getId()) != null)
			servicioDAO.delete(em, getServicio(servicio.getId()));
	}

	@Transactional
	public List<Tmio1Servicio> findByRangeOfDates(Calendar d1, Calendar d2) {
		List<Tmio1Servicio> servicios = null;
		if (d1 != null && d2 != null)
			servicios = servicioDAO.findByRangeOfDates(em, d1, d2);
		return servicios;
	}

	@Transactional
	public Tmio1Servicio getServicio(Tmio1ServicioPK id) {
		return servicioDAO.findById(em, id);
	}

	/**
	 * VALIDACIONES : las llaves for�neas existan; la fecha inicio est� definida y
	 * sea menor o igual que la fecha final. �La soluci�n debe validar que al
	 * momento de crear un nuevo servicio, tanto el bus como el conductor se
	 * encuentren disponibles, es decir que no se encuentren asignados a otro
	 * servicio que coincida con las fechas, d�as y horarios establecidos
	 */
	public boolean validacionLlavesForaneas(Tmio1Bus bus, Tmio1Conductore conductor, Tmio1Ruta ruta) {
		boolean validacion = false;

		if (bus != null && conductor != null && ruta != null && findById(bus.getId()) != null
				&& findByCedula(conductor.getCedula()) != null && findByIdRuta(ruta.getId()) != null)
			validacion = true;

		return validacion;
	}

	public boolean validacionFechaInicioFinal(Date fechaInicio, Date fechaFin) {

		return fechaInicio.compareTo(fechaFin) <= 0 ? true : false;
	}

	public boolean validacionBusesYConductoresDisponibles(Tmio1Bus b, Tmio1Ruta r, Tmio1Conductore c) {
		boolean validacion = false;
		if (busesConductoresLibres(b, r, c)) {
			validacion = true;
		}
		return validacion;
	}

	@Transactional
	public List<Tmio1Servicio> findAllServicios() {
		return servicioDAO.findAll(em);
	}

	@Transactional
	public HashSet<Integer> findAllBuses() {
		HashSet<Integer> hs = new HashSet<Integer>();
		List<Tmio1Bus> buses = busDAO.findAll(em);
		for (Tmio1Bus b : buses) {
			hs.add(b.getId());

		}
		return hs;
	}

	public boolean busesConductoresLibres(Tmio1Bus b, Tmio1Ruta r, Tmio1Conductore c) {

		HashSet<Integer> hs = new HashSet<Integer>();
		HashSet<String> hs1 = new HashSet<String>();
		Date fechaActual = GregorianCalendar.getInstance().getTime();
		List<Tmio1Servicio> servicios = findAllServicios();
		for (Tmio1Servicio s : servicios) {

			if ((s.getId().getFechaFin()).compareTo(fechaActual) > 0) {
				hs.add(s.getTmio1Bus().getId());
				hs1.add(s.getTmio1Conductore().getCedula());
				// tengo que hacer 3 comparaciones:
				// 1.fin>r.inicio && inicio<r.inicio
				// 2. inicio>r.inicio && fin<r.fin
				// 3. inicio<r.fin && fin>r.fin

			} else if ((s.getTmio1Ruta().getDiaFin().compareTo(r.getDiaInicio()) > 0
					&& s.getTmio1Ruta().getDiaInicio().compareTo(r.getDiaInicio()) < 0)
					|| (s.getTmio1Ruta().getDiaInicio().compareTo(r.getDiaInicio()) > 0
							&& s.getTmio1Ruta().getDiaFin().compareTo(r.getDiaFin()) < 0)
					|| (s.getTmio1Ruta().getDiaInicio().compareTo(r.getDiaFin()) < 0
							&& s.getTmio1Ruta().getDiaFin().compareTo(r.getDiaFin()) > 0)
					|| (s.getTmio1Ruta().getDiaInicio().compareTo(r.getDiaInicio()) < 0
							&& s.getTmio1Ruta().getDiaFin().compareTo(r.getDiaFin()) > 0)) {

				if ((s.getTmio1Ruta().getHoraFin().compareTo(r.getHoraInicio()) > 0
						&& s.getTmio1Ruta().getHoraInicio().compareTo(r.getHoraInicio()) < 0)
						|| (s.getTmio1Ruta().getHoraInicio().compareTo(r.getHoraInicio()) > 0
								&& s.getTmio1Ruta().getHoraFin().compareTo(r.getHoraFin()) < 0)
						|| (s.getTmio1Ruta().getHoraInicio().compareTo(r.getHoraFin()) < 0
								&& s.getTmio1Ruta().getHoraFin().compareTo(r.getHoraFin()) > 0)
						|| (s.getTmio1Ruta().getHoraInicio().compareTo(r.getHoraInicio()) < 0
								&& s.getTmio1Ruta().getHoraFin().compareTo(r.getHoraFin()) > 0)) {
					hs.add(s.getTmio1Bus().getId());
					hs1.add(s.getTmio1Conductore().getCedula());
					// se hacen las mismas comparaciones en la hora
					// si esta dentro del intervalo se agrega al hs de los rechazados
					// 4. inicio<r.inicio && fin>r.fin

				}

			}
		}

		if (!hs.contains(b.getId()) && !hs1.contains(c.getCedula())) {
			return true;
		} else {
			return false;
		}
	}

	public void conductoresLibres() {

	}

	@Transactional
	public Tmio1Bus findById(int id) {
		return busDAO.findById(em, id);
	}

	public boolean validacionCedula(String cedula) {
		return cedula.matches("[0-9]+");
	}

	@Transactional
	public Tmio1Conductore findByCedula(String cedula) {
		Tmio1Conductore conductor = null;
		if (validacionCedula(cedula))
			conductor = conductorDAO.findByCedula(em, cedula);
		return conductor;
	}

	@Transactional
	public Tmio1Ruta findByIdRuta(int id) {
		return rutaDAO.findById(em, id);
	}
}
