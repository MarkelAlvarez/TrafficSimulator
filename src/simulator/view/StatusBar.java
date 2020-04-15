package simulator.view;

import java.util.List;

import javax.swing.*;
import java.awt.*;

import simulator.control.Controller;
import simulator.launcher.Main;
import simulator.model.Event;
import simulator.model.RoadMap;
import simulator.model.TrafficSimObserver;

public class StatusBar extends JPanel implements TrafficSimObserver {

	private JLabel tiempo;
	private JLabel eventoAñadido;
	
	private JLabel currentTime;
	private JLabel currentEvent;
	
	private Controller ctrl;
	
	public StatusBar(Controller _ctrl) {
		
		ctrl = _ctrl;
		initGUI();
		ctrl.addObserver(this);
	}

	private void initGUI() {
		
		this.setLayout(new FlowLayout(FlowLayout.LEFT));
		this.setBorder(BorderFactory.createBevelBorder(1));
		
		tiempo = new JLabel("Time: " + currentTime, JLabel.LEFT);
		this.add(tiempo);
		
		this.add(new JSeparator(SwingConstants.VERTICAL));
		
		eventoAñadido = new JLabel("Event added: " + currentEvent, JLabel.LEFT);
		this.add(eventoAñadido);
	}

	@Override
	public void onAdvanceStart(RoadMap map, List<Event> events, int time) {
		
		currentTime.setText(String.valueOf(time));
	}

	@Override
	public void onAdvanceEnd(RoadMap map, List<Event> events, int time) {
	}

	@Override
	public void onEventAdded(RoadMap map, List<Event> events, Event e, int time) {
		
		currentEvent.setText(String.valueOf(events.get(time)));
	}

	@Override
	public void onReset(RoadMap map, List<Event> events, int time) {
		
		currentTime.setText(String.valueOf(0));
		currentEvent.setText(String.valueOf(events.get(time)));
	}

	@Override
	public void onRegister(RoadMap map, List<Event> events, int time) {
	
		currentTime.setText(String.valueOf(time));
		currentEvent.setText(String.valueOf(events.get(time)));
	}

	@Override
	public void onError(String err) {
	}
}