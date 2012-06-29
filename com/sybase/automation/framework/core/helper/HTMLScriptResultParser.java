package com.sybase.automation.framework.core.helper;

import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.htmlparser.Parser;
import org.htmlparser.filters.NodeClassFilter;
import org.htmlparser.tags.TableRow;
import org.htmlparser.util.NodeList;
import org.htmlparser.util.ParserException;

import com.sybase.automation.framework.common.FileUtil;

public class HTMLScriptResultParser {
	
	private String logFileName;
	private List<ScriptResult> scriptResults = new ArrayList<ScriptResult>();
	private List<String> log = new ArrayList<String>();
	
	public void setLogFile(String file){
		this.logFileName = file;
	}
	
	public static void main(String[] args){
		HTMLScriptResultParser parser = new HTMLScriptResultParser();
//		parser.logFileName = "C:\\Documents and Settings\\eric\\IBM\\rationalsdp\\workspace155\\UEP_ET_logs\\testscript.ObjectQuery.script.NamedQuery_MBO_Codegen_01\\rational_ft_logframe.html";
//		parser.logFileName = "C:\\Documents and Settings\\eric\\IBM\\rationalsdp\\workspace155\\UEP_ET_logs\\testscript.ObjectQuery.script.NamedQuery_All\\rational_ft_logframe.html";
		parser.logFileName = args[0];
		parser.checkLog();
		System.out.print(parser.getSummary());
	}
	
	public void checkLog() {
		extractRecords();
		scriptResults = buildResults();
	}

	public void reset() {
		scriptResults.clear();
		log.clear();
	}
	
	private void extractRecords() {
		try {
			Parser parser;
			parser = new Parser(this.logFileName);
			parser.setEncoding("utf8");
			NodeClassFilter filter = new NodeClassFilter(TableRow.class);
			NodeList rows = parser.extractAllNodesThatMatch(filter);
			for (int i = 0; i < rows.size(); i++) {
				String line = rows.elementAt(i).toPlainTextString().trim();
				log.add(line);
			}

		} catch (ParserException e) {
			e.printStackTrace();
		}
	}
	
	private List<ScriptResult> buildResults(){
		List<ScriptResult> scriptResults = new ArrayList<ScriptResult>();
		String currentScriptName = "_NA_";
		ScriptResult result = new ScriptResult();
		for(int i=0;i<log.size();i++){
			result.setResult(ScriptResult.FAIL);
			String line = log.get(i);
			if(getScriptStartLine(line)!=null && !getScriptStartLine(line).equals(currentScriptName)){
				currentScriptName = getScriptStartLine(line);
				result.setName(currentScriptName);
			}
			if(getVPFailureLine(line)!=null){
				result.addVpFailure(getVPFailureLine(line));
			}
			if(getExceptionLine(line)!=null){
				String detailedExceptionStr = log.get(++i);
				String exceptionName = getExceptionName(detailedExceptionStr);
				result.setException(exceptionName);
			}
			if(getResultLine(line)!=null){
				if(getResultLine(line).equalsIgnoreCase("pass") || getResultLine(line).equalsIgnoreCase("warning")){
					result.setResult(ScriptResult.PASS);
//					scripts.add(new ScriptResult(result[0]));
				}
				scriptResults.add(result);
				result = new ScriptResult();
			}
		}
		return scriptResults;
	}
	
//	private void buildResults(){
//		String exceptionScript = "";
//		String currentScriptName = "_NA_";
//		ScriptResult result = new ScriptResult();
//		for(int i=0;i<log.size();i++){
//			String line = log.get(i);
//			if(getScriptStartLine(line)!=null && !getScriptStartLine(line).equals(currentScriptName)){
//				currentScriptName = getScriptStartLine(line);
//				result.setName(currentScriptName);
//			}
//			if(getVPFailureLine(line)!=null){
//				result.addVpFailure(getVPFailureLine(line));
//			}
//			if(getExceptionLine(line)!=null){
//				String detailedExceptionStr = log.get(++i);
//				String exceptionName = getExceptionName(detailedExceptionStr);
//				String exceptionMsg = getExceptionMsg(detailedExceptionStr);
//				scripts.add(new ScriptResult(currentScriptName, exceptionName, exceptionMsg));
////				scripts.add(new ScriptResult(getExceptionLine(line), exceptionName, exceptionMsg));
//				exceptionScript = getExceptionLine(line);
//			}
//			if(getResultLine(line)!=null){
//				String[] result = getResultLine(line);
//				if(result[1].equalsIgnoreCase("pass") || result[1].equalsIgnoreCase("warning")){
//					scripts.add(new ScriptResult(currentScriptName));
////					scripts.add(new ScriptResult(result[0]));
//				}
//				else if(!exceptionScript.equals(result[0])){
//					scripts.add(new ScriptResult(result[0],getVPFailureLine(line)));
//				}
//			}
//		}
//	}
	
