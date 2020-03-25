package simulator.view;

import java.util.List;

import javax.swing.*;

import simulator.control.Controller;
import simulator.launcher.Main;
import simulator.model.Event;
import simulator.model.RoadMap;
import simulator.model.TrafficSimObserver;

public class StatusBar extends JPanel implements TrafficSimObserver {

	private JLabel tiempo;
	private JSeparator separador;
	private JLabel eventoAñadido;
	
	private Controller _ctrl;
	
	public StatusBar(Controller _ctrl) {
		
		this._ctrl = _ctrl;
	}

	@Override
	public void onAdvanceStart(RoadMap map, List<Event> events, int time) {
		
		// TODO: ¿Esto está bien?
		tiempo = new JLabel("Tiempo: " + time, JLabel.LEFT);
		separador = new JSeparator(SwingConstants.VERTICAL);
	}

	@Override
	public void onAdvanceEnd(RoadMap map, List<Event> events, int time) {
		
		// TODO Auto-generated method stub
	}

	@Override
	public void onEventAdded(RoadMap map, List<Event> events, Event e, int time) {
		
		// TODO: aparece todo el rato o solo el instante?
		eventoAñadido = new JLabel("Event Added: (" + e.toString() + ")", JLabel.LEFT);
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