package it.polimi.template.model;

public class Item {
	public static final int LONG = 1;
	public static final int SHORT = 2;
	public static final int HEAVY = 3;
	public static final int LIGHT = 4;

	private String name;
	private int id;
	private int shapeCategory;

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public int getShapeCategory() {
		return shapeCategory;
	}

	public void setShapeCategory(int shapeCategory) {
		this.shapeCategory = shapeCategory;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

}
