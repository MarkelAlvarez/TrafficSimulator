package simulator.view;

import java.awt.*;
import java.util.*;
import java.util.List;

import javax.swing.*;

import simulator.control.Controller;
import simulator.model.*;
import simulator.model.Event;

public class ControlPanel extends JPanel implements TrafficSimObserver {

	private JFileChooser selectorFichero;
	private ChangeCO2ClassDialog changeCO2;
	private ChangeWeatherDialog changeWeather;
	private JButton run;
	private JButton stop;
	private JLabel textoTicks;
	private JSpinner ticks;
	private JDialog exit;
	
	private Controller _ctrl;
	
	public ControlPanel(Controller _ctrl) {
		
		// TODO Auto-generated constructor stub
		this._ctrl = _ctrl;
	}

	private void run_sim(int n) {
		
		if (n > 0 && !_stopped)
		{
			try {
				_ctrl.run(1);
			} catch (Exception e ) {
				// TODO show error message
				_stopped = true ;
				return;
			}
			
			SwingUtilities.invokeLater(new Runnable() {
				@Override
				public void run() {		
					run_sim( n - 1);
				}
			});
		}
		else
		{
			enableToolBar(true);
			_stopped = true;
		}
	}
	
	private void stop() {
		
		_stopped = true ;
	}
	
	@Override
	public void onAdvanceStart(RoadMap map, List<Event> events, int time) {
		
		// TODO Auto-generated method stub
	}

	@Override
	public void onAdvanceEnd(RoadMap map, List<Event> events, int time) {
		
		// TODO Auto-generated method stub	
	}

	@Override
	public void onEventAdded(RoadMap map, List<Event> events, Event e, int time) {
		
		// TODO Auto-generated method stub
	}

	@Override
	public void onReset(RoadMap map, List<Event> events, int time) {
		
		// TODO Auto-generated method stub
	}

	@Override
	public void onRegister(RoadMap map, List<Event> events, int time) {
		
		// TODO Auto-generated method stub
	}

	@Override
	public void onError(String err) {
		
		// TODO Auto-generated method stub
	}
}