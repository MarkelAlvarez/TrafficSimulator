package simulator.factories;

import org.json.JSONObject;
import simulator.model.*;

public class NewVehicleEventBuilder extends Builder<Event> {

	//TODO: todo
	NewVehicleEventBuilder(String type) {
	
		super(type);
	}

	@Override
	protected Event createTheInstance(JSONObject data) {
	
		return null;
	}
}