package component.entity;

import java.io.FileNotFoundException;
import java.io.IOException;

import com.rational.test.ft.script.RationalTestScript;
import com.sybase.automation.framework.common.CmdUtil;
import com.sybase.automation.framework.common.ZipOperate;
import component.entity.model.CGOption;

public class CodeGenCLI extends RationalTestScript{
	public static final String cmd = GlobalConfig.SUP_SERVER_ROOT+"\\UnwiredServer\\bin\\codegen.bat";
	public static final String outputPath = GlobalConfig.CODEGENCLI_OUTPUT_ROOT;
	
	
	public static void generateCode(CGOption cgOption) {
		String command = "\""+cmd+"\""+" -ulj -javac -client -md -rm -output "+outputPath+" "+duPath(cgOption.sp());
		
		System.out.println(command);
		System.out.println(CmdUtil.executeBatchFile(command));
		createJar(cgOption.sp());
	}
	
	private static void createJar(String project)  {
		try {
			new ZipOperate().jar(MainMenu.getCurrentWorkspace()+"\\"+project+".jar", outputPath+"\\genfiles\\java\\classes");
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	private static String duPath(String project){
		return "\""+MainMenu.getCurrentWorkspace()+"\\"+project+"\\deployment_unit.xml\"";
	}
	
	public static void main(String[] args){
		generateCode(new CGOption());
	}

}
