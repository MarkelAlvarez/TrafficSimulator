package simulator.factories;

import org.json.JSONObject;

import simulator.model.*;

public class RoundRobinStrategyBuilder extends Builder<LightSwitchingStrategy> {

	public RoundRobinStrategyBuilder(String type) {
		super(type);
		// TODO: no sé si está bien
	}

	@Override
	protected LightSwitchingStrategy createTheInstance(JSONObject data) {
		// TODO Auto-generated method stub
		return null;
	}

}
