package com.sybase.automation.framework.common;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.HashMap;
import java.util.List;

import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.handlers.MapListHandler;
import component.entity.resource.ASA;
import component.entity.resource.Criteria;
import component.entity.resource.IDBResource;
import component.entity.resource.RC;


public class DBUtil {

	public static List runSQLForResult(IDBResource resource, String sql) throws SQLException, ClassNotFoundException{
		Statement statement = null;
		ResultSet resultSet = null;
		Connection conn = null;
		List resultList = null;
		try {
			Class.forName(resource.getProperty("driverClass").toString());
			conn = DriverManager.getConnection(
					resource.connectionString(), resource.getProperty("login").toString(), resource.getProperty("password").toString());
			Statement stmt = conn.createStatement();
			ResultSet rs = stmt.executeQuery(sql);
			QueryRunner runner = new QueryRunner();
			resultList = (List)runner.query(conn, sql, new MapListHandler());
		} finally {
			try {
				if (resultSet != null) {
					resultSet.close();
				}
			} finally {
				try {
					if (statement != null) {
						statement.close();
					}
				} finally {
					if(conn!=null){
						conn.close();
					}
				}
			}
		}
		return resultList;
	}
	
	public static void runSQL(IDBResource resource, String sql) throws SQLException, ClassNotFoundException{
		Statement statement = null;
		ResultSet resultSet = null;
		Connection conn = null;
		try {
			Class.forName(resource.getProperty("driverClass").toString());
			conn = DriverManager.getConnection(
					resource.connectionString(), resource.getProperty("login").toString(), resource.getProperty("password").toString());
			Statement stmt = conn.createStatement();
			stmt.executeUpdate(sql);
			System.out.println("updated!!!!!!!!!!!!!!!");
		} finally {
			try {
				if (resultSet != null) {
					resultSet.close();
				}
			} finally {
				try {
					if (statement != null) {
						statement.close();
					}
				} finally {
					if(conn!=null){
						conn.close();
					}
				}
			}
		}
	}

	public static void main(String[]args) {
		
		String DB2Host = "WKSP1W64";
		String DB2Port = "50000";
		String DB2DBName = "UEP_TMDB";
		String DB2User = "jagadmin";
		String DB2Pwd = "jaguar2000";
		
		System.out.println("started...");
		
		BufferedReader reader = null;
		try {
			String testSuite = "";
			String startTime = "";
			String endTime = "";
			String productStr = "";
			String versionStr = "";
			String total = "";
			String pass = "";
			String failure = "";
			String error = "";
			
			reader = new BufferedReader(new FileReader(new File(args[0])));
			String tempString = null;
			while ((tempString = reader.readLine()) != null) {
				if (tempString.startsWith("Testsuite:")) {
					testSuite = tempString.replace("Testsuite:", "").trim();
				}
				if (tempString.startsWith("StartTime:")) {
					startTime = tempString.replace("StartTime:", "").trim();
				}
				if (tempString.startsWith("EndTime:")) {
					endTime = tempString.replace("EndTime:", "").trim();
				}
				if (tempString.startsWith("Product:")) {
					productStr = tempString.replace("Product:", "").trim();
				}
				if (tempString.startsWith("Version:")) {
					versionStr = tempString.replace("Version:", "").trim();
				}
				if (tempString.startsWith("Total:")) {
					total = tempString.replace("Total:", "").trim();
				}
				if (tempString.startsWith("Pass:")) {
					pass = tempString.replace("Pass:", "").trim();
				}
				if (tempString.startsWith("Failure:")) {
					failure = tempString.replace("Failure:", "").trim();
				}
				if (tempString.startsWith("Error:")) {
					error = tempString.replace("Error:", "").trim();
				}
			}
			
			// load the DB2 Driver
			Class.forName("com.ibm.db2.jcc.DB2Driver");
			// establish a connection to DB2
			Connection DB2Conn = DriverManager.getConnection("jdbc:db2j:net://" + DB2Host + ":" + DB2Port + "/" + DB2DBName, DB2User, DB2Pwd);
			DB2Conn.setAutoCommit(false);
			
			// use a statement to gather data from the database
			Statement DB2st = DB2Conn.createStatement();
			
			// insert data to table: SUPRTQA.AutomationRunResult
			String insertData = "INSERT INTO SUPRTQA.AutomationRunResult " +
			"(product,version,testsuite,total,pass,failure,error,starttime,endtime) " +
			"VALUES ('" + productStr + "','" + versionStr + "','"+ 
			testSuite + "'," + total + "," + pass + "," + failure + "," + error + ",'" +
			startTime + "','" + endTime + "')";
			
			System.out.println(insertData);
			
			// execute the query
			int iRlt = DB2st.executeUpdate(insertData);
			
			System.out.println("insertion completed");
			DB2st.close();
			DB2Conn.commit();
			DB2Conn.close();
			reader.close();
		} catch (ClassNotFoundException cnfe) {
			cnfe.printStackTrace();
		} catch (SQLException sqle) {
			sqle.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			System.out.println("show me something");
			if (reader != null) {
				try {
					reader.close();
				} catch (IOException e1) {
					e1.printStackTrace();
				}
			}
		}
	}
	 //fanfei add used to find the count the assigned record in DB>>>>>>>>>>20120522
	public static int getDB(String sql){
		try {
			ASA db = new ASA();
			db.setProperty("host", "localhost");
			db.setProperty("login", "dba");
			db.setProperty("password", "sql");
			db.setProperty("port", "5500");
			db.setProperty("driverClass", "com.sybase.jdbc3.jdbc.SybDriver");
			List result = DBUtil.runSQLForResult((IDBResource) db, sql);
			System.out.println(result.size());
			return result.size();
		} catch (Exception e) {
			e.printStackTrace();
			throw new RuntimeException("Failed to get record data from DB");
			}
	}
//	<<<<<<<<<<<<<<<<
	
}
