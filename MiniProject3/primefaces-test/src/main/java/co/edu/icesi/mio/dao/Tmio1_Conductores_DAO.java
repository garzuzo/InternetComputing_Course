package co.edu.icesi.mio.dao;

import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.List;

import javax.persistence.EntityManager;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Repository;

import co.edu.icesi.mio.model.Tmio1Conductore;


public class Tmio1_Conductores_DAO implements ITmio1_Conductores_DAO {

	// adicionales
	@Override
	public List<Tmio1Conductore> findByName(EntityManager em, String name) {
		String jpql = "Select c from Tmio1Conductore c where c.nombre= '" + name + "'";
		return em.createQuery(jpql).getResultList();
	}

	@Override
	public List<Tmio1Conductore> findByLastName(EntityManager em, String lastname) {
		String jpql = "Select c from Tmio1Conductore c where c.apellidos= '" + lastname + "'";
		return em.createQuery(jpql).getResultList();
	}

	@Override
	public Tmio1Conductore findByCedula(EntityManager em, String cedula) {
		return em.find(Tmio1Conductore.class, cedula);
	}

	@Override
	public List<Tmio1Conductore> findAllOrderedByBirthDate(EntityManager em) {
		String jpql = "Select c from Tmio1Conductore c ORDER BY c.fechaNacimiento";
		return em.createQuery(jpql).getResultList();
	}

	// REVISAR
	@Override
	public List<Tmio1Conductore> driversWithServicesInMoreThanOneBus(EntityManager em) {

		String jpql = "Select c from Tmio1Conductore c, Tmio1Servicio s1, Tmio1Servicio s2, Tmio1Bus b1, Tmio1Bus b2"
				+ " WHERE c.cedula= s1.tmio1Conductore.cedula" + " AND c.cedula= s2.tmio1Conductore.cedula"
				+ " AND s1.tmio1Bus.id = b1.id" + " AND s2.tmio1Bus.id = b2.id" + " AND s1.id != s2.id"
				+ " AND b1.id != b2.id" + " GROUP BY c.cedula" + " HAVING count(c.cedula) > 1";
		return em.createQuery(jpql).getResultList();
	}

	@Override
	public List<Tmio1Conductore> driversThatAreFree(EntityManager em) {
		// fecha Actual
		Calendar fechaActual = GregorianCalendar.getInstance();
		String fa = "\'" + fechaActual.get(Calendar.YEAR) + "-" + fechaActual.get(Calendar.MONTH) + "-"
				+ fechaActual.get(Calendar.DAY_OF_MONTH) + "\'";
		String jpql = "SELECT C FROM Tmio1Conductore C WHERE C NOT IN (SELECT C1 FROM Tmio1Servicio S, Tmio1Conductore C1 WHERE S.tmio1Conductore.cedula=C1.cedula AND S.id.fechaFin>="
				+ fa + ")";
		return em.createQuery(jpql).getResultList();
	}

	// normales
	@Override
	public void save(EntityManager em, Tmio1Conductore conductor) {
		em.persist(conductor);
	}

	@Override
	public void update(EntityManager em, Tmio1Conductore conductor) {
		em.merge(conductor);
	}

	@Override
	public void delete(EntityManager em, Tmio1Conductore conductor) {
		em.remove(conductor);
	}

	@Override
	public List<Tmio1Conductore> findAll(EntityManager em) {
		String jpql = "Select c from Tmio1Conductore c";
		return em.createQuery(jpql).getResultList();
	}

}
