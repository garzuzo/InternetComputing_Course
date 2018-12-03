package co.edu.icesi.mio.logic;

import java.math.BigDecimal;
import java.util.List;

import co.edu.icesi.mio.model.Tmio1Ruta;

public interface ITmioRutasLogicRemota {
	public String add(Tmio1Ruta ruta);

	public String update(Tmio1Ruta ruta);

	public String delete(Tmio1Ruta ruta);

	public List<Tmio1Ruta> findByRangoDias(BigDecimal di, BigDecimal df);

	public Tmio1Ruta findById(int id);
}
