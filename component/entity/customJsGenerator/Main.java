package component.entity.customJsGenerator;
import java.io.File;
import java.io.IOException;

import component.entity.GlobalConfig;

public class Main {
	
//	//1-2-3-2-4
//	public CustomJsTestScript script(){
//		CustomJsTestScript ts = new CustomJsTestScript();
//		
//		ts.screen("screen1").getData("data1");
//		ts.screen("screen1").moveTo("screen2").throughMenuItem("menu1"); 
//		
//		ts.screen("screen2").getData("sample_id");
//		ts.screen("screen2").setData("sample_id", "sample_value");
//		ts.screen("screen2").moveTo("screen3").throughLink("menu2");
////		
////		ts.screen("screen3").moveTo("screen2").throughNavigation("menu3");
////		
////		ts.screen("screen2").getData("sample_id");
////		ts.screen("screen2").getData("sample_id2");
////		ts.screen("screen2").setData("sample_id2", "No");
////		ts.screen("screen2").moveTo("screen4").throughButton("button");
//		
//		return ts;
//	}
	
	private CustomJsTestScript customJsTestScript() {
		CustomJsTestScript js =  new CustomJsTestScript();
		js.screen("Start_Screen").moveTo("screen1").throughMenuItem("1");
		js.screen("screen1").getData("box1");
		js.screen("screen1").menuItem("1");
		js.screen("screen1").moveTo("screen2").throughMenuItem("2");
//		js.screen("screen2").moveTo("screen3").throughMenuItem("screen3");
//		js.screen("screen1").moveTo("screen2").throughMenuItem("screen2");
//		js.screen("screen2").moveTo("screen1").throughMenuItem("screen1");
		return js;
	}
	
	public File generateJsScript(CustomJsTestScript script, final File templateFile, String customJsFile) throws IOException{
		JsFileHelper jsHelper = new JsFileHelper(templateFile, script);
		jsHelper.addToMethod("server", "\nreturn "+"\""+GlobalConfig.IP_ADDRESS+"\";\n");
		for(ScreenTransitionAction action: script.getTransitions()){
			jsHelper.writeAction(script, action);
		}
		return jsHelper.toFile(customJsFile);
//		return jsHelper.toFile("c:\\result1.js");
	}
	
	public static void main(String[] args) throws IOException{
		Main m = new Main();
		m.generateJsScript(m.customJsTestScript(), 
				new File("C:\\Documents and Settings\\eric\\IBM\\rationalsdp\\workspace\\UEP_ET\\component\\entity\\customJsGenerator\\template.js"),
						"c:\\result1.js");
	}

}