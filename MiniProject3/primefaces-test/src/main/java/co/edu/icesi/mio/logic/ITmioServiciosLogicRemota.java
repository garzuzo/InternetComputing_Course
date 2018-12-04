package co.edu.icesi.mio.logic;

import java.util.Calendar;
import java.util.List;

import co.edu.icesi.mio.model.Tmio1Bus;
import co.edu.icesi.mio.model.Tmio1Conductore;
import co.edu.icesi.mio.model.Tmio1Ruta;
import co.edu.icesi.mio.model.Tmio1Servicio;
import co.edu.icesi.mio.model.Tmio1ServicioPK;

public interface ITmioServiciosLogicRemota {
	public String createServicio(Tmio1Servicio servicio);

	public String updateServicio(Tmio1Servicio servicio);

	public String deleteServicio(Tmio1Servicio servicio);

	public List<Tmio1Servicio> findByRangeOfDates(Calendar d1, Calendar d2);

	public Tmio1Servicio getServicio(Tmio1ServicioPK id);

	List<Tmio1Bus> getListaBuses();

	List<Tmio1Ruta> getListaRutas();

	List<Tmio1Conductore> getListaConductores();

	Tmio1Ruta findByIdRuta(int id);

	public Tmio1Conductore findByCedula(String cedula);

	public Tmio1Bus findById(int id);
	public List<Tmio1Servicio> getListaServicios(int idBus, int cedula, int idRuta);
}
