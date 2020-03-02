package simulator.model.events;

import java.util.*;
import simulator.misc.Pair;
import simulator.model.RoadMap;

public class NewSetContClassEvent extends Event {

	private int time;
	private List<Pair<String,Integer>> cont;
	
	public NewSetContClassEvent(int time, List<Pair<String,Integer>> cs) {
		
		super(time);
		
		if(cs == null) throw new IllegalArgumentException("La lista no puede estar vacia.");
		
		this.time = time;
		cont = cs;
	}

	@Override
	void execute(RoadMap map) {
		// TODO Auto-generated method stub
	}
}