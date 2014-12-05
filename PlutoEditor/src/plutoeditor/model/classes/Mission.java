package plutoeditor.model.classes;

import java.util.ArrayList;
import java.util.Random;



public class Mission {
	
	private static final int UNEXECUTED = 1;
	private static final int RUNNING = 2;
	private static final int COMPLETED = 3;
	private static final int FAILED = 4;
	private static final int STANDBY = 5;

	private int  id;
	private int status;
	private ArrayList<Trip> trips;

	public Mission() {

		this.id= new Random(1000).nextInt();
		this.status=1;

	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public int getStatus() {
		return status;
	}

	public void setStatus(int status) {
		this.status = status;
	}

	public ArrayList<Trip> getTrips() {
		return trips;
	}

	public void setTrips(ArrayList<Trip> trips) {
		this.trips = trips;
	}


}
