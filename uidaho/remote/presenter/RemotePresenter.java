package edu.uidaho.remote.presenter;
import edu.utah.sci.cyclist.core.event.notification.EventBus;
import edu.utah.sci.cyclist.core.presenter.ViewPresenter;

public class RemotePresenter extends ViewPresenter 
{
	/**
	 * Remote Cyclic Presenter
	 * Constructor
	 * @param bus
	 */
	
	public RemotePresenter(EventBus bus) 
	{
		super(bus);
	}
}
