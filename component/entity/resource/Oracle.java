package component.entity.resource;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;

public class Oracle extends AbstractDBResource {
	@Override
	public boolean isActive() {
		try {
			Class.forName(driverClass());
			Connection conn = DriverManager.getConnection(
					connectionString(), getProperty("login").toString(), getProperty("password").toString());
			Statement stmt = conn.createStatement();
			ResultSet rs = stmt.executeQuery("select BANNER from SYS.V_$VERSION");
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
		return "jdbc:oracle:thin:@"+getProperty("host")+":"+getProperty("port")+":orcl";
	}

	@Override
	public String driverClass() {
		return "oracle.jdbc.driver.OracleDriver";
	}

}
