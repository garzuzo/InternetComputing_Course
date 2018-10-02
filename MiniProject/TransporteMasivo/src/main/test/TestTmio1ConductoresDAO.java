import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertTrue;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.List;
import java.util.Map;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.NoResultException;
import javax.persistence.Persistence;

import org.junit.Test;

import co.edu.icesi.mio.dao.TMIO1_BUSES;
import co.edu.icesi.mio.dao.TMIO1_CONDUCTORES;
import co.edu.icesi.mio.dao.TMIO1_RUTAS;
import co.edu.icesi.mio.dao.TMIO1_SERVICIOS;
import co.edu.icesi.mio.modelo.Tmio1Bus;
import co.edu.icesi.mio.modelo.Tmio1Conductore;
import co.edu.icesi.mio.modelo.Tmio1Ruta;
import co.edu.icesi.mio.modelo.Tmio1Servicio;
import co.edu.icesi.mio.modelo.Tmio1ServicioPK;
import co.edu.icesi.mio.modelo.Tmio1ServiciosSitio;
import co.edu.icesi.mio.modelo.Tmio1SitiosRuta;

public class TestTmio1ConductoresDAO {

	private EntityManagerFactory emfactory = Persistence.createEntityManagerFactory("TransporteMasivo");

	private EntityManager entityManager = emfactory.createEntityManager();

	private TMIO1_BUSES tmio1_buses = new TMIO1_BUSES();
	private TMIO1_CONDUCTORES tmio1_conductores = new TMIO1_CONDUCTORES();
	private TMIO1_RUTAS tmio1_rutas = new TMIO1_RUTAS();
	private TMIO1_SERVICIOS tmio1_servicios = new TMIO1_SERVICIOS();

	public String crearConductor() {
		entityManager.getTransaction().begin();
		Tmio1Conductore conductor = new Tmio1Conductore();
		conductor.setApellidos("Garzon");
		conductor.setCedula("1000");
		GregorianCalendar fContratacion = new GregorianCalendar();
		fContratacion.set(2007, 03, 04);
		conductor.setFechaContratacion(fContratacion.getTime());
		GregorianCalendar fNacimiento = new GregorianCalendar();
		fNacimiento.set(2000, 03, 04);
		conductor.setFechaNacimiento(fNacimiento.getTime());

		conductor.setNombre("Jaime");
		conductor.setTmio1Servicios(new ArrayList<Tmio1Servicio>());
		conductor.setTmio1ServiciosSitios(new ArrayList<Tmio1ServiciosSitio>());
		tmio1_conductores.save(entityManager, conductor);
		entityManager.getTransaction().commit();
		return conductor.getCedula();
	}

	public void escenarioConductorLibre() {
		entityManager.getTransaction().begin();
		Tmio1Conductore conductor = new Tmio1Conductore();
		conductor.setApellidos("Garzon");
		conductor.setCedula("1151");
		GregorianCalendar fContratacion = new GregorianCalendar();
		fContratacion.set(2020, 04, 04);
		conductor.setFechaContratacion(fContratacion.getTime());
		GregorianCalendar fNacimiento = new GregorianCalendar();
		fNacimiento.set(2000, 03, 04);
		conductor.setFechaNacimiento(fNacimiento.getTime());

		conductor.setNombre("Jaime");
		conductor.setTmio1Servicios(new ArrayList<Tmio1Servicio>());
		conductor.setTmio1ServiciosSitios(new ArrayList<Tmio1ServiciosSitio>());
		tmio1_conductores.save(entityManager, conductor);
		entityManager.getTransaction().commit();

	}

