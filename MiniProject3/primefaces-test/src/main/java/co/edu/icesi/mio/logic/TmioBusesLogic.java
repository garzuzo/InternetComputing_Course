package co.edu.icesi.mio.logic;

import java.math.BigDecimal;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import co.edu.icesi.mio.dao.ITmio1_Buses_DAO;
import co.edu.icesi.mio.model.Tmio1Bus;

@Service
public class TmioBusesLogic implements ITmioBusesLogic {

	@Autowired
	private ITmio1_Buses_DAO buses;

	@PersistenceContext
	private EntityManager em;

	@Transactional
	public boolean add(Tmio1Bus bus) {

		if (bus != null && placaMESix(bus) && marcaMEThree(bus) && modeloNumbersEFour(bus.getModelo())
				&& tipoPAT(bus.getTipo()) && capacidadMZero(bus.getCapacidad())) {
			buses.save(em, bus);
			return true;
		}else
			return false;
	}

	@Transactional
	public void update(Tmio1Bus bus) {
		if (bus != null && buses.findById(em, bus.getId()) != null && placaMESix(bus) && marcaMEThree(bus)
				&& modeloNumbersEFour(bus.getModelo()) && tipoPAT(bus.getTipo()) && capacidadMZero(bus.getCapacidad()))
			buses.update(em, bus);
	}

	@Transactional
	public void delete(Tmio1Bus bus) {

		if (bus != null && buses.findById(em, bus.getId()) != null)
			buses.delete(em, findById(bus.getId()));
	}

	@Transactional
	public List<Tmio1Bus> findByModelo(BigDecimal m) {

		if (modeloNumbersEFour(m))
			return buses.findByModel(em, m);
		else
			return null;
	}

	@Transactional
	public List<Tmio1Bus> findByTipo(String t) {
		if (tipoPAT(t))
			return buses.findByType(em, t);
		else
			return null;
	}

	@Transactional
	public List<Tmio1Bus> findByCapacidad(BigDecimal c) {
		if (capacidadMZero(c))
			return buses.findByCapacity(em, c);
		else
			return null;
	}

	@Transactional
	public Tmio1Bus findById(int id) {
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
