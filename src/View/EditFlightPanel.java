package View;

import java.awt.*;
import java.awt.event.*;
import java.util.Calendar;
import java.util.Date;

import javax.swing.*;


import Controller.InputWaiter;
import Model.Flights;
import sql.EditFlight;

public class EditFlightPanel {
	private int selector;

	private JTextField depDate;
	private JTextField remainSeats;
	
	private JLabel flightCreated,dur;
	private JTextField dep_time;
	private JComboBox<String> depCity, ArrCity;
	private JSpinner depTime;
	String[] dep_cityList = { "ORD", "STL", "ATL", "LAX", "DFW", "JFK", "DEN", "SFO", "CLT", "LAS", "PHX", "IAH", "MIA", "SEA"
			,"EWR", "MCO", "MSP", "DTW", "BOS", "PHL", "FLL", "MDW", "TPA", "HOU", "ONT" };
	String[] arr_cityList = dep_cityList;
	String[] dur_list = { "1", "1.5", "2","2.5","3","3.5","4","4.5","5","5.5","6","6.5","7","7.5","8"};


	public  EditFlightPanel(MakeFrame frame,  InputWaiter wait, Flights flight){
		int selected = flight.getSelectedFlight();
		System.out.println(flight.getAllFlights().get(selected).getDepart_date().toString());

		//		============	Make Frame 	===================

		frame.setSize(700,500);
		frame.setLocationRelativeTo(null);
		frame.getBar().repaint();
		frame.getBar().revalidate();

		try {
			Thread.sleep(50);
		} catch (InterruptedException e1) {
			e1.printStackTrace();
		}

		frame.repaint();
		frame.toFront();


		//		============			Date picker 				===================

		depTime = new JSpinner();
		SpinnerDateModel spinnermodel1 = new SpinnerDateModel();
		spinnermodel1.setCalendarField(Calendar.MINUTE);
		depTime.setModel(spinnermodel1);
		depTime.setEditor(new JSpinner.DateEditor(depTime , " HH:mm"));
		depTime.setPreferredSize(new Dimension(75,200));
		

		//		============			Make Panel 					===================
		JPanel startPane = new JPanel();
		startPane.setLayout(null);

		//		============	Make, Set and Position Components. 	===================

		
		JLabel lblNewLabel = new JLabel("Departure Date");
		lblNewLabel.setBounds(51, 254, 104, 16);
		startPane.add(lblNewLabel);
		
		depDate = new JTextField();
		depDate.setText(flight.getAllFlights().get(selected).getDepart_date().toString());
		depDate.setBounds(159, 249, 130, 26);
		startPane.add(depDate);
		depDate.setColumns(10);
		
		JLabel lblDepartureTime = new JLabel("Departure Time");
		lblDepartureTime.setBounds(51, 292, 104, 16);
		startPane.add(lblDepartureTime);
		
		
		depTime.setBounds(159, 287, 104, 26);
		startPane.add(depTime);
		
		JLabel lblNewLabel_1 = new JLabel("Arraival City");
		lblNewLabel_1.setBounds(369, 290, 86, 16);
		startPane.add(lblNewLabel_1);
		
		JLabel lblDepartureCity = new JLabel("Departure City");
		lblDepartureCity.setBounds(369, 252, 97, 16);
		startPane.add(lblDepartureCity);
		
		depCity = new JComboBox(dep_cityList);
		depCity.setSelectedItem(flight.getAllFlights().get(selected).getDepart_city());
		depCity.setBounds(488, 248, 119, 27);
		startPane.add(depCity);
		
		ArrCity = new JComboBox(arr_cityList);
		ArrCity.setSelectedItem(flight.getAllFlights().get(selected).getArrival_city());
		ArrCity.setBounds(488, 286, 119, 27);
		startPane.add(ArrCity);
		
		JLabel lblFlightId = new JLabel("Flight Id:");
		lblFlightId.setBounds(92, 210, 61, 16);
		startPane.add(lblFlightId);
		
		JLabel flightId = new JLabel(Integer.toString(flight.getAllFlights().get(selected).getFlightId()));
		flightId.setBounds(158, 210, 105, 16);
		startPane.add(flightId);
		
		JLabel lblNewLabel_2 = new JLabel("Remaining Seats:");
		lblNewLabel_2.setBounds(323, 210, 119, 16);
		startPane.add(lblNewLabel_2);
		
		remainSeats = new JTextField();
		remainSeats.setText(Integer.toString(flight.getAllFlights().get(selected).getRemainSeats()));
		remainSeats.setBounds(447, 205, 130, 26);
		startPane.add(remainSeats);
		remainSeats.setColumns(10);
		
		System.out.println(flight.getAllFlights().get(selected).getRemainSeats());
		
		JButton btnUpdateFlight = new JButton("Update Flight");
		btnUpdateFlight.setBounds(292, 348, 117, 29);
		startPane.add(btnUpdateFlight);

		String wPic = ("./images/logo.png");
		JLabel wIcon = new JLabel(new ImageIcon(wPic));
		wIcon.setBounds(278, 19, 153, 150);
		startPane.add(wIcon);

		JLabel lblFlightDuration = new JLabel("Flight Duration");
		lblFlightDuration.setBounds(61, 336, 119, 16);
		startPane.add(lblFlightDuration);
		
		dur = new JLabel();
		dur.setText(Integer.toString(flight.getAllFlights().get(selected).getFlightDuration()));
		dur.setBounds(189, 336, 61, 16);
		startPane.add(dur);
		
		JLabel message = new JLabel();
		message.setBounds(292, 388, 117, 29);
		startPane.add(message);


		ActionListener updateFight = new ActionListener(){
			public void actionPerformed(ActionEvent e) {

				String dep_city = (String) depCity.getSelectedItem();
				String arrCity = (String) ArrCity.getSelectedItem();
 
				String dep_date = depDate.getText().replaceAll("-", "");
				
				Date dateFromDateChooser1 = (Date) depTime.getModel().getValue();
				String dep_time = String.format("%1$tH%1$tM", dateFromDateChooser1);

				
System.out.println(dep_time);

				EditFlight edit = new EditFlight();
				edit.editFlight(flight, selected, dep_date, dep_city, arrCity, dep_time, dur.getText(), remainSeats.getText());

//				flightCreated.setText("Flight Created:  "
//						+ dep_city + " to " + arrCity + " on " + depDate + " at " + dep_time.getText() + " with duration of  " +
//						dur.getText() + " hours.");


				System.out.println(""+
						"Departure City: " + dep_city + 
						"\nArrival City: " + arrCity +
						"\nDepature Date: " + depDate.getText() + 
						"\nDepature Time: " + dep_time +
						"\nDuration Time: " + dur.getText());


				setSelector(11);



			}
		};


		btnUpdateFlight.addActionListener(updateFight);


		//		============		Set and Remove panel from frame		===================

		frame.add(startPane);
		try {
			Thread.sleep(20);
		} catch (InterruptedException e1) {
			e1.printStackTrace();
		}
		frame.repaint();
		frame.setVisible(true);
		startPane.setVisible(true);
		startPane.setVisible(true);

		wait.pause();
		wait.run();

		startPane.setVisible(false);



	}

	public int getSelector() {
		return selector;
	}

	public void setSelector(int selector) {
		this.selector = selector;
	}



}
