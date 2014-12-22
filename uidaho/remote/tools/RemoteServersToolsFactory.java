package edu.uidaho.remote.tools;


import edu.utah.sci.cyclist.core.tools.Tool;
import edu.utah.sci.cyclist.core.tools.ToolFactory;
import edu.utah.sci.cyclist.core.util.AwesomeIcon;

import edu.uidaho.remote.tools.RemoteServersTools;

public class RemoteServersToolsFactory implements ToolFactory
{
	@Override
	public String getToolName() 
	{
		return RemoteServersTools.TOOL_NAME;
	}

	@Override
	public AwesomeIcon getIcon() 
	{
		return RemoteServersTools.ICON;
	}
	
	@Override
	public Tool create()
	{
		return new RemoteServersTools();
	}
}
