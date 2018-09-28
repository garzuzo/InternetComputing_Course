package co.edu.icesi.mio.dao;

import java.math.BigDecimal;
import java.util.List;

import javax.persistence.EntityManager;

import co.edu.icesi.mio.modelo.Tmio1Bus;

public class TMIO1_BUSES implements ITMIO1_BUSES {

	@Override
	public void save(EntityManager entityManager, Tmio1Bus entity) {

		entityManager.persist(entity);

	}

	@Override
	public void update(EntityManager entityManager, Tmio1Bus entity) {

		entityManager.merge(entity);

	}

	@Override
	public void delete(EntityManager entityManager, Tmio1Bus entity) {

		entityManager.remove(entity);

	}

	@Override
	public List<Tmio1Bus> findByModelo(EntityManager entityManager, BigDecimal modelo) {
		String jpql = "Select a from Tmio1Bus a where a.modelo=" + modelo.intValue();
		return entityManager.createQuery(jpql).getResultList();
	}

	@Override
	public List<Tmio1Bus> findByTipo(EntityManager entityManager, String tipo) {
		String jpql = "Select a from Tmio1Bus a where a.tipo=" + tipo;
		return entityManager.createQuery(jpql).getResultList();
	}

	@Override
	public List<Tmio1Bus> findByCapacidad(EntityManager entityManager, BigDecimal capacidad) {

		String jpql = "Select a from Tmio1Bus a where a.capacidad=" + capacidad.intValue();
		return entityManager.createQuery(jpql).getResultList();

	}

	@Override
	public List<Tmio1Bus> findAll(EntityManager entityManager) {
		String jpql = "Select a from Tmio1Bus a";
		return entityManager.createQuery(jpql).getResultList();
	}

}
