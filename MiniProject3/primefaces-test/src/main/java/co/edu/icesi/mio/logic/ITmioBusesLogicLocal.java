package co.edu.icesi.mio.logic;

import java.math.BigDecimal;
import java.util.List;

import co.edu.icesi.mio.model.Tmio1Bus;

public interface ITmioBusesLogicLocal {
	public boolean add(Tmio1Bus bus);

	public void update(Tmio1Bus bus);

	public void delete(Tmio1Bus bus);

	public List<Tmio1Bus> findByModelo(BigDecimal m);

	public List<Tmio1Bus> findByTipo(String t);

	public List<Tmio1Bus> findByCapacidad(BigDecimal c);

	public Tmio1Bus findById(int id);
}
