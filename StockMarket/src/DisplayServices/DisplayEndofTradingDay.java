 package DisplayServices;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

import DTO.CompanyDTO;
import DTO.InvestorDTO;
import DisplayServicesInterface.DisplayEndofTradingDayAPI;

public class DisplayEndofTradingDay implements DisplayEndofTradingDayAPI {

	
	public DisplayEndofTradingDay() {
		calculateCapital();	
	}

	List<CompanyDTO> sortedCompanyList = new ArrayList<>();
	List<InvestorDTO> sortedInvestmentList = new ArrayList<>();
	
	private void calculateCapital() {
		for (CompanyDTO c : CompanyDTO.getCompanyList()) {
			//System.out.println("calculated");
			c.setCapital(c.getShareSold() * c.getSharePrice());
			//System.out.println("calculated"+c.getCapital()+""+c.getShareSold() +""+ c.getSharePrice());
		}
	}
	
	
	/* (non-Javadoc)
	 * @see DisplayServices.DisplayEndofTradingDayAPI#companyWithHighestCapital()
	 */
	@Override
	public String companyWithHighestCapital() {
		String str = null;
		sortedCompanyList.addAll(CompanyDTO.getCompanyList());
		Collections.sort(sortedCompanyList, new Comparator<CompanyDTO>() {
			@Override
			public int compare(CompanyDTO c0, CompanyDTO c1) {
				double capital1 = c0.getCapital();
				double capital2 = c1.getCapital();
				if (capital1 == capital2)
					return 0;
				else if (capital1 > capital2)
					return -1;
				else
					return 1;
			}

		});
		/*System.out.println("_________________________________________________________________");
		sortedCompanyList.forEach(System.out::println);
		System.out.println("_________________________________________________________________");
		*/
		str = sortedCompanyList.get(0) + "\n";
		int index = 0;
		for (index = 1; index < sortedCompanyList.size(); index++) {
			if(index == 0){
				continue;
			}
			if (sortedCompanyList.get(index).getCapital() == sortedCompanyList.get(0).getCapital()) {
				str += sortedCompanyList.get(index) + "\n";
			} else {
				break;
			}
		
		}
		return str;

	}

	/* (non-Javadoc)
	 * @see DisplayServices.DisplayEndofTradingDayAPI#companyWithLowestCapital()
	 */
	@Override
	public String companyWithLowestCapital() {
		String str = sortedCompanyList.get(sortedCompanyList.size() - 1) + "\n";
		for (int index = sortedCompanyList.size() - 2; index >= 0; index--) {
			if (sortedCompanyList.get(index).getCapital() == sortedCompanyList.get(sortedCompanyList.size() - 1)
					.getCapital()) {
				str += sortedCompanyList.get(index) + "\n";
			} else {
				break;
			}
		}
		return str;

	}

	/* (non-Javadoc)
	 * @see DisplayServices.DisplayEndofTradingDayAPI#investorWithHighestInvestments()
	 */
	@Override
	public String investorWithHighestInvestments() {
		sortedInvestmentList.addAll(InvestorDTO.getInvestorsList());
		Collections.sort(sortedInvestmentList, new Comparator<InvestorDTO>() {
			@Override
			public int compare(InvestorDTO i0, InvestorDTO i1) {
				double num1 = i0.getShareBought();
				double num2 = i1.getShareBought();
				if (num1 == num2)
					return 0;
				else if (num1 > num2)
					return -1;
				else
					return 1;
			}

		});
		String str = sortedInvestmentList.get(0) + "\n";
		int index =0;
		for (index =1 ; index <= sortedInvestmentList.size(); index++) {
			if (sortedInvestmentList.get(index).getShareBought() == sortedInvestmentList.get(0).getShareBought()) {
				str += sortedInvestmentList.get(index) + "\n";
			} else {
				break;
			}
		}
		return str;

	}

	/* (non-Javadoc)
	 * @see DisplayServices.DisplayEndofTradingDayAPI#investorWithLowestInvestments()
	 */
	@Override
	public String investorWithLowestInvestments() {
		String str = sortedInvestmentList.get(sortedInvestmentList.size() - 1) + "\n";
		for (int index = sortedInvestmentList.size() - 2; index >= 0; index--) {
			if (sortedInvestmentList.get(index).getShareBought() == sortedInvestmentList.get(sortedInvestmentList.size() - 1)
					.getShareBought()) {
				str += sortedInvestmentList.get(index) + "\n";
			} else {
				break;
			}
		}
		return str;


	}

}
