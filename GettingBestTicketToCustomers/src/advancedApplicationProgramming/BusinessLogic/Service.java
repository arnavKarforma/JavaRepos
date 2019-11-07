/**
 * ?????????????????????????????????????????????/
 *
 * @author Arnav Kaforma
 * @version DD-MM-YY
 */

package advancedApplicationProgramming.BusinessLogic;

import advancedApplicationProgramming.DTO.EventsDTO;
import advancedApplicationProgramming.DTO.LocationDTO;

/**
 * @author ARNAV
 *
 */
public interface Service {

	/**
	 * This method generates a random number based on range
	 *
	 * @param nothing
	 * @return nothing
	 */
	int generateRandomNumbers(int range);

	/**
	 * This method generates a random number based on minimum and upper limit
	 *
	 * @param nothing
	 * @return nothing
	 */
	int generateRandomNumbers(int maxRange, int minRange);

	/**
	 * This method on constructor call checks if the Input file is present and
	 * has data if not it calls the generate data to generate seed data
	 * 
	 * @param nothing
	 * @return nothing
	 */
	void checkForInput();

	/**
	 * This method writes the data to the Input file if the file is empty or not
	 * present
	 *
	 * @param filePath
	 * @return nothing
	 */
	void writeInFile(String filePath);

	/**
	 * This method writes the data to the Input file if the file is empty or not
	 * present
	 *
	 * @param nothing
	 * @return nothing
	 */
	void readFromFile(String filePath);

	/**
	 * This method generates seed data in case the input file is not present
	 *
	 * @param nothing
	 * @return nothing
	 */
	void generateData();

	/**
	 * This method takes the user location and applies all the business logic to
	 * get the best event
	 *
	 * @param LocationDTO(user
	 *            location)
	 * @return EventDTO(final event)
	 */
	EventsDTO findClosesEvents(LocationDTO searchLocation);

	Integer calculateDistance(int x, int x1, int y, int y1);

}