package View;

import java.awt.Dimension;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Date;
import java.util.Properties;

import javax.swing.ButtonGroup;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JRadioButton;

import org.jdatepicker.impl.JDatePanelImpl;
import org.jdatepicker.impl.JDatePickerImpl;
import org.jdatepicker.impl.UtilDateModel;

import sql.GetFlights;

import Controller.InputWaiter;
import Model.Flights;

public class SearchPanel {

	private int selector;

	private JPanel startPane;
	private JLabel return_dateText, temp;
	private JButton searchBtn;
	private JRadioButton roundTrip, oneWay;
	private JDatePickerImpl dep_date, return_date;
	private JComboBox<String> dep_city, arr_city;
	private ButtonGroup group ;
	String[] dep_cityList = { "STL", "ORD", "ATL", "LAX", "DFW", "JFK", "DEN", "SFO", "CLT", "LAS", "PHX", "IAH", "MIA", "SEA,"
			,"EWR", "MCO", "MSP", "DTW", "BOS", "PHL", "FLL", "MDW", "TPA", "HOU", "ONT" };
	String[] arr_cityList = dep_cityList;

	public int searchPanel(MakeFrame frame, InputWaiter wait, Flights flightModel) {
		System.out.println("search");


		frame.setSize(700,500);
		frame.setResizable(false);

		try {
			Thread.sleep(50);
		} catch (InterruptedException e1) {
			e1.printStackTrace();
		}
		frame.toFront();


		//		Departure date picker
		UtilDateModel model = new UtilDateModel();
		Properties p = new Properties();
		p.put("text.today", "Today");
		p.put("text.month", "Month");
		p.put("text.year", "Year");
		JDatePanelImpl datePanel = new JDatePanelImpl(model, p);
		dep_date = new JDatePickerImpl(datePanel, new DateLabelFormatter());
		dep_date.getModel().setSelected(true);

		//		Return Date picker
		UtilDateModel model1 = new UtilDateModel();
		JDatePanelImpl datePanel1 = new JDatePanelImpl(model1, p);
		return_date = new JDatePickerImpl(datePanel1, new DateLabelFormatter());
		return_date.getModel().setSelected(true);

		startPane = new JPanel();
		startPane.setLayout(new GridBagLayout());

		GridBagConstraints main = new GridBagConstraints();

		main.gridx = 4;
		main.gridy = 0;
		main.gridwidth = 4;
		main.gridheight = 3;
		main.weightx = -1;
		String wPic = ("./images/logo.png");
		JLabel wIcon = new JLabel(new ImageIcon(wPic));
		startPane.add(wIcon, main);


		group = new ButtonGroup();

		main.gridy = 3;
		main.gridwidth = 1;
		oneWay = new JRadioButton("One-way");
		startPane.add(oneWay,main);
		group.add(oneWay);
		oneWay.setSelected(true);

		main.gridx = 7;
		main.gridwidth = 1;
		roundTrip = new JRadioButton("Round Trip");

		startPane.add(roundTrip, main);
		group.add(roundTrip);


		main.gridx = 1;
		main.gridy = 6;
		main.gridwidth = 1;
		main.weightx = 0;

		main.insets = new Insets(10, 10, 10, 10);
		JLabel dep = new JLabel("Depature City");
		startPane.add(dep, main);

		main.gridx = 2;
		main.gridy = 6;
		main.gridwidth = 2;
		main.weightx = 0;

		main.insets = new Insets(10, 10, 10, 10);
		dep_city = new JComboBox<String>(dep_cityList);
		startPane.add(dep_city, main);


		main.gridx = 7; 
		main.gridwidth = 1;

		startPane.add(new JLabel("Arrival City"),main);

		main.gridx = 8; 
		main.gridy = 6; 
		main.gridwidth = 2;
		arr_city = new JComboBox<String>(arr_cityList);
		startPane.add(arr_city, main);

		main.gridx = 1;
		main.gridy = 9;
		main.gridwidth = 1;
		main.insets = new Insets(10, 10, 10, 10);
		JLabel dep_dateText = new JLabel("Depature Date");
		startPane.add(dep_dateText, main);

		main.gridx = 2;
		main.gridy = 11;
		main.gridwidth = 3;
		main.weightx = 0;
		startPane.add(dep_date,main);		

		main.gridx = 8;
		main.gridwidth = 3;
		main.insets = new Insets(10, 10, 10, 10);
		temp = new JLabel("                                                   ");
		startPane.add(temp, main);


		ActionListener trip = new ActionListener(){
			public void actionPerformed(ActionEvent e) {


				main.gridx = 6;
				main.gridy = 9;
				main.gridwidth = 2;
				main.insets = new Insets(10, 10, 10, 10);
				return_dateText = new JLabel("Return Date");
				startPane.add(return_dateText, main);

				main.gridx = 8;
				main.gridy = 11;
				main.gridwidth = 3;
				startPane.add(return_date,main);		
				startPane.revalidate();
				startPane.repaint();
			}

		};
		ActionListener rmTrip = new ActionListener(){
			public void actionPerformed(ActionEvent e) {

				main.gridx = 8;
				main.gridy = 11;
				main.gridwidth = 3;
				main.insets = new Insets(10, 10, 10, 10);
				temp = new JLabel("                                                   ");
				startPane.add(temp, main);

				startPane.remove(return_date);
				startPane.remove(return_dateText);
				startPane.revalidate();
				startPane.repaint();
				//mainFrame.repaint();


			}

		};
		roundTrip.addActionListener(trip);
		oneWay.addActionListener(rmTrip);


		main.fill = GridBagConstraints.HORIZONTAL;
		main.gridx = 3;
		main.gridy = 15;
		main.gridwidth = 7;
		main.weightx = 0;

		searchBtn = new JButton("Search"); 
		searchBtn.setPreferredSize(new Dimension(300, 50));
		searchBtn.setContentAreaFilled(true);
		startPane.add(searchBtn,main);		



		ActionListener search = new ActionListener(){
			public void actionPerformed(ActionEvent e) {
				String trip_type ;

				if (oneWay.isSelected()){
					trip_type = "OneWay";
				}else{
					trip_type = "RoundTrip";
				}
				String depCity = (String) dep_city.getSelectedItem();
				String arrCity = (String) arr_city.getSelectedItem(); 

				Date dateFromDateChooser = (Date) dep_date.getModel().getValue();
				String depDate = String.format("%1$tY%1$tm%1$td", dateFromDateChooser);

				Date dateFromDateChooser1 = (Date) return_date.getModel().getValue();
				String returnDate = String.format("%1$tY/%1$tm/%1$td", dateFromDateChooser1);



				GetFlights search = new GetFlights();
				search.getFlights(flightModel, depDate, depCity, arrCity);

				System.out.println("Departure City " + depCity + 
						"\nArrival City " + arrCity +
						"\nDepature Date: " + depDate + 
						"\nReturn Date " + returnDate + 
						"\nTrip Type " + trip_type);
				setSelector(8);

				wait.resume();



			}
		};

		searchBtn.addActionListener(search);

		frame.add(startPane);
		try {
			Thread.sleep(20);
		} catch (InterruptedException e1) {
			e1.printStackTrace();
		}
		frame.repaint();
		frame.setVisible(true);
		startPane.setVisible(true);

		wait.pause();
		wait.run();

		startPane.setVisible(false);

		return getSelector();






	}



	public int getSelector() {
		return selector;
	}



	public void setSelector(int selector) {
		this.selector = selector;
	}

}
