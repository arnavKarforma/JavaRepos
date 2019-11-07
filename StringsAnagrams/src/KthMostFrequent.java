import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.function.Function;
import java.util.stream.Collectors;

public class KthMostFrequent {

	@SuppressWarnings("rawtypes")
	public static List<String> mostfrequent(String[] ar, int k) {
		HashMap<String, Integer> hm = new HashMap<>();
		for (String str : ar) {
			Integer x = hm.get(str);
			if (x == null)
				x = 0;
			hm.put(str, x + 1);
		}
		
		HashMap<Integer, List<String>> newhm = new HashMap<>();
		for (Entry l : hm.entrySet()) {

			List<String> tempStr = new ArrayList<>();
			if (newhm.get(l.getValue()) != null)
				tempStr.addAll(newhm.get(l.getValue()));
			tempStr.add((String) l.getKey());
			newhm.put((Integer) l.getValue(), tempStr);
		}
		
		List<Entry> list = new ArrayList<>(newhm.entrySet());
		Collections.sort(list, new Comparator<Object>() {
			public int compare(Object o1, Object o2) {
				int num1 = (Integer) ((Entry) o1).getKey();
				int num2 = (Integer) ((Entry) o2).getKey();
				if (num1 == num2)
					return 0;
				if (num1 < num2)
					return 1;
				else
					return -1;
			}
		});
		list.forEach(System.out::println);
		System.out.println("-----------------------------------");
		if (list.size() >= k-1) {
			return  (List<String>) ( list.get(k-1)).getValue();
		} else
			return null;
	}

	public static void main(String[] args) {
		mostfrequent(new String[] { "a", "a","a","a", "aa", "aaa", "aaaa", "aaaa","aaaaa" }, 3).forEach(System.out::println);;
	}

}
 