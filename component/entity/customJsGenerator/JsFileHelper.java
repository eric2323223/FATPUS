package component.entity.customJsGenerator;
import java.io.File;
import java.io.IOException;

import org.apache.commons.io.FileUtils;

import component.entity.GlobalConfig;



/**
 * @author eric
 *
 */
public class JsFileHelper {
	private String content ;
	private final CustomJsTestScript script;
	
	public final static char SPACE=32;

	public JsFileHelper(File jsTemplateFile, CustomJsTestScript script) throws IOException{
		this.content = FileUtils.readFileToString(jsTemplateFile, null);
//		this.content = FileUtils.readFileToString(jsTemplateFile);
		this.script = script;
	}
	
	public void addToEnd(String code){
		content = content + code +"\n";
	}
	
	public void addToStart(String code){
		content = code + "\n"+ content;
	}
	
	private String removeComments(final String code){
		char[] ba = code.toCharArray();
		for(int i=0;i<ba.length;i++){
			if(ba[i]=='/' && ba[i+1]=='/'){
				while(ba[i]!='\n'){
					ba[i] = SPACE;
					i++;
				}
			}
			if(ba[i]=='/' && ba[i+1]=='*'){
				while(!(ba[i]=='*' && ba[i+1]=='/')){
					ba[i] = SPACE;
					i++;
				}
				ba[i] = SPACE;
				ba[i+1] = SPACE;
			}
		}
		return new String(ba);
	}
	
	public String getInitialDescription(final String code){
		return code.substring(0, code.indexOf("*/")+2);
	}
	
	public boolean hasCondition(String methodName, ScreenTransitionAction action){
		String body = getMethodBody(methodName);
		return action.hasConditionInMethod(body);
	}
	
	public void addToMethod(String methodName, String methodBody){
		String origin = "function "+methodName+getMethodParameters(methodName)+"{"+getMethodBody(methodName)+"}";
		String to = "function "+methodName+getMethodParameters(methodName)+"{"+getMethodBody(methodName)+"\n"+methodBody+"}";
		String str = content.replace(origin, to);
		content = str;
	}
	
	public void updateMethod(String methodName, String methodBody){
		String origin = "function "+methodName+getMethodParameters(methodName)+"{"+getMethodBody(methodName)+"}";
		String to = "function "+methodName+getMethodParameters(methodName)+"{"+methodBody+"}";
		String str = content.replace(origin, to);
		content = str;
	}
	
	public String getMethodParameters(String methodName){
		String str = content.substring(content.indexOf("function "+methodName), content.length());
		str = str.substring(("function "+methodName).length(), str.indexOf("{"));
		return str;
	}
	
	public String getMethodBody(String methodName){
		String str = content.substring(content.indexOf("function "+methodName), content.length());
		str = str.substring(("function "+methodName).length(), str.length());
		str = str.substring(str.indexOf("{"),str.indexOf("function ")); //the blank after function is important
//		System.out.println("method body: "+str);
		str = str.substring(1, str.lastIndexOf("}"));
		return str;
	}
	
	public File toFile(String fileName) throws IOException{
		File file = new File(fileName);
		FileUtils.writeStringToFile(file, content, null);
		return file;
	}

	public void writeAction(CustomJsTestScript script, ScreenTransitionAction action) {
		if(action.isFirstAction() && !action.isLastAction(script) ){
			addToMethod(action.getCallBackMethod(), action.toJavaScriptCode(true, false));
		}
		if(action.isFirstAction() && action.isLastAction(script)){
			addToMethod(action.getCallBackMethod(), action.toJavaScriptCode(true, true));
		}
		if(!action.isFirstAction() && action.isLastAction(script)){
			addToMethod(action.getCallBackMethod(), action.toJavaScriptCode(false, true));
		}
		if(!action.isFirstAction() && !action.isLastAction(script)){
			addToMethod(action.getCallBackMethod(), action.toJavaScriptCode());
		}
	}

	public static void main(String[] args) throws IOException{
		JsFileHelper helper = new JsFileHelper(new File("C:\\Users\\think\\TW\\CustomJsTestScript\\src\\com\\sybase\\automation\\template.js"), null);
		System.out.println(helper.getMethodBody("customAfterShowScreen"));
		helper.addToMethod("customAfterShowScreen", "hello, world!");
		System.out.println(helper.getMethodBody("customAfterShowScreen"));
	}

	public String tweak() {
		String initialDescription = getInitialDescription(content);
		String removeCommentsCode = removeComments(content);
		content = removeCommentsCode;
		for(GlobalAction action: script.getGlobalActions()){
			if(action.getMethodName()==null){
				addDeclaration(action);
			}
			else {
				if (!action.isNewMethod()) {
					updateMethod(action.getMethodName(), action.getCode());
				} else {
					addNewMethod(action);
				}
			}
		}
		for(ScreenTransitionAction action: script.getTransitions()){
			writeAction(script, action);
		}
		this.addToStart("var step=0;\n");
		this.addToEnd("function server(){\nreturn "+"\"http://"+GlobalConfig.IP_ADDRESS+":"+8008+"\";\n }");
		this.addToEnd("function include(file)\n{\nvar script  = document.createElement('script');\n  script.src  = file;\n  script.type = 'text/javascript';\n  script.charset = \"UFT-8\";\n  document.getElementsByTagName('head').item(0).appendChild(script);\n}\n");
		this.addToEnd("include('js/suade_api.js');");
		addToStart(initialDescription);
		return content;
	}

	private void addDeclaration(GlobalAction action) {
		this.addToStart(action.getCode());
	}

	private void addNewMethod(GlobalAction action) {
		String text = "function "+action.getMethodName()+"(";
		for(String argument: action.getParameters()){
			text = text+argument+",";
		}
		if(text.endsWith(",")){
			text = text.substring(0, text.length()-2);
		}
		text = text+"){\n";
		text = text+action.getCode();
		text = text+"}\n";
		this.addToStart(text);
	}
	
}