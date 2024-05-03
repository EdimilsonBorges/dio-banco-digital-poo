package digitalBank.accounts;

import digitalBank.Client;

public abstract class Account implements IAccount {
    protected Client client;
    protected static int number;
    protected double balance = 0.0;

    public Account(Client client) {
        this.client = client;
        Account.number++;
    }

    @Override
    public void deposit(double value) {

      if(isDeposit(value)){
        System.out.printf("\nValor de %.2f depositado com sucesso!", value);
      }else{
        System.out.printf("\nErro! Não foi possível depositar esse valor %.2f!", value);
      }

      viewBalance();

    }

    @Override
    public void withdraw(double value) {
        if(isWithdraw(value)){
            System.out.printf("\nValor de %.2f sacado com sucesso!", value);
        }else{
            System.out.printf("\nSaldo insuficiente para realizar o saque de %.2f", value);
        }

        viewBalance();
    }

    @Override
    public void transfer(double value, Account account) {
        if(isTransfer(value, account)){
            System.out.printf("\nTransferência de %s para %s de %.2f realizado com sucesso!", client.getName(), account.client.getName(), value);
        }else{
            System.out.printf("\nErro, não foi possível transferir %.2f para %s", value, account.client.getName());
        }
    }

    private boolean isDeposit(double value){
        if (value > 0) {
            balance += value;
            return true;
        }
        return false;
    }

    private boolean isWithdraw(double value){
        if (balance >= value && value > 0) {
            balance -= value;
            return true;
        }
        return false;
    }

    private boolean isTransfer(double value, Account account){
        if (isWithdraw(value)) {
            isDeposit(value);
            return true;
        }
        return false;
    }

    protected void viewBalance() {
        System.out.printf("\nSaldo atual de %s é de %.2f!", this.client.getName(), this.balance);
    }

    public Client getClient() {
        return client;
    }

    public int getNumber() {
        return number;
    }

}
