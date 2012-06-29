package component.entity;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import org.dom4j.Document;
import org.dom4j.DocumentException;
import org.dom4j.Element;
import org.dom4j.Node;

import com.sybase.automation.framework.common.FileUtil;
import com.sybase.automation.framework.common.XMLUtil;

import component.entity.model.DUComplexType;
import component.entity.model.DUMbo;

public class DUHelper {

	public static List<DUMbo> getMbos(File file) {
		try {
			Document document = XMLUtil.readFile(file);
			List<Node> list = document.selectNodes("//package/entity");
			List<DUMbo> result = new ArrayList<DUMbo>();
			for (Node item : list) {
				result.add(parseMbo(document, item.valueOf("@name")));
			}
			return result;
		} catch (DocumentException e) {
			e.printStackTrace();
			throw new RuntimeException("Failed to parse DU xml file");
		}
	}

	public static DUMbo getMbo(File file, String name) {
		List<DUMbo> allMbo;
		allMbo = getMbos(file);
		for (DUMbo mbo : allMbo) {
			if (mbo.getName().equals(name)) {
				return mbo;
			}
		}
		return null;
	}

	private static DUMbo parseMbo(Document dom, String name) {
		DUMbo mbo = new DUMbo();
		mbo.setName(name);
		List<Node> attributes = dom.selectNodes("//package/entity[@name=\""
				+ name + "\"]/attribute");
		for (Node node : attributes) {
			mbo.addAttribute(node.valueOf("@name"));
		}
		List<Node> operations = dom.selectNodes("//package/entity[@name=\""
				+ name + "\"]/operation");
		for (Node node : operations) {
			mbo.addOperation(node.valueOf("@name"));
		}
		List<Node> indexes = dom.selectNodes("//package/entity[@name=\"" + name
				+ "\"]/index");
		for (Node node : indexes) {
			mbo.addIndex(node.valueOf("@name"));
		}
//		List<Node> clazz = dom.selectNodes("//package/class[@name=\"" + name
//				+ "\"]/attribute[@name=\""+name2+"\"]/");
//		for (Node node : indexes) {
//			mbo.addIndex(node.valueOf("@name"));
//		}
		return mbo;
	}

	public static List<String> getConnectionProfile(File file) {
		try {
			Document document = XMLUtil.readFile(file);
			List<Node> list = document
					.selectNodes("//package/connection-profile");
			List<String> result = new ArrayList<String>();
			for (Node item : list) {
				result.add(item.valueOf("@name"));
			}
			return result;
		} catch (DocumentException e) {
			e.printStackTrace();
			throw new RuntimeException("Failed to parse DU xml file");
		}
	}
	
	public static DUComplexType getComplexType(File file, String name){
		try {
			DUComplexType type = new DUComplexType(name);
			Document document = XMLUtil.readFile(file);
			List<Node> list = document.selectNodes("//package/class[@name=\""
					+ name + "\"]/attribute");
			for (Node item : list) {
				type.setSubType(item.valueOf("@name") + ","
						+ item.valueOf("@type") + ","
						+ item.valueOf("@max-length"));
			}
			return type;
		} catch (DocumentException e) {
			e.printStackTrace();
			throw new RuntimeException("Failed to parse DU xml file");
		}
		
	}

	public static List<String> getDatabase(File file) {
		try {
			Document document = XMLUtil.readFile(file);
			List<Node> list = document.selectNodes("//package/database");
			List<String> result = new ArrayList<String>();
			for (Node item : list) {
				result.add(item.valueOf("@name"));
			}
			return result;
		} catch (DocumentException e) {
			e.printStackTrace();
			throw new RuntimeException("Failed to parse DU xml file");
		}
	}

	// private static File removeNamespaceTag(File xmlfile){
	// String content = FileUtil.readFromFile(xmlfile);
	// content = content.replaceAll("xmlns=\".*\"", "");
	// File result = new File("c:\\tripedoff.xml");
	// FileUtil.writeToFile(result, content);
	// return result;
	// }

}