	public void escenarioConductorMultipleServicio() {

		entityManager.getTransaction().begin();

		Tmio1Conductore conductor = new Tmio1Conductore();
		conductor.setApellidos("Garzon");
		conductor.setCedula("1231");
		GregorianCalendar fContratacion = new GregorianCalendar();
		fContratacion.set(2007, 03, 04);
		conductor.setFechaContratacion(fContratacion.getTime());
		GregorianCalendar fNacimiento = new GregorianCalendar();
		fNacimiento.set(2000, 03, 04);
		conductor.setFechaNacimiento(fNacimiento.getTime());

		conductor.setNombre("Jaime");
		conductor.setTmio1Servicios(new ArrayList<Tmio1Servicio>());
		conductor.setTmio1ServiciosSitios(new ArrayList<Tmio1ServiciosSitio>());
		tmio1_conductores.save(entityManager, conductor);

		Tmio1Bus bus1 = new Tmio1Bus();
		bus1.setCapacidad(BigDecimal.TEN);
		// sec
//	bus.setId(11);
		bus1.setMarca("volvote");
		bus1.setModelo(BigDecimal.ONE);
		bus1.setPlaca("a20");
		bus1.setTipo("A");
		bus1.setTmio1Servicios(new ArrayList<Tmio1Servicio>());
		bus1.setTmio1ServiciosSitios(new ArrayList<Tmio1ServiciosSitio>());
		tmio1_buses.save(entityManager, bus1);

		Tmio1Bus bus2 = new Tmio1Bus();
		bus2.setCapacidad(BigDecimal.TEN);
		// sec
//	bus.setId(11);
		bus2.setMarca("toyota");
		bus2.setModelo(new BigDecimal("2"));
		bus2.setPlaca("ac29");
		bus2.setTipo("B");
		bus2.setTmio1Servicios(new ArrayList<Tmio1Servicio>());
		bus2.setTmio1ServiciosSitios(new ArrayList<Tmio1ServiciosSitio>());
		tmio1_buses.save(entityManager, bus2);

		Tmio1Ruta ruta = new Tmio1Ruta();
		ruta.setActiva("S");
		ruta.setDescripcion("icesi-javeriana");
		ruta.setDiaFin(new BigDecimal("7"));
		ruta.setDiaInicio(new BigDecimal("6"));
		ruta.setHoraFin(new BigDecimal("7"));
		ruta.setHoraInicio(new BigDecimal("18"));
		// sec
		// ruta.setId(1);
		ruta.setNumero("A12");
		ruta.setTmio1Servicios(new ArrayList<Tmio1Servicio>());
		ruta.setTmio1ServiciosSitios(new ArrayList<Tmio1ServiciosSitio>());
		ruta.setTmio1SitiosRutas1(new ArrayList<Tmio1SitiosRuta>());
		ruta.setTmio1SitiosRutas2(new ArrayList<Tmio1SitiosRuta>());
		tmio1_rutas.save(entityManager, ruta);
		/**
		 * servicioPK
		 * 
		 */
		// servicio PK
		Tmio1ServicioPK servicio1PK = new Tmio1ServicioPK();
		servicio1PK.setCedulaConductor(conductor.getCedula());

		GregorianCalendar fInicio = new GregorianCalendar();
		fInicio.set(2007, 03, 04);
		GregorianCalendar fFin = new GregorianCalendar();
		fFin.set(2018, 9, 30);
		servicio1PK.setFechaFin(fInicio.getTime());
		servicio1PK.setFechaInicio(fFin.getTime());
		servicio1PK.setIdBus(bus1.getId());
		servicio1PK.setIdRuta(ruta.getId());

		Tmio1Servicio servicio1 = new Tmio1Servicio();
		// hay que asignarles id?
		servicio1.setId(servicio1PK);
		servicio1.setTmio1Bus(bus1);
		servicio1.setTmio1Conductore(conductor);
		servicio1.setTmio1Ruta(ruta);
		tmio1_servicios.save(entityManager, servicio1);

		/**
		 * servicioPK
		 * 
		 */
		// servicio PK
		Tmio1ServicioPK servicio2PK = new Tmio1ServicioPK();
		servicio2PK.setCedulaConductor(conductor.getCedula());

		GregorianCalendar fInicio2 = new GregorianCalendar();
		fInicio2.set(2007, 03, 04);
		GregorianCalendar fFin2 = new GregorianCalendar();
		fFin2.set(2018, 9, 30);
		servicio2PK.setFechaFin(fInicio2.getTime());
		servicio2PK.setFechaInicio(fFin2.getTime());
		servicio2PK.setIdBus(bus2.getId());
		servicio2PK.setIdRuta(ruta.getId());

		Tmio1Servicio servicio2 = new Tmio1Servicio();
		// hay que asignarles id?
		servicio2.setId(servicio2PK);
		servicio2.setTmio1Bus(bus2);
		servicio2.setTmio1Conductore(conductor);
		servicio2.setTmio1Ruta(ruta);
		tmio1_servicios.save(entityManager, servicio2);

		entityManager.getTransaction().commit();
	}

