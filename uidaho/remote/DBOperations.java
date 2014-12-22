package edu.uidaho.remote;
import java.sql.*;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.control.ChoiceBox;
import edu.uidaho.remote.SimulationServermaster;
import edu.uidaho.remote.DBClass;

public class DBOperations
{

// this method is used to insert records into Simulations Table
public static void insertSimulationRecord(String Sim_Name, String Sim_Version, String Sim_Des, String AccessURL, String OutputURL)
{
	Connection conn = null;
	
	Statement stmt = null;
	
	try
	{
		 //Execute a query
		  DBClass objDBConnection = new DBClass();
		  
		  conn = objDBConnection.getConnection();
		  
	      stmt = conn.createStatement();
	    
	      String sql = "INSERT INTO Simulations " +
	                   "VALUES (LAST_INSERT_ID(),"+ "'" + Sim_Name + "'" + "," + "'" + Sim_Version + "'"+ "," + "'" + Sim_Des + "'" + "," + "'" + AccessURL + "'" + "," + "'" + OutputURL + "'" + ")";
	      
	      stmt.executeUpdate(sql);
	     
	   }catch(SQLException se){
	      //Handle errors for JDBC
	      se.printStackTrace();
	   }catch(Exception e){
	      //Handle errors for Class.forName
	      e.printStackTrace();
	   }finally{
	      //finally block used to close resources
	      try{
	         if(stmt!=null)
	            conn.close();
	      }catch(SQLException se){
	      }// do nothing
	      try{
	         if(conn!=null)
	            conn.close();
	      }catch(SQLException se){
	         se.printStackTrace();
	      }//end finally try
	   }//end try
}
	
public static void updateSimulationRecord(int SimulationID, String Sim_Name, String Sim_Version, String Sim_Des, String AccessURL, String OutputURL)
{
Connection conn = null;
	
	Statement stmt = null;
	
	try
	{
		 //Execute a query
		  DBClass objDBConnection = new DBClass();
		  
		  conn = objDBConnection.getConnection();
		  
	      System.out.println("Inserting records into the table...");
	     
	      stmt = conn.createStatement();
	    
	      String sql = "update Simulations set Simulation_Name = '"+Sim_Name+"',  Simulation_Version = '"+Sim_Version+"', Simulation_Description = '"+Sim_Des+"', AccessURL = '"+AccessURL+"', DownloadURL = '"+ OutputURL  + "' where Simulation_ID = " + SimulationID + ";";

	      stmt.executeUpdate(sql);
	      
	      System.out.println("Update records into the Simulation table..." + SimulationID);

	   }catch(SQLException se){
	      //Handle errors for JDBC
	      se.printStackTrace();
	   }catch(Exception e){
	      //Handle errors for Class.forName
	      e.printStackTrace();
	   }finally{
	      //finally block used to close resources
	      try{
	         if(stmt!=null)
	            conn.close();
	      }catch(SQLException se){
	      }// do nothing
	      try{
	         if(conn!=null)
	            conn.close();
	      }catch(SQLException se){
	         se.printStackTrace();
	      }//end finally try
	   }//end try
}

// this method is used to insert records to Sim_Servers Table
public static void insertSimServerRecord(String Sim_Ser_Name, String Ser_Location, String Ser_Description)
{
	Connection conn = null;
	
	Statement stmt = null;
	   try
	   {
	      //Execute a query
		  DBClass objDBConnection = new DBClass();
		  
		  conn = objDBConnection.getConnection();
		  
	      System.out.println("Inserting records into the table...");
	     
	      stmt = conn.createStatement();
	     
	      String sql = "INSERT INTO Sim_Servers " +
                  "VALUES (LAST_INSERT_ID(),"+ "'" + Sim_Ser_Name + "'" + "," + "'" + Ser_Location + "'"+ "," + "'" + Ser_Description + "'" + ")";
     
          stmt.executeUpdate(sql);
	      
	     // System.out.println("Inserted records into the table...");

	   }catch(SQLException se){
	      //Handle errors for JDBC
	      se.printStackTrace();
	   }catch(Exception e){
	      //Handle errors for Class.forName
	      e.printStackTrace();
	   }finally{
	      //finally block used to close resources
	      try{
	         if(stmt!=null)
	            conn.close();
	      }catch(SQLException se){
	      }// do nothing
	      try{
	         if(conn!=null)
	            conn.close();
	      }catch(SQLException se){
	         se.printStackTrace();
	      }//end finally try
	   }//end try	  
}

public static void updateSimServerRecord(int SimServerID, String Sim_Ser_Name, String Ser_Location, String Ser_Description)
{
	Connection conn = null;
	
	Statement stmt = null;
	   try
	   {
	      //Execute a query
		  DBClass objDBConnection = new DBClass();
		  
		  conn = objDBConnection.getConnection();
		  
	      System.out.println("Inserting records into the table...");
	     
	      stmt = conn.createStatement();
	     
	      String sql =  "update Sim_Servers set Server_Name = '" + Sim_Ser_Name + "', Location = '" + Ser_Location + "' , Description = '"+Ser_Description+"' where  Server_ID = "+ SimServerID + ";";

          stmt.executeUpdate(sql);
	      
	      System.out.println("Update records into the Sim_Servers table..." + SimServerID);

	   }catch(SQLException se){
	      //Handle errors for JDBC
	      se.printStackTrace();
	   }catch(Exception e){
	      //Handle errors for Class.forName
	      e.printStackTrace();
	   }finally{
	      //finally block used to close resources
	      try{
	         if(stmt!=null)
	            conn.close();
	      }catch(SQLException se){
	      }// do nothing
	      try{
	         if(conn!=null)
	            conn.close();
	      }catch(SQLException se){
	         se.printStackTrace();
	      }//end finally try
	   }//end try	  
}

//this method is used to insert records to ServerSimulation Table
public static void insertServerSimulationRecord(int ServerID, int SimulationID)
{
	Connection conn = null;
	
	Statement stmt = null;
	
	try
	{
		 //Execute a query
		  DBClass objDBConnection = new DBClass();
		  
		  conn = objDBConnection.getConnection();
		  
	      //System.out.println("Inserting records into the table...");
	     
	      stmt = conn.createStatement();
	    
	      String sql = "INSERT INTO Server_Simulation " +
	                   "VALUES (LAST_INSERT_ID(),"+ ServerID  + "," + SimulationID +  ")";
	      
	      stmt.executeUpdate(sql);
	      
	      System.out.println("Inserted records into the table...");

	   }catch(SQLException se){
	      //Handle errors for JDBC
	      se.printStackTrace();
	   }catch(Exception e){
	      //Handle errors for Class.forName
	      e.printStackTrace();
	   }finally{
	      //finally block used to close resources
	      try{
	         if(stmt!=null)
	            conn.close();
	      }catch(SQLException se){
	      }// do nothing
	      try{
	         if(conn!=null)
	            conn.close();
	      }catch(SQLException se){
	         se.printStackTrace();
	      }//end finally try
	   }//end try
}

//this method is used to get the Simulatin_ID based on the Simulation Name
public static int GetSim_ID(String Sim_Name)
{
	int Sim_ID = 0;
	
	Connection conn = null;
	
	Statement stmt = null;
	
	try
	   {
	      //Execute a query
		  DBClass objDBConnection = new DBClass();
		  
		  conn = objDBConnection.getConnection();
	     
		  stmt = conn.createStatement();
	      
		  String sql = "select Simulation_ID from Simulations where Simulation_Name = '" + Sim_Name + "';";
	     
		  ResultSet rs = stmt.executeQuery(sql);
		  
		  while(rs.next())
		  {
			  Sim_ID = rs.getInt("Simulation_ID");
		  }
	   }
	 catch(Exception ex)
	   {
		   System.out.print(ex.toString());
	   }
	
	return Sim_ID;
		  
}

//this method is used to get the Simulatin_ID based on the Simulation Name
public static int GetSimServer_ID(String SimServer_Name)
{
	int SimServer_ID = 0;
	
	Connection conn = null;
	
	Statement stmt = null;
	
	try
	   {
	      //Execute a query
		  DBClass objDBConnection = new DBClass();
		  
		  conn = objDBConnection.getConnection();
	     
		  stmt = conn.createStatement();
	      
		  String sql = "Select Server_ID from Sim_Servers where Server_Name = '" + SimServer_Name + "';";
	     
		  ResultSet rs = stmt.executeQuery(sql);
		  
		  while(rs.next())
		  {
			  SimServer_ID = rs.getInt("Server_ID");
		  }
	   }
	 catch(Exception ex)
	   {
		   System.out.print(ex.toString());
	   }
	
	return SimServer_ID;
}

public static String GetSim_DownloadURL(String Sim_Name)
{
	String outputURL = null;
	
	Connection conn = null;
	
	Statement stmt = null;
	
	try
	   {
	      //Execute a query
		  DBClass objDBConnection = new DBClass();
		  
		  conn = objDBConnection.getConnection();
	     
		  stmt = conn.createStatement();
	      
		  String sql = "select DownloadURL from Simulations where Simulation_Name = '" + Sim_Name + "';";
	     
		  ResultSet rs = stmt.executeQuery(sql);
		  
		  while(rs.next())
		  {
			  outputURL = rs.getString("DownloadURL");
		  }
	   }
	 catch(Exception ex)
	   {
		   System.out.print(ex.toString());
	   }
	
	return outputURL;
		  
}

public static void insertInputParameterRecord(String InputData_Name, String Input_Data_Des, String Location)
{
	Connection conn = null;
	
	Statement stmt = null;
	   try
	   {
	      //Execute a query
		  DBClass objDBConnection = new DBClass();
		  
		  conn = objDBConnection.getConnection();
	    
	      stmt = conn.createStatement();
	      
	      String sql = "INSERT INTO InputData " +
                  "VALUES (LAST_INSERT_ID(),"+ "'" + InputData_Name + "'" + "," + "'" + Input_Data_Des + "'"+ "," + "'" + Location + "'" + ")";
     
          stmt.executeUpdate(sql);
	      
	      System.out.println("Inserted records into the table...");

	   }catch(SQLException se){
	      //Handle errors for JDBC
	      se.printStackTrace();
	   }catch(Exception e){
	      //Handle errors for Class.forName
	      e.printStackTrace();
	   }finally{
	      //finally block used to close resources
	      try{
	         if(stmt!=null)
	            conn.close();
	      }catch(SQLException se){
	      }// do nothing
	      try{
	         if(conn!=null)
	            conn.close();
	      }catch(SQLException se){
	         se.printStackTrace();
	      }//end finally try
	   }//end try
	   System.out.println("Goodbye!");
}

public static void updateInputParameterRecord(int InputData_ID, String InputData_Name, String Input_Data_Des, String Location)
{
	Connection conn = null;
	
	Statement stmt = null;
	   try
	   {
	      //Execute a query
		  DBClass objDBConnection = new DBClass();
		  
		  conn = objDBConnection.getConnection();
		  
	      System.out.println("Updating records into the table...");
	     
	      stmt = conn.createStatement();
	     
	      String sql =  "update InputData set InputData_Name = '" + InputData_Name + "', InputData_Description = '" + Input_Data_Des + "' , Path = '"+ Location +"' where  InputData_ID = "+ InputData_ID + ";";

          stmt.executeUpdate(sql);	      
	      

	   }catch(SQLException se){
	      //Handle errors for JDBC
	      se.printStackTrace();
	   }catch(Exception e){
	      //Handle errors for Class.forName
	      e.printStackTrace();
	   }finally{
	      //finally block used to close resources
	      try{
	         if(stmt!=null)
	            conn.close();
	      }catch(SQLException se){
	      }// do nothing
	      try{
	         if(conn!=null)
	            conn.close();
	      }catch(SQLException se){
	         se.printStackTrace();
	      }//end finally try
	   }//end try	  
}

public static int GetInputPara_ID(String InputDataName)
{
	int InputPara_ID = 0;
	
	Connection conn = null;
	
	Statement stmt = null;
	
	try
	   {
	      //Execute a query
		  DBClass objDBConnection = new DBClass();
		  
		  conn = objDBConnection.getConnection();
	     
		  stmt = conn.createStatement();
	      
		  String sql = "select InputData_ID from InputData where InputData_Name = '" + InputDataName + "';";
	     
		  ResultSet rs = stmt.executeQuery(sql);
		  
		  while(rs.next())
		  {
			  InputPara_ID = rs.getInt("InputData_ID");
		  }
	   }
	 catch(Exception ex)
	   {
		   System.out.print(ex.toString());
	   }
	
	return InputPara_ID;
}

public static void insertInputDataServer(int Server_ID, int InputData_ID)
{
	Connection conn = null;
	
	Statement stmt = null;
	
	try
	{
		 //Execute a query
		  DBClass objDBConnection = new DBClass();
		  
		  conn = objDBConnection.getConnection();
		  
	      //System.out.println("Inserting records into the table...");
	     
	      stmt = conn.createStatement();
	    
	      String sql = "INSERT INTO InputData_Server " +
	                   "VALUES (LAST_INSERT_ID(),"+ Server_ID  + "," + InputData_ID +  ")";
	      
	      stmt.executeUpdate(sql);
	      
	      System.out.println("Inserted records into the table...");

	   }catch(SQLException se){
	      //Handle errors for JDBC
	      se.printStackTrace();
	   }catch(Exception e){
	      //Handle errors for Class.forName
	      e.printStackTrace();
	   }finally{
	      //finally block used to close resources
	      try{
	         if(stmt!=null)
	            conn.close();
	      }catch(SQLException se){
	      }// do nothing
	      try{
	         if(conn!=null)
	            conn.close();
	      }catch(SQLException se){
	         se.printStackTrace();
	      }//end finally try
	   }//end try
}

public static ChoiceBox<Sim_Server_ComboItems> populateSimServe_Items()
{
	ChoiceBox<Sim_Server_ComboItems> cmb = null;
	
	Connection conn = null;
	
	Statement stmt = null;
	
	try
	   {
	      //Execute a query
		  DBClass objDBConnection = new DBClass();
		  
		  conn = objDBConnection.getConnection();
	     
		  stmt = conn.createStatement();
	      
		  String sql = "select Server_ID, Server_Name from Sim_Servers;";
	     
		  ResultSet rs = stmt.executeQuery(sql);
		  
		  cmb = new ChoiceBox<Sim_Server_ComboItems>();
		  
		  while(rs.next())
		  {
			  int Ser_ID = rs.getInt("Server_ID");
			  
			  String ServerName = rs.getString("Server_Name");
			 
			  cmb.getItems().add(new Sim_Server_ComboItems(Ser_ID, ServerName));
		   }
	   }
	   catch(Exception ex)
	   {
		   System.out.print(ex.toString());
	   }
	
	return cmb;
	
}

public static ChoiceBox<Sim_ComboItems> populateSim_Items(String SimulationServer)
{
	ChoiceBox<Sim_ComboItems> cmb = null;
	
	Connection conn = null;
	
	Statement stmt = null;
	
	try
	   {
	      //Execute a query
		  DBClass objDBConnection = new DBClass();
		  
		  conn = objDBConnection.getConnection();
	     
		  stmt = conn.createStatement();
	      
		  String sql = "select Simulation_Name,AccessURL from Simulations where Simulation_ID IN (Select Simulation_ID from Server_Simulation inner join Sim_Servers on Server_Simulation.Server_ID = Sim_Servers.Server_ID where Sim_Servers.Server_Name = '" + SimulationServer + "');";
	     
		  ResultSet rs = stmt.executeQuery(sql);
		  
		  cmb = new ChoiceBox<Sim_ComboItems>();
		  
		  while(rs.next())
		  {
			  String Sim_Name = rs.getString("Simulation_Name");
			  
			  String Access_URL = rs.getString("AccessURL");
			 
			  cmb.getItems().add(new Sim_ComboItems(Sim_Name, Access_URL));
		   }
	   }
	   catch(Exception ex)
	   {
		   System.out.print(ex.toString());
	   }
	
	return cmb;
	
}

public static ChoiceBox<InputScenario_ComboItems> populateInputScenario_Items(String SimulationServer)
{
	ChoiceBox<InputScenario_ComboItems> cmb = null;
	
	Connection conn = null;
	
	Statement stmt = null;
	
	try
	   {
	      //Execute a query
		  DBClass objDBConnection = new DBClass();
		  
		  conn = objDBConnection.getConnection();
	     
		  stmt = conn.createStatement();
	      
		  String sql = "select InputData_Name,Path from InputData where InputData_ID IN (Select InputData_ID from InputData_Server inner join Sim_Servers on InputData_Server.Server_ID = Sim_Servers.Server_ID where Sim_Servers.Server_Name = '" + SimulationServer + "');";
	     
		  ResultSet rs = stmt.executeQuery(sql);
		  
		  cmb = new ChoiceBox<InputScenario_ComboItems>();
		  
		  while(rs.next())
		  {
			  String InputData_Name = rs.getString("InputData_Name");
			  
			  String Path = rs.getString("Path");
			 
			  cmb.getItems().add(new InputScenario_ComboItems(InputData_Name, Path));
		   }
	   }
	   catch(Exception ex)
	   {
		   System.out.print(ex.toString());
	   }
	
	return cmb;
	
}

public static ObservableList<SimulationServermaster> buildData()
{
	ObservableList<SimulationServermaster> data = FXCollections.observableArrayList();
	
	Connection con = null;
	
	Statement stmt = null;
	
	try
	{
		DBClass objDBConnection = new DBClass();
		
		con = objDBConnection.getConnection();
		
		stmt = con.createStatement();
		
		String SQL = "Select * from Sim_Servers";
		
		ResultSet rs = stmt.executeQuery(SQL);
		
		while(rs.next())
		{
			SimulationServermaster sm = new SimulationServermaster();
			sm.Server_ID.set(rs.getInt("Server_ID"));
			sm.Server_Name.set(rs.getString("Server_Name"));
			sm.Location.set(rs.getString("Location"));
			sm.Description.set(rs.getString("Description"));
			data.add(sm);			
		}
	}
	catch(Exception e)
	{
        e.printStackTrace();
        System.out.println("Error on Building Data");            
    }
	finally
	{
	      //finally block used to close resources
	      try
	      {
	    	  if(stmt!=null)
	          con.close();
	      }catch(SQLException se)
	      {
	      }// do nothing
	}
	return data;
}


}
