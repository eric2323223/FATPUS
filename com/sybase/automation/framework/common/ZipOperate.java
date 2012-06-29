package com.sybase.automation.framework.common;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.jar.JarEntry;
import java.util.jar.JarOutputStream;

public class ZipOperate {

	public void jar(String jarFileName, String inputFile) throws FileNotFoundException, IOException {
		jar(jarFileName, new File(inputFile));
	}

	private void jar(String jarFileName, File inputFile) throws FileNotFoundException, IOException {
		JarOutputStream out = null;
		try {
			out = new JarOutputStream(new FileOutputStream(jarFileName));
			jar(out, inputFile, "");

		}  finally {
			try {
				out.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}

	private void jar(JarOutputStream out, File f, String base)
			throws IOException {
		if (f.isDirectory()) {
			File[] fl = f.listFiles();
			out.putNextEntry(new JarEntry(base + "/"));
			base = base.length() == 0 ? "" : base + "/";
			for (int i = 0; i < fl.length; i++) {
				jar(out, fl[i], base + fl[i].getName());
			}
		} else {
			out.putNextEntry(new JarEntry(base));

			FileInputStream in = null;
			try {
				in = new FileInputStream(f);
				int b;
				while ((b = in.read()) != -1) {
					out.write(b);
				}
			} catch(Exception e){
				e.printStackTrace();
			}
			finally {
				in.close();
			}

		}
	}

	public static void main(String[] args)  throws IOException {
		new ZipOperate().jar("c:\\documents and settings\\eric\\workspace\\integration_06.jar", "C:\\genfiles\\java\\classes");
	}

}