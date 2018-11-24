package co.edu.icesi.mio.bean;

import java.io.Serializable;

import javax.faces.view.ViewScoped;
import javax.inject.Named;

@Named
@ViewScoped
public class Login implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 3738865488453069657L;

	private String user;
	private String password;
	
	public String validar() {
		
		String correctUser="garzuzo";
		String correctPass="123";
		if(user.equals(correctUser)&&password.equals(correctPass))
			return "correct";
		return "failed";
		
	}
}
