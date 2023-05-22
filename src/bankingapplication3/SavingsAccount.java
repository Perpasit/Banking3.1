
package bankingapplication3;


public class SavingsAccount implements Account{
    private int accountNumber;
    private String accountName;
    private double balance;
    private final String accountType = "SavingsAccount";
    
    public SavingsAccount(int accountNumber, String accountName, double balance) {
        this.accountNumber = accountNumber;
        this.accountName = accountName;
        this.balance = balance;
    }
    
    @Override
    public int getAccountNumber() {
        return this.accountNumber;
    }
    @Override
    public String getAccountName() {
        return this.accountName;
    }
    
    @Override
    public void deposit(double amount) {
        this.balance = this.balance + amount;
    }
    @Override
    public void withdraw(double amount) {
        this.balance =this.balance - amount;
    }
    @Override
    public double getBalance() {
        return this.balance;
    }
    @Override
    public String getAccountType() {
        return this.accountType;
    }
}
