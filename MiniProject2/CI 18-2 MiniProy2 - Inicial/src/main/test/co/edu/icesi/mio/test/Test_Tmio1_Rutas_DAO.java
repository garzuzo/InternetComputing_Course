package co.edu.icesi.mio.test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
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
import co.edu.icesi.mio.model.Tmio1Conductore;
import co.edu.icesi.mio.model.Tmio1Ruta;
import co.edu.icesi.mio.model.Tmio1Servicio;
import co.edu.icesi.mio.model.Tmio1ServiciosSitio;
import co.edu.icesi.mio.model.Tmio1SitiosRuta;

public class Test_Tmio1_Rutas_DAO {
    
	@PersistenceContext
    private EntityManager em;
    
    @Autowired
    private Tmio1_Rutas_DAO rutasDAO;
    
    @Test
    public void saveTest() {
		em.getTransaction().begin();
		rutasDAO = new Tmio1_Rutas_DAO();
		
		Tmio1Ruta ruta= new Tmio1Ruta();
		ruta.setActiva("A");
		ruta.setDescripcion("ruta A a B");
		ruta.setDiaInicio(new BigDecimal(1));
		ruta.setDiaFin(new BigDecimal(6));
		ruta.setHoraInicio(new BigDecimal(4));
		ruta.setHoraFin(new BigDecimal(17));
		ruta.setNumero("P47");
    	ruta.setTmio1Servicios(new ArrayList<Tmio1Servicio>());
    	ruta.setTmio1ServiciosSitios(new ArrayList<Tmio1ServiciosSitio>());
    	ruta.setTmio1SitiosRutas1(new ArrayList<Tmio1SitiosRuta>());
		
		rutasDAO.save(em,ruta);
		em.getTransaction().commit();
    }
    
	@Test
	public void testUpdate() {
		
		em.getTransaction().begin();
		rutasDAO = new Tmio1_Rutas_DAO();
		Tmio1Ruta ruta = rutasDAO.findById(em, -46);
		assertNotNull("Code not found", ruta);
		ruta.setDiaFin(new BigDecimal(5));
		rutasDAO.update(em, ruta);
		em.getTransaction().commit();
	}
	
	private void setUpEscenario1() {
		rutasDAO = new Tmio1_Rutas_DAO();
		em.getTransaction().begin();
		
		Tmio1Ruta ruta= new Tmio1Ruta();
		ruta.setActiva("A");
		ruta.setDescripcion("ruta A a B");
		ruta.setDiaInicio(new BigDecimal(6));
		ruta.setDiaFin(new BigDecimal(7));
		ruta.setHoraInicio(new BigDecimal(4));
		ruta.setHoraFin(new BigDecimal(17));
		ruta.setNumero("P27");
    	ruta.setTmio1Servicios(new ArrayList<Tmio1Servicio>());
    	ruta.setTmio1ServiciosSitios(new ArrayList<Tmio1ServiciosSitio>());
    	ruta.setTmio1SitiosRutas1(new ArrayList<Tmio1SitiosRuta>());
		
		rutasDAO.save(em,ruta);
		em.getTransaction().commit();
		
		em.getTransaction().begin();
		
		Tmio1Ruta ruta1= new Tmio1Ruta();
		ruta1.setActiva("A");
		ruta1.setDescripcion("ruta A a B");
		ruta1.setDiaInicio(new BigDecimal(7));
		ruta1.setDiaFin(new BigDecimal(7));
		ruta1.setHoraInicio(new BigDecimal(4));
		ruta1.setHoraFin(new BigDecimal(17));
		ruta1.setNumero("P37");
		ruta1.setTmio1Servicios(new ArrayList<Tmio1Servicio>());
		ruta1.setTmio1ServiciosSitios(new ArrayList<Tmio1ServiciosSitio>());
		ruta1.setTmio1SitiosRutas1(new ArrayList<Tmio1SitiosRuta>());
		
		rutasDAO.save(em,ruta1);
		em.getTransaction().commit();
	}
	
	@Test
	public void testFindByRangeOfDays() {
		setUpEscenario1();
		//rutasDAO = new Tmio1_Rutas_DAO();
		em.getTransaction().begin();
		List<Tmio1Ruta> rutas = rutasDAO.findByRangeOfDays(em,new BigDecimal(1), new BigDecimal(7));
		em.getTransaction().commit();
		assertNotNull("No existen rutas en este rango de dias", rutas);		
		assertEquals(6, rutas.size());
	}
	
	@Test
	public void testDelete() {
		em.getTransaction().begin();
		rutasDAO = new Tmio1_Rutas_DAO();
		Tmio1Ruta ruta = rutasDAO.findById(em, -42);
		assertNotNull("La ruta NO existe", ruta);
		rutasDAO.delete(em, ruta);
		em.getTransaction().commit();
		
	}
}
