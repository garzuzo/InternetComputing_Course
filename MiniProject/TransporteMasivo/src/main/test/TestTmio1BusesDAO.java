
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertTrue;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.NoResultException;
import javax.persistence.Persistence;

import org.junit.Test;

import co.edu.icesi.mio.dao.*;
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
import co.edu.icesi.mio.modelo.Tmio1ServiciosSitioPK;
import co.edu.icesi.mio.modelo.Tmio1Sitio;
import co.edu.icesi.mio.modelo.Tmio1SitiosRuta;
import co.edu.icesi.mio.modelo.Tmio1SitiosRutaPK;

public class TestTmio1BusesDAO {

	private EntityManagerFactory emfactory = Persistence.createEntityManagerFactory("TransporteMasivo");

	private EntityManager entityManager = emfactory.createEntityManager();

	private TMIO1_BUSES tmio1_buses = new TMIO1_BUSES();
	private TMIO1_CONDUCTORES tmio1_conductores = new TMIO1_CONDUCTORES();
	private TMIO1_RUTAS tmio1_rutas = new TMIO1_RUTAS();
	private TMIO1_SERVICIOS tmio1_servicios = new TMIO1_SERVICIOS();

	public void anadirDatos() {

		entityManager.getTransaction().begin();

		Tmio1Bus bus = new Tmio1Bus();
		bus.setCapacidad(BigDecimal.TEN);
		// sec
//	bus.setId(11);
		bus.setMarca("volvito");
		bus.setModelo(BigDecimal.ONE);
		bus.setPlaca("ab290");
		bus.setTipo("A");
		bus.setTmio1Servicios(new ArrayList<Tmio1Servicio>());
		bus.setTmio1ServiciosSitios(new ArrayList<Tmio1ServiciosSitio>());
		tmio1_buses.save(entityManager, bus);

		Tmio1Conductore conductor = new Tmio1Conductore();
		conductor.setApellidos("Montes");
		conductor.setCedula("1");
		conductor.setFechaContratacion(new Date());
		conductor.setFechaNacimiento(new Date());
		conductor.setNombre("Manuela");
		conductor.setTmio1Servicios(new ArrayList<Tmio1Servicio>());
		conductor.setTmio1ServiciosSitios(new ArrayList<Tmio1ServiciosSitio>());
		tmio1_conductores.save(entityManager, conductor);

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
		Tmio1ServicioPK servicioPK = new Tmio1ServicioPK();
		servicioPK.setCedulaConductor(conductor.getCedula());

		GregorianCalendar fInicio = new GregorianCalendar();
		fInicio.set(2007, 03, 04);
		GregorianCalendar fFin = new GregorianCalendar();
		fFin.set(2018, 9, 30);
		servicioPK.setFechaFin(fInicio.getTime());
		servicioPK.setFechaInicio(fFin.getTime());
		servicioPK.setIdBus(bus.getId());
		servicioPK.setIdRuta(ruta.getId());

		Tmio1Servicio servicio = new Tmio1Servicio();
		// hay que asignarles id?
		servicio.setId(servicioPK);
		servicio.setTmio1Bus(bus);
		servicio.setTmio1Conductore(conductor);
		servicio.setTmio1Ruta(ruta);
		tmio1_servicios.save(entityManager, servicio);

		Tmio1Sitio sitio = new Tmio1Sitio();
		sitio.setDescripcion("sitio blabla");
		// sec
		// sitio.setId(13);
		sitio.setNombre("Sitio 13");
		sitio.setTmio1ServiciosSitios(new ArrayList<Tmio1ServiciosSitio>());
		sitio.setTmio1SitiosRutas1(new ArrayList<Tmio1SitiosRuta>());
		sitio.setTmio1SitiosRutas2(new ArrayList<Tmio1SitiosRuta>());

		/**
		 * sitiosRuta1PK
		 * 
		 */
		// sitio ruta PK
		Tmio1SitiosRutaPK sitiosRuta1PK = new Tmio1SitiosRutaPK();
		sitiosRuta1PK.setIdRuta(ruta.getId());
		sitiosRuta1PK.setIdSitio((int) sitio.getId());

		Tmio1SitiosRuta sitiosRuta1 = new Tmio1SitiosRuta();
		sitiosRuta1.setTmio1Ruta1(ruta);
		sitiosRuta1.setTmio1Ruta2(ruta);
		sitiosRuta1.setTmio1Sitio1(sitio);
		sitiosRuta1.setTmio1Sitio2(sitio);

		/**
		 * sitiosRuta1PK
		 * 
		 */
		// sitio ruta PK
		Tmio1SitiosRutaPK sitiosRuta2PK = new Tmio1SitiosRutaPK();
		sitiosRuta2PK.setIdRuta(ruta.getId());
		sitiosRuta2PK.setIdSitio((int) sitio.getId());

		Tmio1SitiosRuta sitiosRuta2 = new Tmio1SitiosRuta();
		sitiosRuta2.setId(sitiosRuta2PK);
		sitiosRuta2.setTmio1Ruta1(ruta);
		sitiosRuta2.setTmio1Ruta2(ruta);
		sitiosRuta2.setTmio1Sitio1(sitio);
		sitiosRuta2.setTmio1Sitio2(sitio);

		/**
		 * serviciosSitioPK
		 * 
		 */
		Tmio1ServiciosSitioPK serviciosSitioPK = new Tmio1ServiciosSitioPK();

		serviciosSitioPK.setCedulaConductor(conductor.getCedula());

		GregorianCalendar fechaServSitio = new GregorianCalendar();
		fechaServSitio.set(2007, 03, 04);
		serviciosSitioPK.setFecha(fechaServSitio.getTime());
		serviciosSitioPK.setHoraProgramada(10);
		serviciosSitioPK.setIdBus(bus.getId());
		serviciosSitioPK.setIdRuta(ruta.getId());
		serviciosSitioPK.setIdSitios((int) sitio.getId());
		Tmio1ServiciosSitio serviciosSitio = new Tmio1ServiciosSitio();
		// id
		serviciosSitio.setId(serviciosSitioPK);
		serviciosSitio.setRealizado("S");
		serviciosSitio.setHoraReal(BigDecimal.TEN);
		serviciosSitio.setTmio1Bus(bus);
		serviciosSitio.setTmio1Conductore(conductor);
		serviciosSitio.setTmio1Ruta(ruta);
		serviciosSitio.setTmio1Sitio(sitio);

		// relaciones sitio
		sitio.getTmio1SitiosRutas1().add(sitiosRuta1);
		sitio.getTmio1SitiosRutas2().add(sitiosRuta2);
		sitio.getTmio1ServiciosSitios().add(serviciosSitio);

		// relaciones ruta falta add
		ruta.getTmio1Servicios().add(servicio);
		ruta.getTmio1SitiosRutas1().add(sitiosRuta2);
		tmio1_buses.save(entityManager, bus);
		ruta.getTmio1SitiosRutas2().add(sitiosRuta1);
		ruta.getTmio1ServiciosSitios();

		// bus
		bus.getTmio1Servicios().add(servicio);
		bus.getTmio1ServiciosSitios().add(serviciosSitio);

		// conductores
		conductor.getTmio1Servicios().add(servicio);
		conductor.getTmio1ServiciosSitios().add(serviciosSitio);

		

		entityManager.getTransaction().commit();

	}

