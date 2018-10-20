package co.edu.icesi.mio.test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.PersistenceContext;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;

import co.edu.icesi.mio.dao.ITmio1_Buses_DAO;
import co.edu.icesi.mio.dao.ITmio1_Conductores_DAO;
import co.edu.icesi.mio.dao.Tmio1_Buses_DAO;
import co.edu.icesi.mio.dao.Tmio1_Conductores_DAO;
import co.edu.icesi.mio.dao.Tmio1_Rutas_DAO;
import co.edu.icesi.mio.dao.Tmio1_Servicios_DAO;
import co.edu.icesi.mio.model.Tmio1Bus;
import co.edu.icesi.mio.model.Tmio1Conductore;
import co.edu.icesi.mio.model.Tmio1Ruta;
import co.edu.icesi.mio.model.Tmio1Servicio;
import co.edu.icesi.mio.model.Tmio1ServicioPK;
import co.edu.icesi.mio.model.Tmio1ServiciosSitio;

public class Test_Tmio1_Conductore_DAO {

	@PersistenceContext
    private EntityManager em;
    
	@Autowired
    private Tmio1_Conductores_DAO conductorDAO;
    
    @Test
    public void saveTest() {
		em.getTransaction().begin();
    	Tmio1Conductore tmioConductor = new Tmio1Conductore();
    	tmioConductor.setCedula("12345");
		tmioConductor.setNombre("Jack");
		tmioConductor.setApellidos("Bauer");
		Calendar d = new GregorianCalendar(2018,01,20);
		tmioConductor.setFechaContratacion(d.getTime());
		Calendar d1 = new GregorianCalendar(1998,01,20);
		tmioConductor.setFechaNacimiento(d1.getTime());
		tmioConductor.setTmio1Servicios(new ArrayList<Tmio1Servicio>());
		tmioConductor.setTmio1ServiciosSitios(new ArrayList<Tmio1ServiciosSitio>());
		
		Tmio1Conductore tmioConductor1 = new Tmio1Conductore();
		tmioConductor1.setCedula("12349");
		tmioConductor1.setNombre("Jack");
		tmioConductor1.setApellidos("Melo");
		Calendar d2 = new GregorianCalendar(2018,01,20);
		tmioConductor1.setFechaContratacion(d2.getTime());
		Calendar d3 = new GregorianCalendar(1999,01,20);
		tmioConductor1.setFechaNacimiento(d3.getTime());
		tmioConductor1.setTmio1Servicios(new ArrayList<Tmio1Servicio>());
		tmioConductor1.setTmio1ServiciosSitios(new ArrayList<Tmio1ServiciosSitio>());
		
		conductorDAO.save(em,tmioConductor);
		conductorDAO.save(em, tmioConductor1);
		em.getTransaction().commit();
    }
    
	@Test
	public void testUpdate() {
		em.getTransaction().begin();		
		Tmio1Conductore conductor = conductorDAO.findByCedula(em,"12345");
		assertNotNull("Code not found", conductor);
		conductor.setNombre("Jack Jack");
		conductorDAO.update(em, conductor);
		em.getTransaction().commit();		
	}
	
	@Test
	public void testFindByName() {
		em.getTransaction().begin();
		Tmio1Conductore conductor = conductorDAO.findByName(em, "Jack Jack").get(0);
		em.getTransaction().commit();
		assertNotNull("No se encontro el conductor por ese nombre", conductor);
	}
	
	@Test
	public void testFindByLastName() {
		em.getTransaction().begin();
		Tmio1Conductore conductor = conductorDAO.findByLastName(em,"Melo").get(0);
		em.getTransaction().commit();
		assertNotNull("No se encontro el conductor por ese apellido", conductor);
	}
	
	private void setUpEscenario1() {
		em.getTransaction().begin();
    	Tmio1Conductore tmioConductor = new Tmio1Conductore();
    	tmioConductor.setCedula("12346");
		tmioConductor.setNombre("Samantha");
		tmioConductor.setApellidos("Pocket");
		Calendar d = new GregorianCalendar(2018,01,20);
		tmioConductor.setFechaContratacion(d.getTime());
		Calendar d1 = new GregorianCalendar(1967,01,20);
		tmioConductor.setFechaNacimiento(d1.getTime());
		tmioConductor.setTmio1Servicios(new ArrayList<Tmio1Servicio>());
		tmioConductor.setTmio1ServiciosSitios(new ArrayList<Tmio1ServiciosSitio>());
		
		conductorDAO.save(em,tmioConductor);
		em.getTransaction().commit();
		
		em.getTransaction().begin();
    	Tmio1Conductore tmioConductor1 = new Tmio1Conductore();
    	tmioConductor1.setCedula("12347");
    	tmioConductor1.setNombre("Diego");
    	tmioConductor1.setApellidos("Perez");
    	Calendar d2 = new GregorianCalendar(2018,01,20);
		tmioConductor1.setFechaContratacion(d2.getTime());
		Calendar d3 = new GregorianCalendar(1997,01,20);
		tmioConductor1.setFechaNacimiento(d3.getTime());
		tmioConductor1.setTmio1Servicios(new ArrayList<Tmio1Servicio>());
		tmioConductor1.setTmio1ServiciosSitios(new ArrayList<Tmio1ServiciosSitio>());
		
		conductorDAO.save(em,tmioConductor1);
		em.getTransaction().commit();
	}
	
