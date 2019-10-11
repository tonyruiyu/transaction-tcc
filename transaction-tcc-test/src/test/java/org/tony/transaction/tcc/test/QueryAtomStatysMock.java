package org.tony.transaction.tcc.test;

import java.util.HashMap;
import java.util.Map;

import org.tony.transaction.tcc.core.AtomStatus;

public class QueryAtomStatysMock {

    private static Map<String, AtomStatus> atomStatusMock = new HashMap<>();

    public static void setStatus(String atomId, AtomStatus status) {
        if (atomStatusMock.containsKey(atomId)) {
            throw new RuntimeException("the Atom (atomid : " + atomId + ") status has bean set");
        }
        atomStatusMock.put(atomId, status);
    }

    public static AtomStatus getAtomStatus(String atomId) {
        return atomStatusMock.get(atomId);
    }

}
