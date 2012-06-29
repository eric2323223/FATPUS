package component.entity.WFCustomizer;
import java.io.File;
import java.io.IOException;
import java.util.List;

import com.rational.test.ft.script.RationalTestScript;
import com.sybase.automation.framework.common.FileUtil;
import com.sybase.automation.framework.tool.AnnotationUtil;
import com.sybase.automation.framework.tool.MethodUtil;


import component.entity.GlobalConfig;
import component.entity.MainMenu;
import component.entity.WN;
import component.entity.WFCustomizer.NanoHTTPD.Response;
import component.entity.customJsGenerator.CustomJsTestScript;
import component.entity.customJsGenerator.GlobalAction;
import component.entity.customJsGenerator.JsFileHelper;
import component.entity.customJsGenerator.ScreenTransitionAction;
import component.entity.model.WorkFlowPackage;
import component.entity.tplan.Robot;
import component.entity.tplan.TestResult;

/**
 * @author eric
 *
 */
public class WFCustomizer extends RationalTestScript{
	private NanoHTTPD httpd;
	private static WFClientResult actualResult;
	private int port;
	
	public WFCustomizer(int port){
//		try {
			this.port = port;
//			this.httpd = new NanoHTTPD(port);
//		} catch (IOException e) {
//			e.printStackTrace();
//		}
	}
	
	public static void runTest(WorkFlowPackage wfp, CustomJsTestScript script){
		new WFCustomizer(8008).run(wfp, script);
	}
	
	public static void runTest(WorkFlowPackage wfp, CustomJsTestScript script, CallBackMethod method){
		new WFCustomizer(8008).run(wfp, script, method);
	}
	
	public static void runTest(WorkFlowPackage wfp, RationalTestScript script){
		new WFCustomizer(8008).run(wfp, script);
	}
	
	public static void runTest(WorkFlowPackage wfp, String jsFile, RationalTestScript script){
		new WFCustomizer(8008).run(wfp, jsFile, script);
	}
	
	public static void runTest(WorkFlowPackage wfp, CustomJsTestScript jsFile, RationalTestScript script){
		new WFCustomizer(8008).run(wfp, jsFile, script);
	}
	
	public static void runTest(WorkFlowPackage wfp, CustomJsTestScript jsFile, String script){
		new WFCustomizer(8008).run(wfp, jsFile, script);
	}
	
	public static void runTest(WorkFlowPackage wfp, List<CustomJsTestScript> jsFiles, String script){
		new WFCustomizer(8008).run(wfp, jsFiles, script);
	}
	
	public static void runTest(WorkFlowPackage wfp, CustomJsTestScript jsFile, String script, CallBackMethod method){
		new WFCustomizer(8008).run(wfp, jsFile, script, method);
	}
	
	private void startServerForWFClientResult(){
		WFClientResult result = new WFClientResult();
		getHttpd().registerOnDataReceivedListener(result);
		getHttpd().run();
		actualResult = result.waitForComplete();
	}
	
	private void startServerForWFClientResult_ServeRequest(WorkFlowPackage wfp){
		WFClientResult result = new WFClientResult();
		getHttpd().registerOnDataReceivedListener(result);
		WorkFlowNameHandler myHandler = new WorkFlowNameHandler();
		String wfName = wfp.sp().substring(wfp.sp().lastIndexOf("->")).replace(".xbw", "");
		myHandler.setUriResponse("getWorkflowName", getHttpd().new Response(wfName));
		getHttpd().registerHandler(myHandler);
		getHttpd().run();
		actualResult = result.waitForComplete();
	}
	
	private void run(WorkFlowPackage wfp, CustomJsTestScript jsFile,String script) {
		actualResult = null;
//		handleCustomJs(wfp, jsFile);
		tweakCustomJs(wfp, jsFile);
		copySuadeAPI(wfp);
		deployWFPackge(wfp);
		sleep(5);
//		if(runInNormalMode()){
//			Robot.run(script);
//		}
		startServerForWFClientResult();
	}
	
	private boolean runInNormalMode() {
		Class callerClass = MethodUtil.getCallingClass();
		return !AnnotationUtil.containAnnotation(callerClass, "@E2ETestScript");
	}
	
//	private boolean tplanMustPass(){
//		Class callerClass = MethodUtil.getCallingClass();
//		return AnnotationUtil.getProperty("@E2ETestScript", "executionMode").equals("");
//	}

