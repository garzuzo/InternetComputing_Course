package co.edu.icesi.mio.logic;

import java.math.BigDecimal;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.HashSet;
import java.util.List;

import javax.ejb.Local;
import javax.ejb.Remote;
import javax.ejb.Stateless;
import javax.ejb.TransactionAttribute;
import javax.ejb.TransactionAttributeType;
import javax.ejb.TransactionManagement;
import javax.ejb.TransactionManagementType;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import co.edu.icesi.mio.dao.ITmio1_Buses_DAO;
import co.edu.icesi.mio.dao.ITmio1_Conductores_DAO;
import co.edu.icesi.mio.dao.ITmio1_Rutas_DAO;
import co.edu.icesi.mio.dao.ITmio1_Servicios_DAO;
import co.edu.icesi.mio.dao.Tmio1_Buses_DAO;
import co.edu.icesi.mio.dao.Tmio1_Conductores_DAO;
import co.edu.icesi.mio.dao.Tmio1_Rutas_DAO;
import co.edu.icesi.mio.dao.Tmio1_Servicios_DAO;
import co.edu.icesi.mio.model.Tmio1Bus;
import co.edu.icesi.mio.model.Tmio1Conductore;
import co.edu.icesi.mio.model.Tmio1Ruta;
import co.edu.icesi.mio.model.Tmio1Servicio;
import co.edu.icesi.mio.model.Tmio1ServicioPK;

@Stateless(name = "TmioServiciosLogic", mappedName = "TmioServiciosLogic")
@TransactionAttribute(TransactionAttributeType.REQUIRED)
@TransactionManagement(TransactionManagementType.CONTAINER)
@Local(ITmioServiciosLogicLocal.class)
@Remote(ITmioServiciosLogicRemota.class)
public class TmioServiciosLogic implements ITmioServiciosLogicLocal, ITmioServiciosLogicRemota {

	private ITmio1_Servicios_DAO servicioDAO;

	private ITmio1_Conductores_DAO conductorDAO;

	private ITmio1_Buses_DAO busDAO;

	private ITmio1_Rutas_DAO rutaDAO;

	@PersistenceContext
	private EntityManager em;

	public String createServicio(Tmio1Servicio servicio) {
		servicioDAO = new Tmio1_Servicios_DAO();
		conductorDAO = new Tmio1_Conductores_DAO();
		busDAO = new Tmio1_Buses_DAO();
		rutaDAO = new Tmio1_Rutas_DAO();
		if (servicio == null)
			return "El servicio es nulo";

		if (!validacionLlavesForaneas(servicio.getTmio1Bus(), servicio.getTmio1Conductore(), servicio.getTmio1Ruta()))
			return "No existe el bus, conductor o ruta";

		if (!validacionBusesYConductoresDisponibles(servicio.getTmio1Bus(), servicio.getTmio1Ruta(),
				servicio.getTmio1Conductore()))
			return "Los buses y/o los conductores no estan disponibles";

		if (!validacionFechaInicioFinal(servicio.getId().getFechaInicio(), servicio.getId().getFechaFin()))
			return "La fecha inicial es mayor que la fecha final";

		servicioDAO.save(em, servicio);
		return "Servicio creado exitosamente";

	}

	public String updateServicio(Tmio1Servicio servicio) {
		servicioDAO = new Tmio1_Servicios_DAO();
		conductorDAO = new Tmio1_Conductores_DAO();
		busDAO = new Tmio1_Buses_DAO();
		rutaDAO = new Tmio1_Rutas_DAO();
	

		if (servicio == null)
			return "El servicio es nulo";

		if (getServicio(servicio.getId()) == null)
			return "El servicio no se encuentra agregado";

		if (!validacionLlavesForaneas(servicio.getTmio1Bus(), servicio.getTmio1Conductore(), servicio.getTmio1Ruta()))
			return "No existe el bus, conductor o ruta";

		if (!validacionBusesYConductoresDisponibles(servicio.getTmio1Bus(), servicio.getTmio1Ruta(),
				servicio.getTmio1Conductore()))
			return "Los buses y/o los conductores no estan disponibles";

		if (!validacionFechaInicioFinal(servicio.getId().getFechaInicio(), servicio.getId().getFechaFin()))
			return "La fecha inicial es mayor que la fecha final";

		servicioDAO.update(em, servicio);
		return "Servicio creado exitosamente";
	}

	public String deleteServicio(Tmio1Servicio servicio) {
		servicioDAO = new Tmio1_Servicios_DAO();
		conductorDAO = new Tmio1_Conductores_DAO();
		busDAO = new Tmio1_Buses_DAO();
		rutaDAO = new Tmio1_Rutas_DAO();
		if (servicio == null)
			return "El servicio es nulo";

			if (getServicio(servicio.getId()) == null)
				return "El servicio no se encuentra agregado";
			
			
				servicioDAO.delete(em, getServicio(servicio.getId()));
				return "El servicio no se eliminó correctamente";
	}

	public List<Tmio1Servicio> findByRangeOfDates(Calendar d1, Calendar d2) {
		servicioDAO = new Tmio1_Servicios_DAO();
		conductorDAO = new Tmio1_Conductores_DAO();
		busDAO = new Tmio1_Buses_DAO();
		rutaDAO = new Tmio1_Rutas_DAO();
		List<Tmio1Servicio> servicios = null;
		if (d1 != null && d2 != null)
			servicios = servicioDAO.findByRangeOfDates(em, d1, d2);
		return servicios;
	}

	public Tmio1Servicio getServicio(Tmio1ServicioPK id) {
		servicioDAO = new Tmio1_Servicios_DAO();
		conductorDAO = new Tmio1_Conductores_DAO();
		busDAO = new Tmio1_Buses_DAO();
		rutaDAO = new Tmio1_Rutas_DAO();
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

	public List<Tmio1Servicio> findAllServicios() {
		return servicioDAO.findAll(em);
	}

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

	public Tmio1Bus findById(int id) {
		return busDAO.findById(em, id);
	}

	public boolean validacionCedula(String cedula) {
		return cedula.matches("[0-9]+");
	}

	public Tmio1Conductore findByCedula(String cedula) {
		Tmio1Conductore conductor = null;
		if (validacionCedula(cedula))
			conductor = conductorDAO.findByCedula(em, cedula);
		return conductor;
	}

	public Tmio1Ruta findByIdRuta(int id) {
		return rutaDAO.findById(em, id);
	}
}
