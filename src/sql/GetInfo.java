package sql;


import Model.Customer;

import java.sql.*;

public class GetInfo {


	String myDriver = "com.mysql.jdbc.Driver"; 
	String myUrl = "jdbc:mysql://localhost/flightbooking";
	String  SQLuser = "root";
	String SQLpass = "root";

	// lost pass get from email or user name
	public String getLostPass(String username, String email){

		try
		{
			Class.forName(myDriver);
			Connection conn = DriverManager.getConnection(myUrl,  SQLuser, SQLpass);

			PreparedStatement st = conn.prepareStatement("SELECT * FROM Customers WHERE username = ? or email = ?");
			st.setString(1, username);
			st.setString(2, email);
			ResultSet r1=st.executeQuery();


			while(r1.next()) 
			{
				String user =  r1.getString("username");
				String dbEmail = r1.getString("email");
				String password = r1.getString("password");


				if(username.equalsIgnoreCase(user) || email.equals(dbEmail) ) 
				{

					String foundUser = "User found. \n Your password is: " + password;
					return foundUser;
				}

			}
		}

		catch (SQLException e) 
		{
			System.out.println("SQL Exception: "+ e.toString());
		} 
		catch (ClassNotFoundException cE) 
		{
			System.out.println("Class Not Found Exception: "+ cE.toString());
		}

		return "User not found";
	}


	// login checker  \\
	public int credChecker(String username, String password, Customer cust){

		try
		{

			Class.forName(myDriver);
			Connection conn = DriverManager.getConnection(myUrl,  SQLuser, SQLpass);

			PreparedStatement st = conn.prepareStatement("SELECT * FROM Customers WHERE username = ? and password = ?");
			st.setString(1, username);
			st.setString(2, password);
			ResultSet r1=st.executeQuery();

			String user;
			String pass;
			String access;
			String firstName;
			String lastName;
			String address; 
			String city;
			String state;
			String zip;
			String phoneNum;
			String email;

			while(r1.next()) 
			{
				user =  r1.getString("username");
				pass = r1.getString("password");
				access = r1.getString("access");

				email =  r1.getString("email");
				firstName =  r1.getString("first_name");
				lastName = r1.getString("last_name");
				address = r1.getString("address");
				city =  r1.getString("city");
				state = r1.getString("state");
				zip = r1.getString("zip");
				phoneNum =  r1.getString("phone");


				String admin = "admin";
				String customer = "customer";

				if(username.equalsIgnoreCase(user) && password.equals(pass) && access.equals(customer)) 
				{	
					System.out.println("customer 1");

					cust.setFirst_name(firstName);
					cust.setLast_name(lastName);
					cust.setAddress(address);
					cust.setCity(city);
					cust.setState(state);
					cust.setZip(zip);
					cust.setUsername(user);
					cust.setPassword(pass);
					cust.setPhone(phoneNum);
					cust.setEmail(email);


					return 1;
				}else if(username.equalsIgnoreCase(user) && password.equals(pass) && access.equals(admin)){
					System.out.println("admin");

					cust.setFirst_name(firstName);
					cust.setLast_name(lastName);
					cust.setAddress(address);
					cust.setCity(city);
					cust.setState(state);
					cust.setZip(zip);
					cust.setUsername(user);
					cust.setPassword(pass);
					cust.setPhone(phoneNum);
					cust.setEmail(email);
					return 2;

				}else if(username.equalsIgnoreCase(user) && !password.equals(pass)){
					System.out.println("not found");
					return 3;
				}

			}
		}

		catch (SQLException e) 
		{
			System.out.println("SQL Exception: "+ e.toString());
		} 
		catch (ClassNotFoundException cE) 
		{
			System.out.println("Class Not Found Exception: "+ cE.toString());
		}

		System.out.println("3");
		return 3;

	}


