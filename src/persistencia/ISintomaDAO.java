package persistencia;

import java.util.ArrayList;
import java.util.List;

import logica.Sintoma;
import excepciones.DAOExcepcion;

public interface ISintomaDAO {
	public ArrayList<Sintoma> cargarSintomas () throws DAOExcepcion;
	public void guardarSintomas(ArrayList<Sintoma> s) throws DAOExcepcion;
}
