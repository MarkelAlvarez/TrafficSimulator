package simulator.view;

import java.awt.*;
import java.io.File;
import java.io.IOException;
import java.util.*;
import java.util.List;

import javax.imageio.ImageIO;
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
	//array de Image para almacenar las imagenes y luego cogerlas en initGui() para cargar las imagenes con loadImages()	
	private JLabel coche;
	
	private static final int _JRADIUS = 10;
	private static final Color _BG_COLOR = Color.WHITE;
	private static final Color _JUNCTION_COLOR = Color.BLUE;
	private static final Color _JUNCTION_LABEL_COLOR = new Color(200, 100, 0);
	private static final Color _GREEN_LIGHT_COLOR = Color.GREEN;
	private static final Color _RED_LIGHT_COLOR = Color.RED;
	private RoadMap _map;
	private Image _car;
	
	public MapByRoadComponent(Controller ctrl) {
		
		initGUI();
		setPreferredSize(new Dimension(300, 200));
		ctrl.addObserver(this);	
	}

	private void initGUI() {
		
		_car = loadImage("car.png");
	}

	public void paintComponent(Graphics graphics) {
		
		super.paintComponent(graphics);
		
		Graphics2D g = (Graphics2D) graphics;
		g.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
		g.setRenderingHint(RenderingHints.KEY_TEXT_ANTIALIASING, RenderingHints.VALUE_TEXT_ANTIALIAS_ON);

		// clear with a background color
		g.setColor(_BG_COLOR);
		g.clearRect(0, 0, getWidth(), getHeight());

		if (_map == null || _map.getJunctions().size() == 0/*TODO: cambiar esto*/)
		{
			g.setColor(Color.red);
			g.drawString("No map by road yet!", getWidth() / 2 - 50, getHeight() / 2);
		}
		else
		{
			updatePrefferedSize();
			//drawMap(g);
		}
	}

	/*private void drawMap(Graphics g) {
		
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
	}*/
	
	// this method is used to update the preffered and actual size of the component,
	// so when we draw outside the visible area the scrollbars show up
	private void updatePrefferedSize() {
		
		int maxW = 200;
		int maxH = 200;
		
		for (Junction j: _map.getJunctions())
		{
			maxW = Math.max(maxW, j.getX());
			maxH = Math.max(maxH, j.getY());
		}
		
		maxW += 20;
		maxH += 20;
		
		if ((maxW > getWidth()) || (maxH > getHeight()))
		{
			setPreferredSize(new Dimension(maxW, maxH));
			setSize(new Dimension(maxW, maxH));
		}
	}

	// This method draws a line from (x1,y1) to (x2,y2) with an arrow.
	// The arrow is of height h and width w.
	// The last two arguments are the colors of the arrow and the line
	private void drawLineWithArrow(Graphics g, int x1, int y1, int x2, int y2, int w, int h, Color lineColor, Color arrowColor) {

		int dx = x2 - x1, dy = y2 - y1;
		double D = Math.sqrt(dx * dx + dy * dy);
		double xm = D - w, xn = xm, ym = h, yn = -h, x;
		double sin = dy / D, cos = dx / D;

		x = xm * cos - ym * sin + x1;
		ym = xm * sin + ym * cos + y1;
		xm = x;

		x = xn * cos - yn * sin + x1;
		yn = xn * sin + yn * cos + y1;
		xn = x;

		int[] xpoints = { x2, (int) xm, (int) xn };
		int[] ypoints = { y2, (int) ym, (int) yn };

		g.setColor(lineColor);
		g.drawLine(x1, y1, x2, y2);
		g.setColor(arrowColor);
		g.fillPolygon(xpoints, ypoints, 3);
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

	public void update(RoadMap map) {
		
		_map = map;
		repaint();
	}

	@Override
	public void onAdvanceStart(RoadMap map, List<Event> events, int time) {
	}

	@Override
	public void onAdvanceEnd(RoadMap map, List<Event> events, int time) {
		
		update(map);
	}

	@Override
	public void onEventAdded(RoadMap map, List<Event> events, Event e, int time) {
		
		update(map);
	}

	@Override
	public void onReset(RoadMap map, List<Event> events, int time) {
		
		update(map);
	}

	@Override
	public void onRegister(RoadMap map, List<Event> events, int time) {
		
		update(map);
	}

	@Override
	public void onError(String err) {
		
	}
}