package View;

import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.*;


import Controller.InputWaiter;
import Model.Customer;
import sql.GetInfo;

public class ProfilePanel {
	private int selector;

	private JTextField first_name;
	private JTextField lastname;
	private JTextField phoneNum;
	private JTextField address;
	private JTextField city;
	private JTextField state;
	private JTextField zip;
	private JLabel username;
	private JTextField email;
	private JPasswordField passwordField;
	private JLabel msg;
	
	
	public  ProfilePanel(MakeFrame frame,  InputWaiter wait, Customer cust){

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
		startPane.setLayout(null);
		//		============	Make, Set and Position Components. 	===================

		String wPic = ("./images/logo.png");
		JLabel lblNewLabel = new JLabel(new ImageIcon(wPic));
		lblNewLabel.setPreferredSize(new Dimension(150, 150));
		lblNewLabel.setBounds(264, 26, 170, 118);
		startPane.add(lblNewLabel);

		JLabel lblFirstName = new JLabel("First Name:");
		lblFirstName.setBounds(43, 174, 93, 16);
		startPane.add(lblFirstName);

		JLabel lblNewLabel_1 = new JLabel("Last Name: ");
		lblNewLabel_1.setBounds(334, 174, 83, 16);
		startPane.add(lblNewLabel_1);

		first_name = new JTextField(cust.getFirst_name());
		first_name.setBounds(138, 169, 170, 26);
		startPane.add(first_name);
		first_name.setColumns(10);

		lastname = new JTextField(cust.getLast_name());
		lastname.setBounds(418, 169, 200, 26);
		startPane.add(lastname);
		lastname.setColumns(10);

		JLabel lblPhone = new JLabel("Phone:");
		lblPhone.setBounds(43, 220, 61, 16);
		startPane.add(lblPhone);

		phoneNum = new JTextField(cust.getPhone());
		phoneNum.setBounds(138, 215, 170, 26);
		startPane.add(phoneNum);
		phoneNum.setColumns(10);

		JLabel lblGender = new JLabel("Email: ");
		lblGender.setBounds(350, 220, 61, 16);
		startPane.add(lblGender);

		email = new JTextField(cust.getEmail());
		email.setBounds(418, 216, 200, 26);
		startPane.add(email);

		JLabel lblAddress = new JLabel("Mailing Address:");
		lblAddress.setBounds(94, 269, 130, 16);
		startPane.add(lblAddress);

		JLabel lblAddress_1 = new JLabel("Address:");
		lblAddress_1.setBounds(43, 300, 61, 16);
		startPane.add(lblAddress_1);

		address = new JTextField(cust.getAddress());
		address.setBounds(138, 294, 161, 26);
		startPane.add(address);
		address.setColumns(10);

		JLabel lblCity = new JLabel("City:");
		lblCity.setBounds(43, 333, 61, 16);
		startPane.add(lblCity);

		city = new JTextField(cust.getCity());
		city.setBounds(138, 332, 161, 26);
		startPane.add(city);
		city.setColumns(10);

		JLabel lblState = new JLabel("State:");
		lblState.setBounds(43, 373, 41, 16);
		startPane.add(lblState);

		state = new JTextField(cust.getState());
		state.setBounds(138, 368, 161, 26);
		startPane.add(state);
		state.setColumns(10);

		JLabel lblZip = new JLabel("Zip:");
		lblZip.setBounds(43, 412, 61, 16);
		startPane.add(lblZip);

		zip = new JTextField(cust.getZip());
		zip.setBounds(138, 407, 161, 26);
		startPane.add(zip);
		zip.setColumns(10);

		JLabel lblUserLogin = new JLabel("User Login");
		lblUserLogin.setBounds(478, 279, 93, 16);
		startPane.add(lblUserLogin);

		JLabel lblUsername = new JLabel("Username:");
		lblUsername.setBounds(375, 312, 74, 16);
		startPane.add(lblUsername);

		username = new JLabel(cust.getUsername());
		username.setBounds(463, 307, 130, 26);
		startPane.add(username);

		JLabel lblPassword = new JLabel("Password: ");
		lblPassword.setBounds(375, 352, 74, 16);
		startPane.add(lblPassword);

		passwordField = new JPasswordField(cust.getPassword());
		passwordField.setBounds(455, 345, 128, 26);
		startPane.add(passwordField);

		JButton btnUpdate = new JButton("Update Profile");
		btnUpdate.setBounds(438, 375, 117, 29);
		startPane.add(btnUpdate);
		
		msg = new JLabel("                                  ");
		msg.setBounds(350, 400, 330, 30);
		startPane.add(msg);

		//		============		Action Listener		===================


		ActionListener update = new ActionListener(){
			public void actionPerformed(ActionEvent e) {

				GetInfo user = new GetInfo();
					user.updateProfile(cust, username.getText(),passwordField.getText(), email.getText(),first_name.getText(), lastname.getText(), phoneNum.getText(), 
							address.getText(), city.getText(), state.getText(),	zip.getText());
				

						msg.setText("Profile Updated");	
						startPane.setVisible(false);
						startPane.repaint();
						startPane.setVisible(true);
						
						setSelector(6);
						cust.setLogin(true);
						frame.getBar().login();
						wait.resume();
						System.out.println("Profile Updated");

						wait.resume();
						
			}
		};
		
		btnUpdate.addActionListener(update);
		
		

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

		frame.getBar().repaint();
		frame.getBar().validate();
		frame.getBar().revalidate();
		frame.getBar().setVisible(true);
		
		
		startPane.setVisible(false);



	}

	public int getSelector() {
		return selector;
	}

	public void setSelector(int selector) {
		this.selector = selector;
	}



}
