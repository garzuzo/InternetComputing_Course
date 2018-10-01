package co.edu.icesi.mio.dao;

import java.util.Calendar;
import java.util.Date;
import java.util.List;

import javax.persistence.EntityManager;

import co.edu.icesi.mio.modelo.Tmio1Servicio;

public interface ITMIO1_SERVICIOS {

	public void save(EntityManager entityManager,Tmio1Servicio entity);
	public void update(EntityManager entityManager,Tmio1Servicio entity);
	public void delete(EntityManager entityManager,Tmio1Servicio entity);
	public List<Tmio1Servicio>  findByRangoFechas(EntityManager entityManager,Calendar fechaInicio, Calendar fechaFin);
	/*
	 * Listado de los servicios que actualmente se prestan 
	 * sábados y domingos o solamente
	 *los domingos
	 */
	public List<Tmio1Servicio> listarServicios(EntityManager entityManager);
	public List<Tmio1Servicio> findAll(EntityManager entityManager);
	
	
}
