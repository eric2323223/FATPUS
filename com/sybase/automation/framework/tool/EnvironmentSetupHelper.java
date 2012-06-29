package com.sybase.automation.framework.tool;

import java.io.IOException;

import com.sybase.automation.framework.common.FileUtil;
import component.entity.GlobalConfig;

public class EnvironmentSetupHelper {
	public static void copyRFTEnabler() throws IOException{
		FileUtil.copyToFolder(GlobalConfig.RFT_ROOT+"\\FunctionalTester\\EclipseEnabler\\features", GlobalConfig.SUP_ROOT+"\\Eclipse");
		FileUtil.copyToFolder(GlobalConfig.RFT_ROOT+"\\FunctionalTester\\EclipseEnabler\\plugins", GlobalConfig.SUP_ROOT+"\\Eclipse");
	}

	public static void main(String[] args)throws IOException{
		copyRFTEnabler();
	}
}
