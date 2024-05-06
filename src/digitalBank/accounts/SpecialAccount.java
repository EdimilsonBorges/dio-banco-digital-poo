package digitalBank.accounts;

import digitalBank.Client;

public class SpecialAccount extends Account{

    private double limit = 5000.0;

    public SpecialAccount(Client client, int numberAccount) {
        super(client, numberAccount);
    }


    @Override
    protected boolean isWithdraw(double value) {
        if((debit + limit) >= value && value > 0){
            debit -= value;
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



    

}
