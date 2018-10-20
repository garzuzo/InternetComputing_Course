package co.edu.icesi.mio.dao;

import java.math.BigDecimal;
import java.util.List;

import javax.persistence.EntityManager;

import co.edu.icesi.mio.model.Tmio1Bus;

public interface ITmio1_Buses_DAO {

	//normales
	public void save(EntityManager em, Tmio1Bus bus);
	public void update(EntityManager em, Tmio1Bus bus);
	public void delete(EntityManager em, Tmio1Bus bus);
	public List<Tmio1Bus> findAll(EntityManager em);
	public Tmio1Bus findById(EntityManager em, Integer id);
			
	//adicionales
	public List<Tmio1Bus> findByModel(EntityManager em, BigDecimal model);
	public List<Tmio1Bus> findByType(EntityManager em, String type);
	public List<Tmio1Bus> findByCapacity(EntityManager em, BigDecimal capacity);
}
