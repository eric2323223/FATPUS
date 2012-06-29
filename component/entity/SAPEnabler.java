package component.entity;

import java.io.IOException;

import com.sybase.automation.framework.common.FileUtil;

public class SAPEnabler {
	private String SAPJar = GlobalConfig.RFT_PROJECT_LIB+"\\sapjco.jar";
	
	private String[] sapDlls(){
		return new String[]{GlobalConfig.RFT_PROJECT_LIB+"\\sapjcorfc.dll", 
				GlobalConfig.RFT_PROJECT_LIB+"\\librfc32.dll"};
	}
	
	private String[] dependantDlls(){
		return new String[]{GlobalConfig.RFT_PROJECT_LIB+"\\msvcp71.dll", 
				GlobalConfig.RFT_PROJECT_LIB+"\\msvcr71.dll"};
	}
		
	
	public void copySapLibraries() throws IOException{
		copyJarToETLibFolder();
		copyJarToServerLibFolder();
		copyDllToSupJREBinFolder();
		copyDependantDllToSupJREFolder();
		copyDllToWindowsSystemFolder();
		copyDependantDllToWindowsSystemFolder();
		System.out.print("Done");
	}
	
	private void copyDependantDllToWindowsSystemFolder()  {
		for(String dll:dependantDlls()){
			try{
				FileUtil.copyToFolder(dll, GlobalConfig.WINDOWS_SYSTEM);
			}catch(IOException e){
				System.out.println("Failed to copy file ["+dll+"] due the reason:\n\t"+e.getCause().getMessage() );
			}
		}
	}

	private void copyDependantDllToSupJREFolder()  {
		for(String dll:dependantDlls()){
			try{
				FileUtil.copyToFolder(dll, GlobalConfig.SUP_JRE_BIN);
			}catch(IOException e){
				System.out.println("Failed to copy file ["+dll+"] due the reason:\n\t"+e.getCause().getMessage() );
			}
		}
	}

	private void copyDllToWindowsSystemFolder()  {
		for(String dll:sapDlls()){
			try{
				FileUtil.copyToFolder(dll, GlobalConfig.WINDOWS_SYSTEM);
			}catch(IOException e){
				System.out.println("Failed to copy file ["+dll+"] due the reason:\n\t"+e.getCause().getMessage() );
			}
		}
	}

	private void copyDllToSupJREBinFolder() {
		for(String dll:sapDlls()){
			try{
				FileUtil.copyToFolder(dll, GlobalConfig.SUP_JRE_BIN);
			}catch(IOException e){
				System.out.println("Failed to copy file ["+dll+"] due the reason:\n\t"+e.getCause().getMessage() );
			}
		}
		
		
	}

	private void copyJarToServerLibFolder() throws IOException {
		FileUtil.copyToFolder(SAPJar, GlobalConfig.SUP_SERVER_UNWIREDSERVER_LIB+"\\3rdparty");
	}

	private void copyJarToETLibFolder() throws IOException {
		FileUtil.copyToFolder(SAPJar, GlobalConfig.SUP_TOOLING_SAP_PLUGIN+"\\lib");
	}

	public static void main(String[]args) throws IOException{
		new SAPEnabler().copySapLibraries();
		
	}
}
