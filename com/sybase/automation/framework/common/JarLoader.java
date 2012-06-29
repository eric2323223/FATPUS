package com.sybase.automation.framework.common;
/*
 * Jars loader
*/
 
import java.util.jar.JarFile;
import java.util.jar.JarEntry;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Enumeration;
import java.net.URLClassLoader;
import java.net.URL;
import java.net.MalformedURLException;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
 
public class JarLoader {
	private static URLClassLoader classLoader = null;
	private static String jarFile;
	
	public static void init(String jar) throws MalformedURLException{
		System.out.println("Load classes from jar: "+jar);
		if (jarFile == null) {
			jarFile = jar;
			URL url[] = new URL[] { new URL("file:" + jarFile) };
			classLoader = new URLClassLoader(url);
		}
	}
	
	private static JarFile getJarFile() throws IOException{
		return new JarFile(jarFile);
	}
 
 
  /* Return the list of the jar's classes */
    public static ArrayList<String> getClassList() throws IOException {
       ArrayList<String> classList = new ArrayList<String>();
        JarFile jarFile = null;
        try {
            jarFile = getJarFile();
            Enumeration<JarEntry> entries = jarFile.entries();
            while (entries.hasMoreElements()) {
                String entry = entries.nextElement().getName();
                if (entry.endsWith(".class")) {
                    entry = entry.replace("/", ".").substring(0, entry.length()-6);
                    classList.add(entry);
                }
            }
        }
        finally {
            jarFile.close();
        }
 
        return classList;            
    }
    
    public static Object createInstance(String classname){
    	if (jarFile == null) {
			throw new RuntimeException("JarLoader is not initiated!");
		}
		try {
			Class clazz = loadClass(classname);
			return clazz.newInstance();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
    }
    
    public static Class[] getParameters(String classname, String method) {
		if (jarFile == null) {
			throw new RuntimeException("JarLoader is not initiated!");
		}
		try {
			Class clazz = loadClass(classname);
			Method[] methods = clazz.getDeclaredMethods();
			for (Method mtd : methods) {
				if (mtd.getName().equals(method)) {
//					System.out.print("method <"+method+"> parameter: ");
//					for(Class cl:mtd.getParameterTypes()){
//						System.out.println(cl.getName() +" ");
//					}
					return mtd.getParameterTypes();
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;

	}
 
    public static Class loadClass(String className) throws ClassNotFoundException  {
        return classLoader.loadClass(className);        
    }
    
	public static Object invokeClassMethod(String classname, String method,	Class[] types, Object[] param) {
		if(jarFile==null){
			throw new RuntimeException("JarLoader is not initiated!");
		}
		Object result = null;
		try {
			Class clazz = loadClass(classname);
			Method m = clazz.getDeclaredMethod(method, types);
//			m.setAccessible(true);
			result = m.invoke(clazz, param);
		} catch (InvocationTargetException e) {
			e.getCause().printStackTrace();

		} catch (Exception e) {
			e.printStackTrace();
		}
		return result;
	}
	
	
	public static Object invokeClassMethod2(String classname, String method,	int parameterCount, Object[] param) {
		if(jarFile==null){
			throw new RuntimeException("JarLoader is not initiated!");
		}
		Object result = null;
		try {
			Class clazz = loadClass(classname);
			Method m = findMethod(clazz, method, parameterCount);
//			m.setAccessible(true);
			result = m.invoke(clazz, param);
		} catch (InvocationTargetException e) {
			e.getCause().printStackTrace();
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		return result;
	}
	
	public static Object invokeInstanceMethod(Object obj, String classname, String method,
			Class[] claz, Object[] param) {
		if(jarFile==null){
			throw new RuntimeException("JarLoader is not initiated!");
		}
		Object result = null;
		try {
			Class clazz = loadClass(classname);
			Method m = clazz.getDeclaredMethod(method, claz);
//			m.setAccessible(true);
			result = m.invoke(obj, param);
		} catch (InvocationTargetException e) {
			e.getCause().printStackTrace();

		} catch (Exception e) {
			e.printStackTrace();
		}
		return result;
	}
	
	public static Object invokeInstanceMethod2(Object obj, String classname, String method,
			int parameterCount, Object[] param) {
		if(jarFile==null){
			throw new RuntimeException("JarLoader is not initiated!");
		}
		Object result = null;
		try {
			Class clazz = loadClass(classname);
			Method m = findMethod(clazz, method, parameterCount);
//			m.setAccessible(true);
			result = m.invoke(obj, param);
		} catch (InvocationTargetException e) {
			e.getCause().printStackTrace();
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		return result;
	}
	
	private static Method findMethod(Class clazz, String method, int parameterCount){
		Method[] allMethods = clazz.getMethods();
		for(Method m:allMethods){
			if(m.getName().equals(method)&&
					m.getParameterTypes().length == parameterCount){
				return m;
			}
		}
		return null;
	}

	public static Class[] getParameters2(String classname,	String method, int parameterCount) {
		if (jarFile == null) {
			throw new RuntimeException("JarLoader is not initiated!");
		}
		try {
			Class clazz = loadClass(classname);
			Method[] methods = clazz.getDeclaredMethods();
			for (Method mtd : methods) {
				if (mtd.getName().equals(method) && mtd.getParameterTypes().length==parameterCount) {
					return mtd.getParameterTypes();
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}
	
	
	//Only use this method when check license
	public static void setClassLoader(URLClassLoader loader){
		classLoader = loader;
	}

}
