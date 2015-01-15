package persistencia;
import java.util.ArrayList;
import java.util.List;

import logica.Paciente;
import excepciones.*;
public interface IPacienteDAO {
	public ArrayList<Paciente> cargarPacientes() throws DAOExcepcion;
	public void guardarPacientes(ArrayList<Paciente> pac) throws DAOExcepcion;
}
