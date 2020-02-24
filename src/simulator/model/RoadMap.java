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
	 * Añade el cruce j al final de la lista de cruces y modifica el mapa
	 * correspondiente. Se comprueba que no existe otro cruce con el mismo
	 * identificcador.
	 * 
	 * @param j
	 */
	void addJunction(Junction j) {
		
	}
	
	/**
	 * Añade la carretera r al final de la lista de carreteras y modifica el mapa 
	 * correspondiente.
	 * 
	 * @param r
	 */
	void addRoad(Road r) {
		
	}
	
	/**
	 * Añade el vehículo v al final de la lista de vehículos y modifica el mapa 
	 * de vehículos en concordancia.
	 * 
	 * @param v
	 */
	void addVehicle(Vehicle v) {
		
	}
	
	/**
	 * Limpia todas las listas y mapas
	 */
	void reset() {
		
		
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
		
		return null;
	}
	
	public Road getRoad(String id) {
		
		return null;
	}
	
	public Vehicle getVehicle(String id) {

		return null;
	}
	
	public List<Junction>getJunctions() {
		
		return listaCruces;
	}
	
	public List<Road> getRoads() {
		
		return listaCarreteras;	
	}
	
	public List<Vehicle> getVehicles() {
		
		return listaVehiculos;
	}
}