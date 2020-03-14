package simulator.model;

public class InterCityRoad extends Road {

	private Vehicle vehiculo;
	private Weather clima;
	private Road carretera;
	
	public InterCityRoad(String id, Junction srcJunc, Junction destJunc, int maxSpeed, int contLimit, int length, Weather weather) {

		super(id, srcJunc, destJunc, maxSpeed, contLimit, length, weather);
	}

	/**
	 * Reduce la contaminaci�n total medinte la formula:
	 * (int)((100.0-x)/100.0)*tc)
	 */
	@Override
	void reduceTotalContamination() {
		
		int tc = getContTotal(), x = weatherANumero();
		
		setContTotal((int)((100.0-x)/100.0)*tc);
	}

	/**
	 * Si la contaminación total excede el límite de contaminación,entonces
	 * pone el límite de la velocidad al 50% de la velocidad máxima mediante
	 * esta formula:
	 * (int)(maxSpeed*0.5)
	 */
	@Override
	void updateSpeedLimit() {
		
		if (getContTotal() > getLimiteCont())
		{
			setLimiteActual((int)(getVelocMaxima()*0.5));
		}
		else
		{
			setLimiteActual(getVelocMaxima());
		}
	}

	/**
	 * Pone la velocidad del veh�culo a la velocidad l�mite de la carretera
	 * dependiendo del clima.
	 * 
	 * @param v
	 */
	@Override
	int calculateVehicleSpeed(Vehicle v) {

		if (clima == Weather.STORM)
		{
			return (int) (limiteActual*0.8);
		}
		else
		{
			return limiteActual;
		}
	}
	
	/**
	 * Dependiendo del clima devuelve el valor de cada clima
	 * para reducir la contaminaci�n. 
	 */
	public int weatherANumero()
	{
		int condicion = 0;
		
		if (clima == Weather.SUNNY)
		{
			condicion = 2;
		}
		else if (clima == Weather.CLOUDY)
		{
			condicion = 3;
		}
		else if (clima == Weather.RAINY)
		{
			condicion = 10;
		}
		else if (clima == Weather.WINDY)
		{
			condicion = 15;
		}
		else if (clima == Weather.STORM)
		{
			condicion = 20;
		}
		
		return condicion;	
	}
}