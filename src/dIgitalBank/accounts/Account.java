package digitalBank.accounts;

import digitalBank.Client;

public abstract class Account implements IAccount {
    protected Client client;
    protected static int number;
    protected double debit = 0.0;

    public Account(Client client) {
        this.client = client;
        Account.number++;
    }

    @Override
    public void deposit(double value) {

        if (isDeposit(value)) {
            System.out.printf(GREEN + "\nValor de %.2f depositado com sucesso!" + RESET, value);
        } else {
            System.out.printf(BRIGHT_RED + "\nErro! Não foi possível depositar esse valor %.2f!" + RESET, value);
        }

        viewBalance();

    }

    @Override
    public void withdrawDebit(double value) {

        if (isWithdraw(value)) {
            System.out.printf(BRIGHT_YELLOW  + "\nValor de %.2f sacado com sucesso!" + RESET, value);
        } else {
            System.out.printf(RED + "\nSaldo insuficiente para realizar o saque de %.2f" + RESET, value);
        }
        viewBalance();
    }

    public void withdrawCredit(double value) {

        if (!(this instanceof CurrentAccount)) {
            System.out.printf(BRIGHT_RED + "\nErro! Não é possível realizar o saque na função de crédito nesse tipo de conta" + RESET);
        }

        viewBalance();
    }

    @Override
    public void transfer(double value, Account account) {
        if (isTransfer(value, account)) {
            System.out.printf(BRIGHT_GREEN + "\nTransferência de %s para %s de %.2f realizado com sucesso!" + RESET, client.getName(),
                    account.client.getName(), value);
        } else {
            System.out.printf(BRIGHT_RED + "\nErro, não foi possível transferir %.2f para %s" + RESET, value, account.client.getName());
        }
        viewBalance();
    }

    protected boolean isDeposit(double value) {
        if (value > 0) {
            debit += value;
            return true;
        }
        return false;
    }

    protected boolean isWithdraw(double value) {
        if (debit >= value && value > 0) {
            debit -= value;
            return true;
        }
        return false;
    }

    protected boolean isTransfer(double value, Account account) {
        if (isWithdraw(value)) {
            account.isDeposit(value);
            return true;
        }
        return false;
    }

    protected void viewBalance() {
        System.out.printf(BLUE + "\nSaldo atual de %s é de %.2f!" + RESET, this.client.getName(), this.debit);
    }

    public Client getClient() {
        return client;
    }

    public int getNumber() {
        return number;
    }

}
