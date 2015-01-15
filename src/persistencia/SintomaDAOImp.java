package persistencia;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import logica.Sintoma;
import excepciones.DAOExcepcion;

public class SintomaDAOImp implements ISintomaDAO{
	protected ConnectionManager connManager;
	
	public SintomaDAOImp() throws DAOExcepcion {
		super();
		try{
			connManager= new ConnectionManager("emergenciasBD");
		}
		catch (ClassNotFoundException e){	throw new DAOExcepcion(e);}
	}
	
	public ArrayList<Sintoma> cargarSintomas() throws DAOExcepcion{
		try{
			connManager.connect();
			ResultSet rs=connManager.queryDB("select * from SINTOMA");	
			connManager.close();
	  	  
			ArrayList<Sintoma> listaSintomas=new ArrayList<Sintoma>();
				
			try{				
				while (rs.next()){
					Sintoma s = buscarSintoma(rs.getString("IDREGISTRO"));	 
					listaSintomas.add(s);
				}
				return listaSintomas;
				}
			catch (Exception e){throw new DAOExcepcion(e);}
			}
		catch (DAOExcepcion e){throw e;}		
	}
	
	public ArrayList<Sintoma> cargarSintomas(String idr) throws DAOExcepcion{
		try{
			connManager.connect();
			ResultSet rs=connManager.queryDB("select * from SINTOMA where IDREGISTRO='"+idr+"'");	
			connManager.close();
	  	  
			ArrayList<Sintoma> listaSintomas=new ArrayList<Sintoma>();
				
			try{				
				while (rs.next()){
					Sintoma s = buscarSintoma(rs.getString("IDREGISTRO"));	 
					listaSintomas.add(s);
				}
				return listaSintomas;
				}
			catch (Exception e){throw new DAOExcepcion(e);}
			}
		catch (DAOExcepcion e){throw e;}		
	}
	
	public void guardarSintomas(ArrayList<Sintoma> s) throws DAOExcepcion{
		ArrayList<Sintoma> aux = cargarSintomas();
		for(int i=0; i<s.size(); i++)
			if(!aux.contains(s.get(i))) crearSintoma(s.get(i));
	}	
	
	public Sintoma buscarSintoma (String idRegistro) throws DAOExcepcion{
		try{
			connManager.connect();
			ResultSet rs=connManager.queryDB("select * from SINTOMA where IDREGISTRO= '"+idRegistro+"'");
			connManager.close();
		
			if (rs.next())
			{
				return new Sintoma (rs.getString("IDESPECIALIDAD"), idRegistro, rs.getString("ESTADO"), rs.getString("DURACION"), rs.getString("DESCRIPCION"));
			}
			else
				return null;	
		}
		catch (SQLException e){	throw new DAOExcepcion(e);}	
	}
	public void crearSintoma (Sintoma s) throws DAOExcepcion{
		try{
			connManager.connect();
			connManager.updateDB("insert into SINTOMA (IDESPECIALIDAD, IDREGISTRO, ESTADO, DURACION, DESCRIPCION) values ('"+s.getEspecialidad()+"','"+s.getRegistro()+"','"+s.getEstado()+"', '"+s.getDuracion()+"', "+s.getDescripcion()+"')");
			connManager.close();
		}
		catch (Exception e){	throw new DAOExcepcion(e);}
	}
}
