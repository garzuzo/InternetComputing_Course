package co.edu.icesi.mio.dao;

import java.math.BigDecimal;
import java.util.List;

import javax.persistence.EntityManager;

import co.edu.icesi.mio.modelo.Tmio1Bus;



public interface ITMIO1_BUSES {

	public void save(EntityManager entityManager, Tmio1Bus entity);
	public void update(EntityManager entityManager,Tmio1Bus entity);
	public void delete(EntityManager entityManager,Tmio1Bus entity);
	public List<Tmio1Bus> findByModelo(EntityManager entityManager,BigDecimal modelo);
	public List<Tmio1Bus> findByTipo(EntityManager entityManager,String tipo);
	public List<Tmio1Bus> findByCapacidad(EntityManager entityManager,BigDecimal capacidad);
	public List<Tmio1Bus> findAll(EntityManager entityManager);

}
