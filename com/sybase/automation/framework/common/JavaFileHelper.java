package com.sybase.automation.framework.common;

import java.io.File;
import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import com.rational.test.ft.object.interfaces.TopLevelTestObject;
import com.rational.test.ft.script.RationalTestScript;
import com.sybase.automation.framework.widget.DOF;
import component.entity.MainMenu;

public class JavaFileHelper extends RationalTestScript{
	static String methodDefinitionPattern = "\\s+public.*\\s+(.*)\\((.*)\\)\\s+\\{\\s+(.*)\\s+\\}";
	
	public static String[] getInterfaces(File javaFile){
		List<String> interfaces = new ArrayList<String>();
		String content = FileUtil.readFromFile(javaFile);
		content = content.substring(content.indexOf("implements"), content.indexOf("{")).replace("implements", "");
		String[] ints = content.split(",");
		String[] result = new String[ints.length];
		for(int i =0;i<result.length;i++){
			result[i] = ints[i].trim();
		}
		return result;
	}
	
	public static void main(String[] args){
		getInterfaces(new File("C:\\Documents and Settings\\eric\\workspace\\db_asa\\Filters\\SampleRC.java"));
	}

	public static String getMethodImplementation(File javaFile,String methodName) {
		String content = FileUtil.readFromFile(javaFile);
		Pattern p = Pattern.compile(methodDefinitionPattern);
		Matcher m = p.matcher(content);
		while(m.find()){
			if(m.groupCount()==3){
				if(m.group(1).equals(methodName)){
					return m.group(3);
				}
			}
		}
		return null;
	}
	
	public static void setContent(String content){
		ClipBoardUtil.setClipboardText(content);
		
		DOF.getStyledText(DOF.getRoot()).click();
		DOF.getStyledText(DOF.getRoot()).click(RationalTestScript.RIGHT);
		
		DOF.getContextMenu().click(RationalTestScript.atPath("Paste"));
		MainMenu.saveAll();
		RationalTestScript.sleep(3);
	}
	
	public static void createClass(String sourceFolder, String pkg, String name, String content){
		DOF.getMenu().click(RationalTestScript.atPath("File->New->Other..."));
		TopLevelTestObject dialog = DOF.getDialog("New");

		DOF.getTree(dialog).click(RationalTestScript.atPath("Java->Class"));
		DOF.getButton(dialog, "&Next >").click();
		
		dialog = DOF.getDialog("New Java Class");
		DOF.getTextField(dialog, "Source folder:").click();
		dialog.inputKeys(SpecialKeys.CLEARALL);
		dialog.inputChars(sourceFolder);
		
		DOF.getTextField(dialog, "Package:").click();
		dialog.inputKeys(SpecialKeys.CLEARALL);
		dialog.inputChars(pkg);
		
		DOF.getTextField(dialog, "Name:").click();
		dialog.inputKeys(SpecialKeys.CLEARALL);
		dialog.inputChars(name);
		
		DOF.getButton(dialog, "&Finish").click();

		
		RationalTestScript.sleep(5);
		
		DOF.getStyledText(DOF.getRoot()).click();
		DOF.getRoot().inputKeys("^a{ExtDelete}");
		
		ClipBoardUtil.setClipboardText(content);
		
		
		// Frame: Mobile Development - db_asa/Filters/SampleFilter2.java - Sybase Unwired WorkSpace
		DOF.getStyledText(DOF.getRoot()).click();
		DOF.getStyledText(DOF.getRoot()).click(RationalTestScript.RIGHT);
		
		DOF.getContextMenu().click(RationalTestScript.atPath("Paste"));
		MainMenu.saveAll();
		RationalTestScript.sleep(3);
		}

}
