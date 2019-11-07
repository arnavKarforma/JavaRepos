package javaAssignmentDA2;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Test {

	public static void main(String[] args) {
		List<Measurement> ms = new ArrayList<>();
		ms.add(new Measurement(3, 45.0));
		ms.add(new Measurement(4, 47.0));
		ms.add(new Measurement(5, 49.0));
		ms.add(new Measurement(6, 50.0));
		ms.add(new Measurement(7, 55.0));
		ms.add(new Measurement(8, 53.0));
		ms.add(new Measurement(9, 56.0));
		String arr[] = {"abc", "cdf", "jhg"};
		WeatherStation ws = new WeatherStation("fgh", ms, Arrays.asList(arr));
		System.out.println(ws.countTemperatures(45.0,53.6,50.0));

	}

}
