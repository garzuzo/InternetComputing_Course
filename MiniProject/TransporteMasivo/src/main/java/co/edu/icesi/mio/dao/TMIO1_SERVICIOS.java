package co.edu.icesi.mio.dao;

import java.util.Date;
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
	public List<Tmio1Servicio> findByRangoFechas(EntityManager entityManager, Date fechaInicio, Date fechaFin) {
		String psql = "select s from Tmio1Servicio s where s.fechaInicio<=" + fechaInicio + "AND s.fechaFin>="
				+ fechaFin;

		return entityManager.createQuery(psql).getResultList();
	}

	@Override
	public List<Tmio1Servicio> listarServicios(EntityManager entityManager) {

		String psql = "SELECT s FROM Tmio1Servicio s, Tmio1Ruta r WHERE s.tmioRuta=r.id AND ((r.diaInicio=6 AND r.diaFin=7) OR (r.diaInicio=7 AND r.diaFin=7))";

		return entityManager.createQuery(psql).getResultList();
	}

	@Override
	public List<Tmio1Servicio> findAll(EntityManager entityManager) {
		String jpql = "Select a from Tmio1Servicio a";
		return entityManager.createQuery(jpql).getResultList();
	}

}
