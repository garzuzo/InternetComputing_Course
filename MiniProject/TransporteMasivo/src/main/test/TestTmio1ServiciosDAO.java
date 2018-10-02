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

public class TestTmio1ServiciosDAO {

	private EntityManagerFactory emfactory = Persistence.createEntityManagerFactory("TransporteMasivo");

	private EntityManager entityManager = emfactory.createEntityManager();

	private TMIO1_SERVICIOS tmio1_servicios = new TMIO1_SERVICIOS();
	private TMIO1_BUSES tmio1_buses = new TMIO1_BUSES();
	private TMIO1_CONDUCTORES tmio1_conductores = new TMIO1_CONDUCTORES();
	private TMIO1_RUTAS tmio1_rutas = new TMIO1_RUTAS();

	public void escenarioListarServicios() {

		entityManager.getTransaction().begin();

		Tmio1Conductore conductor = new Tmio1Conductore();
		conductor.setApellidos("Alvinito TestServicio5");
		conductor.setCedula("12301");
		GregorianCalendar fContratacion = new GregorianCalendar();
		fContratacion.set(2007, 03, 04);
		conductor.setFechaContratacion(fContratacion.getTime());
		GregorianCalendar fNacimiento = new GregorianCalendar();
		fNacimiento.set(2000, 03, 04);
		conductor.setFechaNacimiento(fNacimiento.getTime());

		conductor.setNombre("Adriana");
		conductor.setTmio1Servicios(new ArrayList<Tmio1Servicio>());
		conductor.setTmio1ServiciosSitios(new ArrayList<Tmio1ServiciosSitio>());
		tmio1_conductores.save(entityManager, conductor);

		Tmio1Bus bus1 = new Tmio1Bus();
		bus1.setCapacidad(BigDecimal.TEN);
		// sec
//		bus.setId(11);
		bus1.setMarca("Chevrolet");
		bus1.setModelo(BigDecimal.ONE);
		bus1.setPlaca("a10");
		bus1.setTipo("A");
		bus1.setTmio1Servicios(new ArrayList<Tmio1Servicio>());
		bus1.setTmio1ServiciosSitios(new ArrayList<Tmio1ServiciosSitio>());
		tmio1_buses.save(entityManager, bus1);

		Tmio1Ruta ruta = new Tmio1Ruta();
		ruta.setActiva("S");
		ruta.setDescripcion("santiago-uao");
		ruta.setDiaFin(new BigDecimal("7"));
		ruta.setDiaInicio(new BigDecimal("5"));
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
		fInicio.set(2007, 2, 1);
		GregorianCalendar fFin = new GregorianCalendar();
		fFin.set(2017, 9, 30);
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

	/**
	 *Permita que los servicios puedan buscarse por rango de fechas
	 *working!
	 */
	@Test
	public void rangoFechaTest() {
		try {
		 escenarioListarServicios();
		}catch(Exception e) {
			
		}
		GregorianCalendar fInicio = new GregorianCalendar();
		fInicio.set(2007, 4, 4);
		GregorianCalendar fFin = new GregorianCalendar();
		fFin.set(2018, 11, 16);

		List<Tmio1Servicio> lista =tmio1_servicios.findByRangoFechas(entityManager, fInicio, fFin);
		System.out.println("encontrar por rango de fechas");
		for (Tmio1Servicio servicio : lista) {
			System.out.println(servicio.getTmio1Bus().getId() + " " + servicio.getTmio1Conductore().getCedula() + " "
					+ servicio.getTmio1Ruta().getId());
		}
		System.out.println();
	}

	/**
	 * prueba Listado de los servicios que actualmente se prestan sábados y domingos
	 * o solamente los domingos
	 * working!
	 */
	@Test
	public void listarServiciosTest() {
		try {
			escenarioListarServicios();
			}catch(Exception e) {
				
			}
		
		List<Tmio1Servicio> lista = tmio1_servicios.listarServicios(entityManager);
		System.out.println("conductores libres");
		for (Tmio1Servicio servicio : lista) {
			System.out.println(servicio.getTmio1Bus().getId() + " " + servicio.getTmio1Conductore().getCedula() + " "
					+ servicio.getTmio1Ruta().getId());
		}
		System.out.println();

	}
}
