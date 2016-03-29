package Controller;

import Model.Flights;

public class FlightStart {


		public static void main(String[] arg){
			
			 Flights flightModel = new Flights();;

			FlightController fc = new FlightController(flightModel);
			fc.run();
			
			

		}
	}
