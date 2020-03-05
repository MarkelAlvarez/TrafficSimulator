package simulator.model;

import java.util.*;

import org.json.JSONArray;
import org.json.JSONObject;

public class RoadMap {

	private List<Junction> listaCruces;
	private List<Road> listaCarreteras;
	private List<Vehicle> listaVehiculos;
	private Map<String,Junction> mapaCruces;
	private Map<String,Road> mapaCarreteras;
	private Map<String,Vehicle> mapaVehiculos;
	
	protected RoadMap() {
		
		listaCruces = new ArrayList<Junction>();
		listaCarreteras = new ArrayList<Road>();
		listaVehiculos = new ArrayList<Vehicle>();
		mapaCruces = new HashMap<String, Junction>();
		mapaCarreteras = new HashMap<String, Road>();
		mapaVehiculos = new HashMap<String, Vehicle>();
	}
	
	/**
	 * A�ade el cruce j al final de la lista de cruces y modifica el mapa
	 * correspondiente. Se comprueba que no existe otro cruce con el mismo
	 * identificcador.
	 * 
	 * @param j
	 */
	void addJunction(Junction j) {
		
		if(!listaCruces.contains(j))
		{
			listaCruces.add(j);
			mapaCruces.put(j.getId(), j);
		}
		else
		{
			throw new IllegalArgumentException("Ya existe ese cruce.");
		}
	}
	
	/**
	 * A�ade la carretera r al final de la lista de carreteras y modifica el mapa 
	 * correspondiente.
	 * 
	 * @param r
	 */
	void addRoad(Road r) {
		
		if (!listaCarreteras.contains(r) && mapaCruces.containsValue(r.getCruceOrigen()) && mapaCruces.containsValue(r.getCruceDestino()))
		{
			throw new IllegalArgumentException("No existe esa carretera.");
		}
		
		listaCarreteras.add(r);
		mapaCarreteras.put(r.getId(), r);
	}
	
	/**
	 * A�ade el veh�culo v al final de la lista de veh�culos y modifica el mapa 
	 * de veh�culos en concordancia.
	 * 
	 * @param v
	 */
	void addVehicle(Vehicle v) {
		
		List<Junction> l = v.getItinerario();
		
		if (!listaVehiculos.contains(v))
		{
			throw new IllegalArgumentException("No existe esa carretera.");
		}
		
		for (int i = 0; i < l.size()-1; i++)
		{
			if (l.get(i).roadTo(l.get(i+1)) == null)
			{
				throw new IllegalArgumentException("El itineario es nulo.");
			}
		}
		
		listaVehiculos.add(v);
		mapaVehiculos.put(v.getId(), v);
	}
	
	/**
	 * Limpia todas las listas y mapas
	 */
	void reset() {
		
		listaCarreteras.clear();
		listaVehiculos.clear();
		listaCruces.clear();
		mapaCarreteras.clear();
		mapaCruces.clear();
		mapaVehiculos.clear();
	}
	
	/**
	 * Devuelve el estado del mapa de carreteras en formato JSON
	 */
	public JSONObject report() {

		JSONObject json = new JSONObject();
		JSONArray jArray = new JSONArray();
		JSONArray jArrayVehicle = new JSONArray();
		JSONArray jArrayRoad = new JSONArray();
		
		json.put("junctions", jArray);
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
		}
		
		return json;
	}
	
	/*SETS & GETS*/
	
	public Junction getJunction(String id) {
		
		if(listaCruces.contains(mapaCruces.get(id)))
		{
			return mapaCruces.get(id);
		}
		
		return null;
	}
	
	public Road getRoad(String id) {
		
		if(listaCarreteras.contains(mapaCarreteras.get(id)))
		{
			return mapaCarreteras.get(id);
		}
		
		return null;
	}
	
	public Vehicle getVehicle(String id) {
		
		if(listaVehiculos.contains(mapaVehiculos.get(id)))
		{
			return mapaVehiculos.get(id);
		}
		
		return null;
	}
	
	public List<Junction>getJunctions() {
		
		return Collections.unmodifiableList(listaCruces);
	}
	
	public List<Road> getRoads() {
		
		return Collections.unmodifiableList(listaCarreteras);
	}
	
	public List<Vehicle> getVehicles() {
		
		return Collections.unmodifiableList(listaVehiculos);
	}
}