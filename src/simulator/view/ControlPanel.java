package simulator.view;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.InputStream;
import java.util.*;
import java.util.List;

import javax.swing.*;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;
import javax.swing.filechooser.FileNameExtensionFilter;

import simulator.control.Controller;
import simulator.launcher.Main;
import simulator.model.*;
import simulator.model.Event;

public class ControlPanel extends JPanel implements TrafficSimObserver {

	private JToolBar barraHerramientas;
	private JButton botonFichero;
	private JButton botonCO2;
	private JButton botonWeather;
	private JButton run;
	private JButton stop;
	private JButton botonExit;
	
	private JLabel textoTicks;
	private JSpinner ticks;
	private int _ticks;
	
	private JFileChooser selectorFichero;
	private ChangeCO2ClassDialog changeCO2;
	private ChangeWeatherDialog changeWeather;
	private JDialog exit;
	
	private Controller ctrl;
	private boolean _stopped;
	
	public ControlPanel(Controller _ctrl) {
		
		ctrl = _ctrl;
		_stopped = true;
		initGUI();
		ctrl.addObserver(this);	
	}
	
	private void initGUI() {
		
		barraHerramientas = new JToolBar();
		this.setLayout(new BorderLayout());
		this.add(barraHerramientas, BorderLayout.PAGE_START);

		barraHerramientas.addSeparator();
		botonCargaFichero();
		barraHerramientas.addSeparator();
		botonCO2();
		botonWeather();	
		barraHerramientas.addSeparator();
		botonRun();
		botonStop();
		spinnerTicks();
		barraHerramientas.add(Box.createHorizontalGlue());
		barraHerramientas.addSeparator();
		botonExit();
	}

