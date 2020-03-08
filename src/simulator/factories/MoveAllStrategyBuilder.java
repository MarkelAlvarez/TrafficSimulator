package simulator.factories;

import org.json.JSONObject;
import simulator.model.*;

public class MoveAllStrategyBuilder extends Builder<DequeuingStrategy> {

	//TODO: todo
	public MoveAllStrategyBuilder(String type) {
	
		super(type);
	}

	@Override
	protected DequeuingStrategy createTheInstance(JSONObject data) {
	
		return null;
	}
}