	private void run(WorkFlowPackage wfp, List<CustomJsTestScript> jsFiles,String script) {
		for(CustomJsTestScript s: jsFiles){
			run(wfp, s, script);
		}
	}
	
	private void run(WorkFlowPackage wfp, CustomJsTestScript jsFile,String script, CallBackMethod method){
		actualResult = null;
		handleCustomJs(wfp, jsFile);
		copySuadeAPI(wfp);
		deployWFPackge(wfp);
		method.call();
		if(runInNormalMode()){
//			TestResult result = Robot.run(script);
//			if(tplanMustPass() && !result.isPass()){
//				return;
//			}
		}
		startServerForWFClientResult();
	}
	
	private NanoHTTPD getHttpd(){
		if(httpd == null){
			httpd = NanoHTTPD.getInstance(port);
		}
		return httpd;
	}

	public void handleCustomJs(WorkFlowPackage wfp, CustomJsTestScript script) {
		try {
			WorkFlowPackage pkg = wfp.clone();
			WN.createWorkFlowPackage(pkg.deployToServer("false").assignToUser("null"));
			JsFileHelper helper = new JsFileHelper(new File(GlobalConfig.RFT_PROJECT_ROOT+"\\component\\entity\\customJsGenerator\\template.js"), script);
			for(GlobalAction action: script.getGlobalActions()){
				helper.updateMethod(action.getMethodName(), action.getCode());
			}
			helper.addToMethod("server", "\nreturn "+"\"http://"+GlobalConfig.IP_ADDRESS+":"+port+"\";\n");
			for(ScreenTransitionAction action: script.getTransitions()){
				helper.writeAction(script, action);
			}
			String project = wfp.sp().split("->")[0].substring(0, wfp.sp().split("->")[0].indexOf("[")).trim();
			String wf = wfp.sp().split("->")[1];
			if(wfp.getExternalFolder()!=null){
				helper.toFile(wfp.getExternalFolder()+"\\"+wf+"\\html\\js\\Custom.js");
			}else{
				helper.toFile(MainMenu.getCurrentWorkspace()+"\\"+project+"\\Generated Hybrid App\\"+wf+"\\html\\js\\Custom.js");
			}
			WN.refresh(WN.projectNameWithVersion(wf));
//			WN.refresh(WN.filePath(getProject(wfp), "Generated Hybrid App->"+getWorkflow(wfp)));
		
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	public void tweakCustomJs(WorkFlowPackage wfp, CustomJsTestScript script){
		try {
			WorkFlowPackage pkg = wfp.clone();
			WN.createWorkFlowPackage(pkg.deployToServer("false").assignToUser("null"));
			String projectName = wfp.sp().split("->")[0].substring(0, wfp.sp().split("->")[0].indexOf("[")).trim();
			String workflowName = wfp.sp().split("->")[1];
			
			String absoluteCustomJsFileName;
			if(wfp.getExternalFolder()!=null){
				absoluteCustomJsFileName = wfp.getExternalFolder()+"\\"+workflowName+"\\html\\js\\Custom.js";
			}else{
				absoluteCustomJsFileName = MainMenu.getCurrentWorkspace()+"\\"+projectName+"\\Generated Hybrid App\\"+workflowName+"\\html\\js\\Custom.js";
			}
//			String absoluteCustomJsFileName = GlobalConfig.SUP_TOOLING_WORKSPACE+"\\"+projectName+"\\Generated Hybrid App\\"+workflowName+"\\html\\js\\Custom.js";
			JsFileHelper helper = new JsFileHelper(new File(absoluteCustomJsFileName), script);
			String tweakCode = helper.tweak();
			FileUtil.writeToFile(new File(absoluteCustomJsFileName), tweakCode);
			WN.refresh(WN.projectNameWithVersion(projectName));
//			WN.refresh(WN.filePath(getProject(wfp), "Generated Hybrid App->"+getWorkflow(wfp)));
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public static void runTest(int port, WorkFlowPackage wfp, RationalTestScript script){
		new WFCustomizer(port).run(wfp, script);
	}

	public static void runTest(int port, WorkFlowPackage wfp, String jsFile, RationalTestScript script){
		new WFCustomizer(port).run(wfp, jsFile, script);
	}
	
	public static void runTest(int port, WorkFlowPackage wfp, CustomJsTestScript jsFile, RationalTestScript script){
		new WFCustomizer(port).run(wfp, jsFile, script);
	}
	
	public void run(WorkFlowPackage wfp, CustomJsTestScript script){
		actualResult = null;
		tweakCustomJs(wfp, script);
//		handleCustomJs(wfp, script);
		copySuadeAPI(wfp);
		deployWFPackge(wfp);
		startServerForWFClientResult();
	}
	
	public void run(WorkFlowPackage wfp, CustomJsTestScript script, CallBackMethod method){
		actualResult = null;
		tweakCustomJs(wfp, script);
//		handleCustomJs(wfp, script);
		copySuadeAPI(wfp);
		deployWFPackge(wfp);
		method.call();
		startServerForWFClientResult();
	}
	
	public void run(WorkFlowPackage wfp, RationalTestScript script ){
		actualResult = null;
		copySuadeAPI(wfp);
		deployWFPackge(wfp);
		Robot.run(script);
		startServerForWFClientResult();
	}
	
	public void run(WorkFlowPackage wfp, String customJs, RationalTestScript script ){
		actualResult = null;
		copySuadeAPI(wfp);
		copyCustomJs(wfp, customJs);
		deployWFPackge(wfp);
		if(runInNormalMode()){
			Robot.run(script);
		}
		startServerForWFClientResult();
	}
	
	public void run(WorkFlowPackage wfp, String customJs, String script ){
		actualResult = null;
		copySuadeAPI(wfp);
		copyCustomJs(wfp, customJs);
		deployWFPackge(wfp);
		if(runInNormalMode()){
			Robot.run(script);
		}
		startServerForWFClientResult();
	}
	
	public void run(WorkFlowPackage wfp, CustomJsTestScript customJs, RationalTestScript script ){
		actualResult = null;
		tweakCustomJs(wfp, customJs);
//		handleCustomJs(wfp, customJs);
		copySuadeAPI(wfp);
		deployWFPackge(wfp);
		if(runInNormalMode()){
			Robot.run(script);
		}
		startServerForWFClientResult();
	}

	public static void verifyResult(WFClientResult expected ) {
		
//		actualResult.setExpected(expected);
		
		System.out.println("acutal data:");
		actualResult.print();
		System.out.println("expected data:");
		expected.print();
		if(actualResult!=null){
			actualResult.verify(expected);
		}else{
			throw new RuntimeException("Oops, there is no client test result yet.");
		}
	}

	private void copyCustomJs(WorkFlowPackage wfp, String customJs) {
		try {
			FileUtil.copy(customJs, generatedWFFilePath(wfp)+"\\html\\js\\Custom.js");
			WN.refresh(WN.filePath(getProject(wfp), "Generated Hybrid App->"+getWorkflow(wfp)));
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	private void copySuadeAPI(WorkFlowPackage wfp){
		try {
			FileUtil.copy(new File(GlobalConfig.RFT_PROJECT_ROOT+"\\component\\entity\\customJsGenerator\\suade_api.js"),  new File(generatedWFFilePath(wfp)+"\\html\\js\\suade_api.js"));
//			FileUtil.copy(new File(GlobalConfig.RFT_PROJECT_ROOT+"\\component\\entity\\WFCustomizer\\suade_api.js"),  new File(generatedWFFilePath(wfp)+"\\html\\js\\suade_api.js"));
			WN.refresh(WN.filePath(getProject(wfp), "Generated Hybrid App->"+getWorkflow(wfp)));
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	private String generatedWFFilePath(WorkFlowPackage wfp){
		return MainMenu.getCurrentWorkspace()+"\\"+getProject(wfp)+"\\Generated Hybrid App\\"+getWorkflow(wfp);
	}
	
	private String getProject(WorkFlowPackage wfp){
		String projectName = wfp.sp().split("->")[0];
		return projectName.substring(0, projectName.indexOf(" [Version"));
	}
	
	private String getWorkflow(WorkFlowPackage wfp){
		return wfp.sp().split("->")[1].replace(".xbw","");
	}

	private void deployWFPackge(WorkFlowPackage wfp) {
		WN.createWorkFlowPackage(wfp);
	}
}