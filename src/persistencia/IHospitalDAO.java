package persistencia;
import java.util.ArrayList;
import java.util.List;

import logica.Hospital;
import excepciones.*;
public interface IHospitalDAO {
	public ArrayList<Hospital> cargarHospitales() throws DAOExcepcion;
}