package DTO;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import DataGenerator.RandomDataGenerator;
import DataGeneratorImpl.RandomDataGeneratorImpl;


public class InvestorDTO {

	public InvestorDTO(int investorId, double budget, int shareBought, Set<Integer> shareBoughtInCompany) {
		super();
		this.investorId = investorId;
		this.budget = budget;
		this.shareBought = shareBought;
		this.shareBoughtInCompany = shareBoughtInCompany;
	}
	
	

	public InvestorDTO() {
		// TODO Auto-generated constructor stub
	}



	private int investorId;
	private double budget;
	private int shareBought = 0;
	private static List<InvestorDTO> investorsList = new ArrayList<>();
	private static boolean isBudgetLeft = false;
	private Set<Integer> shareBoughtInCompany = new HashSet<>();;
	private static List<Integer> investorsOutOfBudgetList = new ArrayList<>();

	public int getInvestorId() {
		return investorId;
	}

	public void setInvestorId(int investorId) {
		this.investorId = investorId;
	}

	public double getBudget() {
		return budget;
	}

	public void setBudget(double budget) {
		this.budget = budget;
	}

	public static List<InvestorDTO> getInvestorsList() {
		return investorsList;
	}

	public static InvestorDTO getInvestorFromList(Integer investor) {
		for (InvestorDTO i : investorsList) {
			if (i.getInvestorId() == investor) {
				return i;
			}
		}
		return null;
	}

	public static void setInvestorsList(InvestorDTO investors) {
		investorsList.add(investors);
	}

	public static boolean isBudgetLeft() {
		isBudgetLeft = false;
		for (InvestorDTO inv : investorsList) {
			if (inv.getBudget() > CompanyDTO.cheapestSharePrice) {
				isBudgetLeft = true;
			}
		}
		return isBudgetLeft;
	}

	public int getShareBought() {
		return shareBought;
	}

	public void setShareBought(int shareBought) {
		this.shareBought = shareBought;
	}

	public Set<Integer> getShareBoughtInCompany() {
		return shareBoughtInCompany;
	}

	public void setShareBoughtInCompany(Integer shareBoughtInCompany) {
		this.shareBoughtInCompany.add(shareBoughtInCompany);
	}

	@Override
	public String toString() {
		return "InvestorDTO [investorId=" + investorId + ", budget=" + budget + ", shareBought=" + shareBought + "]";
	}

	public static List<Integer> getInvestorsOutOfBudgetList() {
		return investorsOutOfBudgetList;
	}

	public static void setInvestorsOutOfBudgetList(List<Integer> investorsOutOfBudgetList) {
		InvestorDTO.investorsOutOfBudgetList = investorsOutOfBudgetList;
	}

	private int randomCompanyId() {
		RandomDataGenerator utility = RandomDataGeneratorImpl.getRandomDataGeneratorImpl();
		int randomComId = utility.generateRandomNumbers(199, 100);
		if (this.getShareBoughtInCompany().isEmpty() || this.getShareBoughtInCompany().size() == 50) {
			return randomComId;
		}
		if (randomComId == CompanyDTO.cheapestShareId) {
			return randomComId;
		}
		while (true) {
			if (getShareBoughtInCompany().size() == 100) {
				return randomComId;
			}

			if (this.getShareBoughtInCompany().contains(randomComId)) {
				randomComId = utility.generateRandomNumbers(199, 100);
				continue;
			} else
				break;

		}
		return randomComId;
	}

	/**
	 * This method is to buy a share from company
	 *
	 * @param nothing
	 * @return nothing
	 */
	public void buyShare() {
		int companyId = randomCompanyId();
		while (true) {
			CompanyDTO comp = CompanyDTO.getCompanyFromList(companyId);
			if (this.budget >= comp.getSharePrice()) {
				buyShare(comp);
				return;
			}
			if (budget < CompanyDTO.cheapestSharePrice) {
				investorsOutOfBudgetList.add(this.investorId);
				return;
			}
			companyId = randomCompanyId();
			System.out.println("random" + companyId);

		}
		/*
		 * while (true) { CompanyDTO comp =
		 * CompanyDTO.getCompanyFromList(companyId); traversedCompany++; if
		 * (traversedCompany == CompanyDTO.getCompanyList().size() - 1) {
		 * buyShare(comp); break; } if (comp.getInvestorsBoughtShares() != null)
		 * { if (comp.getInvestorsBoughtShares().containsKey(investorId)) {
		 * companyId = randomCompanyId(); continue; } } buyShare(comp); break;
		 * 
		 * }
		 */ }

	private void buyShare(CompanyDTO c) {
		boolean shareBought = c.sellShare(investorId);
		if (!shareBought) {
			System.out.println("company share sold");
			buyShare();
			return;
		}
		budget = budget - c.getSharePrice();
		this.shareBought++;
		this.setShareBoughtInCompany(new Integer(c.getId()));
	}

	public static void printAllInvestors() {
		investorsList.forEach(System.out::println);
	}

	public InvestorDTO getClone() {

		return new InvestorDTO(investorId, budget, shareBought, shareBoughtInCompany);
	}
}
