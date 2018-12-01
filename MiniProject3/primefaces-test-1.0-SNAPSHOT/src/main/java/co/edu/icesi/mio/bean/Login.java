package co.edu.icesi.mio.bean;

import java.io.Serializable;

import javax.enterprise.context.SessionScoped;
import javax.faces.context.FacesContext;
import javax.faces.view.ViewScoped;
import javax.inject.Named;
import javax.servlet.http.HttpSession;

@Named
@SessionScoped
public class Login implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 3738865488453069657L;

	private String user;

	public String getUser() {
		return user;
	}

	public void setUser(String user) {
		this.user = user;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	private String password;

	private String nav;
	public String getNav() {
		return password;
	}

	public void setNav(String nav) {
		this.nav = nav;
	}
	public String validar() {

		String correctUser = "garzuzo";
		String correctPass = "123";
		if (user.equals(correctUser) && password.equals(correctPass)) {
//			HttpSession session = (HttpSession) FacesContext.getCurrentInstance().getExternalContext()
//					.getSession(false);
//			
//			
//			session.getServletContext().setAttribute("username", user);
//			session.setAttribute("username", user);
		
			return "correct";
		}
		return "failed";

	}
	
	public String navigation() {
		
		String caso=nav;
		
		if(caso.equals("cBus")) {
			return "cBus";
		}else if(caso.equalsIgnoreCase("gBus")) {
			return "gBus";
		}else if(caso.equalsIgnoreCase("cRuta")) {
			return "cRuta";
		}else if(caso.equalsIgnoreCase("gRuta")) {
			return "gRuta";
		}else if(caso.equalsIgnoreCase("cServicio")) {
			return "cServicio";
		}else if(caso.equalsIgnoreCase("gServicio")) {
			return "gServicio";
		}else if(caso.equalsIgnoreCase("cConductor")) {
			return "cConductor";
		}else if(caso.equalsIgnoreCase("gConductor")) {
			return "gConductor";
		}else if(caso.equalsIgnoreCase("mainview")) {
			return "mainview";
		}else {
			return "login";
		}
		
		
	}
}
