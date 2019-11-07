
package Simulator;

import javax.swing.JOptionPane;

import DTO.CompanyDTO;
import DTO.InvestorDTO;
import DataGeneratorImpl.RandomDataGeneratorImpl;
import DisplayServices.DisplayEndofTradingDay;
import DisplayServicesInterface.DisplayEndofTradingDayAPI;
import SimulatorInterface.TradingDaySimularorInterface;

public class TradingDaySimularor implements TradingDaySimularorInterface {

	/* (non-Javadoc)
	 * @see Simulator.TradingDaySimularorInterface#traddingday()
	 */
	@Override
	public void traddingday() {
		while (InvestorDTO.isBudgetLeft()) {
			if (!CompanyDTO.isShareLeft()) {
				break;
			}
			Integer investorId = new Integer(
					RandomDataGeneratorImpl.getRandomDataGeneratorImpl().generateRandomNumbers(100, 1));
			if (InvestorDTO.getInvestorsOutOfBudgetList().contains(investorId)) {
				continue;
			}
			//System.out.println("company vanished" + InvestorDTO.getInvestorsOutOfBudgetList().size());
			InvestorDTO investor = InvestorDTO.getInvestorFromList(investorId);
			investor.buyShare();
			
			System.out.println("1" + CompanyDTO.isShareLeft());
			System.out.println("2" + InvestorDTO.isBudgetLeft());

		}
		DisplayEndofTradingDayAPI ds = new DisplayEndofTradingDay();
		String str = "Highest capital company" + "\n" + ds.companyWithHighestCapital() + "Lowest capital company" + "\n"
				+ ds.companyWithLowestCapital() + "\n" + "Highest number of shared bought by Investor" + "\n"
				+ ds.investorWithHighestInvestments() + "Highest number of shared bought by Investor" + "\n"
				+ ds.investorWithLowestInvestments();
		JOptionPane.showMessageDialog(null, str);
		System.out.println(str);

	}
}
