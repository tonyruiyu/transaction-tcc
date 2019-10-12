# transaction-tcc

## 介绍
transaction-tcc提供了一个基于TCC模型的分布式事务的简单处理工具。

## 使用方式 (try,confirm,cancel须幂等，参数一致。):
开启一个TCC事务
```
public class BusinessServiceImpl {

	@Resource ATransactionApi aTransactionApi;
	@Resource BTransactionApi bTransactionApi;

 	@TccTransaction("yourCompositeCode")
    public void runBiz() {
        aTransactionApi.tryMethod(new Object[] {});
        bTransactionApi.tryMethod(buildArgs());
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


