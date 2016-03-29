package sql;

//STEP 1. Import required packages
import java.sql.*;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

import Model.Flights;
public class EditFlight {


	// JDBC driver name and database URL
	static final String JDBC_DRIVER = "com.mysql.jdbc.Driver";  
	static final String DB_URL = "jdbc:mysql://localhost/flightbooking";

	//  Database credentials
	static final String SQLUSER = "root";
	static final String SQLPASS = "root";

	public void editFlight( Flights model, int selected, String depDate, String depCity, String arrCity, String depTime, String dur, String seats ) {

			
		
			try
			{
				Class.forName(JDBC_DRIVER);
				Connection conn = DriverManager.getConnection(DB_URL, SQLUSER, SQLPASS);

				int flightId = model.getAllFlights().get(selected).getFlightId();
				
			      // the mysql insert statement
			      String query = " UPDATE Flights SET departure_date = ?, departure_time = ?, departure_city = ?, arrival_city = ?"
			      		+ ", duration = ?, seatsRemain = ? WHERE ID = ?";
			 
			      // create the mysql insert prepared statement
			      PreparedStatement preparedStmt = conn.prepareStatement(query);
//			      preparedStmt.execute();
			      preparedStmt.setString (1, depDate);
			      preparedStmt.setString (2, depTime);
			      preparedStmt.setString (3, depCity);
			      preparedStmt.setString (4, arrCity);
			      preparedStmt.setString (5, dur);
			      preparedStmt.setString (6, seats);
			      preparedStmt.setInt (7, flightId);



				 preparedStmt.execute();

				 
				 Date date = Calendar.getInstance().getTime();
				    SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMdd");
				    System.out.println(sdf.format(date));
				 
				 PreparedStatement st = conn.prepareStatement("SELECT * FROM Flights WHERE departure_date >= " + sdf.format(date));
					
					ResultSet r1=st.executeQuery();
				 
				while(r1.next()) 
				{
					Flights flight = new Flights();
					
					
					int id = r1.getInt("id");
					java.sql.Date dbDepDate =  r1.getDate("departure_date");
					String dbDeparture_city = r1.getString("departure_city");
					String dbArrival_city = r1.getString("arrival_city");
					int dbDur = r1.getInt("duration");
					int dbseats = r1.getInt("seatsRemain");

					
					flight.setFlightId(id);
					flight.setDepart_date(dbDepDate);
					flight.setDepart_city(dbDeparture_city);
					flight.setArrival_city(dbArrival_city);
					flight.setFlightDuration(dbDur);
					flight.setRemainSeats(dbseats);
					
					 model.getAllFlights().add(flight);
					
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

			
		
	}//end main

	
}//end FirstExample

