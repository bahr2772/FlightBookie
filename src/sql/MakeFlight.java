package sql;

import java.sql.Connection;
import java.sql.Date;
import java.sql.DriverManager;
import java.sql.PreparedStatement;

public class MakeFlight {

	public MakeFlight(String depDate, String depTime, String depCity, String arrCity, String dur) {

	
		  try {
		      // create a mysql database connection
		      String myDriver = "com.mysql.jdbc.Driver"; 
		      String myUrl = "jdbc:mysql://localhost/flightbooking";
		      Class.forName(myDriver);
		      Connection conn = DriverManager.getConnection(myUrl, "root", "root");
		    
		      
		      
		      
		      // the mysql insert statement
		      String query = " INSERT INTO Flights (id, departure_date, departure_time, departure_city, arrival_city, duration)"
		        + " values (?, ?, ?, ?, ?, ?)";
		 
		      // create the mysql insert prepared statement
		      PreparedStatement preparedStmt = conn.prepareStatement(query);
		      preparedStmt.setString (1, null);
		      preparedStmt.setString (2, depDate);
		      preparedStmt.setString (3, depTime);
		      preparedStmt.setString (4, depCity);
		      preparedStmt.setString (5, arrCity);
		      preparedStmt.setString (6, dur);

		      // execute the prepared statement
		      preparedStmt.execute();
		       
		      conn.close();
		    }
		    catch (Exception e)
		    {
		      System.err.println("Got an exception!");
		      System.err.println(e.getMessage());
		    }
		  System.out.println("flight created");
	
	}

}
