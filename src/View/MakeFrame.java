package View;

import java.awt.Dimension;
import java.awt.Image;
import java.awt.Toolkit;

import javax.swing.JFrame;

import Controller.InputWaiter;
import Model.Customer;

public class MakeFrame extends JFrame{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private MenuBar bar ;

	public MakeFrame(InputWaiter wait, Customer cust) {
		setTitle("Book Your Flight");
		
		bar = new MenuBar(wait, cust);
		this.setJMenuBar(bar);
		
		

		Image icon = Toolkit.getDefaultToolkit().getImage("./images/logo.png");
		setIconImage(icon);
		setPreferredSize(new Dimension(500,600));
		setLocationRelativeTo(null);
		setResizable(false);
		toFront();
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);




	}

	

	public MenuBar getBar() {
		return bar;
	}

	public void setBar(MenuBar bar) {
		this.bar = bar;
	}




}
