
package DataGeneratorImpl;

import java.util.Random;

import Constraints.Constraints;
import DTO.CompanyDTO;
import DTO.InvestorDTO;
import DataGenerator.RandomDataGenerator;


public class RandomDataGeneratorImpl implements RandomDataGenerator {

	 private static RandomDataGeneratorImpl obj=new RandomDataGeneratorImpl();  
	 private RandomDataGeneratorImpl(){}  
	   
	 public static RandomDataGeneratorImpl getRandomDataGeneratorImpl(){  
	  return obj;  
	 }  
	private Random seedDataGenerator = new Random();

	/* (non-Javadoc)
	 * @see DataGeneratorImpl.RandomDataGenerator#generateRandomNumbers(int, int)
	 */
	@Override
	public int generateRandomNumbers(int maxRange, int minRange) {
		return seedDataGenerator.nextInt(maxRange + 1 - minRange) + minRange;
	}

	/* (non-Javadoc)
	 * @see DataGeneratorImpl.RandomDataGenerator#generateDataForCompanies()
	 */
	@Override
	public void generateDataForCompanies() {
		int idGenerator = 100;
		for (int i = 0; i < Constraints.MAX_COMPANIES; i++) {
			CompanyDTO companyDto = new CompanyDTO();
			companyDto.setId(idGenerator++);
			companyDto.setNumOfShares(generateRandomNumbers(Constraints.MAX_SHARES, Constraints.MIN_SHARES));
			companyDto.setSharePrice(generateRandomNumbers(Constraints.MAX_SHARES_PRICE, Constraints.MIN_SHARES_PRICE));
			CompanyDTO.setCompanyList(companyDto);
		}
	}

	/* (non-Javadoc)
	 * @see DataGeneratorImpl.RandomDataGenerator#generateDataForInvestors()
	 */
	@Override
	public void generateDataForInvestors() {
		int idGenerator = 1;
		for (int i = 0; i < Constraints.MAX_INVESTORS; i++) {
			InvestorDTO investorDto = new InvestorDTO();
			investorDto.setInvestorId(idGenerator++);
			investorDto
					.setBudget(generateRandomNumbers(Constraints.MAX_INVESTOR_BUDGET, Constraints.MIN_INVESTOR_BUDGET));
			InvestorDTO.setInvestorsList(investorDto);
		}

	}
}
