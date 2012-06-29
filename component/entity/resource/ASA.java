package component.entity.resource;

import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;

import component.entity.GlobalConfig;

public class ASA extends AbstractDBResource implements IDBResource{

	@Override
	public boolean isActive() {
		try {
			Class.forName(driverClass());
			Connection conn = DriverManager.getConnection(
					connectionString(), getProperty("login").toString(), getProperty("password").toString());
			Statement stmt = conn.createStatement();
			ResultSet rs = stmt.executeQuery("SELECT * FROM sys.syscatalog");
			if (rs != null) {
				return true;
			}
		} catch (Exception e) {
			e.printStackTrace();
		} 
		return false;
	}
	
	public String connectionString(){
		return "jdbc:sybase:Tds:"+getProperty("host")+":"+getProperty("port");
	}

	@Override
	public String driverClass() {
		return "com.sybase.jdbc3.jdbc.SybDriver";
	}


}
