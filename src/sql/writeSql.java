package sql;
import java.sql.*;

public class writeSql {

	
	public writeSql(String username, String password, String email, String firstName, String lastName, String phoneNum, String address, String state, String city, String zip){
	    
		  try {
	      // create a mysql database connection
	      String myDriver = "com.mysql.jdbc.Driver"; 
	      String myUrl = "jdbc:mysql://localhost/flightbooking";
	      Class.forName(myDriver);
	      Connection conn = DriverManager.getConnection(myUrl, "root", "root");
	    
	 
	      // the mysql insert statement
	      String query = " INSERT INTO Customers (id, username, password, email, first_name, last_name, phone, address, state, city, zip)"
	        + " values (?,?,?,?,?,?,?,?,?,?,?)";
	 
	    
	      
	      // create the mysql insert prepared statement
	      PreparedStatement preparedStmt = conn.prepareStatement(query);
	      preparedStmt.setString (1, null);
	      preparedStmt.setString (2, username);
	      preparedStmt.setString (3, password);
	      preparedStmt.setString (4, email);
	      preparedStmt.setString (5, firstName);
	      preparedStmt.setString (6, lastName);
	      preparedStmt.setString (7, phoneNum);
	      preparedStmt.setString (8, address);
	      preparedStmt.setString (9, state);
	      preparedStmt.setString (10, city);
	      preparedStmt.setString (11, zip);
	 
	      // execute the prepared statement
	      preparedStmt.execute();
	       
	      conn.close();
	    }
	    catch (Exception e)
	    {
	      System.err.println("Got an exception!");
	      System.err.println(e.getMessage());
	    }
	  }
	}
	

