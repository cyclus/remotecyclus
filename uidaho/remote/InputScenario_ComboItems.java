package edu.uidaho.remote;

public class InputScenario_ComboItems 
{
	   private String InputData_Name;
	   
	   private String Path;
	   
	   public InputScenario_ComboItems(String I_Name, String I_Path)
	   {
		   this.InputData_Name = I_Name;
		   this.Path = I_Path;
	   }   
	   
	   @Override
	   public String toString()
	   {
		  return InputData_Name; 
	   }   
	   
	   public String getPath()
	   {
		   return Path;
	   }
}
