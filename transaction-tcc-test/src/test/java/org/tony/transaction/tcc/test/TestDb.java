package org.tony.transaction.tcc.test;

import javax.annotation.Resource;

import org.junit.Test;
import org.tony.transaction.tcc.mysql.DefaultPersistenceManager;


public class TestDb extends TestBase{

    @Resource
    DefaultPersistenceManager defaultPersistenceManager;
    
    
    @Test 
    public void testDPM() {
        defaultPersistenceManager.insertAtom(null);
    }
    
    
}
