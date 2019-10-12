# transaction-tcc

## 介绍
transaction-tcc提供了一个基于TCC模型的分布式事务的简单处理工具。

## 使用方式 (try,confirm,cancel须幂等，参数一致。)

### JAVA调用方式

开启一个TCC事务
```
public class BusinessServiceImpl {

	@Resource ATransactionApi aTransactionApi;
	@Resource BTransactionApi bTransactionApi;

	@TccTransaction("yourCompositeCode")
	public void runBiz(Object... obj) {
		Object yourParam1 = ...;
		Object yourParam2 = ...;
		...
		
		aTransactionApi.tryMethod(yourParam1,yourParam2);
		bTransactionApi.tryMethod(yourParam3,yourParam4);
    }
}
```

服务调用者 需要一个本地Service ATransactionApi 封装远端服务 ATransactionPRCApi 
```
public class ATransactionApi {

	@Resource ATransactionPRCApi aTransactionPRCApi;

	@TryMethod(atomCode="yourAtomCodeA")
	public Object myMethodTry(Object yourParam1, Object yourParam2){
	
		Object obj =  aTransactionPRCApi.myMethodTry(yourParam1,yourParam2);
		
		//验证接口返回结果如果确定为业务异常或者其他需要回滚的异常  抛出UndoException会立即调用回滚方法
		//其他非UndoException 会调用query方法
		if(isUndo(obj)) {
			throw new UndoException("error message");
		}
		
		//获取当前原子事务id方式
		Atom atom = TransactionContext.getAtom(atomCode);
		String atomId = atom.getAtomId();

		//... your biz code
	}
	
	@ConfirmMethod
	public Object confirm(Object yourParam1, Object yourParam2) {
    		//... your biz code 
    		aTransactionPRCApi.confirm(yourParam1,yourParam2);
    		//...
    }
    
	@CancelMethod
	public Object cancel(Object... args) {
		//... your biz code 
		aTransactionPRCApi.cancel(yourParam1,yourParam2);
		//...
    }
    
    @QueryMethod
    public AtomStatus query(String atomId) {
    		//为了恢复效率 在业务端侵入一个方法，使用原子单id位参数 String类型，返回AtomStatus类型
    		//后续版本计划删除掉query方法
        return atomStatus;
    }
}
```
一个本地Service ATransactionApi 封装远端服务 ATransactionPRCApi 

```
public class BTransactionApi {

	@Resource BTransactionPRCApi bTransactionPRCApi;

	@TryMethod(atomCode="yourAtomCodeB")
	public Object myMethodTry(Object yourParam1, Object yourParam2){
	
		Object obj =  bTransactionPRCApi.myMethodTry(yourParam1,yourParam2);

		//... your biz code
	}
	
	@ConfirmMethod
    public Object confirm(Object yourParam1, Object yourParam2) {
    		//... your biz code 
    		bTransactionPRCApi.confirm(yourParam1,yourParam2);
    		//...
    }
    
	@CancelMethod
    public Object cancel(Object... args) {
    		//... your biz code 
    		bTransactionPRCApi.cancel(yourParam1,yourParam2);
    		//...
    }
    
    @QueryMethod
    public AtomStatus query(String atomId) {
    		//为了恢复效率 在业务端侵入一个方法，使用原子单id位参数 String类型，返回AtomStatus类型
    		//后续版本计划删除掉query方法
        return atomStatus;
    }
}
```


### Spring配置说明
如果需要开启注解
```
	<aop:aspectj-autoproxy proxy-target-class="true"/>
	    
	<bean class="org.tony.transaction.tcc.spring.BeginTransactiongAspect" />
	<bean class="org.tony.transaction.tcc.spring.AtomTransactionAspect" />
```

配置TCC事务管理器
```
<!-- 事务管理器 -->
<bean id="tccTransactionManager" class="org.tony.transaction.tcc.core.TccTransactionManager">
	<property name="persistenceManager" ref="defaultPersistenceManager" />
</bean>

<!-- 持久化管理器 -->
<bean id="defaultPersistenceManager" class="org.tony.transaction.tcc.mysql.DefaultPersistenceManager">
    <property name="atomMapper" ref="atomMapper"></property>
    <property name="compositeMapper" ref="compositeMapper"></property>
    <property name="defineMapper" ref="defineMapper"></property>
</bean>

<!-- 默认mybatis实现相关配置 -->
<bean id="tccFactory" class="org.mybatis.spring.SqlSessionFactoryBean">
	<property name="dataSource" ref="tccDataSource" />
	<property name="configLocation" value="classpath:mybatis/mybatis-tcc-configuration.xml" />
	<property name="typeAliasesPackage" value="org.tony.transaction.tcc.mysql.po" />
</bean>

<bean id="tccMapper" class="org.mybatis.spring.mapper.MapperScannerConfigurer">
	<property name="basePackage" value="org.tony.transaction.tcc.mysql.mapper" />
	<property name="sqlSessionFactoryBeanName" value="tccFactory" />
</bean>


<!-- mysql数据源配置 -->
<bean id="tccDataSource" class="org.apache.commons.dbcp2.BasicDataSource">
	<property name="driverClassName" value="${mysql.tcc.driverClassName}" />
	<property name="url" value="${mysql.tcc.url}" />
	<property name="username" value="${mysql.tcc.user}" />
	<property name="password" value="${mysql.tcc.password}" />
	<property name="initialSize" value="${mysql.tcc.initialSize}" />
	<property name="minIdle" value="${mysql.tcc.minIdle}" />
	<property name="maxIdle" value="${mysql.tcc.maxIdle}" />
	<property name="maxTotal" value="${mysql.tcc.maxTotal}" />
	<property name="maxWaitMillis" value="${mysql.tcc.maxWaitMillis}" />
	<property name="timeBetweenEvictionRunsMillis" value="${mysql.tcc.timeBetweenEvictionRunsMillis}" />
	<property name="minEvictableIdleTimeMillis" value="${mysql.tcc.minEvictableIdleTimeMillis}" />
	<property name="testWhileIdle" value="${mysql.tcc.testWhileIdle}" />
	<property name="testOnBorrow" value="${mysql.tcc.testOnBorrow}" />
	<property name="testOnReturn" value="${mysql.tcc.testOnReturn}" />
	<property name="removeAbandonedOnMaintenance" value="${mysql.tcc.removeAbandonedOnMaintenance}" />
	<property name="removeAbandonedOnBorrow" value="${mysql.tcc.removeAbandonedOnBorrow}" />
	<property name="removeAbandonedTimeout" value="${mysql.tcc.removeAbandonedTimeout}" />
	<property name="logAbandoned" value="${mysql.tcc.logAbandoned}" />
	<property name="validationQuery" value="select 1" />
</bean>
```







