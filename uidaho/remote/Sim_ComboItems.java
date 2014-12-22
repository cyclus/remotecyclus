package edu.uidaho.remote;

public class Sim_ComboItems
{
	   private String Simulation_Name;
	   
	   private String AccessURL;
	   
	   public Sim_ComboItems(String S_Name, String S_AccessURL)
	   {
		   this.Simulation_Name = S_Name;
		   this.AccessURL = S_AccessURL;
	   }   
	   
	   @Override
	   public String toString()
	   {
		  return Simulation_Name; 
	   }   
	   
	   public String getAccessURL()
	   {
		   return AccessURL;
	   }
}
