package simulator.view;

import java.awt.*;
import javax.swing.*;

import simulator.model.Vehicle;

public class ChangeCO2ClassDialog extends JDialog {

	private JPanel emergente;
	private JLabel texto;
	private JComboBox<Vehicle> vehicle;
	private JComboBox<cont[]> co2;
	private JSpinner ticks;

	private int cont[] = {0, 1, 2, 3, 4, 5, 6, 7, 8, 9, 10};
	
	private JPanel botones;
	private JButton ok;
	private JButton cancel;

	private int estado = 0; //cambiar valor segun si es ok o cancel

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
		
		texto = new JLabel("Select your favorite");
		emergente.add(texto);
		emergente.add(Box.createRigidArea(new Dimension(0, 20)));
		
		
		
	}
}