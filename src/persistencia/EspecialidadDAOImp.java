package persistencia;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

import logica.Especialidad;
import excepciones.DAOExcepcion;

public class EspecialidadDAOImp implements IEspecialidadDAO {
	protected ConnectionManager connManager;

	public EspecialidadDAOImp() throws DAOExcepcion {
		super();
		try{
			connManager= new ConnectionManager("emergenciasBD");
		}
		catch (ClassNotFoundException e){throw new DAOExcepcion(e);}
	}
	  
	public ArrayList <Especialidad> cargarEspecialidades() throws DAOExcepcion{
		try{
			connManager.connect();
			ResultSet rs=connManager.queryDB("select * from ESPECIALIDAD");						
			connManager.close();
	  	  
			ArrayList<Especialidad> listaEspecialidades=new ArrayList<Especialidad>();
				
			try{				
				while (rs.next()){
					Especialidad e = buscarEspecialidad(rs.getString("NOMBRE"));	 
					listaEspecialidades.add(e);
				}
				return listaEspecialidades;
				}
			catch (Exception e){throw new DAOExcepcion(e);}
			}
		catch (DAOExcepcion e){throw e;}	
	 }
	  
	public Especialidad buscarEspecialidad(String nombre)throws DAOExcepcion{
		try{
			connManager.connect();
			ResultSet rs=connManager.queryDB("select * from ESPECIALIDAD where NOMBRE= '"+nombre+"'");
			connManager.close();
		
			if (rs.next())
			{
				return new Especialidad (nombre);
			}
			else
				return null;	
		}
		catch (SQLException e){	throw new DAOExcepcion(e);}	
	}
	
public ArrayList<Especialidad> listarEspecialidades(String h) throws DAOExcepcion {
		
		try{
			
			connManager.connect();
			ResultSet rs = connManager.queryDB("select * from ESPECIALIDAD e, ATIENDE a, HOSPITAL h where h.nombre = '"+h+"' AND a.idhospital = h.nombre AND e.nombre = a.idespecialidad ");						
			connManager.close();
			
			ArrayList<Especialidad> listaEspecialidad = new ArrayList<Especialidad>();
			
			try{
				while(rs.next()){
				 Especialidad esp = new Especialidad(rs.getString("NOMBRE"));
				 listaEspecialidad.add(esp);
				}
				return listaEspecialidad;
				
			}catch (Exception e){throw new DAOExcepcion(e);}
		
	 }catch (DAOExcepcion e){throw e;}	
		
	}
}

