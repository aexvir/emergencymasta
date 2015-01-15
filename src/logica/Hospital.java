package logica;

import java.util.ArrayList;

public class Hospital {
	private String nombre;
	private String direccion;
	private float latitud;
	private float longitud;
	private ArrayList <Especialidad> especialidades;
	private ArrayList <BHospital> bhospitales;
	private ArrayList <RegistroEmergencia> registros;

	public Hospital(){}

	public Hospital(String n, String d, float la, float ln){
		nombre = n;
		direccion = d;
		latitud = la;
		longitud = ln;
	}

	public void setNombre(String n){nombre = n;}
	public String getNombre(){return nombre;}
	public void setDireccion(String d){direccion = d;}
	public String getDireccion(){return direccion;}
	public void setLatitud(float l){latitud = l;}
	public float getLatitud(){return latitud;}
	public void setLongitud(float l){longitud = l;}
	public float getLongitud(){return longitud;}
	public void setEspecialidades(ArrayList<Especialidad> e){especialidades = e;}
	public ArrayList<Especialidad> getEspecialidades(){return especialidades;}
	public void setBHospitales(ArrayList<BHospital> b){bhospitales = b;}
	public ArrayList<BHospital> getBHospitales(){return bhospitales;}
	public void setRegistroEmergencias(ArrayList<RegistroEmergencia> r){registros = r;}
	public ArrayList<RegistroEmergencia> getRegistroEmergencias(){return registros;}
}
