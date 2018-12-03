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

	public boolean add(Tmio1Bus bus) {
		buses = new Tmio1_Buses_DAO();
		if (bus != null && placaMESix(bus) && marcaMEThree(bus) && modeloNumbersEFour(bus.getModelo())
				&& tipoPAT(bus.getTipo()) && capacidadMZero(bus.getCapacidad())) {
			buses.save(em, bus);
			return true;
		} else
			return false;
	}

	public void update(Tmio1Bus bus) {
		buses = new Tmio1_Buses_DAO();
		if (bus != null && placaMESix(bus) && marcaMEThree(bus) && modeloNumbersEFour(bus.getModelo())
				&& tipoPAT(bus.getTipo()) && capacidadMZero(bus.getCapacidad()))
			buses.update(em, bus);
	}

	public void delete(Tmio1Bus bus) {
		buses = new Tmio1_Buses_DAO();
		if (bus != null && buses.findById(em, bus.getId()) != null)
			buses.delete(em, findById(bus.getId()));
	}

	public List<Tmio1Bus> findByModelo(BigDecimal m) {
		buses = new Tmio1_Buses_DAO();
		if (modeloNumbersEFour(m))
			return buses.findByModel(em, m);
		else
			return null;
	}

	public List<Tmio1Bus> findByTipo(String t) {
		buses = new Tmio1_Buses_DAO();
		if (tipoPAT(t))
			return buses.findByType(em, t);
		else
			return null;
	}

	public List<Tmio1Bus> findByCapacidad(BigDecimal c) {
		buses = new Tmio1_Buses_DAO();
		if (capacidadMZero(c))
			return buses.findByCapacity(em, c);
		else
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
		return bus.matches("P|A|T");
	}

	// la capacidad sea numérica mayor a cero
	public boolean capacidadMZero(BigDecimal bus) {
		return bus.compareTo(BigDecimal.ZERO) > 0;
	}

}
