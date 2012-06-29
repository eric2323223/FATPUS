package com.sybase.automation.framework.common;

import java.io.File;

import org.apache.commons.vfs2.FileChangeEvent;
import org.apache.commons.vfs2.FileListener;
import org.apache.commons.vfs2.FileMonitor;
import org.apache.commons.vfs2.FileObject;
import org.apache.commons.vfs2.FileSystemException;
import org.apache.commons.vfs2.FileSystemManager;
import org.apache.commons.vfs2.VFS;
import org.apache.commons.vfs2.impl.DefaultFileMonitor;

public class FileWatcher {
	private FileSystemManager fsm;
	private DefaultFileMonitor fm;
	private IOperation operation;
	private WatchLog watchLog = new WatchLog();
	
	public FileWatcher watch(String[] files, IOperation op) {
		try {
			fsm = VFS.getManager();
			FileObject[] fos = new FileObject[files.length];
			for(int i=0;i<fos.length;i++){
				fos[i] = fsm.resolveFile(files[i]);
			}
//			FileObject fo = fsm.resolveFile(file.getAbsolutePath());
			fm = new DefaultFileMonitor(new FileListener(){

				@Override
				public void fileChanged(FileChangeEvent arg0) throws Exception {
					watchLog.addEvent(arg0);
				}

				@Override
				public void fileCreated(FileChangeEvent arg0) throws Exception {
					watchLog.addEvent(arg0);
				}

				@Override
				public void fileDeleted(FileChangeEvent arg0) throws Exception {
					watchLog.addEvent(arg0);
				}
				
			});
			fm.setRecursive(true);
			for(int i=0;i<fos.length;i++){
				fm.addFile(fos[i]);
			}
//			fm.addFile(fo);
			operation = op;
			return this;
		} catch (FileSystemException e) {
			e.printStackTrace();
			throw new RuntimeException();
		}
	}


	public WatchLog perform(){
		watchLog.reset();
		fm.start();
		operation.operate();
		fm.stop();
		return watchLog;
	}

}
