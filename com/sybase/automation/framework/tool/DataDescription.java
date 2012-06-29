package com.sybase.automation.framework.tool;

public class DataDescription {
	private int length;

	public DataDescription length(int i) {
		this.length = i;
		return this;
	}
	
	public int getLength(){
		return this.length;
	}

}
