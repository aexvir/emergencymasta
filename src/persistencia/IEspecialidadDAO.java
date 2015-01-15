package persistencia;

import java.util.ArrayList;
import java.util.List;

import logica.Especialidad;
import excepciones.DAOExcepcion;

public interface IEspecialidadDAO {
	public ArrayList<Especialidad> cargarEspecialidades() throws DAOExcepcion;
	public ArrayList<Especialidad> listarEspecialidades(String h) throws DAOExcepcion;
}
