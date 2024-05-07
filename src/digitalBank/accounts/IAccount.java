package digitalBank.accounts;

public interface IAccount {

     void deposit(double value);
     void withdrawDebit(double value);
     void transfer(double value, Account account);  
} 