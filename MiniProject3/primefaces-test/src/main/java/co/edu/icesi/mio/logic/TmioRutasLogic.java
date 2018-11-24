package co.edu.icesi.mio.logic;

import java.math.BigDecimal;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import co.edu.icesi.mio.dao.ITmio1_Rutas_DAO;
import co.edu.icesi.mio.model.Tmio1Ruta;

@Service
public class TmioRutasLogic implements ITmioRutasLogicLocal,ITmioRutasLogicRemota {

	@Autowired
	private ITmio1_Rutas_DAO rutas;

	@PersistenceContext
	private EntityManager em;

	@Transactional
	public boolean add(Tmio1Ruta ruta) {

		if (ruta != null && validacionNumeroRuta(ruta) && validacionDiaInicio(ruta) && validacionDiaFin(ruta)
				&& validacionDiaInicioMenorFin(ruta) && validacionHoraFin(ruta) && validacionHoraInicio(ruta)
				&& validacionHoraInicioMenorFin(ruta) && validacionActiva(ruta)) {
			rutas.save(em, ruta);
			return true;
		} else
			return false;
	}

	@Transactional
	public void update(Tmio1Ruta ruta) {
		if (ruta != null && rutas.findById(em, ruta.getId()) != null && validacionNumeroRuta(ruta)
				&& validacionDiaInicio(ruta) && validacionDiaFin(ruta) && validacionDiaInicioMenorFin(ruta)
				&& validacionHoraFin(ruta) && validacionHoraInicio(ruta) && validacionHoraInicioMenorFin(ruta)
				&& validacionActiva(ruta))
			rutas.update(em, ruta);
	}

	@Transactional
	public void delete(Tmio1Ruta ruta) {
		if (ruta != null && findById(ruta.getId()) != null)
			rutas.delete(em, findById(ruta.getId()));
	}

	@Transactional
	public List<Tmio1Ruta> findByRangoDias(BigDecimal di, BigDecimal df) {
		// TODO Auto-generated method stub
		if (validacionDiaInicio(di) && validacionDiaFin(df) && validacionDiaInicioMenorFin(di, df))
			return rutas.findByRangeOfDays(em, di, df);
		else
			return null;
	}

	@Transactional
	public Tmio1Ruta findById(int id) {
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
