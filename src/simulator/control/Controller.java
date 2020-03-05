package simulator.control;

import simulator.factories.*;
import simulator.model.*;

public class Controller {

	private TrafficSimulator sim;
	private Factory<Event> eventosFact;
	
	public Controller(TrafficSimulator sim, Factory<Event> eventsFactory)
	{
	// TODO complete
	}
	
	public void loadEvents(InputStream in) {
		//TODO: todo
		JSONObject jo = new JSONObject(new JSONTokener(in));
	}
	
	public void run(int n, OutputStream out) {
		//TODO: todo

	}
	
	public void reset() {
		//TODO: todo

	}
}