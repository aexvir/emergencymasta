package logica;

import java.util.ArrayList;
import java.util.List;

import excepciones.DAOExcepcion;
import excepciones.LogicaExcepcion;

public class Controlador {
	
	private ServicioEmergencia servicioEmergencia;
	private static Controlador controlador = null;
	
	private Controlador() {
		try{
			servicioEmergencia = new ServicioEmergencia();
		}
		catch (DAOExcepcion e)
		{
			System.out.println(e);
		}
	}
	
	public static Controlador dameControlador(){
		if (controlador == null) controlador = new Controlador();
		return controlador;
	}

	public Hospital calculaHospital(double la, double lo, ArrayList<Sintoma> ls) throws LogicaExcepcion{
		return servicioEmergencia.calculaHospital(la, lo, ls);
	}
	
	public ArrayList<RegistroEmergencia> listarRegistrosEmergencias() throws LogicaExcepcion{
		return servicioEmergencia.listarRegistrosEmergencia();
	}
	
	public void addRegistroEmergencia(RegistroEmergencia re) throws LogicaExcepcion{
		servicioEmergencia.addRegistroEmergencia(re);
	}
	
	public void AltaPaciente (Paciente pa) throws LogicaExcepcion{
		//Los servivios se implementaron en la clase ServicioEmergencia en las sesiones previas
		servicioEmergencia.addPaciente(pa);
	}
	
	public Ambulancia calculaAmbulancia(double la, double lo, Hospital ho) throws LogicaExcepcion{
		return servicioEmergencia.calculaAmbulancia(la, lo, ho);
	}
	
	public Paciente buscarPaciente(String dni) throws LogicaExcepcion{
		return servicioEmergencia.buscarPaciente(dni);
	}
	
	public ArrayList<Paciente> ListarPacientes() throws LogicaExcepcion{
		return servicioEmergencia.listarPacientes();
	}
	
	public void CambiarDisponibilidad(String nre) throws LogicaExcepcion{
		servicioEmergencia.cambiarDisponibilidad(nre);
	}
	
	public void CambiarCoordenadas(String nre, float lati, float longi) throws LogicaExcepcion{
		servicioEmergencia.cambiarCoordenadas(nre, lati, longi);
	}
	
	public ArrayList<Especialidad> ListarEspecialidades(String hospital) throws LogicaExcepcion{
		return servicioEmergencia.listarEspecialidades(hospital);
	}
	
	public ArrayList<Especialidad> ListarEspecialidades() throws LogicaExcepcion{
		return servicioEmergencia.listarEspecialidades();
	}
	
	public Ambulancia BuscarAmbulancia(String nregistro) throws LogicaExcepcion{
		return servicioEmergencia.buscarAmbulancia(nregistro);
	}
	
	public void exit() throws LogicaExcepcion{
		servicioEmergencia.exit();
	}
}
