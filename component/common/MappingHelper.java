package component.common;

import java.awt.Point;
import java.awt.Rectangle;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Vector;

import com.rational.test.ft.object.interfaces.ScrollGuiSubitemTestObject;
import com.rational.test.ft.object.interfaces.ScrollTestObject;
import com.rational.test.ft.object.interfaces.TestObject;
import com.rational.test.ft.script.RationalTestScript;
import com.sybase.automation.framework.tool.NativeInvoker;
import com.sybase.automation.framework.widget.DOF;
import com.sybase.automation.framework.widget.factory.EclipseWidgetFactory;
import com.sybase.automation.framework.widget.interfaces.ITable;
import com.sybase.automation.framework.widget.interfaces.ITree;

/**
 * Description : Functional Test Script
 * 
 * @author dongxu
 */
public class MappingHelper extends RationalTestScript {

	/** Eclipse widget factory */
	private EclipseWidgetFactory ewFactory = new EclipseWidgetFactory();

	/** The object which hold the map */
	private ScrollTestObject mapControl = null;

	/** Decoration for source tree object */
	private TreeFigureDecoration sTreeDec = null;

	/** Decoration for target tree object */
	private TreeFigureDecoration tTreeDec = null;

	/** Tree Figure class */
	private static final String TREE_FIGURE = "com.sybase.uep.tooling.common.ui.mapper.TreeViewerFigure";

	/**
	 * Script Name : <b>MappingHelper</b> Generated : <b>Jul 16, 2008 4:38:52
	 * PM</b> Description : Functional Test Script Original Host : WinNT
	 * Version 5.1 Build 2600 (S)
	 * 
	 * @since 2008/07/16
	 * @author dongxu
	 */
//	public void testMain(Object[] args) {
//		init(DOF.getMapperFigureContorol(DOF.getDialog("New Relationship")));
////		init(mapperFigureControl());
//		Map m = new LinkedHashMap();
//		m.put("a", "a");
//		map(m, true);
//		Map m1 = new LinkedHashMap();
//		m1.put("a", "a");
//		m1.put("b", "b");
//		verifyMapped(m1, true);
//		m1.put("a", "b");
//		verifyMapped(m1, true);
//		verifyMappingTable("a", "a", table(), 1, 3, true);
//		verifyMappingTableInParam(m1, table(), true);
//		verifyMappingTable(m1, table(), 1, 3, true);
//	}

	/**
	 * Default constructor, to make script runable.
	 * 
	 */
	public MappingHelper() {

	}

	/**
	 * Construct a helper with specified mapControl. to perform mapping actions,
	 * the given Map Control Figure must be completely shown.
	 * 
	 * @param mapControl
	 */
	public MappingHelper(ScrollTestObject mapControl) {
		init(mapControl);
	}

	/**
	 * Init the helper
	 * 
	 * @param mapControl
	 */
	public void init(ScrollTestObject mapControl) {
		this.mapControl = mapControl;
		reload();
	}

	/**
	 * Find source and target map tree out
	 * 
	 */
	public void reload() {
		TestObject content = (TestObject) mapControl.invoke("getContents");
		TestObject children = (TestObject) content.invoke("getChildren");
		int length = ((Integer) children.invoke("size")).intValue();
		sTreeDec = null;
		tTreeDec = null;
		for (int i = 0; i < length; i++) {
			TestObject obj = (TestObject) NativeInvoker.invoke(children, "get",
					i);
			if (!obj.getProperty("class").equals(TREE_FIGURE)) {
				continue;
			}
			if (isSourceTreeFigure(obj)) {
				this.sTreeDec = new TreeFigureDecoration(obj, true);
			} else {
				this.tTreeDec = new TreeFigureDecoration(obj, false);
			}
			if (sTreeDec != null && tTreeDec != null) {
				break;
			}
		}
	}

	/**
	 * Un map several items in map tree
	 * 
	 * @param unmapList
	 *            items to be unmapped
	 * @param isSource
	 *            unmap the source tree or target tree
	 */
	public void unMap(List unmapList, boolean isSource) {
		for (int i = 0; i < unmapList.size(); i++) {
			String item = (String) unmapList.get(i);
			unMap(item, isSource);
		}
	}

	/**
	 * Map items in map tree, store name pair in map
	 * 
	 * @param map
	 *            items to be mapped
	 * @param delExisting
	 *            whether delete existing map
	 */
	public void map(Map map, boolean delExisting) {
		for (Iterator iter = map.entrySet().iterator(); iter.hasNext();) {
			Map.Entry mapEntry = (Map.Entry) iter.next();
			map(mapEntry, delExisting);
		}
	}

