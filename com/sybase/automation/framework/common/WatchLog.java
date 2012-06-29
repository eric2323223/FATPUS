package com.sybase.automation.framework.common;

import java.util.ArrayList;
import java.util.List;

import org.apache.commons.vfs2.FileChangeEvent;

public class WatchLog {
	private List<FileChangeEvent> events = new ArrayList<FileChangeEvent>();

	public void addEvent(FileChangeEvent event) {
		events.add(event);
	}
	
	public boolean hasChanges(){
		return events.size()>0;
	}

	public void reset() {
		events.clear();
	}

}
