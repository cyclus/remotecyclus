package edu.uidaho.remote;

import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;

public class SimulationServermaster 
{
public SimpleIntegerProperty Server_ID = new SimpleIntegerProperty();
public SimpleStringProperty Server_Name = new SimpleStringProperty();
public SimpleStringProperty Location = new SimpleStringProperty();
public SimpleStringProperty Description = new SimpleStringProperty();

public Integer getServerId()
{
	return Server_ID.get();
}

public String getServerName()
{
	return Server_Name.get();
}

public String getLocation()
{
	return Location.get();
}

public String getDescription()
{
	return Description.get();
}
}
