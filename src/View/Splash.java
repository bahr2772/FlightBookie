package View;

import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;

import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JPanel;

import Controller.InputWaiter;



public class Splash {
	private int selector;

	private JLabel flightCreated;

	public  Splash(MakeFrame frame,  InputWaiter wait){

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

		main.gridx = 4;
		main.gridy = 0;
		main.gridwidth = 4;
		main.gridheight = 3;

		String wPic = ("./images/logo.png");
		JLabel wIcon = new JLabel(new ImageIcon(wPic));
		startPane.add(wIcon, main);



		main.fill = GridBagConstraints.HORIZONTAL;
		main.gridx = 0;
		main.gridy = 25;
		main.gridwidth = 17;
		flightCreated = new JLabel("         "); 
		startPane.add(flightCreated,main);		


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