	/**
	 * working!
	 */
	@Test
	public void findByCedula() {
		String cedula = crearConductor();

		Tmio1Conductore conductor = tmio1_conductores.findByCedula(entityManager, cedula);
		assertNotNull(conductor);
		System.out.println("conductor encontrado:" + conductor.getNombre());
		entityManager.getTransaction().begin();
		tmio1_conductores.delete(entityManager, conductor);
		entityManager.getTransaction().commit();
		boolean test = false;
		try {
			conductor = tmio1_conductores.findByCedula(entityManager, cedula);
		} catch (NoResultException e) {
			test = true;
		}
		assertTrue(test);

	}
/**
 * working!
 */
	@Test
	public void findByNombre() {
		String cedula = crearConductor();
		Tmio1Conductore conductor = tmio1_conductores.findByCedula(entityManager, cedula);

		List<Tmio1Conductore> lista = tmio1_conductores.findByNombre(entityManager, "Jaime");
		System.out.println("encontrar por nombre:");
		for (Tmio1Conductore cond : lista) {
			System.out.print(cond.getCedula() + " ");
		}
		System.out.println();

		entityManager.getTransaction().begin();
		tmio1_conductores.delete(entityManager, conductor);
		entityManager.getTransaction().commit();

	}

	/**
	 * working!
	 */
	@Test
	public void findByApellidos() {
		String cedula = crearConductor();
		Tmio1Conductore conductor = tmio1_conductores.findByCedula(entityManager, cedula);

		List<Tmio1Conductore> lista = tmio1_conductores.findByApellidos(entityManager, "Nino");

		System.out.println("encontrar por apellido:");
		for (Tmio1Conductore cond : lista) {
			System.out.print(cond.getCedula() + " ");
		}
		System.out.println();

		entityManager.getTransaction().begin();
		tmio1_conductores.delete(entityManager, conductor);
		entityManager.getTransaction().commit();
	}

	/**
	 * 
	 * Listado de los conductores que no tienen servicios asignados que no hayan
	 * terminado (están libres).
	 * working!
	 */
	@Test
	public void obtenerConductoresLibresTest() {
		try {
			escenarioConductorLibre();
		} catch (Exception e) {

		}
		List<Tmio1Conductore> lista = tmio1_conductores.obtenerConductoresLibres(entityManager);

		System.out.println("conductores libres:");
		for (Tmio1Conductore cond : lista) {
			System.out.print(cond.getCedula() + " ");
		}
		System.out.println();
	}

	/**
	 * Mostrar los conductores que han tenido asignados servicios en más de un bus.
	 * working!
	 */
	@Test
	public void obtenerConductoresMultipleServicioAsignadoTest() {
		try {
			escenarioConductorMultipleServicio();
		} catch (Exception e) {

		}

		List<Tmio1Conductore> lista = tmio1_conductores.obtenerConductoresMultipleServicioAsignado(entityManager);

		System.out.println("conductores con varios servicios asignados:");
		for (Tmio1Conductore cond : lista) {

			System.out.print(cond.getCedula() + " ");
		}
		System.out.println();
	}

	/**
	 * 
	 * Se obtienen los conductores ordenados por fecha de nacimiento
	 * working!
	 */
	@Test
	public void obtenerConductoresOrdenadosTest() {
		List<Tmio1Conductore> lista = tmio1_conductores.obtenerConductores(entityManager);

		System.out.println("conductores ordenados por fecha de nacimiento");
		for (Tmio1Conductore cond : lista) {
			System.out.print(cond.getCedula() + " ");
		}
		System.out.println();

	}
}
