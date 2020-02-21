package simulator.model;

public class InterCityRoad extends Road {

	private Vehicle vehiculo;
	private Weather clima;
	private int contTotal;
	
	public InterCityRoad(String id, Junction srcJunc, Junction destJunc, int maxSpeed, int contLimit, int length, Weather weather) {

		super(id, srcJunc, destJunc, maxSpeed, contLimit, length, weather);
	}

	@Override
	void reduceTotalContamination() {
		
		int tc = vehiculo.getContTotal(), x = weatherANumero();
		
		contTotal = (int)((100.0-x)/100.0)*tc;
	}

	@Override
	void updateSpeedLimit() {
		
		
	}

	@Override
	int calculateVehicleSpeed(Vehicle v) {
		// TODO Auto-generated method stub
		return 0;
	}
	
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