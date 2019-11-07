import javax.swing.JOptionPane;

/**
 * MyBankController class is to take input from the user and create an
 * BankAccount Object for banking application of “MyBankInc”.
 *
 * @author Arnav Kaforma
 * @version 12-02-2019
 */
public class MyBankController {
	private static int numbersOfCustomersExisting = 0;
	private static final int NUMBERS_OF_CUSTOMER_ALLOWED = 3;
	private BankAccount bankAccountArr[];
	BankAccount bankAccount;

	/**
	 * Non-parameterized constructor for MyBankController
	 */
	public MyBankController() {
		this.numbersOfCustomersExisting = 0;
		this.bankAccountArr = new BankAccount[NUMBERS_OF_CUSTOMER_ALLOWED];
	}

	/**
	 * @return Array of BanKAccount
	 */
	public BankAccount[] getBanKAccountArr() {
		return bankAccountArr;
	}

	/**
	 * 
	 * @param set
	 *            Array of BanKAccount Object to BanKAccountArr in class
	 *            BankAccount
	 */
	public void setBanKAccountArr(BankAccount[] bankAccountArr) {
		this.bankAccountArr = bankAccountArr;
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
		System.out.println(numbersOfCustomersExisting);
		if (numbersOfCustomersExisting < NUMBERS_OF_CUSTOMER_ALLOWED) {
			bankAccount = new BankAccount(customerName, accountNumber);
			bankAccountArr[numbersOfCustomersExisting] = bankAccount;
			numbersOfCustomersExisting++;
			printAccountDetails(bankAccount);
		} else
			JOptionPane.showMessageDialog(null, "No more accounts can be created further at this moment", "ATTENTION",
					JOptionPane.ERROR_MESSAGE);

	}

	/**
	 * Its a utility method to display the bank account details of any customer
	 *
	 * @param BankAccount
	 *            (Object of BankAccount class)
	 * @return nothing
	 */
	private void printAccountDetails(BankAccount bankAccount) {
		JOptionPane.showMessageDialog(null, bankAccount, "ACCOUNT DETAILS", JOptionPane.INFORMATION_MESSAGE);
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
		for (BankAccount bankAccount : bankAccountArr) {
			if (bankAccount != null) {
				System.out.println(bankAccount);
				allBankAccStr.append("Account Number: " + bankAccount.getAccountNumber() + ", Customer Name: "
						+ bankAccount.getCustomerName() + ", Account Balance: " + bankAccount.getCurrentBalanceInEuro()
						+ ", Account Active: " + bankAccount.isAccountStatus() + "\n" + "\n");
			}
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
		StringBuilder allBankAccStr = new StringBuilder();
		for (BankAccount bankAccount : bankAccountArr) {
			if (bankAccount != null) {
				if (bankAccount.isAccountStatus())
					allBankAccStr.append("Account Number: " + bankAccount.getAccountNumber() + ", Customer Name: "
							+ bankAccount.getCustomerName() + ", Account Balance: "
							+ bankAccount.getCurrentBalanceInEuro() + ", Account Active: "
							+ bankAccount.isAccountStatus() + "\n" + "\n");
			}
		}
		JOptionPane.showMessageDialog(null, allBankAccStr, "ACCOUNT DETAILS", JOptionPane.INFORMATION_MESSAGE);
	}
	
	

}
