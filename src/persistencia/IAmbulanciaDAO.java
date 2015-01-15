package persistencia;

import java.util.ArrayList;
import java.util.List;

import logica.Ambulancia;
import excepciones.DAOExcepcion;

public interface IAmbulanciaDAO {
	public ArrayList<Ambulancia> cargarAmbulancias() throws DAOExcepcion;
	public ArrayList<Ambulancia> cargarAmbulanciasBHosp(String h) throws DAOExcepcion;
	public void guardarAmbulancias(ArrayList<Ambulancia> am) throws DAOExcepcion;
}
