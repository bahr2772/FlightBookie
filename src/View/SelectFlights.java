package View;

import java.awt.Dimension;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.Date;

import javax.swing.*;
import javax.swing.event.*;

import Controller.InputWaiter;
import Model.Flights;
import sql.EditFlight;
import sql.RemoveFlight;

public class SelectFlights {
	private int selector;
	private JButton removeFlight, editFlight;
	private int selected;
	public  void selectFlight(MakeFrame frame,  InputWaiter wait, Flights model){

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

		main.gridx = 5;
		main.gridy = 0;
		main.gridwidth = 2;
		main.gridheight = 2;

		String wPic = ("./images/logo.png");
		JLabel wIcon = new JLabel(new ImageIcon(wPic));
		startPane.add(wIcon, main);
		
		if(model.getAllFlights().size() < 0){
			main.gridx = 0;
			main.gridy = 7;
			main.gridwidth = 10;
			main.gridheight = 6;
			JLabel noRs = new JLabel("Sorry, No Flights today, or furture flights.");
			//			model.getSearchResults().add("Sorry, We do not have any flights matching your search parameters.");
			startPane.add(noRs, main);
			
		}else{
			
			ArrayList<String> temp = new ArrayList<String>();
			
			for(int i = 0; i < model.getAllFlights().size(); i++){
				Date dateFromDateChooser = (Date) model.getAllFlights().get(i).getDepart_date() ;
				String depDate = String.format("%1$tm/%1$td/%1$tY", dateFromDateChooser);

				
				temp.add("Flight Id: " + model.getAllFlights().get(i).getFlightId() +
						"\tDeparture Date: " +  depDate +
						"\tDepature City: " + model.getAllFlights().get(i).getDepart_city() +
						"\tArrival City: " + model.getAllFlights().get(i).getArrival_city() +
						"\tDuation: " + model.getAllFlights().get(i).getFlightDuration() + 
						"\tReamaining Seats: " + model.getAllFlights().get(i).getRemainSeats());
			}
			
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
			removeFlight = new JButton("Remove Flight");
			removeFlight.setEnabled(false);
			startPane.add(removeFlight, main);
			
			main.gridx = 4;
			editFlight = new JButton("Edit Flight");
			editFlight.setEnabled(false);
			startPane.add(editFlight, main);
			
			
			
			list.addListSelectionListener(new ListSelectionListener() {
			      public void valueChanged(ListSelectionEvent evt) {
			        if (evt.getValueIsAdjusting())
			        	removeFlight.setEnabled(true);
						editFlight.setEnabled(true);
			        	selected = setSelected(list.getSelectedIndex());
			        	
			      }
			    });
			
			ActionListener  actionListener = new ActionListener(){
				public void actionPerformed(ActionEvent e) {
					model.setSelectedFlight(selected);
					if(e.getSource().equals(removeFlight)){
						RemoveFlight rmFlight = new RemoveFlight();
						rmFlight.rmFlight(model, selected);
						setSelector(11);
						wait.resume();
					}else if(e.getSource().equals(editFlight)){
						

						setSelector(12);
						wait.resume();
						
					}
					
				}

			};
			
			editFlight.addActionListener(actionListener);
			removeFlight.addActionListener(actionListener);
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

	public int getSelected() {
		return selected;
	}

	public int setSelected(int selected) {
		return this.selected = selected;
	}




}
