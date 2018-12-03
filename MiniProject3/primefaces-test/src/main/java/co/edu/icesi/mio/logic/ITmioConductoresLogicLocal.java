package co.edu.icesi.mio.logic;

import java.util.List;

import co.edu.icesi.mio.model.Tmio1Conductore;

public interface ITmioConductoresLogicLocal {
	public String createConductor(Tmio1Conductore conductor);
	public String updateConductor(Tmio1Conductore conductor);
	public String deleteConductor(Tmio1Conductore conductor);
	
	public List<Tmio1Conductore> findByName(String name);
	public List<Tmio1Conductore> findByLastname(String lastname);
	public Tmio1Conductore findByCedula(String cedula);
}
