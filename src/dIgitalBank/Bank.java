package digitalBank;

import java.util.ArrayList;
import java.util.List;

import digitalBank.accounts.Account;
import digitalBank.accounts.CurrentAccount;

public class Bank {

    static List<Account> acountList = new ArrayList<>();
    public static void main(String[] args) throws Exception {
        Client client1 = new Client("Maria","99999999999", 18);
        Client client2 = new Client("Ana", "4545345435", 25);

        Account account1 = new CurrentAccount(client1);
        Account account2 = new CurrentAccount(client2);

        Bank.acountList.add(account1);
        Bank.acountList.add(account2);

        account1.deposit(0);
        account1.deposit(100);
        account1.withdraw(200);
        account1.withdraw(50);
        account1.transfer(60, account2);
        account1.transfer(50, account2);
System.out.println("\n----------------------------------------------------------------");
        account2.deposit(0);
        account2.deposit(100);
        account2.withdraw(200);
        account2.withdraw(50);
        account2.transfer(60, account1);
        account2.transfer(50, account1);




    }
}
