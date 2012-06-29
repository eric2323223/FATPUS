package com.sybase.automation.framework.common;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.ProtocolException;
import java.net.URL;


public class DCNRequestSender {
	
	public static HttpURLConnection get(URL url){
		try {
			HttpURLConnection con = (HttpURLConnection) url.openConnection();
			con.setDoOutput(true);
			con.setRequestMethod("GET");
			con.setRequestProperty("Content-Type",	"application/x-www-form-urlencoded");
			OutputStream outStream = con.getOutputStream();
			outStream.flush();
			outStream.close();
			System.out.println(con.getResponseCode());
			BufferedReader in = new BufferedReader(new InputStreamReader(con.getInputStream(), "UTF-8"));
			String line;
			while ((line = in.readLine()) != null) {
				System.out.println(line);
			}
			return con;
		} catch (ProtocolException e) {
			e.printStackTrace();
			return null;
		} catch (IOException e) {
			e.printStackTrace();
			return null;
		}
	}
	
	public static HttpURLConnection post(URL url){
		try {
			HttpURLConnection con = (HttpURLConnection) url.openConnection();
			con.setDoOutput(true);
			con.setRequestMethod("POST");
			con.setRequestProperty("Content-Type",	"application/x-www-form-urlencoded");
			OutputStream outStream = con.getOutputStream();
			outStream.flush();
			outStream.close();
			System.out.println(con.getResponseCode());
			return con;
		} catch (ProtocolException e) {
			e.printStackTrace();
			return null;
		} catch (IOException e) {
			e.printStackTrace();
			return null;
		}
	}
	
//	public static HttpURLConnection sendRequest(URL url, Json string){
//		
//	}

}
