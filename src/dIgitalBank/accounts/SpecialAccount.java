package digitalBank.accounts;

import digitalBank.Client;

public class SpecialAccount extends Account{

    private double limit = 5000.0;

    public SpecialAccount(Client client) {
        super(client);
    }

    @Override
    public void withdraw(double value) {
        if (isWithdraw(value)) {
            System.out.printf("Valor %.2f sacado com sucesso!", value);
        } else {
            System.out.printf("Saldo insuficiente para realizar o saque de %.2f", value);
        }

        super.viewBalance();
    }

    private boolean isWithdraw(double value) {
        if (super.balance >= (value + limit) && value > 0) {
            super.balance -= value;
            return true;
        } else {
            return false;
        }
    }
}
