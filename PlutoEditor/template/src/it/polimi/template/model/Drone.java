package it.polimi.template.model;

import java.util.Random;

public class Drone {

	public static final int LONG = 1;
	public static final int SHORT = 2;
	public static final int HEAVY = 3;
	public static final int LIGHT = 4;

	public static final int FREE = 5;
	public static final int BUSY = 6;
	public static final int CHARGING = 6;

	private int id;
	private int status;
	private int shapeCategory;
	private int batteryLevel;


	public Drone() {
		this.id = new Random().nextInt(Integer.MAX_VALUE) +1;
		this.status=5;
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

	public int getShapeCategory() {
		return shapeCategory;
	}

	public void setShapeCategory(int shapeCategory) {
		this.shapeCategory = shapeCategory;
	}

	public int getBatteryLevel() {
		return batteryLevel;
	}

	public void setBatteryLevel(int batteryLevel) {
		this.batteryLevel = batteryLevel;
	}
	
	
	public boolean flyToAndDoAction(String target, Action action){
		//TODO
		try {
			Thread.sleep(5000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		action.doAction();
		this.status=Drone.FREE;
		return true;
	}
}
