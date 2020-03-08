package simulator.factories;

import org.json.JSONObject;
import simulator.model.*;

public class SetWeatherEventBuilder extends Builder<Event> {

	//TODO: todo
	SetWeatherEventBuilder(String type) {
	
		super(type);
	}

	@Override
	protected Event createTheInstance(JSONObject data) {
	
		return null;
	}
}