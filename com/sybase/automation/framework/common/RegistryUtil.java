package com.sybase.automation.framework.common;

import java.io.IOException;
import java.io.InputStream;
import java.io.StringWriter;

public class RegistryUtil {
	public static String readRegistry(String location, String key){
        try {
            // Run reg query, then read output with StreamReader (internal class)
            Process process = Runtime.getRuntime().exec("reg query \""+ location + "\" /v \""+key+"\"");

            StreamReader reader = new StreamReader(process.getInputStream());
            reader.start();
            process.waitFor();
            reader.join();
            String output = reader.getResult();

            // Output has the following format:
            // \n<Version information>\n\n<key>\t<registry type>\t<value>
            if( ! output.contains("\t")){
                    throw new RuntimeException("Unable to get value for registry key: "+location+"\\"+key);
            }

            // Parse out the value
            String[] parsed = output.split("\t");
            return parsed[parsed.length-1];
        }
        catch (Exception e) {
        	e.printStackTrace();
            return null;
        }

    }

    static class StreamReader extends Thread {
        private InputStream is;
        private StringWriter sw= new StringWriter();;

        public StreamReader(InputStream is) {
            this.is = is;
        }

        public void run() {
            try {
                int c;
                while ((c = is.read()) != -1)
                    sw.write(c);
            }
            catch (IOException e) { 
        }
        }

        public String getResult() {
            return sw.toString();
        }
    }
    public static void main(String[] args) {

        // Sample usage
        String value = RegistryUtil.readRegistry("HKLM\\Software\\SYBASE\\UnwiredPlatform\\1.6\\", "RootLocation");
        System.out.println(value);

    }

}
