package com.sample.model;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.enterprise.context.SessionScoped;
import javax.inject.Named;

/**
 * Created by kasun on 5/24/17.
 */
@Named("LiquorSelect")
@SessionScoped
public class LiquorSelect implements Serializable {

	private String selectedOpt;
	
	private ArrayList result;

	public String getSelectedOpt() {
		return selectedOpt;
	}

	public void setSelectedOpt(String selectedOpt) {
		this.selectedOpt = selectedOpt;
	}

	public ArrayList getResult() {
		return result;
	}

	public void setResult(ArrayList result) {
		this.result = result;
	}

	public List getAvailableBrands(LiquorType type) {

		List brands = new ArrayList();

		if (type.equals(LiquorType.WINE)) {
			brands.add("Adrianna Vineyard");
			brands.add(("J. P. Chenet"));

		} else if (type.equals(LiquorType.WHISKY)) {
			brands.add("Glenfiddich");
			brands.add("Johnnie Walker");

		} else if (type.equals(LiquorType.BEER)) {
			brands.add("Corona");

		} else {
			brands.add("No Brand Available");
		}
		return brands;
	}

	public void submit() {
		
		List brands = new ArrayList();

		if (selectedOpt.equals(LiquorType.WINE)) {
			brands.add("Adrianna Vineyard");
			brands.add(("J. P. Chenet"));

		} else if (selectedOpt.equals(LiquorType.WHISKY)) {
			brands.add("Glenfiddich");
			brands.add("Johnnie Walker");

		} else if (selectedOpt.equals(LiquorType.BEER)) {
			brands.add("Corona");

		} else {
			brands.add("No Brand Available");
		}
		//return brands;

	}
}