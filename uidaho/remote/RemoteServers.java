package edu.uidaho.remote;


import edu.utah.sci.cyclist.core.ui.components.ViewBase;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.control.Tab;
import javafx.scene.control.TabPane;
import javafx.scene.Group;
import javafx.scene.Scene;

import java.util.ArrayList;

public class RemoteServers  extends ViewBase
{
	
	GridPane gpRemoteServers = new GridPane();
	
	Label lblstatus; 
	
	public static final String TITLE = "Manage Remote Servers";
	
	public RemoteServers()
	{
		super();
		
		gpRemoteServers.setHgap(4);
		
		gpRemoteServers.setVgap(4);
		
		gpRemoteServers.setPadding(new Insets(10, 10, 10, 10));		
		
		init();
	}
	/**
	 * Adds the GridPane and input nodes to the simulationInfo view.
	 */
	public void init()
	{
		setTitle(TITLE);
		
		Group root = new Group();
		
		Scene scene = new Scene(root,500,350);
		
		TextField txtSim_Id;
		
		TextField txtSimServer_id;
		
		

		
		
		TabPane tabPane = new TabPane();  //main TabPane
		
		//////////////////Simulation Tab///////////////////////////
		//********************************************************//
		
		
		
		GridPane gpSimulation = new GridPane();
		
		gpSimulation.setHgap(2);
		
		gpSimulation.setVgap(2);
		
		gpSimulation.setPadding(new Insets(10, 10, 10, 10));
		
		Tab Simulation_Tab = new Tab(); //Simulation Tab
		
		Simulation_Tab.setText("Simulation Server");
		
		Simulation_Tab.setClosable(false);
				
		Simulation_Tab.setContent(gpSimulation);
	
		tabPane.getTabs().add(Simulation_Tab);
		
		//////////////////////Input Parameters///////////////////////
		//********************************************************//
		
		GridPane gpInputPara = new GridPane();
		gpInputPara.setHgap(6);		
		gpInputPara.setVgap(6);		
		gpInputPara.setPadding(new Insets(10, 10, 10, 10));
		
		Tab InputParameter_Tab = new Tab();
		
		InputParameter_Tab.setText("Input Paramters");
		
		InputParameter_Tab.setContent(gpInputPara);
		
		InputParameter_Tab.setClosable(false);
		
		tabPane.getTabs().add(InputParameter_Tab);
		
		//////////////////////View Simulations///////////////////////
		//********************************************************//
		
		GridPane gpGetSimulation = new GridPane();
		gpGetSimulation.setHgap(10);		
		gpGetSimulation.setVgap(10);
		
		gpGetSimulation.setPadding(new Insets(10, 10, 10, 10));
		
		Tab ViewSim_Tab = new Tab();
		
		ViewSim_Tab.setText("View Simulations");
		
		ViewSim_Tab.setClosable(false);
		
		ViewSim_Tab.setContent(gpGetSimulation);
		
		tabPane.getTabs().add(ViewSim_Tab);
		
//////////////////////////////////////////////////////////////////////////////////////////
////////////////////////////////////////////////////////////////////////////////////////
		
		gpSimulation.add(new Label("Server Name (*)"), 0, 0);
		final TextField txtSumilation_Server = new TextField();
		gpSimulation.add(txtSumilation_Server, 1, 0);
		
		gpSimulation.add(new Label("Geographic Location"), 0, 1);
		final TextField txt_location = new TextField();
		gpSimulation.add(txt_location, 1, 1);
		
		gpSimulation.add(new Label("Description"), 0, 2);
		final TextField txtServer_Des = new TextField();
		gpSimulation.add(txtServer_Des, 1, 2);
						
		gpSimulation.add(new Label("Simulation Name (*)"), 0, 3);
		final TextField txtSimName = new TextField();
		gpSimulation.add(txtSimName, 1, 3);
		
		gpSimulation.add(new Label("Simulation Version "), 0, 4);
		final TextField txtSimVersion = new TextField();
		gpSimulation.add(txtSimVersion, 1, 4);
		
		gpSimulation.add(new Label("Simulation Description"), 0, 5);
		final TextField txtSimDescription = new TextField();
		gpSimulation.add(txtSimDescription, 1, 5);
		
		gpSimulation.add(new Label("Access URL (*)"), 0, 6);
		final TextField txtURL = new TextField();
		gpSimulation.add(txtURL, 1, 6);
		
		gpSimulation.add(new Label("Download Output URL"), 0, 7);
		final TextField txtOutputURL = new TextField();
		gpSimulation.add(txtOutputURL, 1, 7);
		
		txtSim_Id = new TextField();
		txtSimServer_id = new TextField();
		
		//Save Button
		// Store the Simulation details
		// Store the Server_Simulation details
		Button btnSimulationSave = new Button("Save");
		gpSimulation.add(btnSimulationSave, 0, 8);
		
		btnSimulationSave.setOnAction(new EventHandler<ActionEvent>(){
					@Override
					public void handle(ActionEvent event)
					{
						if(txtSumilation_Server.getText().trim().length()==0||txtSimName.getText().trim().length()==0||txtURL.getText().trim().length()==0)
						{
							lblstatus = new Label();
							lblstatus.setText("Warning! Reuired filed(s) are empty!");
							gpSimulation.add(lblstatus, 1, 10);
						}
						else
						{

							if(btnSimulationSave.getText()=="Save")
							{
								DBOperations.insertSimServerRecord(txtSumilation_Server.getText(), txt_location.getText(), txtServer_Des.getText());
								
								DBOperations.insertSimulationRecord(txtSimName.getText(), txtSimVersion.getText(), txtSimDescription.getText(),txtURL.getText(),txtOutputURL.getText());
								
								int SimServerID = DBOperations.GetSimServer_ID(txtSumilation_Server.getText());
								
								int SimulationID = DBOperations.GetSim_ID(txtSimName.getText());
								
								String SimServerIDs = new Integer(SimServerID).toString();
								
								String SimulationIDs = new Integer(SimulationID).toString();
								
								txtSim_Id.setText(SimulationIDs);
								
								txtSimServer_id.setText(SimServerIDs);
								
								DBOperations.insertServerSimulationRecord(SimServerID, SimulationID);
		    					
								btnSimulationSave.setText("Update");
								
								lblstatus = new Label(txtSimName.getText() + "has been stored");
								
								gpSimulation.add(lblstatus, 0, 10);
								
							}
							else if(btnSimulationSave.getText()=="Update")
							{
								DBOperations.updateSimServerRecord(Integer.parseInt(txtSimServer_id.getText()), txtSumilation_Server.getText(), txt_location.getText(), txtServer_Des.getText());
								
								DBOperations.updateSimulationRecord(Integer.parseInt(txtSim_Id.getText()),txtSimName.getText(), txtSimVersion.getText(), txtSimDescription.getText(),txtURL.getText(),txtOutputURL.getText());
								
								gpSimulation.getChildren().remove(lblstatus);
								
								lblstatus = new Label("Records are updated");
								
								gpSimulation.add(lblstatus, 0, 10);
							}
							
						}
    				}});
						
				
				
		//Clear Button
		Button btnSimClear = new Button("Clear");
		gpSimulation.add(btnSimClear, 1, 8);
		btnSimClear.setOnAction(new EventHandler<ActionEvent>(){
							@Override
		public void handle(ActionEvent event)
				{
								clear(txtSimName);
		    					clear(txtSimVersion);
		    					clear(txtSimDescription);
		    					clear(txtSimName);
		    					clear(txtURL);	
		    					clear(txtSumilation_Server);
		    					clear(txt_location);
		    					clear(txtServer_Des);
		    					clear(txtOutputURL);
		    					clear(txtSim_Id);
		    					clear(txtSimServer_id);
		    					btnSimulationSave.setText("Save");
		    					lblstatus.setText("");
				} });
				
	
				
		tabPane.getSelectionModel().selectedIndexProperty().addListener(new ChangeListener<Number>() {
			@Override
		    public void changed(ObservableValue<? extends Number> ov, Number oldValue, Number newValue) {
				if(tabPane.getTabs().get((Integer)newValue).getText()=="Input Paramters")
				{
					TextField txtInputParameterID = new TextField();
					
					Label lblSimSeverlbl = new Label();	
					lblSimSeverlbl.setText("Server Name");
					gpInputPara.add(lblSimSeverlbl, 0, 0);
					
					Label lblInputParameterStatus = new Label();
					gpInputPara.add(lblInputParameterStatus, 1, 5);
					
					TextField txtSim_Server_Name = new TextField();					
					gpInputPara.add(txtSim_Server_Name, 1, 0);
								
					if(txtSumilation_Server.getText().trim().length() != 0)
					{ 
						
						txtSim_Server_Name.setText(txtSumilation_Server.getText());
						txtSim_Server_Name.setDisable(true);
							
					}else
					{
						txtSim_Server_Name.setDisable(false);
					}			
					
					gpInputPara.add(new Label("Input Scenario File Name [.XML] (*)"), 0, 1);
					final TextField txtParaData = new TextField();
					gpInputPara.add(txtParaData, 1, 1);
					
					gpInputPara.add(new Label("File Description"), 0, 2);
					final TextField txtParaDes = new TextField();
					gpInputPara.add(txtParaDes, 1, 2);
					
					gpInputPara.add(new Label("File path (*)"), 0, 3);
					final TextField txtParaURL = new TextField();
					gpInputPara.add(txtParaURL, 1, 3);
					
					//Save Button
					Button btnInputParaSave = new Button("Save");
					btnInputParaSave.setOnAction(new EventHandler<ActionEvent>(){
								@Override
								public void handle(ActionEvent event)
								{
									if(txtSim_Server_Name.getText().trim().length()==0 || txtParaData.getText().trim().length()==0 || txtParaURL.getText().trim().length()==0)
									{
										lblInputParameterStatus.setText("Warning! Required filed(s) are empty");
										
									}
									else 
									{
										int Sim_ServerID = DBOperations.GetSimServer_ID(txtSim_Server_Name.getText());
										
										if(Sim_ServerID == 0)
										{
											lblInputParameterStatus.setText("Your Server name is not registerd! Check your server name");
											
										}
										else
										{
											if(btnInputParaSave.getText()=="Save")
											{
												DBOperations.insertInputParameterRecord(txtParaData.getText(),txtParaDes.getText(),txtParaURL.getText());
												int InputParaID = DBOperations.GetInputPara_ID(txtParaData.getText());											
												DBOperations.insertInputDataServer(Sim_ServerID, InputParaID);
												txtInputParameterID.setText(new Integer(InputParaID).toString());
												lblInputParameterStatus.setText(txtParaData.getText() + " has been stored!");
												btnInputParaSave.setText("Update");
											}
											else
											{
												DBOperations.updateInputParameterRecord(Integer.parseInt(txtInputParameterID.getText()), txtParaData.getText(),txtParaDes.getText(),txtParaURL.getText());
												lblInputParameterStatus.setText(txtParaData.getText() + " has been updated!");
											}
										}
									}
								}});									
					gpInputPara.add(btnInputParaSave, 0, 4);
							
					
					Button btnInputParaClear = new Button("Clear");					
					btnInputParaClear.setOnAction(new EventHandler<ActionEvent>()
					{
										@Override
					public void handle(ActionEvent event)
							{
							  clear(txtParaData);
							  clear(txtParaDes);
							  clear(txtParaURL);
							  lblInputParameterStatus.setText("");
							  btnInputParaSave.setText("Save");													
							} });
							
					gpInputPara.add(btnInputParaClear, 1, 4);
				}
				else if(tabPane.getTabs().get((Integer)newValue).getText()=="View Simulations")
				{
					TableView tableview;
					
					tableview = new TableView();
					
				    DisplayDatabase.buildData(tableview);
				     
				    gpGetSimulation.add(tableview, 0, 0);
				}
		    }
		});
		
				
		BorderPane borderPane = new BorderPane();
		
		borderPane.prefHeightProperty().bind(scene.heightProperty());
		
		borderPane.prefWidthProperty().bind(scene.widthProperty());
		
		borderPane.setCenter(tabPane);
		
		root.getChildren().add(borderPane);
		
		gpRemoteServers.add(root, 0, 0);
		
		setContent(gpRemoteServers);
		
	}
	
	public TabPane CreateNewTab(TabPane rootTabPan, ArrayList<String> TabMemNames)
	{
		for(int i=0;i<TabMemNames.size();i++)
		{
			Tab NewTab = new Tab();
			
			HBox hbox = new HBox();
			
			NewTab.setText(TabMemNames.get(i));
			
			NewTab.setId(String.valueOf(i));
			
			NewTab.setClosable(false);
				
			hbox.getChildren().add(new Label(TabMemNames.get(i)));
				
			hbox.setAlignment(Pos.CENTER);
				
			NewTab.setContent(hbox);
						
			rootTabPan.getTabs().add(NewTab);
		}
		
		return rootTabPan;
	}
	
    public static void clear(TextField txt)
    {
    	txt.setText("");
    }
}
