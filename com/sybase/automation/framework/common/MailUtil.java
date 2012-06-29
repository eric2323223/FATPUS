package com.sybase.automation.framework.common;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Properties;

import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.AddressException;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

import com.sybase.automation.framework.core.helper.ResultLogParser;

/**
 * A collection of utilities on java mail 
 * 
 * Example:
 * 
 * send( 25, "joe@smith.com", "xfu@sybase.com", "re: dinner", "How about at 7?");
 * 
 * @author xfu
 */
public class MailUtil
{
    /**
     * Send to only one  reciever
     * @param smtpPort
     * @param from
     * @param to
     * @param subject
     * @param content
     * @throws AddressException
     * @throws MessagingException
     */
    public static void send(int smtpPort, String from, String to, String subject, String content)
        throws AddressException, MessagingException
    {
        // Create a mail session
        java.util.Properties props = new java.util.Properties();
        props.put("mail.smtp.host", "smtp.sybase.com");
        props.put("mail.smtp.port", "" + smtpPort);
        Session session = Session.getDefaultInstance(props, null);
        // Construct the message
        Message msg = new MimeMessage(session);
        msg.setFrom(new InternetAddress(from));
        msg.setRecipient(Message.RecipientType.TO, new InternetAddress(to));
        msg.setSubject(subject);
        msg.setText(content);
        // Send the message
        Transport.send(msg);
    }
    
    public static void send(String server, int smtpPort, String from, String to, String subject, String content)
    throws AddressException, MessagingException
    {
    	// Create a mail session
    	System.out.println("[server]"+server);
    	System.out.println("[smtpPort]"+smtpPort);
    	System.out.println("[from]"+from);
    	System.out.println("[to]"+to);
    	System.out.println("[subject]"+subject);
    	System.out.println("[content]"+content);
    	
    	java.util.Properties props = new java.util.Properties();
    	props.put("mail.smtp.host", server);
    	props.put("mail.smtp.port", smtpPort);
    	Session session = Session.getDefaultInstance(props, null);
    	// Construct the message
    	Message msg = new MimeMessage(session);
    	msg.setFrom(new InternetAddress(from));
    	msg.setRecipient(Message.RecipientType.TO, new InternetAddress(to));
    	msg.setSubject(subject);
    	msg.setText(content);
    	// Send the message
    	Transport.send(msg);
    }
    
    
    /**
 * Send to multiple reciever
 * @param smtpPort
 * @param from
 * @param to
 * @param subject
 * @param content
 * @throws AddressException
 * @throws MessagingException
 */
    public static void send(int smtpPort, String from, String[] to, String subject, String content)
        throws AddressException, MessagingException
    {
        for (int i = 0; i < to.length; i++)
        {
    		if(!to[i].trim().equals("")){
    			send(smtpPort, from, to[i].trim(), subject, content);
    		}
        }
    }
    
    public static void send(String server, int smtpPort, String from, String[] to, String subject, String content)
    throws AddressException, MessagingException
    {
    	for (int i = 0; i < to.length; i++)
    	{
    		if(!to[i].trim().equals("")){
    			send(smtpPort, from, to[i].trim(), subject, content);
    		}
    	}
    }
    
    public static void main(String[] args) throws AddressException, MessagingException{
    	int port = new Integer(args[1]).intValue();
    	String[] receivers = args[3].split(",");
//    	String content = FileUtil.readFromFile(args[5]);
    	String startDate = args[6];
    	String startTime = args[7];
    	String endDate = args[8];
    	String endTime = args[9];
    	String product = args[10];
    	String version = args[11];
    	if(receivers.length>1){
      		MailUtil.send(args[0], port, args[2], receivers, ResultLogParser.generateSubject(args[4],args[5], product, version), ResultLogParser.generateMailContent(args[5], startDate, startTime, endDate, endTime, product, version));
//      		MailUtil.send(args[0], port, args[2], receivers, args[4], ResultLogParser.generateMailContent(content, start, end));
    	}else{
    		MailUtil.send(args[0], port, args[2], args[3], ResultLogParser.generateSubject(args[4],args[5], product, version), ResultLogParser.generateMailContent(args[5], startDate, startTime,endDate, endTime, product, version));
//    		MailUtil.send(args[0], port, args[2], args[3], args[4], ResultLogParser.generateMailContent(content, start, end));
    	}
    }

}
