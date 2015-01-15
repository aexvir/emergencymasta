package persistencia;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import logica.RegistroEmergencia;
import logica.Sintoma;
import persistencia.SintomaDAOImp;
import excepciones.DAOExcepcion;

public class RegistroEmergenciaDAOImp implements IRegistroEmergenciaDAO {
	protected ConnectionManager connManager;
	
	public RegistroEmergenciaDAOImp() throws DAOExcepcion {
		super();
		try{
			connManager= new ConnectionManager("emergenciasBD");
		}
		catch (ClassNotFoundException e){	throw new DAOExcepcion(e);}
	}
	
	public void guardarRegistrosEmergencias(ArrayList<RegistroEmergencia> re) throws DAOExcepcion{
		ArrayList<RegistroEmergencia> aux = cargarRegistrosEmergencias();
		for(int i=0; i<re.size(); i++)
			if(!aux.contains(re.get(i))) crearRegistroEmergencia(re.get(i));
	}
	
	public RegistroEmergencia buscarRegistroEmergencia(String idRegistro)throws DAOExcepcion
	{
		try{
			connManager.connect();
			ResultSet rs=connManager.queryDB("select * from REGISTROEMERGENCIA where IDREGISTRO= '"+idRegistro+"'");
			connManager.close();
			
			ArrayList<Sintoma> sin = new SintomaDAOImp().cargarSintomas(idRegistro);
		
			if (rs.next())
			{
				return new RegistroEmergencia (idRegistro, rs.getFloat("LATITUD"), rs.getFloat("LONGITUD"), rs.getString("DNI"), rs.getString("FECHA"), rs.getString("HORA"), rs.getString("IDAMBULANCIA"), rs.getString("IDHOSPITAL"), sin);
			}
			else
				return null;	
		}
		catch (SQLException e){	throw new DAOExcepcion(e);}	
	}
	
	public void crearRegistroEmergencia (RegistroEmergencia r)throws DAOExcepcion
	{
		try{
			connManager.connect();
			connManager.updateDB("insert into REGISTROEMERGENCIA (IDREGISTRO, LATITUD, LONGITUD, DNI, FECHA, HORA, IDAMBULANCIA, IDHOSPITAL) values ('"+r.getIdRegistro()+"','"+r.getLatitud()+"','"+r.getLongitud()+"', '"+r.getDniPaciente()+"', '"+r.getFecha()+"', '"+r.getHora()+"','"+r.getIdAmbulancia()+"', '"+r.getIdHospital()+"')");
			connManager.close();
		}
		catch (Exception e){throw new DAOExcepcion(e);}
	}

	public ArrayList <RegistroEmergencia> cargarRegistrosEmergencias() throws DAOExcepcion
	{
		try{
			connManager.connect();
			ResultSet rs=connManager.queryDB("select * from REGISTROEMERGENCIA");	
			connManager.close();
	  	  
			ArrayList<RegistroEmergencia> listaRegistros=new ArrayList<RegistroEmergencia>();
				
			try{				
				while (rs.next()){
					RegistroEmergencia r = buscarRegistroEmergencia(rs.getString("IDREGISTRO"));	 
					listaRegistros.add(r);
				}
				return listaRegistros;
				}
			catch (Exception e){	throw new DAOExcepcion(e);}
			}
		catch (DAOExcepcion e){		throw e;}	
	 }
	  
	}
