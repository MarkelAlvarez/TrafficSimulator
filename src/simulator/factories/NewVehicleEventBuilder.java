package simulator.factories;

import java.util.List;

import org.json.*;
import simulator.model.*;

public class NewVehicleEventBuilder extends Builder<Event> {

	private int time;
	private String id;
	private int gradoCont;
	private int velocMaxima;
	private List<String> itinerario;
	
	public NewVehicleEventBuilder() {
	
		super("new_vehicle");
	}

	@Override
	protected Event createTheInstance(JSONObject data) {
	
		time = data.getInt("time");
		id = data.getString("id");
		velocMaxima = data.getInt("maxspeed");
		gradoCont = data.getInt("class");
		
		for (int i = 0; i < data.getJSONArray("itinerary").length(); i++)
		{
			itinerario.add(data.getJSONArray("itinerary").getString(i));
		}
		
		return new NewVehicleEvent(time, id, velocMaxima, gradoCont, itinerario);
	}
}