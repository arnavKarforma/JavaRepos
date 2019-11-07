/**
 * ?????????????????????????????????????????????/
 *
 * @author Arnav Kaforma
 * @version DD-MM-YY
 */

package dummy;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.OptionalDouble;
import java.util.stream.Collectors;

/**
 * @author ARNAV
 *
 */
public class string {

	public static void main(String[] args) {
		List<String> arr = new ArrayList<>(Arrays.asList(new String[]{"EUR0.67","EUR3.67","EUR4.67","EUR9.67","EUR8.57"}));
		Double arr2 = arr.stream().map(str -> str.replace("EUR", "")).map(Double::parseDouble).mapToDouble(v -> v).max().orElse(0.00);
		
		System.out.println("EUR"+arr2);
		
				
	}
}
