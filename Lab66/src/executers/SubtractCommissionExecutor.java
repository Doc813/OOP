package executers;

import operations.Operation;
import operations.SubstractCommissionOperation;

public class SubtractCommissionExecutor extends OperationExecuter {

    public SubtractCommissionExecutor(OperationExecuter operationExecuter) {
        super(operationExecuter);
    }

    public void executeOperation(Operation operation) {
        if(operation instanceof SubstractCommissionOperation){
            operation.execute();
            return;
        }
        if(next != null) {
            next.executeOperation(operation);
        }else throw new IllegalArgumentException("can't execute operation");
    }
}
