package model;

public class Player {

	private String name;
	private int pointsScored;
	
	public Player(String name, int pointsScored) {
		super();
		this.name = name;
		this.pointsScored = pointsScored;
	}
	
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public int getPointsScored() {
		return pointsScored;
	}
	public void setPointsScored(int pointsScored) {
		this.pointsScored = pointsScored;
	}
	
}
