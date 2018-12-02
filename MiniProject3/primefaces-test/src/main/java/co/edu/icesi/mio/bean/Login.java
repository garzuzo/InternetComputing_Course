package co.edu.icesi.mio.bean;

import java.io.Serializable;

import javax.enterprise.context.SessionScoped;
import javax.faces.bean.ManagedBean;
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

			return "aceptado";
		}
		return "failed";

	}

	public String inicio() {
		return "salida";
	}

	public String mainview() {
		return "mainview";
	}

	public String cBus() {
		return "cBus";
	}

	public String gBus() {
		return "gBus";
	}

	public String cRuta() {
		return "cRuta";
	}

	public String gRuta() {
		return "gRuta";
	}

	public String cServicio() {
		return "cServicio";
	}

	public String gServicio() {
		return "gServicio";
	}

	public String cConductor() {
		return "cConductor";
	}

	public String gConductor() {
		return "gConductor";
	}
}