	public void crearBus() {
		entityManager.getTransaction().begin();

		Tmio1Bus bus = new Tmio1Bus();
		bus.setCapacidad(BigDecimal.TEN);
		// sec
//	bus.setId(11);
		bus.setMarca("volvito");
		bus.setModelo(BigDecimal.TEN);
		bus.setPlaca("abc221");
		bus.setTipo("d");
		bus.setTmio1Servicios(new ArrayList<Tmio1Servicio>());
		bus.setTmio1ServiciosSitios(new ArrayList<Tmio1ServiciosSitio>());
		tmio1_buses.save(entityManager, bus);
		entityManager.getTransaction().commit();
	}

	public int agregarBus() {
		entityManager.getTransaction().begin();

		Tmio1Bus bus = new Tmio1Bus();
		bus.setCapacidad(BigDecimal.TEN);
		// sec
//	bus.setId(11);
		bus.setMarca("volvito");
		bus.setModelo(BigDecimal.TEN);
		bus.setPlaca("abc221");
		bus.setTipo("d");
		bus.setTmio1Servicios(new ArrayList<Tmio1Servicio>());
		bus.setTmio1ServiciosSitios(new ArrayList<Tmio1ServiciosSitio>());
		tmio1_buses.save(entityManager, bus);
		entityManager.getTransaction().commit();
		return bus.getId();
	}

	/**
	 * working!
	 */
	@Test
	public void findByTipoTest() {
		try {
			 crearBus();
			}catch(Exception e) {
				
			}
		List<Tmio1Bus> lista = tmio1_buses.findByTipo(entityManager, "d");
		for (Tmio1Bus bus : lista)
			System.out.println(bus);

	}

	/**
	 * working!
	 */
	@Test
	public void FindAndDeleteTest() {
		int idBus = agregarBus();

		Tmio1Bus finded = tmio1_buses.findById(entityManager, idBus);
		assertNotNull(finded);
		entityManager.getTransaction().begin();
		tmio1_buses.delete(entityManager, finded);
		entityManager.getTransaction().commit();
		boolean test = false;
		try {
			finded = tmio1_buses.findById(entityManager, idBus);
		} catch (NoResultException e) {
			test = true;
		}
		assertTrue(test);

	}

	/**
	 * working!
	 */
	@Test
	public void findByModeloTest() {
		try {
			crearBus();
		} catch (Exception e) {

		}
		List<Tmio1Bus> lista = tmio1_buses.findByModelo(entityManager, BigDecimal.TEN);
		System.out.println("por modelo");
		for (Tmio1Bus bus : lista) {
			System.out.print(bus.getId() + " ");
		}
		System.out.println();
	}

	/**
	 * working!
	 */
	@Test
	public void findByCapacidadTest() {
		try {
			crearBus();
		} catch (Exception e) {

		}
		List<Tmio1Bus> lista = tmio1_buses.findByCapacidad(entityManager, BigDecimal.TEN);
		System.out.println("por capacidad");
		for (Tmio1Bus bus : lista) {
			System.out.println(bus.getId());
		}
		System.out.println();

	}
}
