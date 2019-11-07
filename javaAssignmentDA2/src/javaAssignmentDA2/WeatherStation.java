package javaAssignmentDA2;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

public class WeatherStation {

	private String city;
	private List<Measurement> measurement;
	private static List<String> stations;

	public WeatherStation(String city, List<Measurement> measurement, List<String> stations) {
		super();
		this.city = city;
		this.measurement = measurement;
		this.stations = stations;
	}

	public String getCity() {
		return city;
	}

	public void setCity(String city) {
		this.city = city;
	}

	public List<Measurement> getMeasurement() {
		return measurement;
	}

	public void setMeasurement(List<Measurement> measurement) {
		this.measurement = measurement;
	}

	public static List<String> getStations() {
		return stations;
	}

	public static void setStations(List<String> stations) {
		WeatherStation.stations = stations;
	}

	public double maxtemperature(int startTime, int endTime) {
		Optional<Measurement> meas = measurement.stream()
				.filter(measurement -> measurement.getTime() >= startTime && measurement.getTime() <= endTime)
				.max(Comparator.comparing(Measurement::getTemperature));
		return meas.get().getTemperature();
	}

	public List<HashMap<Double, Integer>> countTemperatures(double t1, double t2, double r) {
		HashMap<Double, List<Double>> lhm1 = new HashMap<>();
		HashMap<Double, List<Double>> lhm2 = new HashMap<>();
		HashMap<Double, Integer> lhr1 = new HashMap<>();
		HashMap<Double, Integer> lhr2 = new HashMap<>();
		List<HashMap<Double, Integer>> finalmap = new ArrayList<>();
        
		lhm1 = map(t1, r);
		lhm2 = map(t2,r);
        lhr1 = reduce(lhm1,t1);
        lhr2 = reduce(lhm2,t2);
        finalmap.add(lhr1);
        finalmap.add(lhr2);
		return finalmap;

	}

	public HashMap<Double, List<Double>> map(Double t1, Double r) {
		HashMap<Double, List<Double>> hm= new HashMap<>();
		List<Double> meas = measurement.stream()
				.filter(measurement -> measurement.getTemperature() >= t1-r && measurement.getTemperature() <= t1+r)
				.map(Measurement::getTemperature).collect(Collectors.toList());
		hm.put(t1,meas);
		return hm ;

	}

	public HashMap<Double,Integer> reduce(HashMap<Double, List<Double>> mapped, double t1) {
		HashMap<Double, Integer> hm= new HashMap<Double, Integer>();
		System.out.println(mapped);
		int count = mapped.values().stream().mapToInt(List::size).sum();
		System.out.println(count);
		hm.put(t1, new Integer(count));
		return hm;
				

	}

}
