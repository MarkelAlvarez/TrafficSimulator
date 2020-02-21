package simulator.model;

import java.util.*;

import org.json.JSONObject;

public abstract class Road extends SimulatedObject {

	private Junction cruceOrigen = null;
	private Junction cruceDestino = null;
	private int longitud;
	private int velocMaxima;
	private int limiteActual;
	private int limiteCont;
	private Weather condMet = null;
	private int contTotal;
	private List<Vehicle> vehiculos;
	
	Road(String id, Junction srcJunc, Junction destJunc, int maxSpeed,
		int contLimit, int length, Weather weather) {
		super(id);
		if(maxSpeed <= 0) throw new IllegalArgumentException("La velocidad maxima tiene que ser un numero positivo.");
		if(contLimit < 0) throw new IllegalArgumentException("El limite de contaminacion no puede ser negativo.");
		if(length <= 0) throw new IllegalArgumentException("La longitud tiene que ser un numero positivo.");
		if(srcJunc == null) throw new IllegalArgumentException("El cruce de origen no puede ser nulo.");
		if(destJunc == null) throw new IllegalArgumentException("El cruce de destino no puede ser nulo.");
		if(weather == null) throw new IllegalArgumentException("La condicion meteorologica no puede ser nula.");
		

		cruceOrigen = srcJunc;
		cruceDestino = destJunc;
		velocMaxima = maxSpeed;
		limiteCont = contLimit;
		longitud = length;
		condMet = weather;
	}
	
	void enter(Vehicle v) {
		if(v.getLocalizacion() != 0) throw new IllegalArgumentException("La localizacion del vehiculo tiene que ser 0.");
		if(v.getVelocActual() != 0) throw new IllegalArgumentException("La velocidad del vehiculo tiene que ser 0.");
		
		vehiculos.add(v);
	}
	
	void exit(Vehicle v) {
		vehiculos.remove(v);
	}
	
	void setWeather(Weather w) {
		if(w == null) throw new IllegalArgumentException("La condicion meteorologica no puede ser nula.");

		condMet = w;
	}
	
	void addContamination(int c) {
		if(c < 0) throw new IllegalArgumentException("La contaminacion no puede ser negativa.");
		
		contTotal += c;
	}
	
	@Override
	void advance(int time) {
		reduceTotalContamination();
		updateSpeedLimit();
		for (int i = 0; i < vehiculos.size(); i++) {
			vehiculos.get(i).setSpeed(calculateVehicleSpeed(vehiculos.get(i)));
		}
		vehiculos.sort();
	}

	@Override
	public JSONObject report() {
		// TODO Auto-generated method stub
		return null;
	}

	public int getLongitud() {
		return longitud;
	}
	
	abstract void reduceTotalContamination();
	abstract void updateSpeedLimit();
	abstract int calculateVehicleSpeed(Vehicle v);

}
