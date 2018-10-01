
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
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
import co.edu.icesi.mio.modelo.Tmio1ServiciosSitio;
import co.edu.icesi.mio.modelo.Tmio1Sitio;
import co.edu.icesi.mio.modelo.Tmio1SitiosRuta;

public class TestTmio1BusesDAO {

	private EntityManagerFactory emfactory = Persistence.createEntityManagerFactory("TransporteMasivo");

	private EntityManager entityManager = emfactory.createEntityManager();

	private TMIO1_BUSES tmio1_buses = new TMIO1_BUSES();
	private TMIO1_CONDUCTORES tmio1_conductores = new TMIO1_CONDUCTORES();
	private TMIO1_RUTAS tmio1_rutas = new TMIO1_RUTAS();
	private TMIO1_SERVICIOS tmio1_servicios = new TMIO1_SERVICIOS();

	public void escenario() {

		entityManager.getTransaction().begin();
		// tmio1_buses.begin(entityManager);

		// tmio1_conductores.begin(entityManager);
		// tmio1_rutas.begin(entityManager);
		// tmio1_servicios.begin(entityManager);
		Tmio1Bus bus = new Tmio1Bus();
		bus.setCapacidad(BigDecimal.TEN);
		// sec
//	bus.setId(11);
		bus.setMarca("volvo");
		bus.setModelo(BigDecimal.ONE);
		bus.setPlaca("abc290");
		bus.setTipo("alimentador");
		bus.setTmio1Servicios(new ArrayList<Tmio1Servicio>());
		bus.setTmio1ServiciosSitios(new ArrayList<Tmio1ServiciosSitio>());

		Tmio1Conductore conductor = new Tmio1Conductore();
		conductor.setApellidos("Garzon Saa");
		conductor.setCedula("1");
		conductor.setFechaContratacion(new Date());
		conductor.setFechaNacimiento(new Date());
		conductor.setNombre("Ernesto");
		conductor.setTmio1Servicios(new ArrayList<Tmio1Servicio>());
		conductor.setTmio1ServiciosSitios(new ArrayList<Tmio1ServiciosSitio>());

		Tmio1Ruta ruta = new Tmio1Ruta();
		ruta.setActiva("activa");
		ruta.setDescripcion("icesi-javeriana");
		ruta.setDiaFin(new BigDecimal("7"));
		ruta.setDiaInicio(new BigDecimal("6"));
		ruta.setHoraFin(new BigDecimal("7"));
		ruta.setHoraInicio(new BigDecimal("18"));
		// sec
		// ruta.setId(1);
		ruta.setNumero("12");
		ruta.setTmio1Servicios(new ArrayList<Tmio1Servicio>());
		ruta.setTmio1ServiciosSitios(new ArrayList<Tmio1ServiciosSitio>());
		ruta.setTmio1SitiosRutas1(new ArrayList<Tmio1SitiosRuta>());
		ruta.setTmio1SitiosRutas2(new ArrayList<Tmio1SitiosRuta>());

		Tmio1Servicio servicio = new Tmio1Servicio();
		// hay que asignarles id?
		// servicio.setId(ServicioPK);
		servicio.setTmio1Bus(bus);
		servicio.setTmio1Conductore(conductor);
		servicio.setTmio1Ruta(ruta);

		Tmio1Sitio sitio = new Tmio1Sitio();
		sitio.setDescripcion("sitio blabla");
		// sec
		// sitio.setId(13);
		sitio.setNombre("Sitio 13");
		sitio.setTmio1ServiciosSitios(new ArrayList<Tmio1ServiciosSitio>());
		sitio.setTmio1SitiosRutas1(new ArrayList<Tmio1SitiosRuta>());
		sitio.setTmio1SitiosRutas2(new ArrayList<Tmio1SitiosRuta>());

		Tmio1SitiosRuta sitiosRuta1 = new Tmio1SitiosRuta();
		sitiosRuta1.setTmio1Ruta1(ruta);
		sitiosRuta1.setTmio1Ruta2(ruta);
		sitiosRuta1.setTmio1Sitio1(sitio);
		sitiosRuta1.setTmio1Sitio2(sitio);

		Tmio1SitiosRuta sitiosRuta2 = new Tmio1SitiosRuta();
		sitiosRuta2.setTmio1Ruta1(ruta);
		sitiosRuta2.setTmio1Ruta2(ruta);
		sitiosRuta2.setTmio1Sitio1(sitio);
		sitiosRuta2.setTmio1Sitio2(sitio);

		Tmio1ServiciosSitio serviciosSitio = new Tmio1ServiciosSitio();
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
		ruta.getTmio1SitiosRutas2().add(sitiosRuta1);
		ruta.getTmio1ServiciosSitios();

		// bus
		bus.getTmio1Servicios().add(servicio);
		bus.getTmio1ServiciosSitios().add(serviciosSitio);

		// conductore
		conductor.getTmio1Servicios().add(servicio);
		conductor.getTmio1ServiciosSitios().add(serviciosSitio);

		tmio1_buses.save(entityManager, bus);
		tmio1_conductores.save(entityManager, conductor);
		tmio1_rutas.save(entityManager, ruta);
		tmio1_servicios.save(entityManager, servicio);

		entityManager.getTransaction().commit();
//		tmio1_buses.commit(entityManager);
//		tmio1_conductores.commit(entityManager);
//		tmio1_rutas.commit(entityManager);
//		tmio1_servicios.commit(entityManager);
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

	@Test
	public void encontrarPorParametrosTest() {

		// escenario();

	}

	@Test
	public void findByTipoTest() {
		//crearBus();
		List<Tmio1Bus> lista=tmio1_buses.findByTipo(entityManager, "c");
		for(Tmio1Bus bus:lista)
			System.out.println(bus);
		
	}

	@Test
	public void findByModeloTest() {
	//	crearBus();
		List<Tmio1Bus> lista = tmio1_buses.findByModelo(entityManager, BigDecimal.TEN);
	System.out.println("por modelo");
		for (Tmio1Bus bus : lista) {
			System.out.print(bus.getId()+" ");
		}
System.out.println();
	}

	@Test
	public void findByCapacidadTest() {
		// crearBus();
		List<Tmio1Bus> lista = tmio1_buses.findByCapacidad(entityManager, BigDecimal.TEN);
		System.out.println("por capacidad");
		for (Tmio1Bus bus : lista) {
			System.out.println(bus.getId());
		}
		System.out.println();

	}
}
