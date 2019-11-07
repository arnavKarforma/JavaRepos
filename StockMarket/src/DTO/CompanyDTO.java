package DTO;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class CompanyDTO {

	
	public CompanyDTO(int id, int numOfShares, double sharePrice, Map<Integer, Integer> investorsBoughtShares,
			int shareSold, boolean sharePriceDropped, double capital) {
		super();
		this.id = id;
		this.numOfShares = numOfShares;
		this.sharePrice = sharePrice;
		this.investorsBoughtShares = investorsBoughtShares;
		this.shareSold = shareSold;
		this.sharePriceDropped = sharePriceDropped;
		this.capital = capital;
	}
	

	public CompanyDTO() {
		// TODO Auto-generated constructor stub
	}


	private int id;
	private int numOfShares;
	private double sharePrice;
	private static List<CompanyDTO> companyList = new ArrayList<>();
	private static boolean shareLeft = false;
	private Map<Integer, Integer> investorsBoughtShares = new HashMap<Integer, Integer>();
	private int shareSold;
	private boolean sharePriceDropped = false;
	private double capital = 0;
	public static double cheapestSharePrice;
	public static int cheapestShareId ;

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public int getNumOfShares() {
		return numOfShares;
	}

	public void setNumOfShares(int numOfShares) {
		this.numOfShares = numOfShares;
	}

	public double getSharePrice() {
		return sharePrice;
	}

	public void setSharePrice(double sharePrice) {
		this.sharePrice = sharePrice;
	}

	public static List<CompanyDTO> getCompanyList() {
		return companyList;
	}

	public static void setCompanyList(CompanyDTO compDTO) {
		CompanyDTO.companyList.add(compDTO);
	}

	public static boolean isShareLeft() {
		shareLeft = false;
		double min = 100;
		for (CompanyDTO com : companyList) {
			if (com.numOfShares > 0) {
				if (com.getSharePrice() < min) {
					min = com.getSharePrice();
					cheapestShareId = com.id;
				}
			}
			if (com.getNumOfShares() > 0) {
				shareLeft = true;
			}
		}
		CompanyDTO.cheapestSharePrice = min;
		return shareLeft;
	}

	public Map<Integer, Integer> getInvestorsBoughtShares() {
		return investorsBoughtShares;
	}

	public int getShareSold() {
		return shareSold;
	}

	public void setShareSold(int shareSold) {
		this.shareSold = shareSold;
	}

	public void setInvestorsBoughtShares(Integer idOfInvestor) {
		if (!getInvestorsBoughtShares().isEmpty()) {
			if (getInvestorsBoughtShares().get(idOfInvestor) != null) {
				int previouslyBoughtShare = getInvestorsBoughtShares().get(idOfInvestor).intValue();
				this.investorsBoughtShares.put(idOfInvestor, previouslyBoughtShare + 1);
			}
		} else
			this.investorsBoughtShares.put(idOfInvestor, 1);
	}

	public double getCapital() {
		return capital;
	}

	public void setCapital(double capital) {
		this.capital = capital;
	}

	@Override
	public String toString() {
		return "CompanyDTO [id=" + id + ", numOfShares=" + numOfShares + ", sharePrice=" + sharePrice
				+ ", investorsBoughtShares=" + investorsBoughtShares + ", shareSold=" + shareSold + ", capital="
				+ capital + "]";
	}

	public static CompanyDTO getCompanyFromList(Integer company) {
		for (CompanyDTO c : companyList) {
			if (c.getId() == company.intValue()) {
				return c;
			}
		}
		System.out.println("Cannot find" + company);
		return null;
	}

	/**
	 * This method is to sell a share from company
	 *
	 * @param investor id
	 * @return boolean value of status of transaction
	 */
	public synchronized boolean sellShare(int investorId) {
		if (this.getNumOfShares() > 0) {
			int previousShare = this.getNumOfShares();
			int previousSoldShare = this.getShareSold();
			setNumOfShares(previousShare - 1);
			setShareSold(previousSoldShare + 1);
			setInvestorsBoughtShares(investorId);
			if (getNumOfShares() == 10) {
				doubleSharePrice();
				if (sharePriceDropped == false)
					halfSharePrices();
			}
			return true;
		}
		return false;

	}

	private void doubleSharePrice() {
		setSharePrice(getSharePrice() * 2);
	}

	private void halfSharePrices() {
		for (CompanyDTO c : companyList) {
			if (c.getShareSold() == 0) {
				c.setSharePrice(c.getSharePrice() / 2);
			}
		}
		sharePriceDropped = true;

	}

	public static void printAllCompany() {
		companyList.forEach(System.out::println);
	}
	
	public CompanyDTO getClone() {  
        
        return new CompanyDTO(id,numOfShares,sharePrice,investorsBoughtShares,
    			shareSold,sharePriceDropped,capital);  
    }  

}
