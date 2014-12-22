package edu.uidaho.remote;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLConnection;

import edu.utah.sci.cyclist.core.ui.components.ViewBase;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;
import javafx.stage.DirectoryChooser;
import javafx.stage.FileChooser;

public class Remote  extends ViewBase
{
	GridPane gpHelloWorld = new GridPane();
	
	//the following 4 items are being used in the local simulation process GUI
	Button btnlocalFileBrowser;
	Label lbllocalSimulation;
	Button btnlocalInputFileBrowser;
	Label lbllocalInputScenario;
	Button btnlocalRun;
	Label lbllocalStatus;
	
	//the following items are related to MetaData Server GUI
	Label lblMDSimServer;
	Label lblMDSimulation;
	Label lblMDInputScenario;	
	ChoiceBox<Sim_ComboItems> MDSimulations;
	ChoiceBox<InputScenario_ComboItems> MDInputDataScenario;
	ChoiceBox<Sim_Server_ComboItems> MDSim_ServerSource;
	CheckBox MDchbox;
	Label lblMDResults;
	Label lblMDStatus;
	Button btnMDRun;
	Label lblMDEmail;
	TextField txtMDEmail;
	
	//the following items are related to add new server
	Label lblANServer;
	TextField txtANServer;
	Label lblANInputScenarioFileChoice;
    ChoiceBox cbANInputScenarioFile;
    Label lblANRemoteInputScenarioFile;
    TextField txtANInputScenarioFile;
    CheckBox chbANchboxDownload;
    CheckBox chbANchboxSaveDetails;
    Label lblANResults;
	Label lblANStatus;
	Button btnANRun;
	Button btnANBrowseLocalFile;
	Label lblANLocalBrowsedFile;
	Label lblANUploadULR;
	TextField txtANUploadURL;
	Label lblANEmail;
	TextField txtANEmail;

	String localuploadedFile; 
	
	String tt;
	
	String yy;
	
	boolean localfileflag = false;
	
	boolean Remotefileflag = false;
	
	
	public static final String TITLE = "Run Simulation";
	
	public Remote()
	{
		super();
		
		gpHelloWorld.setHgap(4);
		
		gpHelloWorld.setVgap(4);
		
		gpHelloWorld.setPadding(new Insets(10, 10, 10, 10));		
		
		init();
	}	
			
