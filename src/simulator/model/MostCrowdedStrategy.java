package simulator.model;

import java.util.List;

public class MostCrowdedStrategy implements LightSwitchingStrategy {

	private int ticksVerde;
	
	public MostCrowdedStrategy(int timeSlot) {
		ticksVerde = timeSlot;
	}

	@Override
	public int chooseNextGreen(List<Road> roads, List<List<Vehicle>> qs, int currGreen, int lastSwitchingTime,
			int currTime) {

		if(roads.isEmpty()) {
			return -1;
		}
		else if(currGreen == -1){
			int tamCola = 0;
			int pos = 0;
			
			for (int i = 0; i < qs.size(); i++) {
				if(tamCola < qs.get(i).size()) {
					tamCola = qs.get(i).size();
					pos = i;
				}
			}
			return pos;
		}
		else if (currTime-lastSwitchingTime <ticksVerde) {
			return currGreen;
		}
		//TODO: la que falta
		return 0;
	}

}
