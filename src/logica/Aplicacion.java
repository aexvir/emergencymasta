package logica;

import excepciones.DAOExcepcion;

public class Aplicacion {
	
	public static void main (String [] args)
	{
		try
		{
			//Se crea el Servicio de Emergencia
			ServicioEmergencia emergencias = new ServicioEmergencia();
			
				//INCLUIR EJECUCIONES
		}
		catch (DAOExcepcion e)
		{
			System.out.println("DAOExcepcion: " + e);
		}
	}
}
