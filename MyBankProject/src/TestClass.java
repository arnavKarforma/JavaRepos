
public class TestClass {

	public static void main(String[] args) {
		MyBankController1 mac = new MyBankController1();
		mac.createAccount("Arnav1", "1234");
		mac.createAccount("Arnav2", "1234");
		mac.createAccount("Arnav3", "1234");
		mac.createAccount("Arnav4", "1234");
		
		/*mac.makeWithdrawal("8769869781", 5);
		mac.makeWithdrawal("8769869781", 5);
		mac.makeWithdrawal("8769869781", 5);
		mac.displayAccount("8769869781");
		mac.removeAccount("8769869781");
		mac.makeWithdrawal("8769869781", 5);
		mac.displayAccount("8769869781");*/
		mac.customerInterface();
		/*//BankAccount ba = new BankAccount("Arnav Karforma", "9876875764");
		ba.makeLodgement(0);
		ba.makeLodgement(20);
		ba.makeWithdrawal(30);*/
		//mac.printAccountDetails(ba);

	}

}
