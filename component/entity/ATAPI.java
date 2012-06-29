package component.entity;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

import com.sybase.sup.admin.client.SUPApplication;
import com.sybase.sup.admin.client.SUPObjectFactory;
import com.sybase.sup.admin.context.ClusterContext;
import com.sybase.sup.admin.context.ServerContext;
import com.sybase.sup.admin.enumeration.application.ACSF;
import com.sybase.sup.admin.enumeration.application.AC_REQ_REG;
import com.sybase.sup.admin.exception.SUPAdminException;
import component.entity.model.ServerAdminInfo;

//AT client api does not work well with RFT JRE, need to run this class with standard JRE
public class ATAPI {
	public static final String ADMIN_LOG_IN = "supAdmin";
	public static final String ADMIN_LOG_PW = "s3pAdmin";
	public static final int ADMIN_PORT = 2000;
	public static final String USER_ID = "DeviceTest";
	public static final String ACTIVATION_CODE = "123";
	public static final int EXPIRATION_HOUR = 72;

	private static SUPApplication getSUPApplication(ServerAdminInfo info) throws SUPAdminException {
		ServerContext serCont = new ServerContext(info.getClusterName(), info.getAdminPort(), info.getAdminLogIn(), info.getAdminLogPW(), false);
		ClusterContext context = serCont.getClusterContext(info.getClusterContextName());
		return SUPObjectFactory.getSUPApplication(context);
	}
	
	private static SUPApplication getSUPApplication(String server) throws SUPAdminException {
		ServerContext serCont = new ServerContext(server,2000, ADMIN_LOG_IN, ADMIN_LOG_PW, false);
		ClusterContext context = serCont.getClusterContext(server);
		return SUPObjectFactory.getSUPApplication(context);
	}
	
	public static void registerApplicationConnection(ServerAdminInfo info){
		try{
		  SUPApplication supApp = getSUPApplication(info);
		  
		  Collection<Map<AC_REQ_REG, Object>> settings=new ArrayList <Map<AC_REQ_REG, Object>>();
		  Map<AC_REQ_REG, Object> map=new HashMap<AC_REQ_REG, Object>();
		  map.put(AC_REQ_REG.USER_ID, USER_ID);
		  map.put(AC_REQ_REG.ACTIVATION_CODE, ACTIVATION_CODE);
		  map.put(AC_REQ_REG.EXPIRATION_HOUR, EXPIRATION_HOUR);
		  settings.add(map);
		  Map<ACSF, Object> arg2=supApp.getApplicationConnectionTemplateSettings("default");
		  
		  supApp.registerApplicationConnections("Default", settings, arg2);
		}catch(Exception e){
			e.printStackTrace();
		}
	}
	
	public static void registerApplicationConnection(String server){
		try{
			SUPApplication supApp = getSUPApplication(server);
			
			Collection<Map<AC_REQ_REG, Object>> settings=new ArrayList <Map<AC_REQ_REG, Object>>();
			Map<AC_REQ_REG, Object> map=new HashMap<AC_REQ_REG, Object>();
			map.put(AC_REQ_REG.USER_ID, USER_ID);
			  map.put(AC_REQ_REG.ACTIVATION_CODE, ACTIVATION_CODE);
			  map.put(AC_REQ_REG.EXPIRATION_HOUR, EXPIRATION_HOUR);
			settings.add(map);
			Map<ACSF, Object> arg2=supApp.getApplicationConnectionTemplateSettings("Default");
			
			supApp.registerApplicationConnections("Default", settings, arg2);
		}catch(Exception e){
			e.printStackTrace();
		}
	}
	
	public static void main(String [] args){
		registerApplicationConnection(GlobalConfig.HOST_NAME);
	}
	
	public static void lockWorkflow(String name){
		
	}

}
