package simulator.model;

public class CityRoad extends Road {

	private Vehicle vehiculo;
	private Weather clima;
	private int limitCont;
	
	public CityRoad(String id, Junction srcJunc, Junction destJunc, int maxSpeed, int contLimit, int length, Weather weather) {

		super(id, srcJunc, destJunc, maxSpeed, contLimit, length, weather);
		
		limitCont = contLimit;
	}

	/**
	 * Reduce el total de la contaminaci�n en x unidades de CO2,
	 * donde x depende de las condiciones atmosféricas:
	 */
	@Override
	void reduceTotalContamination() {
		
		int aReducir = 0, aux = limitCont;
		
		if (clima == Weather.WINDY)
		{
			aReducir = 10;
		}
		else if (clima == Weather.STORM)
		{
			aReducir = 2;
		}
		
		if (aux - aReducir >= 0)
		{
			limitCont -= aReducir;
		}
		else
		{
			throw new IllegalArgumentException("El total de contaminación no puede ser negativo.");
		}
		
	}

	@Override
	void updateSpeedLimit() {
		
		limiteActual = velocMaxima;
	}
	
	/**
	 * Calcula la velocidad de un vehículo usando la siguiente expresión:
	 * (int)(((11.0-f)/11.0)*s)
	 * 
	 * @param v
	 */
	@Override
	int calculateVehicleSpeed(Vehicle v) {
		
		int s = limiteActual, f = vehiculo.getGradoCont();
				
		return (int)(((11.0-f)/11.0)*s);
	}
}