package dIgitalBank.accounts;

public interface IAccount {

     boolean deposit(double value);
     boolean withdraw(double value);
     boolean transfer(double value, Account account);
    
} 