package component.entity.resource;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import org.dom4j.Document;
import org.dom4j.DocumentException;
import org.dom4j.Element;

import com.sybase.automation.framework.common.XMLUtil;
import component.entity.GlobalConfig;
import component.entity.model.SSHCommand;

public class RC {
	private static final String resourceFile = GlobalConfig.RFT_PROJECT_ROOT+"\\component\\entity\\resource\\resources.xml";
//	private static final String resourceFile = "C:\\Documents and Settings\\eric\\IBM\\rationalsdp\\workspace16\\UEP_ET\\component\\entity\\resource\\resources.xml";

	public static IResource getResource(Class clazz){
		List<IResource> resources = loadResources();
		for(IResource resource:resources){
			if(resource.getClass().equals(clazz)&&resource.isActive()){
				return resource;
			}
		}
		throw new RuntimeException("RC can't find active "+clazz.getSimpleName());
	}

	public static List<IResource> loadResources(){
		List<IResource> allResource = new ArrayList<IResource>();
		try {
			Document document = XMLUtil.readFile(resourceFile);
			Element root = document.getRootElement();
	        for ( Iterator i = root.elementIterator(); i.hasNext(); ) {
	            Element element = (Element) i.next();
	            allResource.add(parseResource(element));
	        }
		} catch (DocumentException e) {
			e.printStackTrace();
		}
		return allResource;
	}
	
	private static IResource parseResource(Element element){
		String type = element.attributeValue("type");
		IResource resource = null;
		if(type.equals("ASA")){
			resource = new ASA();
		}
		else if(type.equals("ASE")){
			resource = new ASE();
		}
		else if(type.equals("Oracle")){
			resource = new Oracle();
		}
		else if(type.equals("MSSQL")){
			resource = new MSSQL();
		}
		else if(type.equals("DB2")){
			resource = new DB2();
		}
		else if(type.equals("SAP")){
			resource = new SAP();
		}
		else if(type.equals("WS")){
			resource = new WS();
		}
		else if(type.equals("SSHCommand")){
			resource = new SSHCommand();
		}
		else{
			throw new RuntimeException("Unknown resouce type: "+type);
		}
		for(Iterator i = element.elementIterator(); i.hasNext();){
			Element e = (Element)i.next();
//			System.out.println(resource.toString()+"\t"+e.getName()+"\t"+e.getText());
			resource.setProperty(e.getName(), e.getText());
		}
		return resource;
	}
	
	public static void main(String[] args){
		RC.loadResources();
//		ASA asa = (ASA)RC.getResource(ASA.class);
//		System.out.println(asa.getProperty("dbName"));
//		
//		ASE ase = (ASE)RC.getResource(ASE.class);
//		System.out.println(ase.getProperty("dbName"));
//		
//		Oracle oracle = (Oracle)RC.getResource(Oracle.class);
//		System.out.println(oracle.getProperty("dbName"));
		
//		DB2 db2 = (DB2)RC.getResource(DB2.class);
//		System.out.println(db2.getProperty("dbName"));
		
//		MSSQL ms = (MSSQL)RC.getResource(MSSQL.class);
//		System.out.println(ms.getProperty("dbName"));

	}

	public static IResource getResource(Class clazz, Criteria criteria) {
		List<IResource> resources = loadResources();
		for(IResource resource:resources){
			if(resource.getClass().equals(clazz)&& resource.isActive()
					&& resource.match(criteria)){
				return resource;
			}
		}
		throw new RuntimeException("RC can't find active "+clazz.getSimpleName());
	}
	
}
