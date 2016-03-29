package View;

import java.awt.Dimension;
import java.awt.Frame;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Calendar;
import java.util.Date;
import java.util.Properties;

import javax.swing.ButtonGroup;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JSpinner;
import javax.swing.SpinnerDateModel;

import org.jdatepicker.impl.JDatePanelImpl;
import org.jdatepicker.impl.JDatePickerImpl;
import org.jdatepicker.impl.UtilDateModel;

import Controller.InputWaiter;
import sql.MakeFlight;

public class CreateFlightPanel {
	private int selector;

	private JLabel flightCreated;
	private JButton searchBtn;
	private JComboBox<String> dep_city, arr_city, dur;
	private ButtonGroup group ;
	private JSpinner dep_time, dep_date;
	String[] dep_cityList = { "STL", "ORD", "ATL", "LAX", "DFW", "JFK", "DEN", "SFO", "CLT", "LAS", "PHX", "IAH", "MIA", "SEA,"
			,"EWR", "MCO", "MSP", "DTW", "BOS", "PHL", "FLL", "MDW", "TPA", "HOU", "ONT" };
	String[] arr_cityList = dep_cityList;
	String[] dur_list = { "1", "1.5", "2","2.5","3","3.5","4","4.5","5","5.5","6","6.5","7","7.5","8"};


	public  CreateFlightPanel(MakeFrame frame,  InputWaiter wait){

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
		dep_date = new JSpinner();
		SpinnerDateModel spinnermodel1 = new SpinnerDateModel();
		spinnermodel1.setCalendarField(Calendar.MINUTE);
		dep_date.setModel(spinnermodel1);
		dep_date.setEditor(new JSpinner.DateEditor(dep_date , "  MMM dd, yyyy "));
		dep_date.setPreferredSize(new Dimension(75,200));


		//		============			Time picker 				===================
		dep_time = new JSpinner();
		SpinnerDateModel spinnermodel = new SpinnerDateModel();
		spinnermodel.setCalendarField(Calendar.MINUTE);
		dep_time.setModel(spinnermodel);
		dep_time.setEditor(new JSpinner.DateEditor(dep_time , "  HH:mm  "));
		dep_time.setPreferredSize(new Dimension(75,200));


		//		============			Make Panel 					===================
		JPanel startPane = new JPanel();
		startPane.setLayout(new GridBagLayout());

		//		============	Make, Set and Position Components. 	===================

		GridBagConstraints main = new GridBagConstraints();

		main.gridx = 4;
		main.gridy = 0;
		main.gridwidth = 4;
		main.gridheight = 3;

		String wPic = ("./images/logo.png");
		JLabel wIcon = new JLabel(new ImageIcon(wPic));
		startPane.add(wIcon, main);


		group = new ButtonGroup();

		main.gridx = 2;
		main.gridy = 3;
		main.gridwidth = 1;

		main.gridx = 7;
		main.gridwidth = 1;
		JLabel tmp = new JLabel("             ");
		startPane.add(tmp, main);


		main.gridx = 1;
		main.gridy = 6;
		main.gridwidth = 2;
		main.insets = new Insets(10, 10, 10, 10);
		JLabel dep = new JLabel("Depature City");
		startPane.add(dep, main);

		main.gridx = 3;
		main.gridy = 6;
		main.gridwidth = 2;
		main.insets = new Insets(10, 10, 10, 10);
		dep_city = new JComboBox<String>(dep_cityList);
		startPane.add(dep_city, main);


		main.gridx = 6; 
		main.gridwidth = 2;
		startPane.add(new JLabel("Arrival City"),main);

		main.gridx = 9; 
		main.gridy = 8; 
		main.gridwidth = 2;
		arr_city = new JComboBox<String>(arr_cityList);
		startPane.add(arr_city, main);

		main.gridx = 1;
		main.gridy = 9;
		main.gridwidth = 3;
		JLabel dep_dateText = new JLabel("Depature Date");
		startPane.add(dep_dateText, main);

		main.gridx = 3;
		main.gridy = 9;
		main.gridwidth = 3;
		main.weightx = 0;
		startPane.add(dep_date,main);		



		main.gridx = 6;
		main.gridy = 9;
		main.gridwidth = 2;
		JLabel dep_timeText = new JLabel("Depature Time");
		startPane.add(dep_timeText, main);

		main.gridx = 9;
		main.gridy = 9;
		main.gridwidth = 2;
		startPane.add(dep_time, main);

		main.gridx = 4;
		main.gridy = 15;
		main.gridwidth = 2;
		JLabel flightDuration = new JLabel("Flight Duration");
		startPane.add(flightDuration, main);

		main.gridx = 6;
		main.gridy = 15;
		main.gridwidth = 2;
		dur = new JComboBox<String>(dur_list);
		startPane.add(dur, main);

		main.fill = GridBagConstraints.HORIZONTAL;
		main.gridx = 3;
		main.gridy = 20;
		main.gridwidth = 6;
		searchBtn = new JButton("Create Flight"); 
		searchBtn.setPreferredSize(new Dimension(300, 50));
		searchBtn.setContentAreaFilled(true);
		startPane.add(searchBtn,main);		


		main.fill = GridBagConstraints.HORIZONTAL;
		main.gridx = 0;
		main.gridy = 25;
		main.gridwidth = 17;
		flightCreated = new JLabel("         "); 
		startPane.add(flightCreated,main);		



		ActionListener creatFlight = new ActionListener(){
			public void actionPerformed(ActionEvent e) {

				String depCity = (String) dep_city.getSelectedItem();
				String arrCity = (String) arr_city.getSelectedItem(); 


				Date dateFromDateChooser = (Date) dep_date.getModel().getValue();
				String depDate = String.format("%1$tY/%1$tm/%1$td", dateFromDateChooser);

				Date dateFromDateChooser1 = (Date) dep_time.getModel().getValue();
				String depTime = String.format("%1$tH:%1$tM", dateFromDateChooser1);


				MakeFlight make = new MakeFlight(depDate, depTime, depCity, arrCity, (String) dur.getSelectedItem());

				flightCreated.setText("Flight Created:  "
						+ depCity + " to " + arrCity + " on " + depDate + " at " + depTime + " with duration of  " +
						 dur.getSelectedIndex() + " hours.");


				System.out.println(""+
						"Departure City: " + depCity + 
						"\nArrival City: " + arrCity +
						"\nDepature Date: " + depDate + 
						"\nDepature Time: " + depTime + 
						"\nDuration Time: " + (String) dur.getSelectedItem());


				setSelector(1);



			}
		};


		searchBtn.addActionListener(creatFlight);


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
