package plutoeditor.model.classes;



public class Trip {
	
	private static final int WAITING = 1;
	private static final int COMPLETED = 2;
	static final int FAILED = 3;

	private String sourceLocation;
	private String targetLocation;
	private int delay;
	private int priority;
	private int status;
	private Drone drone;
	private Item item;
	
	private String startTime;
		
	public Trip() {
		this.delay=0;
		this.priority=100;
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


}
