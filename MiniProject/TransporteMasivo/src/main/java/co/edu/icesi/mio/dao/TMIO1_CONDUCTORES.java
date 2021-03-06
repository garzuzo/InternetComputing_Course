package co.edu.icesi.mio.dao;

import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.List;
import java.util.Map;

import javax.persistence.EntityManager;

import co.edu.icesi.mio.modelo.Tmio1Conductore;

public class TMIO1_CONDUCTORES implements ITMIO1_CONDUCTORES {

	@Override
	public void save(EntityManager entityManager, Tmio1Conductore entity) {
		entityManager.persist(entity);

	}

	@Override
	public void update(EntityManager entityManager, Tmio1Conductore entity) {
		entityManager.merge(entity);

	}

	@Override
	public void delete(EntityManager entityManager, Tmio1Conductore entity) {

		entityManager.remove(entity);

	}

	/**
	 * Revisar
	 */
	@Override
	public Tmio1Conductore findByCedula(EntityManager entityManager, String cedula) {
		String jpql = "Select a from Tmio1Conductore a where a.cedula=\'" + cedula + "\'";
		return (Tmio1Conductore) entityManager.createQuery(jpql).getSingleResult();
	}

	@Override
	public List<Tmio1Conductore> findByApellidos(EntityManager entityManager, String apellidos) {
		String jpql = "Select a from Tmio1Conductore a where a.apellidos=\'" + apellidos + "\'";
		return entityManager.createQuery(jpql).getResultList();
	}

	@Override
	public List<Tmio1Conductore> findByNombre(EntityManager entityManager, String nombre) {
		String jpql = "Select a from Tmio1Conductore a where a.nombre=\'" + nombre + "\'";
		return entityManager.createQuery(jpql).getResultList();
	}

	/**
	 * 
	 * El(los) conductor(es) con sus datos, ordenados por fecha de nacimiento
	 */
	@Override
	public List<Tmio1Conductore> obtenerConductores(EntityManager entityManager) {
		String jpql = "Select a from Tmio1Conductore a ORDER BY a.fechaNacimiento";
		return entityManager.createQuery(jpql).getResultList();
	}

	@Override
	public List<Tmio1Conductore> obtenerConductoresMultipleServicioAsignado(EntityManager entityManager) {
		
		//si se quiere saber cuantos servicios diferentes tiene un conductor (agregar COUNT(C))
		//String jpql = "SELECT C, COUNT(C) FROM Tmio1Conductore C, Tmio1Servicio S1, Tmio1Servicio S2, Tmio1Bus B1, Tmio1Bus B2 WHERE C.cedula=S1.tmio1Conductore.cedula AND C.cedula=S2.tmio1Conductore.cedula AND S1.id!=S2.id AND B1.id!=B2.id AND S1.tmio1Bus.id=B1.id AND S2.tmio1Bus.id=B2.id GROUP BY C";
		//en caso contrario, que solo retorne el conductor
		String jpql = "SELECT C FROM Tmio1Conductore C, Tmio1Servicio S1, Tmio1Servicio S2, Tmio1Bus B1, Tmio1Bus B2 WHERE C.cedula=S1.tmio1Conductore.cedula AND C.cedula=S2.tmio1Conductore.cedula AND S1.id!=S2.id AND B1.id!=B2.id AND S1.tmio1Bus.id=B1.id AND S2.tmio1Bus.id=B2.id GROUP BY C";
		
		return entityManager.createQuery(jpql).getResultList();
	}

	/**
	 * Listado de los conductores que no tienen servicios asignados que no hayan
	 * terminado (están libres).
	 */
	@Override
	public List<Tmio1Conductore> obtenerConductoresLibres(EntityManager entityManager) {
		// fecha Actual
		Calendar fechaActual = GregorianCalendar.getInstance();
		String fa = "\'" + fechaActual.get(Calendar.YEAR) + "-" + fechaActual.get(Calendar.MONTH) + "-"
				+ fechaActual.get(Calendar.DAY_OF_MONTH) + "\'";
		String jpql = "SELECT C FROM Tmio1Conductore C WHERE C NOT IN (SELECT C1 FROM Tmio1Servicio S, Tmio1Conductore C1 WHERE S.tmio1Conductore.cedula=C1.cedula AND S.id.fechaFin>="+fa+")";

		return entityManager.createQuery(jpql).getResultList();
	}

	@Override
	public List<Tmio1Conductore> findAll(EntityManager entityManager) {
		String jpql = "Select a from Tmio1Conductore a";
		return entityManager.createQuery(jpql).getResultList();
	}

}
