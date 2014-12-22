package edu.uidaho.remote;

public class Sim_Server_ComboItems 
{
   private int Server_ID;
   
   private String Server_Name;
   
   public Sim_Server_ComboItems(int S_ID, String S_Name)
   {
	   this.Server_ID = S_ID;
	   this.Server_Name = S_Name;
   }   
   
   @Override
   public String toString()
   {
	  return Server_Name; 
   }   
   
   public int getKey()
   {
	   return Server_ID;
   }
}
