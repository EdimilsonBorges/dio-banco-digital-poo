package digitalBank;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Scanner;

import digitalBank.accounts.Account;
import digitalBank.accounts.CurrentAccount;
import digitalBank.accounts.SavingsAccount;
import digitalBank.accounts.SpecialAccount;

public class Bank {

    private List<Client> clientList = new ArrayList<>();
    private Map<Integer, Account> accountList = new HashMap<>();
    private Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) throws Exception {
        Bank bank = new Bank();
        boolean exit = false;
        while (!exit) {
            bank.menu();
            int option = bank.scanner.nextInt();
            bank.scanner.nextLine();
            switch (option) {
                case 1:
                    bank.creatClient();
                    break;
                case 2:
                    bank.createAccount();
                    break;
                case 3:
                    bank.deposit();
                    break;
                case 4:
                    bank.withdraw();
                    break;
                case 5:
                    bank.transfer();
                    break;
                case 6:
                    exit = true;
                    break;
                default:
                    System.out.println("Opção Inválida.");
                    break;
            }

        }
    }

    private void transfer() {
        verify();
        System.out.println("De qual conta você que transferir?");
        accountList.forEach((i, a) -> System.out.println(i + ". " + a.getClient().getName() + " " + a.getNumberAccount()));
        int accountFrom = scanner.nextInt();

        System.out.println("Para qual conta você que transferir?");
        accountList.forEach((i, a) -> System.out.println(i + ". " + a.getClient().getName() + " " +  a.getNumberAccount()));
        
        int accountTo = scanner.nextInt();

        System.out.println("Qual valor você deseja transferir?");
        double value = scanner.nextDouble();

        accountList.get(accountFrom).transfer(value, accountList.get(accountTo));
    }

    private void withdraw() {
        verify();
        int option = scanner.nextInt();
        scanner.nextLine();
        System.out.println("Qual valor você deseja sacar?");
        double valueWithdraw = scanner.nextDouble();
        scanner.nextLine();
        if (!(accountList.get(option) instanceof CurrentAccount)) {
            accountList.get(option).withdrawDebit(valueWithdraw);
        } else {
            System.out.println("Selecione 1 para débito ou 2 para crédito.");
            int option3 = scanner.nextInt();
            if (option3 == 1) {
                accountList.get(option).withdrawDebit(valueWithdraw);
            } else {
                accountList.get(option).withdrawCredit(valueWithdraw);
            }
        }
    }

    private void deposit() {
        verify();
        int option = scanner.nextInt();
        scanner.nextLine();
        System.out.println("Qual valor você deseja depositar?");
        double valueDeposit = scanner.nextDouble();
        scanner.nextLine();
        accountList.get(option).deposit(valueDeposit);
    }

    private void verify() {
        if (clientList.isEmpty()) {
            System.out.println("Não existe nenhum cliente cadastrado, vamos cadastrar um.");
            creatClient();
        }
        if (accountList.isEmpty()) {
            System.out.println("Não existe nenhuma conta criada, vamos criar uma.");
            createAccount();
        }
        System.out.println("Selecione a conta");
        accountList.forEach((i, a) -> System.out.println(i + ". " + a.getClient().getName() + " " + a.getNumberAccount()));
    }

    private void menu() {
        String menu = """
                1. Cadastrar cliente
                2. Cadastrar conta
                3. Depositar
                4. Sacar
                5. Transferir
                6. Sair

                Selecione uma opção.""";
        System.out.println(menu);
    }

    private void createAccount() {

        System.out.println("Selecione o cliente para abrir a conta");
        for (int i = 1; i <= clientList.size(); i++) {
            int select = i - 1;
            System.out.println(i + ". " + clientList.get(select).getName());
        }

        int clientSelected = scanner.nextInt();
        clientSelected--;
        scanner.nextLine();
        System.out.println("1. Conta Poupança");
        System.out.println("2. Conta Corrente");
        System.out.println("3. Conta Especial");
        System.out.println("Selecione o tipo de conta que você quer abrir no banco");
        int typeAccont = scanner.nextInt();
        scanner.nextLine();
        Account account = null;

        if (typeAccont == 1) {
            account = new SavingsAccount(clientList.get(clientSelected), accountList.size()+1);
        } else if (typeAccont == 2) {
            account = new CurrentAccount(clientList.get(clientSelected), accountList.size()+1, 500.0);
        } else if (typeAccont == 3) {
            account = new SpecialAccount(clientList.get(clientSelected), accountList.size()+1);
        } else {
            System.out.println("Opção Inválida");
        }

        if (account != null) {
            accountList.put(account.getNumberAccount(), account);
            System.out.println("Conta criada com sucesso!");
        } else {
            createAccount();
            return;
        }
    }

    private void creatClient() {
        Client client = null;
        System.out.println("Digite o CPF do cliente");
        String cpf = scanner.nextLine();
        if (cpf.length() != 11) {
            System.out.println("CPF inválido, seu CPF precisa conter 11 caractere");
            creatClient();
            return;
        }
        System.out.println("Digite o nome do cliente");
        String name = scanner.nextLine();
        System.out.println("Digite a idade do cliente");
        int age = scanner.nextInt();
        scanner.nextLine();

        client = new Client(name, cpf, age);
        System.out.println("Cliente cadastrado com sucesso!");
        clientList.add(client);
    }
}
