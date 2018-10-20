package co.edu.icesi.mio.test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.PersistenceContext;

import org.junit.Test;

import co.edu.icesi.mio.dao.ITmio1_Buses_DAO;
import co.edu.icesi.mio.dao.Tmio1_Buses_DAO;
import co.edu.icesi.mio.model.Tmio1Bus;
import co.edu.icesi.mio.model.Tmio1Servicio;
import co.edu.icesi.mio.model.Tmio1ServiciosSitio;

public class Test_Tmio1_Buses_DAO {
	
	@PersistenceContext
    private EntityManager em;
    
	private Tmio1_Buses_DAO busDAO;
	
	@Test
	public void testSave() {
			em.getTransaction().begin();
			busDAO = new Tmio1_Buses_DAO();
			
			Tmio1Bus bus = new Tmio1Bus();
			
			bus.setCapacidad(new BigDecimal(1000));
			bus.setMarca("Renault");
			bus.setModelo(new BigDecimal(2015));
			bus.setPlaca("KGZ 310");
			bus.setTipo("T");
			bus.setTmio1Servicios(new ArrayList<Tmio1Servicio>());
			bus.setTmio1ServiciosSitios(new ArrayList<Tmio1ServiciosSitio>());
			
			busDAO.save(em, bus);
			em.getTransaction().commit();	
			
			Tmio1Bus bus1 = new Tmio1Bus();
			
			em.getTransaction().begin();
			bus1.setCapacidad(new BigDecimal(1000));
			bus1.setMarca("Volvo");
			bus1.setModelo(new BigDecimal(2015));
			bus1.setPlaca("KGZ 311");
			bus1.setTipo("P");
			bus1.setTmio1Servicios(new ArrayList<Tmio1Servicio>());
			bus1.setTmio1ServiciosSitios(new ArrayList<Tmio1ServiciosSitio>());
			
			busDAO.save(em, bus1);
			em.getTransaction().commit();	
			
			em.getTransaction().begin();
			Tmio1Bus bus2 = new Tmio1Bus();
			
			bus2.setCapacidad(new BigDecimal(1700));
			bus2.setMarca("Mercedes Benz");
			bus2.setModelo(new BigDecimal(2017));
			bus2.setPlaca("KGZ 320");
			bus2.setTipo("A");
			bus2.setTmio1Servicios(new ArrayList<Tmio1Servicio>());
			bus2.setTmio1ServiciosSitios(new ArrayList<Tmio1ServiciosSitio>());
			
			busDAO.save(em, bus2);
			em.getTransaction().commit();	
			
			Tmio1Bus bus3 = new Tmio1Bus();
			
			em.getTransaction().begin();
			bus3.setCapacidad(new BigDecimal(1050));
			bus3.setMarca("Volvo");
			bus3.setModelo(new BigDecimal(2016));
			bus3.setPlaca("KGZ 325");
			bus3.setTipo("A");
			bus3.setTmio1Servicios(new ArrayList<Tmio1Servicio>());
			bus3.setTmio1ServiciosSitios(new ArrayList<Tmio1ServiciosSitio>());
			
			busDAO.save(em, bus3);
			em.getTransaction().commit();	
	}
	
	@Test
	public void testUpdate() {
			em.getTransaction().begin();
			busDAO = new Tmio1_Buses_DAO();
			
			Tmio1Bus bus = busDAO.findById(em,-23);
			assertNotNull("Code not found", bus);
			bus.setCapacidad(new BigDecimal(1500));
			busDAO.update(em, bus);
			em.getTransaction().commit();	
	}
	
		
	@Test
	public void testFindByModel() {
			em.getTransaction().begin();
			
			busDAO = new Tmio1_Buses_DAO();		
			
			List<Tmio1Bus> bus = busDAO.findByModel(em,new BigDecimal(2015));
			em.getTransaction().commit();
			assertNotNull("No se encontro el bus por ese modelo", bus);	
			assertEquals(2, bus.size());
	}
	
	@Test
	public void testFindByType() {
			em.getTransaction().begin();
			busDAO = new Tmio1_Buses_DAO();
			
			List<Tmio1Bus> bus = busDAO.findByType(em,"T");
			em.getTransaction().commit();
			assertNotNull("No se encontro el bus por ese tipo", bus);
			assertEquals(1, bus.size());
	}
	
	@Test
	public void testFindByCapacity() {
			em.getTransaction().begin();
			busDAO = new Tmio1_Buses_DAO();
			
			List<Tmio1Bus> bus = busDAO.findByCapacity(em,new BigDecimal(1500));
			em.getTransaction().commit();
			assertNotNull("No se encontro el bus por esa capacidad", bus);	
			assertEquals(1, bus.size());	
	}
	
	@Test
	public void testDelete() {
			em.getTransaction().begin();

			busDAO = new Tmio1_Buses_DAO();
			
			Tmio1Bus bus = busDAO.findById(em,-21);
			em.getTransaction().commit();
			assertNotNull("El bus NO existe", bus);
			em.getTransaction().begin();
			busDAO.delete(em, bus);
			em.getTransaction().commit();	
	}
}
