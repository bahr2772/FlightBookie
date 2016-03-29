package View;

import java.awt.Dimension;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.Date;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.ListSelectionModel;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;

import Controller.InputWaiter;
import Model.Flights;

public class ResultsPanel {
	private int selector;
	private JButton bookFlight;
	public  void Results(MakeFrame frame,  InputWaiter wait, Flights flightModel){

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



		//		============			Make Panel 					===================
		JPanel startPane = new JPanel();
		startPane.setLayout(new GridBagLayout());



		//		============	Make, Set and Position Components. 	===================



		GridBagConstraints main = new GridBagConstraints();

		main.gridx = 7;
		main.gridy = 0;
		main.gridwidth = 2;
		main.gridheight = 2;

		String wPic = ("./images/logo.png");
		JLabel wIcon = new JLabel(new ImageIcon(wPic));
		startPane.add(wIcon, main);

		ArrayList<String> temp = new ArrayList<String>();
		
		for(int i = 0; i < flightModel.getSearchResults().size(); i++){
			Date dateFromDateChooser = (Date) flightModel.getSearchResults().get(i).getDepart_date() ;
			String depDate = String.format("%1$tm/%1$td/%1$tY", dateFromDateChooser);

			
			temp.add("Flight Id: " + flightModel.getSearchResults().get(i).getFlightId() +
					"\tDeparture Date: " + depDate +
					"\tDepature City: " + flightModel.getSearchResults().get(i).getDepart_city() +
					"\tArrival City: " + flightModel.getSearchResults().get(i).getArrival_city() +
					"\tDuation: " + flightModel.getSearchResults().get(i).getFlightDuration() + 
					"\tReamaining Seats: " + flightModel.getSearchResults().get(i).getRemainSeats());
		}
		
		
		if(flightModel.getSearchResults().size() == 0){
			main.gridx = 0;
			main.gridy = 7;
			main.gridwidth = 10;
			main.gridheight = 6;
			JLabel noRs = new JLabel("Sorry, We do not have any flights matching your search parameters.");
			//			flightModel.getSearchResults().add("Sorry, We do not have any flights matching your search parameters.");
			startPane.add(noRs, main);
		}else{
			JList list = new JList(temp.toArray());
			list.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
			JScrollPane pane = new JScrollPane(list,JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED, JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
			main.gridx = 0;
			main.gridy = 7;
			main.gridwidth = 10;
			main.gridheight = 6;
			list.setLayoutOrientation(JList.VERTICAL);
			pane.setPreferredSize(new Dimension(690, 150));
			
			pane.setAutoscrolls(true);
			startPane.add(pane, main);
			
			
			
			main.gridx = 0;
			main.gridy = 20;
			main.gridwidth = 4;
			main.gridheight = 2;
			bookFlight = new JButton("Book Flight");
			bookFlight.setEnabled(false);
			startPane.add(bookFlight, main);
			
			
			list.addListSelectionListener(new ListSelectionListener() {
			      public void valueChanged(ListSelectionEvent evt) {
			        if (evt.getValueIsAdjusting())
			        	bookFlight.setEnabled(true);
			          return;
			       
			      }
			    });
			
			
			
			
		}

		
		
		
		
		



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
