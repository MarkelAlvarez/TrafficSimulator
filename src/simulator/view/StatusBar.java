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
	private JLabel eventoAnadido;
	
	private JLabel currentTime = new JLabel();
	private JLabel currentEvent = new JLabel();
	
	private Controller ctrl;
	
	public StatusBar(Controller _ctrl) {
		
		ctrl = _ctrl;
		initGUI();
		ctrl.addObserver(this);
	}

	private void initGUI() {
		
		this.setLayout(new FlowLayout(FlowLayout.LEFT));
		this.setBorder(BorderFactory.createBevelBorder(1));
		
		tiempo = new JLabel("Time: ", JLabel.LEFT);
		currentTime = new JLabel("");
		this.add(tiempo);
		this.add(currentTime);
		
		this.add(new JSeparator(SwingConstants.VERTICAL));
		
		eventoAnadido = new JLabel("Event added: ", JLabel.LEFT);
		currentEvent = new JLabel("");
		this.add(eventoAnadido);
		this.add(currentEvent);
	}

	@Override
	public void onAdvanceStart(RoadMap map, List<Event> events, int time) {
		
		currentTime.setText(String.valueOf(time));
		currentEvent.setText("");
	}

	@Override
	public void onAdvanceEnd(RoadMap map, List<Event> events, int time) {
	}

	@Override
	public void onEventAdded(RoadMap map, List<Event> events, Event e, int time) {
		
		currentEvent.setText(String.valueOf(events.get(time)));
		currentEvent.setText(e.toString());
	}

	@Override
	public void onReset(RoadMap map, List<Event> events, int time) {
		
		currentTime.setText(String.valueOf(0));
		currentEvent.setText("");
	}

	@Override
	public void onRegister(RoadMap map, List<Event> events, int time) {
	
		currentTime.setText(String.valueOf(time));
		currentEvent.setText("");
	}

	@Override
	public void onError(String err) {
	}
}