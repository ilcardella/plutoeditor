package it.polimi.template.model;

public class Trip  {

	public static final int WAITING = 1;
	public static final int COMPLETED = 2;
	public static final int FAILED = 3;
	public static final int EXECUTING = 4;
	public static final int DELAYED = 5;

	private String name;
	private Action action;
	private String sourceLocation;
	private String targetLocation;
	private int delay;
	private int priority;
	private int status;
	private Drone drone;
	private Item item;
	private String startTime;
	private Mission mission;
	private boolean used=false;

	public Trip() {
		this.delay = 0;
		this.priority = 100;
		this.status = WAITING;
	}

	public String getSourceLocation() {
		return sourceLocation;
	}

	public void setSourceLocation(String sourceLocation) {
		this.sourceLocation = sourceLocation;
	}

	public String getTargetLocation() {
		return targetLocation;
	}

	public void setTargetLocation(String targetLocation) {
		this.targetLocation = targetLocation;
	}

	public int getDelay() {
		return delay;
	}

	public void setDelay(int delay) {
		this.delay = delay;
	}

	public int getPriority() {
		return priority;
	}

	public void setPriority(int priority) {
		this.priority = priority;
	}

	public Drone getDrone() {
		return drone;
	}

	public void setDrone(Drone drone) {
		this.drone = drone;
	}

	public Item getItem() {
		return item;
	}

	public void setItem(Item item) {
		this.item = item;
	}

	public String getStartTime() {
		return startTime;
	}

	public void setStartTime(String startTime) {
		this.startTime = startTime;
	}

	public int getStatus() {
		return status;
	}

	public void setStatus(int status) {
		this.status = status;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Mission getMission() {
		return mission;
	}

	public void setMission(Mission mission) {
		this.mission = mission;
	}

	public boolean getUsed() {
		return used;
	}

	public void setUsed(boolean used) {
		this.used = used;
	}

	public Action getAction() {
		return action;
	}

	public void setAction(Action action) {
		this.action = action;
	}

	public static String getStatusNameFromValue(int value) {

		switch (value) {
			case WAITING:
				return "WAITING";
			case EXECUTING:
				return "EXECUTING";
			case COMPLETED:
				return "COMPLETED";
			case FAILED:
				return "FAILED";
			case DELAYED:
				return "DELAYED";
			default:
				return "Unknown";
		}
	}



}




