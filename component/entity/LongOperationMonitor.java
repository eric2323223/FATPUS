package component.entity;

import com.rational.test.ft.*;
import com.rational.test.ft.object.interfaces.*;
import com.rational.test.ft.object.interfaces.SAP.*;
import com.rational.test.ft.object.interfaces.WPF.*;
import com.rational.test.ft.object.interfaces.dojo.*;
import com.rational.test.ft.object.interfaces.siebel.*;
import com.rational.test.ft.object.interfaces.flex.*;
import com.rational.test.ft.script.*;
import com.rational.test.ft.value.*;
import com.rational.test.ft.vp.*;
import com.sybase.automation.framework.common.DialogCloser;
import com.sybase.automation.framework.widget.DOF;

/**
 * Description : Functional Test Script
 * 
 * @author eric
 */
public class LongOperationMonitor extends RationalTestScript {
	/**
	 * Script Name : <b>LongOperationMonitor</b> Generated : <b>Jul 30, 2010
	 * 1:12:31 PM</b> Description : Functional Test Script Original Host : WinNT
	 * Version 5.1 Build 2600 (S)
	 * 
	 * @since 2010/07/30
	 * @author eric
	 */
	private static final int MAX_RETRY = 180;
	
	public void testMain(Object[] args) {
		LongOperationMonitor.waitForDialog("Migrating Results");
	}
	
	public static void waitForDialog(String name, int max){
		int retry = 0;
		try{
			while (retry < max) {
				retry++;
//				System.out.println("retry: "+retry);
				TestObject dialog = DOF.getDialog(name);
				if (dialog == null) {
					sleep(1);
				} else {
					sleep(1);
					break;
				}
			}
		}catch(Exception  e){
			System.out.println("There is a exception in waitForDialog");
			return;
		}
		
	}

	public static void waitForDialog(String name) {
		waitForDialog(name, MAX_RETRY);
	}
	
	public static String waitForDialog() {
		int retry = 0;
		while (true) {
//				System.out.println("retry: "+retry);
			String title = getScreen().getActiveWindow().getText();
			if(!title.startsWith("Mobile Development")){
				return title;
			}
			sleep(1);
		}
	}

	public static void waitForDialogToVanish(String name) {
		sleep(1);
		TestObject dialog;
		int retry = 0;
		while ((dialog = DOF.getDialog(name)) != null &&
				retry < MAX_RETRY) {
			retry++;
			sleep(1);
		}
		sleep(1);
	}

	public static void waitForProgressBarVanish(TestObject parent) {
		if (parent != null) {
			sleep(1);
			TestObject progressBar = DOF.getProgressBar(parent);
			int retry = 0;
			while (progressBar != null && retry < MAX_RETRY) {
				retry++;
				sleep(1);
				progressBar = DOF.getProgressBar(parent);
			}
		}
		
	}

	public static void waitForDialogToVanish(String name,	DialogCloser dialogCloser) {
		sleep(3);
//		TestObject dialog;
		int retry = 0;
		while (true){
			if(DOF.getDialog(name) == null || retry >= MAX_RETRY) {
				break;
			}else{
				dialogCloser.closeNamedDialogs();
			}
			retry++;
			sleep(1);
		}
		sleep(2);
		
	}
}
