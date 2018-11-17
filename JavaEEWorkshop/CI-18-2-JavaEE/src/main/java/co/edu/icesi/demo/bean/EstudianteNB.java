package co.edu.icesi.demo.bean;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import javax.ejb.EJB;
import javax.enterprise.context.SessionScoped;
import javax.inject.Inject;
import javax.inject.Named;

import co.edu.icesi.demo.logic.IEstudianteLogicRemota;
import co.edu.icesi.demo.model.TAlumno;
import co.edu.icesi.demo.model.TMatxaprobar;
import co.edu.icesi.demo.model.TProgAlumno;
import co.edu.icesi.demo.model.TProgAlumnoPK;
import co.edu.icesi.demo.model.TPrograma;

@Named("estudianteNB")
@SessionScoped
public class EstudianteNB implements Serializable {

	@EJB(lookup="java:global/CI-18-2-JavaEE-prof-full/EstudiantesLogic!co.edu.icesi.demo.logic.IEstudianteLogicRemota")
	private IEstudianteLogicRemota estudianteLogic;

	/**
	 * 
	 */
	private static final long serialVersionUID = -1179646551179392211L;

	private String codigo;

	private String apellidos;

	private String nombre;

	private String sexo;

	private String tipo;
	
	private String programa;

	private List<TMatxaprobar> TMatxaprobars;

	private List<TProgAlumno> TProgAlumnos;

	public String getCodigo() {
		return codigo;
	}

	public void setCodigo(String codigo) {
		this.codigo = codigo;
	}

	public String getApellidos() {
		return apellidos;
	}

	public void setApellidos(String apellidos) {
		this.apellidos = apellidos;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public String getSexo() {
		return sexo;
	}

	public void setSexo(String sexo) {
		this.sexo = sexo;
	}

	public String getTipo() {
		return tipo;
	}

	public void setTipo(String tipo) {
		this.tipo = tipo;
	}

	public List<TMatxaprobar> getTMatxaprobars() {
		return TMatxaprobars;
	}

	public void setTMatxaprobars(List<TMatxaprobar> tMatxaprobars) {
		TMatxaprobars = tMatxaprobars;
	}

	public List<TProgAlumno> getTProgAlumnos() {
		return TProgAlumnos;
	}

	public void setTProgAlumnos(List<TProgAlumno> tProgAlumnos) {
		TProgAlumnos = tProgAlumnos;
	}
	
	public List<String> listaProgramas()
	{
		return estudianteLogic.listaProgramas();
	}

	public String agregarEstudiante() {
		TAlumno talumno = new TAlumno();

		talumno.setApellidos(apellidos);
		talumno.setCodigo(codigo);
		talumno.setNombre(nombre);
		talumno.setSexo(sexo);
		talumno.setTipo(tipo);

		TPrograma pActual=estudianteLogic.consultarPrograma(programa.split("-")[0]);
		
		TProgAlumno tProgAl=new TProgAlumno();
		tProgAl.setTAlumno(talumno);
		tProgAl.setTPrograma(pActual);
		tProgAl.setCohorte("182");
		tProgAl.setSemestre("2");
		TProgAlumnoPK tpaPK=new TProgAlumnoPK();
		tpaPK.setAlumnoCodigo(talumno.getCodigo());
		tpaPK.setPeriodoAcad("182");
		tpaPK.setPrincipal("S");
		tpaPK.setProgramaCodigo(pActual.getCodigo());
		tProgAl.setId(tpaPK);
		
		talumno.setTMatxaprobars(TMatxaprobars);
		TProgAlumnos =new ArrayList<TProgAlumno>();
		
		TProgAlumnos.add(tProgAl);
		talumno.setTProgAlumnos(TProgAlumnos);
		
		
		
		//TODO obtener el programa del alumno y crear agregarlo a la colección
		// de programas para agregarlo al estudiante
	
		estudianteLogic.createAlumno(talumno);
		//TODO validar si la creación fue exitosa para retornar failure o success.
		return "success";
	}

	public String getPrograma() {
		return programa;
	}

	public void setPrograma(String programa) {
		this.programa = programa;
	}

}
