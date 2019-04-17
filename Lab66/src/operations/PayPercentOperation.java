package operations;

import accounts.Account;

public class PayPercentOperation implements Operation {

    protected Account account;

    public PayPercentOperation(Account account){
        this.account = account;
    }

    public void execute() {
        account.payPercents();
    }
}
