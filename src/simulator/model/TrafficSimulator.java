package simulator.model;

import java.util.List;
import org.json.JSONArray;
import org.json.JSONObject;

public class TrafficSimulator {

	private RoadMap mapaCarreteras;
	private List<Event> listaEventos; //¿Ordenar con lo mismo que en CompararEventos.java?
	private int time;
	
	public TrafficSimulator() {
		
		mapaCarreteras = null;
		listaEventos = null;
		time = 0;
	}
	
	public void addEvent(Event e) {
		
		//TODO: cómo va lo de la SortedArrayList
	}
	
	public void advance() {
		
		time++; //TODO: lo demás
	}
	
	public void reset() {
		
		mapaCarreteras = null;
		listaEventos = null;
		time = 0;
	}
	
	public JSONObject report() {
		
		JSONObject json = new JSONObject();
		JSONArray jArray = new JSONArray();
		JSONArray jArrayVehicle = new JSONArray();
		JSONArray jArrayRoad = new JSONArray();
		
		json.put("time", time);
		json.put("state", jArray);
		
		/*json.put("junctions", jArray);
		for (Junction junction : listaCruces)
		{
			jArray.put(junction.report());
		}
		
		json.put("roads", jArrayRoad);
		for (Road roads : listaCarreteras)
		{
			jArrayRoad.put(roads.report());
		}
		
		json.put("vehicles", jArrayVehicle);
		for (Vehicle vehicle : listaVehiculos)
		{
			jArrayVehicle.put(vehicle.report());
		}*/
		
		return json;
	}
}