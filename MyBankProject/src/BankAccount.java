import javax.swing.JOptionPane;

/**
 * BankAccount class contains few fields of user and there utility functions for
 * banking application of “MyBankInc”.
 *
 * @author Arnav Kaforma
 * @version 12-02-2019
 */
public class BankAccount {

	// once the customerName and accountNumber is inserted during the object
	// creation of Bank account cannot be changed hence no setters for them.
	private final String customerName;
	private final String accountNumber;

	// declared currentBalance & accountStatus set with default value in
	// constructor and can be latter
	// changed with there setter
	private double currentBalance;
	private boolean accountStatus;

	// Hard-coded currency symbol EURO
	public static final String CURRENCY_SYMBOL = "\u20AC";

	public static final String CUSTOMER_INTERFACE_DISPLAY = "[1] Make a Lodgement : \n "
			+ "[2] Make a withdrawl : \n [3]  Display Account Details : \n [4] Close Account : \n "
			+ "[5] Exit";
	public static final String BANK_OFFICIAL_INTERFACE_DISPLAY = "[1] Display All Accounts : \n "
			+ "[2] Display All Active Accounts \n [3]  Open a New Account : \n [4] Close an Existing Account : \n "
			+ "[5] Run start of the day : \n [6] Run End of the Day: \n [7] Exit";
	/**
	 * Parameterized constructor for BankAccount which accepts only two of the
	 * class fields
	 */
	public BankAccount(String customerName, String accountNumber) {
		super();
		this.customerName = customerName;
		this.accountNumber = accountNumber;
		this.currentBalance = 0.00;
		this.accountStatus = true;
	}

	/**
	 * Parameterized constructor for BankAccount which accepts only two of the
	 * class fields
	 */
	public BankAccount(String customerName, String accountNumber, Double currentBalance, boolean accountStatus) {
		super();
		this.customerName = customerName;
		this.accountNumber = accountNumber;
		this.currentBalance = currentBalance;
		this.accountStatus = accountStatus;
	}

	
	/**
	 * @return current Balance of the customers
	 */
	public double getCurrentBalance() {
		return currentBalance;
	}

	public String getCurrentBalanceInEuro() {
		return this.CURRENCY_SYMBOL + String.format(" %.2f", currentBalance);
	}

	/**
	 * @return AccountStatus in boolean value
	 */
	public boolean isAccountStatus() {
		return accountStatus;
	}

	/**
	 * 
	 * @param set
	 *            AccountStatus to accountStatus in class BankAccount
	 */
	public void setAccountStatus(boolean accountStatus) {
		this.accountStatus = accountStatus;
	}

	/**
	 * @return customerName
	 */
	public String getCustomerName() {
		return customerName;
	}

	/**
	 * @return accountNumber of customer inserted during object creation
	 */
	public String getAccountNumber() {
		return accountNumber;
	}

	/**
	 * method to deposit money in any customers account
	 *
	 * @param amount
	 *            takes the amount to lodged
	 * @return nothing
	 */
	public void makeLodgement(double amount) {
		if (accountStatus == false) {
			JOptionPane.showMessageDialog(null, "This account is not active. No any tranaction can be performed.",
					"ACCOUNT NOT ACTIVE", JOptionPane.ERROR_MESSAGE);
			return;
		}
		if (amount > 0) {
			currentBalance += amount;
			JOptionPane.showMessageDialog(null,
					"Your deposit of " + CURRENCY_SYMBOL + String.format(" %.2f", amount) + " is successful.",
					"BALANCE ADDED", JOptionPane.INFORMATION_MESSAGE);
		} else
			JOptionPane.showMessageDialog(null, "Please ensure to add amount more than \u20AC 0.00 to continue",
					"INVALID AMOUNT", JOptionPane.ERROR_MESSAGE);

	}

	/**
	 * method to withdraw money from any customers account
	 *
	 * @param amount
	 *            takes money that is to be deducted from customer account
	 * @return nothing
	 */
	public void makeWithdrawal(double amount) {
		double balanceWillBe = currentBalance - amount;
		if (accountStatus == false) {
			JOptionPane.showMessageDialog(null, "This account is not active. No any tranaction can be performed.",
					"ACCOUNT NOT ACTIVE", JOptionPane.ERROR_MESSAGE);
			return;
		}
		if (amount > 0) {
			if (balanceWillBe >= 0) {
				currentBalance -= amount;
				JOptionPane.showMessageDialog(null,
						"Amount of " + CURRENCY_SYMBOL + String.format(" %.2f", amount) + " is succesfully withdrawn."
								+ "Current account balance is " + CURRENCY_SYMBOL
								+ String.format(" %.2f", currentBalance),
						"BALANCE WITHDRAWN", JOptionPane.INFORMATION_MESSAGE);
			} else
				JOptionPane.showMessageDialog(null,
						"Sorry Insufficent funds\n\n" + "The balance of your account is : " + CURRENCY_SYMBOL
								+ String.format(" %.2f", currentBalance) + "\n\nYou requested a withdrawal of : "
								+ CURRENCY_SYMBOL + String.format(" %.2f", amount) + "\n\nShortfall of : "
								+ CURRENCY_SYMBOL + String.format(" %.2f", (amount - currentBalance)) + "\n\n",
						"Warning", JOptionPane.WARNING_MESSAGE);

		} else
			JOptionPane.showMessageDialog(null, "Please ensure to add amount more than \u20AC 0.00 to continue",
					"INVALID AMOUNT", JOptionPane.ERROR_MESSAGE);

	}

	/**
	 * method to closeAccount of any existing user just changes the
	 * accountStatus
	 *
	 * @param takes
	 *            no input
	 * @return nothing
	 */
	public void closeAccount() {
		double refund = getCurrentBalance();
		if (refund > 0.00)
			makeWithdrawal(currentBalance);
		if (this.accountStatus) {
			this.accountStatus = false;
			JOptionPane.showMessageDialog(null, "Account has been closed with " + CURRENCY_SYMBOL
					+ String.format(" %.2f", refund) + " refunded\n\n", "Confirmation", JOptionPane.PLAIN_MESSAGE);
		} else
			JOptionPane.showMessageDialog(null, "Account was already closed", "Confirmation",
					JOptionPane.PLAIN_MESSAGE);
	}

	/**
	 * method to override the default toString of the object BankAccount
	 *
	 * @param takes
	 *            nothing
	 * @return takes nothing
	 */
	@Override
	public String toString() {
		return customerName + ", A/C " + accountNumber + ", Balance = " + CURRENCY_SYMBOL
				+ String.format(" %.2f", currentBalance)+ ", Status = "+ accountStatus;
	}

}
