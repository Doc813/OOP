
package accounts;

import client.Client;

import java.math.BigDecimal;
import java.util.Date;

public class CreditAccount extends BaseAccount {

    private BigDecimal commissions;
    private Client client;

    public CreditAccount(Client client, BigDecimal commission) {
        super();
        this.commissions = commission;
        this.client = client;
    }

    protected BigDecimal getCommission() {
        return commissions;
    }

    protected Client getClient() {
        return client;
    }

    protected Float getPercents() {
        return (float) 0;
    }

    public BigDecimal getCommissions() {
        return commissions;
    }

    public boolean validateToRefill(Date date, BigDecimal amount) {
        return true;
    }

    public boolean validateToWithDraw(Date date, BigDecimal amount) {
        return super.getAmount().compareTo(amount) >= 0;
    }
}
