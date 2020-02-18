package simulator.model;

import org.json.JSONObject;
import java.util.*;
import java.lang.RuntimeException;

public class Vehicle extends SimulatedObject {

	private List<Junction> itinerario;
	private int velocMaxima;
	private int velocActual;
	private VehicleStatus estado;
	private Road carretera;
	private int localizacion;
	private int gradoCont;
	private int contTotal;
	private int distTotRec;
	
	Vehicle(String id, int maxSpeed, int contClass,
			List<Junction> itinerary) {
		super(id);
		if(velocMaxima<0) throw new IllegalArgumentException("La velocidad maxima tiene que ser un numero positivo.");
		if(contClass < 0 || contClass > 10) throw new IllegalArgumentException ("El grado de contaminacion tiene que tener un valor entre 0 y 10.");
		if(itinerary.leght < 2) throw new IllegalArgumentException ("El itinerario tiene que tener al menos dos elementos.");
	
		velocMaxima = maxSpeed;
		gradoCont = contClass;
		itinerario = Collections.unmodifiableList(new ArrayList<>(itinerary));
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
	
	void setSpeed(int s) {
		
	}
	
	void setContaminationClass(int c) {
		
	}
	
	void moveToNextRoad() {
		
	}

	public JSONObject report() {
		
	}
	
	
}
