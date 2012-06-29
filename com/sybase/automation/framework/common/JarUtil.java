package com.sybase.automation.framework.common;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Enumeration;
import java.util.jar.JarEntry;
import java.util.jar.JarFile;
import java.util.zip.ZipEntry;

import org.dom4j.DocumentException;

import component.entity.DUHelper;

public class JarUtil {
	
	public static File extractFile(String jarFile, String fileToBeExtracted, String destDir) throws IOException {
		JarFile jar = new JarFile(jarFile);
		Enumeration enumeration = jar.entries();
		while (enumeration.hasMoreElements()) {
			JarEntry file = (JarEntry) enumeration.nextElement();
			if(file.getName().equals(fileToBeExtracted)){
				File outputFile = new File(destDir + File.separator	+ file.getName());
				InputStream is = jar.getInputStream(file); 
				FileOutputStream fos = new FileOutputStream(outputFile);
				
				while (is.available() > 0) { 
					fos.write(is.read());
					
				}
				fos.close();
				is.close();
				return outputFile;
			}
		}
		return null;
	}
	
	
	
	public static void main(String[] args) throws IOException, DocumentException{
		File file = extractFile("C:\\Documents and Settings\\eric\\workspace\\ObjectQuery\\Deployment\\ObjectQuery.pkg\\ObjectQuery1.jar", "deployment_unit.xml", "C:\\Documents and Settings\\eric\\workspace\\ObjectQuery\\Deployment\\ObjectQuery.pkg");


	}

}
