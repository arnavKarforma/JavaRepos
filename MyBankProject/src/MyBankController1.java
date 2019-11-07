import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JOptionPane;

/**
 * MyBankController class is to take input from the user and create an
 * BankAccount Object for banking application of “MyBankInc”.
 *
 * @author Arnav Kaforma
 * @version 12-02-2019
 */
public class MyBankController1 {
	private List<BankAccount> bankAccountList;
	BankAccount bankAccount;
    private final String  FILE_PATH= "\\src\\Output.txt";
	/**
	 * Non-parameterized constructor for MyBankController
	 */
	public MyBankController1() {
		this.bankAccountList = new ArrayList<>();
	}

	/**
	 * @return ArrayList of BanKAccount Objects
	 */
	public List<BankAccount> getBanKAccountArr() {
		return bankAccountList;
	}

	/**
	 * method to create Account of any customer by creating Object of
	 * BankAccount by accepting params
	 *
	 * @param customerName
	 *            and AccountNumber (the fields being accepted in the
	 *            BankAccount class and the rest are set by default)
	 * @return nothing
	 */
	public void createAccount(String customerName, String accountNumber) {
		bankAccount = new BankAccount(customerName, accountNumber);
		bankAccountList.add(bankAccount);
		printAccountDetails(bankAccount);
	}

	/**
	 * Its a utility method to display the bank account details of any customer
	 *
	 * @param BankAccount
	 *            (Object of BankAccount class)
	 * @return nothing
	 */
	private void printAccountDetails(BankAccount bankAccount) {
		JOptionPane.showMessageDialog(null,
				bankAccount.toString().substring(0, bankAccount.toString().indexOf(", Status = ")), "ACCOUNT DETAILS",
				JOptionPane.INFORMATION_MESSAGE);
	}

	/**
	 * It is a utility method to show all the account details available in the
	 * memory to the user
	 *
	 * @param nothing
	 * @return nothing
	 */
	public void listAllAccount() {
		StringBuilder allBankAccStr = new StringBuilder();
		allBankAccStr.append("List of All Accounts :" + "\n");
		for (BankAccount bankAccount : bankAccountList) {
			allBankAccStr.append("Account Number: " + bankAccount.getAccountNumber() + ", Customer Name: "
					+ bankAccount.getCustomerName() + ", Account Balance: " + bankAccount.getCurrentBalanceInEuro()
					+ ", Account Active: " + bankAccount.isAccountStatus() + "\n" + "\n");
		}
		if (bankAccountList.size() < 1) {
			allBankAccStr.append("\n" + "No any account to display");
		}
		JOptionPane.showMessageDialog(null, allBankAccStr, "ACCOUNT DETAILS", JOptionPane.INFORMATION_MESSAGE);
	}

	/**
	 * It is a utility method to show all the active account details available
	 * in the memory to the user
	 *
	 * @param nothing
	 * @return nothing
	 */
	public void listAllOpenAccounts() {
		boolean isThrAnyActiveAcc = false;
		StringBuilder allBankAccStr = new StringBuilder();
		for (BankAccount bankAccount : bankAccountList) {
			if (bankAccount.isAccountStatus()) {
				isThrAnyActiveAcc = true;
				allBankAccStr.append("Account Number: " + bankAccount.getAccountNumber() + ", Customer Name: "
						+ bankAccount.getCustomerName() + ", Account Balance: " + bankAccount.getCurrentBalanceInEuro()
						+ ", Account Active: " + bankAccount.isAccountStatus() + "\n" + "\n");

			}
		}
		if (!isThrAnyActiveAcc) {
			allBankAccStr.append("\n" + "No any active account to display");
		}
		JOptionPane.showMessageDialog(null, allBankAccStr, "ACCOUNT DETAILS", JOptionPane.INFORMATION_MESSAGE);
	}

	/**
	 * It is a utility method to get the index of the account number from the
	 * list of accounts
	 * 
	 *
	 * @param account
	 *            number of the customer
	 * @return index of the account number
	 */
	private int getIndex(String accountNumber) {
		BankAccount index = bankAccountList.stream().filter(v -> accountNumber.equals(v.getAccountNumber())).findAny()
				.orElse(null);
		if (index != null) {
			return bankAccountList.indexOf(index);
		} else
			return -1;
	}

