package com.sybase.automation.framework.common;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;

import com.rational.test.ft.object.interfaces.TestObject;
import com.rational.test.ft.object.interfaces.TopLevelTestObject;
import com.rational.test.ft.script.RationalTestScript;
import com.sybase.automation.framework.widget.DOF;

public class DialogCloser extends RationalTestScript{
	private static final int MAX_RETRY = 10;
	private final String LOGO = "[DialogCloser]";
	
	private HashMap<String, String> dialogs = new HashMap<String, String>();
	
	public static void main(String[] args){
		System.out.println("hello!");
		DialogCloser closer = new DialogCloser();
		closer.closeDialogs();
	}
	
	public void closeDialogs() {
		int retryCount = 1;
		while(!isWindowOnTop(DOF.getRoot())&&retryCount<MAX_RETRY){
			log("looking for other windows");
			while(getTopWindowCaptions().size()>1){
				List<String> captions = getTopWindowCaptions();
				for(String text:captions){
					try{
						sleep(1);
						log("highlighting "+text);
						if(DOF.getDialog(text)!=null){
							DOF.getDialog(text).activate();
							RationalTestScript.sleep(1);
							log("closing "+text);
							DOF.getDialog(text).inputKeys(SpecialKeys.ESCAPE);
							RationalTestScript.sleep(1);
						}
					}catch(com.rational.test.ft.WindowActivateFailedException e){
						log("failed in hightlighting "+text);
					}
				}
			}
		}
	}
	
	public DialogCloser dialog(String dialogName, String buttonName){
		this.dialogs.put(dialogName, buttonName);
		return this;
		
	}
	
	private void log(String msg){
		System.out.println(LOGO+msg);
	}
	
	private boolean isWindowOnTop(TopLevelTestObject window){
		try{
			window.activate();
			return true;
		}catch(Exception e){
			log(window.getProperty(".captionText")+" is not the top window");
			return false;
		}
	}
	
	public void closeNamedDialogs(){
		Iterator<String> it = this.dialogs.keySet().iterator();
		while(it.hasNext()){
			String dialogName = it.next();
			String buttonName = dialogs.get(dialogName);
			DOF.getButton(DOF.getDialog(dialogName), buttonName).click();
			sleep(1);
		}
		
	}
	
	private List<String> getTopWindowCaptions(){
		List<String> captions = new ArrayList<String>();
		TestObject[] objs = RationalTestScript.find(RationalTestScript.atChild(
				".domain", "Java"));
		for(TestObject obj:objs){
			if(obj.getObjectClassName().equals("org.eclipse.swt.widgets.Shell")&&DOF.isVisible(obj)){
				if(!obj.getProperty(".captionText").toString().trim().equals("")){
					captions.add(obj.getProperty(".captionText").toString());
				}
			}
		}
		return captions;
	}
}
