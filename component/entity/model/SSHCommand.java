package component.entity.model;

import component.entity.resource.AbstractDBResource;
import component.entity.resource.Criteria;
import component.entity.resource.IResource;

public class SSHCommand extends AbstractDBResource{
	String host;
	String port;
	String user;
	String password;
	String command;
	
	@Override
	public String connectionString() {
		// TODO Auto-generated method stub
		return null;
	}
	
	@Override
	public String driverClass() {
		// TODO Auto-generated method stub
		return null;
	}
	@Override
	public boolean isActive() {
		// TODO Auto-generated method stub
		return true;
	}
	
	

}