	/**
	 * Map a pair of items
	 * 
	 * @param sourceItem
	 *            source item name
	 * @param targetItem
	 *            target item name
	 * @param delExisting
	 *            whether delete existing map
	 */
	public void map(String sourceItem, String targetItem, boolean delExisting) {
		TestObject[] anchors = getMapSTAnchors(sourceItem, targetItem);
		doMap(anchors[0], anchors[1], delExisting);
	}
	
	public void unMap(String sourceItem){
		TestObject anchor = getSourceAnchor(sourceItem);
		doUnMap(anchor, true);
	}

	/**
	 * Un map one item
	 * 
	 * @param item
	 *            item name
	 * @param isSource
	 *            unmap the source tree or target tree
	 */
	public void unMap(String item, boolean isSource) {
		TreeFigureDecoration dec = isSource ? sTreeDec : tTreeDec;
		String path = dec.getTreeWidget().findFirstLeafNodePath(
				makeItemNodeRegex(item));
		if (path == null) {
			throw new RuntimeException(
					"MappingHelper.unMap: path not found with specified item");
		}
		TestObject anchor = isSource ? getSourceAnchor(path)
				: getSourceAnchor(path);
		doUnMap(anchor, isSource);
	}

	/**
	 * Implement map action between two anchors, the anchor must be completely
	 * shown an click able
	 * 
	 * @param sAnchor
	 * @param tAnchor
	 * @param delExisting
	 */
	public void doMap(TestObject sAnchor, TestObject tAnchor,
			boolean delExisting) {
		dealMapped(sAnchor, tAnchor, delExisting);
		Point sPoint = calcAnchorClickPoint(sAnchor);
		Point tPoint = calcAnchorClickPoint(tAnchor);
		mapControl.click(atPoint(sPoint.x, sPoint.y));
		sleep(0.5);
		mapControl.click(atPoint(tPoint.x, tPoint.y));
		sleep(0.5);
	}

	/**
	 * Implement un map action of one anchor, the anchor must be completely
	 * shown and click able
	 * 
	 * @param anchor
	 * @param isSource
	 */
	public void doUnMap(TestObject anchor, boolean isSource) {
		TestObject conn = getConn(anchor);
		if (conn == null) {
			return;
		}
		ConnectionDecoration cd = new ConnectionDecoration(conn);
		Point clickPoint = isSource ? cd.getSourceClickPoint() : cd
				.getTargetClickPoint();
		mapControl.click(atPoint(clickPoint.x, clickPoint.y));
		sleep(0.5);
		mapControl.click(RIGHT);
		sleep(0.5);
	}

	/**
	 * Verify the mapping figure, whether contains specified mappings or not
	 * 
	 * @param map
	 *            specified mappings
	 * @param shouldMapped
	 *            whether the specified mappings should exist or not
	 * @return
	 */
	public boolean verifyMapped(Map map, boolean shouldMapped) {
		boolean rst = shouldMapped;
		for (Iterator iter = map.entrySet().iterator(); iter.hasNext();) {
			Map.Entry me = (Map.Entry) iter.next();
			TestObject[] anchors = getMapSTAnchors((String) me.getKey(),
					(String) me.getValue());
			rst = hasSameConn(anchors[0], anchors[1]);
			if (rst != shouldMapped) {
				break;
			}
		}
		return vpManual("VerifyMapped", Boolean.valueOf(shouldMapped),
				Boolean.valueOf(rst)).performTest();
	}

	/**
	 * Verify the mapping figure, whether contains specified mapping or not
	 * 
	 * @param sourceItem
	 *            source item
	 * @param targetItem
	 *            target item
	 * @param shouldMapped
	 *            whether the specified mappings should exist or not
	 * @return
	 */
	public boolean verifyMapped(String sourceItem, String targetItem,
			boolean shouldMapped) {
		TestObject[] anchors = getMapSTAnchors(sourceItem, targetItem);
		return vpManual("VerifyMapped", Boolean.valueOf(shouldMapped),
				Boolean.valueOf(hasSameConn(anchors[0], anchors[1])))
				.performTest();
	}

	/**
	 * Verify mapping existance table in attribute view with specified table and
	 * map
	 * 
	 * @param map
	 * @param table
	 * @param shouldMapped
	 * @return
	 */
	public boolean verifyMappingTableInAttr(Map map,
			ScrollGuiSubitemTestObject table, boolean shouldMapped) {
		return verifyMappingTable(map, table, 1, 3, shouldMapped);
	}

