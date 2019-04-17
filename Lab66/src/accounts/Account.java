package accounts;

import java.math.BigDecimal;
import java.util.Date;

public interface Account {
    void supplement(Date date, BigDecimal amount) throws Exception;

    void withdraw(Date date, BigDecimal amount) throws Exception;

    void transfer(Date date, Account baseAccount, BigDecimal amount) throws Exception;

    BigDecimal getAmount();

    BigDecimal getCommissions();

    boolean validateToRefill(Date date, BigDecimal amount) throws IllegalAccessException;

    void payPercents();

    void substractCommissions();
}
