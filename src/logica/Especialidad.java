package logica;

import java.util.ArrayList;

public class Especialidad {
	private String nombre;
	private ArrayList<Hospital> hospitales;

	public Especialidad(){
		nombre = null;
		hospitales = null;
	}

	public Especialidad(String n){
		nombre = n;
		hospitales = null;
	}

	public void setNombre(String n){nombre = n;}
	public String getNombre(){return nombre;}
	public void setHospitales(ArrayList<Hospital> h){hospitales = h;}
	public ArrayList<Hospital> getHospitales(){return hospitales;}
}
