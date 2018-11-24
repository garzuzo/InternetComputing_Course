package co.edu.icesi.mio.dao;

import java.math.BigDecimal;
import java.util.List;

import javax.persistence.EntityManager;

import co.edu.icesi.mio.model.Tmio1Ruta;

public interface ITmio1_Rutas_DAO {
	
	//normales
	public void save(EntityManager em, Tmio1Ruta ruta);
	public void update(EntityManager em, Tmio1Ruta ruta);
	public void delete(EntityManager em, Tmio1Ruta ruta);
	public List<Tmio1Ruta> findAll(EntityManager em);
	public Tmio1Ruta findById(EntityManager em, Integer id);

	//adicionales
	public List<Tmio1Ruta> findByRangeOfDays(EntityManager em, BigDecimal diaInicio, BigDecimal diaFin);
}
