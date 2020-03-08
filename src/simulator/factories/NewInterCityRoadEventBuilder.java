package simulator.factories;

import org.json.JSONObject;
import simulator.model.*;

public class NewInterCityRoadEventBuilder extends Builder<Event> {

	//TODO: todo
	public NewInterCityRoadEventBuilder(String type) {
	
		super(type);
	}

	@Override
	protected Event createTheInstance(JSONObject data) {

		return null;
	}
}