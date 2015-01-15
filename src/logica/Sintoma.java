package logica;

public class Sintoma {
	private String estado;
	private String duracion;
	private String descripcion;
	private String idRegistro;
	private String idEspecialidad;
	public Sintoma(String idEsp, String idReg, String est, String dur, String desc){
		idEspecialidad = idEsp;
		idRegistro = idReg;
		estado = est;
		duracion = dur;
		descripcion = desc;
	}

	public void setEstado(String e){estado = e;}
	public String getEstado(){return estado;}
	public void setDuracion(String d){duracion = d;}
	public String getDuracion(){return duracion;}
	public void setDescripcion(String d){descripcion = d;}
	public String getDescripcion(){return descripcion;}
	public void setEspecialidad(String idEsp){idEspecialidad = idEsp;}
	public String getEspecialidad(){return idEspecialidad;}
	public void setRegistro(String idReg){idRegistro = idReg;}
	public String getRegistro(){return idRegistro;}
}
