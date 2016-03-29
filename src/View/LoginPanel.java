package View;

import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;

import Controller.InputWaiter;
import Model.Customer;

public class LoginPanel{
	private JPanel logIn = new JPanel(new GridBagLayout());

	private JLabel labelUsername = new JLabel("Enter username: ");
	private JLabel labelPassword = new JLabel("Enter password: ");
	private JLabel loginFail 	= new JLabel("Log in failed");
	private JLabel temp 	= new JLabel("        ");

	private JTextField textUsername = new JTextField(20);
	private JPasswordField fieldPassword = new JPasswordField(20);
	private JButton buttonLogin = new JButton("Login");

	private int selector;

	GridBagConstraints constraints = new GridBagConstraints();

	public int login(MakeFrame frame,InputWaiter wait, Customer cust) {


		frame.setSize(700,500);
		frame.setResizable(false);

		try {
			Thread.sleep(50);
		} catch (InterruptedException e1) {
			e1.printStackTrace();
		}
		frame.toFront();


		// add components to the panel
		constraints.gridx = 3;
		constraints.gridy = 0;
		String wPic = ("./images/logo.png");
		JLabel wIcon = new JLabel(new ImageIcon(wPic));
		logIn.add(wIcon, constraints);

		constraints.gridx = 1;
		constraints.gridy = 5;     
		logIn.add(labelUsername, constraints);

		constraints.gridx = 3;
		logIn.add(textUsername, constraints);

		constraints.gridx = 1;
		constraints.gridy = 7;    
		logIn.add(labelPassword, constraints);

		constraints.gridx = 3;
		logIn.add(fieldPassword, constraints);

		constraints.gridx = 4;
		constraints.gridy = 9;
		constraints.gridwidth = 1;
		logIn.add(temp, constraints);

		constraints.gridx = 1;
		constraints.gridy = 8;
		constraints.gridwidth = 2;
		logIn.add(buttonLogin, constraints);




		ActionListener login = new ActionListener(){
			public void actionPerformed(ActionEvent e) {


				sql.GetInfo checkCreds = new sql.GetInfo();
				int value = checkCreds.credChecker(textUsername.getText(), fieldPassword.getText(), cust );

				//			customer 
				if(value == 1){
					setSelector(6);
					cust.setLogin(true);
					frame.getBar().login();
					wait.resume();
					System.out.println("Customer Login");
					
					//			admin
				}else if(value == 2){
					setSelector(4);
					cust.setLogin(true);
					cust.setAdmin(true);
					frame.getBar().login();
					frame.getBar().adminMenu();
					wait.resume();
					System.out.println("Admin Login");
					

					//			login fail
				}else{
					textUsername.setText("");
					fieldPassword.setText("");

					constraints.gridx = 0;
					constraints.gridy = 4;
					constraints.gridwidth = 2;
					constraints.anchor = GridBagConstraints.SOUTHEAST;
					logIn.add(loginFail, constraints);

					logIn.repaint();
					logIn.revalidate();
				}
			}

		};



		// add the panel to this frame

		buttonLogin.setMnemonic(KeyEvent.VK_ENTER);
		buttonLogin.addActionListener(login);

		frame.add(logIn);

		try {
			Thread.sleep(20);
		} catch (InterruptedException e1) {
			e1.printStackTrace();
		}
		frame.repaint();
		frame.setVisible(true);
		logIn.setVisible(true);

		wait.pause();
		wait.run();

		frame.getBar().repaint();
		frame.getBar().validate();
		frame.getBar().revalidate();
		frame.getBar().setVisible(true);
		
		
		logIn.setVisible(false);

		return getSelector();




	}


	public int getSelector() {
		return selector;
	}
	public void setSelector(int selector) {
		this.selector = selector;
	}



}