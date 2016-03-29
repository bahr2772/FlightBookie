package View;

import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.JLabel;
import java.awt.GridBagLayout;
import java.awt.GridBagConstraints;
import java.awt.Insets;
import javax.swing.JSpinner;
import javax.swing.JComboBox;
import javax.swing.JButton;

public class test extends JPanel {
	private JTextField depDate;
	private JTextField remainSeats;

	/**
	 * Create the panel.
	 */
	public test() {
		setLayout(null);
		
		JLabel lblNewLabel = new JLabel("Departure Date");
		lblNewLabel.setBounds(51, 254, 104, 16);
		add(lblNewLabel);
		
		JPanel panel = new JPanel();
		panel.setBounds(447, 5, 10, 10);
		add(panel);
		
		depDate = new JTextField();
		depDate.setBounds(159, 249, 130, 26);
		add(depDate);
		depDate.setColumns(10);
		
		JLabel lblDepartureTime = new JLabel("Departure Time");
		lblDepartureTime.setBounds(51, 292, 104, 16);
		add(lblDepartureTime);
		
		JSpinner depTime = new JSpinner();
		depTime.setBounds(159, 287, 104, 26);
		add(depTime);
		
		JLabel lblNewLabel_1 = new JLabel("Arraival City");
		lblNewLabel_1.setBounds(369, 290, 86, 16);
		add(lblNewLabel_1);
		
		JLabel lblDepartureCity = new JLabel("Departure City");
		lblDepartureCity.setBounds(369, 252, 97, 16);
		add(lblDepartureCity);
		
		JComboBox depCity = new JComboBox();
		depCity.setBounds(488, 248, 119, 27);
		add(depCity);
		
		JComboBox ArrCity = new JComboBox();
		ArrCity.setBounds(488, 286, 119, 27);
		add(ArrCity);
		
		JLabel lblFlightId = new JLabel("Flight Id:");
		lblFlightId.setBounds(92, 210, 61, 16);
		add(lblFlightId);
		
		JLabel flightId = new JLabel("New label");
		flightId.setBounds(158, 210, 105, 16);
		add(flightId);
		
		JLabel lblNewLabel_2 = new JLabel("Remaining Seats:");
		lblNewLabel_2.setBounds(323, 210, 119, 16);
		add(lblNewLabel_2);
		
		remainSeats = new JTextField();
		remainSeats.setBounds(447, 205, 130, 26);
		add(remainSeats);
		remainSeats.setColumns(10);
		
		JButton btnUpdateFlight = new JButton("Update Flight");
		btnUpdateFlight.setBounds(292, 388, 117, 29);
		add(btnUpdateFlight);
		
		JLabel lblNewLabel_3 = new JLabel("New label");
		lblNewLabel_3.setBounds(244, 48, 153, 150);
		add(lblNewLabel_3);
		
		JLabel lblFlightDuration = new JLabel("Flight Duration");
		lblFlightDuration.setBounds(61, 336, 119, 16);
		add(lblFlightDuration);
		
		JLabel dur = new JLabel("flight.getAllFlights.get(selected).getDuration;");
		dur.setBounds(189, 336, 61, 16);
		add(dur);

	}
}
