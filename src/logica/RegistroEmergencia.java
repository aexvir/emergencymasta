package logica;

import java.util.ArrayList;

public class RegistroEmergencia {
	private float latitud;
	private float longitud;
	private String fecha;
	private String hora;
	private String idRegistro;
	private String dniPaciente;
	private String idAmbulancia;
	private String idHospital;
	private ArrayList<Sintoma> sintomas; 

	public RegistroEmergencia(){}

	public RegistroEmergencia(String i,float la, float ln,String dni, String f, String h, String idAmb, String idH, ArrayList<Sintoma> lsin){
		latitud = la;
		longitud = ln;
		fecha = f;
		hora = h;
		idRegistro = i;
		dniPaciente = dni;
		idAmbulancia = idAmb;
		idHospital = idH;
		sintomas = lsin;
	}

	public void setLatitud(float l){latitud = l;}
	public float getLatitud(){return latitud;}
	public void setLongitud(float l){longitud = l;}
	public float getLongitud(){return longitud;}
	public void setFecha(String f){fecha = f;}
	public String getFecha(){return fecha;}
	public void setHora(String h){hora = h;}
	public String getHora(){return hora;}
	public void setIdRegistro(String i){idRegistro = i;}
	public String getIdRegistro(){return idRegistro;}
	public void setDniPaciente(String dni){dniPaciente = dni;}
	public String getDniPaciente(){return dniPaciente;}
	public void setIdAmbulancia(String id){idAmbulancia = id;}
	public String getIdAmbulancia(){return idAmbulancia;}
	public void setIdHospital(String h){idHospital = h;}
	public String getIdHospital(){return idHospital;}
	public void setSintomas(ArrayList<Sintoma> s){sintomas = s;}
	public void addSintoma(Sintoma s){ this.sintomas.add(s);}
	public ArrayList<Sintoma> getSintomas(){return sintomas;}
	
}
