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

import co.edu.icesi.mio.dao.ITmio1_Rutas_DAO;
import co.edu.icesi.mio.dao.Tmio1_Rutas_DAO;
import co.edu.icesi.mio.model.Tmio1Ruta;

@Stateless(name = "TmioRutasLogic", mappedName = "TmioRutasLogic")
@TransactionAttribute(TransactionAttributeType.REQUIRED)
@TransactionManagement(TransactionManagementType.CONTAINER)
@Local(ITmioRutasLogicLocal.class)
@Remote(ITmioRutasLogicRemota.class)
public class TmioRutasLogic implements ITmioRutasLogicLocal, ITmioRutasLogicRemota {

	private ITmio1_Rutas_DAO rutas;

	@PersistenceContext
	private EntityManager em;

	public String add(Tmio1Ruta ruta) {
		rutas = new Tmio1_Rutas_DAO();

		if (ruta == null)
			return "La ruta es nula";
		if (!validacionNumeroRuta(ruta))
			return "El número de la ruta no es de 3 caracteres";
		if (!validacionDiaInicio(ruta))
			return "El día inicio no es numérico y su valor no está entre 1 y 7";
		if (!validacionDiaFin(ruta))
			return "El día fin no es numérico y su valor no está entre 1 y 7";
		if (!validacionDiaInicioMenorFin(ruta))
			return "El día inicio no es menor al día fin";
		if (!validacionHoraInicio(ruta))
			return "La hora inicio no es numérico y su valor no está entre 1 y 1440";
		if (!validacionHoraFin(ruta))
			return "La hora fin no es numérico y su valor no está entre 1 y 1440";
		if (!validacionHoraInicioMenorFin(ruta))
			return "La hora inicio no es menor a la hora fin";
		if (!validacionActiva(ruta))
			return "Activa no es S o N";
	rutas.save(em, ruta);
		return "Se agregó correctamente la ruta";

	}

	public String update(Tmio1Ruta ruta) {
		rutas = new Tmio1_Rutas_DAO();
		if (ruta == null)
			return "La ruta es nula";
		if (rutas.findById(em, ruta.getId()) == null)
			return "No existe la ruta a actualizar";
		if (!validacionNumeroRuta(ruta))
			return "El número de la ruta no es de 3 caracteres";
		if (!validacionDiaInicio(ruta))
			return "El día inicio no es numérico y su valor no está entre 1 y 7";
		if (!validacionDiaFin(ruta))
			return "El día fin no es numérico y su valor no está entre 1 y 7";
		if (!validacionDiaInicioMenorFin(ruta))
			return "El día inicio no es menor al día fin";
		if (!validacionHoraInicio(ruta))
			return "La hora inicio no es numérico y su valor no está entre 1 y 1440";
		if (!validacionHoraFin(ruta))
			return "La hora fin no es numérico y su valor no está entre 1 y 1440";
		if (!validacionHoraInicioMenorFin(ruta))
			return "La hora inicio no es menor a la hora fin";
		if (!validacionActiva(ruta))
			return "Activa no es S o N";

		rutas.update(em, ruta);
		return "Se actualizó la ruta correctamente";
	}

	public String delete(Tmio1Ruta ruta) {
		rutas = new Tmio1_Rutas_DAO();
		if (ruta == null)
			return "La ruta es nula";
		if (findById(ruta.getId()) == null)
			return "No existe la ruta a eliminar";

		rutas.delete(em, findById(ruta.getId()));
		return "Se eliminó correctamente la ruta";
	}

	public List<Tmio1Ruta> findByRangoDias(BigDecimal di, BigDecimal df) {
		rutas = new Tmio1_Rutas_DAO();
		// TODO Auto-generated method stub
		if (validacionDiaInicio(di) && validacionDiaFin(df) && validacionDiaInicioMenorFin(di, df))
			if (rutas.findByRangeOfDays(em, di, df) != null)
				return rutas.findByRangeOfDays(em, di, df);

		return null;
	}

	public Tmio1Ruta findById(int id) {
		rutas = new Tmio1_Rutas_DAO();
		return rutas.findById(em, id);

	}

	/**
	 * VALIDACIONES
	 */

	public boolean validacionNumeroRuta(Tmio1Ruta ruta) {
		return ruta.getNumero().length() == 3;
	}

	public boolean validacionDiaInicio(BigDecimal di) {
		return di.compareTo(BigDecimal.ONE) >= 0 && di.compareTo(new BigDecimal("7")) <= 0;
	}

	public boolean validacionDiaFin(BigDecimal df) {
		return df.compareTo(BigDecimal.ONE) >= 0 && df.compareTo(new BigDecimal("7")) <= 0;
	}

	public boolean validacionDiaInicioMenorFin(BigDecimal di, BigDecimal df) {
		return di.compareTo(df) <= 0;
	}

	public boolean validacionDiaInicio(Tmio1Ruta ruta) {
		return ruta.getDiaInicio().compareTo(BigDecimal.ONE) >= 0
				&& ruta.getDiaInicio().compareTo(new BigDecimal("7")) <= 0;
	}

	public boolean validacionDiaFin(Tmio1Ruta ruta) {
		return ruta.getDiaFin().compareTo(BigDecimal.ONE) >= 0 && ruta.getDiaFin().compareTo(new BigDecimal("7")) <= 0;
	}

	public boolean validacionDiaInicioMenorFin(Tmio1Ruta ruta) {
		return ruta.getDiaInicio().compareTo(ruta.getDiaFin()) <= 0;
	}

	public boolean validacionHoraFin(Tmio1Ruta ruta) {
		return ruta.getHoraFin().compareTo(BigDecimal.ONE) >= 0
				&& ruta.getHoraFin().compareTo(new BigDecimal("1440")) <= 0;
	}

	public boolean validacionHoraInicio(Tmio1Ruta ruta) {
		return ruta.getHoraInicio().compareTo(BigDecimal.ONE) >= 0
				&& ruta.getHoraInicio().compareTo(new BigDecimal("1440")) <= 0;
	}

	public boolean validacionHoraInicioMenorFin(Tmio1Ruta ruta) {
		return ruta.getHoraInicio().compareTo(ruta.getHoraFin()) <= 0;
	}

	public boolean validacionActiva(Tmio1Ruta ruta) {
		return ruta.getActiva().matches("S|N");
	}

}
