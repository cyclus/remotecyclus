package edu.uidaho.remote.tools;

import edu.utah.sci.cyclist.core.tools.Tool;
import edu.utah.sci.cyclist.core.tools.ToolFactory;
import edu.utah.sci.cyclist.core.util.AwesomeIcon;
import edu.uidaho.remote.tools.RemoteTools;

public class RemoteToolsFactory implements ToolFactory
{
	@Override
	public String getToolName() 
	{
		return RemoteTools.TOOL_NAME;
	}

	@Override
	public AwesomeIcon getIcon() 
	{
		return RemoteTools.ICON;
	}
	
	@Override
	public Tool create() 
	{
		return new RemoteTools();
	}
}
