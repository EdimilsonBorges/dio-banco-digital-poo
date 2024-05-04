package digitalBank;

import java.util.ArrayList;
import java.util.List;

import digitalBank.accounts.Account;
import digitalBank.accounts.CurrentAccount;
import digitalBank.accounts.SavingsAccount;
import digitalBank.accounts.SpecialAccount;

public class Bank {

    static List<Account> acountList = new ArrayList<>();
    public static void main(String[] args) throws Exception {
        Client client1 = new Client("Maria","99999999999", 18);
        Client client2 = new Client("Ana", "4545345435", 25);
        Client client3 = new Client("Pedro", "4545345435", 40);

        Account account1 = new CurrentAccount(client1, 500.0);
        Account account2 = new SavingsAccount(client2);
        Account account3 = new SpecialAccount(client3);

        Bank.acountList.add(account1);
        Bank.acountList.add(account2);

        account1.deposit(0);
        account1.deposit(10);
        account1.withdrawDebit(200);
        account1.withdrawDebit(50);
        account1.withdrawCredit(200);
        account1.transfer(60, account2);
        account1.transfer(50, account2);
System.out.println("\n----------------------------------------------------------------");
        account2.deposit(0);
        account2.deposit(100);
        account2.withdrawDebit(200);
        account2.withdrawDebit(50);
        account2.withdrawCredit(50);
        account2.transfer(60, account1);
        account2.transfer(50, account1);
System.out.println("\n----------------------------------------------------------------");
        account3.deposit(0);
        account3.deposit(100);
        account3.withdrawDebit(200);
        account3.withdrawDebit(50);
        account3.withdrawCredit(50);
        account3.transfer(160, account1);
        account3.transfer(150, account1);
        account3.transfer(3060, account1);
        account3.transfer(3060, account1);
        account3.withdrawDebit(50);
        account3.deposit(1000);
    }
}
