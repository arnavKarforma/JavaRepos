import java.io.*;
import java.util.ArrayList;
import java.util.ListIterator;

import javax.swing.JOptionPane;

public class MyProfitability {

	private static ArrayList<Tank> tanks = new ArrayList<Tank>();

	public static void userInterface() {
		
		int menu;
		menu = Integer.parseInt(JOptionPane.showInputDialog(null, "What action do you want to do:\n\n"
				+ "1) Add Daily Data\n"
				+ "2) Run Profitability Report\n"
				+ "3) Show Menu\n"
				+ "4) Exit\n\n"));
		
		while(menu >= 1 && menu < 4 && menu != 4) {
			
			if(menu == 1) {
				addDailyData();
			} else if(menu == 2) {
				runProfitabilityReport();
			} else if(menu == 3) {
				utilityMenu();
			} else if(menu == 4) {
				break;
			} else {
				JOptionPane.showMessageDialog(null, "Sorry you've entered an incorrect action request. Please try again.", "Error", JOptionPane.ERROR_MESSAGE);
			}
			
			menu = Integer.parseInt(JOptionPane.showInputDialog(null, "What action do you want to do:\n\n"
					+ "1) Add Daily Data\n"
					+ "2) Run Profitability Report\n"
					+ "3) Show Menu\n"
					+ "4) Exit\n\n"));
			
			
		}
		
	}

	private static void addDailyData() {
		
		int litres = Integer.parseInt(JOptionPane.showInputDialog(null, "Input litres to for filling today:\n"));
		int maxTempToReach = Integer.parseInt(JOptionPane.showInputDialog(null, "Input max temperature to reach for today:\n"));
		int daysToFerment = Integer.parseInt(JOptionPane.showInputDialog(null, "Input hours to ferment today:\n"));
		
		for(int i = 0; i < tanks.size(); i++) {
			tanks.get(i).setLitresHolding(litres);
			tanks.get(i).setMaxTemp(maxTempToReach);
			tanks.get(i).setDaysFermenting(daysToFerment);
			tanks.get(i).daysFermenting++;
		}
		
	}

	private static void runProfitabilityReport() {
		
		for(int i = 0; i < tanks.size(); i++) {
			double cost = tanks.get(i).calculateCosts(tanks.get(i).getDaysFermenting(), tanks.get(i).getMaxTemp(), tanks.get(i).getLitresHolding());
			System.out.println("Cost for running a " + tanks.get(i).getTankSize() + " tank: €" + cost
					+ " over " + tanks.get(i).getDaysFermenting() + " days, "
					+ "holding " + tanks.get(i).getLitresHolding() + " litres, "
					+ "and reaching a max temperature of " + tanks.get(i).getMaxTemp() + " C.");
		}
		
	}
	
	private static void utilityMenu() {
		
		int menu = Integer.parseInt(JOptionPane.showInputDialog(null, "What do you want to do:\n"
				+ "1) Build tank\n"
				+ "2) Destroy tank\n"
				+ "3) Display Tank Report\n\n" //TODO: get to print out when data is added, need data to write to file
				+ "Or enter a number not 1, 2 or 3 to quit.\n\n"));
		
		while(menu >= 1 && menu <= 4) {
			
			if(menu == 1) {
				int tankType = Integer.parseInt(JOptionPane.showInputDialog(null, "What tank do you want to build:\n"
						+ "1) Small tank\n"
						+ "2) Medium Tank\n"
						+ "3) Large Tank\n\n"
						+ "Or enter a number not 1, 2 or 3 to go back.\n\n"));
				
				createTank(tankType);
				
			} else if(menu == 2) {
				
				int tankId = Integer.parseInt(JOptionPane.showInputDialog(null, "What tank do you want to destroy, enter it\'s ID number:\n"));
				
				deleteTank(tankId);
				
			} else if(menu == 3) {
				
				getTankReport();
				
			} else if(menu == 4) {
				
				System.out.println("Writing to file");
				try {
					System.out.println("Infile");
					FileOutputStream fos = new FileOutputStream("brewingCompany.txt");
					ObjectOutputStream oos = new ObjectOutputStream(fos);
					
					for(int i = 0; i < tanks.size(); i++) {
				    	oos.writeObject(tanks.get(i).toString() + "\n");
				    	System.out.println("Wrote to file" + tanks.get(i).toString());
				    }
					
					oos.close();
					
				} catch (Exception e) {
					e.printStackTrace();
				}
				
			}
			
			menu = Integer.parseInt(JOptionPane.showInputDialog(null, "What do you want to do:\n"
					+ "1) Build tank\n"
					+ "2) Destroy tank\n"
					+ "3) Display Tank Report\n\n"
					+ "Enter a number not 1, 2 or 3 to quit.\n\n"));
		}
		
		System.out.println(tanks.toString());
		
	}
	
	public static void createTank(int tankToCreate) {
		
		int litres = Integer.parseInt(JOptionPane.showInputDialog(null, "Input litres to fill for the tank:\n"));
		int maxTempToReach = Integer.parseInt(JOptionPane.showInputDialog(null, "Input max temperature to reach for the tank:\n"));
		
		if (tankToCreate == 1 || tankToCreate == 2 || tankToCreate == 3) {
			
			if(tankToCreate == 1) {
				
				SmallTank smallTank = new SmallTank(litres, maxTempToReach);
				tanks.add(smallTank);
				
				JOptionPane.showMessageDialog(null, "New small tank built:\n\n" + 
						"Holiding " + litres
						+ " litres \nMax Temp: " + maxTempToReach
						+ " hours", "Info", JOptionPane.INFORMATION_MESSAGE);
				
				System.out.println(tanks.toString());
				
			} else if(tankToCreate == 2) {
			
				MediumTank mediumTank = new MediumTank(litres, maxTempToReach);
				tanks.add(mediumTank);
				
				JOptionPane.showMessageDialog(null, "New medium tank built:\n\n" + 
						"Holiding " + litres
						+ " litres \nMax Temp: " + maxTempToReach
						+ " hours", "Info", JOptionPane.INFORMATION_MESSAGE);
				
				System.out.println(tanks.toString());
				
			} else if(tankToCreate == 3) {
				
				LargeTank largeTank = new LargeTank(litres, maxTempToReach);
				tanks.add(largeTank);
				
				JOptionPane.showMessageDialog(null, "New large tank built:\n\n" + 
						"Holiding " + litres
						+ " litres \nMax Temp: " + maxTempToReach
						+ " hours", "Info", JOptionPane.INFORMATION_MESSAGE);
				
				System.out.println(tanks.toString());
				
			} else {
				utilityMenu();
			}
		}
		
		
		
		
		
	}
	
	private static void getTankReport() {
		String tankSummary = "";
		int i = 0;
		
		try {
			FileInputStream fis = new FileInputStream("brewingCompany.txt");
			ObjectInputStream ois = new ObjectInputStream(fis);
			
			String displayTank = (String) ois.readObject();
			tankSummary += "Tank " + i++ + ") " + displayTank.toString() + "\n";
			
			ois.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		System.out.println("Tank Summary: " + tankSummary);
		
	}
	
	private static void deleteTank(int tankId) {
		
		ListIterator<Tank> iterator = tanks.listIterator();
		while(iterator.hasNext()) {
			if(iterator.next().getId() == tankId) {
				iterator.remove();
				System.out.println("Removed: " + tankId);
				System.out.println(tanks.toString());
			}
		}
		
	}

}
