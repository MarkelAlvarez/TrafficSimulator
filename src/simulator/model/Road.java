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
	private String id;

	Road(String id, Junction srcJunc, Junction destJunc, int maxSpeed, int contLimit, int length, Weather weather) {

		super(id);

		if(maxSpeed <= 0) throw new IllegalArgumentException("La velocidad maxima tiene que ser un numero positivo.");
		if(contLimit < 0) throw new IllegalArgumentException("El limite de contaminacion no puede ser negativo.");
		if(length <= 0) throw new IllegalArgumentException("La longitud tiene que ser un numero positivo.");
		if(srcJunc == null) throw new IllegalArgumentException("El cruce de origen no puede ser nulo.");
		if(destJunc == null) throw new IllegalArgumentException("El cruce de destino no puede ser nulo.");
		if(weather == null) throw new IllegalArgumentException("La condicion meteorologica no puede ser nula.");

		this.id = id;
		cruceOrigen = srcJunc;
		cruceDestino = destJunc;
		velocMaxima = maxSpeed;
		limiteCont = contLimit;
		longitud = length;
		condMet = weather;
	}

	/**
	 * Se utiliza por los veh�culos para entrar a la carretera. Simplemente
	 * a�ade el veh�culo a la lista de veh�culos de la carretera (al final).
	 * 
	 * @param v
	 */
	void enter(Vehicle v) {

		if(v.getLocalizacion() != 0) throw new IllegalArgumentException("La localizacion del vehiculo tiene que ser 0.");
		if(v.getVelocActual() != 0) throw new IllegalArgumentException("La velocidad del vehiculo tiene que ser 0.");

		vehiculos.add(v);
	}

	/**
	 * Lo utiliza un veh�culo para abandonar la carretera. Simplemente
	 * elimina el veh�culo de la lista de veh�culos de la carretera.
	 * 
	 * @param v
	 * */
	void exit(Vehicle v) {

		vehiculos.remove(v);
	}

	/**
	 * a�ade c unidades de CO2 al total de la contaminaci�n de la carretera. Tiene
	 * que comprobar que c no es negativo y lanzar una excepci�n en otro caso.
	 * 
	 * @param c
	 */
	void addContamination(int c) {

		if(c < 0) throw new IllegalArgumentException("La contaminacion no puede ser negativa.");

		contTotal += c;
	}

	/**
	 * Avanza el estado de la carretera de la siguiente forma:
	 * 
	 * (1) llama a reduceTotalContamination para reducir la 
	 * contaminaci�n total, es decir, la disminuci�n de CO2.
	 * 
	 * (2) llama a updateSpeedLimit para establecer el l�mite de
	 * velocidad de la carretera en el paso de simulaci�n actual.
	 * 
	 * (3) recorre la lista de veh�culos (desde el primero al �ltimo) y,
	 * para cada veh�culo:
	 *  	a) pone la velocidad del veh�culo al valor devuelto por calculateVehicleSpeed. 
	 * 		b) llama al m�todo advance del veh�culo.
	 * 
	 * @param time
	 */
	@Override
	void advance(int time) {

		reduceTotalContamination();
		updateSpeedLimit();

		for (int i = 0; i < vehiculos.size(); i++)
		{
			vehiculos.get(i).setSpeed(calculateVehicleSpeed(vehiculos.get(i)));
		}

		Collections.sort(vehiculos);
	}

	/**
	 * Devuelve el estado de la carretera en formato JSON
	 */
	@Override
	public JSONObject report() {

		JSONObject json = new JSONObject();
		
		json.put("id", id);
		json.put("speedlimit", limiteActual);
		json.put("weather", condMet);
		json.put("vehicles", vehiculos);	
		
		return json;
	}

	/*GETS & SETS*/

	/**
	 * Pone las condiciones atmosf�ericas de la carretera al valor w. Debe 
	 * comprobar que w no es null y lanzar una excepci�n en caso contrario.
	 * 
	 * @param w
	 */
	void setWeather(Weather w) {

		if(w == null) throw new IllegalArgumentException("La condicion meteorologica no puede ser nula.");

		condMet = w;
	}

	public Weather getCondMet() {

		return condMet;
	}

	public int getLimiteCont() {
		
		return limiteCont;
	}

	public void setLimiteCont(int limiteCont) {
		
		this.limiteCont = limiteCont;
	}

	public int getLongitud() {

		return longitud;
	}

	public int getLimiteActual() {
		
		return limiteActual;
	}

	public void setLimiteActual(int limiteActual) {
		
		this.limiteActual = limiteActual;
	}

	public int getVelocMaxima() {
		
		return velocMaxima;
	}

	public void setVelocMaxima(int velocMaxima) {
		
		this.velocMaxima = velocMaxima;
	}

	@Override
	public String getId() {
		
		return id;
	}

	public void setId(String id) {
		
		this.id = id;
	}

	/**
	 * M�todo abstracto para reducir el total de la contaminaci�n de la carretera.
	 * La implementaci�n espec�fica la definir�n las subclases de Road.
	 */
	abstract void reduceTotalContamination();
	/**
	 * m�todo abstracto para actualizar la velocidad l�mite de la carretera.
	 * La implementaci�n espec�fica la definir�n las subclases de Road.
	 */
	abstract void updateSpeedLimit();
	/**
	 * m�todo abstracto para calcular la velocidad de un veh�culo v.
	 * La implementaci�n espec�fica la definir�n las subclases de Road.
	 * 
	 * @param v
	 */
	abstract int calculateVehicleSpeed(Vehicle v);
}