	/**
	 * Verify mapping existance table in attribute view with specified table,
	 * source item and target item
	 * 
	 * @param sourceItem
	 * @param targetItem
	 * @param table
	 * @param shouldMapped
	 * @return
	 */
	public boolean verifyMappingTableInAttr(String sourceItem,
			String targetItem, ScrollGuiSubitemTestObject table,
			boolean shouldMapped) {
		return verifyMappingTable(sourceItem, targetItem, table, 1, 3,
				shouldMapped);
	}

	/**
	 * Verify mapping existance table in parameters view with specified table
	 * and map
	 * 
	 * @param map
	 * @param table
	 * @param shouldMapped
	 * @return
	 */
	public boolean verifyMappingTableInParam(Map map,
			ScrollGuiSubitemTestObject table, boolean shouldMapped) {
		return verifyMappingTable(map, table, 1, 3, shouldMapped);
	}

	/**
	 * Verify mapping existance table in parameters view with specified table ,
	 * source item and target item
	 * 
	 * @param sourceItem
	 * @param targetItem
	 * @param table
	 * @param shouldMapped
	 * @return
	 */
	public boolean verifyMappingTableInParam(String sourceItem,
			String targetItem, ScrollGuiSubitemTestObject table,
			boolean shouldMapped) {
		return verifyMappingTable(sourceItem, targetItem, table, 1, 3,
				shouldMapped);
	}

	/**
	 * Verify mapping existance table in parameters view with specified table,
	 * map, source item column and target column
	 * 
	 * @param map
	 * @param table
	 * @param sourceCol
	 * @param targetCol
	 * @param shouldMapped
	 * @return
	 */
	public boolean verifyMappingTable(Map map,
			ScrollGuiSubitemTestObject table, int sourceCol, int targetCol,
			boolean shouldMapped) {
		boolean rst = shouldMapped;
		ITable t = ewFactory.createTable(table);
		for (Iterator iter = map.entrySet().iterator(); iter.hasNext();) {
			Map.Entry me = (Map.Entry) iter.next();
			rst = t.doesMapExist((String) me.getKey(), (String) me.getValue(),
					sourceCol, targetCol);
			if (rst != shouldMapped) {
				break;
			}
		}
		return vpManual("VerifyMapTable", Boolean.valueOf(shouldMapped),
				Boolean.valueOf(rst)).performTest();
	}

	/**
	 * Verify mapping existance table in parameters view with specified table,
	 * source item, target item, source item column and target item column
	 * 
	 * @param sourceItem
	 * @param targetItem
	 * @param table
	 * @param sourceCol
	 * @param targetCol
	 * @param shouldMapped
	 * @return
	 */
	public boolean verifyMappingTable(String sourceItem, String targetItem,
			ScrollGuiSubitemTestObject table, int sourceCol, int targetCol,
			boolean shouldMapped) {
		boolean exist = ewFactory.createTable(table).doesMapExist(sourceItem,
				targetItem, sourceCol, targetCol);
		return vpManual("VerifyMapTable", Boolean.valueOf(shouldMapped),
				Boolean.valueOf(exist)).performTest();
	}

	/**
	 * Get an anchor of source item
	 * 
	 * @param itemPath
	 * @return
	 */
	public TestObject getSourceAnchor(String itemPath) {
		return getAnchor(sTreeDec, itemPath);
	}

	/**
	 * Get an anchor of target item
	 * 
	 * @param itemPath
	 * @return
	 */
	public TestObject getTargetAnchor(String itemPath) {
		return getAnchor(tTreeDec, itemPath);
	}

	/**
	 * Get a connection associated with given anchor
	 * 
	 * @param anchor
	 * @return
	 */
	public TestObject getConn(TestObject anchor) {
		Vector v = (Vector) anchor.invoke("getConnections");
		return v == null ? null : (TestObject) v.get(0);
	}

	/**
	 * Caculate the click point on an anchor
	 * 
	 * @param anchor
	 * @return
	 */
	public Point calcAnchorClickPoint(TestObject anchor) {
		return FigureHelper.getCenter(getAnchorDecora(anchor));
	}

	/**
	 * Implement map one entry in map
	 * 
	 * @param mapEntry
	 * @param delExisting
	 */
	private void map(Map.Entry mapEntry, boolean delExisting) {
		String sourceItem = (String) mapEntry.getKey();
		String targetItem = (String) mapEntry.getValue();
		map(sourceItem, targetItem, delExisting);
	}

