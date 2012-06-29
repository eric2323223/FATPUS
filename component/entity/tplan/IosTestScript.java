package component.entity.tplan;

public abstract class IosTestScript extends SuadeTestScript {

	@Override
	public abstract void doTest() throws Exception ;
	
	protected int getClickHoldTime(){
		return 100;
	}

}
