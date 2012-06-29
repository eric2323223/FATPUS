package com.sybase.automation.framework.common;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.nio.channels.FileChannel;
import java.util.Properties;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * A collection of utilities on File
 * @author xfu, Eric
 */
public class FileUtil {

	/**
	 * Method writeToFile.
	 */
	public static void writeToFile(String path, String contents) {
		File file = new File(path);
		writeToFile(file, contents);
	}

	public static void writeToFile(File file, String contents) {
		try {
			FileWriter writer = new FileWriter(file);
			writer.write(contents);
			writer.flush();
			writer.close();
		} catch (IOException e) {
			e.printStackTrace();

		}

	}

	public static void appendToFile(File file, String contents) {

		try {
			FileWriter writer = new FileWriter(file, true);
			writer.write(contents);
			writer.flush();
			writer.close();
		} catch (IOException e) {
			e.printStackTrace();

		}

	}

	public static void appendToFile(String path, String contents) {

		File file = new File(path);
		appendToFile(file, contents);

	}

	public static String readFromFile(File file) {
		StringBuffer whole = new StringBuffer();
		char[] buffer = new char[256];
		try {
			BufferedReader in = new BufferedReader(
					new InputStreamReader(new FileInputStream (file),"8859_1"));
			int count;
			while ((count = in.read(buffer)) >= 0) {	
				whole.append(buffer, 0, count);
			}
			in.close();
			return new String(whole.toString().getBytes("8859_1"),"GBK");
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}
		return null;
	}
	
	public static String readFromCommonFile(String fileName){
		return readFromCommonFile(new File(fileName));
	}
	
	public static String readFromCommonFile(File file) {
		StringBuffer whole = new StringBuffer();
		char[] buffer = new char[256];
		try {
			BufferedReader in = new BufferedReader(
					new InputStreamReader(new FileInputStream (file)));
			int count;
			while ((count = in.read(buffer)) >= 0) {	
				whole.append(buffer, 0, count);
			}
			in.close();
			return new String(whole.toString());
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}
		return null;
	}

	/**
	 * Method readFromFile.
	 */
	public static String readFromFile(String path) {
		File file = new File(path);
		return readFromFile(file);

	}

	/**
	 * 
	 * 
	 */
	public static boolean compareFile(File f1, File f2) {
		if (f1 == null || f2 == null)
			return false;
		if (readFromFile(f1).equals(readFromFile(f2)))
			return true;

		return false;

	}
//	
//	public static void copy(String from, String to)	throws IOException {
//		File file = new File(from);
//		if(file.isDirectory()){
//			File[] children = file.listFiles();
//			for(File child:children){
//				String fileName = child.getAbsolutePath();
//				copy(fileName, to);
//			}
//		}else {
//			File source = new File(from);
//			File target = new File(to);
//			while (!target.exists()) {
//				FileChannel in = (new FileInputStream(source)).getChannel();
//				FileChannel out = (new FileOutputStream(target)).getChannel();
//				in.transferTo(0, source.length(), out);
//				in.close();
//				out.close();
//			}
//		}
//	}
	public static void copy(String from, String to) throws IOException{
		copy(new File(from), new File(to));
	}
	
