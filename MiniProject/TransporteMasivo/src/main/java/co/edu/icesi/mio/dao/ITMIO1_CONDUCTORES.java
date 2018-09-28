package co.edu.icesi.mio.dao;

import java.util.List;

import javax.persistence.EntityManager;

import co.edu.icesi.mio.modelo.Tmio1Conductore;

public interface ITMIO1_CONDUCTORES {

	
	public void save(EntityManager entityManager,Tmio1Conductore entity);
	public void update(EntityManager entityManager,Tmio1Conductore entity);
	public void delete(EntityManager entityManager,Tmio1Conductore entity);
	public Tmio1Conductore findByCedula(EntityManager entityManager,String cedula);
	public List<Tmio1Conductore> findByApellidos(EntityManager entityManager,String apellidos);
	public List<Tmio1Conductore> findByNombre(EntityManager entityManager,String nombre);
	//ordenados por fecha de nacimiento
	public List<Tmio1Conductore> obtenerConductores(EntityManager entityManager);
	
	//conductores que han tenido asignados servicios en mas de un bus
	public  List<Tmio1Conductore> obtenerConductoresMultipleServicioAsignado(EntityManager entityManager);
	
	/*
	 * Listado de los conductores que no tienen servicios asignados que no hayan terminado
(están libres).
	 */
	public  List<Tmio1Conductore> obtenerConductoresLibres(EntityManager entityManager);
	
	public List<Tmio1Conductore> findAll(EntityManager entityManager);
}
