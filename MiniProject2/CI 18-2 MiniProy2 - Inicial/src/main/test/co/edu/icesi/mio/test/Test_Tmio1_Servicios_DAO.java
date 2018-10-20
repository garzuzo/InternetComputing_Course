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
import org.springframework.beans.factory.annotation.Autowired;

import co.edu.icesi.mio.dao.Tmio1_Buses_DAO;
import co.edu.icesi.mio.dao.Tmio1_Conductores_DAO;
import co.edu.icesi.mio.dao.Tmio1_Rutas_DAO;
import co.edu.icesi.mio.dao.Tmio1_Servicios_DAO;
import co.edu.icesi.mio.model.Tmio1Bus;
import co.edu.icesi.mio.model.Tmio1Conductore;
import co.edu.icesi.mio.model.Tmio1Servicio;
import co.edu.icesi.mio.model.Tmio1ServicioPK;
import co.edu.icesi.mio.model.Tmio1ServiciosSitio;

public class Test_Tmio1_Servicios_DAO {

	@PersistenceContext
    private EntityManager em;
    
	@Autowired
	private Tmio1_Servicios_DAO servicioDAO;
	
//	@Test
//	public void testSave() {
//			em.getTransaction().begin();
//			servicioDAO = new Tmio1_Servicios_DAO();
//			Tmio1ServicioPK s1PK = new Tmio1ServicioPK();
//			s1PK.setCedulaConductor("12345");
//			s1PK.setIdBus(-23);
//			s1PK.setIdRuta(-46);
//			Calendar d2 = new GregorianCalendar(2018,1,20);
//			s1PK.setFechaInicio(d2.getTime());
//			Calendar d3 = new GregorianCalendar(2018,8,27);
//			s1PK.setFechaFin(d3.getTime());
//			
//			Tmio1Servicio s1= new Tmio1Servicio();
//			s1.setId(s1PK);
//			Tmio1_Buses_DAO busDAO= new Tmio1_Buses_DAO();
//			s1.setTmio1Bus(busDAO.findById(em, -23));
//			Tmio1_Conductores_DAO conductorDAO= new Tmio1_Conductores_DAO();
//			s1.setTmio1Conductore(conductorDAO.findByCedula(em, "12348"));
//			Tmio1_Rutas_DAO rutasDAO= new Tmio1_Rutas_DAO();
//			s1.setTmio1Ruta(rutasDAO.findById(em, -46));
//			
//			Tmio1ServicioPK s2PK = new Tmio1ServicioPK();
//			s2PK.setCedulaConductor("12346");
//			s2PK.setIdBus(-22);
//			s2PK.setIdRuta(-35);
//			Calendar d4 = new GregorianCalendar(2018,1,27);
//			s2PK.setFechaInicio(d4.getTime());
//			Calendar d5 = new GregorianCalendar(2018,9,27);
//			s2PK.setFechaFin(d5.getTime());
//			
//			Tmio1Servicio s2= new Tmio1Servicio();
//			s2.setId(s2PK);
//			//Tmio1_Buses_DAO bus= new Tmio1_Buses_DAO();
//			s2.setTmio1Bus(busDAO.findById(em, -22));
//			s2.setTmio1Conductore(conductorDAO.findByCedula(em, "12348"));
//			//Tmio1_Rutas_DAO rutas2= new Tmio1_Rutas_DAO();
//			s2.setTmio1Ruta(rutasDAO.findById(em, -35));
//			
//			servicioDAO.save(em, s1);
//			servicioDAO.save(em, s2);
//			em.getTransaction().commit();
//	}
	
	@Test
	public void testFindByRangeOfDates() {
		em.getTransaction().begin();
		Calendar d = new GregorianCalendar(2018,1,15);
		Calendar d2 = new GregorianCalendar(2018,11,28);
		List<Tmio1Servicio> servicios = servicioDAO.findByRangeOfDates(em, d, d2);
		em.getTransaction().commit();
		assertNotNull("No existen servicios en este rango de dias", servicios);
		//da 4 por los 2 agregados en conductor
		assertEquals(4, servicios.size());
	}
	
	@Test
	public void testServicesSaturdaysAndSundaysOrJustSundays() {
		em.getTransaction().begin();
		List<Tmio1Servicio> servicios = servicioDAO.servicesSaturdaysAndSundaysOrJustSundays(em);
		em.getTransaction().commit();
		assertNotNull("No existen servicios en dichos dias", servicios);
		assertEquals(2, servicios.size());
	}
}
