package co.edu.icesi.mio.logic;

import java.math.BigDecimal;
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

import co.edu.icesi.mio.dao.ITmio1_Buses_DAO;
import co.edu.icesi.mio.dao.Tmio1_Buses_DAO;
import co.edu.icesi.mio.model.Tmio1Bus;

@Stateless(name = "TmioBusesLogic", mappedName = "EstudiantesLogic")
@TransactionAttribute(TransactionAttributeType.REQUIRED)
@TransactionManagement(TransactionManagementType.CONTAINER)
@Local(ITmioBusesLogicLocal.class)
@Remote(ITmioBusesLogicRemota.class)
public class TmioBusesLogic implements ITmioBusesLogicLocal, ITmioBusesLogicRemota {

	private ITmio1_Buses_DAO buses;

	@PersistenceContext
	private EntityManager em;

	public String add(Tmio1Bus bus) {
		buses= new Tmio1_Buses_DAO();
		String m;
		if (bus == null) 
			return "El bus es nulo";
		if(!placaMESix(bus))
			return "La placa no tiene 6 caracteres";
		if(!marcaMEThree(bus))
			return "La marca tiene menos de 3 caracteres";
		if(!modeloNumbersEFour(bus.getModelo()))
			return "El modelo no es númerico de 4 dígitos";
		if(bus.getTipo()==null ||!tipoPAT(bus.getTipo()))
			return "El tipo no es P, A o T";
		if(!capacidadMZero(bus.getCapacidad()))
			return "La capacidad no es mayor a cero";
		
		buses.save(em, bus);
		return "Se agregó correctamente el bus";

	}

	public String update(Tmio1Bus bus) {
		buses= new Tmio1_Buses_DAO();
		if(bus==null)
			return "El bus es nulo";
		if(buses.findById(em, bus.getId())==null)
			return "No existe el bus a actualizar";
		if(!placaMESix(bus))
			return "La placa no tiene 6 caracteres";
		if(!marcaMEThree(bus))
			return "La marca tiene menos de 3 caracteres";
		if(!modeloNumbersEFour(bus.getModelo()))
			return "El modelo no es númerico de 4 dígitos";
		if(bus.getTipo()==null||!tipoPAT(bus.getTipo()))
			return "El tipo no es P, A o T";
		if(!capacidadMZero(bus.getCapacidad()))
			return "La capacidad no es mayor a cero";
		
		buses.update(em, bus);
		return "Se actualizó correctamente el bus";
	}

	public String delete(Tmio1Bus bus) {
		buses= new Tmio1_Buses_DAO();
		if(bus==null)
			return "El bus es nulo";
		if(buses.findById(em, bus.getId())==null)
			return "No se encontró el bus a eliminar";
		
		
		buses.delete(em, findById(bus.getId()));
		return "Se eliminó correctamente el bus";
	}

	public List<Tmio1Bus> findByModelo(BigDecimal m) {
		buses = new Tmio1_Buses_DAO();
		if (modeloNumbersEFour(m))
			if(buses.findByModel(em, m)!=null)
			return buses.findByModel(em, m);
	
			return null;
	}

	public List<Tmio1Bus> findByTipo(String t) {
		buses = new Tmio1_Buses_DAO();
		if (t!=null&&tipoPAT(t))
			if(buses.findByType(em, t)!=null)
			return buses.findByType(em, t);
		
			return null;
	}

	public List<Tmio1Bus> findByCapacidad(BigDecimal c) {
		buses = new Tmio1_Buses_DAO();
		if (capacidadMZero(c))
			if(buses.findByCapacity(em, c)!=null)
			return buses.findByCapacity(em, c);
		
			return null;
	}

	public Tmio1Bus findById(int id) {
		buses = new Tmio1_Buses_DAO();
		
		return buses.findById(em, id);
	}

	/**
	 * VALIDACIONES
	 */

	// la placa esté definida y tenga seis caracteres
	public boolean placaMESix(Tmio1Bus bus) {
		return bus.getPlaca().length() == 6;
	}

	// la marca esté definida y tenga al menos tres caracteres
	public boolean marcaMEThree(Tmio1Bus bus) {
		return bus.getMarca().length() >= 3;
	}

	// el modelo sea numérico de cuatro dígitos
	public boolean modeloNumbersEFour(BigDecimal bus) {
		return bus.compareTo(new BigDecimal("10000")) < 0;
	}

	// el tipo sea P, A, o T
	public boolean tipoPAT(String bus) {
		return bus.matches("[P|A|T]");
	}

	// la capacidad sea numérica mayor a cero
	public boolean capacidadMZero(BigDecimal bus) {
		return bus.compareTo(BigDecimal.ZERO) > 0;
	}

}
