package logica;

import java.util.ArrayList;

import persistencia.DAL;
import excepciones.DAOExcepcion;
import excepciones.LogicaExcepcion;

public class ServicioEmergencia {
	private ArrayList<Paciente> listaPacientes;
	private ArrayList<Hospital> listaHospitales;
	private ArrayList<Especialidad> listaEspecialidades;
	private ArrayList<Ambulancia> listaAmbulancias;
	private ArrayList<RegistroEmergencia> listaRegistros;
	private ArrayList<Sintoma> listaSintomas;
	
	//CONSTRUCTOR
	
	public ServicioEmergencia() throws DAOExcepcion {
		try{
			this.listaPacientes = DAL.dameDAL().cargarPacientes();
			this.listaHospitales = DAL.dameDAL().cargarHospitales();
			this.listaEspecialidades = DAL.dameDAL().cargarEspecialidades();
			this.listaAmbulancias = DAL.dameDAL().cargarAmbulancias();
			this.listaRegistros = DAL.dameDAL().cargarRegistrosEmergencias();
			//this.listaSintomas = DAL.dameDAL().cargarSintomas();
		}catch(DAOExcepcion e){e.printStackTrace();}
		//Cargamos listas auxiliares
		for(int i=0; i<this.listaHospitales.size(); i++){
			this.listaHospitales.get(i).setEspecialidades(DAL.dameDAL().listarEspecialidades(this.listaHospitales.get(i).getNombre()));
			ArrayList<Ambulancia> aux = DAL.dameDAL().cargarAmbulanciasBHosp(this.listaHospitales.get(i).getNombre());
			ArrayList<BHospital> bh = new ArrayList<BHospital>();
			for(int j=0; j<this.listaAmbulancias.size(); ++j){
				if(aux.contains(this.listaAmbulancias.get(j))){
					Ambulancia x = this.listaAmbulancias.get(j);
					bh.add(new BHospital(this.listaHospitales.get(i), x.getRegistro(), x.getTipo(), x.getLatitud(), x.getLongitud(), x.getEquipo(), x.getDisponibilidad()));
				}
			}
			for(int k=0; k<bh.size(); k++){
				bh.get(k).setHospitalAsignado(this.listaHospitales.get(i));
			}
		}
	}
	
	//PACIENTE
	
	public Paciente buscarPaciente(String d) throws LogicaExcepcion {
		for(int i=0; i<this.listaPacientes.size(); i++){
			if(this.listaPacientes.get(i).getDni().equals(d))
				return this.listaPacientes.get(i);
		}
		return null;
	}
	
	public ArrayList<Paciente> listarPacientes() throws LogicaExcepcion {
		return this.listaPacientes;
	}
	
	public void addPaciente(Paciente p) throws LogicaExcepcion {
		this.listaPacientes.add(p);
	}
	
	//HOSPITAL
	
	public Hospital buscarHospital(String n) throws LogicaExcepcion {
		for(int i=0; i<this.listaHospitales.size(); i++){
			if(this.listaHospitales.get(i).getNombre().equals(n))
				return this.listaHospitales.get(i);
		}
		return null;
	}
	
	public ArrayList<Hospital> listarHospitales() throws LogicaExcepcion {
		return this.listaHospitales;
	}
	
	//ESPECIALIDADES
	
	public Especialidad buscarEspecialidad(String n) throws LogicaExcepcion {
		for(int i=0; i<this.listaEspecialidades.size(); i++){
			System.out.println(this.listaEspecialidades.get(i).getNombre());
			if(this.listaEspecialidades.get(i).getNombre().equals(n))
				return this.listaEspecialidades.get(i);
		}
		return null;
	}
	
	public ArrayList<Especialidad> listarEspecialidades() throws LogicaExcepcion {
		return this.listaEspecialidades;
	}
	
	public ArrayList<Especialidad> listarEspecialidades(String h) throws LogicaExcepcion {
		for(int i=0; i<this.listaHospitales.size(); i++){
			if(this.listaHospitales.get(i).getNombre().equals(h))
				return (this.listaHospitales.get(i).getEspecialidades());
		}
		return null;
	}
	
	//AMBULANCIA
	
	public Ambulancia buscarAmbulancia(String n) throws LogicaExcepcion {
		for(int i=0; i<this.listaAmbulancias.size(); i++){
			System.out.println(this.listaAmbulancias.get(i));
			if(this.listaAmbulancias.get(i).getRegistro().equals(n))
				return this.listaAmbulancias.get(i);
		}
		return null;
	}
	
	public void cambiarCoordenadas(String n, float lat, float lon) throws LogicaExcepcion {
		buscarAmbulancia(n).setCoordenadas(lat, lon);
		//aux.setCoordenadas(lat, lon);
	}
	
	public void cambiarDisponibilidad(String n) throws LogicaExcepcion {
		Ambulancia aux = buscarAmbulancia(n);
		System.out.println(aux.getDisponibilidad());
		if(aux.getDisponibilidad()==0)
			aux.setDisponibilidad(1);
		else
			aux.setDisponibilidad(0);
	}
	
	//REGISTROS DE EMERGENCIA
	
