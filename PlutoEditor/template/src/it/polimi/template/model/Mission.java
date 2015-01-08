package it.polimi.template.model;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;



public class Mission {
	
	public static final int UNEXECUTED = 1;
	public static final int RUNNING = 2;
	public static final int COMPLETED = 3;
	public static final int FAILED = 4;
	public static final int STANDBY = 5;

	private int  id;
	private String name;
	private int status;
	private List<Trip> trips;
	private boolean used= false;

	public Mission() {

		this.id= new Random().nextInt(Integer.MAX_VALUE) +1;
		this.status= UNEXECUTED;
		this.trips = new ArrayList<Trip>();

	}
	
	public Mission(String name) {

		this.name=name;
		this.id= new Random().nextInt(Integer.MAX_VALUE) +1;
		this.status= UNEXECUTED;
		this.trips = new ArrayList<Trip>();

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

	public List<Trip> getTrips() {
		return trips;
	}

	public void setTrips(List<Trip> trips) {
		this.trips = trips;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public boolean getUsed() {
		return used;
	}

	public void setUsed(boolean used) {
		this.used = used;
	}





}
