/**
 * Installation
 *~~~~~~~~~~~~~~
 * 1.copy jacob.dll to a location in path (e.g. c:\winnt)
 * 2.include jacob.jar in classpath (e.g. set in Project Properties)
 * 3.regsrv32 SuadeXDE.dll
 * 
 * Example Usage
 *~~~~~~~~~~~~~~~
 *  public righClickDBConnectionProfile(){
 * 		SybaseWorkspace().activate();//ensure eclipse is foreground
 *		sleep(1);
 *		String name="PBTreeView32_100";
 *      NativeTreeView tree = new NativeTreeView(Composite(),name);
 *      tree.rightClick("Database Connection Profiles->ASA Demo");
 *      sleep(1);
 * }
 * 
 */
package com.sybase.automation.framework.widget.eclipse;

import com.rational.test.ft.*;
import com.rational.test.ft.object.interfaces.*;
import com.sybase.automation.framework.widget.win32.impl._TreeView;

public class TreeView {

	private static final String	p_compName = "SuadeXDE.NativeTreeView";
	private _TreeView 		p_TreeView=null;//treeview COM object
	private int 					hTv=0;//treeview handle

	public TreeView(ScrollTestObject obContainer, String className) {
		p_TreeView=new _TreeView(p_compName);
		
		java.awt.Rectangle rect;
		rect=obContainer.getScreenRectangle();
		int xPos, yPos;
		

		xPos=rect.x+rect.width/2;
		yPos=rect.y+rect.height/2;
		hTv=p_TreeView.findTreeView(className,xPos,yPos); //get the center position
		if(hTv==0){
			throw new ObjectNotFoundException(className + "not found at ["+xPos+","+yPos+"]");
		}
	}
	public TreeView(int xPos, int yPos, String className) {
		p_TreeView=new _TreeView(p_compName);
		
		hTv=p_TreeView.findTreeView(className,xPos,yPos); //get the center position
		if(hTv==0){
			throw new ObjectNotFoundException(className + "not found at ["+xPos+","+yPos+"]");
		}
	}

	/**
	 * @param path
	 * @param level
	 * 			0 - debug
	 * 			2 - info
	 * 			4 - warn
	 * 			6 - error (default)
	 */
	public void configLog(String path, int level){
		p_TreeView.configLog(path, level);
	}
	public void click(String path){
		p_click(hTv,p_findItem(path),true,1);
	}
	public void doubleClick(String path){
		p_click(hTv,p_findItem(path),true,2);
	}
	public void rightClick(String path){
		p_click(hTv,p_findItem(path),false,1);
	}
	public boolean nodeExists(String path){
		try{
//			int hItem=p_findItem(path);
		}catch(ObjectNotFoundException e){
			return false;
		}
		return true;
	}
	
	///////////////////////////////////////////////////////////////////////////
	private int p_findItem(String path){
		String[] items=path.split("->");
		int h=0;
		for(int i=0; i<items.length; i++){
			h=p_TreeView.findChild(hTv,h,items[i]);
			if(h==0){
				throw new ObjectNotFoundException("tree item not found, "+path);
			}
			if(i<items.length-1){
				p_TreeView.expandItem(hTv,h);
			}
		}
		
		return h;
	}
	
	private void p_click(int hTv, int hItem, boolean isLeft,int n){
		if(hItem==0){
			throw new ObjectNotFoundException();
		}
		p_TreeView.click(hTv, hItem,isLeft,(short)n);
	}
}