	/**
	 * It is a request mapping method to perform withdrawal on given user
	 * account in memory
	 *
	 * @param account
	 *            number of the customer, amount to be withdrawn
	 * @return nothing
	 */
	public void makeWithdrawal(String accountNumber, double amount) {
		int index = getIndex(accountNumber);
		if (index != -1)
			bankAccountList.get(index).makeWithdrawal(amount);
		else
			JOptionPane.showMessageDialog(null, "This account doesn't exist", "OPERATION FAILED",
					JOptionPane.WARNING_MESSAGE);
	}

	/**
	 * It is a request mapping method to perform Lodgement on given user account
	 * in memory
	 *
	 * @param account
	 *            number of the customer, amount to be deposited
	 * @return nothing
	 */
	public void makeLodgement(String accountNumber, double amount) {
		int index = getIndex(accountNumber);
		if (index != -1)
			bankAccountList.get(index).makeLodgement(amount);
		else
			JOptionPane.showMessageDialog(null, "This account doesn't exist", "OPERATION FAILED",
					JOptionPane.WARNING_MESSAGE);
	}

	/**
	 * It is a request mapping method to close given user account in the memory
	 *
	 * @param account
	 *            number of the customer
	 * @return nothing
	 */
	public void closeAccount(String accountNumber) {
		int index = getIndex(accountNumber);
		if (index != -1)
			bankAccountList.get(index).closeAccount();
		else
			JOptionPane.showMessageDialog(null, "This account doesn't exist", "OPERATION FAILED",
					JOptionPane.WARNING_MESSAGE);
	}

	/**
	 * It is a request mapping method which closes user account in the memory
	 *
	 * @param account
	 *            number of the customer
	 * @return nothing
	 */
	public void removeAccount(String accountNumber) {
		int index = getIndex(accountNumber);
		if (index != -1) {
			if (bankAccountList.get(index).isAccountStatus())
				bankAccountList.get(index).closeAccount();
			bankAccountList.remove(index);
		} else
			JOptionPane.showMessageDialog(null, "This account doesn't exist", "OPERATION FAILED",
					JOptionPane.WARNING_MESSAGE);
	}

	/**
	 * It is a request mapping method to show details of the requested account
	 *
	 * @param account
	 *            number of the customer
	 * @return nothing
	 */
	public void displayAccount(String accountNumber) {
		int index = getIndex(accountNumber);
		if (index != -1)
			JOptionPane.showMessageDialog(null, bankAccountList.get(index).toString().substring(0, bankAccountList.get(index).toString().indexOf(", Status = ")), "ACCOUNT DETAILS",
					JOptionPane.INFORMATION_MESSAGE);
		else
			JOptionPane.showMessageDialog(null, "This account doesn't exist", "OPERATION FAILED",
					JOptionPane.WARNING_MESSAGE);
	}

	/**
	 * It is a request mapping method to let the customer perform all the
	 * operations based on there Account
	 *
	 * @param nothing
	 * 
	 * @return nothing
	 */
	public void customerInterface() {
		String userAccount = JOptionPane.showInputDialog(null, "Please Enter your account number", "ACCOUNT LOGIN",
				JOptionPane.DEFAULT_OPTION);
		if (userAccount == null) {
			return;
		}
		int userInput;
		if (!userAccount.isEmpty()) {
			int index = getIndex(userAccount);
			if (index != -1) {
				while (true) {
					try {
						String userInputInString = JOptionPane.showInputDialog(null,
								"Please Select an Option Below: \n\n" + BankAccount.CUSTOMER_INTERFACE_DISPLAY,
								"MyBank ATM", JOptionPane.DEFAULT_OPTION);
						if (userInputInString == null) {
							return;
						}
						userInput = Integer.parseInt(userInputInString);

					} catch (NumberFormatException e) {
						JOptionPane.showMessageDialog(null, "Please enter a number to Proceed", "OPERATION FAILED",
								JOptionPane.WARNING_MESSAGE);
						continue;
					}
					if (!performCustomerOperations(userInput, userAccount))
						break;
				}

			} else {
				JOptionPane.showMessageDialog(null, "This User Account doesn't exists", "OPERATION FAILED",
						JOptionPane.WARNING_MESSAGE);
				customerInterface();
			}
		} else {
			JOptionPane.showMessageDialog(null, "Please enter a User Account to proceed", "OPERATION FAILED",
					JOptionPane.WARNING_MESSAGE);
			customerInterface();
			// customerInterface();
		}

	}

