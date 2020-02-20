package simulator.model;

import org.json.JSONObject;
import java.util.*;
import java.lang.RuntimeException;

public class Vehicle extends SimulatedObject {

	private List<Junction> itinerario;
	private int velocMaxima;
	private int velocActual;
	private VehicleStatus estado;
	private Road carretera = null;
	private int localizacion = 0;
	private int gradoCont;
	private int contTotal;
	private int distTotRec;
	
	Vehicle(String id, int maxSpeed, int contClass, List<Junction> itinerary) {
		
		super(id);
		
		if(maxSpeed < 0) throw new IllegalArgumentException("La velocidad maxima tiene que ser un numero positivo.");
		if(contClass < 0 || contClass > 10) throw new IllegalArgumentException("El grado de contaminacion tiene que tener un valor entre 0 y 10.");
		if(itinerary.length() < 2) throw new IllegalArgumentException("El itinerario tiene que tener al menos dos elementos.");
	
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
	
	void moveToNextRoad() {
		
	}
	
	/*GETS & SETS*/
	
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
	
	void setContaminationClass(int c) {
		
		if (gradoCont < 0 || gradoCont > 10)
		{
			throw new IllegalArgumentException("El valor de contaminación debe se entre 0 y 10 (ambos inclusive).");
		}
		
		gradoCont = c;
	}
}