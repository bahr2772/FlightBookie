package sql;

//STEP 1. Import required packages
import java.sql.*;
public class SearchDb {


	// JDBC driver name and database URL
	static final String JDBC_DRIVER = "com.mysql.jdbc.Driver";  
	static final String DB_URL = "jdbc:mysql://localhost/flightbooking";

	//  Database credentials
	static final String SQLUSER = "root";
	static final String SQLPASS = "root";

	public String searchOneWay(String depDate, String departure_city, String arrival_city ) {

		String temp= null;

			try
			{
				Class.forName(JDBC_DRIVER);
				Connection conn = DriverManager.getConnection(DB_URL, SQLUSER, SQLPASS);

				PreparedStatement st = conn.prepareStatement("SELECT * FROM Flights WHERE departure_city = ? and departure_date = ? and arrival_city = ?");
			
				st.setString(1, depDate.toString());
				st.setString(2, departure_city);
				st.setString(3, arrival_city);
				ResultSet r1=st.executeQuery();


				while(r1.next()) 
				{
					String dbDepDate =  r1.getString("departure_date");
					String dbDeparture_city = r1.getString("departure_city");
					String dbArrival_city = r1.getString("arrival_city");
					 
					temp = temp + "Depature Date: " + dbDepDate + " Departure City: " + dbDeparture_city + " Arrival City: " + dbArrival_city + "\n";
				
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

			
		System.out.println(temp);
		return temp;
	}//end main

	
}//end FirstExample

