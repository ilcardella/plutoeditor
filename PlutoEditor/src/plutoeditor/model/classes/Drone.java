package plutoeditor.model.classes;

public class Drone {
	private int id;
	private int status;
	private int shapeCategory;
	private int batteryLevel;
	

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
}
