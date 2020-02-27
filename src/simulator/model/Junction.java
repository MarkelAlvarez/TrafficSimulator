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
	private String id;
	
	protected Junction(String id, LightSwitchingStrategy lsStrategy, DequeuingStrategy dqStrategy, int xCoor, int yCoor) {
		
		super(id);
		
		this.id = id;
	}

	@Override
	void advance(int time) {
		// TODO Auto-generated method stub

	}

	@Override
	public JSONObject report() {

		JSONObject json = new JSONObject();
		
		json.put("id", id);
		json.put(, );
		json.put("queues", listaColas);	
		
		return json;
	}
}