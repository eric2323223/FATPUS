package com.sybase.automation.framework.common;

import java.io.IOException;
import java.io.InputStream;
import java.io.StringWriter;

import com.sybase.automation.framework.common.RegistryUtil.StreamReader;

public class CmdUtil {

	public static final String executeCommand(String command) {
		try {
			Process process = Runtime.getRuntime().exec("cmd /c " + command);
			StreamReader reader;
			if(process.getErrorStream()!=null){
				reader = new StreamReader(process.getErrorStream());
			}
			else{
				reader = new StreamReader(process.getInputStream());
			}
			reader.start();
			int exitCode = process.waitFor();
			System.out.println(exitCode);
			reader.join();
			String output = reader.getResult();
			return output;
		} catch (Exception e) {
			throw new RuntimeException("Failed to run command: "+command);
		}
	}
	
	public static final String executeBatchFile(String command) {
		try {
			Process process = Runtime.getRuntime().exec(command);
			StreamReader resultReader = new StreamReader(process.getErrorStream());
			StreamReader errorReader = new StreamReader(process.getInputStream());
			resultReader.start();
			errorReader.start();
			int exitCode = process.waitFor();
			System.out.println(exitCode);
			resultReader.join();
			errorReader.join();
			String output = resultReader.getResult();
			return output;
		} catch (Exception e) {
			throw new RuntimeException("Failed to run batch file: "+command);
		}
	}

	static class StreamReader extends Thread {
		private InputStream is;
		private StringWriter sw = new StringWriter();;

		public StreamReader(InputStream is) {
			this.is = is;
		}
		public void run() {
			try {
				int c;
				while ((c = is.read()) != -1)
					sw.write(c);
			} catch (IOException e) {
			}
		}
		public String getResult() {
			return sw.toString();
		}
	}
	
	public static void main(String[] args){
		System.out.println(CmdUtil.executeCommand("dir"));
		
//		System.out.println(CmdUtil.executeBatchFile("C:/Sybase/UnwiredPlatform/Servers/UnwiredServer/bin/codegen.bat"));
	}
}
