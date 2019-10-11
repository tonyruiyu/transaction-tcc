package org.tony.transaction.tcc.test.spring;

import javax.annotation.Resource;

import org.junit.Test;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.BeanFactory;
import org.springframework.beans.factory.NoSuchBeanDefinitionException;

import org.tony.transaction.tcc.test.TestBase;
import org.tony.transaction.tcc.test.stock.B2CTransactionApi;

public class SpringTest extends TestBase  {

    @Resource
    B2CTransactionApi     b2cTransactionApi;

    @Test
    public void testA() {
        b2cTransactionApi.tryMethod(new Object[] {});
    }

    
    
}
