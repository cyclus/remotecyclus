package edu.uidaho.remote;
import java.sql.*;

public class DBClass 
{
	//JDBC driver name and database URL
	static final String JDBC_DRIVER = "com.mysql.jdbc.Driver";
	
	static final String DB_URL = "jdbc:mysql://129.101.194.157:3306/Cyclus_MetaData";

	//Database credentials
	static final String USER = "cyclus";
	
	static final String PASS = "mapwindow15";
	
	public Connection getConnection() throws ClassNotFoundException, SQLException{       
		
		Connection conn = null;
		
		Class.forName("com.mysql.jdbc.Driver");
		
		//Register JDBC driver
	    Class.forName("com.mysql.jdbc.Driver");

	    //Open a connection
	    conn = DriverManager.getConnection(DB_URL, USER, PASS);
	    
	    return conn; 
  }

}
