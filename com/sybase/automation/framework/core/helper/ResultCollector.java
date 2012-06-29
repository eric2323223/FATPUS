/*
 * Created on Jul 23, 2007
 *
 * TODO To change the template for this generated file go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
package com.sybase.automation.framework.core.helper;

import java.io.File;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Hashtable;
import java.util.Iterator;
import java.util.Set;
import java.util.TreeMap;

import org.dom4j.DocumentException;
import org.jawin.COMException;

import com.rational.test.ft.application.rational_ft_impl;
import com.rational.test.ft.script.RationalTestScript;
import com.rational.test.ft.services.ILog;
import com.sybase.automation.framework.common.FileUtil;
import com.sybase.automation.framework.core.cleanup.ICleanUp;
import com.sybase.automation.framework.core.cleanup.ResetEclipse;
import com.sybase.automation.framework.tool.ComponentGenerator;
import com.sybase.automation.framework.widget.factory.EclipseWidgetFactory;

/**
 * @author xfu
 * 
 */
public class ResultCollector extends RationalTestScript {
	public ICleanUp cleaner = null;

	//count the elapse time of each testcase
	private Timer timer = new Timer();

	//store the test results of each testcase
	private java.util.List testResults = new ArrayList();

	//weather to send a mail with test result
	private int timeCount = 0;

//	private boolean sendMail = false;

	/**
	 * @return the root path of this project
	 */
	protected String getProjectRootPath() {
		// TODO Auto-generated method stub
		return (String) getOption("rt.datastore");
	}

	/**
	 * pretty-print a hashtable (sorted)
	 */
	protected static void printHash(Hashtable h) {
		TreeMap sortedMap = new TreeMap(h);
		Set keySet = sortedMap.keySet();
		Iterator iter = keySet.iterator();

		while (iter.hasNext()) {
			Object key = iter.next();
			Object value = h.get(key);
			System.out.println(key + " = " + value);

		}
	}

	/**
	 * pretty-print a array (sorted)
	 */
	protected static void printArray(Object[] array) {
		for (int i = 0; i < array.length; i++) {
			System.out.println(i + ": " + array[i]);
		}
	}

	protected String normalize(String input) {
		String output;
		output = input.replaceAll("\r", "");
		output = output.replaceAll("\n", "");
		return output;
	}

	//	************************CallScript Overriding**********************
	/**
	 * @see com.rational.test.ft.script.RationalTestScript#callScript(String)
	 */
	protected Object callScript(String arg0) {

		return callScript(arg0, null);

	}

	/**
	 * @see com.rational.test.ft.script.RationalTestScript#callScript(String,
	 *      Object[]) 1.ensure continuous excution of script regardless of any
	 *      interruption 2.support "F11" hotkey 3.print the start/end/execution
	 *      time information of each scripts
	 */
	public Object callScript(String arg0, Object[] arg1) {
		notice(arg0 + " Start!");
		timer.reset();
		try {
			super.callScript(arg0, arg1);

		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			timeCount += timer.elapsedTime();
			testResults.add(new TestResult(arg0, timer
					.getFormattedElapsedTime()));
			try {
				//==do some clean up stuff
				new ResetEclipse().doClean();
			} catch (COMException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
				logError("Fatal Error while cleaning the eclipse");
			}
		}
		return null;
	}

	public static class Timer {
		private long start = 0;

		/**
		 * constructor - starts the timer.
		 */
		public Timer() {
			reset();
		}

		/**
		 * Reset the timer to the current time.
		 */
		public void reset() {
			start = System.currentTimeMillis();
		}

		/**
		 * Returns elapsed time (since timer was created) in seconds
		 */
		public double elapsedTime() {
			long end = System.currentTimeMillis();
			return (end - start) / 1000;
		}

		/**
		 * Returns true if elapsed time (since timer was created) is greater
		 * than seconds
		 */
		public boolean isTimeUp(double seconds) {
			return elapsedTime() > seconds;
		}

		/**
		 *  
		 */
		public String getFormattedElapsedTime() {
			int time = (int) elapsedTime();
			String hours = Integer.toString(time / 3600);
			String mins = Integer.toString(time / 60 % 60);
			String secs = Integer.toString(time % 60);
			return hours + ":" + mins + ":" + secs;
		}

		public static String getFormattedElapsedTime(int seconds) {
			String hours = Integer.toString(seconds / 3600);
			String mins = Integer.toString(seconds / 60 % 60);
			String secs = Integer.toString(seconds % 60);
			return hours + ":" + mins + ":" + secs;
		}
	}

