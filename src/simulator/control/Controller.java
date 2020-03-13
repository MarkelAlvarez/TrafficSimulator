package simulator.control;

import java.io.*;
import org.json.*;
import simulator.factories.*;
import simulator.misc.Pair;
import simulator.model.*;

public class Controller {

	private TrafficSimulator sim;
	private Factory<Event> eventosFact;
	
	public Controller(TrafficSimulator sim, Factory<Event> eventsFactory) {
		
		if (sim == null) {throw new IllegalArgumentException("El valor \"traffic simulator\" no pueden ser null.");}
		if (eventsFactory == null) {throw new IllegalArgumentException("El valor \"events factory\" no pueden ser null.");}
		
		this.sim = sim;
		this.eventosFact = eventsFactory;
	}
	
	public void loadEvents(InputStream in) {
		
		JSONObject jo = new JSONObject(new JSONTokener(in));
		JSONArray array = jo.getJSONArray("events");
		
		if (!jo.has("events"))
		{
			throw new IllegalArgumentException("JSONObject tag in Controller.java loadEvents should be \"events\".");
		}
		
		for (int i = 0; i < array.length(); i++)
		{
			sim.addEvent(eventosFact.createInstance(array.getJSONObject(i)));
		}
	}
	
	public void run(int n, OutputStream out) {
		
		for (int i = 0; i < n; i++) 
		{
			sim.advance();
		}
		//TODO: Escribir JSON
	}
	
	public void reset() {

		sim.reset();
	}
}