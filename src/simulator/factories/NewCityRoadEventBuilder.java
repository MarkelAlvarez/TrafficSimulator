package simulator.factories;

import org.json.JSONObject;
import simulator.model.Event;

public class NewCityRoadEventBuilder extends Builder<Event> {

	//TODO: todo
	public NewCityRoadEventBuilder(String type) {
	
		super(type);
	}

	@Override
	protected Event createTheInstance(JSONObject data) {
		
		return null;
	}
}