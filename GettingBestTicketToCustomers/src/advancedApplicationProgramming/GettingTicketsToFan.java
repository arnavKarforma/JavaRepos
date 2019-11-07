/**
 *
 * @author Arnav Kaforma
 * @version 23-03-2019
 */

package advancedApplicationProgramming;

import java.util.Scanner;

import javax.swing.JFrame;
import javax.swing.JOptionPane;

import advancedApplicationProgramming.BusinessLogic.Service;
import advancedApplicationProgramming.BusinessLogicImpl.ServiceImpl;
import advancedApplicationProgramming.DTO.EventsDTO;
import advancedApplicationProgramming.DTO.LocationDTO;

/**
 * This is the main test class that accepts input from user and calls the
 * respective services
 *
 **/
public class GettingTicketsToFan {
	static Scanner sc = new Scanner(System.in);

	/**
	 * This method validates the user input
	 *
	 * @param String
	 *            (Takes user Input as string)
	 * @return nothing
	 */
	public static String readInput() {
		String input = sc.next();
		String regex = Constraints.INPUT_PATTERN;
		String result;
		if (input.matches(regex)) {
			result = input;
		} else {
			System.out.println("Coordinates entered are not valid. Please try again in x,y fromat");
			result = readInput();
		}
		return result;
	}

	/**
	 * This method calls the interface to tell the user the best fit Event for
	 * them
	 *
	 * @param String
	 *            (Takes user Input as string)
	 * @return nothing
	 */
	public static void main(String[] args) {
		Service utility = new ServiceImpl ();
		LocationDTO loc = new LocationDTO();
		System.out.println("Please type Coordinates in the format x,y");
		String input = readInput();
		System.out.println("Input has been read " + input);
		String[] axisMeasures = input.split(",");
		loc.setX_axis(Integer.parseInt(axisMeasures[0]));
		loc.setY_axis(Integer.parseInt(axisMeasures[1]));
		EventsDTO events = utility.findClosesEvents(loc);
		/*JFrame f = new JFrame();  
		JOptionPane.showMessageDialog(f,
				"The Best Event for you is" + System.lineSeparator() + "Event Id = " + events.getId()
						+ "Ticket with Price = " + events.getCheapestPriceInPriceList() + Constraints.CURRENCY_SYMBOL
						+ System.lineSeparator() + "Distance Froim Your Location = " + events.getDistance(),
				"Best Ticket You Can Buy", JOptionPane.INFORMATION_MESSAGE);
*/
		System.out.println("-------------------------Final Output--------------------------------------");
		System.out.println("The Best Event for you is" + System.lineSeparator() + "Event Id = " + events.getId()
		+ "Ticket with Price = " + events.getCheapestPriceInPriceList() + Constraints.CURRENCY_SYMBOL
		+ System.lineSeparator() + "Distance From Your Location = " + events.getDistance());

	}

}
