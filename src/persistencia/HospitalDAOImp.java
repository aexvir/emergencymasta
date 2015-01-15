package persistencia;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

import logica.Hospital;
import excepciones.DAOExcepcion;

public class HospitalDAOImp implements IHospitalDAO {
	protected ConnectionManager connManager;

	public HospitalDAOImp() throws DAOExcepcion {
		super();
		try{
			connManager= new ConnectionManager("emergenciasBD");
		}
		catch (ClassNotFoundException e){throw new DAOExcepcion(e);}
	}
	  
	public ArrayList <Hospital> cargarHospitales() throws DAOExcepcion{
		try{
			connManager.connect();
			ResultSet rs=connManager.queryDB("select * from HOSPITAL");						
			connManager.close();
	  	  
			ArrayList<Hospital> listaHospitales=new ArrayList<Hospital>();
				
			try{				
				while (rs.next()){
					Hospital h = buscarHospital(rs.getString("NOMBRE"));	 
					listaHospitales.add(h);
				}
				return listaHospitales;
				}
			catch (Exception e){	throw new DAOExcepcion(e);}
			}
		catch (DAOExcepcion e){		throw e;}	
	 }
	  
	public Hospital buscarHospital(String nombre)throws DAOExcepcion{
		try{
			connManager.connect();
			ResultSet rs=connManager.queryDB("select * from HOSPITAL where NOMBRE= '"+nombre+"'");
			connManager.close();
		
			if (rs.next())
			{
				return new Hospital (nombre, rs.getString("DIRECCION"), rs.getFloat("LATITUD"),rs.getFloat("LONGITUD"));
			}
			else
				return null;	
		}
		catch (SQLException e){	throw new DAOExcepcion(e);}	
	}
}
