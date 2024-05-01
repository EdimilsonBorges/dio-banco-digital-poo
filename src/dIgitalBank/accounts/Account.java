package dIgitalBank.accounts;

import dIgitalBank.Client;

public abstract class Account implements IAccount {
    protected Client client;
    protected static int number;
    protected double balance = 0.0;

    public Account(Client client) {
        this.client = client;
        Account.number++;
    }

    @Override
    public boolean deposit(double value) {
        
        if(value > 0){
            balance += value;
            System.out.printf("\nValor de %.2f depositado com sucesso!", value);
            viewBalance();
            return true;
        }else{
            System.out.printf("\nErro! Não foi possível depositar esse valor %.2f!", value);
            viewBalance();
            return false;
        }
        
    }

    @Override
    public boolean withdraw(double value) {
        if (balance >= value && value > 0) {
            balance -= value;
            System.out.printf("\nValor de %.2f sacado com sucesso!", value);
            viewBalance();
            return true;
        } else {
            System.out.printf("\nSaldo insuficiente para realizar o saque de %.2f", value);
            viewBalance();
            return false;
        }
    }

    @Override
    public boolean transfer(double value, Account account) {

        if(account.withdraw(value)){
            deposit(value);
            System.out.printf("\nTransferência de %s para %s de %.2f realizado com sucesso!", client.getName(), account.client.getName(), value);
            viewBalance();
            return true;
        }else{
            System.out.printf("\nErro, não foi possível transferir %.2f", value);
            viewBalance();
            return false;
        }
        
    }

    private void viewBalance(){
        System.out.printf("\nSaldo atual de %s é de %.2f!", this.client.getName(), this.balance);
    }

    public Client getClient() {
        return client;
    }


    public int getNumber() {
        return number;
    }

}
