package simulator.model;

import org.json.JSONObject;
import java.util.*;

public class Vehicle extends SimulatedObject implements Comparable<Vehicle> {

	private List<Junction> itinerario;
	private int velocMaxima;
	private int velocActual;
	private VehicleStatus estado;
	private Road carretera = null;
	private int localizacion = 0;
	private int gradoCont;
	private int contTotal;
	private int distTotRec = 0;
	private String id;
	
	Vehicle(String id, int maxSpeed, int contClass, List<Junction> itinerary) {
		
		super(id);
		
		if(maxSpeed <= 0) throw new IllegalArgumentException("La velocidad maxima tiene que ser un numero positivo.");
		if(contClass < 0 || contClass > 10) throw new IllegalArgumentException("El grado de contaminacion tiene que tener un valor entre 0 y 10.");
		if(itinerary.size() < 2) throw new IllegalArgumentException("El itinerario tiene que tener al menos dos elementos.");
	
		this.id = id;
		velocMaxima = maxSpeed;
		gradoCont = contClass;
		itinerario = Collections.unmodifiableList(new ArrayList<>(itinerary));
	}
	
	@Override
	void advance(int time) {
		
		int locNueva = 0, contProd = 0;
		
		if(estado == VehicleStatus.TRAVELING) 
		{	
			locNueva = localizacion + velocActual;
			
			if(locNueva > carretera.getLongitud())
			{
				locNueva = carretera.getLongitud();
				//TODO: METER EN COLA AL COCHE
				estado = VehicleStatus.WAITING;
			}
			
			contProd = gradoCont * (locNueva - localizacion);
			distTotRec += (locNueva - localizacion);
			contTotal += contProd;
			carretera.addContamination(contProd);
			localizacion = locNueva;
		}
		else
		{
			velocActual = 0;
		}
	}

	/**
	 * Mueve el vehículo a la siguiente carretera. Este proceso se hace 
	 * saliendo de la carretera actual y entrando a la siguiente carretera
	 * de su itinerario, en la localización 0.
	 */
	void moveToNextRoad() {
		
		//TODO: pues eso
	}
	
	/**
	 * Devuelve el estado del vehículo en formato JSON.
	 */
	@Override
	public JSONObject report() {

		JSONObject json = new JSONObject();
		
		json.put("id", id);
		json.put("speed", velocActual);
		json.put("distance", distTotRec);
		json.put("co2", contTotal);
		json.put("class", gradoCont);
		
		if (estado != VehicleStatus.PENDING || estado != VehicleStatus.ARRIVED)
		{
			json.put("road", carretera);
			json.put("location", localizacion);
		}		
		
		return json;
	}
	
	/*GETS & SETS*/
	
	/**
	 * Pone la velocidad actual al valor mínimo entre s y la velocidad
	 * máxima del vehículo. Lanza una excepción si s es negativo.
	 * 
	 * @param s
	 */
	void setSpeed(int s) {
		
		if (s < 0)
		{
			throw new IllegalArgumentException("La velocidad debe ser positiva.");
		}
		
		if (s <= velocMaxima)
		{
			velocActual = s;
		}
		else
		{
			velocActual = velocMaxima;
		}
	}
	
	/**
	 * Pone el valor de contaminación del vehículo a 'c'. Lanza una 
	 * excepción si 'c' no es un valor entre 0 y 10 (ambos incluidos).
	 * 
	 * @param c
	 */
	void setContaminationClass(int c) {
		
		if (gradoCont < 0 || gradoCont > 10)
		{
			throw new IllegalArgumentException("El valor de contaminación debe se entre 0 y 10 (ambos inclusive).");
		}
		
		gradoCont = c;
	}

	public int getGradoCont() {
		
		return gradoCont;
	}

	public int getLocalizacion() {
		
		return localizacion;
	}

	public int getVelocActual() {
		
		return velocActual;
	}
	
	@Override
	public String getId() {
		
		return id;
	}

	public void setId(String id) {
		
		this.id = id;
	}

	@Override
	public int compareTo(Vehicle o) {
		
		return this.localizacion < o.localizacion ? 1 : this.localizacion < o.localizacion ? -1 : 0;
	}
	
	public int getContTotal() {
		
		return contTotal;
	}

	public void setContTotal(int contTotal) {
		
		this.contTotal = contTotal;
	}
	
	public Road getCarretera() {
		return carretera;
	}

	public void setCarretera(Road carretera) {
		this.carretera = carretera;
	}
}