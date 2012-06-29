package component.entity.resource;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;

public class DB2 extends AbstractDBResource {
	@Override
	public boolean isActive() {
		try {
			Class.forName(driverClass());
			Connection conn = DriverManager.getConnection(
					connectionString(), getProperty("login").toString(), getProperty("password").toString());
			Statement stmt = conn.createStatement();
			ResultSet rs = stmt.executeQuery("select * from SYSIBM.syschecks");
			if (rs != null) {
				conn.close();
				return true;
			}
			conn.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return false;
	}
	
	public String connectionString(){
		return "jdbc:db2://"+getProperty("host")+":"+getProperty("port")+"/"+getProperty("dbName");
	}

	@Override
	public String driverClass() {
		// TODO Auto-generated method stub
		return "com.ibm.db2.jcc.DB2Driver";
	}

}