	private void botonCargaFichero() {
		
		selectorFichero = new JFileChooser();
		selectorFichero.setDialogTitle("Carga de fichero de datos");
		selectorFichero.setCurrentDirectory(new File("./resources/examples/"));
		selectorFichero.setMultiSelectionEnabled(false);
		selectorFichero.setFileFilter(new FileNameExtensionFilter("JavaScript Object Notation (JSON)", "json"));
		
		botonFichero = new JButton();
		botonFichero.setToolTipText("Carga los archivos de datos a la aplicación");
		botonFichero.setIcon(new ImageIcon("./resources/icons/open.png"));
		botonFichero.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				updateUI();
				int ret = selectorFichero.showOpenDialog(selectorFichero);
				if (ret == JFileChooser.APPROVE_OPTION)
				{
					InputStream in;
					try {
						in = new FileInputStream(selectorFichero.getSelectedFile());
						ctrl.loadEvents(in);
					} catch (FileNotFoundException e1) {
						JOptionPane.showMessageDialog(null, "Error al cargar el archivo");
					}					
				}
				else
				{
					JOptionPane.showMessageDialog(null, "Se ha pulsado cancelar o ha ocurrido un error.");
				}			
			}
		});
		
		barraHerramientas.add(botonFichero);
	}
	
	private void botonCO2() {
		
		botonCO2 = new JButton();
		botonCO2.setIcon(new ImageIcon("./resources/icons/co2class.png"));
		botonCO2.setToolTipText("Modificar información de la contaminación");
		botonFichero.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				cambiarCO2();
			}
		});
		
		barraHerramientas.add(botonCO2);
	}
	
	protected void cambiarCO2() {
		
		int estado = 0;
		changeCO2 = new ChangeCO2ClassDialog(null); //TODO: null?
		
		estado = changeCO2.open();
		if (estado != 0)
		{
			//TODO: cambiar el co2
			//System.out.println("Your favorite dish is: " + dialog.getDish());
		}
	}

	private void botonWeather() {
		
		botonWeather = new JButton();
		botonWeather.setToolTipText("Modificar información del clima");
		botonWeather.setIcon(new ImageIcon("./resources/icons/weather.png"));
		botonWeather.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				cambiarTiempo();				
			}
		});
		barraHerramientas.add(botonWeather);
	}
	

	protected void cambiarTiempo() {
		
		int estado = 0;
		changeWeather = new ChangeWeatherDialog(null); //TODO: null?
		
		estado = changeCO2.open();
		if (estado != 0)
		{
			//TODO: cambiar el co2
			//System.out.println("Your favorite dish is: " + dialog.getDish());
		}
	}

	private void botonRun() {
		
		run = new JButton();
		run.setToolTipText("Iniciar simulación");
		run.setIcon(new ImageIcon("./resources/icons/run.png"));
		run.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				enableToolBar(false);
				_stopped = false; //TODO: comprobar 
				run_sim((Integer) ticks.getValue());			
			}
		});
		
		barraHerramientas.add(run);
	}

	private void botonStop() {

		stop = new JButton();
		stop.setToolTipText("Parar simulación");
		stop.setIcon(new ImageIcon("./resources/icons/stop.png"));
		stop.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				stop();		
			}
		});
		barraHerramientas.add(stop);
	}
	
	private void spinnerTicks() {
		
		textoTicks = new JLabel("Ticks: ", JLabel.CENTER);
		
		ticks = new JSpinner(new SpinnerNumberModel(10, 1, 300, 1));
		ticks.setMinimumSize(new Dimension(80, 30));
		ticks.setMaximumSize(new Dimension(200, 30));
		ticks.setPreferredSize(new Dimension(80, 30));
		ticks.addChangeListener(new ChangeListener() {
			@Override
			public void stateChanged(ChangeEvent e) {

				_ticks = Integer.valueOf(ticks.getValue().toString());
			}
		});
		
		barraHerramientas.add(textoTicks);
		barraHerramientas.add(ticks);
		
	}
	
	private void botonExit() {

		botonExit = new JButton();
		botonExit.setAlignmentX(RIGHT_ALIGNMENT);
		botonExit.setToolTipText("Cerrar aplicación");
		botonExit.setIcon(new ImageIcon("./resources/icons/exit.png"));
		botonExit.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				int ret = JOptionPane.showConfirmDialog(null, "¿Desea cerrar el programa?", "Salir", JOptionPane.YES_NO_OPTION);
				
				if (ret == 0)
				{
					System.exit(0);
				}
			}
		});
		
		barraHerramientas.add(botonExit);
	}
	
	private void run_sim(int n) {
		
		if (n > 0 && !_stopped)
		{
			try {
				ctrl.run(1, null);
			} catch (Exception e ) {
				JOptionPane.showMessageDialog(null, "Se ha producido un error");
				enableToolBar(true);
				_stopped = true;
				return;
			}
			
			SwingUtilities.invokeLater(new Runnable() {
				@Override
				public void run() {		
					run_sim( n - 1);
				}
			});
		}
		else
		{
			enableToolBar(true);
			_stopped = true;
		}
	}
	
	private void enableToolBar(boolean b) {
		
		botonFichero.setEnabled(b);
		botonCO2.setEnabled(b);
		botonWeather.setEnabled(b);
		run.setEnabled(b);
		stop.setEnabled(b);
		botonExit.setEnabled(b);
		ticks.setEnabled(b);
	}

	private void stop() {
		
		_stopped = true ;
	}
	
	@Override
	public void onAdvanceStart(RoadMap map, List<Event> events, int time) {
		
		// TODO Auto-generated method stub
	}

	@Override
	public void onAdvanceEnd(RoadMap map, List<Event> events, int time) {
		
		// TODO Auto-generated method stub
		mapa = map;
		tiempo = time;	
	}

	@Override
	public void onEventAdded(RoadMap map, List<Event> events, Event e, int time) {
		
		// TODO Auto-generated method stub
		mapa = map;
		tiempo = time;
	}

	@Override
	public void onReset(RoadMap map, List<Event> events, int time) {
		
		// TODO Auto-generated method stub
	}

	@Override
	public void onRegister(RoadMap map, List<Event> events, int time) {
		
		// TODO Auto-generated method stub
		mapa = map;
		tiempo = time;
	}

	@Override
	public void onError(String err) {
		
		// TODO Auto-generated method stub
	}
}