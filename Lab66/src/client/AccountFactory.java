package client;

import accounts.*;
import java.math.BigDecimal;
import java.util.Date;

public class AccountFactory {

    public static Account createAccount(Client client, Integer durationAsDays, BigDecimal commission, Float percents){
        BaseAccount baseAccount = null;
        if(percents != null && durationAsDays == null && commission == null){
            baseAccount = new CurrentAccount(client, percents);
        }
        if(commission != null && durationAsDays == null && percents == null){
            baseAccount = new CreditAccount(client, commission);
        }
        if(durationAsDays != null && commission == null && percents != null){
            baseAccount = new DepositAccount(client, new Date(), durationAsDays, percents);
        }
        if(baseAccount == null) throw new IllegalArgumentException("Incorrect input");
        return wrapToSuspiciosAccount(client, baseAccount);
    }

    private static Account wrapToSuspiciosAccount(Client client, BaseAccount account){
        if(client.adress() == null || client.passport() == null){
            return new SuspiciousAccount(account, new BigDecimal(5000));
        }
        return account;
    }
}
