package simulator.model;

import java.util.List;
import org.json.JSONObject;

import simulator.misc.SortedArrayList;

public class TrafficSimulator {

	private RoadMap mapaCarreteras;
	private List<Event> listaEventos;
	private int time;
	
	public TrafficSimulator() {

		listaEventos = new SortedArrayList<Event>();
		mapaCarreteras = null;
		time = 0;
	}
	
	public void addEvent(Event e) {
		
		listaEventos.add(e);
	}
	
	public void advance() {
		
		time++; //TODO: lo demás
	}
	
	public void reset() {
		
		mapaCarreteras.reset();
		listaEventos.clear();
		time = 0;
	}
	
	public JSONObject report() {
		
		JSONObject json = new JSONObject();
		/*JSONArray jArray = new JSONArray();
		JSONArray jArrayVehicle = new JSONArray();
		JSONArray jArrayRoad = new JSONArray();*/
		
		json.put("time", time);
		json.put("state", mapaCarreteras.report());
		
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