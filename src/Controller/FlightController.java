package Controller;

import javax.swing.JFrame;

import Model.Customer;
import Model.Flights;
import View.CreateFlightPanel;
import View.EditFlightPanel;
import View.LoginPanel;
import View.MakeFrame;
import View.ProfilePanel;
import View.RegisterPanel;
import View.ResultsPanel;
import View.SearchPanel;
import View.SelectFlights;
import View.Splash;
import sql.GetAllFlights;

public class FlightController implements Runnable {

	private int selector;
	private ResultsPanel results;
	private MakeFrame frame;
	private SearchPanel searchPan;
	private LoginPanel loginPanel;
	private InputWaiter wait;
	private CreateFlightPanel flightPan;
	private Splash splash;
	private Flights flightModel;
	private Customer cust = new Customer();
	private SelectFlights select;
	private EditFlightPanel edit;
	private RegisterPanel reg;
	private ProfilePanel profile;
	public FlightController(Flights flightModel){
		this.flightModel = flightModel;
	}


	public void frame(){

		frame.setSize(700,500);
		frame.setResizable(true);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setLocationRelativeTo(null);




	}



	public void run(){


		wait = new InputWaiter();
		frame = new MakeFrame(wait, cust);
		frame();





		while(true){
			delay();
			System.out.println("start");

			switch(getSelector()){

			case 1:										// search panel
				mkSearch();
				setSelector(searchPan.getSelector());
				delay();
				break;
			case 2:										// login panel
				mkLogin();
				setSelector(loginPanel.getSelector());
				delay();
				break;
			case 3:										// register panel
				mkRegister();
				setSelector(reg.getSelector());
				break;	
			case 4:										// Admin: create flight
				mkFlights();
				break;
			case 5:										// book flight panel
				delay();
				break;
			case 6:										// customer login splash
				setSelector(0);
				delay();
				break;
			case 7:										// log out
				delay();
				break;
			case 8:										// results panel
				mkResults();
				setSelector(results.getSelector());
				break;
			case 9:										// customer reservations
				delay();
				break;
			case 10:									// customer profile
				mkProfile();
				break;
			case 11: 									// select Flight 
				selectFlight();
				setSelector(select.getSelector());
				break;

			case 12:
				mkEdit();
				setSelector(edit.getSelector());
				break;
			default:
				mkSplash();
				break;

			}
			System.out.println(getSelector());
		}


	}

	private void mkProfile() {
		profile = new ProfilePanel(frame, wait, cust);
		setSelector(frame.getBar().getSelector());
		System.out.println("Profile Panel");
		delay();		
	}


	private void mkRegister() {
		reg = new RegisterPanel(frame, wait, cust);
		setSelector(frame.getBar().getSelector());
		System.out.println("Register Panel");
		delay();		

	}


	private void mkEdit() {
		edit = new EditFlightPanel(frame, wait, flightModel);
		System.out.println("Edit Flights Panel");
		setSelector(frame.getBar().getSelector());
		delay();		
	}


	public void selectFlight(){
		GetAllFlights getAll = new GetAllFlights();
		getAll.getFlights(flightModel);

		select = new SelectFlights();
		select.selectFlight(frame, wait,flightModel);
		System.out.println("Select Flights Panel");
		setSelector(frame.getBar().getSelector());
		delay();
	}
	public void mkSplash(){
		splash = new Splash(frame, wait);
		System.out.println("Splash Panel");
		setSelector(frame.getBar().getSelector());
		delay();
	}

	public void mkFlights(){
		System.out.println("Flight Maker Panel");
		flightPan = new CreateFlightPanel(frame, wait);
		setSelector(frame.getBar().getSelector());
		delay();
	}

	public void mkResults(){
		results = new ResultsPanel();
		results.Results(frame, wait, flightModel);
		System.out.println("Results Panel");
		setSelector(frame.getBar().getSelector());
		delay();
	}

	public void mkSearch(){
		searchPan = new SearchPanel();
		System.out.println("111");
		searchPan.searchPanel(frame,wait, flightModel);
		setSelector(frame.getBar().getSelector());
		delay();
	}

	public void mkLogin(){
		loginPanel = new LoginPanel();
		System.out.println("222");
		loginPanel.login(frame,wait, cust);
		setSelector(frame.getBar().getSelector());
		delay();
	}

	public void delay(){
		try {
			Thread.sleep(50);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}


	public int getSelector() {
		return selector;
	}
	public void setSelector(int selector) {
		this.selector = selector;
	}

}
