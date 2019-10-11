package org.tony.transaction.tcc.test.stock;

import org.tony.transaction.tcc.core.Atom;
import org.tony.transaction.tcc.core.AtomStatus;
import org.tony.transaction.tcc.core.TransactionContext;
import org.tony.transaction.tcc.core.UndoException;
import org.tony.transaction.tcc.test.QueryAtomStatysMock;
import org.tony.transaction.tcc.test.testcase.StockB2cTransactionBase;

public abstract class MockBase {

    protected boolean    tryError     = false;

    protected boolean    isUndo       = true;

    protected boolean    confirmError = false;

    protected boolean    cancelError  = false;

    protected AtomStatus queryResult  = AtomStatus.CFM;

    protected void saveStatus(String atomCode) {
        if (TransactionContext.getCompositeTransaction() != null) {
            StockB2cTransactionBase.ctx.set(TransactionContext.getCompositeTransaction());
        }
        Atom atom = TransactionContext.getAtom(atomCode);
        if (tryError && !isUndo) {
            QueryAtomStatysMock.setStatus(atom.getAtomId(), queryResult);
        }
    }

    protected void tryMockException() {
        if (tryError) {
            if (isUndo) {
                throw new UndoException("mock");
            }
            throw new RuntimeException("mock");
        }
    }

    public boolean isTryError() {
        return tryError;
    }

    public void setTryError(boolean tryError) {
        this.tryError = tryError;
    }

    public boolean isUndo() {
        return isUndo;
    }

    public void setUndo(boolean isUndo) {
        this.isUndo = isUndo;
    }

    public boolean isConfirmError() {
        return confirmError;
    }

    public void setConfirmError(boolean confirmError) {
        this.confirmError = confirmError;
    }

    public boolean isCancelError() {
        return cancelError;
    }

    public void setCancelError(boolean cancelError) {
        this.cancelError = cancelError;
    }

    public AtomStatus getQueryResult() {
        return queryResult;
    }

    public void setQueryResult(AtomStatus queryResult) {
        this.queryResult = queryResult;
    }
}
