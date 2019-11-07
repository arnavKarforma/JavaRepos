
package DataGenerator;


public interface RandomDataGenerator {

	/**
	 * This method generates a random number based on minimum and upper limit
	 *
	 * @param nothing
	 * @return nothing
	 */
	int generateRandomNumbers(int maxRange, int minRange);

	/**
	 * This method populates the random data for companies
	 *
	 * @param nothing
	 * @return nothing
	 */
	void generateDataForCompanies();

	/**
	 * This method creates data for investors
	 *
	 * @param nothing
	 * @return nothing
	 */
	void generateDataForInvestors();

}