package co.edu.icesi.mio.dao;

import java.math.BigDecimal;
import java.util.List;

import javax.persistence.EntityManager;

import co.edu.icesi.mio.modelo.Tmio1Ruta;

public class TMIO1_RUTAS implements ITMIO1_RUTAS{
	
	
	@Override
	public void save(EntityManager entityManager, Tmio1Ruta entity) {
		entityManager.persist(entity);
		
	}

	@Override
	public void update(EntityManager entityManager, Tmio1Ruta entity) {
		entityManager.merge(entity);
		
	}

	@Override
	public void delete(EntityManager entityManager, Tmio1Ruta entity) {
		entityManager.remove(entity);
		
	}

	@Override
	public List<Tmio1Ruta> findByRangoDias(EntityManager entityManager, BigDecimal diaInicio, BigDecimal diaFin) {
		String jpql = "Select a from Tmio1Ruta a where a.diaInicio>="+diaInicio.intValue()+"AND a.diaFin<="+diaFin.intValue();
		return entityManager.createQuery(jpql).getResultList();
	
	}

	@Override
	public List<Tmio1Ruta> findAll(EntityManager entityManager) {
		String jpql = "Select a from Tmio1Ruta a";
		return entityManager.createQuery(jpql).getResultList();
	}

}
