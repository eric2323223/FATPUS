package component.entity.resource;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;

public class MSSQL extends AbstractDBResource {
	@Override
	public boolean isActive() {
		try {
			Class.forName(driverClass());
			Connection conn = DriverManager.getConnection(
					connectionString(), getProperty("login").toString(), getProperty("password").toString());
			Statement stmt = conn.createStatement();
			ResultSet rs = stmt.executeQuery("select * from sys.all_views");
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
		return "jdbc:sqlserver://"+getProperty("host")+":"+getProperty("port")+";DatabaseName="+getProperty("dbName");
	}
	
	public String driverClass(){
		return "com.microsoft.sqlserver.jdbc.SQLServerDriver";
	}

}
