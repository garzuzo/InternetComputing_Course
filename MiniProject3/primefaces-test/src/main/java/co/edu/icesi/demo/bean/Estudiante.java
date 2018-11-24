package co.edu.icesi.demo.bean;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.enterprise.context.SessionScoped;
import javax.inject.Named;

import org.primefaces.component.commandbutton.CommandButton;
import org.primefaces.component.inputtext.InputText;

import co.edu.icesi.demo.logic.IEstudianteLogicRemota;
import co.edu.icesi.demo.model.TAlumno;
import co.edu.icesi.demo.model.TMatxaprobar;
import co.edu.icesi.demo.model.TProgAlumno;
import co.edu.icesi.demo.model.TProgAlumnoPK;
import co.edu.icesi.demo.model.TPrograma;

@Named
@SessionScoped
public class Estudiante implements Serializable {

	@EJB(lookup = "java:global/primefaces-test-1.0-SNAPSHOT/EstudiantesLogic!co.edu.icesi.demo.logic.IEstudianteLogicRemota")
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

	private List<TMatxaprobar> tMatxaprobars;

	private List<TProgAlumno> tProgAlumnos;
	
	
//	private CommandButton btnAdd;
//	private CommandButton btnQuery;
//	private CommandButton btnEdit;
//	private CommandButton btnDelete;
//	private CommandButton btnCancel;
//	private CommandButton btnClean;


	public Estudiante() {
		super();
		// TODO Auto-generated constructor stub
	}

	@PostConstruct
	public void init() {
		cleanValuesButtons();
	}


	public String cleanValuesButtons() {

		codigo = "";
		nombre = "";
		apellidos = "";
		tipo = "";
		sexo = "";
		TPrograma prog = estudianteLogic.consultarPrograma("09");
		programa = prog.getCodigo() + "-" + prog.getDescripcion();
		
		return "";
	}
	
	public String setQueryButtons()
	{
		
		return "";
	}

	public IEstudianteLogicRemota getEstudianteLogic() {
		return estudianteLogic;
	}

	public void setEstudianteLogic(IEstudianteLogicRemota estudianteLogic) {
		this.estudianteLogic = estudianteLogic;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}

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
		return tMatxaprobars;
	}

	public void setTMatxaprobars(List<TMatxaprobar> tMatxaprobars) {
		this.tMatxaprobars = tMatxaprobars;
	}

	public List<TProgAlumno> getTProgAlumnos() {
		return tProgAlumnos;
	}

	public void setTProgAlumnos(List<TProgAlumno> tProgAlumnos) {
		this.tProgAlumnos = tProgAlumnos;
	}

	public List<String> listaProgramas() {
		return estudianteLogic.listaProgramas();
	}

	public String addEstudiante() {
		TAlumno talumno = new TAlumno();

		talumno.setApellidos(apellidos);
		talumno.setCodigo(codigo);
		talumno.setNombre(nombre);
		talumno.setSexo(sexo);
		talumno.setTipo(tipo);
		talumno.setTMatxaprobars(tMatxaprobars);

		TProgAlumno tprogalumno = new TProgAlumno();

		String progcod = programa.substring(0, 2);

		TPrograma tprograma = estudianteLogic.consultarPrograma(progcod);

		tProgAlumnos = new ArrayList<TProgAlumno>();
		tProgAlumnos.add(tprogalumno);

		talumno.setTProgAlumnos(tProgAlumnos);

		TProgAlumnoPK tprogalumnopk = new TProgAlumnoPK();

		tprogalumnopk.setAlumnoCodigo(codigo);
		tprogalumnopk.setPeriodoAcad("182");
		tprogalumnopk.setPrincipal("S");
		tprogalumnopk.setProgramaCodigo(progcod);

		tprogalumno.setId(tprogalumnopk);
		tprogalumno.setTAlumno(talumno);
		tprogalumno.setTPrograma(tprograma);
		tprogalumno.setCohorte("182");
		tprogalumno.setSemestre("1");

		estudianteLogic.createAlumno(talumno);

		// TODO validar si la creación fue exitosa para retornar failure o success.
		cleanValuesButtons();
		return "success";
	}

	public String editEstudiante() {

		TAlumno talumno = estudianteLogic.consultarEstudiante(codigo);

		talumno.setApellidos(apellidos);
		talumno.setCodigo(codigo);
		talumno.setNombre(nombre);
		talumno.setSexo(sexo);
		talumno.setTipo(tipo);
		talumno.setTMatxaprobars(tMatxaprobars);
		talumno.setTProgAlumnos(tProgAlumnos);

		// TODO obtener el programa del alumno y crear agregarlo a la colección
		// de programas para agregarlo al estudiante

		estudianteLogic.editarAlumno(talumno);
		// TODO validar si la creación fue exitosa para retornar failure o success.
		cleanValuesButtons();
		return "success";
	}

	public String queryEstudiante() {

		TAlumno talumno = estudianteLogic.consultarEstudiante(codigo);

		apellidos = talumno.getApellidos();
		nombre = talumno.getNombre();
		sexo = talumno.getSexo();
		tipo = talumno.getTipo();

		List<TProgAlumno> tProgAlumnos = talumno.getTProgAlumnos();
		if (tProgAlumnos.size() > 0) {
			TProgAlumno tProgAlumno = tProgAlumnos.get(0);
			programa = tProgAlumno.getTPrograma().getCodigo() + "-" + tProgAlumno.getTPrograma().getDescripcion();
		}
		
		setQueryButtons();
		return "success";
	}

	public String deleteEstudiante() {

		TAlumno talumno = estudianteLogic.consultarEstudiante(codigo);

		estudianteLogic.borrarAlumno(talumno);
		cleanValuesButtons();
		return "success";
	}

	public String cancelEstudiante() {
		cleanValuesButtons();		
		return "success";
	}

	public String getPrograma() {
		return programa;
	}

	public void setPrograma(String programa) {
		this.programa = programa;
	}
	
}
