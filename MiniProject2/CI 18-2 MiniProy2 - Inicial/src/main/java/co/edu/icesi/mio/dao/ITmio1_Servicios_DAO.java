package co.edu.icesi.mio.dao;

import java.util.Calendar;
import java.util.Date;
import java.util.List;

import javax.persistence.EntityManager;

import co.edu.icesi.mio.model.Tmio1Servicio;
import co.edu.icesi.mio.model.Tmio1ServicioPK;

public interface ITmio1_Servicios_DAO {

	//normales
	public void save(EntityManager em, Tmio1Servicio servicio);
	public void update(EntityManager em, Tmio1Servicio servicio);
	public void delete(EntityManager em, Tmio1Servicio servicio);
	public List<Tmio1Servicio> findAll(EntityManager em);
	public Tmio1Servicio findById(EntityManager em, Tmio1ServicioPK id);
	
	//Adicionales
	public List<Tmio1Servicio> findByRangeOfDates(EntityManager em, Calendar fechaInicio, Calendar fechaFin);
	
	public List<Tmio1Servicio> servicesSaturdaysAndSundaysOrJustSundays(EntityManager em);
}
