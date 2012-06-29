package com.sybase.automation.framework.common;

import java.io.InputStream;

import com.jcraft.jsch.Channel;
import com.jcraft.jsch.ChannelExec;
import com.jcraft.jsch.JSch;
import com.jcraft.jsch.Session;
import component.entity.model.SSHCommand;

public class SSHCommandInvoker {

	public static void run(String host, String user, String password, String command){
        try{
 
            java.util.Properties config = new java.util.Properties();
            config.put("StrictHostKeyChecking", "no");
            JSch jsch = new JSch();
            Session session=jsch.getSession(user, host, 22);
            session.setPassword(password);
            session.setConfig(config);
            session.connect();
            System.out.println("Connected");
 
            Channel channel=session.openChannel("exec");
            ((ChannelExec)channel).setCommand(command);
            channel.setInputStream(null);
            ((ChannelExec)channel).setErrStream(System.err);
 
            InputStream in=channel.getInputStream();
            channel.connect();
            byte[] tmp=new byte[1024];
            while(true){
              while(in.available()>0){
                int i=in.read(tmp, 0, 1024);
                if(i<0)break;
                System.out.print(new String(tmp, 0, i));
              }
              if(channel.isClosed()){
//                System.out.println("exit-status: "+channel.getExitStatus());
                break;
              }
              try{Thread.sleep(1000);}catch(Exception ee){}
            }
            channel.disconnect();
            session.disconnect();
//            System.out.println("DONE");
        }catch(Exception e){
            e.printStackTrace();
        }
    
	}
	
	public static void runCommand(SSHCommand sshCommand){
		String host = sshCommand.getProperty("host").toString();
		String user = sshCommand.getProperty("user").toString();
		String password = sshCommand.getProperty("password").toString();
		String command = sshCommand.getProperty("command").toString();
		run(host, user, password, command);
	}
	
	 public static void main(String[] args) {
		 SSHCommandInvoker.run("10.35.180.238", "test", "test", "c:\\androidSDK\\R12\\platform-tools\\adb.exe devices");
			
	 }

}