	/**
	 * Get an anchor with specified tree figure decoration and item path
	 * 
	 * @param treeFigureDecora
	 * @param nodePath
	 * @return
	 */
	private TestObject getAnchor(TreeFigureDecoration treeFigureDecora,
			String nodePath) {
		int itemHeightCenter = getItemHeightCenter(treeFigureDecora, nodePath);
		TestObject anchor = findAnchorInList(itemHeightCenter, treeFigureDecora
				.getAnchorVector());
		if (anchor == null) {
			throw new RuntimeException(
					"MappingHelper.getAnchor: anchor not found");
		}
		return anchor;
	}

	/**
	 * Get the specified anchor for map action using, find the item's path with
	 * method from tree widget
	 * 
	 * @param sourceItem
	 * @param targetItem
	 * @return a TestObject array contains 2 elements, first is source anchor,
	 *         second is target anchor
	 */
	private TestObject[] getMapSTAnchors(String sourceItem, String targetItem) {
//		String sourcePath = sTreeDec.getTreeWidget().findFirstLeafNodePath(
//				makeItemNodeRegex(sourceItem));
//		String targetPath = tTreeDec.getTreeWidget().findFirstLeafNodePath(
//				makeItemNodeRegex(targetItem));
//		if (sourcePath == null || targetPath == null) {
//			throw new RuntimeException(
//					"MappingHelper.map: path not found with specified item");
//		}
		
		String sourcePath = sourceItem; 
		String targetPath = targetItem;
		
		
		TestObject[] anchors = new TestObject[2];
		anchors[0] = getSourceAnchor(sourcePath);
		anchors[1] = getTargetAnchor(targetPath);
		return anchors;
	}

	/**
	 * Make the regular expression for item path finding
	 * 
	 * @param itemName
	 * @return
	 */
	private String makeItemNodeRegex(String itemName) {
		return itemName + "\\[.+\\]";
	}

	/**
	 * Deal with mapped items on map figure
	 * 
	 * @param sAnchor
	 * @param tAnchor
	 * @param delExisting
	 */
	private void dealMapped(TestObject sAnchor, TestObject tAnchor,
			boolean delExisting) {
		TestObject sConn = getConn(sAnchor);
		TestObject tConn = getConn(tAnchor);
		if (sConn != null && tConn != null) {
			dealBothExisting(sAnchor, tAnchor, delExisting);
		} else if (sConn != null) {
			dealOneExisting(sAnchor, delExisting, true);
		} else if (tConn != null) {
			dealOneExisting(tAnchor, delExisting, false);
		} else {
			return;
		}
	}

	/**
	 * When both of items has mapping
	 * 
	 * @param sAnchor
	 * @param tAnchor
	 * @param delExisting
	 */
	private void dealBothExisting(TestObject sAnchor, TestObject tAnchor,
			boolean delExisting) {
		if (hasSameConn(sAnchor, tAnchor) && !delExisting) {
			return;
		}
		dealOneExisting(sAnchor, delExisting, true);
		dealOneExisting(tAnchor, delExisting, false);
	}

	/**
	 * When one item has mapping
	 * 
	 * @param anchor
	 * @param delExisting
	 * @param isSource
	 */
	private void dealOneExisting(TestObject anchor, boolean delExisting,
			boolean isSource) {
		if (!delExisting) {
			throw new RuntimeException("MappingHelper.map: specified "
					+ (isSource ? "source" : "target")
					+ " node already mapped!");
		}
		doUnMap(anchor, isSource);
	}

	/**
	 * Judge whether two anchor has same connection mapped, means they are
	 * mapped together
	 * 
	 * @param sAnchor
	 * @param tAnchor
	 * @return
	 */
	private boolean hasSameConn(TestObject sAnchor, TestObject tAnchor) {
		ConnectionDecoration sConnDecora = new ConnectionDecoration(
				getConn(sAnchor));
		ConnectionDecoration tConnDecora = new ConnectionDecoration(
				getConn(tAnchor));
		Point sp = sConnDecora.getSourceClickPoint();
		Point tp = tConnDecora.getSourceClickPoint();
		boolean b = sp.x == tp.x && sp.y == tp.y;
		return b;
	}

	/**
	 * Get decoration of an anchor with reflect
	 * 
	 * @param anchor
	 * @return
	 */
	private TestObject getAnchorDecora(TestObject anchor) {
		return (TestObject) anchor.invoke("getDecoration");
	}

