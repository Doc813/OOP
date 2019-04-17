import accounts.Account;
import client.AccountFactory;
import client.Client;
import executers.OperationExecuter;
import executers.PayPercentsExecuter;
import executers.SubtractCommissionExecutor;
import operations.Operation;
import operations.SubstractCommissionOperation;

import java.math.BigDecimal;
import java.util.Date;



public class Main {
    public static void main(String[] argv) {

        Client client = new Client.Builder("Name", "lastName").adress("Pushkin").passport("13").build();
        Account account = AccountFactory.createAccount(client, 30, null, (float) 10.5);
        Account account1 = AccountFactory.createAccount(client, null, null, (float) 10);

        Client client1 = new Client.Builder("Name", "lastName").adress("Pushkin").build();
        Account account2 = AccountFactory.createAccount(client1, null, new BigDecimal(100), null);

        try {
            account.supplement(new Date(), new BigDecimal(50000));
            System.out.println(account.getAmount());
            account.withdraw(new Date(2019, 12, 1), new BigDecimal(1));
            account.transfer(new Date(), account1, new BigDecimal(5002));
            System.out.println(account.getAmount());
            System.out.println(account1.getAmount());
            account2.supplement(new Date(), new BigDecimal(1000));
            System.out.println(account2.getAmount());
            account2.withdraw(new Date(), new BigDecimal(100));
            System.out.println(account2.getAmount());

            OperationExecuter oe = new PayPercentsExecuter(new SubtractCommissionExecutor(null));
            Operation operation = new SubstractCommissionOperation(account2);
            oe.executeOperation(operation);

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
