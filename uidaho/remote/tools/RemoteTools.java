package edu.uidaho.remote.tools;

import edu.utah.sci.cyclist.core.event.notification.EventBus;
import edu.utah.sci.cyclist.core.presenter.ViewPresenter;
import edu.utah.sci.cyclist.core.tools.Tool;
import edu.utah.sci.cyclist.core.ui.View;
import edu.utah.sci.cyclist.core.util.AwesomeIcon;

import edu.uidaho.remote.Remote;
import edu.uidaho.remote.presenter.RemotePresenter;

public class RemoteTools implements Tool
{
	public static final String ID 			= "edu.uidaho.remote.RemoteTools";
	public static final String TOOL_NAME 	= "Run Simulation";
	public static final AwesomeIcon ICON 	= AwesomeIcon.APPLE;
	
	private View _view = null;
	
	private ViewPresenter _presenter = null;
	
	@Override
	public String getId() {
		return ID;
	}

	@Override
	public String getName() {
		return TOOL_NAME;
	}

	@Override
	public View getView() 
	{
		if (_view == null) 
			_view = new Remote();
		return _view;
	}

	@Override
	public ViewPresenter getPresenter(EventBus bus) {
		if (_presenter == null)
			_presenter = new RemotePresenter(bus);
		return _presenter;
	}

}
