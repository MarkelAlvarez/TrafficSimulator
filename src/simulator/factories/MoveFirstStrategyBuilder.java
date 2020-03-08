package simulator.factories;

import org.json.JSONObject;
import simulator.model.*;

public class MoveFirstStrategyBuilder extends Builder<DequeuingStrategy> {

	//TODO: todo
	public MoveFirstStrategyBuilder(String type) {
	
		super(type);
	}

	@Override
	protected DequeuingStrategy createTheInstance(JSONObject data) {
	
		return null;
	}
}