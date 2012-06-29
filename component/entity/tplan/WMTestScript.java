package component.entity.tplan;

import java.awt.Point;
import java.io.IOException;

public abstract class WMTestScript extends SuadeTestScript {

	public abstract void doTest() throws Exception;
	
	public void start(String picFile) throws IOException{
//		clickOn("start_button_WM");
//		clickOn("picFile", 5,5 );
	}
	
	protected int getClickHoldTime(){
		return 250;
	}

}
