
package bankingapplication3;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.logging.Level;
import java.util.logging.Logger;

public class Bank {     
    private String bankName;
    
    public Bank() {
    }
    
    public Bank(String bankName) {
        this.bankName = bankName;
    }
    
    public String getBankName() {
        return this.bankName;
    }
    
    public void listAccount() {
        Connection con = BankConnection.connect();
        String sql = "SELECT * FROM account";
        try {
            Statement statement = con.createStatement();
            ResultSet results = statement.executeQuery(sql);
            while (results.next()) {
                System.out.println(results.getString(1) +" "+ results.getString(2) +" "+ results.getString(3) + " "+ results.getString(4)  );
            }
            
        } catch (SQLException ex) {
            Logger.getLogger(Bank.class.getName()).log(Level.SEVERE, null, ex);
        }
        
    }
    
    
    
    
    public void openAccount(Account account) {
        Connection con = BankConnection.connect();
        String sql = "INSERT INTO account(accID, accName, accBalance, accType) VALUES(?,?,?,?)";
        try {
            PreparedStatement preparedstatement = con.prepareStatement(sql);
            preparedstatement.setInt(1, account.getAccountNumber());
            preparedstatement.setString(2, account.getAccountName());
            preparedstatement.setDouble(3, account.getBalance());
            preparedstatement.setString(4, account.getAccountType());
            preparedstatement.executeUpdate();
        } catch (SQLException ex) {
            Logger.getLogger(Bank.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    public void closeAccount(Account account) {
        Connection con = BankConnection.connect();
        String sql = "DELETE FROM account WHERE accID = ? AND accType = ?";
        try {
            PreparedStatement preparedstatement = con.prepareStatement(sql);
            preparedstatement.setInt(1, account.getAccountNumber());
            preparedstatement.setString(2, account.getAccountType());
            preparedstatement.executeUpdate();
        } catch (SQLException ex) {
            Logger.getLogger(Bank.class.getName()).log(Level.SEVERE, null, ex);
        }
        
    }
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    public void depositMoney(Account account, double amount) {
        account.deposit(amount);
        System.out.println(account.getBalance());
        Connection con = BankConnection.connect();
        String sql = "UPDATE  account SET accBalance = ? WHERE accID = ?";
        try {
            PreparedStatement preparedstatement = con.prepareStatement(sql);
            preparedstatement.setDouble(1, account.getBalance());
            preparedstatement.setInt(2, account.getAccountNumber());
            preparedstatement.executeUpdate();
        } catch (SQLException ex) {
            Logger.getLogger(Bank.class.getName()).log(Level.SEVERE, null, ex);
        }
        
    }
    

    public void withdrawMoney(Account account, double amount) {
        account.withdraw(amount);
        System.out.println(account.getBalance());
        Connection con = BankConnection.connect();
        String sql = "UPDATE account SET accBalance = ? WHERE accID = ?";
        try {
            PreparedStatement preparedstatement = con.prepareStatement(sql);
            preparedstatement.setDouble(1, account.getBalance());
            preparedstatement.setInt(2, account.getAccountNumber());
            preparedstatement.executeUpdate();
        } catch (SQLException ex) {
            Logger.getLogger(Bank.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    
    
    
    
    
    
    
    
    
    
    public Account getAccount(int accountNumber, String accountType) {
        Connection con = BankConnection.connect();
        Account account = null;
        String sql = "SELECT * FROM account WHERE accID = ? AND accType = ?";
        try {
            PreparedStatement preparedstatement = con.prepareStatement(sql);
            preparedstatement.setInt(1, accountNumber);
            preparedstatement.setString(2, accountType);
            ResultSet results = preparedstatement.executeQuery();
            results.next();
            String accountName = results.getString(2);
            double balance = results.getDouble(3);
            if(accountType.equals("SavingsAccount")) {
                account = new SavingsAccount(accountNumber, accountName, balance);
            } else if(accountType.equals("CurrentAccount")) {
                account = new CurrentAccount(accountNumber, accountName, balance);
            }
            
        } catch (SQLException ex) {
            Logger.getLogger(Bank.class.getName()).log(Level.SEVERE, null, ex);
        }
        return account;
    }
    
    
    
}
