package component.entity;

import java.io.File;
import java.net.InetAddress;
import java.net.UnknownHostException;

import com.sybase.automation.framework.common.CmdUtil;
import com.sybase.automation.framework.common.RegistryUtil;

public class GlobalConfig {
	public static final String RFT_ROOT = getRFTRoot();
	public static  String RFT_PROJECT_ROOT = getRFTProjectRoot();
	public static final String RFT_PROJECT_LIB = RFT_PROJECT_ROOT+"\\lib";
	public static  String SUP_ROOT = getSupRoot();
	public static final String SUP_SERVER_ROOT = SUP_ROOT+"\\Servers";
	public static final String SUP_SERVER_UNWIREDSERVER_LIB = SUP_SERVER_ROOT+"\\UnwiredServer\\lib";
	public static final String SUP_TOOLING_ROOT = SUP_ROOT+"\\MobileSDK22\\Unwired_WorkSpace\\Eclipse\\sybase_workspace\\mobile\\eclipse\\plugins";
	public static final String SUP_JDK = SUP_ROOT+"\\"+getSupJDK();
	public static final String SUP_JRE_BIN = SUP_JDK+"\\jre\\bin";
	public static final String SUP_TOOLING_SAP_PLUGIN = SUP_TOOLING_ROOT+"\\"+getSAPPlugin();
	public static final String CDB_ROOT = SUP_ROOT + "\\Servers\\UnwiredServer\\deploy\\sup";
	public static final String WINDOWS_SYSTEM = "C:\\windows\\system32";
	public static final String androidJar = RFT_PROJECT_LIB+"\\android.jar";
	public static final String CODEGENCLI_OUTPUT_ROOT = "c:\\";
	public static final String CODEGENCLI_OUTPUT_BIN_ROOT = CODEGENCLI_OUTPUT_ROOT+"genfiles\\java\\classes";
	public static final String HOST_NAME=getHostName();
	public static final String IP_ADDRESS=getIpAddress();
//	public static final String STD_JDK_PATH = "C:\\Program Files\\Java\\jdk1.6.0_31";
	public static final String STD_JDK_PATH = "C:\\Sybase\\UnwiredPlatform\\MobileSDK22\\JDK1.6.0_31";
	public static final String SUP_TOOLING_WORKSPACE = MainMenu.getCurrentWorkspace();
	
	
	private static final String getSupRoot(){
		return RegistryUtil.readRegistry("HKLM\\Software\\SYBASE\\MobileSDK\\2.2", "RootLocation").trim();
	}
	
	private static String getHostName() {
//		return "autotest_xp32en";
		try {
			return InetAddress.getLocalHost().getHostName();
		} catch (UnknownHostException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return null;
		}
	}
	
	private static String getIpAddress() {
		try {
			return InetAddress.getLocalHost().getHostAddress();
		} catch (UnknownHostException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return null;
		}
	}

	private static final String getRFTRoot(){
		return RegistryUtil.readRegistry("HKLM\\Software\\Rational Software\\Rational Test\\8", "Eclipse Directory").trim();
	}
	
	private static final String getSAPPlugin() {
		String [] files = new File(SUP_TOOLING_ROOT).list();
		for(String file:files){
			if(file.startsWith("com.sybase.uep.com.sap")){
				return file;
			}
		}
		return null;
	}

	private static final String getSupJDK() {
		String [] files = new File(SUP_ROOT).list();
		for(String file:files){
			if(file.startsWith("JDK")){
				return file;
			}
		}
		return null;
	}

	public static String getRFTProjectRoot(){
//		return "C:\\Documents and Settings\\eric\\IBM\\rationalsdp\\workspace16\\UEP_ET";
		String pwd = new File(".").getAbsolutePath();
		if(pwd.endsWith("UEP_ET")){
			return pwd.substring(0,pwd.length()-2);
		}else{
			return pwd.substring(0, pwd.indexOf("UEP_ET")+6);
		}
	}
	
	public static void main(String[] args){
		System.out.println(GlobalConfig.RFT_PROJECT_ROOT);
		System.out.println(GlobalConfig.RFT_PROJECT_LIB);
	}

}
