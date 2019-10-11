package org.tony.transaction.tcc.core;

import java.util.HashMap;
import java.util.Map;

public final class TransactionContext {

    private static ThreadLocal<Map<String, Atom>> cache = new ThreadLocal<Map<String, Atom>>();
    private static ThreadLocal<CompositeTransaction> ctx = new ThreadLocal<CompositeTransaction>();

    public static final void setAtom(Atom atom) {
        Map<String, Atom> map = cache.get();
        if (map == null) {
            map = new HashMap<String, Atom>();
            cache.set(map);
        }
        map.put(atom.getCode(), atom);
    }

    public static final Atom getAtom(String code) {
        Map<String, Atom> map = cache.get();
        if (map == null) {
            throw new TccTransactionException();
        }

        return map.get(code);
    }
    
    public static final void setCompositeTransaction(CompositeTransaction compositeTransaction) {
        ctx.set(compositeTransaction);
    }
    
    public static final CompositeTransaction getCompositeTransaction() {
        return ctx.get();
    }
    
    public static void clean() {
        cache.remove();
        cache.remove();
    }
}
