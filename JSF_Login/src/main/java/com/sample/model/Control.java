package com.sample.model;

import java.io.Serializable;

import javax.enterprise.context.SessionScoped;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.inject.Named;
import javax.servlet.http.HttpSession;

@Named("Control")
@SessionScoped
public class Control implements Serializable {

	
	private String user;
	private String pwd;
	private String msg;
	
	//validate and login
	public String validateUsernamePassword() {
	boolean valid = user.equals(pwd);
	if (valid && !user.equals("")) {
	HttpSession session = (HttpSession) FacesContext.getCurrentInstance()
	.getExternalContext().getSession(false);
	session.setAttribute("username", user);
	return "admin";
	} else {
	FacesContext.getCurrentInstance().addMessage(null,new FacesMessage(FacesMessage.SEVERITY_WARN,"Incorrect information","Please enter correct username/Password"));
	user = null;
	return "login";
	}
	}
	//logout
	public String logout() {
	HttpSession session = (HttpSession) FacesContext.getCurrentInstance()
	.getExternalContext().getSession(false);
	session.invalidate();
	user=null;
	return "logout";
	}
	
	public String getUser() {
		return user;
	}
	public void setUser(String user) {
		this.user = user;
	}
	public String getPwd() {
		return pwd;
	}
	public void setPwd(String pwd) {
		this.pwd = pwd;
	}
	public String getMsg() {
		return msg;
	}
	public void setMsg(String msg) {
		this.msg = msg;
	}
	
	
	
	
}
