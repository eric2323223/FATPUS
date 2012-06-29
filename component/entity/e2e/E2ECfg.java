package component.entity.e2e;

import com.rational.test.ft.script.RationalTestScript;
import component.entity.GlobalConfig;
import component.entity.MainMenu;

public class E2ECfg extends RationalTestScript{
	
	public static String SybaseWorkSpace ;
	public static String RFTInstallationPath;
	public static String SybaseInstallationPath ;
	
	static{
		SybaseWorkSpace = MainMenu.getCurrentWorkspace();
		RFTInstallationPath = "C:\\Program Files\\IBM\\SDP";
		SybaseInstallationPath = GlobalConfig.SUP_ROOT;
//		SybaseInstallationPath = "E:\\Sybase\\UnwiredPlatform";
	}
	
}
