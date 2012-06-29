package com.sybase.automation.framework.core.helper;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

import com.sybase.automation.framework.common.FileUtil;

public class ResultLogParser {
	private static List<String> lines = new ArrayList<String>();
	
	private static String duration(String start, String end){
//		System.out.println(start+"-"+end);
		start = start.replace("-", ":");
		end = end.replace("-", ":");
		int startHour = new Integer(start.split(":")[0]).intValue();
		int startMinute = new Integer(start.split(":")[1]).intValue();
		int startSecond = new Integer(start.split(":")[2]).intValue();
		int endHour = new Integer(end.split(":")[0]).intValue();
		int endMinute = new Integer(end.split(":")[1]).intValue();
		int endSecond = new Integer(end.split(":")[2]).intValue();
		int elapsedInSecond = endHour*60*60 + endMinute*60+endSecond - startHour*60*60-startMinute*60-startSecond;
		if(elapsedInSecond<0){
			elapsedInSecond = elapsedInSecond + 24*60*60;
		}
		int durationHour = elapsedInSecond/60/60;
		int durationMinute = (elapsedInSecond - 60*60*durationHour)/60;
		int durationSecond = elapsedInSecond - 60*60*durationHour - 60*durationMinute;
		return durationHour+":"+durationMinute+":"+durationSecond;
	}

	public static String generateMailContent(String contentFile, String start, String end) {
		String content = FileUtil.readFromFile(contentFile);
		System.out.println("content: "+content);
		System.out.println("start: "+start);
		System.out.println("end: "+end);
		String text = "";
		text = text+"Start Time: "+start.replace("-", ":")+"\n";
		text = text+"End Time: "+end.replace("-", ":")+"\n";
		text = text+"Duration: "+duration(start, end)+"\n";
		text = text+"Executed case number: "+totalNumber(content)+"\n";
		text = text+"Passed case number: "+passNumber(content) +" ("+passRate(content)+")\n";
		text = text+"Failed case number: "+failNumber(content) +" ("+failRate(content)+")\n";
		text = text+"\n";
		text = text+"Details:\n";
		text = text+content;
		return text;
	}
	
	public static String generateMailContent(String contentFile, String startDate,
			String startTime, String endDate, String endTime, String product, String version) {
		String content = FileUtil.readFromFile(contentFile);
		System.out.println("StartTime: "+startDate+" "+startTime.replace("-", ":"));
		System.out.println("EndTime: "+endDate+" "+endTime.replace("-", ":"));
		System.out.println("Total: "+totalNumber(content));
		System.out.println("Pass: "+passNumber(content));
		System.out.println("Failure: "+failNumber(content));
		System.out.println("Error: "+errorNumber(content));
		System.out.println("Product: "+product);
		System.out.println("Version: "+version);
		System.out.println("Testsuite: ET");
		
		String text = "";
		text = text+"Product: "+product+"\n";
		text = text+"Version: "+version+"\n";
		text = text+"Start Time: "+startDate+" "+startTime.replace("-", ":")+"\n";
		text = text+"End Time: "+endDate+" "+endTime.replace("-", ":")+"\n";
		text = text+"Duration: "+duration(startTime, endTime)+"\n";
		text = text+"Executed case number: "+totalNumber(content)+"\n";
		text = text+"Passed case number: "+passNumber(content) +" ("+passRate(content)+")\n";
		text = text+"Failed case number: "+failNumber(content) +" ("+failRate(content)+")\n";
		text = text+"Error case number: "+errorNumber(content) +" ("+errorRate(content)+")\n";
		text = text+"\n";
		text = text+"Details:\n";
		text = text+content;
		return text;
		
	}
	
//	public static void main(String[] args){
//		System.out.println(duration("19:00:00", "20:00:00"));
//	}
	
	private static String failRate(String content) {
		float rate = (float)failNumber(content)/(float)totalNumber(content)*100;
		return String.format("%.2f", rate)+"%";
//		return new Float(rate).toString()+"%";
	}
	
	private static String errorRate(String content) {
		float rate = (float)errorNumber(content)/(float)totalNumber(content)*100;
		return String.format("%.2f", rate)+"%";
	}

	private static String passRate(String content) {
		float rate = (float)passNumber(content)/(float)totalNumber(content)*100;
		return String.format("%.2f", rate)+"%";
//		return new Float(rate).toString()+"%";
	}

	private static int passNumber(String content){
		int counter = 0;
		for(String line:allLines(content)){
			ScriptResult result = new ScriptResult(line);
			if(result.isPass()){
				counter++;
			}
		}
		return counter;
	}
	
	private static int errorNumber(String content){
		int counter = 0;
		for(String line:allLines(content)){
			ScriptResult result = new ScriptResult(line);
			if(result.isError()){
				counter++;
			}
		}
		return counter;
	}
	
	private static int failNumber(String content){
		return totalNumber(content) - passNumber(content) - errorNumber(content);
	}
	
	private static int totalNumber(String content){
		return allLines(content).size();
	}
	
	private static void reset(){
		lines.clear();
	}
	
	private static List<String> allLines(String content){
		if(lines.size()==0){
			for(String line:content.split("\n")){
				if(!line.trim().equals("")){
					lines.add(line);
				}
			}
		}
		return lines;
	}
	
	public static void main(String[] args){
		System.out.println(args[0]);
		System.out.println(args[1]);
		System.out.println(args[2]);
		System.out.println(args[3]);
		System.out.println(args[4]);
		System.out.println(args[5]);
		System.out.println(args[6]);
		generateMailContent(args[0], args[1], args[2], args[3], args[4], args[5], args[6]);
//		FileUtil.writeToFile(args[3], generateMailContent(FileUtil.readFromFile(args[0]), args[1], args[2], args[3], args[4]));
	}

	public static String generateSubject(String prefix, String contentFile, String product, String version) {
		String content = FileUtil.readFromFile(contentFile);
		if(failNumber(content)>0){
			return prefix+product+"-"+version+ " " +
				passRate(content)+" Passed ("+passNumber(content)+"/"+totalNumber(content)+"), " +
				failRate(content)+" Failed ("+failNumber(content)+"/"+totalNumber(content)+")";
		}else{
			return prefix+product+"-"+version+passRate(content)+" Passed ("+passNumber(content)+"/"+totalNumber(content)+")";
		}
	}



}
