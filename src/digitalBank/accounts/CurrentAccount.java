package digitalBank.accounts;

import digitalBank.Client;
import digitalBank.Color;

public class CurrentAccount extends Account{
    
    private double limitMax = 0.0;
    private double credit = 0.0;

    public CurrentAccount(Client client,  int numberAccount, double credit) {
        super(client, numberAccount);
        this.credit = credit;
        this.limitMax = credit;
    }

    

    @Override
    protected boolean isDeposit(double value) { 
        if(value > 0){
            if (limitMax > credit && (credit + value) <= limitMax) {
                credit += value;
            }else{
                double controllerLimit = limitMax - credit; 
                double controllerValue = value; 
                controllerValue -= controllerLimit;
                credit += controllerLimit;
                debit += controllerValue;
            }
            return true;
        }
        return false;
    }

    @Override
    protected boolean isTransfer(double value, Account account) {
        if (isWithdraw(value)) {
            account.isDeposit(value);
            return true;
        }
        return false;
    }
    
    @Override
    public void withdrawCredit(double value) {
        if(isWithdrawCredit(value)){
            System.out.printf(Color.YELLOW + "Valor de %.2f sacado dos créditos com sucesso!\n" + Color.RESET, value);
        } else {
            System.out.printf(Color.RED + "Saldo insuficiente para realizar o saque dos crédito de %.2f\n"  + Color.RESET, value);
        }

        viewBalance();
        
    }

    private boolean isWithdrawCredit(double value) {

            if(value > 0 && credit >= value) {
                credit -= value;
                return true;
            }

        return false;
    }

    @Override
    protected void viewBalance() {
        super.viewBalance();
        System.out.printf(Color.CYAN + "Saldo de crédito atual de %s é de %.2f!\n" + Color.RESET, this.client.getName(), this.credit);
    }

    public double getCredit() {
        return credit;
    }
    
}
