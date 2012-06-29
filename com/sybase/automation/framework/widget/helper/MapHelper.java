package com.sybase.automation.framework.widget.helper;
import java.awt.Point;
import java.awt.Rectangle;
import java.util.HashMap;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Vector;

import com.rational.test.ft.*;
import com.rational.test.ft.object.interfaces.*;
import com.rational.test.ft.object.interfaces.SAP.*;
import com.rational.test.ft.object.interfaces.WPF.*;
import com.rational.test.ft.object.interfaces.dojo.*;
import com.rational.test.ft.object.interfaces.siebel.*;
import com.rational.test.ft.object.interfaces.flex.*;
import com.rational.test.ft.script.*;
import com.rational.test.ft.value.*;
import com.rational.test.ft.vp.*;
import com.sybase.automation.framework.tool.NativeInvoker;
import com.sybase.automation.framework.widget.DOF;
import com.sybase.automation.framework.widget.factory.EclipseWidgetFactory;
import com.sybase.automation.framework.widget.interfaces.ITable;
import com.sybase.automation.framework.widget.interfaces.ITree;

import component.common.FigureHelper;
import component.common.MappingHelper;
import component.common.MappingHelper.ConnectionDecoration;
import component.common.MappingHelper.TreeFigureDecoration;

/**
 * Description   : Functional Test Script
 * @author eric
 */
public class MapHelper extends RationalTestScript
{
	public static void map(ScrollTestObject mapFigure, String sourceAttribute, String targetAttribute){
		String sourcePath = getFullTreePath(getSourceTree(mapFigure), sourceAttribute);
		String targetPath = getFullTreePath(getTargetTree(mapFigure), targetAttribute);
		getSourceTree(mapFigure).click(atPath(sourcePath));
		TestObject sAnchor = getSourceAnchorDecorationByTreeItem(mapFigure, sourcePath);
		getTargetTree(mapFigure).click(atPath(targetPath));
		TestObject tAnchor = getTargetAnchorDecorationByTreeItem(mapFigure, targetPath);
		Point sPoint = FigureHelper.getCenter(sAnchor);
		Point tPoint = FigureHelper.getCenter(tAnchor);
		mapFigure.click(atPoint(sPoint.x, sPoint.y));
		mapFigure.click(atPoint(tPoint.x, tPoint.y));
	}
	
	public static void unmap(ScrollTestObject mapFigure, String sourceAttribute, String targetAttribute){
		getConnection(mapFigure, sourceAttribute, targetAttribute).click(RIGHT);
		DOF.getContextMenu().click(atPath("Delete"));
	}
	
	private static GuiTestObject getConnection(ScrollTestObject mapFigure, String sourceAttribute, String targetAttribute){
		TestObject[] objs = mapFigure.find(	RationalTestScript.atDescendant(".class",
						"com.sybase.uep.tooling.common.ui.mapper.SelectableConnection"));
		System.out.println(objs.length);
//		NativeInvoker.printMethods(objs[0]);
		for(TestObject obj:objs){
			TestObject sourceAnchor = (TestObject)obj.invoke("getSourceAnchor");
			TestObject sourceAnchorDecoration = getAnchorDecoration(sourceAnchor);
			TestObject targetAnchor = (TestObject)obj.invoke("getTargetAnchor");
			TestObject targetAnchorDecoration = getAnchorDecoration(targetAnchor);
			
			String sourcePath = getFullTreePath(getSourceTree(mapFigure), sourceAttribute);
			String targetPath = getFullTreePath(getTargetTree(mapFigure), targetAttribute);
			TestObject sAnchor = getSourceAnchorDecorationByTreeItem(mapFigure, sourcePath);
			TestObject tAnchor = getTargetAnchorDecorationByTreeItem(mapFigure, targetPath);
			
			if(isSame(sourceAnchorDecoration, sAnchor) && isSame(targetAnchorDecoration, tAnchor)){
				return (GuiTestObject)obj;
			}
		}
		return null;
	}
	
