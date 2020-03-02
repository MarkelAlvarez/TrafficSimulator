package simulator.model.events;

import java.util.List;
import java.util.Map;

import simulator.model.*;

public class NewJunctionEvent extends Event {

	private int time;
	private LightSwitchingStrategy estratSem;
	private DequeuingStrategy estratCola;
	private int x;
	private int y;
	private String id;
	
	public NewJunctionEvent(int time, String id, LightSwitchingStrategy lsStrategy, DequeuingStrategy dqStrategy, int xCoor, int yCoor) {
	
		super(time);
		
		if(lsStrategy == null && dqStrategy == null) throw new IllegalArgumentException("Ninguna de las estrategias no pueden ser null.");
		if((xCoor >= 0) && (yCoor >= 0)) throw new IllegalArgumentException("Las coordenadas deben tener un valor positivo mayor que 0.");
		
		this.time = time;
		this.id = id;
		estratSem = lsStrategy;
		estratCola = dqStrategy;
		x = xCoor;
		y = yCoor;
	}

	@Override
	void execute(RoadMap map) {
		// TODO Auto-generated method stub
	}
}