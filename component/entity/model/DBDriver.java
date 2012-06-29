package component.entity.model;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import component.entity.resource.AbstractDBResource;
import component.entity.resource.IResource;

public class DBDriver extends AbstractModel{
	private String template;
	private String name;
	private String version;
	private List<String> jarList = new ArrayList<String>();
	private String connectionString;
	private String dbName;
	private String driverClass;
	private String userID;
	
	public DBDriver(AbstractDBResource resource){
		this.driverClass = resource.driverClass();
		this.connectionString = resource.connectionString();
		this.dbName = resource.getProperty("dbName").toString();
//		this.userID = resource.getProperty("userID").toString();
	}

	public DBDriver jarList(String jarfiles){
		String[] files = jarfiles.split(",");
		for(String file:files){
			jarList.add(file);
		}
		return this;
	}
	
	public DBDriver jar(String str){
		jarList.add(str);
		return this;
	}
	
	public String getDbName(){
		return this.dbName;
	}
	
	public String getDriverClass(){
		return this.driverClass;
	}
	
	public String getConnectionString(){
		return this.connectionString;
	}

	public DBDriver template(String str){
		this.template = str;
		return this;
	}
	
	public String getTemplate(){
		return this.template;
	}
	
	private List<String> splitToSegments(String str){
		List<String> segments = new ArrayList<String>();
		Pattern p = Pattern.compile("(\\[\\w+\\][^,]+)");
		Matcher m = p.matcher(str);
		while(m.find()){
//			for(int i=1;i<=m.groupCount();i++){
//				segments.add(m.group(i).trim());
//			}
			segments.add(m.group());
		}
		return segments;
	}
	
	private HashMap<String, String> parseSegments(List<String> segments){
		HashMap<String, String> hash = new HashMap<String, String>();
		for(String segment:segments){
			String str = segment.replace("[", "").replace("]", ",");
			String key = str.split(",")[0];
			String value = str.split(",")[1];
			hash.put(key, value);
		}
		return hash;
	}
	
	public DBDriver(String str){
		HashMap<String,String> pairs = parseSegments(splitToSegments(str));
		Iterator<String> it = pairs.keySet().iterator();
		while(it.hasNext()){
			String key = it.next();
			if(key.equals("name")){
				this.name = pairs.get(key);
			}
			if(key.equals("template")){
				this.template = pairs.get(key);
			}
			if(key.equals("connectionString")){
				this.connectionString = pairs.get(key);
			}
			if(key.equals("driverClass")){
				this.driverClass = pairs.get(key);
			}
			if(key.equals("dbName")){
				this.dbName = pairs.get(key);
			}
			if(key.equals("jarFiles")){
				String[] files = pairs.get(key).split("\\|");
				for(String file:files){
					this.jarList.add(file);
				}
				
			}
		}
		
//		str = str.replace("[name]", "");
//		str = str.replace("[template]", "");
//		str = str.replace("[jarFiles]", "");
//		String[] parts = str.split(",");
//		this.name = parts[0];
//		this.template = parts[1];
//		String[] files = parts[2].split("\\|");
//		for(String file:files){
//			this.jarList.add(file);
//		}
	}
	
	public DBDriver() {	}

	public String getName() {
		return name;
	}

	public String getVersion() {
		return version;
	}

	public List<String> getJarList() {
		return jarList;
	}

	public DBDriver name(String str){
		this.name = str;
		return this;
	}
	
	public DBDriver version(String str){
		this.version = str;
		return this;
	}
	
	public String toString(){
		String str = "";
		if(name!=null){
			str = str + "[name]"+name+",";
		}
		if(template!=null){
			str = str + "[template]"+template+",";
		}
		if(jarList!=null){
			str = str+"[jarFiles]";
			for(String file:jarList){
				str = str+file+"|";
			}
			str = str.substring(0, str.length()-1)+",";
		}
		if(connectionString!=null){
			str = str+"[connectionString]"+connectionString+",";
		}
		if(driverClass!=null){
			str = str+"[driverClass]"+driverClass+",";
		}
		if(dbName!=null){
			str = str+"[dbName]"+dbName;
		}
		return str.substring(0, str.length()-1);
	}
}
