package executers;


import operations.Operation;
import operations.PayPercentOperation;

public class PayPercentsExecuter extends OperationExecuter {

    public PayPercentsExecuter(OperationExecuter operationExecuter) {
        super(operationExecuter);
    }

    public void executeOperation(Operation operation) {
        if(operation instanceof PayPercentOperation){
            operation.execute();
            return;
        }
        if(next != null) {
            next.executeOperation(operation);
        }else throw new IllegalArgumentException("can't execute operation");
    }
}
