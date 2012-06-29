package com.sybase.automation.framework.core.helper;

import java.util.ArrayList;
import java.util.List;

public class DeviceResult {

	private String scriptName;
	private boolean isPassed = true;
	private List<DeviceVP> vpList = new ArrayList<DeviceVP>();
	
	public DeviceResult(String string) {
		this.scriptName = string;
	}
	
	public String getScriptName() {
		return scriptName;
	}
	public void setScriptName(String scriptName) {
		this.scriptName = scriptName;
	}
	public boolean isPassed() {
		return isPassed;
	}
	public void setPassed(boolean isPassed) {
		this.isPassed = isPassed;
	}
	public List<DeviceVP> getVpList() {
		return vpList;
	}
	public void setVpList(List<DeviceVP> vpList) {
		this.vpList = vpList;
	}
	
	public DeviceResult addVp(DeviceVP vp){
		if(!this.scriptName.equals(vp.getScriptName())){
			throw new RuntimeException("Ooops!");
		}else{
			if(!vp.isPass()){
				this.isPassed = false;
			}
			vpList.add(vp);
			return this;
		}
	}
}
