package co.edu.icesi.mio.dao;

import java.util.Date;
import java.util.GregorianCalendar;
import java.util.List;

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
		String jpql = "Select a from Tmio1Conductore a where a.cedula=" + cedula;
		return (Tmio1Conductore) entityManager.createQuery(jpql).getSingleResult();
	}

	@Override
	public List<Tmio1Conductore> findByApellidos(EntityManager entityManager, String apellidos) {
		String jpql = "Select a from Tmio1Conductore a where a.apellidos=" + apellidos;
		return entityManager.createQuery(jpql).getResultList();
	}

	@Override
	public List<Tmio1Conductore> findByNombre(EntityManager entityManager, String nombre) {
		String jpql = "Select a from Tmio1Conductore a where a.nombre=" + nombre;
		return entityManager.createQuery(jpql).getResultList();
	}

	/**
	 * 
	 * El(los) conductor(es) con sus datos, ordenados por fecha de nacimiento
	 */
	@Override
	public List<Tmio1Conductore> obtenerConductores(EntityManager entityManager) {
		String jpql = "Select a from Tmio1Conductore a ORDER BY a.fechaNacimiento : Date ASC";
		return entityManager.createQuery(jpql).getResultList();
	}

	@Override
	public List<Tmio1Conductore> obtenerConductoresMultipleServicioAsignado(EntityManager entityManager) {
		// fecha Actual
		Date fechaActual = GregorianCalendar.getInstance().getTime();
		String jpql = "SELECT C FROM Tmio1Conductore C WHERE C.fechaContratacion >=" + fechaActual
				+ " AND EXCEPT SELECT C1 FROM Tmio1Servicio S, Tmio1Conductore C1 WHERE S.Tmio1Conductore=C1.cedula";

		return entityManager.createQuery(jpql).getResultList();
	}

	/**
	 * Listado de los conductores que no tienen servicios asignados que no hayan
	 * terminado (están libres).
	 */
	@Override
	public List<Tmio1Conductore> obtenerConductoresLibres(EntityManager entityManager) {

		String jpql = "Select a from Tmio1Conductore a";
		return entityManager.createQuery(jpql).getResultList();
	}

	@Override
	public List<Tmio1Conductore> findAll(EntityManager entityManager) {
		String jpql = "Select a from Tmio1Conductore a";
		return entityManager.createQuery(jpql).getResultList();
	}

}
