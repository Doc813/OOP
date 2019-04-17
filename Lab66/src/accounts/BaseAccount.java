package accounts;

import client.Client;

import java.math.BigDecimal;
import java.util.Date;

public abstract class BaseAccount implements Account {

    private BigDecimal amount;

    protected BaseAccount(){
        amount = new BigDecimal(0);
    }

    public BigDecimal getAmount() {
        return amount;
    }

    public final void supplement(Date date, BigDecimal amount) throws Exception {
        if(validateToRefill(date, amount)){
            this.amount = this.amount.add(amount);
        }
        else throw new Exception("you have no permissions to supplement account");
    }

    public final void withdraw(Date date, BigDecimal amount) throws Exception {
        if(validateToWithDraw(date, amount)){
            this.amount = this.amount.subtract(amount.add(getCommission()));
        }
        else throw new Exception("you have no permissions to withdraw from account");
    }

    public final void transfer(Date date, Account account, BigDecimal amount) throws Exception {
        if(validateToRefill(date, amount) && account.validateToRefill(date, amount)){
            withdraw(date, amount);
            account.supplement(date, amount);
        }
        else throw new Exception("you have no permissions to transfer money");
    }

    public final void payPercents(){
        amount = amount.add(amount.multiply(new BigDecimal(getPercents())).divide(new BigDecimal(100)));
    }

    public final void substractCommissions() {
        amount = amount.subtract(getCommission());
    }

    protected abstract BigDecimal getCommission();

    protected abstract Client getClient();

    protected abstract Float getPercents();

    public abstract boolean validateToRefill(Date date, BigDecimal amount);

    public abstract boolean validateToWithDraw(Date date, BigDecimal amount);
}
