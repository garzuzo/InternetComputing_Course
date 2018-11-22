package co.edu.icesi.demo.model;

import java.io.Serializable;
import javax.persistence.*;

import java.util.List;


/**
 * The persistent class for the t_alumnos database table.
 * 
 */
@Entity
@Table(name="t_alumnos")
@NamedQuery(name="TAlumno.findAll", query="SELECT t FROM TAlumno t")
public class TAlumno implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	private String codigo;

	private String apellidos;

	private String nombre;

	private String sexo;

	private String tipo;

	//bi-directional many-to-one association to TMatxaprobar
	@OneToMany(mappedBy="TAlumno")
	private List<TMatxaprobar> TMatxaprobars;

	//bi-directional many-to-one association to TProgAlumno
	@OneToMany(fetch=FetchType.EAGER,mappedBy="TAlumno")
	private List<TProgAlumno> TProgAlumnos;

	public TAlumno() {
	}
	

	public TAlumno(String nombre, String apellidos) {
		this.nombre = nombre;
		this.apellidos = apellidos;
	}

	public String getCodigo() {
		return this.codigo;
	}

	public void setCodigo(String string) {
		this.codigo = string;
	}

	public String getApellidos() {
		return this.apellidos;
	}

	public void setApellidos(String apellidos) {
		this.apellidos = apellidos;
	}

	public String getNombre() {
		return this.nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public String getSexo() {
		return this.sexo;
	}

	public void setSexo(String sexo) {
		this.sexo = sexo;
	}

	public String getTipo() {
		return this.tipo;
	}

	public void setTipo(String tipo) {
		this.tipo = tipo;
	}

	public List<TMatxaprobar> getTMatxaprobars() {
		return this.TMatxaprobars;
	}

	public void setTMatxaprobars(List<TMatxaprobar> TMatxaprobars) {
		this.TMatxaprobars = TMatxaprobars;
	}

	public TMatxaprobar addTMatxaprobar(TMatxaprobar TMatxaprobar) {
		getTMatxaprobars().add(TMatxaprobar);
		TMatxaprobar.setTAlumno(this);

		return TMatxaprobar;
	}

	public TMatxaprobar removeTMatxaprobar(TMatxaprobar TMatxaprobar) {
		getTMatxaprobars().remove(TMatxaprobar);
		TMatxaprobar.setTAlumno(null);

		return TMatxaprobar;
	}

	public List<TProgAlumno> getTProgAlumnos() {
		return this.TProgAlumnos;
	}

	public void setTProgAlumnos(List<TProgAlumno> TProgAlumnos) {
		this.TProgAlumnos = TProgAlumnos;
	}

	public TProgAlumno addTProgAlumno(TProgAlumno TProgAlumno) {
		getTProgAlumnos().add(TProgAlumno);
		TProgAlumno.setTAlumno(this);

		return TProgAlumno;
	}

	public TProgAlumno removeTProgAlumno(TProgAlumno TProgAlumno) {
		getTProgAlumnos().remove(TProgAlumno);
		TProgAlumno.setTAlumno(null);

		return TProgAlumno;
	}
	
	public String toString()
	{
		return "cod: "+ codigo + " name: " + nombre +" " + apellidos;
	}

}