	private static boolean isSame(TestObject anchorDecorationA, TestObject anchorDecorationB){
		Point pointA = FigureHelper.getCenter(anchorDecorationA);
		Point pointB = FigureHelper.getCenter(anchorDecorationB);
		if(pointA.x == pointB.x && pointA.y == pointB.y){
			return true;
		}else{
			return false;
		}
	}
	
	private static String getFullAttributeName(TestObject tree, String attribute){
		String[] attributes = TreeHelper.getNodesOfLevel(tree, 2);
		for(String att:attributes){
			if(att.startsWith(attribute+"[")||
					att.startsWith("+ "+attribute+"[")){
				return att;
			}
		}
		return null;
	}
	
	private static String getFullTreePath(TestObject tree, String attr){
		if(attr.contains("->")){
			return attr;
		}else{
			return TreeHelper.getFirstItem((ScrollGuiSubitemTestObject)tree)+"->Attributes->"+getFullAttributeName(tree, attr);
		}
	}
	
	private static TestObject getSourceAnchorDecorationByTreeItem(ScrollTestObject mapFigure, String path){
		Vector sourceAnchors = (Vector)(getSoucreFigure(mapFigure).invoke("getSourceAnchors"));
		for(int i=0;i<sourceAnchors.size();i++){
			TestObject sDeco = getAnchorDecoration((TestObject)sourceAnchors.get(i));
			int yAnchor = getY(sDeco);
			int yTreeItem = getY((TestObject)getSourceTreeItem(mapFigure, path));
			if(absDistance(yAnchor, yTreeItem+magicNumber())<3){
				return (TestObject)sDeco;
			}
		}
		return null;
	}
	
	private static TestObject getTargetAnchorDecorationByTreeItem(ScrollTestObject mapFigure,String path){
		Vector targetAnchors = (Vector)(getTargetFigure(mapFigure).invoke("getTargetAnchors"));
		for(int i=0;i<targetAnchors.size();i++){
			TestObject tDeco = getAnchorDecoration((TestObject)targetAnchors.get(i));
			int yAnchor = getY(tDeco);
			int yTreeItem = getY((TestObject)getTargetTreeItem(mapFigure, path));
			if(absDistance(yAnchor, yTreeItem+magicNumber())<3){
				return (TestObject)tDeco;
			}
		}
		return null;
	}
	
	private static int absDistance(int a, int b) {
		return a > b ? a - b : b - a;
	}
	
	private static int getY(TestObject obj){
		Point p = FigureHelper.getCenter(obj);
		return p.y;
	}

	private static TestObject getSourceTreeItem(ScrollTestObject map, String path){
		return (TestObject)getSourceTree(map).getSubitem(atPath(path));
	}
	
	private static TestObject getTargetTreeItem(ScrollTestObject map, String path){
		return (TestObject)getTargetTree(map).getSubitem(atPath(path));
	}
	
	private static TestObject getAnchorDecoration(TestObject anchor){
		return (TestObject)anchor.invoke("getDecoration");
	}
	
	private static String getId(TestObject anchor){
		return anchor.invoke("getId").toString();
	}
	
	private static TestObject getSourceAnchor(ScrollTestObject map,int index){
		Vector sourceAnchors = (Vector)(getSoucreFigure(map).invoke("getSourceAnchors"));
		return (TestObject)sourceAnchors.get(index);
	}
	
	private static TestObject getSoucreFigure(ScrollTestObject mapFigure){
		TestObject sourceFigure = (TestObject)(mapFigure.invoke("getSourceFigure"));
		return sourceFigure;
	}
	
	private static TestObject getTargetFigure(ScrollTestObject mapFigure){
		TestObject targetFigure = (TestObject)(mapFigure.invoke("getTargetFigure"));
		return targetFigure;
	}
	
	private static ScrollGuiSubitemTestObject getSourceTree(ScrollTestObject map){
		return (ScrollGuiSubitemTestObject)getSoucreFigure(map).invoke("getTree");
	}
	
	private static ScrollGuiSubitemTestObject getTargetTree(ScrollTestObject map){
		return (ScrollGuiSubitemTestObject)getTargetFigure(map).invoke("getTree");
	}
	
	private static int magicNumber(){
		return 24;
	}
}

