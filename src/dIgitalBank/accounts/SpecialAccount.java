package dIgitalBank.accounts;

import dIgitalBank.Client;

public class SpecialAccount extends Account{

    private double limit = 5000.0;

    public SpecialAccount(Client client) {
        super(client);
    }

    @Override
    public boolean withdraw(double value) {
        if (super.balance >= (value + limit) && value > 0) {
            super.balance -= value;
            System.out.printf("Valor %.2f sacado com sucesso!", value);
            return true;
        } else {
            System.out.printf("Saldo insuficiente para realizar o saque de %.2f", value);
            return false;
        }
    }
}
