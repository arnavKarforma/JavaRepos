import java.util.HashMap;
import java.util.Map;
import java.util.Map.Entry;

/**
 * ?????????????????????????????????????????????/
 *
 * @author Arnav Kaforma
 * @version DD-MM-YY
 */

/**
 * @author ARNAV
 *
 */
public class TrySomething {
public static void main(String[] args) {
	TrysomethingDto td = new TrysomethingDto("Arnav", 123);
	td.setName("Arnav");
	TrysomethingDto td2 = new TrysomethingDto("Arnav", 123);
	td2.setName("Arnav");
	
	
	if(td.equals(td2))
		System.out.println("td.equals(td2)");
	if(td.getName().equals(td2.getName()))
		System.out.println("td.getName().equals(td2.getName())");
	if(td.getName() == (td2.getName()))
		System.out.println("td.getName() == (td2.getName())");
	
	int arr[] = new int[4];
	String arrStr[] = new String[8];
	System.out.println(arr[3]);
	Map<String,TrysomethingDto> mp = new HashMap<>();
	
	mp.put("one", new TrysomethingDto("Arnav", 123));
	mp.put("two", new TrysomethingDto("Arnav2", 123));
	mp.put("three", new TrysomethingDto("Arnav3", 123));
	
	for(Entry e: mp.entrySet()){
		System.out.println(e.getValue()+" "+e.getKey());
		
	}
	
}
}