	public ArrayList<RegistroEmergencia> listarRegistrosEmergencia() throws LogicaExcepcion {
		return this.listaRegistros;
	}
	
	public void addRegistroEmergencia(RegistroEmergencia re){
		this.listaRegistros.add(re);
	}
	
	//SINTOMAS
	
	public ArrayList<Sintoma> listarSintomas() throws LogicaExcepcion {
		return this.listaSintomas;
	}
	
	/**CASO DE USO REGISTRO DE EMERGENCIA 
	 * --------------------------------
	 */
	public void calculaAmbYsetHosp(double lat, double lon, ArrayList<Sintoma> listaSin){
		try{
		double aux = Double.MAX_VALUE;
		double dist;
		ArrayList<Hospital> hospAmb = new ArrayList<Hospital>();
		Hospital h = null;
		Ambulancia a = null;
		//pasa los hospitales que tienen esa especialidad a una lista
		for(int k= 0; k<listaSin.size(); k++){
			for(int l = 0; l<this.listaHospitales.size();l++){
				for(int m= 0; m<this.listaHospitales.get(l).getEspecialidades().size(); m++){
					if(listaSin.get(k).getEspecialidad().equals(this.listaHospitales.get(l).getEspecialidades().get(m).getNombre()))
					{hospAmb.add(this.listaHospitales.get(l));}
				}
			}
		}
			
		for(int i = 0; i<this.listaAmbulancias.size(); i++){
			for (int j = 0; j<hospAmb.size(); j++){
				dist = Math.sqrt((Math.pow((lat-hospAmb.get(j).getLatitud()), 2)+(Math.pow((lon-hospAmb.get(j).getLongitud()), 2))))+Math.sqrt((Math.pow((lat-this.listaAmbulancias.get(i).getLatitud()), 2) + (Math.pow((lon-this.listaAmbulancias.get(i).getLongitud()), 2))));
				if( dist<aux && (this.listaAmbulancias.get(i) instanceof BHospital  && ((BHospital)this.listaAmbulancias.get(i)).getHospitalAsignado().equals(hospAmb.get(j).getNombre()) || !(this.listaAmbulancias.get(i) instanceof BHospital)) && this.listaAmbulancias.get(i).getDisponibilidad()==1){
						aux = dist;
						h = hospAmb.get(j);
						a = this.listaAmbulancias.get(i);
					
				}
			}
		}
		a.setDisponibilidad(0);
		}catch(Exception e){ e.printStackTrace();}
		
	}
	/**WORKING**/	
	//devuelve el hospital mas cercano
	public Hospital calculaHospital(double lat, double lon, ArrayList<Sintoma> listaSin) {
		double aux = Double.MAX_VALUE;
		Hospital hCer = null;
		int cont = 0;
		boolean encontrada = false;
			for(int i = 0 ; i<this.listaHospitales.size() ; i++){
				cont = 0;
				if(Math.sqrt(
							 (Math.pow((lat-this.listaHospitales.get(i).getLatitud()), 2) 
									 	+ (Math.pow((lon-this.listaHospitales.get(i).getLongitud()), 2))))<aux )
				{
					for(int k = 0; k<listaSin.size() ; k++){
						encontrada = false;
						for(int x = 0 ; x<this.listaHospitales.get(i).getEspecialidades().size() && (!encontrada) ; x++){					
								if(listaSin.get(k).getEspecialidad().equals(this.listaHospitales.get(i).getEspecialidades().get(x).getNombre())){
										encontrada = true;
										cont++;																	
						}
					}
					if(cont == listaSin.size()){
						hCer = this.listaHospitales.get(i);
						aux = Math.sqrt((Math.pow((lat-this.listaHospitales.get(i).getLatitud()), 2) + (Math.pow((lon-this.listaHospitales.get(i).getLongitud()), 2))));					
						}
					}	
			
					
			   }		
			}return hCer;
	}
	
	//devuelve la ambulancia mas cercana
	public Ambulancia calculaAmbulancia(double lat, double lon, Hospital hospi) {
		double aux = Double.MAX_VALUE;
		Ambulancia ambuCer = null;
		for(int i = 0 ; i<this.listaAmbulancias.size() ; i++){
			if(Math.sqrt(
						 (Math.pow((lat-this.listaAmbulancias.get(i).getLatitud()), 2) 
								 	+ (Math.pow((lon-this.listaAmbulancias.get(i).getLongitud()), 2))))<aux && this.listaAmbulancias.get(i).getDisponibilidad()==1)
			{
				ambuCer = this.listaAmbulancias.get(i);
				aux = Math.sqrt((Math.pow((lat-this.listaAmbulancias.get(i).getLatitud()), 2) + (Math.pow((lon-this.listaAmbulancias.get(i).getLongitud()), 2))));
			}				
			
		}
		ambuCer.setDisponibilidad(0);
		return ambuCer;
	}	
	
	//SALIR
	
	public void exit() throws LogicaExcepcion{
		try{
			DAL.dameDAL().guardarPacientes(this.listaPacientes);
			DAL.dameDAL().guardarAmbulancias(this.listaAmbulancias);
			DAL.dameDAL().guardarRegistrosEmergencias(this.listaRegistros);
		}catch(DAOExcepcion e){e.printStackTrace();}
	}
}