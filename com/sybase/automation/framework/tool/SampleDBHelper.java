package com.sybase.automation.framework.tool;

import java.io.IOException;

import component.entity.GlobalConfig;

public class SampleDBHelper {
	private static String dbName = GlobalConfig.SUP_ROOT+"\\servers\\unwiredserver\\data\\sampledb.db";
	
	public static void backup(){
		try {
			stopDB();
			String backupDB = dbName.replace(".", "_backup.");
			Runtime.getRuntime().exec("cmd /k copy /y "+dbName+" "+backupDB);
			startDB();
		} catch (IOException e) {
			e.printStackTrace();
		} 
	}
	
	public static void restore(){
		try {
			stopDB();
			String backupDB = dbName.replace(".", "_backup.");
			Runtime.getRuntime().exec("cmd /c del "+dbName);
			Runtime.getRuntime().exec("cmd /c copy "+backupDB+" "+dbName);
			startDB();
		} catch (IOException e) {
			e.printStackTrace();
		}
		
	}
	
	public static void startDB(){
		try {
			Runtime.getRuntime().exec(GlobalConfig.SUP_ROOT+"\\servers\\unwiredserver\\bin\\sampledb.bat start");
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	public static void stopDB(){
		try {
			Process p = Runtime.getRuntime().exec(GlobalConfig.SUP_ROOT+"\\servers\\unwiredserver\\bin\\sampledb.bat stop");
			p.waitFor();
		} catch (IOException e) {
			e.printStackTrace();
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}
	
	public static void main(String []args){
		SampleDBHelper.backup();
		
	}

}
