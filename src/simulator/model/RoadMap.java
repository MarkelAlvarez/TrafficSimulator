package simulator.model;

import java.util.*;
import org.json.JSONObject;

public class RoadMap {

	private List<Junction> listaCruces;
	private List<Road> listaCarreteras;
	private List<Vehicle> listaVehiculos;
	private Map<String,Junction> mapaCruces;
	private Map<String,Road> mapaCarreteras;
	private Map<String,Vehicle> mapaVehiculos;
	
	protected RoadMap(List<Junction> listaCruces, List<Road> listaCarreteras, List<Vehicle> listaVehiculos, Map<String, Junction> mapaCruces, Map<String, Road> mapaCarreteras, Map<String, Vehicle> mapaVehiculos) {
		
		this.listaCruces= listaCruces;
		this.listaCarreteras = listaCarreteras;
		this.listaVehiculos = listaVehiculos;
		this.mapaCruces = mapaCruces;
		this.mapaCarreteras = mapaCarreteras;
		this.mapaVehiculos = mapaVehiculos;
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
	}
	
	/**
	 * A�ade la carretera r al final de la lista de carreteras y modifica el mapa 
	 * correspondiente.
	 * 
	 * @param r
	 */
	void addRoad(Road r) {
		
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
		
		//TODO Como va lo de los JSON
		return null;
	}
	
	/*SETS & GETS*/
	
	public Junction getJunction(String id) {
		if(listaCruces.contains(mapaCruces.get(id))){
			return mapaCruces.get(id);
		}
		return null;
	}
	
	public Road getRoad(String id) {
		if(listaCarreteras.contains(mapaCarreteras.get(id))){
			return mapaCarreteras.get(id);
		}
		return null;
	}
	
	public Vehicle getVehicle(String id) {
		if(listaVehiculos.contains(mapaVehiculos.get(id))){
			return mapaVehiculos.get(id);
		}
		return null;
	}
	
	//No se si esto est� bien, pero como pide de solo lectura pues eso - JP
	public List<Junction>getJunctions() {
		final List<Junction> lista = new ArrayList<Junction>(listaCruces);
		return lista;
	}
	
	public List<Road> getRoads() {
		final List<Road> lista = new ArrayList<Road>(listaCarreteras);
		return lista;
	}
	
	public List<Vehicle> getVehicles() {
		final List<Vehicle> lista = new ArrayList<Vehicle>(listaVehiculos);
		return lista;
	}
}