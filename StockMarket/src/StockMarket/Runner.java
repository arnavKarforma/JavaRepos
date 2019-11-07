package StockMarket;

import DataGenerator.RandomDataGenerator;
import DataGeneratorImpl.RandomDataGeneratorImpl;
import Simulator.TradingDaySimularor;
import SimulatorInterface.TradingDaySimularorInterface;

public class Runner {
public static void main(String[] args) {
	RandomDataGenerator random = RandomDataGeneratorImpl.getRandomDataGeneratorImpl();
	random.generateDataForCompanies();
	//CompanyDTO.printAllCompany();
	random.generateDataForInvestors();
	//InvestorDTO.printAllInvestors();
	TradingDaySimularorInterface ts = new TradingDaySimularor();
	ts.traddingday();
}
}
