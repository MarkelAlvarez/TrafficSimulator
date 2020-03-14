package simulator.model;

import java.util.*;
import simulator.misc.Pair;

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
		
		for (Pair<String, Integer> c : cont)
		{
			if(!map.getVehicles().contains(cont))
			{
				throw new IllegalArgumentException("El vehiculo no existe.");
			}
			map.getVehicle(c.getFirst()).setContaminationClass(c.getSecond());
		}
	}
}