package simulator.view;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.*;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;

import extra.dialog.MyDialogWindow;
import simulator.model.Vehicle;

public class ChangeCO2ClassDialog extends JDialog {

	private JPanel emergente;
	private JLabel texto;
	private JLabel textoVehicle;
	private JComboBox<> vehicle;
	private JLabel textoCO2;
	private JComboBox<> co2;
	private JLabel textoTicks;
	private JSpinner ticks;
	
	private JPanel botones;
	private JButton ok;
	private JButton cancel;

	private int estado = 0;

	//TODO: boton cancel y ok: setVisile(false) para esconder la ventana o dispose() para quitarla por completo (hay que distingir )
	// casting a vehicle para el comboBox para obtener lo que queremos
	public ChangeCO2ClassDialog(Frame padre) {
		
		super(padre, true);
		initGUI();
	}

	private void initGUI() {
		
		setTitle("Change CO2 Class");
		
		emergente = new JPanel();
		emergente.setLayout(new BoxLayout(emergente, BoxLayout.Y_AXIS));
		setContentPane(emergente);
		
		texto = new JLabel("Schedule an event to change the CO2 class of a vehicle after a given number os simulation ticks form now.");
		emergente.add(texto);
		emergente.add(Box.createRigidArea(new Dimension(0, 20)));		
	
		botones = new JPanel();
		botones.setAlignmentX(CENTER_ALIGNMENT);
		emergente.add(botones);
		
		textoVehicle = new JLabel("Vehicle: ", JLabel.CENTER);
		vehicle = new JComboBox<>();
		textoCO2 = new JLabel("CO2 Class: ", JLabel.CENTER);
		co2 = new JComboBox<>;
		ticks = new JSpinner();
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
		
		botones.add(textoTicks);
		botones.add(ticks);
		
		cancel = new JButton("Cancel");
		cancel.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				estado = 0;
				ChangeCO2ClassDialog.this.setVisible(false);
			}
		});
		botones.add(cancel);

		ok = new JButton("OK");
		ok.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				if (_dishesModel.getSelectedItem() != null) {
					estado = 1;
					ChangeCO2ClassDialog.this.setVisible(false);
				}
			}
		});
		botones.add(ok);

		setPreferredSize(new Dimension(500, 200));
		pack();
		setResizable(false);
		setVisible(false);
	}
	
	public int open() {
		
		setLocation(getParent().getLocation().x + 10, getParent().getLocation().y + 10);
		setVisible(true);
		return estado;
	}
}