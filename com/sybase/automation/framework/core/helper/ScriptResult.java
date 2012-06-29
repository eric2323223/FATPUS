package com.sybase.automation.framework.core.helper;

import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class ScriptResult {

	public static final int PASS = 0;
	public static final int FAIL = 1;
	public static final int ERROR = 2;
	
	private String name;
	private String exceptionName;
	private String exceptionMessage;
	private List<String> vp  = new ArrayList<String>();
	private int result;
	
	public String getName() {
		return name;
	}
	
	public String getExceptionName() {
		return exceptionName;
	}
	
	public String getExceptionMessage() {
		return exceptionMessage;
	}
	
//	public ScriptResult(String name){
//		this.name = name;
//	}
//	
//	public ScriptResult(String name, String str){
//		this.name = name;
//		this.vp.add(str);
//	}
//	
//	public ScriptResult(String name, String exceptionName, String exceptionMsg){
//		this.name = name;
//		this.exceptionName = exceptionName;
//		this.exceptionMessage = exceptionMsg;
//	}
	
	public ScriptResult() {
		// TODO Auto-generated constructor stub
	}
	
	public ScriptResult(String line){
		Pattern p = Pattern.compile("\\[(.*)\\]\\s+(\\w+)\\s+");
		Matcher m = p.matcher(line);
		if(m.find()&&m.groupCount()==2){
			this.name = m.group(1);
			this.result = m.group(2).equals("PASS")? PASS:FAIL;
		}
	}

	public String toString(){
		if(this.isPass()){
			return "["+this.name+"]\tPASS\n";
		}else if(!this.isPass() && this.vp.size()>0){
			String text = "["+this.name+"]\tFAIL Verification Points: ";
			for(String entry:this.vp){
				text = text + "["+entry+"] ";
			}
			return text+"\n";
		}else{
			return "["+this.name+"]\tFAIL Exception: ["+this.exceptionName+"]\n";
//			return "["+this.name+"]\tFAIL\n"+this.exceptionName+"\n"+this.exceptionMessage+"\n";
		}
	}
	
	public boolean isPass(){
		return this.result == PASS;
	}
	
	public boolean isFail(){
		return this.result == FAIL && !hasVpFail();
	}
	
	public boolean isError(){
		return hasVpFail();
	}

	public boolean hasException(){
		return this.exceptionName!=null;
	}
	
	public boolean hasVpFail(){
		return this.vp.size()>0;
	}
	
	public List<String> getVpFailures(){
		return this.vp;
	}

	public void setName(String currentScriptName) {
		this.name = currentScriptName;
	}

	public void addVpFailure(String failureLine) {
		this.vp.add(failureLine);
	}

	public void setException(String exceptionName2) {
		this.exceptionName = exceptionName2;
	}

	public void setResult(int pass2) {
		this.result = pass2;
	}

	public void clear() {
		this.name = null;
		this.result = FAIL;
		this.vp.clear();
		this.exceptionName = null;
		this.exceptionMessage = null;
	}

	public ScriptResult mergeDeviceResult(DeviceResult deviceResult) {
		if(!deviceResult.isPassed()){
			this.result = FAIL;
			for(DeviceVP vp:deviceResult.getVpList()){
				addVpFailure(vp.toString());
			}
		}
		return this;
	}
	
}
