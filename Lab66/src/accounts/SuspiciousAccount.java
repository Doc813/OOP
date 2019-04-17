package accounts;

import client.Client;

import java.math.BigDecimal;
import java.util.Date;

public class SuspiciousAccount implements Account{

    private BigDecimal amountRestriction;
    private BaseAccount deligatedAccount;

    public SuspiciousAccount(BaseAccount account, BigDecimal amountRestriction) {
        this.deligatedAccount = account;
        this.amountRestriction = amountRestriction;
    }

    protected Client getClient() {
        return deligatedAccount.getClient();
    }

    protected Float getPercents() {
        return deligatedAccount.getPercents();
    }

    private boolean validate(Date date, BigDecimal amount) {
        return (amountRestriction.compareTo(amount) >=0);
    }

    public void supplement(Date date, BigDecimal amount) throws Exception {
        if(validate(date, amount)) {
            deligatedAccount.supplement(date, amount);
        }
        else throw new IllegalAccessException("you have no permission to supplememnt deligatedAccount");
    }

    public void withdraw(Date date, BigDecimal amount) throws Exception {
        if(validate(date, amount)){
            deligatedAccount.withdraw(date, amount);
        }
        else throw new IllegalAccessException("you have no permission to withdraw from deligatedAccount");
    }

    public void transfer(Date date, Account account, BigDecimal amount) throws Exception {
        if(validate(date, amount)) {
            deligatedAccount.transfer(date, account, amount);
        }
        else throw new IllegalAccessException("you have no permission to transfer from deligatedAccount");
    }

    public BigDecimal getAmount() {
        return deligatedAccount.getAmount();
    }

    public BigDecimal getCommissions() {
        return deligatedAccount.getCommissions();
    }

    public boolean validateToRefill(Date date, BigDecimal amount) throws IllegalAccessException {
        if(amountRestriction.compareTo(amount) >= 0){
            return deligatedAccount.validateToRefill(date, amount);
        }
        else throw new IllegalAccessException("you have no permissions to transfer");
    }

    public void payPercents() {
        deligatedAccount.payPercents();
    }

    public void substractCommissions() {
        deligatedAccount.substractCommissions();
    }
}
