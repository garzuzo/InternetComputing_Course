import static org.junit.Assert.assertTrue;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
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
import co.edu.icesi.mio.modelo.Tmio1Servicio;
import co.edu.icesi.mio.modelo.Tmio1ServiciosSitio;

public class TestTmio1ConductoresDAO {

	private EntityManagerFactory emfactory = Persistence.createEntityManagerFactory("TransporteMasivo");

	private EntityManager entityManager = emfactory.createEntityManager();

	private TMIO1_BUSES tmio1_buses = new TMIO1_BUSES();
	private TMIO1_CONDUCTORES tmio1_conductores = new TMIO1_CONDUCTORES();
	private TMIO1_RUTAS tmio1_rutas = new TMIO1_RUTAS();
	private TMIO1_SERVICIOS tmio1_servicios = new TMIO1_SERVICIOS();

	public void crearConductor() {
		entityManager.getTransaction().begin();
		Tmio1Conductore conductor = new Tmio1Conductore();
		conductor.setApellidos("Garzon");
		conductor.setCedula("17");
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
	}

	@Test
	public void findByNombre() {
//crearConductor();
		List<Tmio1Conductore> lista = tmio1_conductores.findByNombre(entityManager, "Sandra");
		System.out.println("por nombre");
		for (Tmio1Conductore cond : lista) {
			System.out.print(cond.getCedula() + " ");
		}
		System.out.println();

	}

	@Test
	public void findByApellidos() {
		List<Tmio1Conductore> lista = tmio1_conductores.findByApellidos(entityManager, "Nino");
		
		
		System.out.println("por apellido");
		for (Tmio1Conductore cond : lista) {
			System.out.print(cond.getNombre() + " ");
		}
		System.out.println();

	}

	@Test
	public void findByCedula() {
		crearConductor();
		Tmio1Conductore lista = tmio1_conductores.findByCedula(entityManager, "14");
		
		
		System.out.println("nombre de la persona encontrada: " + lista.getNombre());

	}

	@Test
	public void encontrarPorParametros() {

	}

	@Test
	public void obtenerConductoresMultipleServicioAsignado() {

	}

	/**
	 * 
	 * Se obtienen los conductores ordenados por fecha de nacimiento
	 */
	@Test
	public void obtenerConductores() {
		List<Tmio1Conductore> lista = tmio1_conductores.obtenerConductores(entityManager);
		
		
		System.out.println("conductores ordenados por fecha de nacimiento");
		for (Tmio1Conductore cond : lista) {
			System.out.print(cond.getCedula() + " ");
		}
		System.out.println();

	}
}
