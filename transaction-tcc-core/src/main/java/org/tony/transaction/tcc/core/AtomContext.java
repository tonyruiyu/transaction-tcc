package org.tony.transaction.tcc.core;

public class AtomContext {

    private AtomTransaction atomTransaction;

    private Atom            atom;

    private Object[]        args;

    private String[]        paramTypes;

    private Composite       composite;

    public AtomTransaction getAtomTransaction() {
        return atomTransaction;
    }

    public void setAtomTransaction(AtomTransaction atomTransaction) {
        this.atomTransaction = atomTransaction;
    }

    public Atom getAtom() {
        return atom;
    }

    public void setAtom(Atom atom) {
        this.atom = atom;
        TransactionContext.setAtom(atom);
    }

    public Object[] getArgs() {
        return args;
    }

    public void setArgs(Object[] args) {
        this.args = args;
    }

    public String[] getParamTypes() {
        return paramTypes;
    }

    public void setParamTypes(String[] paramTypes) {
        this.paramTypes = paramTypes;
    }

    public Composite getComposite() {
        return composite;
    }

    public void setComposite(Composite composite) {
        this.composite = composite;
    }

}
