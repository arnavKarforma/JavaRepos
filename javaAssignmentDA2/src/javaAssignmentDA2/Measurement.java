package javaAssignmentDA2;

public class Measurement {

	private int time;
	private double temperature;
	public Measurement(int time, double temperature) {
		super();
		this.time = time;
		this.temperature = temperature;
	}
	public int getTime() {
		return time;
	}
	public void setTime(int time) {
		this.time = time;
	}
	public double getTemperature() {
		return temperature;
	}
	public void setTemperature(double temperature) {
		this.temperature = temperature;
	}
	
}