	public void onTerminate() {

		buildResutls();
		String mailContents = showResults();
		
		System.out.println(mailContents);

	}

	/**
	 * @return
	 */
	private String showResults() {
		String allTime = Timer.getFormattedElapsedTime(timeCount);
		Object[] testResult_array = testResults.toArray();
		Arrays.sort(testResult_array);
		StringBuffer results = new StringBuffer();
		results.append("\n");
		results.append(getNotice("Test Results"));
		int failCount = 0;
		int totalCount = testResults.size();
		//
		for (int i = 0; i < testResult_array.length; i++) {
			TestResult element = (TestResult) testResult_array[i];
			results.append(element).append("\n");
			if (element.getResult().equals("FAIL"))
				failCount++;
		} // summerize the info to display and send
		results.append(getNotice(" Cases Failed:" + failCount + "/"
				+ totalCount + "  **" + "  Pass Ratio:"
				+ (100 - failCount * 100 / totalCount) + "%  **"
				+ "  Time Consumed:" + allTime));
		System.out.println(results.toString());
		return results.toString();
	}

	/**
	 *  
	 */
	private void buildResutls() {
		File htmlResultFile = waitForHtmlResult();
		String resultFile = FileUtil.readFromFile(htmlResultFile);
		for (Iterator iter = testResults.iterator(); iter.hasNext();) {
			TestResult result = (TestResult) iter.next();
			String line = findLine(resultFile, result.get_scriptName());
			if (line.indexOf("FAIL") != -1)
				result.setResult("FAIL");
			else
				result.setResult("PASS");
		}

	}

	private String findLine(String str, String scirptName) {

		String[] contents = str.split("\n");
		for (int i = 0; i < contents.length; i++) {
			if (contents[i].indexOf("Script end") != -1
					&& contents[i].indexOf(scirptName) != -1)
				return contents[i - 2];
		}
		return null;
	}

	private File waitForHtmlResult() {
		int total = 0;
		File htmlResult = new File(getLogPath());
		System.out.println(htmlResult.exists());
		while (!htmlResult.exists()) {
			sleep(1);
		}
		return htmlResult;
	}

	private String getLogPath() {
		ILog log = rational_ft_impl.getLog();
		String path = log.getLogDirectory() + "\\" + log.getLogFilename();
		path = path.substring(0, path.length() - 5);
		path = path + "frame.html";
		System.out.println(path);
		return path;

	}

	public class TestResult implements Comparable {
		String _scriptName;

		String _result;

		String _elapseTime;

		/**
		 * Constructor for TestResult.
		 */
		public TestResult(String scirptName, String result, String elapseTime) {
			_scriptName = scirptName;
			_result = result;
			_elapseTime = elapseTime;
		}

		/**
		 *  
		 */
		public TestResult(String scirptName, String elapseTime) {
			_scriptName = scirptName;
			_elapseTime = elapseTime;
		}

		/**
		 * @see java.lang.Object#toString()
		 */
		public String toString() {
			return _scriptName + "\t" + _result + "\t Time: " + _elapseTime;
		}

		/**
		 * Returns the _scriptName.
		 * 
		 * @return String
		 */
		public String get_scriptName() {
			return _scriptName;
		}

		/**
		 * Sets the _isSuccess.
		 * 
		 * @param _isSuccess
		 *            The _isSuccess to set
		 */
		public void setResult(String result) {
			_result = result;
		}

		/**
		 * Returns the test result.
		 * 
		 * @return String
		 */
		public String getResult() {
			return _result;
		}

		/**
		 * @see java.lang.Comparable#compareTo(Object)
		 */
		public int compareTo(Object o) {
			TestResult other = (TestResult) o;
			return _result.compareTo(other.getResult());
		}

		/**
		 * Returns the _elapseTime.
		 * 
		 * @return String
		 */
		public String getElapseTime() {
			return _elapseTime;
		}

	}

	protected void notice(String str) {
		System.out.println(getNotice(str));
	}

	protected void notice() {
		notice("");
	}

	protected String getNotice(String str) {
		String decorator = "*******************************************";
		int maxLen = 80;
		while (maxLen < str.length() + 2) {
			maxLen = maxLen + 10;
		}
		int count = (maxLen - str.length()) / 2;
		String output = decorator.substring(0, count);
		if (str.length() % 2 == 0)
			return "\n" + output + str + output + "\n";
		else
			return "\n" + output + "*" + str + output + "\n";
	}
}