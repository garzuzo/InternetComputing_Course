package co.edu.icesi.mio.logic;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.List;

import javax.ejb.Local;
import javax.ejb.Remote;
import javax.ejb.Stateless;
import javax.ejb.TransactionAttribute;
import javax.ejb.TransactionAttributeType;
import javax.ejb.TransactionManagement;
import javax.ejb.TransactionManagementType;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import co.edu.icesi.mio.dao.ITmio1_Conductores_DAO;
import co.edu.icesi.mio.dao.Tmio1_Conductores_DAO;
import co.edu.icesi.mio.model.Tmio1Conductore;
import co.edu.icesi.mio.model.Tmio1Servicio;
import co.edu.icesi.mio.model.Tmio1ServiciosSitio;

@Stateless(name = "TmioConductoresLogic", mappedName = "TmioConductoresLogic")
@TransactionAttribute(TransactionAttributeType.REQUIRED)
@TransactionManagement(TransactionManagementType.CONTAINER)
@Local(ITmioConductoresLogicLocal.class)
@Remote(ITmioConductoresLogicRemota.class)
public class TmioConductoresLogic implements ITmioConductoresLogicLocal, ITmioConductoresLogicRemota {

	private ITmio1_Conductores_DAO conductorDAO;

	@PersistenceContext
	private EntityManager em;

	public String createConductor(Tmio1Conductore conductor) {
		conductorDAO = new Tmio1_Conductores_DAO();
		if (conductor == null)
			return "El conductor es nulo";

		if (findByCedula(conductor.getCedula()) != null)
			return "El conductor ya existe";

		if (!validacionCedula(conductor.getCedula()))
			return "La cedula no es valida, debe  ser númerica";

		if (!validacionNombre(conductor.getNombre()))
			return "El nombre no es válido, debe tener 3 caracteres o más";

		if (!validacionApellido(conductor.getApellidos()))
			return "El apellido no es válido, debe tener 3 caracteres o más";

		if (!validacionFechaNacimiento(conductor.getFechaNacimiento()))
			return "Recuerda que tienes que ser mayor de edad";

		if (!validacionFechaContratacion(conductor.getFechaContratacion()))
			return "Recuerda que la fecha de contratación tiene que ser menor a la fecha actual";

		conductorDAO.save(em, conductor);
		return "Se creó exitosamente el conductor";
	}

	

	public String updateConductor(Tmio1Conductore conductor) {
		conductorDAO = new Tmio1_Conductores_DAO();
		if (conductor == null)
			return "El conductor es nulo";

		if (findByCedula(conductor.getCedula()) == null)
			return "El conductor no existe";

		if (!validacionCedula(conductor.getCedula()))
			return "La cedula no es valida, debe  ser númerica";

		if (!validacionNombre(conductor.getNombre()))
			return "El nombre no es válido, debe tener 3 caracteres o más";

		if (!validacionApellido(conductor.getApellidos()))
			return "El apellido no es válido, debe tener 3 caracteres o más";

		if (!validacionFechaNacimiento(conductor.getFechaNacimiento()))
			return "Recuerda que tienes que ser mayor de edad";

		if (!validacionFechaContratacion(conductor.getFechaContratacion()))
			return "Recuerda que la fecha de contratación tiene que ser menor a la fecha actual";

		conductorDAO.update(em, conductor);
		return "Se actualizó exitosamente el conductor";
	}

	public String deleteConductor(Tmio1Conductore conductor) {
		conductorDAO = new Tmio1_Conductores_DAO();
		if (conductor == null)
			return "El conductor es nulo";

		if (findByCedula(conductor.getCedula())== null)
			return "El conductor no existe";
		
			conductorDAO.delete(em, findByCedula(conductor.getCedula()));
			return "Se eliminó correctamente el conductor";
	}

	public List<Tmio1Conductore> findByName(String name) {
		conductorDAO = new Tmio1_Conductores_DAO();
		List<Tmio1Conductore> conductores = null;
		if (validacionNombre(name))
			if(conductorDAO.findByName(em, name)!=null)
			conductores = conductorDAO.findByName(em, name);
		return conductores;
	}

	public List<Tmio1Conductore> findByLastname(String lastname) {
		conductorDAO = new Tmio1_Conductores_DAO();
		List<Tmio1Conductore> conductores = null;
		if (validacionApellido(lastname))
			if(conductorDAO.findByLastName(em, lastname)!=null)
			conductores = conductorDAO.findByLastName(em, lastname);
		return conductores;
	}

	public Tmio1Conductore findByCedula(String cedula) {
		conductorDAO = new Tmio1_Conductores_DAO();
		Tmio1Conductore conductor = null;
		if (validacionCedula(cedula))
			if(conductorDAO.findByCedula(em, cedula)!=null)
			conductor = conductorDAO.findByCedula(em, cedula);
		return conductor;
	}

	/**
	 * VALIDACIONES
	 */
	public boolean validacionCedula(String cedula) {
		return cedula.matches("[0-9]+");
	}

	public boolean validacionNombre(String nombre) {
		return !nombre.equals("") && nombre.length() >= 3;
	}

	public boolean validacionApellido(String apellido) {
		return !apellido.equals("") && apellido.length() >= 3;
	}

	public boolean validacionFechaNacimiento(Date fechaNacimiento) {
		Calendar gc1 = new GregorianCalendar();
		gc1.setTime(fechaNacimiento);
		Calendar gc = new GregorianCalendar().getInstance();
		return fechaNacimiento != null && (gc.get(GregorianCalendar.YEAR) - gc1.get(GregorianCalendar.YEAR)) >= 18;
	}

	public boolean validacionFechaContratacion(Date fechaContratacion) {
		Calendar gc1 = new GregorianCalendar();
		gc1.setTime(fechaContratacion);
		Calendar gc = new GregorianCalendar().getInstance();

		return fechaContratacion != null && gc1.compareTo(gc) < 0;
	}

}
