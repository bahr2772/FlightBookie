package View;

import javax.swing.JPanel;
import javax.swing.JLabel;
import java.awt.GridBagLayout;
import java.awt.GridBagConstraints;
import javax.swing.JTextField;
import javax.swing.JComboBox;
import javax.swing.JRadioButton;
import javax.swing.JPasswordField;
import javax.swing.JButton;

public class register extends JPanel {
	private JTextField first_name;
	private JTextField lastname;
	private JTextField phoneNum;
	private JTextField address;
	private JTextField city;
	private JTextField state;
	private JTextField zip;
	private JTextField textField;
	private JPasswordField passwordField;

	/**
	 * Create the panel.
	 */
	public register() {
		setLayout(null);
		
		JLabel lblNewLabel = new JLabel("New label");
		lblNewLabel.setBounds(264, 26, 170, 118);
		add(lblNewLabel);
		
		JLabel lblFirstName = new JLabel("First Name:");
		lblFirstName.setBounds(43, 174, 93, 16);
		add(lblFirstName);
		
		JLabel lblNewLabel_1 = new JLabel("Last Name: ");
		lblNewLabel_1.setBounds(334, 174, 83, 16);
		add(lblNewLabel_1);
		
		first_name = new JTextField();
		first_name.setBounds(138, 169, 130, 26);
		add(first_name);
		first_name.setColumns(10);
		
		lastname = new JTextField();
		lastname.setBounds(418, 169, 130, 26);
		add(lastname);
		lastname.setColumns(10);
		
		JLabel lblPhone = new JLabel("Phone:");
		lblPhone.setBounds(43, 220, 61, 16);
		add(lblPhone);
		
		phoneNum = new JTextField();
		phoneNum.setBounds(138, 215, 130, 26);
		add(phoneNum);
		phoneNum.setColumns(10);
		
		JLabel lblGender = new JLabel("Gender:");
		lblGender.setBounds(334, 220, 61, 16);
		add(lblGender);
		
		JRadioButton rdbtnMale = new JRadioButton("Male");
		rdbtnMale.setBounds(405, 216, 61, 23);
		add(rdbtnMale);
		
		JRadioButton rdbtnFelmale = new JRadioButton("Female");
		rdbtnFelmale.setBounds(465, 216, 83, 23);
		add(rdbtnFelmale);
		
		JLabel lblAddress = new JLabel("Mailing Address:");
		lblAddress.setBounds(94, 269, 130, 16);
		add(lblAddress);
		
		JLabel lblAddress_1 = new JLabel("Address:");
		lblAddress_1.setBounds(43, 300, 61, 16);
		add(lblAddress_1);
		
		address = new JTextField();
		address.setBounds(138, 294, 161, 26);
		add(address);
		address.setColumns(10);
		
		JLabel lblCity = new JLabel("City:");
		lblCity.setBounds(43, 333, 61, 16);
		add(lblCity);
		
		city = new JTextField();
		city.setBounds(138, 332, 161, 26);
		add(city);
		city.setColumns(10);
		
		JLabel lblState = new JLabel("State:");
		lblState.setBounds(43, 373, 41, 16);
		add(lblState);
		
		state = new JTextField();
		state.setBounds(138, 368, 161, 26);
		add(state);
		state.setColumns(10);
		
		JLabel lblZip = new JLabel("Zip:");
		lblZip.setBounds(43, 412, 61, 16);
		add(lblZip);
		
		zip = new JTextField();
		zip.setBounds(138, 407, 161, 26);
		add(zip);
		zip.setColumns(10);
		
		JLabel lblUserLogin = new JLabel("User Login");
		lblUserLogin.setBounds(478, 279, 93, 16);
		add(lblUserLogin);
		
		JLabel lblUsername = new JLabel("Username:");
		lblUsername.setBounds(375, 312, 74, 16);
		add(lblUsername);
		
		textField = new JTextField();
		textField.setBounds(463, 307, 130, 26);
		add(textField);
		textField.setColumns(10);
		
		JLabel lblPassword = new JLabel("Password: ");
		lblPassword.setBounds(375, 352, 74, 16);
		add(lblPassword);
		
		passwordField = new JPasswordField();
		passwordField.setBounds(465, 345, 128, 26);
		add(passwordField);
		
		JButton btnRegister = new JButton("Register");
		btnRegister.setBounds(418, 407, 117, 29);
		add(btnRegister);

	}
}