	// check user name exist for register \\
	public boolean usernameChecker(String username){

		boolean usernameExists = false;

		try
		{
			Class.forName(myDriver);
			Connection conn = DriverManager.getConnection(myUrl, SQLuser, SQLpass);

			PreparedStatement st = conn.prepareStatement("SELECT * FROM Customers WHERE username = ? ");
			st.setString(1, username);
			ResultSet r1=st.executeQuery();

			String user;

			while(r1.next()) 
			{
				user =  r1.getString("username");
				if(username.equalsIgnoreCase(user)) 
				{
					System.out.println("It already exists");
					usernameExists = true;
				}
			}
		}

		catch (SQLException e) 
		{
			System.out.println("SQL Exception: "+ e.toString());
		} 
		catch (ClassNotFoundException cE) 
		{
			System.out.println("Class Not Found Exception: "+ cE.toString());
		}

		return usernameExists;
	}


	public int newRegister(String password1, String username, String email, String firstName, String lastName, String phoneNum, 
			String address, String city, String state, String zip){

		boolean check = usernameChecker(username);


		// user name found (true)
		if(check) 
			return 1;

		// check user name is smaller then 4
		if(username.length() < 4 ){
			System.out.println("username to small");
			return 2;
			//password to short
		}else if(password1.length() < 2){
			System.out.println("password too short");
			return 3;

			// check passwords match
		}else {
			System.out.println("user sent");
			@SuppressWarnings("unused")
			writeSql newUser = new writeSql (username, password1, email, firstName, lastName, phoneNum, address, state, city, zip);

			System.out.println("User created");
			return 5;
		}


	}
	public void updateProfile(Customer cust, String username, String password, String email, String firstName, String lastName, String phoneNum, String address, String city, String state, String zip){

		try {
			// create a mysql database connection
			String myDriver = "com.mysql.jdbc.Driver"; 
			String myUrl = "jdbc:mysql://localhost/flightbooking";
			Class.forName(myDriver);
			Connection conn = DriverManager.getConnection(myUrl, "root", "root");


			// the mysql insert statement
			String query = " UPDATE Customers SET password = ?, email = ?, first_name = ?, "
					+ "last_name = ?, phone = ?, address = ?, state = ?, city = ?, zip = ? WHERE Customers.username = ?";



			// create the mysql insert prepared statement
			PreparedStatement preparedStmt = conn.prepareStatement(query);
			preparedStmt.setString (1, password);
			preparedStmt.setString (2, email);
			preparedStmt.setString (3, firstName);
			preparedStmt.setString (4, lastName);
			preparedStmt.setString (5, phoneNum);
			preparedStmt.setString (6, address);
			preparedStmt.setString (7, state);
			preparedStmt.setString (8, city);
			preparedStmt.setString (9, zip);
			preparedStmt.setString (10, username);

			preparedStmt.execute();




			PreparedStatement st = conn.prepareStatement("SELECT * FROM Customers WHERE username = ?");
			st.setString (1, username);
			
			ResultSet r1=st.executeQuery();

			

			
			String user;
			String pass;

			
			while(r1.next()) 
			{

				user =  r1.getString("username");
				pass = r1.getString("password");

				email =  r1.getString("email");
				firstName =  r1.getString("first_name");
				lastName = r1.getString("last_name");
				address = r1.getString("address");
				city =  r1.getString("city");
				state = r1.getString("state");
				zip = r1.getString("zip");
				phoneNum =  r1.getString("phone");


				cust.setFirst_name(firstName);
				cust.setLast_name(lastName);
				cust.setAddress(address);
				cust.setCity(city);
				cust.setState(state);
				cust.setZip(zip);
				cust.setUsername(user);
				cust.setPassword(pass);
				cust.setPhone(phoneNum);
				cust.setEmail(email);
			}
			conn.close();
		}
		catch (Exception e)
		{
			System.err.println("Got an exception!");
			System.err.println(e.getMessage());
		}
	}


}//close class





