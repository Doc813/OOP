package operations;

import accounts.Account;

import java.util.Date;

public class SubstractCommissionOperation implements Operation{

    protected Account account;

    public SubstractCommissionOperation(Account account) throws Exception {
        this.account = account;
    }

    public void execute() {
        account.substractCommissions();
    }
}
