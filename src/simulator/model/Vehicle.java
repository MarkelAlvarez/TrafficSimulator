package simulator.model;

import org.json.JSONObject;
import java.util.*;
import java.lang.RuntimeException;

public class Vehicle extends SimulatedObject implements Comparable<Vehicle> {

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
		
		if(maxSpeed <= 0) throw new IllegalArgumentException("La velocidad maxima tiene que ser un numero positivo.");
		if(contClass < 0 || contClass > 10) throw new IllegalArgumentException("El grado de contaminacion tiene que tener un valor entre 0 y 10.");
		if(itinerary.length() < 2) throw new IllegalArgumentException("El itinerario tiene que tener al menos dos elementos.");
	
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
			contTotal += contProd;
			carretera.addContamination(contProd);
			localizacion = locNueva;
		}
		else
		{
			velocActual = 0;
		}
	}

	@Override
	public JSONObject report() {
		//TODO: implementar JSON cuando se sepa como
		return null;
	}
	
	void moveToNextRoad() {
		//TODO: pues eso
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
	public int compareTo(Vehicle o) {
		
		return this.localizacion < o.localizacion ? 1 : this.localizacion < o.localizacion ? -1 : 0;
	}
	
	public int getContTotal() {
		
		return contTotal;
	}

	public void setContTotal(int contTotal) {
		
		this.contTotal = contTotal;
	}
}