	/**
	 * Adds the GridPane and input nodes to the simulationInfo view.
	 */
	public void init()
	{
		setTitle(TITLE);
		
		gpHelloWorld.add(new Label("Method of Simulation Invocation"), 0, 0);
		
		ChoiceBox cb = new ChoiceBox();
		
		cb.setItems(FXCollections.observableArrayList("Local System","Metadata Server","Remote Server (Special User)"));
		
		gpHelloWorld.add(cb, 1, 0);
		
		cb.getSelectionModel().selectedIndexProperty().addListener(new ChangeListener<Number>() {
			@Override
			public void changed(ObservableValue<? extends Number> OV, Number num1, Number num2)
			{
				if(cb.getItems().get((Integer)num2) == "Local System")
				{

					gpHelloWorld.getChildren().remove(MDSim_ServerSource);
					gpHelloWorld.getChildren().remove(lblMDSimServer);
					gpHelloWorld.getChildren().remove(lblMDSimulation);
					gpHelloWorld.getChildren().remove(lblMDInputScenario);
					gpHelloWorld.getChildren().remove(MDSimulations);
					gpHelloWorld.getChildren().remove(MDInputDataScenario);
					gpHelloWorld.getChildren().remove(lblMDResults);
					gpHelloWorld.getChildren().remove(lblMDStatus);
					gpHelloWorld.getChildren().remove(btnMDRun);
					gpHelloWorld.getChildren().remove(MDchbox);
					gpHelloWorld.getChildren().remove(lblMDEmail);
					gpHelloWorld.getChildren().remove(txtMDEmail);
					
					gpHelloWorld.getChildren().remove(lblANServer);
					gpHelloWorld.getChildren().remove(txtANServer);
					gpHelloWorld.getChildren().remove(lblANInputScenarioFileChoice);
					gpHelloWorld.getChildren().remove(cbANInputScenarioFile);
					gpHelloWorld.getChildren().remove(lblANRemoteInputScenarioFile);
					gpHelloWorld.getChildren().remove(txtANInputScenarioFile);
					gpHelloWorld.getChildren().remove(chbANchboxDownload);
					gpHelloWorld.getChildren().remove(chbANchboxSaveDetails);
					gpHelloWorld.getChildren().remove(lblANResults);
					gpHelloWorld.getChildren().remove(lblANStatus);
					gpHelloWorld.getChildren().remove(btnANRun);
					gpHelloWorld.getChildren().remove(btnANBrowseLocalFile);
					gpHelloWorld.getChildren().remove(lblANLocalBrowsedFile);
					gpHelloWorld.getChildren().remove(lblANUploadULR);
					gpHelloWorld.getChildren().remove(txtANUploadURL);
					gpHelloWorld.getChildren().remove(lblANEmail);
					gpHelloWorld.getChildren().remove(txtANEmail);
					
					
					btnlocalFileBrowser = new Button();
					btnlocalFileBrowser.setText("Simulation Path");
					gpHelloWorld.add(btnlocalFileBrowser, 0, 1);
					
					lbllocalSimulation = new Label();
					lbllocalSimulation.setText(".....");
					gpHelloWorld.add(lbllocalSimulation, 1, 1);
					
					btnlocalFileBrowser.setOnAction(new EventHandler<ActionEvent>() {
						
						@Override
						public void handle(ActionEvent event) {
							  
							 
							  DirectoryChooser chooser = new DirectoryChooser();
							  chooser.setTitle("Choose cyclus path");
							  File Selected = chooser.showDialog(null);
							  
							  if(Selected!=null)
							  {
								  lbllocalSimulation.setText(Selected.getPath());
							  }						
			              }
					});
					
					
					btnlocalInputFileBrowser = new Button();
					btnlocalInputFileBrowser.setText("Input Scenario File (.XML)");
					gpHelloWorld.add(btnlocalInputFileBrowser, 0, 2);
					
					lbllocalInputScenario = new Label();
					lbllocalInputScenario.setText(".....");
					gpHelloWorld.add(lbllocalInputScenario, 1, 2);
					
					btnlocalInputFileBrowser.setOnAction(new EventHandler<ActionEvent>() {
						
						@Override
						public void handle(ActionEvent event) {
							FileChooser fileChooser = new FileChooser();

			                  fileChooser.setTitle("Select Input Scenario File");

			                  File file = fileChooser.showOpenDialog(null);
			                 if(file!=null)

			                  lbllocalInputScenario.setText(file.getPath());
							
						}
					});
					
					btnlocalRun = new Button();
					btnlocalRun.setText("Run");
					gpHelloWorld.add(btnlocalRun, 0, 3);
					lbllocalStatus = new Label();
					gpHelloWorld.add(lbllocalStatus,1,3);
					btnlocalRun.setOnAction(new EventHandler<ActionEvent>() {
						
						@Override
						public void handle(ActionEvent event) {
						try {
							if(lbllocalSimulation.getText()!="....." || lbllocalInputScenario.getText()!=".....")
							{
								String result = InvokeSimulation.RunLocalSimulation(lbllocalSimulation.getText(), lbllocalInputScenario.getText());
								lbllocalStatus.setText(result);
							}
							else
							{
								lbllocalStatus.setText("Error: You need to select the file path(s)");
							}
								
							
						} catch (IOException e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
							lbllocalStatus.setText(e.toString());
						}			
							
						}
					});
					
				}
				else if(cb.getItems().get((Integer)num2) == "Metadata Server")
				{
					gpHelloWorld.getChildren().remove(lbllocalSimulation);
					gpHelloWorld.getChildren().remove(btnlocalFileBrowser);
					gpHelloWorld.getChildren().remove(btnlocalInputFileBrowser);
					gpHelloWorld.getChildren().remove(lbllocalInputScenario);
					gpHelloWorld.getChildren().remove(btnlocalRun);
					gpHelloWorld.getChildren().remove(lbllocalStatus);
					
					gpHelloWorld.getChildren().remove(lblANServer);
					gpHelloWorld.getChildren().remove(txtANServer);
					gpHelloWorld.getChildren().remove(lblANInputScenarioFileChoice);
					gpHelloWorld.getChildren().remove(cbANInputScenarioFile);
					gpHelloWorld.getChildren().remove(lblANRemoteInputScenarioFile);
					gpHelloWorld.getChildren().remove(txtANInputScenarioFile);
					gpHelloWorld.getChildren().remove(chbANchboxDownload);
					gpHelloWorld.getChildren().remove(chbANchboxSaveDetails);
					gpHelloWorld.getChildren().remove(lblANResults);
					gpHelloWorld.getChildren().remove(lblANStatus);
					gpHelloWorld.getChildren().remove(btnANRun);
					gpHelloWorld.getChildren().remove(btnANBrowseLocalFile);
					gpHelloWorld.getChildren().remove(lblANLocalBrowsedFile);	
					gpHelloWorld.getChildren().remove(lblANUploadULR);
					gpHelloWorld.getChildren().remove(txtANUploadURL);
					gpHelloWorld.getChildren().remove(lblANEmail);
					gpHelloWorld.getChildren().remove(txtANEmail);
					
					
					lblMDSimServer = new Label();
					lblMDSimServer.setText("Simulation Server");
					gpHelloWorld.add(lblMDSimServer, 0, 1);
					
					MDSim_ServerSource = DBOperations.populateSimServe_Items();
				    gpHelloWorld.add(MDSim_ServerSource, 1, 1);					
					MDSim_ServerSource.getSelectionModel().selectedIndexProperty().addListener(new ChangeListener<Number>() {
					@Override
					public void changed(ObservableValue<? extends Number> OV, Number num1, Number num2)
					{
						    String SelectedSim_Server = (MDSim_ServerSource.getItems().get((Integer)num2)).toString();
							
							MDSimulations = DBOperations.populateSim_Items(SelectedSim_Server);							
							MDInputDataScenario = DBOperations.populateInputScenario_Items(SelectedSim_Server); 
							
							lblMDSimulation = new Label();
							lblMDSimulation.setText("Simulation");
							gpHelloWorld.add(lblMDSimulation, 0, 2);							
							gpHelloWorld.add(MDSimulations, 1, 2);
							
							lblMDInputScenario = new Label();
							lblMDInputScenario.setText("Input Scenario");
							gpHelloWorld.add(lblMDInputScenario, 0, 3);
							gpHelloWorld.add(MDInputDataScenario, 1, 3);
							
							lblMDEmail = new Label();
							lblMDEmail.setText("E-mail Address");
							gpHelloWorld.add(lblMDEmail, 0, 4);
							
							txtMDEmail = new TextField();
							gpHelloWorld.add(txtMDEmail, 1, 4);
							
							MDchbox = new CheckBox("Download output");					
							gpHelloWorld.add(MDchbox, 0, 5);
							
							lblMDResults = new Label();
							
							lblMDStatus = new Label();
									
							//Submit Button
							btnMDRun = new Button("Run");
							
							btnMDRun.setOnAction(new EventHandler<ActionEvent>(){
								@Override
								public void handle(ActionEvent event)
								{
									try 
									{
										
										String AccessURL = MDSimulations.getValue().getAccessURL();
										
										String InputScenarioPath = MDInputDataScenario.getValue().getPath();
										
										if(AccessURL != null && InputScenarioPath !=null )
										{
											Thread simulationThread = new Thread(){
												public void run()
												{
													try
													{
													  if(txtMDEmail.getText().equals(""))
													  {
														  lblMDResults.setText((InvokeSimulation.RunSimulationwithOutput(AccessURL, InputScenarioPath)));
													  }
													  else
													  {
													     lblMDResults.setText((InvokeSimulation.RunSimulationwithEmail(AccessURL, InputScenarioPath,txtMDEmail.getText())) +  "\n" + "And email has been sent to " + txtMDEmail.getText()+ " with download link");
													  } 
													}
													catch (IOException e) {
														// TODO Auto-generated catch block
														e.printStackTrace();
													}
												}
											};
											
											simulationThread.start();
											
											simulationThread.join();
												
											gpHelloWorld.add(lblMDStatus, 1, 6);
										
											gpHelloWorld.add(lblMDResults, 1, 7);	
										
											if(MDchbox.isSelected())
											{
											  FileChooser fileChooser = new FileChooser();						  
								              fileChooser.setTitle("Choose the location to store the simulation output");
											
								              //Show save file dialog
											  java.io.File f1 = fileChooser.showSaveDialog(null);
								              
								              
								              if(f1 != null)
								              {
								            	 String DownloadURL =  DBOperations.GetSim_DownloadURL(MDSimulations.getValue().toString());
								            	 String FilePath = f1.toString() + ".sqlite";
								            	 String downloadStatus =  InvokeSimulation.DownLoadFile(FilePath,DownloadURL);
								            	 lblMDStatus.setText(downloadStatus);
								              }
											}
										}
									} 
									
									catch (Exception e) 
									{
										// TODO Auto-generated catch block
										e.printStackTrace();
									}	
								}
							});	
							
							gpHelloWorld.add(btnMDRun, 0, 7);
							}
					});					
					
				}
				else if(cb.getItems().get((Integer)num2) == "Remote Server (Special User)")
				{
					gpHelloWorld.getChildren().remove(MDSim_ServerSource);
					gpHelloWorld.getChildren().remove(lblMDSimServer);
					gpHelloWorld.getChildren().remove(lblMDSimulation);
					gpHelloWorld.getChildren().remove(lblMDInputScenario);
					gpHelloWorld.getChildren().remove(MDSimulations);
					gpHelloWorld.getChildren().remove(MDInputDataScenario);
					gpHelloWorld.getChildren().remove(lblMDResults);
					gpHelloWorld.getChildren().remove(lblMDStatus);
					gpHelloWorld.getChildren().remove(btnMDRun);
					gpHelloWorld.getChildren().remove(MDchbox);
					gpHelloWorld.getChildren().remove(lblMDEmail);
					gpHelloWorld.getChildren().remove(txtMDEmail);
					gpHelloWorld.getChildren().remove(lbllocalSimulation);
					gpHelloWorld.getChildren().remove(btnlocalFileBrowser);
					gpHelloWorld.getChildren().remove(btnlocalInputFileBrowser);
					gpHelloWorld.getChildren().remove(lbllocalInputScenario);
					gpHelloWorld.getChildren().remove(btnlocalRun);
					gpHelloWorld.getChildren().remove(lbllocalStatus);
					
					lblANServer = new Label();
					lblANServer.setText("Simulation URL (*)");
					gpHelloWorld.add(lblANServer, 0, 1);
					
					txtANUploadURL = new TextField();
					txtANServer = new TextField();
					gpHelloWorld.add(txtANServer, 1, 1);
					
					lblANInputScenarioFileChoice = new Label();
					lblANInputScenarioFileChoice.setText("Input Scenario File Options");
					gpHelloWorld.add(lblANInputScenarioFileChoice, 0, 2);
					
					cbANInputScenarioFile = new ChoiceBox();
					
					cbANInputScenarioFile.setItems(FXCollections.observableArrayList("Local File","Remote File"));
					gpHelloWorld.add(cbANInputScenarioFile, 1, 2);					
					
					cbANInputScenarioFile.getSelectionModel().selectedIndexProperty().addListener(new ChangeListener<Number>() {
						@Override
						public void changed(ObservableValue<? extends Number> OV, Number num1, Number num2)
						{
							if(cbANInputScenarioFile.getItems().get((Integer)num2) == "Local File")
							{
								gpHelloWorld.getChildren().remove(lblANRemoteInputScenarioFile);
								gpHelloWorld.getChildren().remove(txtANInputScenarioFile);
								lblANStatus.setText(null);
								lblANLocalBrowsedFile = new Label();
								gpHelloWorld.add(lblANLocalBrowsedFile, 1, 3);
								
								btnANBrowseLocalFile = new Button();
								btnANBrowseLocalFile.setText("Input Scenario File (.XML) (*)");
								gpHelloWorld.add(btnANBrowseLocalFile, 0, 3);


								 lblANUploadULR = new Label();
								 lblANUploadULR.setText("Upload server URL (*)");
								 
								  
								  gpHelloWorld.add(lblANUploadULR,0,4);
								  gpHelloWorld.add(txtANUploadURL,1,4);
								  
								btnANBrowseLocalFile.setOnAction(new EventHandler<ActionEvent>() {
									
									@Override
									public void handle(ActionEvent event) 
									{
										FileChooser fileChooser = new FileChooser();

								        fileChooser.setTitle("Select Input Scenario File");

							
								  //Show open file dialog

								       File file = fileChooser.showOpenDialog(null);
								      if(file!=null)

								           lblANLocalBrowsedFile.setText(file.getPath());
								 
								      localfileflag = true;
								      Remotefileflag = false;
								  
								  tt = txtANUploadURL.getText();
								  yy = lblANLocalBrowsedFile.getText();
									
								  }
									
								});
								
								
							}
							else if(cbANInputScenarioFile.getItems().get((Integer)num2) == "Remote File")
							{
								gpHelloWorld.getChildren().remove(btnANBrowseLocalFile);
								gpHelloWorld.getChildren().remove(lblANLocalBrowsedFile);
								gpHelloWorld.getChildren().remove(lblANUploadULR);
								gpHelloWorld.getChildren().remove(txtANUploadURL);
								
								lblANStatus.setText(null);
								
								lblANRemoteInputScenarioFile = new Label();
								lblANRemoteInputScenarioFile.setText("Input Scenario Path (*)");
								gpHelloWorld.add(lblANRemoteInputScenarioFile, 0, 3);
								
								 localfileflag = false;
							     Remotefileflag = true;
							      
								txtANInputScenarioFile = new TextField();
								gpHelloWorld.add(txtANInputScenarioFile,1,3);
							}
						}
						}); // end of choicebox event
					
						
				    
					
					lblANEmail = new Label();
					lblANEmail.setText("E-mail address (*)");
					gpHelloWorld.add(lblANEmail,0,5);
					txtANEmail = new TextField();
					gpHelloWorld.add(txtANEmail, 1, 5);
					
					chbANchboxDownload = new CheckBox();
					chbANchboxDownload.setText("Download output");
					//gpHelloWorld.add(chbANchboxDownload,0,6);
					
					btnANRun = new Button();
					btnANRun.setText("Run");
					gpHelloWorld.add(btnANRun, 0, 7);
			        lblANStatus = new Label();
			        gpHelloWorld.add(lblANStatus, 1, 7);
					
					btnANRun.setOnAction(new EventHandler<ActionEvent>() {
					@Override
					public void handle(ActionEvent event) 
					{
						if(txtANServer.getText().equals("")||txtANEmail.getText().equals(""))
						{
							lblANStatus.setText("Required fields are empty");
						}
						
						if(localfileflag==true && Remotefileflag==false)
						{
							//in the start I clear the status to empty string
							lblANStatus.setText(null);
							//The following code is for uploading file in the server
							//The reason I keep it in thread to make sure that
							//"This event has to complete FIRST and then simulation will run"
							Thread FileUploadThread = new Thread(){
								public void run()
								{
									try {
										  
									     String boundary;
										 String LINE_FEED = "\r\n";
										 HttpURLConnection httpConn;
								         OutputStream outputStream;
									     PrintWriter writer;
										 File uploadFile = new File(yy);
										 
										 boundary = "===" + System.currentTimeMillis() + "===";

										URL url = new URL(txtANUploadURL.getText());
										httpConn = (HttpURLConnection) url.openConnection();
										httpConn.setDoOutput(true); // indicates POST method
										httpConn.setDoInput(true);
										httpConn.setRequestProperty("Content-Type",
												"multipart/form-data; boundary=" + boundary);
										outputStream = httpConn.getOutputStream();
										writer = new PrintWriter(new OutputStreamWriter(outputStream, "UTF-8"),true);
										
										
									 
									   // Add file parts
										String fileName = uploadFile.getName();
										writer.append("--" + boundary).append(LINE_FEED);
										writer.append(
												"Content-Disposition: form-data; name=\"" + uploadFile
														+ "\"; filename=\"" + fileName + "\"")
												.append(LINE_FEED);
										writer.append(
												"Content-Type: "
														+ URLConnection.guessContentTypeFromName(fileName))
												.append(LINE_FEED);
										writer.append("Content-Transfer-Encoding: binary").append(LINE_FEED);
										writer.append(LINE_FEED);
										writer.flush();
										
										
										FileInputStream inputStream = new FileInputStream(uploadFile);
										byte[] buffer = new byte[4096];
										int bytesRead = -1;
										
										while ((bytesRead = inputStream.read(buffer)) != -1) 
										{
											outputStream.write(buffer, 0, bytesRead);
								        }

										inputStream.close();
										
										outputStream.flush();
										writer.append(LINE_FEED);
										writer.flush();

										writer.append(LINE_FEED).flush();
										writer.append("--" + boundary + "--").append(LINE_FEED);
										writer.close();
										
										
										
										// check server's status code first
										int status = httpConn.getResponseCode();
										
										if (status == HttpURLConnection.HTTP_OK) 
										{
											 BufferedReader input = new BufferedReader(new InputStreamReader(httpConn.getInputStream())); 
										      
									          String line;
									          
									          while((line=input.readLine()) != null)
									          {   
									        	  localuploadedFile = line+"/" + fileName;
									          }     
									         input.close();  
											 httpConn.disconnect();
										} else {
											throw new IOException("Server returned non-OK status: " + status);
										}
									} catch (Exception e) {}
									
								}};
								
								FileUploadThread.start();
								
								try {
									FileUploadThread.join();
								} catch (InterruptedException e) {
									
								}
								
								String Status;
								try {
									if(txtANServer.getText().equals("") || localuploadedFile == "" ||txtANEmail.getText().equals(""))
									{
										lblANStatus.setText("Warning! Required fields are empty!");
									}
									else
									{
									Status = InvokeSimulation.RunSimulationwithEmail(txtANServer.getText(), localuploadedFile,txtANEmail.getText());
									lblANStatus.setText(Status + "\n" + "And email has been sent to " + txtANEmail.getText()+ " with download link");
									}
									} catch (IOException e) {
									// TODO Auto-generated catch block
									e.printStackTrace();
								}
							
						}
						else if(localfileflag==false && Remotefileflag==true)
						{
							 try 
							 {
								lblANStatus.setText(null);
								if(txtANServer.getText().equals("") || txtANInputScenarioFile.getText().equals("") ||txtANEmail.getText().equals(""))
								{
									lblANStatus.setText("Warning! Required fileds are empty");
								}
								else
								{
								String Status = InvokeSimulation.RunSimulationwithEmail(txtANServer.getText(), txtANInputScenarioFile.getText(),txtANEmail.getText());
								lblANStatus.setText(Status + "\n" + "And email has been sent to " + txtANEmail.getText()+ " with download link");
								}
								} catch (IOException e) {
								// TODO Auto-generated catch block
								e.printStackTrace();
							}
							 
						}					
			        }
					});					
				}
				
				System.out.print(cb.getItems().get((Integer)num2));
						
			}
			});
		
		setContent(gpHelloWorld);
	}
	
    
	
}