	private String getVPFailureLine(String line){
		Pattern pattern = Pattern.compile(".*Verification Point \\[(.*)\\] failed");
		Matcher m = pattern.matcher(line);
		if(m.find()){
			return m.group(1);
		}
		return null;
	}
	
	private String getExceptionLine(String line) {
		Pattern pattern = Pattern.compile("FAIL\\W+.*\\W+([\\d|\\w|_|.]+) had an unhandled exception.");
		Matcher m = pattern.matcher(line);
		if(m.find()){
			return m.group(1).substring(0, m.group(1).lastIndexOf("."));
		}
		return null;
	}
	
	private String getScriptStartLine(String line){
		Pattern pattern = Pattern.compile(".*Script start \\[(.*)\\]");
		Matcher m = pattern.matcher(line);
		if(m.find()){
			return m.group(1);
		}
		return null;
		
	}
	
	private String getResultLine(String line){
		String[] result = new String[2];
		Pattern pattern = Pattern.compile("([A-Z]+)\\W+.*\\W+Script end \\[.*\\]");
		Matcher m = pattern.matcher(line);
		if(m.find()){
			return m.group(1);
		}
		return null;
	}
	
//	private String[] getResultLine(String line){
//		String[] result = new String[2];
//		Pattern pattern = Pattern.compile("([A-Z]+)\\W+.*\\W+Script end \\[(.*)\\]");
//		Matcher m = pattern.matcher(line);
//		if(m.find()&&m.groupCount()==2){
//			result[0] = m.group(2);
//			result[1] = m.group(1);
//			return result;
//		}
//		return null;
//	}
	
	private String getExceptionMsg(String detailedExceptionStr) {
		Pattern pattern = Pattern.compile(".*\\W+(exception_message = .+)\\W+.*");
		Matcher m = pattern.matcher(detailedExceptionStr);
		if(m.find()){
			return m.group(1);
		}
		return null;
	}

	private String getExceptionName(String detailedExceptionStr) {
		Pattern pattern = Pattern.compile("(exception_name = .+)\\W+.*");
		Matcher m = pattern.matcher(detailedExceptionStr);
		if(m.find()){
			return m.group(1);
		}
		return null;
	}
	
	public String getSummary() {
		if(scriptResults.size()>1){
			return getSuiteSummary();
		}else{
			return getSingleScriptSummary();
		}
	}

	private String getSingleScriptSummary() {
		return scriptResults.get(0).toString();
	}

	private String getSuiteSummary() {
		String text = "";
		int failCount = 0;
		for (ScriptResult result : scriptResults) {
			if (!result.isPass()) {
				failCount++;
				text = text + failCount + ". " + result.toString();
			}
		}
		return text + failCount + " out of " + scriptResults.size()
				+ " scripts failed, fail rate: "
				+ failRate(failCount, scriptResults.size()) + "\n";
	}
	
	
	public String getFailedScriptsInvocation() {
		String text = "";
		for(ScriptResult result:scriptResults){
			if(!result.isPass()){
				text = text + "callScript(\""+ result.getName()+"\");"+"\n";
			}
		}
		return text;
	}
	
	private String failRate(int failCount, int total){
		float ratio = (float)failCount/(float)total*100;
		return new Float(ratio).toString()+"%";
	}

	public void insertDeviceResults(List<DeviceResult> deviceResults) {
		for(int i=0;i<scriptResults.size();i++){
			ScriptResult scriptResult = scriptResults.remove(i);
			for(DeviceResult deviceResult:deviceResults){
				if(scriptResult.getName().equals(deviceResult.getScriptName())){
					scriptResult = scriptResult.mergeDeviceResult(deviceResult);
					scriptResults.add(i, scriptResult);
				}
			}
		}
	}
}