	private void setUpEscenario2() {
		em.getTransaction().begin();
    	Tmio1Conductore tmioConductor = new Tmio1Conductore();
    	tmioConductor.setCedula("12348");
		tmioConductor.setNombre("Johnatan");
		tmioConductor.setApellidos("Garzon");
		Calendar d = new GregorianCalendar(2018,01,20);
		tmioConductor.setFechaContratacion(d.getTime());
		Calendar d1 = new GregorianCalendar(1999,03,20);
		tmioConductor.setFechaNacimiento(d1.getTime());
		tmioConductor.setTmio1Servicios(new ArrayList<Tmio1Servicio>());
		tmioConductor.setTmio1ServiciosSitios(new ArrayList<Tmio1ServiciosSitio>());
		conductorDAO.save(em,tmioConductor);
		em.getTransaction().commit();
		
		em.getTransaction().begin();
		Tmio1_Servicios_DAO servicioDAO = new Tmio1_Servicios_DAO();
		Tmio1ServicioPK s1PK = new Tmio1ServicioPK();
		s1PK.setCedulaConductor("12348");
		s1PK.setIdBus(-20);
		s1PK.setIdRuta(-41);
		Calendar d2 = new GregorianCalendar(2018,1,20);
		s1PK.setFechaInicio(d2.getTime());
		Calendar d3 = new GregorianCalendar(2018,10,27);
		s1PK.setFechaFin(d3.getTime());
		
		Tmio1Servicio s1= new Tmio1Servicio();
		s1.setId(s1PK);
		Tmio1_Buses_DAO busDAO= new Tmio1_Buses_DAO();
		s1.setTmio1Bus(busDAO.findById(em, -20));
		Tmio1_Conductores_DAO conductorDAO= new Tmio1_Conductores_DAO();
		s1.setTmio1Conductore(conductorDAO.findByCedula(em, "12348"));
		Tmio1_Rutas_DAO rutasDAO= new Tmio1_Rutas_DAO();
		s1.setTmio1Ruta(rutasDAO.findById(em, -41));
		
		Tmio1ServicioPK s2PK = new Tmio1ServicioPK();
		s2PK.setCedulaConductor("12348");
		s2PK.setIdBus(-19);
		s2PK.setIdRuta(-36);
		Calendar d4 = new GregorianCalendar(2018,1,27);
		s2PK.setFechaInicio(d4.getTime());
		Calendar d5 = new GregorianCalendar(2018,10,27);
		s2PK.setFechaFin(d5.getTime());
		
		Tmio1Servicio s2= new Tmio1Servicio();
		s2.setId(s2PK);
		//Tmio1_Buses_DAO bus= new Tmio1_Buses_DAO();
		s2.setTmio1Bus(busDAO.findById(em, -19));
		s2.setTmio1Conductore(conductorDAO.findByCedula(em, "12348"));
		//Tmio1_Rutas_DAO rutas2= new Tmio1_Rutas_DAO();
		s2.setTmio1Ruta(rutasDAO.findById(em, -36));
		
		servicioDAO.save(em, s1);
		servicioDAO.save(em, s2);
		em.getTransaction().commit();

	}
	
	@Test
	public void testFindALlOrderedByBirthDate() {
		
		setUpEscenario1();
		em.getTransaction().begin();	
		List<Tmio1Conductore> conductores = conductorDAO.findAllOrderedByBirthDate(em);
		em.getTransaction().commit();
		assertNotNull(conductores);
		//ASI se deja despues de eliminar a 12347 y cuando 12348 ya esta agregado
		assertEquals("12346", conductores.get(0).getCedula());
		//assertEquals("12347", conductores.get(1).getCedula());
		assertEquals("12345", conductores.get(1).getCedula());
		assertEquals("12349", conductores.get(2).getCedula());
		assertEquals("12348", conductores.get(3).getCedula());
		
	}
	
	@Test
	public void testDriversServicesInMoreThanOneBus() {
		
		setUpEscenario2();
		em.getTransaction().begin();
		List<Tmio1Conductore> conductores = conductorDAO.driversWithServicesInMoreThanOneBus(em);
		em.getTransaction().commit();
		assertNotNull("No existen conductores con servicios en m�s de 1 bus", conductores);
		assertEquals("12348", conductores.get(0).getCedula());
	}
	
	@Test
	public void testDriversThatAreFree() {
		em.getTransaction().begin();
		List<Tmio1Conductore> conductores = conductorDAO.driversThatAreFree(em);
		em.getTransaction().commit();
		assertNotNull("No existen conductores libres", conductores);
		//Es 4 antes del delete siguiente, es 3 despues del delete
		assertEquals(3, conductores.size());
	}
	
	@Test
	public void testDelete() {
		em.getTransaction().begin();
		Tmio1Conductore conductor = conductorDAO.findByCedula(em,"12347");
		assertNotNull("El conductor NO existe", conductor);
		conductorDAO.delete(em, conductor);
		em.getTransaction().commit();
		
	}
}
