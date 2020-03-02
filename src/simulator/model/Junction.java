package simulator.model;

import java.util.*;

import org.json.JSONArray;
import org.json.JSONObject;

public class Junction extends SimulatedObject {

	//TODO: Rellenar metodos
	private List<Road> listaEntrantes;
	private Map<Junction,Road> mapaSalientes;
	private List<List<Vehicle >> listaColas;
	private Map<Road,List<Vehicle>> carretera_cola;
	private int indiceVerde;
	private int pasoSemaforo;
	private LightSwitchingStrategy estratSem;
	private DequeuingStrategy estratCola;
	private int x;
	private int y;
	private String id;
	
	protected Junction(String id, LightSwitchingStrategy lsStrategy, DequeuingStrategy dqStrategy, int xCoor, int yCoor) {
		
		super(id);
		
		if(lsStrategy == null && dqStrategy == null) throw new IllegalArgumentException("Ninguna de las estrategias no pueden ser null.");
		if((xCoor >= 0) && (yCoor >= 0)) throw new IllegalArgumentException("Las coordenadas deben tener un valor positivo mayor que 0.");
		
		this.id = id;
		estratSem = lsStrategy;
		estratCola = dqStrategy;
		x = xCoor;
		y = yCoor;
	}

	//TODO: metodos de Junction
	void addIncommingRoad(Road r) {
		
	}
	
	void addOutGoingRoad(Road r) {
		
	}
	
	void enter(Vehicle v) {
		
	}
	
	Road roadTo(Junction j) {
		
		return null;
	}
	
	@Override
	void advance(int time) {
		
	}

	@Override
	public JSONObject report() {

		JSONObject json = new JSONObject();
		
		json.put("id", id);
		
		if (indiceVerde == -1)
		{
			json.put("green", "none");
		}
		else
		{
			json.put("green", listaEntrantes.get(indiceVerde).getId());
		}
		
		JSONArray jArray = new JSONArray();
		json.put("queues", jArray);
		/* Estos son los de dentro de "queues" */
		for (Road r : listaEntrantes)
		{
			JSONObject jsonCarreteras = new JSONObject();
			jArray.put(jsonCarreteras);
			jsonCarreteras.put("road", r.getId());
		}
		
		return json;
	}
	
	/*GETS Y SETS*/
	
	@Override
	public String getId() {
		
		return id;
	}

	public void setId(String id) {
		
		this.id = id;
	}
}