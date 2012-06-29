package component.entity.tplan;

import java.awt.Rectangle;

import com.rational.test.ft.script.RationalTestScript;
import com.sybase.automation.framework.common.AppSwitcher;

public class WindowSpy extends RationalTestScript {
	public static Rectangle getWindowRectangle(String title){
		AppSwitcher.switchTo(title);
		return getScreen().getActiveWindow().getScreenRectangle();
	}
}
