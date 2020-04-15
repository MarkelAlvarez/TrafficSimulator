package simulator.view;

import java.awt.*;
import java.util.*;
import java.util.List;

import javax.swing.*;

import simulator.control.Controller;
import simulator.model.*;
import simulator.model.Event;

public class MapByRoadComponent extends JComponent implements TrafficSimObserver {

	private int x1;
	private int x2;
	private int y;
	private int i;
	
	private int xCoche;
	private RoadMap roadMap;
	//array de Image para almacenar las imagenes y luego cogerlas en initGui() para cargar las imagenes con loadImages()
	
	private JColorChooser selectorColor;
	private Graphics2D g;
	
	private JLabel coche;
	
	
	public MapByRoadComponent(Controller _ctrl) {
		
		// TODO Auto-generated constructor stub
		setPreferredSize(new Dimension(300, 200));
		ctrl.addObserver(this);	
	}

	public void paintComponent() {

		/*super.paintComponent(graphics);
		
		Graphics2D g = (Graphics2D) graphics;
		g.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
		g.setRenderingHint(RenderingHints.KEY_TEXT_ANTIALIASING, RenderingHints.VALUE_TEXT_ANTIALIAS_ON);

		// clear with a background color
		g.setColor(_BG_COLOR);
		g.clearRect(0, 0, getWidth(), getHeight());

		if (_map == null || _map.getJunctions().size() == 0)
		{
			g.setColor(Color.red);
			g.drawString("No map yet!", getWidth() / 2 - 50, getHeight() / 2);
		}
		else
		{
			drawMap(g);
		}*/
	}

	private void drawMap(Graphics g) {
		
		int A, B;
		 
		selectorColor = new JColorChooser();
		Color color = JColorChooser.showDialog(this, "Color de fondo", new Color(255, 255, 255));

		//TODO: como obtener i (lista de carreteras)
		i = 50;
		x1 = 50;
		x2 = getWidth();
		y = (i+1)*50;
		g.drawLine(x1, y, x2, y);
		
		//TODO: circulos que representas los cruces
		g.drawOval(x, y, width, height);
		
		//TODO: A y B como acceder
		coche = new JLabel(new ImageIcon("./resources/icons/car.png"));
		xCoche = x1+(int)((x2-x1)*((double)A/(double)B));

		drawVehicle(g);
	}

	private void drawVehicle(Graphics g) {
		
		int A, B;
		 
		selectorColor = new JColorChooser();
		Color color = JColorChooser.showDialog(this, "Color de fondo", new Color(255, 255, 255));
		
		//TODO: como obtener i (lista de carreteras)
		i = 50;
		x1 = 50;
		x2 = getWidth();
		y = (i+1)*50;
		g.drawLine(x1, y, x2, y);
		
		//TODO: circulos que representas los cruces
		g.drawOval(x, y, width, height);
		
		//TODO: A y B como acceder
		coche = new JLabel(new ImageIcon("./resources/icons/car.png"));
		xCoche = x1+(int)((x2-x1)*((double)A/(double)B));
	}
	
	// loads an image from a file
	private Image loadImage(String img) {
		
		Image i = null;
		
		try {
			return ImageIO.read(new File("resources/icons/" + img));
		} catch (IOException e) {
		}
		
		return i;
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