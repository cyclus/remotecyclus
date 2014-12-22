package edu.uidaho.remote;
import java.sql.Connection;  
import java.sql.ResultSet;  
import javafx.beans.property.SimpleStringProperty;  
import javafx.beans.value.ObservableValue;  
import javafx.collections.FXCollections;  
import javafx.collections.ObservableList;  
import javafx.scene.control.TableColumn;  
import javafx.scene.control.TableColumn.CellDataFeatures;  
import javafx.scene.control.TableView;  
import javafx.util.Callback;

public class DisplayDatabase 
{
	  //Tableview and data  
	   private static ObservableList<ObservableList> data;  
	  
	   public static void buildData(TableView tableview)
	   {
		      Connection c ;  
		      data = FXCollections.observableArrayList();  
		      try{ 
		    	  DBClass obj = new DBClass();
		    	  c = obj.getConnection();  
		          String SQL = "SELECT Simulations.Simulation_Name ,Simulations.Simulation_Version,Simulations.Simulation_Description,Simulations.AccessURL,Sim_Servers.Server_Name from Simulations,Sim_Servers ;";  
		          //ResultSet  
		          ResultSet rs = c.createStatement().executeQuery(SQL);  
		          /**********************************  
		           * TABLE COLUMN ADDED DYNAMICALLY *  
		           **********************************/  
		          for(int i=0 ; i<rs.getMetaData().getColumnCount(); i++){  
		            //We are using non property style for making dynamic table  
		            final int j = i;          
		            TableColumn col = new TableColumn(rs.getMetaData().getColumnName(i+1));  
		            col.setCellValueFactory(new Callback<CellDataFeatures<ObservableList,String>,ObservableValue<String>>(){            
		              public ObservableValue<String> call(CellDataFeatures<ObservableList, String> param) {                                                 
		                return new SimpleStringProperty(param.getValue().get(j).toString());              
		              }            
		            });  
		            tableview.getColumns().addAll(col);  
		          }  
		          /********************************  
		           * Data added to ObservableList *  
		           ********************************/  
		          while(rs.next()){  
		            //Iterate Row  
		            ObservableList<String> row = FXCollections.observableArrayList();  
		            for(int i=1 ; i<=rs.getMetaData().getColumnCount(); i++){  
		              //Iterate Column  
		              row.add(rs.getString(i));  
		              
		            }  
		            //System.out.println("Row [1] added "+row );  
		            data.add(row);
		      }
		          //FINALLY ADDED TO TableView  
		          tableview.setItems(data); 
		          tableview.setColumnResizePolicy(TableView.UNCONSTRAINED_RESIZE_POLICY);
		      }
		      catch(Exception e){  
		          e.printStackTrace();  
		          System.out.println("Error on Building Data");        
		        }  
		          
	   }
       
	   public static void StoredData(TableView tableview,int Sim_ID, int Sim_Ser_ID)
	   {
		      Connection c ;  
		      
		      data = FXCollections.observableArrayList();  
		      try{ 
		    	  DBClass obj = new DBClass();
		    	  c = obj.getConnection();  
		          String SQL = "Select si.Simulation_Name,si.Simulation_Version,si.Simulation_Description,si.AccessURL,Server_Name,Location,Description from Simulations as si,Sim_Servers where si.Simulation_ID = " + Sim_ID +  " and Server_ID = " + Sim_Ser_ID + ";";  
		          //ResultSet  
		          ResultSet rs = c.createStatement().executeQuery(SQL);  
		          /**********************************  
		           * TABLE COLUMN ADDED DYNAMICALLY *  
		           **********************************/  
		          for(int i=0 ; i<rs.getMetaData().getColumnCount(); i++){  
		            //We are using non property style for making dynamic table  
		            final int j = i;          
		            TableColumn col = new TableColumn(rs.getMetaData().getColumnName(i+1));  
		            col.setCellValueFactory(new Callback<CellDataFeatures<ObservableList,String>,ObservableValue<String>>(){            
		              public ObservableValue<String> call(CellDataFeatures<ObservableList, String> param) {                                                 
		                return new SimpleStringProperty(param.getValue().get(j).toString());              
		              }            
		            });  
		            tableview.getColumns().addAll(col);  
		          }  
		          /********************************  
		           * Data added to ObservableList *  
		           ********************************/  
		          while(rs.next()){  
		            //Iterate Row  
		            ObservableList<String> row = FXCollections.observableArrayList();  
		            for(int i=1 ; i<=rs.getMetaData().getColumnCount(); i++){  
		              //Iterate Column  
		              row.add(rs.getString(i));  
		            }  
		            //System.out.println("Row [1] added "+row );  
		            data.add(row);
		      }
		          //FINALLY ADDED TO TableView  
		          tableview.setItems(data); 
		      }
		      catch(Exception e){  
		          e.printStackTrace();  
		          System.out.println("Error on Building Data");        
		        }  
		          
	   }
	   
	   
}
