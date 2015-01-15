package logica;

public class Paciente {
	private String dni;
	private String nombre;
	private String apellidos;
	private String direccion;
	private int edad;
	private String telefono;
	private char sexo;

	public Paciente(){
		dni = null;
		nombre = null;
		apellidos = null;
		sexo = ' ';
		edad = 0;
		telefono = null;
		direccion = null;
	}

	public Paciente(String d, String n, String a, char s, int e, String t, String dir){
		dni = d;
		nombre = n;
		apellidos = a;
		sexo = s;
		edad = e;
		telefono = t;
		direccion = dir;
	}

	public void setDni(String d){dni = d;}
	public String getDni(){return dni;}
	public void setNombre(String n){nombre = n;}
	public String getNombre(){return nombre;}
	public void setApellidos(String a){apellidos = a;}
	public String getApellidos(){return apellidos;}
	public void setSexo(char s){sexo = s;}
	public char getSexo(){return sexo;}
	public void setEdad(int e){edad = e;}
	public int getEdad(){return edad;}
	public void setTelefono(String t){telefono = t;}
	public String getTelefono(){return telefono;}
	public void setDireccion(String d){direccion = d;}
	public String getDireccion(){return direccion;}
}
