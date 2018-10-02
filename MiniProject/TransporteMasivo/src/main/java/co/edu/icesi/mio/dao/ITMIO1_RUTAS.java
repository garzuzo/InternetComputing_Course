package co.edu.icesi.mio.dao;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

import javax.persistence.EntityManager;

import co.edu.icesi.mio.modelo.Tmio1Ruta;

public interface ITMIO1_RUTAS {

	
	public void save(EntityManager entityManager,Tmio1Ruta entity);
	public void update(EntityManager entityManager,Tmio1Ruta entity);
	public void delete(EntityManager entityManager,Tmio1Ruta entity);
	public List<Tmio1Ruta> findByRangoDias(EntityManager entityManager,BigDecimal diaInicio,BigDecimal diaFin);
	public List<Tmio1Ruta> findAll(EntityManager entityManager);
	public Tmio1Ruta findById(EntityManager entityManager, int id);
	
}
