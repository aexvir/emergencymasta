package persistencia;

import java.util.ArrayList;
import java.util.List;

import logica.Ambulancia;
import logica.Especialidad;
import logica.Hospital;
import logica.Paciente;
import logica.RegistroEmergencia;
import logica.Sintoma;
import excepciones.DAOExcepcion;

public class DAL {
	private static DAL dal;
	
	IPacienteDAO pacienteDAO;
	IAmbulanciaDAO ambulanciaDAO;
	IEspecialidadDAO especialidadDAO;
	IHospitalDAO hospitalDAO;
	IRegistroEmergenciaDAO registroEmergenciaDAO;
	ISintomaDAO sintomaDAO;
	
	private DAL() {}
	
	public static DAL dameDAL() 
	{
		if (dal == null)
			dal = new DAL();
		return dal;
	}
	
	//PACIENTE
	
	public ArrayList<Paciente> cargarPacientes() throws DAOExcepcion {
		pacienteDAO = new PacienteDAOImp();
		return pacienteDAO.cargarPacientes();
	}
	
	public void guardarPacientes(ArrayList<Paciente> pac) throws DAOExcepcion{
		pacienteDAO = new PacienteDAOImp();
		pacienteDAO.guardarPacientes(pac);
	}
	
	//HOSPITAL
	
	public ArrayList<Hospital> cargarHospitales() throws DAOExcepcion{
		hospitalDAO = new HospitalDAOImp();
		return hospitalDAO.cargarHospitales();
	}
	
	public ArrayList<Especialidad> listarEspecialidades(String h) throws DAOExcepcion{
		especialidadDAO = new EspecialidadDAOImp();
		return especialidadDAO.listarEspecialidades(h);
	}
	
	public ArrayList<Ambulancia> cargarAmbulanciasBHosp(String h) throws DAOExcepcion{
		ambulanciaDAO = new AmbulanciaDAOImp();
		return ambulanciaDAO.cargarAmbulanciasBHosp(h);
	}
		
	//ESPECIALIDADES
	
	private void AmbulanciaDAOImp() {
		// TODO Auto-generated method stub
		
	}

	public ArrayList<Especialidad> cargarEspecialidades() throws DAOExcepcion{
		especialidadDAO = new EspecialidadDAOImp();
		return especialidadDAO.cargarEspecialidades();
	}
		
	//AMBULANCIAS
	
	public ArrayList<Ambulancia> cargarAmbulancias() throws DAOExcepcion{
		ambulanciaDAO = new AmbulanciaDAOImp();
		return ambulanciaDAO.cargarAmbulancias();
	}
	
	public void guardarAmbulancias(ArrayList<Ambulancia> am) throws DAOExcepcion{
		ambulanciaDAO = new AmbulanciaDAOImp();
		ambulanciaDAO.guardarAmbulancias(am);
	}
	
	//REGISTROS
	
	public ArrayList<RegistroEmergencia> cargarRegistrosEmergencias() throws DAOExcepcion{
		registroEmergenciaDAO = new RegistroEmergenciaDAOImp();
		return registroEmergenciaDAO.cargarRegistrosEmergencias();
	}
	
	public void guardarRegistrosEmergencias(ArrayList<RegistroEmergencia> re) throws DAOExcepcion{
		registroEmergenciaDAO = new RegistroEmergenciaDAOImp();
		registroEmergenciaDAO.guardarRegistrosEmergencias(re);
	}
	
	//SINTOMAS
	
		public ArrayList<Sintoma> cargarSintomas(String idr) throws DAOExcepcion{
			sintomaDAO = new SintomaDAOImp();
			return sintomaDAO.cargarSintomas();
		}
		
		public void guardarSintomas(ArrayList<Sintoma> re) throws DAOExcepcion{
			sintomaDAO = new SintomaDAOImp();
			sintomaDAO.guardarSintomas(re);
		}
}