	/**
	 * It is a utility method to let the customer perform all the
	 * operations based on there Account
	 *
	 * @param User
	 *            Input (task number to be performed by user), User Account
	 *            Number
	 * 
	 * @return boolean value (which determines whether to continue with other
	 *         operations or close it)
	 */
	private boolean performCustomerOperations(int userInput, String userAccount) {
		boolean continuePerformingOperations = true;
		switch (userInput) {
		case 1:
			double amountToBeLodged = 0.0;
			String amountToBeLodgedInString = JOptionPane.showInputDialog(null, "Enter The amount to be Lodged",
					"MyBank ATM", JOptionPane.DEFAULT_OPTION);
			if (amountToBeLodgedInString == null) {
				return continuePerformingOperations;
			}
			if (amountToBeLodgedInString.isEmpty()) {
				JOptionPane.showMessageDialog(null, "Please enter amount to proceed", "OPERATION FAILED",
						JOptionPane.WARNING_MESSAGE);
				return continuePerformingOperations;
			}
			try {
				amountToBeLodged = Double.parseDouble(amountToBeLodgedInString);
			} catch (NumberFormatException e) {
				JOptionPane.showMessageDialog(null, "Please enter a number to Proceed", "OPERATION FAILED",
						JOptionPane.WARNING_MESSAGE);
				return continuePerformingOperations;
			}
			this.makeLodgement(userAccount, amountToBeLodged);
			return continuePerformingOperations;
		case 2:
			double amountToBeWithdrawn = 0.0;
			String amountToBeWithdrawnInString = JOptionPane.showInputDialog(null, "Enter The amount to be Lodged",
					"MyBank ATM", JOptionPane.DEFAULT_OPTION);
			if (amountToBeWithdrawnInString == null) {
				return continuePerformingOperations;
			}
			if (amountToBeWithdrawnInString.isEmpty()) {
				JOptionPane.showMessageDialog(null, "Please enter a number to proceed", "OPERATION FAILED",
						JOptionPane.WARNING_MESSAGE);
				return continuePerformingOperations;
			}
			try {
				amountToBeWithdrawn = Double.parseDouble(amountToBeWithdrawnInString);
			} catch (NumberFormatException e) {
				JOptionPane.showMessageDialog(null, "Please enter a number to Proceed", "OPERATION FAILED",
						JOptionPane.WARNING_MESSAGE);
				return continuePerformingOperations;
			}
			this.makeWithdrawal(userAccount, amountToBeWithdrawn);
			return continuePerformingOperations;
		case 3:
			this.displayAccount(userAccount);
			return continuePerformingOperations;
		case 4:
			this.closeAccount(userAccount);
			return continuePerformingOperations;
		case 5:
			continuePerformingOperations = false;
			return continuePerformingOperations;
		default:
			JOptionPane.showMessageDialog(null, "Please enter from the given options, don't try on your own",
					"OPERATION FAILED", JOptionPane.WARNING_MESSAGE);
			
		}
		return continuePerformingOperations;
	}

	/**
	 * It is a request mapping method to let the bank officials perform all the
	 * operations on existing account
	 *
	 * @param nothing
	 * 
	 * @return nothing
	 */
	public void bankInterface() {
		int userInput;
		while (true) {
			try {
				String userInputInString = JOptionPane.showInputDialog(null,
						"Please Select an Option Below: \n" + BankAccount.BANK_OFFICIAL_INTERFACE_DISPLAY, "MyBank ATM",
						JOptionPane.DEFAULT_OPTION);
				if (userInputInString == null) {
					return;
				}
				userInput = Integer.parseInt(userInputInString);

			} catch (NumberFormatException e) {
				JOptionPane.showMessageDialog(null, "Please enter a number to Proceed", "OPERATION FAILED",
						JOptionPane.WARNING_MESSAGE);
				continue;
			}
			if (!performBankOfficialOperations(userInput))
				break;
		}

	}

