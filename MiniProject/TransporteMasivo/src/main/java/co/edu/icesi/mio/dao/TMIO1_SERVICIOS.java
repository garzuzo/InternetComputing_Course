package co.edu.icesi.mio.dao;

import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.List;

import javax.persistence.EntityManager;

import co.edu.icesi.mio.modelo.Tmio1Servicio;

public class TMIO1_SERVICIOS implements ITMIO1_SERVICIOS {

	
	@Override
	public void save(EntityManager entityManager, Tmio1Servicio entity) {
		entityManager.persist(entity);

	}

	@Override
	public void update(EntityManager entityManager, Tmio1Servicio entity) {
		entityManager.merge(entity);

	}

	@Override
	public void delete(EntityManager entityManager, Tmio1Servicio entity) {
		entityManager.remove(entity);

	}

	@Override
	public List<Tmio1Servicio> findByRangoFechas(EntityManager entityManager, Calendar fechaInicio, Calendar fechaFin) {
		
		
		
		String fInicio = "\'" + fechaInicio.get(Calendar.YEAR) + "-" + fechaInicio.get(Calendar.MONTH) + "-"
				+ fechaInicio.get(Calendar.DAY_OF_MONTH) + "\'";
		
		
		String fFin = "\'" + fechaFin.get(Calendar.YEAR) + "-" + fechaFin.get(Calendar.MONTH) + "-"
				+ fechaFin.get(Calendar.DAY_OF_MONTH) + "\'";
		
		
		
		
		String psql = "select s from Tmio1Servicio s where s.id.fechaInicio>= " + fInicio + " AND s.id.fechaFin<= "
				+ fFin;

		return entityManager.createQuery(psql).getResultList();
	}

	/**
	 * prueba Listado de los servicios que actualmente se prestan sábados y domingos o solamente
	 *los domingos
	 */
	@Override
	public List<Tmio1Servicio> listarServicios(EntityManager entityManager) {

		String psql = "SELECT s FROM Tmio1Servicio s, Tmio1Ruta r WHERE s.tmio1Ruta.id=r.id AND ((r.diaInicio=6 AND r.diaFin=7) OR (r.diaInicio=7 AND r.diaFin=7))";

		return entityManager.createQuery(psql).getResultList();
	}

	@Override
	public List<Tmio1Servicio> findAll(EntityManager entityManager) {
		String jpql = "Select a from Tmio1Servicio a";
		return entityManager.createQuery(jpql).getResultList();
	}

}