	/**
	 * Find an anchor in anchor list with height paramter
	 * 
	 * @param itemHeightCenter
	 * @param sourceAnchors
	 * @return
	 */
	private TestObject findAnchorInList(int itemHeightCenter,
			Vector sourceAnchors) {
		for (int i = 0; i < sourceAnchors.size(); i++) {
			TestObject candidate = (TestObject) sourceAnchors.get(i);
			TestObject candiDecorate = getAnchorDecora(candidate);
			Rectangle bounds = FigureHelper.getBounds(candiDecorate);
			if (bounds.y <= itemHeightCenter
					&& (bounds.y + bounds.height) >= itemHeightCenter) {
				return candidate;
			}
		}
		return null;
	}

	/**
	 * Move an item to be completely visiable, and get its center point's height
	 * 
	 * @param treeFigureDecora
	 * @param itemPath
	 * @return
	 */
	private int getItemHeightCenter(TreeFigureDecoration treeFigureDecora,
			String itemPath) {
		ScrollGuiSubitemTestObject tree = treeFigureDecora.getTree();
		tree.click(atPath(itemPath));
		Rectangle treeFigureBound = FigureHelper.getBounds(treeFigureDecora
				.getTreeFigureObj());
		Rectangle treeBound = FigureHelper.getBounds(tree);
		TestObject item = (TestObject) tree.getSubitem(atPath(itemPath));
		Rectangle itemBound = FigureHelper.getBounds(item);
		if (itemBound.y < itemBound.height) {
			tree.scrollLineUp();
		}
		item = (TestObject) tree.getSubitem(atPath(itemPath));
		itemBound = FigureHelper.getBounds(item);
		return treeFigureBound.y + treeBound.y + itemBound.y
				+ (itemBound.height / 2);
	}

	/**
	 * Judge whether a tree figure is source or target
	 * 
	 * @param treeFigObj
	 * @return
	 */
	private boolean isSourceTreeFigure(TestObject treeFigObj) {
		Object o = treeFigObj.invoke("getSourceAnchors");
		return o != null;
	}

	/**
	 * Decorate the connection object
	 * 
	 * @author dongxu
	 * 
	 */
	public class ConnectionDecoration {

		public static final int MOVE_AMOUNT = 3;

		private TestObject conn;

		private Point startPoint;

		private Point endPoint;

		public ConnectionDecoration(TestObject conn) {
			if (conn == null) {
				throw new RuntimeException(
						"MappingHelper.ConnectionDecoration.constructor: connection is empty");
			}
			this.conn = conn;
			startPoint = FigureHelper.getPoint(conn, "getStart");
			endPoint = FigureHelper.getPoint(conn, "getEnd");
		}

		public TestObject getConn() {
			return conn;
		}

		public Point getSourceClickPoint() {
			int yIncre = new Float(
					(float) ((float) (endPoint.y - startPoint.y) / (float) (endPoint.x - startPoint.x))
							* (float) MOVE_AMOUNT).intValue();
			return new Point(startPoint.x + MOVE_AMOUNT, startPoint.y + yIncre);
		}

		public Point getTargetClickPoint() {
			int yIncre = new Float(
					(float) ((float) (endPoint.y - startPoint.y) / (float) (endPoint.x - startPoint.x))
							* (float) (-1 * MOVE_AMOUNT)).intValue();
			return new Point(endPoint.x + (-1 * MOVE_AMOUNT), endPoint.y
					+ yIncre);
		}
	}

	/**
	 * Decorate tree figure object
	 * 
	 * @author dongxu
	 * 
	 */
	public class TreeFigureDecoration {

		private TestObject treeFigureObj;

		private String getVectorMethod;

		private boolean isSource;

		private ITree treeWidget;

		public TreeFigureDecoration(TestObject treeFigure, boolean isSource) {
			if (treeFigure == null) {
				throw new RuntimeException(
						"MappingHelper.TreeFigureDecoration.constructor: tree figure is empty");
			}
			treeFigureObj = treeFigure;
			getVectorMethod = isSource ? "getSourceAnchors"
					: "getTargetAnchors";
			treeWidget = ewFactory.createTree(getTree());
		}

		public Vector getAnchorVector() {
			return (Vector) treeFigureObj.invoke(getVectorMethod);
		}

		public boolean isSource() {
			return isSource;
		}

		public TestObject getTreeFigureObj() {
			return treeFigureObj;
		}

		public ScrollGuiSubitemTestObject getTree() {
			return (ScrollGuiSubitemTestObject) treeFigureObj.invoke("getTree");
		}

		public ITree getTreeWidget() {
			return treeWidget;
		}
	}
}