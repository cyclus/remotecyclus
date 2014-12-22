//this class is used to run the simulation remotely
package edu.uidaho.remote;

import java.io.*;
import java.net.*;

//this class is for running Servlet from remote servers
public class InvokeSimulation 
{
  public static void RunSimulation(String AccessURL, String InputPath) throws IOException
  {
	  URL url = new URL(AccessURL);
      
      URLConnection conn = url.openConnection();
      
      conn.setDoOutput(true);
      
      BufferedWriter out = new BufferedWriter( new OutputStreamWriter( conn.getOutputStream() ) );
      
      out.write("InputParamter=" + InputPath);
      
      out.flush();
      
      
      out.close();
          
      BufferedReader in = new BufferedReader( new InputStreamReader( conn.getInputStream() ) );
      
      String response;
      
      while ( (response = in.readLine()) != null ) 
      {
              System.out.println( response );
      }
      
      in.close();
  }
  
  public static String RunSimulationwithOutput(String AccessURL, String InputPath) throws IOException
  {
	  String output = "";
	  
	  URL url = new URL(AccessURL);
      
      URLConnection conn = url.openConnection();
      
      conn.setDoOutput(true);
      
      BufferedWriter out = new BufferedWriter( new OutputStreamWriter( conn.getOutputStream() ) );
      
      out.write("InputParamter=" + InputPath);
      
      out.flush();
      
      out.close();
          
      BufferedReader in = new BufferedReader( new InputStreamReader( conn.getInputStream() ) );
      
      String response;
      
      while ( (response = in.readLine()) != null ) 
      {
    	  output = response;
              
      }
      
      in.close();
      
      return output;
  }
  
 
  
  public static String RunSimulationwithEmail(String AccessURL, String InputPath,String Email) throws IOException
  {
	  String output = "";
	  
	  URL url = new URL(AccessURL);
      
      URLConnection conn = url.openConnection();
      
      conn.setDoOutput(true);
      
      BufferedWriter out = new BufferedWriter( new OutputStreamWriter( conn.getOutputStream() ) );
      
      out.write("InputParamter=" + InputPath + "&" +  "EmailTo=" + Email);
     
      out.flush();
      
      out.close();
          
      BufferedReader in = new BufferedReader( new InputStreamReader( conn.getInputStream() ) );
      
      String response;
      
      while ( (response = in.readLine()) != null ) 
      {
    	  output = response;
              
      }
      
      in.close();
      
      return output;
  }
  
  public static String DownLoadFile(String FileName, String DownloadURL) throws IOException
  {
	  String Download_status = null;
	  System.out.println("opening connection");
	  
      URL url = new URL(DownloadURL);
      
      InputStream in = url.openStream();
      
      FileOutputStream fos = new FileOutputStream(new File(FileName));

      System.out.println("reading file...");
      
      int length = -1;
      byte[] buffer = new byte[1024];// buffer for portion of data from
                              // connection
      
      while ((length = in.read(buffer)) > -1) 
      {
          fos.write(buffer, 0, length);
      }
      fos.close();
      
      in.close();
      
      Download_status = "file was downloaded";
      
      return Download_status;
	  
  }

  public static String RunLocalSimulation(String exePath, String InputParamter) throws IOException
  {
	 ProcessBuilder process = new ProcessBuilder();
    
     process.directory(new File(exePath));
  
     process.command("./cyclus", InputParamter);

     Process p = process.start();
    
     String output = ""; 
     
     BufferedReader input = new BufferedReader(new InputStreamReader(p.getInputStream())); 
      
          String line;
          
          while((line=input.readLine()) != null)
          {   
        	  output =  "Cyclus run successful" + "\n" + line;        	  
          }     
          
          input.close();          
          return output;
    }
  
}
