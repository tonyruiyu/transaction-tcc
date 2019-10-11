package org.tony.transaction.tcc.core;

public interface AtomTransaction  {
    
    public Object tryMethod(Object ... args) ;
    
    public Object confirm(Object ... args);
    
    public Object cancel(Object ... args);

    public AtomStatus query(String atomId);
    
    public String getAtomCode();
}
