package View;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;

import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;

import Controller.InputWaiter;
import Model.Customer;
import Model.Flights;


public class MenuBar extends JMenuBar implements ActionListener {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	JMenu fileMenu, loginMenu, searchMenu, adminMenu, loggedIn;
	JMenuItem exit, reg, login, search, createFlight, editFlights,logOut,custProfile,searchFlights, custRes;

	private int selector = 0;
	private Customer cust;

	public MenuBar(InputWaiter wait, Customer cust){
		this.cust= cust;

		// File Menu, F - Mnemonic
		fileMenu = new JMenu("Exit");
		fileMenu.setMnemonic(KeyEvent.VK_F);
		// Admin Menu, A - Mnemonic
		loginMenu = new JMenu("Login/Register");
		loginMenu.setMnemonic(KeyEvent.VK_A);
		// Search Menu, S - Mnemonic
		searchMenu = new JMenu("Search");
		searchMenu.setMnemonic(KeyEvent.VK_S);


		this.add(fileMenu);
		this.add(loginMenu);
		this.add(searchMenu);



		exit = new JMenuItem("Exit", KeyEvent.VK_E);
		login = new JMenuItem("Log-In", KeyEvent.VK_A);
		search = new JMenuItem("Search Flights", KeyEvent.VK_S);
		reg = new JMenuItem("Register" , KeyEvent.VK_R);

		loginMenu.add(reg);
		fileMenu.add(exit);
		loginMenu.add(login);
		searchMenu.add(search);

		//CUSTOMER LOGGED IN
		String name =  cust.getFirst_name();
		if(name == null || name.equals("")){
			loggedIn = new JMenu("User Info");
		}else{
			loggedIn = new JMenu(name);
		}
		loggedIn.setMnemonic(KeyEvent.VK_I);


		logOut = new JMenuItem("Log Out", KeyEvent.VK_O);
		searchFlights = new JMenuItem("Search Flights", KeyEvent.VK_S);
		custRes = new JMenuItem("Your Flights", KeyEvent.VK_F);
		custProfile  = new JMenuItem("Profile", KeyEvent.VK_P);

		adminMenu = new JMenu("Admin");
		adminMenu.setMnemonic(KeyEvent.VK_A);
		//ADMIN LOGGED IN

		createFlight = new JMenuItem("Create Flights", KeyEvent.VK_C);
		editFlights = new JMenuItem("Edit Flights", KeyEvent.VK_E);



		ActionListener  actionListener = new ActionListener(){
			public void actionPerformed(ActionEvent e) {
				if(e.getSource().equals(search)){
					System.out.println("search listen");
					selector = 1;
				}else if(e.getSource().equals(login)){
					System.out.println("admin listen");
					selector = 2;
				}else if(e.getSource().equals(reg)){
					System.out.println("Register");
					selector = 3;

				}else if(e.getSource().equals(logOut)){
					System.out.println("Log Out");
					cust.setLogin(false);
					remove(adminMenu);
					remove(loggedIn);
					add(loginMenu);
					repaint();
					selector = 0;
				}else if(e.getSource().equals(searchFlights)){
					System.out.println("Search Flights");
					selector = 1;
				}else if(e.getSource().equals(custRes)){
					System.out.println("Customer Reservations");
					selector = 9;
				}else if(e.getSource().equals(custProfile)){
					System.out.println("Customer Profile");
					selector = 10;
				}else if(e.getSource().equals(createFlight)){
					System.out.println("Create Flights");
					selector = 4;
				}else if(e.getSource().equals(editFlights)){
					System.out.println("Edit Flights");
					selector = 11;
				}else if(e.getSource().equals(exit)){
					System.out.println("Exit");
					System.exit(0);
				}




				try {
					Thread.sleep(50);
				} catch (InterruptedException e1) {
					e1.printStackTrace();
				}
				wait.resume();
			}

		};


		reg.addActionListener(actionListener);
		search.addActionListener(actionListener);
		exit.addActionListener(actionListener);
		login.addActionListener(actionListener);
		logOut.addActionListener(actionListener);
		searchFlights.addActionListener(actionListener);
		custRes.addActionListener(actionListener);
		custProfile.addActionListener(actionListener);
		createFlight.addActionListener(actionListener);
		editFlights.addActionListener(actionListener);

	}

	public void login(){

		this.remove(loginMenu);
		this.add(loggedIn);

		loggedIn.add(custProfile);
		loggedIn.add(custRes);
		loggedIn.add(searchFlights);
		loggedIn.add(logOut);
	}


	public void adminMenu(){

		this.add(adminMenu);
		adminMenu.add(createFlight);
		adminMenu.add(editFlights);
	}


	public int getSelector() {
		return selector;
	}

	public void setSelector(int selector) {
		this.selector = selector;
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		if(e.getSource().equals(search)){
			setSelector(3);
		}
	}




}


