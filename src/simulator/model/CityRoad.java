package simulator.model;

public class CityRoad extends Road {

	private Vehicle vehiculo;
	private Road carretera;
	private Weather clima;
	private int limitCont;
	
	public CityRoad(String id, Junction srcJunc, Junction destJunc, int maxSpeed, int contLimit, int length, Weather weather) {

		super(id, srcJunc, destJunc, maxSpeed, contLimit, length, weather);
		
		limitCont = contLimit;
	}

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
		//TODO Preguntar si no hay que hacer velLimite = velMaxima
	}

	@Override
	int calculateVehicleSpeed(Vehicle v) {
		
		int s = carretera.getLimiteActual(), f = vehiculo.getGradoCont();
		
		v.setSpeed((int)(((11.0-f)/11.0)*s));
		
		return 0;
	}
}