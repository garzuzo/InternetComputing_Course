package co.edu.icesi.mio.logic;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import co.edu.icesi.mio.dao.ITmio1_Conductores_DAO;
import co.edu.icesi.mio.model.Tmio1Conductore;
import co.edu.icesi.mio.model.Tmio1Servicio;
import co.edu.icesi.mio.model.Tmio1ServiciosSitio;

@Service
public class TmioConductoresLogic implements ITmioConductoresLogicLocal,ITmioConductoresLogicRemota{

	@Autowired
	private ITmio1_Conductores_DAO conductorDAO;
	
	@PersistenceContext
    private EntityManager em;
	
	@Transactional
	public void createConductor(Tmio1Conductore conductor) {

		if(conductor != null && findByCedula(conductor.getCedula())==null &&
			validacionCedula(conductor.getCedula()) && 
				validacionNombre(conductor.getNombre()) &&
					validacionApellido(conductor.getApellidos()) &&
						validacionFechaNacimiento(conductor.getFechaNacimiento()) &&
							validacionFechaContratacion(conductor.getFechaContratacion())) {
				conductorDAO.save(em,conductor);
		}
	}
	
	@Transactional
	public void updateConductor(Tmio1Conductore conductor) {
		
		if(conductor != null && findByCedula(conductor.getCedula())!=null &&
			validacionCedula(conductor.getCedula()) && 
				validacionNombre(conductor.getNombre()) &&
					validacionApellido(conductor.getApellidos()) &&
						validacionFechaNacimiento(conductor.getFechaNacimiento()) &&
							validacionFechaContratacion(conductor.getFechaContratacion())) {
				conductorDAO.update(em, conductor);

		}
	}

	@Transactional
	public void deleteConductor(Tmio1Conductore conductor) {
		if(conductor!=null && findByCedula(conductor.getCedula())!=null)
		conductorDAO.delete(em, findByCedula(conductor.getCedula()));
	}

	@Transactional
	public List<Tmio1Conductore> findByName(String name) {
		List<Tmio1Conductore> conductores= null;
		if(validacionNombre(name))
			conductores= conductorDAO.findByName(em, name);
		return conductores;
	}

	@Transactional
	public List<Tmio1Conductore> findByLastname(String lastname) {
		List<Tmio1Conductore> conductores= null;
		if(validacionApellido(lastname))
			conductores= conductorDAO.findByLastName(em, lastname);
		return conductores; 
	}

	@Transactional
	public Tmio1Conductore findByCedula(String cedula) {
		Tmio1Conductore conductor= null;
		if(validacionCedula(cedula))
			conductor= conductorDAO.findByCedula(em, cedula);
		return conductor; 
	}
	
	/**
	 * 	VALIDACIONES
	 */
	public boolean validacionCedula(String cedula) {
		return cedula.matches("[0-9]+");
	}
	
	public boolean validacionNombre(String nombre) {
		return !nombre.equals("") && nombre.length()>=3;
	}
	
	public boolean validacionApellido(String apellido) {
		return !apellido.equals("") && apellido.length()>=3;
	}
	
	public boolean validacionFechaNacimiento(Date fechaNacimiento) {
		Calendar gc1= new GregorianCalendar();
		gc1.setTime(fechaNacimiento);
		Calendar gc= new GregorianCalendar().getInstance();
		return fechaNacimiento!= null && (gc.get(GregorianCalendar.YEAR) - gc1.get(GregorianCalendar.YEAR))>=18;
	}
	
	public boolean validacionFechaContratacion(Date fechaContratacion) {
		Calendar gc1= new GregorianCalendar();
		gc1.setTime(fechaContratacion);
		Calendar gc= new GregorianCalendar().getInstance();
		
		return fechaContratacion != null && gc1.compareTo(gc)<0;
	}

	
}