	/**
	 * This function will copy files or directories from one location to another.
	 * note that the source and the destination must be mutually exclusive. This 
	 * function can not be used to copy a directory to a sub directory of itself.
	 * The function will also have problems if the destination files already exist.
	 * @param src -- A File object that represents the source for the copy
	 * @param dest -- A File object that represnts the destination for the copy.
	 * @throws IOException if unable to copy.
	 */
	public static void copy(File src, File dest) throws IOException {
		//Check to ensure that the source is valid...
		if (!src.exists()) {
			throw new IOException("copyFiles: Can not find source: " + src.getAbsolutePath()+".");
		} else if (!src.canRead()) { //check to ensure we have rights to the source...
			throw new IOException("copyFiles: No right to source: " + src.getAbsolutePath()+".");
		}
		//is this a directory copy?
		if (src.isDirectory()) 	{
			if (!dest.exists()) { //does the destination already exist?
				//if not we need to make it exist if possible (note this is mkdirs not mkdir)
				if (!dest.mkdirs()) {
					throw new IOException("copyFiles: Could not create direcotry: " + dest.getAbsolutePath() + ".");
				}
			}
			//get a listing of files...
			String list[] = src.list();
			//copy all the files in the list.
			for (int i = 0; i < list.length; i++)
			{
				File dest1 = new File(dest, list[i]);
				File src1 = new File(src, list[i]);
				copy(src1 , dest1);
			}
		} else { 
			//This was not a directory, so lets just copy the file
			FileInputStream fin = null;
			FileOutputStream fout = null;
			byte[] buffer = new byte[4096]; //Buffer 4K at a time (you can change this).
			int bytesRead;
			try {
				//open the files for input and output
				fin =  new FileInputStream(src);
				fout = new FileOutputStream (dest);
				//while bytesRead indicates a successful read, lets write...
				while ((bytesRead = fin.read(buffer)) >= 0) {
					fout.write(buffer,0,bytesRead);
				}
				System.out.println("Copied file ["+src.getAbsolutePath()+"] to ["+dest.getAbsolutePath()+"]");
			} catch (IOException e) { //Error copying file... 
				IOException wrapper = new IOException("copyFiles: Unable to copy file: " + 
							src.getAbsolutePath() + " to " + dest.getAbsolutePath()+".");
//				wrapper.initCause(e);
//				wrapper.setStackTrace(e.getStackTrace());
//				throw wrapper;
			} finally { //Ensure that the files are closed (if they were open).
				if (fin != null) { fin.close(); }
				if (fout != null) { fout.close(); }
			}
		}
	}
	

	public static boolean compareFile(String path1, String path2) {
		if (path1 == null || path2 == null)
			return false;
		if (readFromFile(path1).equals(readFromFile(path2)))
			return true;

		return false;

	}

	/*
	 * @author ywhu
	 */
	public static void createFile(String FileName) {
		try {
			File file = new File(FileName);
			boolean success = file.createNewFile();
			if (success) {
				System.out.println("File was created");
			} else {
				System.out.println("File already exist");
			}
		} catch (IOException e) {
		}
	}

	/*
	 * @author ywhu
	 */
	public static void deleteFile(String FileName) {
		File file = new File(FileName);
		boolean success = file.delete();
		if (success) {
			System.out.println("File was deleted");
		} else {
			System.out.println("Failed to delete file");
		}
	}

	public static void createDirectory(String path) {
		File dir = new File(path);
		if (!dir.exists()) {
			dir.mkdir();
		}
	}
	
	public static boolean isFileExist(String fileName){
		File file = new File(fileName);
		return file.exists();
	}
	
	public static void main(String[] args) {
	}

	public static String getPropertyFromFile(String key, String configFile) {
		FileInputStream file;
		Properties prop = new java.util.Properties();
		try {
			file = new java.io.FileInputStream(configFile);
			prop.load(file);
			file.close();
		} catch (java.io.FileNotFoundException e) {
			System.out.println("file not found, filename=" + configFile);
			return null;
		} catch (java.io.IOException e1) {
			System.out.println("load property file failed, filename="
					+ configFile);
		}
		String val = prop.getProperty(key);
		if (val == null) {
			System.out.println("can't find the vaule of key:"+ key);
			return null;
		}
		return val;
	}

	public static File replaceContent(File rawFile, String tobeChanged,	String changedTo) {
		String content = FileUtil.readFromFile(rawFile);
		content = content.replaceAll("xmlns=\".*\"", "");
		File result = new File("c:\\leaveMeAlone");
		FileUtil.writeToFile(result, content);
		return result;
	}

	public static String getChild(String folder, Pattern pattern) {
		String[] children = new File(folder).list();
		for(String child:children){
			Matcher matcher = pattern.matcher(child);
			if(matcher.matches()){
				return child;
			}
		}
		return null;
	}

	public static void copyToFolder(String file, String toFolder) throws IOException {
		String pureName = file.substring(file.lastIndexOf("\\"), file.length());
		copy(file, toFolder+"\\"+pureName);
	}

	public static String getFileName(String parent, Pattern pattern) {
		String[] fileNames = new File(parent).list();
		for(String name:fileNames){
			Matcher matcher = pattern.matcher(name);
			if(matcher.matches()){
				return name;
			}
		}
		return null;
	}

	public static void head(File file, String string) {
		// TODO Auto-generated method stub
		
	}


}
