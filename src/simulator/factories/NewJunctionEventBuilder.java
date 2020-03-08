package simulator.factories;

import org.json.JSONObject;
import simulator.model.*;

public class NewJunctionEventBuilder extends Builder<Event> {

	//TODO: todo
	public NewJunctionEventBuilder(Factory<LightSwitchingStrategy> lssFactory, Factory<DequeuingStrategy> dqsFactory) {
		
	}

	@Override
	protected Event createTheInstance(JSONObject data) {
	
		return null;
	}
}