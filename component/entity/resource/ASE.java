package component.entity.resource;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;

public class ASE extends AbstractDBResource {
	@Override
	public boolean isActive() {
		try {
			Class.forName(driverClass());
			Connection conn = DriverManager.getConnection(
					connectionString(), getProperty("login").toString(), getProperty("password").toString());
			Statement stmt = conn.createStatement();
			ResultSet rs = stmt.executeQuery("select name from sysobjects");
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
		return "jdbc:sybase:Tds:"+getProperty("host")+":"+getProperty("port")+"/"+getProperty("dbName");
	}

	@Override
	public String driverClass() {
		return "com.sybase.jdbc3.jdbc.SybDriver";
	}

}
