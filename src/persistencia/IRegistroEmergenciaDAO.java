package persistencia;

import java.util.ArrayList;
import java.util.List;

import logica.RegistroEmergencia;
import excepciones.DAOExcepcion;

public interface IRegistroEmergenciaDAO {
	public ArrayList<RegistroEmergencia> cargarRegistrosEmergencias() throws DAOExcepcion;
	public void guardarRegistrosEmergencias(ArrayList<RegistroEmergencia> re) throws DAOExcepcion;
}
