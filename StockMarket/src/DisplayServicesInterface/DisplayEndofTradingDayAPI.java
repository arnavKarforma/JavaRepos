
package DisplayServicesInterface;


public interface DisplayEndofTradingDayAPI {

	/**
	 * This method gives companies with highest capital
	 *
	 * @param nothing
	 * @return nothing
	 */
	String companyWithHighestCapital();

	/**
	 * This method gives companies with lowest capital
	 *
	 * @param nothing
	 * @return nothing
	 */
	String companyWithLowestCapital();

	/**
	 * This method gives investor with highest number of company invested
	 *
	 * @param nothing
	 * @return nothing
	 */
	String investorWithHighestInvestments();

	/**
	 * This method gives investor with lowest number of company invested
	 *
	 * @param nothing
	 * @return nothing
	 */
	String investorWithLowestInvestments();

}