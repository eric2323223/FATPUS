package com.sybase.automation.framework.core.helper;

import java.util.Properties;

public class DeviceVP {
	private String scriptName;
	private String vpName;
	private boolean isPass;
	private String expected;
	public String getScriptName() {
		return scriptName;
	}

	public String getVpName() {
		return vpName;
	}

	public boolean isPass() {
		return isPass;
	}

	public String getExpected() {
		return expected;
	}

	public String getActual() {
		return actual;
	}

	private String actual;
	
	//format1: rftScriptName=*, vpName=*
	//format2: rftScriptName=*, vpName=*, expected=*, actual=*
	public DeviceVP(Properties p) {
		this.scriptName = p.getProperty("rftScriptName");
		this.vpName = p.getProperty("vpName");
		if(p.getProperty("actual")!=null){
			this.isPass = false;
			this.expected = p.getProperty("expected");
			this.actual = p.getProperty("actual");
		}else{
			this.isPass = true;
		}
	}
	
	public String toString(){
		String str = "["+this.scriptName+"]["+this.vpName+"]";
		if(this.isPass){
			return str;
		}else{
			return str+"["+this.expected+"]["+this.actual+"]";
		}
	}
	
	public static DeviceVP parseProperties(Properties p){
		if(p.getProperty("rftScriptName")!=null){
			return new DeviceVP(p);
		}else{
			return null;
		}
		
	}

}
