package com.sybase.automation.framework.common;

import java.awt.AWTException;
import java.awt.Robot;
import java.awt.event.KeyEvent;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import com.rational.test.ft.script.RationalTestScript;

public class AppSwitcher {

	public static void switchTo(String name){
		while(true){
			String windowName = RationalTestScript.getScreen().getActiveWindow().getText();
//			System.out.println(windowName);
			if(windowName.equals(name)){
				break;
			}else{
				switchFocus((int)(Math.random()*10));
				RationalTestScript.sleep(1);
			}
		}
	}
	
	public static void switchTo(Pattern p){
		while(true){
			String windowName = RationalTestScript.getScreen().getActiveWindow().getText();
//			System.out.println(windowName);
			Matcher m = p.matcher(windowName);
			if(m.find()){
				break;
			}else{
				switchFocus((int)(Math.random()*10));
				RationalTestScript.sleep(1);
			}
		}
	}
	
	private static void switchFocus(int times) {
		  try {
		    Robot r = new Robot();
		    r.keyPress(KeyEvent.VK_ALT);
		    for(int i=0;i<times; i++){
			    r.keyPress(KeyEvent.VK_TAB);
			    r.keyRelease(KeyEvent.VK_TAB);
		    }
		    r.keyRelease(KeyEvent.VK_ALT);
		  } catch(AWTException e) {
		    
		  }
		
	}
}
