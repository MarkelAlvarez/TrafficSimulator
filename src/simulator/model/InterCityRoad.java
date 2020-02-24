package simulator.model;

public class InterCityRoad extends Road {

	private Vehicle vehiculo;
	private Weather clima;
	private Road carretera;
	private int contTotal;
	
	public InterCityRoad(String id, Junction srcJunc, Junction destJunc, int maxSpeed, int contLimit, int length, Weather weather) {

		super(id, srcJunc, destJunc, maxSpeed, contLimit, length, weather);
	}

	/**
	 * Reduce la contaminaci�n total medinte la formula:
	 * (int)((100.0-x)/100.0)*tc)
	 */
	@Override
	void reduceTotalContamination() {
		
		int tc = vehiculo.getContTotal(), x = weatherANumero();
		
		contTotal = (int)((100.0-x)/100.0)*tc;
	}

	/**
	 * Si la contaminaci�n total excede el l�mite de contaminaci�n,entonces
	 * pone el l�mite de la velocidad al 50% de la velocidad m�xima mediante
	 * esta formula:
	 * (int)(maxSpeed*0.5)
	 */
	@Override
	void updateSpeedLimit() {
		
		if (vehiculo.getContTotal() > carretera.getLimiteCont())
		{
			carretera.setLimiteActual((int)(getVelocMaxima()*0.5));
		}
		else
		{
			carretera.setLimiteActual(getVelocMaxima());
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
			v.setSpeed((int)(carretera.getLimiteActual()*0.8));
		}
		else
		{
			v.setSpeed(carretera.getLimiteActual());
		}
		
		return 0;
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