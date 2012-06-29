package com.sybase.automation.framework.core.helper;

import java.io.File;
import java.io.IOException;
//import java.lang.annotation.Annotation;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Properties;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.joda.time.Interval;
import org.joda.time.Period;

import com.rational.test.ft.object.interfaces.ProcessTestObject;
import com.rational.test.ft.vp.IFtVerificationPoint;
import com.sybase.automation.framework.common.DialogCloser;
import component.entity.MainMenu;
import component.entity.WFCustomizer.NanoHTTPD;
import component.entity.WFCustomizer.OnDataReceivedListener;
import component.entity.model.E2ETestScript;

/**
 * Description : Super class for script helper
 * 
 * @author eric
 * @since August 12, 2010
 */
public abstract class BatchExecutionSuperClass extends VPHelper {

	protected List<ScriptResult> scripts = new ArrayList<ScriptResult>();
	protected List<String> log = new ArrayList<String>();
	protected HTMLScriptResultParser resultParser = new HTMLScriptResultParser();

	//new mode to run batch scripts
	private List<String> e2eTestScripts = new ArrayList<String>();
	private List<DeviceResult> deviceResults = new ArrayList<DeviceResult>(); 
	private NanoHTTPD httpd = NanoHTTPD.getInstance(8008);
	
	private String mode = "normal";
	private long startTime;
	private long stopTime;
	
	public void setExecutionMode(String mode){
		this.mode = mode;
	}
	
	public boolean isNormalMode(){
		return this.mode.equals("normal");
	}
	
	public Object callScript(String name) {
		try {
//			if(!isNormalMode()){
//				if(e2eTestScripts.size()==0){
//					startListener();
//				}
//				if(isE2ETestScript(name)){
//					e2eTestScripts.add(name);
//				}
//			}
			super.callScript(name);
		} catch (Exception e) {
			restoreEnv(e);
		} finally {
			unregisterAll();
			return null;
		}
	}

//	private boolean isE2ETestScript(String name) throws ClassNotFoundException {
//		Annotation[] annotations = Class.forName(name).getDeclaredAnnotations();
//		for(Annotation annotation: annotations){
//			if(annotation instanceof E2ETestScript){
//				return true;
//			}
//		}
//		return false;
//	}
	
	private void checkDeviceResult(DeviceVP vp){
		for(String script:e2eTestScripts){
			if(script.equals(vp.getScriptName())){
				e2eTestScripts.remove(script);
				DeviceResult r = new DeviceResult(script);
				r.addVp(vp);
				deviceResults.add(r);
				return ;
			}
		}
		int index = getDeviceResultIndex(vp.getScriptName());
		if(index >= 0){
			DeviceResult result = deviceResults.remove(index);
			result.addVp(vp);
			deviceResults.add(result);
		}
	}
	
	private int getDeviceResultIndex(String scriptName){
		for(int i=0;i<deviceResults.size();i++){
			if(deviceResults.get(i).equals(scriptName)){
				return i;
			}
		}
		return -1;
	}

	private void startListener() {
		System.out.println("listener is started.");
		httpd.registerOnDataReceivedListener(new OnDataReceivedListener(){
			NanoHTTPD server;

			@Override
			public void onDataReceived(Properties d) {
				DeviceVP vp = DeviceVP.parseProperties(d);
				if(vp!=null){
					checkDeviceResult(vp);
				}
			}

			@Override
			public void setSever(NanoHTTPD nanoHTTPD) {
				server = nanoHTTPD;
			}
			
			public void onTerminate(){
				//no need to do anything, it's the responsibility of BatchExecutionSuperHelper.onTerminate()
			}
			
		});
		httpd.run();
	}
	
	private void shutdownListener(){
		httpd.stop();
		System.out.println("listener is stopped.");
	}

	@Override
	public void onVpFailure(IFtVerificationPoint vp) {
		System.out.println("---"+vp.getVPName()+"---");
		System.out.println("Expected: ["+vp.getExpectedData()+"]");
		System.out.println("Actual  : ["+vp.getActualData()+"]");
		super.onVpFailure(vp);
	}

	private void restoreEnv(Exception e) {
		closeDialogs();
		MainMenu.resetPerspective();
	}


	private void closeDialogs(){
		new DialogCloser().closeDialogs();
	}

	public void onTerminate() {
		System.out.println();
		System.out.println("=================================================");
		System.out.println("Summary of test suite["+getScriptName()+"]");
		System.out.println("=================================================");
		stopTime = new Date().getTime();
		System.out.println(getTimeElapsed(startTime, stopTime));
		this.resultParser.checkLog();
		
		if(!isNormalMode()){
			waitForDeviceTestResults();
			shutdownListener();
			resultParser.insertDeviceResults(deviceResults);
		}
		
		System.out.println(this.resultParser.getSummary());
		System.out.println(this.resultParser.getFailedScriptsInvocation());
		this.resultParser.reset();
	}
	
