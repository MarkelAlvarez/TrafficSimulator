package simulator.factories;

import org.json.JSONObject;
import simulator.model.*;

public class RoundRobinStrategyBuilder extends Builder<LightSwitchingStrategy> {

	//TODO: todo
	public RoundRobinStrategyBuilder(String type) {

		super(type);
	}

	@Override
	protected LightSwitchingStrategy createTheInstance(JSONObject data) {

		return null;
	}
}