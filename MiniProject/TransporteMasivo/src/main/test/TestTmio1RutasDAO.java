import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.GregorianCalendar;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
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

public class TestTmio1RutasDAO {

	private EntityManagerFactory emfactory = Persistence.createEntityManagerFactory("TransporteMasivo");

	private EntityManager entityManager = emfactory.createEntityManager();

	private TMIO1_BUSES tmio1_buses = new TMIO1_BUSES();
	private TMIO1_CONDUCTORES tmio1_conductores = new TMIO1_CONDUCTORES();
	private TMIO1_RUTAS tmio1_rutas = new TMIO1_RUTAS();
	private TMIO1_SERVICIOS tmio1_servicios = new TMIO1_SERVICIOS();

	public void escenarioRangoDias() {
		entityManager.getTransaction().begin();

		Tmio1Conductore conductor = new Tmio1Conductore();
		conductor.setApellidos("Munoz TestRutas4");
		conductor.setCedula("1321");
		GregorianCalendar fContratacion = new GregorianCalendar();
		fContratacion.set(2006, 03, 04);
		conductor.setFechaContratacion(fContratacion.getTime());
		GregorianCalendar fNacimiento = new GregorianCalendar();
		fNacimiento.set(1995, 03, 04);
		conductor.setFechaNacimiento(fNacimiento.getTime());

		conductor.setNombre("Juan Carlos");
		conductor.setTmio1Servicios(new ArrayList<Tmio1Servicio>());
		conductor.setTmio1ServiciosSitios(new ArrayList<Tmio1ServiciosSitio>());
		tmio1_conductores.save(entityManager, conductor);

		Tmio1Bus bus1 = new Tmio1Bus();
		bus1.setCapacidad(BigDecimal.TEN);
		// sec
//			bus.setId(11);
		bus1.setMarca("Audi");
		bus1.setModelo(BigDecimal.ONE);
		bus1.setPlaca("a16");
		bus1.setTipo("A");
		bus1.setTmio1Servicios(new ArrayList<Tmio1Servicio>());
		bus1.setTmio1ServiciosSitios(new ArrayList<Tmio1ServiciosSitio>());
		tmio1_buses.save(entityManager, bus1);

		Tmio1Ruta ruta = new Tmio1Ruta();
		ruta.setActiva("S");
		ruta.setDescripcion("uao");
		ruta.setDiaFin(new BigDecimal("7"));
		ruta.setDiaInicio(new BigDecimal("6"));
		ruta.setHoraFin(new BigDecimal("8"));
		ruta.setHoraInicio(new BigDecimal("3"));
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
		fInicio.set(2008, 2, 1);
		GregorianCalendar fFin = new GregorianCalendar();
		fFin.set(2019, 9, 27);
		servicio1PK.setFechaFin(fFin.getTime());
		servicio1PK.setFechaInicio(fInicio.getTime());
		servicio1PK.setIdBus(bus1.getId());
		servicio1PK.setIdRuta(ruta.getId());

		Tmio1Servicio servicio1 = new Tmio1Servicio();
		// hay que asignarles id?
		servicio1.setId(servicio1PK);
		servicio1.setTmio1Bus(bus1);
		servicio1.setTmio1Conductore(conductor);
		servicio1.setTmio1Ruta(ruta);
		tmio1_servicios.save(entityManager, servicio1);

		entityManager.getTransaction().commit();

	}

	public int crearRuta() {

		entityManager.getTransaction().begin();

		Tmio1Ruta ruta = new Tmio1Ruta();
		ruta.setActiva("S");
		ruta.setDescripcion("icesi-carulla");
		ruta.setDiaFin(new BigDecimal("7"));
		ruta.setDiaInicio(new BigDecimal("6"));
		ruta.setHoraFin(new BigDecimal("9"));
		ruta.setHoraInicio(new BigDecimal("3"));
		// sec
		// ruta.setId(1);
		ruta.setNumero("A12");
		ruta.setTmio1Servicios(new ArrayList<Tmio1Servicio>());
		ruta.setTmio1ServiciosSitios(new ArrayList<Tmio1ServiciosSitio>());
		ruta.setTmio1SitiosRutas1(new ArrayList<Tmio1SitiosRuta>());
		ruta.setTmio1SitiosRutas2(new ArrayList<Tmio1SitiosRuta>());
		tmio1_rutas.save(entityManager, ruta);

		entityManager.getTransaction().commit();

		return ruta.getId();
	}
/**
 * working!
 */
	@Test
	public void findUpdateAndDelete() {

		// find
		int idRuta = crearRuta();
		Tmio1Ruta ruta = tmio1_rutas.findById(entityManager, idRuta);
		assertNotNull(ruta);

		entityManager.getTransaction().begin();
		ruta.setDiaInicio(new BigDecimal("7"));
		// update
		tmio1_rutas.update(entityManager, ruta);
		entityManager.getTransaction().commit();
		ruta = tmio1_rutas.findById(entityManager, idRuta);
		assertEquals(ruta.getDiaInicio(), new BigDecimal("7"));

		// delete
		entityManager.getTransaction().begin();
		ruta = tmio1_rutas.findById(entityManager, idRuta);
		tmio1_rutas.delete(entityManager, ruta);
		entityManager.getTransaction().commit();
		boolean deleted = false;
		try {
			ruta = tmio1_rutas.findById(entityManager, idRuta);
		} catch (Exception e) {
			deleted = true;
		}
		assertTrue(deleted);
	}

	/**
	 * Permita que las rutas puedan buscarse por rango de días
	 *  working!
	 */
	@Test
	public void TestRangoDias() {
		try {
			escenarioRangoDias();
		} catch (Exception e) {

		}
		BigDecimal diaInicio = new BigDecimal("6");
		BigDecimal diaFin = new BigDecimal("7");

		List<Tmio1Ruta> lista = tmio1_rutas.findByRangoDias(entityManager, diaInicio, diaFin);
		System.out.println("por rango de dias:");
		for (Tmio1Ruta ruta : lista) {
			System.out.println(ruta.getId());
		}
		System.out.println();
	}
}
