package simulator.view;

import java.util.List;

import javax.swing.table.AbstractTableModel;

import simulator.control.Controller;
import simulator.model.Event;
import simulator.model.RoadMap;
import simulator.model.TrafficSimObserver;

public class RoadsTableModel extends AbstractTableModel implements TrafficSimObserver {

	public RoadsTableModel(Controller _ctrl) {
		
		// TODO Auto-generated constructor stub
	}

	@Override
	public int getRowCount() {
		
		// TODO listaEventos.size()
		return 0;
	}

	@Override
	public int getColumnCount() {
		
		// TODO columnas.lenght()
		return 0;
	}

	public int getColumnName()
	{
		//TODO
		return 0;
	}

	@Override
	public Object getValueAt(int rowIndex, int columnIndex) {
		
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void onAdvanceStart(RoadMap map, List<Event> events, int time) {
		
		// TODO Auto-generated method stub
	}

	@Override
	public void onAdvanceEnd(RoadMap map, List<Event> events, int time) {
		
		// TODO Auto-generated method stub
	}

	@Override
	public void onEventAdded(RoadMap map, List<Event> events, Event e, int time) {
		
		// TODO Auto-generated method stub
	}

	@Override
	public void onReset(RoadMap map, List<Event> events, int time) {
		
		// TODO Auto-generated method stub
	}

	@Override
	public void onRegister(RoadMap map, List<Event> events, int time) {
		// TODO Auto-generated method stub

	}

	@Override
	public void onError(String err) {
		
		// TODO Auto-generated method stub
	}
}