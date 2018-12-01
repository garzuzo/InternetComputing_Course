package co.edu.icesi.mio.logic;

import java.util.List;

import co.edu.icesi.mio.model.Tmio1Conductore;

public interface ITmioConductoresLogicLocal {
	public void createConductor(Tmio1Conductore conductor);
	public void updateConductor(Tmio1Conductore conductor);
	public void deleteConductor(Tmio1Conductore conductor);
	
	public List<Tmio1Conductore> findByName(String name);
	public List<Tmio1Conductore> findByLastname(String lastname);
	public Tmio1Conductore findByCedula(String cedula);
}
