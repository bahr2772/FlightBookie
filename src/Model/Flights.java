package Model;

import java.util.ArrayList;
import java.util.Date;

public class Flights {

	private ArrayList<Flights> searchResults = new ArrayList<Flights>();
	private ArrayList<Flights> allFlights = new ArrayList<Flights>();
	private String depart_city, arrival_city, departure_time;
	private Date depart_date;
	
	private int flightId, selectedFlight;
	private Date arrival_date;
	private int flightDuration;
	private int remainSeats;
	
	ArrayList<String> seats = new ArrayList<String>();
	ArrayList<Customer> customers = new ArrayList<Customer>();
	
	
	public String getDepart_city() {
		return depart_city;
	}
	public void setDepart_city(String depart_city) {
		this.depart_city = depart_city;
	}
	public Date getDepart_date() {
		return depart_date;
	}
	public void setDepart_date(Date depart_date) {
		this.depart_date = depart_date;
	}
	public String getArrival_city() {
		return arrival_city;
	}
	public void setArrival_city(String arrival_city) {
		this.arrival_city = arrival_city;
	}
	public Date getArrival_date() {
		return arrival_date;
	}
	public void setArrival_date(Date arrival_date) {
		this.arrival_date = arrival_date;
	}
	public ArrayList<Flights> getSearchResults() {
		return searchResults;
	}
	public void setSearchResults(ArrayList<Flights> searchResults) {
		this.searchResults = searchResults;
	}
	public ArrayList<Flights> getAllFlights() {
		return allFlights;
	}
	public void setAllFlights(ArrayList<Flights> allFlights) {
		this.allFlights = allFlights;
	}
	public int getFlightDuration() {
		return flightDuration;
	}
	public void setFlightDuration(int flightDuration) {
		this.flightDuration = flightDuration;
	}
	public int getRemainSeats() {
		return remainSeats;
	}
	public void setRemainSeats(int remainSeats) {
		this.remainSeats = remainSeats;
	}
	public int getFlightId() {
		return flightId;
	}
	public void setFlightId(int flightId) {
		this.flightId = flightId;
	}
	public String getDeparture_time() {
		return departure_time;
	}
	public void setDeparture_time(String departure_time) {
		this.departure_time = departure_time;
	}
	public int getSelectedFlight() {
		return selectedFlight;
	}
	public void setSelectedFlight(int selectedFlight) {
		this.selectedFlight = selectedFlight;
	}
	
	
	
}
