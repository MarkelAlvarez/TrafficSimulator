package simulator.model;

import java.util.List;
import org.json.JSONObject;

import simulator.misc.SortedArrayList;

public class TrafficSimulator implements Observable<TrafficSimObserver> {

	private RoadMap mapaCarreteras;
	private List<Event> listaEventos;
	private int time;
	
	public TrafficSimulator() {

		listaEventos = new SortedArrayList<Event>();
		mapaCarreteras = new RoadMap();
		time = 0;
	}
	
	public void addEvent(Event e) {
		
		listaEventos.add(e);
		
		onEventAdded(mapaCarreteras, listaEventos, e, time);
	}
	
	public void advance() {
		
		time++;
		
		onAdvanceStart(mapaCarreteras, listaEventos, time);
		
		//TODO: ¿esta wea esta bien? y la excepcion de onError se lanza dentro del metodo?
		try {
			while (listaEventos.size() > 0 && listaEventos.get(0).getTime() == time)
			{
		        listaEventos.remove(0).execute(mapaCarreteras);
		    }
			
			for (Junction junction : mapaCarreteras.getJunctions())
			{
				junction.advance(time);
			}
			for (Road road : mapaCarreteras.getRoads())
			{
				road.advance(time);
			}
		} catch (Exception e) {
			onError(e.getMessage());
		}
		
		onAdvanceEnd(mapaCarreteras, listaEventos, time);
	}
	
	public void reset() {
		
		mapaCarreteras.reset();
		listaEventos.clear();
		time = 0;
		
		onReset(mapaCarreteras, listaEventos, time);
	}
	
	public JSONObject report() {
		
		JSONObject json = new JSONObject();
		
		json.put("time", time);
		json.put("state", mapaCarreteras.report());
		
		return json;
	}

	@Override
	public void addObserver(TrafficSimObserver o) {
		
		// TODO Auto-generated method stub
		onRegister(mapaCarreteras, listaEventos, time);
	}

	@Override
	public void removeObserver(TrafficSimObserver o) {
		
		// TODO Auto-generated method stub
	}
}