package persistencia;
import java.sql.*;
import excepciones.DAOExcepcion;

public class ConnectionManager {
	
	private String sourceURL;
	private Connection dbcon=null;
	
	
public ConnectionManager(String dbname) throws ClassNotFoundException{
	 Class.forName("org.hsqldb.jdbcDriver");
	 sourceURL = "jdbc:hsqldb:hsql://localhost/"+dbname;	
}
public void connect() throws DAOExcepcion{
	if (dbcon==null)
		try{
			dbcon = DriverManager.getConnection(sourceURL);
		} catch(SQLException e){
			throw new DAOExcepcion("DB_CONNECT_ERROR");
		}
}
public void close() throws DAOExcepcion{
	if (dbcon!=null){
		try{
			dbcon.close();
		} catch(SQLException e){
			throw new DAOExcepcion("DB_DISCONNECT_ERROR");
		}
		dbcon=null;
	}	
}
public void updateDB(String sql) throws DAOExcepcion{
	if (dbcon!=null){
		try{
			Statement sentencia = dbcon.createStatement();
			sentencia.executeUpdate(sql);
		} catch(SQLException e){
			throw new DAOExcepcion("DB_WRITE_ERROR");
		}
	}
	}
public ResultSet queryDB(String sql) throws DAOExcepcion{
	if (dbcon!=null){
		try{
			Statement sentencia = dbcon.createStatement();
			return sentencia.executeQuery(sql);
		} catch(SQLException e){
			throw new DAOExcepcion("DB_READ_ERROR");
		}
	}
	return null;
}
}