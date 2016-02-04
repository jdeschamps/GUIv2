package view;

import javax.swing.SwingUtilities;

import controller.Controller;
import configuration.MConfiguration;
import gui.MainFrame;

public class UIManager {

	private Controller controller_;
	private MConfiguration conf_;
	private ListenerFactory factory_;
	private MainFrame frame_;
	
	public UIManager(Controller cont){
		controller_ = cont;
		conf_ = controller_.getConfiguration();
		factory_ = new ListenerFactory(controller_);
		
		show();
	}

	public void show() { 
		SwingUtilities.invokeLater(new Runnable()
		{
			@Override
			public void run(){
				frame_ = new MainFrame(factory_, conf_);					/////// need to pass itself to get the updates/listener
			}
		}); 
	}
}
