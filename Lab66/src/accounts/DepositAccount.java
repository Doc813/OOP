package accounts;

import client.Client;

import java.math.BigDecimal;
import java.util.Date;

public class DepositAccount extends BaseAccount {

    private Date startDate;
    private long durationAsMillisec;
    private float percents;
    private Client client;

    public DepositAccount(Client client, Date startDate, Integer durationAsDays, Float percents) {
        super();
        this.startDate = startDate;
        this.durationAsMillisec = (long) durationAsDays * 86400000;
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
        return (date.getTime() - startDate.getTime() + durationAsMillisec >= 0 && super.getAmount().compareTo(money) >= 0);
    }
}
