package simulator.model;

import java.util.Comparator;

public class CompararEventos implements Comparator<Event> {
	
	public CompararEventos() {
	}

	@Override
	public int compare(Event o1, Event o2) {
		
		if(o1.getTime() < o2.getTime())
		{
			return 1;
		}
		else if(o1.getTime() > o2.getTime())
		{
			return -1;
		}
		
		return 0;
	}
}