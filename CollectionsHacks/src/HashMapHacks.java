import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

public class HashMapHacks {

	public static void main(String[] args) {
		HashMap<String, List<String>> hm = new HashMap<>();
		hm.put("Arnav", new ArrayList<String>(Arrays.asList(new String[] { "Java", "Java", "Hibernate" })));
		hm.put("Arnav2", new ArrayList<String>(Arrays.asList(new String[] { "Java", "Spring", "Hibernate" })));
		hm.put("Arnav3", new ArrayList<String>(Arrays.asList(new String[] { "Java", "Spring", "Hibernate" })));
		// Check if particular key exits
		System.out.println(hm.containsKey("Arnav"));

		// Check if particular Value exists in hashmap
		System.out.println(hm.containsValue("Java"));// false
		System.out.println(
				hm.containsValue(new ArrayList<String>(Arrays.asList(new String[] { "Java", "Spring", "Hibernate" })))); // true
		for (Map.Entry me : hm.entrySet()) {
			int i = 0;
			for (String mel : hm.get(me.getKey())) {
				if (mel.equals("Java"))
					++i;
			}
			if (i > 0)
				System.out.println("This Key " + me.getKey() + " has the value " + "Java " + i + " this many times");
		}

		// Map key to arraylist
		ArrayList<String> ar = new ArrayList<>(hm.keySet());
		ar.forEach(System.out::println);
		// Map values to arraylist
		ArrayList<String> ar2 = new ArrayList<>();
		int size = 0;
		for (String me : hm.keySet()) {
			ar2.addAll(hm.get(me)); // map.values() for primitive type values
		}
		ar2.forEach(System.out::println);

		// return the key of a value
		for (String e : hm.keySet()) {
			List<String> ar21 = new ArrayList<>(hm.get(e));
			if (ar21.contains("Java")) {
				System.out.println(e);
			}
		}
		// adding to map on particular key
		List<String> ar_new = new ArrayList<>(hm.get("Arnav"));
		ar_new.add("MicroService");
		hm.put("Arnav", ar_new);
		hm.forEach((k, v) -> System.out.println(k + " " + v));

		// k,v ->v,k
		HashMap<String, ArrayList<String>> newHm = new HashMap<>();
		for (String k : hm.keySet()) {
			for (String mel : hm.get(k)) {
				ArrayList<String> ar1 = null;
				ar1 = new ArrayList<>();
				if (newHm.get(mel) != null) {
					ar1 = new ArrayList<>(newHm.get(mel));
				}
				ar1.add(k);
				newHm.put(mel, ar1);

			}
		}
		System.out.println("___________________________________");
		newHm.forEach((k, v) -> System.out.println(k + " " + v));
		System.out.println("___________________________________");
        
		 	//Reducing in hashmap
		
		// Remove Key
		hm.remove("Arnav");
		hm.forEach((k, v) -> System.out.println(k + " " + v));
	}

}
