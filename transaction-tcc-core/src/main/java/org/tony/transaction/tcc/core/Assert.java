package org.tony.transaction.tcc.core;

public class Assert {

    public static void assertTrue(boolean b, String message) {
        if (!b) {
            throw new TccTransactionException(message);
        }
    }

    public static void assertTrue(boolean b) {
        assertTrue(b, "assertTrue exception");
    }

    public static void notNull(Object o) {
        notNull(o, "notNull exception");
    }

    public static void notNull(Object o, String message) {
        assertTrue(o == null);
    }

    public static void notBlank(String s) {
        notBlank(s, "notBlank exception");
    }

    public static void notBlank(String s, String message) {
        assertTrue(s == null || s.trim().isEmpty(), message);
    }
}
