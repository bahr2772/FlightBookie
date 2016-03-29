/*package View;

import java.awt.Dimension;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Image;
import java.awt.Insets;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.util.Date;
import java.util.Properties;

import javax.swing.ButtonGroup;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JRadioButton;

import org.jdatepicker.impl.JDatePanelImpl;
import org.jdatepicker.impl.JDatePickerImpl;
import org.jdatepicker.impl.UtilDateModel;

import sql.SearchDb;


public class Frame {
	
	
	private JFrame mainFrame;
	private JLabel return_dateText, temp;
	private JPanel controlPanel, startPane;
	private JButton searchBtn, adminBtn;
	private JRadioButton roundTrip, oneWay;
	private JDatePickerImpl dep_date, return_date;
	private JComboBox dep_city, arr_city;
	private ButtonGroup group ;
	String[] dep_cityList = { "STL", "OHR" };
	String[] arr_cityList = { "OHR", "STL" };
	


	public static void main(String[] args) {
		Frame search = new Frame();
		search.mkFrame();
		search.searchPanel();
	}
	
	
	
	
	public void mkFrame() {
		setMainFrame(new JFrame("Book Your Flight"));
		Login log = new Login(getMainFrame());
		MenuBar menu = new MenuBar(getMainFrame(), getStartPane());
		AdminLogin admin = new AdminLogin();

		
		Image icon = Toolkit.getDefaultToolkit().getImage("./images/logo.png");
		getMainFrame().setIconImage(icon);

		getMainFrame().setSize(700, 600);
		getMainFrame().setLocationRelativeTo(null);
		getMainFrame().addWindowListener(new WindowAdapter() {
			public void windowClosing(WindowEvent windowEvent) {
				System.exit(0);
			}
		});


		setControlPanel(new JPanel());
		getMainFrame().add(getControlPanel());
		//mainFrame.setVisible(true);
	}
	
	
	
	
	public void searchPanel() {

		//		Departure date picker
		UtilDateModel model = new UtilDateModel();
		Properties p = new Properties();
		p.put("text.today", "Today");
		p.put("text.month", "Month");
		p.put("text.year", "Year");
		JDatePanelImpl datePanel = new JDatePanelImpl(model, p);
		dep_date = new JDatePickerImpl(datePanel, new DateLabelFormatter());

		//		Return Date picker
		UtilDateModel model1 = new UtilDateModel();
		model1.setDate(2015, 03, 01);
		JDatePanelImpl datePanel1 = new JDatePanelImpl(model1, p);
		return_date = new JDatePickerImpl(datePanel1, new DateLabelFormatter());




		JPanel startPane = new JPanel();
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

		main.gridx = 4;
		main.gridy = 3;
		main.gridwidth = 1;
		main.weightx = 0;

		oneWay = new JRadioButton("One-way");
		startPane.add(oneWay,main);
		group.add(oneWay);
		oneWay.setSelected(true);

		main.gridx = 7;
		main.gridy = 3;
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
		dep_city = new JComboBox(dep_cityList);
		startPane.add(dep_city, main);


		main.gridx = 7; 
		main.gridy = 6; 
		main.gridwidth = 1;
		main.weightx = 0;

		startPane.add(new JLabel("Arrival City"),main);

		main.gridx = 8; 
		main.gridy = 6; 
		main.gridwidth = 2;
		main.weightx = 0;

		arr_city = new JComboBox(arr_cityList);
		startPane.add(arr_city, main);

		main.gridx = 1;
		main.gridy = 9;
		main.gridwidth = 1;
		main.weightx = 0;

		main.insets = new Insets(10, 10, 10, 10);
		JLabel dep_dateText = new JLabel("Depature Date");
		startPane.add(dep_dateText, main);

		main.gridx = 2;
		main.gridy = 11;
		main.gridwidth = 3;
		main.weightx = 0;

		startPane.add(dep_date,main);		


		
		main.gridx = 8;
		main.gridy = 11;
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
				getMainFrame().repaint();
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
				
				
				Date tempDepDate = (Date) dep_date.getModel().getValue();
				Date tempReturnDate = (Date) return_date.getModel().getValue();
				
				String depDate = tempDepDate + "";
				String returnDate = tempReturnDate + "";

				SearchDb search = new SearchDb();
				search.searchOneWay(depDate, depCity, arrCity);
				
				System.out.println("Departure City " + depCity + 
						"\nArrival City " + arrCity +
						"\nDepature Date: " + depDate + 
						"\nReturn Date " + returnDate + 
						"\nTrip Type " + trip_type);
				
				

			}
		};

		searchBtn.addActionListener(search);
		
		getControlPanel().add(startPane);

		getMainFrame().setVisible(true);
		
		
		
		
		
		
		
		
		
	}

	public void actionPerformed(ActionEvent e) {

		if (e.getActionCommand().equals("Check")) {

			System.out.println("Selected Radio Button: " + group.getSelection().getActionCommand());

		}  
	}


	public JPanel getControlPanel() {
		return controlPanel;
	}


	public void setControlPanel(JPanel controlPanel) {
		this.controlPanel = controlPanel;
	}


	public JPanel getStartPane() {
		return startPane;
	}


	public void setStartPane(JPanel startPane) {
		this.startPane = startPane;
	}


	public JFrame getMainFrame() {
		return mainFrame;
	}


	public void setMainFrame(JFrame mainFrame) {
		this.mainFrame = mainFrame;
	}




}


*/