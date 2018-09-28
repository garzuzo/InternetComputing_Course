import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

import org.junit.Test;

import co.edu.icesi.mio.dao.TMIO1_BUSES;
import co.edu.icesi.mio.dao.TMIO1_CONDUCTORES;
import co.edu.icesi.mio.dao.TMIO1_RUTAS;
import co.edu.icesi.mio.dao.TMIO1_SERVICIOS;

public class TestTmio1RutasDAO {

	private EntityManagerFactory emfactory = Persistence.createEntityManagerFactory("TransporteMasivo");

	private EntityManager entityManager = emfactory.createEntityManager();

	public void escenario() {

		TMIO1_BUSES bus = new TMIO1_BUSES();
		TMIO1_CONDUCTORES conductor = new TMIO1_CONDUCTORES();
		TMIO1_RUTAS ruta = new TMIO1_RUTAS();
		TMIO1_SERVICIOS servicio = new TMIO1_SERVICIOS();
	}

	@Test
	public void TestRangoDias() {

	}
}
