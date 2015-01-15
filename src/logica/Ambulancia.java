package logica;

import java.util.ArrayList;

public class Ambulancia {
	private String numRegistro;
	private int tipo;  //Tipo 0 - Pública - Tipo 1 - Privada
	private String equipo;
	private float latitud;
	private float longitud;
	private ArrayList<RegistroEmergencia> registros;
	private int disponibilidad; //1: Disponible - 0: No disponible

	public Ambulancia(String nr, int t, float lt, float ln, String eq, int disp){
		numRegistro = nr; tipo = t; latitud = lt; longitud = ln; equipo = eq; disponibilidad = disp;
	}

	public Ambulancia(){}
	public String getRegistro(){return numRegistro;}
	public int getTipo(){return tipo;}
	public float getLatitud(){return latitud;}
	public float getLongitud(){return longitud;}
	
	public float[] getCoordenadas(){
		float[] dev = {latitud, longitud};
		return dev;
	}
	
	public void setRegistro(String id){this.numRegistro = id;}	
	public void setTipo(int t){this.tipo = t;}	
	public void setLatitud(float l){this.latitud = l;}	
	public void setLongitud(float l){this.longitud = l;}
	
	public void setCoordenadas(float lati, float longi) {
		this.latitud = lati;
		this.longitud = longi;
	}

	public String getEquipo(){return equipo;}
	public void setEquipo(String equipo){this.equipo = equipo;}
	public ArrayList<RegistroEmergencia> getRegistros(){return registros;}
	public void setRegistros(ArrayList<RegistroEmergencia> registros){this.registros = registros;}
	public int getDisponibilidad(){return disponibilidad;}
	public void setDisponibilidad(int disponible) {this.disponibilidad = disponible;}	
}
