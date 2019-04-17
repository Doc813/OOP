
package accounts;

import client.Client;

import java.math.BigDecimal;
import java.util.Date;

public class CurrentAccount extends BaseAccount {

    private float percents;
    private Client client;

    public CurrentAccount(Client client, Float percents) {
        super();
        this.percents = percents;
        this.client = client;
    }

    protected BigDecimal getCommission() {
        return new BigDecimal(0);
    }

    protected Client getClient() {
        return client;
    }

    protected Float getPercents() {
        return percents;
    }

    public BigDecimal getCommissions() {
        return new BigDecimal(0);
    }

    public boolean validateToRefill(Date date, BigDecimal money) {
        return true;
    }

    public boolean validateToWithDraw(Date date, BigDecimal money) {
        return super.getAmount().compareTo(money) >= 0;
    }
}