package component.entity.e2e;

import com.rational.test.ft.script.RationalTestScript;
import com.sybase.automation.framework.common.JarLoader;

public class DeviceDB extends RationalTestScript{
	private static String jarFile;
	private static String project;
	
	public static void init(String projectName){
		try {
			jarFile = E2ESetupHelper.setup(projectName);
			project = projectName;
			JarLoader.init(jarFile);
			DeviceMbo.setProject(project);
			DevicePK.setProject(project);
			DeviceSynchronizationParameter.setProject(project);
			
			deleteDatabase();
		} catch (Exception e) {
			e.printStackTrace();
			throw new RuntimeException("Failed to setup E2E test environment:(");
		}
	}
	
	public static void login(String login, String password){
		Object[] arg1 = new Object[] { login, password };
		JarLoader.invokeClassMethod(getJarName(project), "loginToSync",
				new Class[] { String.class, String.class }, arg1);
	}
	
	public static void synchronize(){
		JarLoader.invokeClassMethod(getJarName(project),  "synchronize",null, null);
	}

	private static String getJarName(String pack) {
		String firstChar = pack.substring(0, 1);
		String capFirstChar = pack.replaceFirst(firstChar, firstChar.toUpperCase());
		String result = pack + "." + capFirstChar + "DB";
		return result;
	}

	public static void deleteDatabase() {
		JarLoader.invokeClassMethod(getJarName(project),  "deleteDatabase",null, null);
	}
	
	public static void synchronize(String syncGroupName){
		JarLoader.invokeClassMethod(getJarName(project),  "synchronize",new Class[]{String.class}, new Object[]{syncGroupName});
	}

	public static void close() {
		JarLoader.invokeClassMethod(getJarName(project),  "closeConnection",null, null);
	}
	
}
