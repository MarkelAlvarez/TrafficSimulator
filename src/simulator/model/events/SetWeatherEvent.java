package simulator.model.events;

import java.util.*;

import simulator.misc.Pair;
import simulator.model.*;

public class SetWeatherEvent extends Event {
	
	private int time;
	private List<Pair<String,Weather>> tiempo;

	public SetWeatherEvent(int time, List<Pair<String,Weather>> ws) {
	
		super(time);
		
		if(ws == null) throw new IllegalArgumentException("La lista no puede estar vacia.");
		
		this.time = time;
		tiempo = ws;
	}

	@Override
	void execute(RoadMap map) {
		// TODO Auto-generated method stub
	}
}