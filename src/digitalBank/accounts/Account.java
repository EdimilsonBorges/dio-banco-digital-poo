package digitalBank.accounts;

import digitalBank.Client;

public abstract class Account implements IAccount {
    protected Client client;
    private int numberAccount;

    protected double debit = 0.0;

    public Account(Client client, int numberAccount) {
        this.client = client;
        this.numberAccount = numberAccount;
    }

    @Override
    public void deposit(double value) {

        if (isDeposit(value)) {
            System.out.printf(GREEN + "Valor de %.2f depositado com sucesso!\n" + RESET, value);
        } else {
            System.out.printf(BRIGHT_RED + "Erro! Não foi possível depositar esse valor %.2f!\n" + RESET, value);
        }

        viewBalance();

    }

    @Override
    public void withdrawDebit(double value) {

        if (isWithdraw(value)) {
            System.out.printf(BRIGHT_YELLOW  + "Valor de %.2f sacado com sucesso!\n" + RESET, value);
        } else {
            System.out.printf(RED + "Saldo insuficiente para realizar o saque de %.2f\n" + RESET, value);
        }
        viewBalance();
    }

    public void withdrawCredit(double value) {

        if (!(this instanceof CurrentAccount)) {
            System.out.printf(BRIGHT_RED + "Erro! Não é possível realizar o saque na função de crédito nesse tipo de conta\n" + RESET);
        }

        viewBalance();
    }

    @Override
    public void transfer(double value, Account account) {
        if (isTransfer(value, account)) {
            System.out.printf(BRIGHT_GREEN + "Transferência de %s %d para %s %d de %.2f realizado com sucesso!\n" + RESET, client.getName(), getNumberAccount(),
                    account.client.getName(), account.getNumberAccount(), value);
        } else {
            System.out.printf(BRIGHT_RED + "Erro, não foi possível transferir %.2f para %s\n" + RESET, value, account.client.getName());
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
        System.out.printf(BLUE + "Saldo atual de %s %d é de %.2f!\n" + RESET, this.client.getName(), getNumberAccount(), this.debit);
    }

    public Client getClient() {
        return client;
    }

    public int getNumberAccount() {
        return numberAccount;
    }

}
