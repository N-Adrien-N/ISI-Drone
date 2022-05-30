package manager;

import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.logging.Level;
import java.util.logging.Logger;

public class MDB {
	private static Connection connection = null;
	private static MPropertieFileReader bdProps;
        
	public static void connect() {
            
            String fileRessource = "configs.properties";     
            try {
                bdProps = new MPropertieFileReader();
                bdProps.init(fileRessource);
                Class.forName(bdProps.getBdName());
                String mysqlURL=bdProps.getBdUrl()+"serverTimezone="+bdProps.getBdTimezone();
                connection = DriverManager.getConnection(mysqlURL, bdProps.getBdUsername(), bdProps.getBdPassword());
                /*Class.forName("com.mysql.cj.jdbc.Driver");
                String mysqlURL="jdbc:mysql://127.0.0.1:3306/isidrone?serverTimezone=UTC";
                connection = DriverManager.getConnection(mysqlURL,"root", "abc123...");*/
            } catch (ClassNotFoundException | SQLException ex) {
                Logger.getLogger(MDB.class.getName()).log(Level.SEVERE, null, ex);
            }
	}
	
	public static ResultSet execQuery(String query) {
		PreparedStatement ps = getPS(query);
		ResultSet rs = null;
		try {
			if(ps != null) {
				ps.execute();
				rs = ps.getResultSet();
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}	
		return rs;
	}
	
	public static PreparedStatement getPS(String query) {
		PreparedStatement ps = null;
		try { 
			if(connection == null || connection.isClosed())
				connect();
			ps = connection.prepareStatement(query);
		}
		catch (SQLException e) {
			e.printStackTrace();
		}
		
		return ps;
	}
	
	public static PreparedStatement getPS(String query, int id) {
		PreparedStatement ps = null;
		try { 
			if(connection == null || connection.isClosed())
				connect();
			if (id == 1)
				ps = connection.prepareStatement(query,
                        Statement.RETURN_GENERATED_KEYS);
		}
		catch (SQLException e) {
			e.printStackTrace();
		}
		
		return ps;
	}
	
	public static PreparedStatement getPS(String query, String column) {
		return getPS(query, new String[]{column});
	}
	
	public static PreparedStatement getPS(String query, String[] columns) {
		PreparedStatement ps = null;
		try { 
			if(connection == null || connection.isClosed())
				connect();
				ps = connection.prepareStatement(query,columns);
		}
		catch (SQLException e) {
			e.printStackTrace();
		}
		
		return ps;
	}
	
	public static void disconnect() {
		try {
			if(connection != null && !connection.isClosed())
				connection.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		finally {
			connection = null;
		}
	}
}
