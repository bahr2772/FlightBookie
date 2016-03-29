package sql;

//STEP 1. Import required packages
import java.sql.*;
import java.util.ArrayList;
import java.util.Date;

import Model.Flights;
public class GetFlights {


	// JDBC driver name and database URL
	static final String JDBC_DRIVER = "com.mysql.jdbc.Driver";  
	static final String DB_URL = "jdbc:mysql://localhost/flightbooking";

	//  Database credentials
	static final String SQLUSER = "root";
	static final String SQLPASS = "root";

	public ArrayList<Flights> getFlights(Flights model, String depDate, String departure_city, String arrival_city ) {

			
		
			try
			{
				Class.forName(JDBC_DRIVER);
				Connection conn = DriverManager.getConnection(DB_URL, SQLUSER, SQLPASS);

				PreparedStatement st = conn.prepareStatement("SELECT * FROM Flights WHERE departure_city = '" + departure_city + "' and departure_date = ? and arrival_city ='"+ arrival_city +"'");
			
				st.setString(1, depDate.toString());
//				st.setString(2, departure_city );
//				st.setString(3, arrival_city);
				ResultSet r1=st.executeQuery();


				while(r1.next()) 
				{
					Flights flight = new Flights();
					
					
					int id = r1.getInt("id");
					java.sql.Date dbDepDate =  r1.getDate("departure_date");
					String dbDeparture_city = r1.getString("departure_city");
					String dbArrival_city = r1.getString("arrival_city");
					int dbDur = r1.getInt("duration");
					int seats = r1.getInt("seatsRemain");
					
//					Date dateFromDateChooser = (Date) dbDepDate;
//					String dbDepDate1 = String.format("%1$tm/%1$td/%1$tY", dateFromDateChooser);
					
					flight.setFlightId(id);
					flight.setDepart_date(dbDepDate);
					flight.setDepart_city(dbDeparture_city);
					flight.setArrival_city(dbArrival_city);
					flight.setFlightDuration(dbDur);
					flight.setRemainSeats(seats);
					
					
				
					
					model.getSearchResults().add(flight);
					
					
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

			
		return model.getSearchResults();
	}//end main

	
}//end FirstExample

