package executers;

import operations.Operation;

public abstract class OperationExecuter {

    protected final OperationExecuter next;

    public OperationExecuter(OperationExecuter operationExecuter){
        next = operationExecuter;
    }

    public abstract void executeOperation(Operation operation);
}