	private String getTimeElapsed(long d1, long d2) {
		Interval interval = new Interval(d1, d2);
		Period period = interval.toPeriod();
		if(period.getHours()>0){
			return "Total Time used: "+ period.getHours()+" hours "+period.getMinutes()+" minutes "+period.getSeconds()+" seconds";
		}else if(period.getMinutes()>0){
			return "Total Time used: "+ period.getMinutes()+" minutes "+period.getSeconds()+" seconds";
		}else{
			return "Total Time used: "+ period.getSeconds()+" seconds";
		}
		
	}

	private void waitForDeviceTestResults() {
		while(e2eTestScripts.size()>0){
			try {
				Thread.sleep(1000);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
	}

	public void onInitialize(){
		this.resultParser.setLogFile(getLogPath());
		this.resultParser.reset();
		startTime = new Date().getTime();
	}

	private String getProjectPath() {
		try {
			File pwd = new File(".");
			String currentPath;
			currentPath = pwd.getCanonicalPath();
			if (currentPath.indexOf("UEP_ET") >= 0) {
//				System.out.println(currentPath);
				return currentPath;
			} else {
				throw new RuntimeException(
						"BatchExcecutionSuperClass can't determine ET script project path.");
			}
		} catch (IOException e) {
			e.printStackTrace();
			return null;
		}

//		 return "C:/Documents and Settings/eric/IBM/rationalsdp/workspace155/UEP_ET";
	}

	private String getLogPath() {
		return getProjectPath() + "_logs\\" + getScriptName()
				+ "\\rational_ft_logframe.html";
	}

	public void callScripts(String pattern){
		String dir = getProjectPath() +"\\"+ pattern.substring(0, pattern.lastIndexOf(".")).replace(".", "\\");
		String p = pattern.substring(pattern.lastIndexOf(".")+1, pattern.length());
		List<String> scripts = getScripts(dir, p);
		System.out.println(scripts.size()+ " scripts are started.");
		for(String script:scripts){
			//prevent infinite loop
			if(!script.toLowerCase().endsWith("all")){
				System.out.println(script);
				callScript(script);
			}
		}
	}
	
	public void callScripts(String pattern, String exclude){
		String dir = getProjectPath() +"\\"+ pattern.substring(0, pattern.lastIndexOf(".")).replace(".", "\\");
		String p = pattern.substring(pattern.lastIndexOf(".")+1, pattern.length());
		List<String> scripts = getScripts(dir, p, exclude);
		System.out.println(scripts.size()+ " scripts are started.");
		for(String script:scripts){
			//prevent infinite loop
			if(!script.toLowerCase().endsWith("all")){
				System.out.println(script);
				callScript(script);
			}
		}
	}
	
	private List<String> getScripts(String folder, String pattern){
		File dir = new File(folder);
		if(dir.exists() && dir.isDirectory()){
			List<String> fileNames = new ArrayList<String>();
			String[] allFiles = dir.list();
			for(String fileName:allFiles){
				if(fileName.endsWith(".testsuite")){
					fileName = fileName.substring(0, fileName.lastIndexOf("."));
					Pattern p = Pattern.compile(translatePattern(pattern));
					Matcher m = p.matcher(fileName);
					if (m.matches()) {
						fileName = folder+"\\"+ fileName;
						fileName = fileName.replace(getProjectPath(), "");
						fileName = fileName.substring(1, fileName.length());
						fileNames.add(fileName.replace("\\", "."));
					}
				}
			}
			return fileNames;
		}else{
			throw new RuntimeException("Invalide folder: "+folder);
		}
	}
	
	private boolean include(String text, String keyword){
		for(String word:keyword.split(",")){
			if(text.contains(word)){
				return true;
			}
		}
		return false;
	}
	
	private List<String> getScripts(String folder, String pattern, String exclude){
		File dir = new File(folder);
		if (dir.exists() && dir.isDirectory()) {
			List<String> fileNames = new ArrayList<String>();
			String[] allFiles = dir.list();
			for (String fileName : allFiles) {
				if (fileName.endsWith(".testsuite")
						&& !include(fileName, exclude)) {
					fileName = fileName.substring(0, fileName.lastIndexOf("."));
					Pattern p = Pattern.compile(translatePattern(pattern));
					Matcher m = p.matcher(fileName);
					if (m.matches()) {
						fileName = folder + "\\" + fileName;
						fileName = fileName.replace(getProjectPath(), "");
						fileName = fileName.substring(1, fileName.length());
						fileNames.add(fileName.replace("\\", "."));
					}
				}

			}
			return fileNames;
		}else{
			throw new RuntimeException("Invalide folder: "+folder);
		}
	}
	
	private String translatePattern(String pattern){
		return pattern.replace("*", ".*");
	}
	
}
