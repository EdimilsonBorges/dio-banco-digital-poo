package digitalBank;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Scanner;

import digitalBank.accounts.Account;
import digitalBank.accounts.CurrentAccount;
import digitalBank.accounts.IAccount;
import digitalBank.accounts.SavingsAccount;
import digitalBank.accounts.SpecialAccount;

public class Bank {

    private List<Client> clientList = new ArrayList<>();
    private Map<Integer, Account> accountList = new HashMap<>();
    private Scanner scanner = new Scanner(System.in);

    public void createAccount() {

        if (clientList.isEmpty()) {
            System.out.println(IAccount.YELLOW + "Não existe nenhum cliente, vamos cadastrar um" + IAccount.RESET);
            creatClient();

        }
        System.out.println("Selecione o cliente para abrir a conta");

        for (int i = 1; i <= clientList.size(); i++) {
            int select = i - 1;
            System.out.println(i + ". " + clientList.get(select).getName());
        }

        int clientSelected = optionInt();
        clientSelected--;
        System.out.println("1. Conta Poupança");
        System.out.println("2. Conta Corrente");
        System.out.println("3. Conta Especial");
        System.out.println("Selecione o tipo de conta que você quer abrir no banco.");
        int typeAccont = optionInt();
        Account account = null;

        if (typeAccont == 1) {
            account = new SavingsAccount(clientList.get(clientSelected), accountList.size() + 1);
        } else if (typeAccont == 2) {
            account = new CurrentAccount(clientList.get(clientSelected), accountList.size() + 1, 500.0);
        } else if (typeAccont == 3) {
            account = new SpecialAccount(clientList.get(clientSelected), accountList.size() + 1);
        } else {
            System.out.println((IAccount.RED + "Opção Inválida" + IAccount.RESET));
        }

        if (account != null) {
            accountList.put(account.getNumberAccount(), account);
            System.out.println(IAccount.GREEN + "Conta criada com sucesso!" + IAccount.RESET);
        } else {
            createAccount();
            return;
        }

        System.out.println(IAccount.YELLOW + "Deseja criar uma nova conta? S/N" +IAccount.RESET);
        String register = scanner.nextLine();
        if (register.equalsIgnoreCase("S")) {
            createAccount();
            return;
        }
    }

    public void creatClient() {
        Client client = null;
        System.out.println("Digite o CPF do novo cliente");
        String cpf = scanner.nextLine();
        if (cpf.length() == 11) {
            try {
                Long.valueOf(cpf);
            } catch (Exception e) {
                System.out.println((IAccount.RED + "CPF inválido, seu CPF precisa conter apenas números" + IAccount.RESET));
                creatClient();
                return;
            }
        } else {
            System.out.println((IAccount.RED + "CPF inválido, seu CPF precisa conter 11 números" + IAccount.RESET));
            creatClient();
            return;
        }
        System.out.println("Digite o nome do cliente");
        String name = scanner.nextLine();
        System.out.println("Digite a idade do cliente");
        int age = optionInt();

        client = new Client(name, cpf, age);
        System.out.println(IAccount.GREEN + "Cliente cadastrado com sucesso!" + IAccount.RESET);
        clientList.add(client);

        System.out.println(IAccount.YELLOW + "Deseja cadastrar um novo clinte? S/N" + IAccount.RESET);
        String register = scanner.nextLine();
        if (register.equalsIgnoreCase("S")) {
            creatClient();
            return;
        }
    }

    public void transfer() {
        verify();
        System.out.println("De qual conta você que transferir?");
        accountList
                .forEach((i, a) -> System.out.println(i + ". " + a.getClient().getName() + " " + a.getNumberAccount()));
        int accountFrom = optionInt();

        System.out.println("Para qual conta você que transferir?");
        accountList
                .forEach((i, a) -> System.out.println(i + ". " + a.getClient().getName() + " " + a.getNumberAccount()));

        int accountTo = optionInt();

        System.out.println("Qual valor você deseja transferir?");
        double value = valueDouble();

        accountList.get(accountFrom).transfer(value, accountList.get(accountTo));
    }

    public void withdraw() {
        verify();
        int option = optionInt();
        System.out.println("Qual valor você deseja sacar?");
        double valueWithdraw = valueDouble();
        if (!(accountList.get(option) instanceof CurrentAccount)) {
            accountList.get(option).withdrawDebit(valueWithdraw);
        } else {
            System.out.println("Selecione 1 para débito ou 2 para crédito.");
            int option3 = optionInt();
            if (option3 == 1) {
                accountList.get(option).withdrawDebit(valueWithdraw);
            } else {
                accountList.get(option).withdrawCredit(valueWithdraw);
            }
        }
    }

    public void deposit() {
        verify();
        int option = optionInt();
        System.out.println("Qual valor você deseja depositar?");
        double valueDeposit = valueDouble();
        accountList.get(option).deposit(valueDeposit);
    }

    private void verify() {
        if (clientList.isEmpty()) {
            System.out.println(IAccount.YELLOW + "Não existe nenhum cliente cadastrado, vamos cadastrar um." + IAccount.RESET);
            creatClient();
        }
        if (accountList.isEmpty()) {
            System.out.println(IAccount.YELLOW + "Não existe nenhuma conta criada, vamos criar uma." + IAccount.RESET);
            createAccount();
        }
        System.out.println("Selecione a conta");
        accountList
                .forEach((i, a) -> System.out.println(i + ". " + a.getClient().getName() + " " + a.getNumberAccount()));
    }

    int optionInt() {
        int option = 0;
        try {
            option = scanner.nextInt();
            scanner.nextLine();
        } catch (Exception e) {
            System.out.println((IAccount.RED + "Opção inválida, tente novamente, digite somente número." + IAccount.RESET));
            scanner.nextLine();
            option = optionInt();
        }

        return option;

    }

    private double valueDouble() {
        double option = 0.0;
        try {
            option = scanner.nextDouble();
            scanner.nextLine();
        } catch (Exception e) {
            System.out.println(IAccount.RED + "Opção inválida, tente novamente, digite somente número." + IAccount.RESET);
            scanner.nextLine();
            option = optionInt();
        }

        return option;

    }

}
