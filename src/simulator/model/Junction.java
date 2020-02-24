package simulator.model;

import java.util.*;
import org.json.JSONObject;

public class Junction extends SimulatedObject {

	private List<Road> listaEntrantes;
	private Map<Junction,Road> mapaSalientes;
	private List<List<Vehicle >> listaColas;
	private Map<Road,List<Vehicle>> carretera_cola;
	private int indiceVerde;
	private int pasoSemaforo;
	private LightSwitchingStrategy estratSem;
	private DequeuingStrategy estratCola;
	
	protected Junction(String id, LightSwitchStrategy lsStrategy, DequeingStrategy dqStrategy, int xCoor, int yCoor) {
		
		super(id);
	}

	@Override
	void advance(int time) {
		// TODO Auto-generated method stub

	}

	@Override
	public JSONObject report() {
		// TODO Auto-generated method stub
		return null;
	}
}