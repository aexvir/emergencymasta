	//****************************************************************
	//****    PATRÓN SINGLETON
	//****************************************************************
//	private static PacienteDAOImp paciente; 
//	
//	public static PacienteDAOImp dameDAO() throws DAOExcepcion{
//		if (paciente==null)		paciente = new PacienteDAOImp();
//		return paciente;
//	}
	
//PacienteDAOImp
package persistencia;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

import logica.Paciente;
import excepciones.DAOExcepcion;

public class PacienteDAOImp implements IPacienteDAO {
	protected ConnectionManager connManager;

	public PacienteDAOImp() throws DAOExcepcion {
		super();
		try{
			connManager= new ConnectionManager("emergenciasBD");
		}
		catch (ClassNotFoundException e){	throw new DAOExcepcion(e);}
	}
	
	public void crearPaciente(Paciente pa) throws DAOExcepcion {		
		try{
			connManager.connect();
			System.out.println(pa.getDni()+"\n"+pa.getNombre()+"\n"+pa.getApellidos()+"\n"+pa.getDireccion()+"\n"+pa.getTelefono()+"\n"+pa.getEdad()+"\n"+pa.getSexo());
			connManager.updateDB("insert into PACIENTE (DNI, NOMBRE, APELLIDOS, DIRECCION, TELEFONO, EDAD, SEXO) values ('"+pa.getDni()+"','"+pa.getNombre()+"', '"+pa.getApellidos()+"', '"+pa.getDireccion()+"', '"+pa.getTelefono()+"', "+pa.getEdad()+", '"+pa.getSexo()+"')");
			connManager.close();
		}
		catch (Exception e){	throw new DAOExcepcion(e);}
	}
	
	public void guardarPacientes(ArrayList<Paciente> pac) throws DAOExcepcion{
		System.out.println("Guardando...");
		ArrayList<Paciente> aux = null;
		aux = cargarPacientes();
		for(int i=0; i<pac.size(); i++){
			if(!aux.contains(pac.get(i)))
				crearPaciente(pac.get(i));
		}			
	}
	  
	public ArrayList<Paciente> cargarPacientes() throws DAOExcepcion{
		try{
			connManager.connect();
			ResultSet rs=connManager.queryDB("select * from PACIENTE");	
			connManager.close();
	  	  
			ArrayList<Paciente> listaPacientes=new ArrayList<Paciente>();
				
			try{				
				while (rs.next()){
					Paciente pa = buscarPaciente(rs.getString("DNI"));	 
					listaPacientes.add(pa);
				}
				return listaPacientes;
				}
			catch (Exception e){	throw new DAOExcepcion(e);}
			}
		catch (DAOExcepcion e){		throw e;}	
	 }
	  
	public Paciente buscarPaciente(String dni)throws DAOExcepcion{
		try{
			connManager.connect();
			ResultSet rs=connManager.queryDB("select * from PACIENTE where DNI= '"+dni+"'");
			connManager.close();
		
			if (rs.next())
			{
				char sexo = rs.getString("SEXO").charAt(0);
				return new Paciente(dni, rs.getString("NOMBRE"), rs.getString("APELLIDOS"), sexo, rs.getInt("EDAD"), rs.getString("TELEFONO"), rs.getString("DIRECCION"));
			}
			else
				return null;	
		}
		catch (SQLException e){	throw new DAOExcepcion(e);}	
	}
}