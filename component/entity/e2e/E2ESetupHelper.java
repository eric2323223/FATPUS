package component.entity.e2e;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.regex.Pattern;

import com.rational.test.ft.script.RationalTestScript;
import com.sybase.automation.framework.common.FileUtil;
import com.sybase.automation.framework.common.ZipOperate;
import component.entity.GlobalConfig;

public class E2ESetupHelper extends RationalTestScript{
	private static String[] dlls = new String[]{"pidutil","PUtilTRU"};
	private static String[] dlls2 = new String[]{"PidUtil","PUtilTRU"};
	
	public static String setup(String project) throws IOException{
		copyDll();
		loadDll();
		return createJarFile(project);
 
	}
	
	public static String createJarFile(String project) throws FileNotFoundException, IOException{
		String jarFile;
		if(isGUIGeneratedCode(project)){
			jarFile = E2ECfg.SybaseWorkSpace + "\\" +project+"\\"+project+".jar";
			new ZipOperate().jar(jarFile, E2ECfg.SybaseWorkSpace+"\\"+project+"\\bin");
		}else{
			jarFile = E2ECfg.SybaseWorkSpace+"\\"+project+".jar";
			new ZipOperate().jar(jarFile, GlobalConfig.CODEGENCLI_OUTPUT_BIN_ROOT);
		}
		return jarFile;
	}
	
	public static boolean isGUIGeneratedCode(String project){
		File folder = new File(E2ECfg.SybaseWorkSpace+"\\"+project+"\\bin");
		if(folder.exists()&&folder.list().length==1){
			return true;
		}else{
			return false;
		}
	}
	
	public static void copyDll() throws IOException{
		String toolingApiFolderName = FileUtil.getFileName(E2ECfg.SybaseInstallationPath + "\\Unwired_WorkSpace\\Eclipse\\sybase_workspace\\mobile\\eclipse\\plugins",Pattern.compile("com\\.sybase\\.uep\\.tooling\\.api_.*"));
		String sybaseDllPath = E2ECfg.SybaseInstallationPath + "\\Unwired_WorkSpace\\Eclipse\\sybase_workspace\\mobile\\eclipse\\plugins\\"+toolingApiFolderName+"\\lib\\client\\RBS\\Win32\\java\\";
//		String sybaseDllPath = E2ECfg.SybaseInstallationPath + "\\ClientAPI\\RBS\\Win32\\cs\\";
//		String sybaseDllPath = E2ECfg.SybaseInstallationPath + "\\ClientAPI\\DeviceID\\Win32\\";

//		String rftDllPath = E2ECfg.RFTInstallationPath +"\\jdk\\jre\\bin\\";
		String rftDllPath = GlobalConfig.STD_JDK_PATH +"\\jre\\bin\\";
		for(String dll:dlls2){
			FileUtil.copy(sybaseDllPath+dll+".dll", rftDllPath+dll+".dll");
//			File source = new File(sybaseDllPath+dll+".dll");
//			File target = new File(rftDllPath+dll+".dll");
//			while(!target.exists()){
//			FileChannel in = (new FileInputStream(source)).getChannel();
//		    FileChannel out = (new FileOutputStream(target)).getChannel();
//		    in.transferTo(0, source.length(), out);
//		    in.close();
//		    out.close();
//			}
		}
	}

	public static void loadDll(){
		for(String dll:dlls2){
			System.loadLibrary(dll);
		}
	}
	
	public static void main(String[] args){
		System.loadLibrary("pidUtil");
	}

}
