/**
 * Installation
 *~~~~~~~~~~~~~~
 * 1.copy jacob.dll to a location in path (e.g. c:\winnt)
 * 2.include jacob.jar in classpath (e.g. set in Project Properties)
 * 3.regsrv32 SuadeXDE.dll
 * 
 * Example Usage
 *~~~~~~~~~~~~~~~
 *	StyledText().click(RIGHT,atPoint(100,100));
 *	NativeContextMenu ctx = new NativeContextMenu();
 *	System.out.println(ctx.itemEnabled("Compare With->Each Other"));
 */
package com.sybase.automation.framework.widget.eclipse;

import com.jacob.com.*;
import com.rational.test.ft.*;
import com.rational.test.ft.object.interfaces.*;
import com.sybase.automation.framework.widget.interfaces.IContextMenu;
import com.sybase.automation.framework.widget.win32.impl._ContextMenu;


public class ContextMenu implements IContextMenu {

	private static final String	p_compName = "SuadeXDE.NativeContextMenu";
	private _ContextMenu		p_ctxMenu=null;//context menu COM object
	private int 					p_hCtxMenu=0;//context menu handle

	public ContextMenu() {
		p_ctxMenu=new _ContextMenu(p_compName);
		p_hCtxMenu=p_ctxMenu.getPopupMenu();
		if(p_hCtxMenu == 0){
			throw new ObjectNotFoundException("Context Menu not found");
		}
	}
	
	public boolean itemChecked(String path){
//		String[] items=path.split("->");
		int hMenu = p_getHMenu(path);
		int idx = p_getItemIdx(hMenu,path);
		return p_ctxMenu.menuItemChecked(hMenu,idx);
	}
	public boolean itemEnabled(String path){
//		String[] items=path.split("->");
		int hMenu = p_getHMenu(path);
		int idx = p_getItemIdx(hMenu,path);
		return p_ctxMenu.menuItemEnabled(hMenu,idx);
	}
	public String[] getChildren(String path){
		String[] children;
		
		int hMenu = p_getHMenu(path);
		children = new String[p_ctxMenu.getItemCount(hMenu)];
		for(int i=0; i<children.length; i++){
			children[i] = p_ctxMenu.getMenuText(hMenu,i);
		}
		return children;
	}
	
	///////////////////////////////////////////////////////////////////////////
	private int p_getHMenu(String path){
		String[] items=path.split("->");
		int hMenu = p_hCtxMenu;
		for(int i=0; i<items.length-1; i++){
			hMenu=p_ctxMenu.findChild(hMenu,items[i]);
			if(hMenu==0){
				throw new ObjectNotFoundException("Context Menu item not found at "+path);
			}
		}
		return hMenu;
	}
	private int p_getItemIdx(int hMenu, String path){
		int idx;

		String[] items=path.split("->");
		idx=p_ctxMenu.findItem(hMenu,items[items.length-1]);
		if(idx<0) {
			throw new ObjectNotFoundException("Context Menu item not found at "+path);
		}
		return idx;
	}
}
