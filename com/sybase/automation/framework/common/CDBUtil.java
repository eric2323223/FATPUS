package com.sybase.automation.framework.common;

import java.sql.SQLException;
import java.util.List;
import java.util.Map;
import java.util.regex.Pattern;

import org.dom4j.Document;

import component.entity.GlobalConfig;
import component.entity.resource.ASA;
import component.entity.resource.Criteria;
import component.entity.resource.IDBResource;
import component.entity.resource.IResource;
import component.entity.resource.RC;

public class CDBUtil {

	public static String getTableName(String project, String mbo) {
		String cdbFolder = GlobalConfig.CDB_ROOT;
		Pattern pattern = Pattern.compile("d1_"+project.toLowerCase()+"\\..*");
		String projectFolder = FileUtil.getChild(cdbFolder, pattern);
		return projectFolder.replace(".","_")+"_"+mbo.toLowerCase();
//		String folder = cdbFolder+"\\"+projectFolder;
//		Pattern filePattern = Pattern.compile("d1_"+project.toLowerCase()+"_.*_"+mbo.toLowerCase().substring(0,5)+"_.*");
//		String file = FileUtil.getChild(folder, filePattern);
//		return file.substring(0, file.lastIndexOf("_"));
	}
	
	public static int getRecordCount(String project, String mbo){
		try {
			String tableName = CDBUtil.getTableName(project, mbo);
			String sql = "select * from "+tableName;
			List result = DBUtil.runSQLForResult((IDBResource) RC.getResource(ASA.class, new Criteria().equal("name", "local_cdb") ), sql);
			return result.size();
		} catch (Exception e) {
			e.printStackTrace();
			throw new RuntimeException("Failed to get record data from CDB");
		} 
	}
	
	public static int getRecordCount(String hostName, String project, String mbo){
		try {
			ASA cdb = new ASA();
			cdb.setProperty("host", hostName);
			cdb.setProperty("login", "dba");
			cdb.setProperty("password", "sql");
			cdb.setProperty("port", "5200");
			cdb.setProperty("driverClass", "com.sybase.jdbc3.jdbc.SybDriver");
//			List<Map<String,String>> list = DBUtil.runSQLForResult(cdb, "SELECT tname FROM sys.syscatalog where tabletype='TABLE' and creator='DBA' and tname like '%wf%department%' ");
			List<Map<String,String>> list = DBUtil.runSQLForResult(cdb, "SELECT tname FROM sys.syscatalog where tabletype='TABLE' and creator='DBA' and tname like '%"+project+"%"+mbo+"%' ");
			String tableName = list.get(0).get("tname");
			String sql = "select * from "+tableName;
			List result = DBUtil.runSQLForResult((IDBResource) cdb, sql);
			return result.size();
		} catch (Exception e) {
			e.printStackTrace();
			throw new RuntimeException("Failed to get record data from CDB");
		} 
	}
	
	public static int getRecordCount(String hostName, String project, String mbo, List<String> whereClauses){
		try {
			ASA cdb = new ASA();
			cdb.setProperty("host", hostName);
			cdb.setProperty("login", "dba");
			cdb.setProperty("password", "sql");
			cdb.setProperty("port", "5200");
			cdb.setProperty("driverClass", "com.sybase.jdbc3.jdbc.SybDriver");
			List<Map<String,String>> list = DBUtil.runSQLForResult(cdb, "SELECT tname FROM sys.syscatalog where tabletype='TABLE' and creator='DBA' and tname like '%"+project+"%"+mbo+"%' ");
			String tableName = list.get(0).get("tname");
			String sql = "select * from "+tableName.trim() + " where ";
			for(String clause:whereClauses){
				sql = sql + clause + " and ";
			}
			sql = sql.substring(0, sql.length()- 4);
			System.out.println(sql);
			List result = DBUtil.runSQLForResult((IDBResource) cdb, sql);
			return result.size();
		} catch (Exception e) {
			e.printStackTrace();
			throw new RuntimeException("Failed to get record data from CDB");
		} 
	}
	
	public static int getRecordCount(String hostName, String project, String mbo, QueryCriteria whereClauses){
		try {
			ASA cdb = new ASA();
			cdb.setProperty("host", hostName);
			cdb.setProperty("login", "dba");
			cdb.setProperty("password", "sql");
			cdb.setProperty("port", "5200");
			cdb.setProperty("driverClass", "com.sybase.jdbc3.jdbc.SybDriver");
			List<Map<String,String>> list = DBUtil.runSQLForResult(cdb, "SELECT tname FROM sys.syscatalog where tabletype='TABLE' and creator='DBA' and tname like '%"+project+"%"+mbo+"%' ");
			String tableName = list.get(0).get("tname");
			String sql = "select * from "+tableName.trim() + " where ";
			for(String clause:whereClauses.getAllClauses()){
				sql = sql + clause + " and ";
			}
			sql = sql.substring(0, sql.length()- 4);
			System.out.println(sql);
			List result = DBUtil.runSQLForResult((IDBResource) cdb, sql);
			return result.size();
		} catch (Exception e) {
			e.printStackTrace();
			throw new RuntimeException("Failed to get record data from CDB");
		} 
	}
	
	public static int getRecordCount(String project, String mbo, List<String> whereClauses){
		try {
			String tableName = CDBUtil.getTableName(project, mbo);
			String sql = "select * from "+tableName + " where ";
			for(String clause:whereClauses){
				sql = sql + clause + " and ";
			}
			sql = sql.substring(0, sql.length()- 4);
			System.out.println(sql);
			List result = DBUtil.runSQLForResult((IDBResource) RC.getResource(ASA.class, new Criteria().equal("name", "local_cdb") ), sql);
			return result.size();
		} catch (Exception e) {
			e.printStackTrace();
			throw new RuntimeException("Failed to get record data from CDB");
		} 
	}
	
	
	public static void main(String [] arg){
		System.out.println(getTableName("CacheGroup", "Employee"));
	}

}
