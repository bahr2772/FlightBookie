package sql;

//STEP 1. Import required packages
import java.sql.*;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;

import Model.Flights;
public class GetAllFlights {


	// JDBC driver name and database URL
	static final String JDBC_DRIVER = "com.mysql.jdbc.Driver";  
	static final String DB_URL = "jdbc:mysql://localhost/flightbooking";

	//  Database credentials
	static final String SQLUSER = "root";
	static final String SQLPASS = "root";

	private ArrayList<Flights> AllFlights = new ArrayList<Flights>();
	private Date date;
	
	Date dateFromDateChooser = (Date) date;
	String date1 = String.format("%1$tm%1$td%1$tY", dateFromDateChooser);
	
//	 SELECT * FROM `la_schedule` WHERE start_date >'2012-11-18';
	
	public ArrayList<Flights> getFlights(Flights model) {


		Date date = Calendar.getInstance().getTime();
	    SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMdd");
	    System.out.println(sdf.format(date));
		
			try
			{
				Class.forName(JDBC_DRIVER);
				Connection conn = DriverManager.getConnection(DB_URL, SQLUSER, SQLPASS);

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
					int seats = r1.getInt("seatsRemain");
					String dbDepTime = r1.getString("departure_time");
					
					flight.setFlightId(id);
					flight.setDepart_date(dbDepDate);
					flight.setDeparture_time(dbDepTime);
					flight.setDepart_city(dbDeparture_city);
					flight.setArrival_city(dbArrival_city);
					flight.setFlightDuration(dbDur);
					flight.setRemainSeats(seats);
				
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
			return getAllFlights();

			
	}//end main


	public ArrayList<Flights> getAllFlights() {
		return AllFlights;
	}

	public void setAllFlights(ArrayList<Flights> allFlights) {
		AllFlights = allFlights;
	}

	
}//end FirstExample

