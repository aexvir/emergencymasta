package persistencia;
import java.sql.*;
import java.util.ArrayList;

import logica.Ambulancia;
import logica.Paciente;
import excepciones.DAOExcepcion;

public class AmbulanciaDAOImp implements IAmbulanciaDAO {
	protected ConnectionManager connManager;
	
	public AmbulanciaDAOImp() throws DAOExcepcion {
		super();
		try{
			connManager= new ConnectionManager("emergenciasBD");
		}
		catch (ClassNotFoundException e){throw new DAOExcepcion(e);}
	}
	  
	public ArrayList<Ambulancia> cargarAmbulancias() throws DAOExcepcion{
		try{
			connManager.connect();
			ResultSet rs=connManager.queryDB("select * from AMBULANCIA");						
			connManager.close();
	  	  
			ArrayList<Ambulancia> listaAmbulancias=new ArrayList<Ambulancia>();
				
			try{				
				while (rs.next()){
					Ambulancia a = buscarAmbulancia(rs.getString("NUMREGISTRO"));	 
					listaAmbulancias.add(a);
				}
				return listaAmbulancias;
				}
			catch (Exception e){throw new DAOExcepcion(e);}
			}
		catch (DAOExcepcion e){throw e;}	
	}
	
	public ArrayList<Ambulancia> cargarAmbulanciasBHosp(String h) throws DAOExcepcion {
		try{
			connManager.connect();
			ResultSet rs=connManager.queryDB("select * from BHOSPITAL b, AMBULANCIA a where b.IDHOSPITAL ='"+h+"' and b.NUMREGISTRO = a.NUMREGISTRO");
			connManager.close();
	  	  
			ArrayList<Ambulancia> listaAmbulancias=new ArrayList<Ambulancia>();
				
			try{				
				while (rs.next()){
					Ambulancia a = buscarAmbulancia(rs.getString("NUMREGISTRO"));
					listaAmbulancias.add(a);
				}
				return listaAmbulancias;
				}
			catch (Exception e){throw new DAOExcepcion(e);}
			}
		catch (DAOExcepcion e){throw e;}
	}
	
	public Ambulancia buscarAmbulancia(String numregistro)throws DAOExcepcion{
		try{
			connManager.connect();
			ResultSet rs=connManager.queryDB("select * from AMBULANCIA where NUMREGISTRO = '"+numregistro+"'");
			connManager.close();
			
			if (rs.next()){
				return new Ambulancia (numregistro, rs.getInt("TIPO"), rs.getFloat("LATITUD"), rs.getFloat("LONGITUD"), rs.getString("EQUIPO"), rs.getInt("DISPONIBILIDAD"));
			}
			else
				return null;	
		}
		catch (SQLException e){	throw new DAOExcepcion(e);}	
	}
	
	public void guardarAmbulancias(ArrayList<Ambulancia> am) throws DAOExcepcion{		
		for(int i=0; i<am.size(); i++){
			connManager.connect();
			ResultSet rs=connManager.queryDB("select * from AMBULANCIA where NUMREGISTRO = '"+am.get(i).getRegistro()+"'");
			connManager.close();
			
			try {
				if(rs.next()){
					connManager.connect();
					connManager.updateDB("UPDATE AMBULANCIA SET LATITUD = "+am.get(i).getLatitud()+" , LONGITUD = "+am.get(i).getLongitud()+" , DISPONIBILIDAD = "+am.get(i).getDisponibilidad()+" WHERE NUMREGISTRO = '"+am.get(i).getRegistro()+"'");
					connManager.close();
				}
			} catch (SQLException e) {e.printStackTrace();}
		}
	}
}