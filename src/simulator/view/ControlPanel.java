package simulator.view;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.io.File;
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

	private JToolBar toolbar;
	private JButton botonFichero;
	private JButton botonCO2;
	private JButton botonWeather;
	private JButton run;
	private JButton stop;
	private JButton botonExit;
	
	private JLabel textoTicks;
	private JSpinner ticks;
	
	private JFileChooser selectorFichero;
	private ChangeCO2ClassDialog changeCO2;
	private ChangeWeatherDialog changeWeather;
	private JDialog exit;
	
	private JSeparator separador1;
	private JSeparator separador2;
	private JSeparator separador3;
	private JSeparator separador4;
	
	private Controller _ctrl;
	private boolean _stopped = false;
	
	public ControlPanel(Controller _ctrl) {
		
		this._ctrl = _ctrl;
		
		toolbar = new JToolBar();
		this.add(toolbar);

		separador1 = new JSeparator(SwingConstants.VERTICAL);
		toolbar.add(separador1);

		botonFichero = new JButton();
		botonFichero.setActionCommand("cargar");
		botonFichero.setIcon(new ImageIcon("./resources/icons/open.png"));
		toolbar.add(botonFichero);
		
		separador2 = new JSeparator(SwingConstants.VERTICAL);
		toolbar.add(separador2);
		
		botonCO2 = new JButton();
		botonCO2.setActionCommand("co2");
		botonCO2.setIcon(new ImageIcon("./resources/icons/co2class.png"));
		toolbar.add(botonCO2);
		
		botonWeather = new JButton();
		botonWeather.setActionCommand("weather");
		botonWeather.setIcon(new ImageIcon("./resources/icons/weather.png"));
		toolbar.add(botonWeather);
		
		separador3 = new JSeparator(SwingConstants.VERTICAL);
		toolbar.add(separador3);
		
		run = new JButton();
		run.setActionCommand("run");
		run.setIcon(new ImageIcon("./resources/icons/run.png"));
		toolbar.add(run);
		
		stop = new JButton();
		stop.setActionCommand("stop");
		stop.setIcon(new ImageIcon("./resources/icons/stop.png"));
		toolbar.add(stop);
		
		textoTicks = new JLabel("Ticks: ", JLabel.CENTER);
		ticks = new JSpinner(new SpinnerNumberModel(10, 1, 300, 1));
		// TODO: hay que cambiar aqui el valor de los ticks?
		ticks.addChangeListener(new ChangeListener() {
			@Override
			public void stateChanged(ChangeEvent e) {
				// Ponemos el valor del JSpinner en el JTextField
				Main.setTicks((Integer) ticks.getValue());
			}
		});
		toolbar.add(ticks);
		
		toolbar.add(Box.createGlue());

		separador4 = new JSeparator(SwingConstants.VERTICAL);
		toolbar.add(separador4);
		
		botonExit = new JButton();
		botonExit.setActionCommand("exit");
		botonExit.setIcon(new ImageIcon("./resources/icons/exit.png"));
		toolbar.add(botonExit);
		
		selectorFichero = new JFileChooser();
		selectorFichero.setCurrentDirectory(new File("./resources/examples/"));
		selectorFichero.setMultiSelectionEnabled(false);
		selectorFichero.setFileFilter(new FileNameExtensionFilter("JavaScript Object Notation (JSON)", "json"));
		toolbar.add(selectorFichero);
	}

	public void actionPerformed(ActionEvent e) {
		
		if (e.getActionCommand().equals("cargar"))
		{
			int ret = selectorFichero.showOpenDialog(this);
			if (ret == JFileChooser.APPROVE_OPTION)
			{
				Main.set_inFile(selectorFichero.getSelectedFile());
			}
			else
			{
				JOptionPane.showMessageDialog(this, "Se ha pulsado cancelar o ha ocurrido un error.");
			}
		}
		else if (e.getActionCommand().equals("co2"))
		{
			//TODO: clase de co2 selector. Los ticks se suman a lo que ya hay (time)
		}
		else if (e.getActionCommand().equals("weather"))
		{
			//TODO: clase de weather selector
		}
		else if (e.getActionCommand().equals("run"))
		{
			_stopped = false; //TODO: comprobar 
			run_sim((Integer) ticks.getValue());
		}
		else if (e.getActionCommand().equals("stop"))
		{
			stop();
		}
		else if (e.getActionCommand().equals("exit"))
		{
			int ret = JOptionPane.showConfirmDialog(this, "¿Desea cerrar el programa?", "Salir", JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE, null);
			
			if (ret == 0)
			{
				System.exit(0);
			}
		}
	}
	
	private void run_sim(int n) {
		
		if (n > 0 && !_stopped)
		{
			try {
				_ctrl.run(1, null);
			} catch (Exception e ) {
				// TODO show error message
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