	/**
	 * It is a utility method to let the Bank Official perform all the
	 * operations based on there task choice
	 *
	 * @param User
	 *            Input (task number to be performed by user)
	 * 
	 * @return boolean value (which determines whether to continue with other
	 *         operations or close it)
	 */
	private boolean performBankOfficialOperations(int userInput) {
		boolean continuePerformingOperations = true;
		switch (userInput) {
		case 1:
			this.listAllAccount();
			return continuePerformingOperations;
		case 2:
			this.listAllOpenAccounts();
			return continuePerformingOperations;
		case 3:
			String customerName = JOptionPane.showInputDialog(null, "Enter Customer Name", "MyBank System",
					JOptionPane.DEFAULT_OPTION);
			if (customerName == null) {
				return continuePerformingOperations;
			}
			if (customerName.isEmpty()) {
				JOptionPane.showMessageDialog(null, "Please enter a name to proceed", "OPERATION FAILED",
						JOptionPane.WARNING_MESSAGE);
				return continuePerformingOperations;
			}
			String accountNumber = JOptionPane.showInputDialog(null, "Enter Account Name", "MyBank System",
					JOptionPane.DEFAULT_OPTION);
			if (accountNumber == null) {
				return continuePerformingOperations;
			}
			if (accountNumber.isEmpty()) {
				JOptionPane.showMessageDialog(null, "Please enter account number to proceed", "OPERATION FAILED",
						JOptionPane.WARNING_MESSAGE);
				return continuePerformingOperations;
			}
			this.createAccount(customerName, accountNumber);
			return continuePerformingOperations;
		case 4:
			String userAccount = JOptionPane.showInputDialog(null, "Account Number that has to be closed",
					"MyBank System", JOptionPane.DEFAULT_OPTION);
			if (userAccount == null) {
				return continuePerformingOperations;
			}
			if (userAccount.isEmpty()) {
				JOptionPane.showMessageDialog(null, "Please enter account number to proceed", "OPERATION FAILED",
						JOptionPane.WARNING_MESSAGE);
				return continuePerformingOperations;
			}
			this.closeAccount(userAccount);
			return continuePerformingOperations;
		case 5:
			this.fileIn();
			return continuePerformingOperations;
		case 6:
			this.fileOut();
			return continuePerformingOperations;
		case 7:
			continuePerformingOperations = false;
			return continuePerformingOperations;
		default:
			JOptionPane.showMessageDialog(null, "Please enter from the given options, don't try on your own",
					"OPERATION FAILED", JOptionPane.WARNING_MESSAGE);
		}
		return continuePerformingOperations;

	}

	/**
	 * It is a request mapping method to let the bank officials perform write
	 * operation to persist the transactions of the day
	 *
	 * @param nothing
	 * 
	 * @return nothing
	 */
	public void fileOut() {
		try (FileWriter fw = new FileWriter(new File("").getAbsolutePath() + FILE_PATH)) {
			bankAccountList.forEach(p -> {
				try {
					String line = p + System.lineSeparator();
					fw.write(line);
				} catch (IOException e) {
					e.printStackTrace();
				}
			});
		} catch (IOException e) {
			System.out.println(e);
		} catch (Exception e) {
			System.out.println(e);
		}
	}

	/**
	 * It is a request mapping method to let the bank officials perform pull
	 * operation of persisted transactions of past
	 *
	 * @param nothing
	 * 
	 * @return nothing
	 */
	public void fileIn() {
		try (FileReader fr = new FileReader(new File("").getAbsolutePath() + FILE_PATH)) {
			int index;
			String line = "";
			String bankAccParam[];
			while ((index = fr.read()) != -1) {
				char character = (char) index;
				line += character;
				if (character == '\n') {
					bankAccParam = line.split(",");
					BankAccount ba = new BankAccount(bankAccParam[0].trim(), bankAccParam[1].trim().replace("A/C ", ""),
							Double.parseDouble(bankAccParam[2].trim().replace("Balance = € ", "")),
							Boolean.getBoolean(bankAccParam[3].trim().replace("Status = ", "")));
					bankAccountList.add(ba);
					line = "";
				}
			}

		} catch (Exception e) {

		}

	}

}
