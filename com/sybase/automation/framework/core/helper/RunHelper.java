/*
 * Created on Mar 30, 2007
 *
 * TODO To change the template for this generated file go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
package com.sybase.automation.framework.core.helper;

import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import java.io.StringWriter;
import java.lang.reflect.Method;
import java.lang.reflect.Modifier;
import java.text.SimpleDateFormat;
import java.util.*;

import javax.mail.MessagingException;
import javax.mail.internet.AddressException;
import junit.framework.Assert;
import com.rational.test.ft.*;
import com.rational.test.ft.script.RationalTestScript;
import com.sybase.automation.framework.common.FileUtil;
import com.sybase.automation.framework.common.MailUtil;
import com.sybase.automation.framework.core.TestRunner;

/**
 * @author juanchen
 * 
 * TODO To change the template for this generated type comment go to Window -
 * Preferences - Java - Code Style - Code Templates
 */
public class RunHelper extends RationalTestScript {

	protected static TestRunner runner = new TestRunner(false); 

	//	count the elapse time of each testcase
	protected static Timer timer = new Timer();

	//store the test results of each testcase
	private static java.util.List testResults = new ArrayList();

	//whether to send a mail with test result
	private static int timeCount = 0;

	/**
	 * run test case
	 * 
	 * @param path :
	 *            the relative path of the CSV file
	 * @throws Throwable
	 * @throws IOException
	 */
	public void runScript(String path) throws IOException, Throwable {
		/* before run */
		notice(path + " Start!");
		timer.reset();
		try {
			/* run the test case */
			runner.run(path);
		} catch (Throwable e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		/* after run */
		timeCount += timer.elapsedTime();
		String result = runner.getCurrentTestCase().getResult();
		TestResult result1 = new TestResult(path, timer
				.getFormattedElapsedTime(), result);
		testResults.add(result1);
	}
	
	/**
	 * run test case for dubeg
	 * 
	 * @param path :
	 *            the relative path of the CSV file
	 * @throws Throwable
	 * @throws IOException
	 */
	public void runScript(String path, int start, int end) throws IOException, Throwable {
		/* before run */
		notice(path + " Start!");
		timer.reset();
		try {
			/* run the test case */
			runner.run(path,start,end);
		} catch (Throwable e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public static void notice(String str) {
		System.out.println(getNotice(str));
	}

	public static void notice() {
		notice("");
	}

	public static String getNotice(String str) {
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

	public void onTerminate() {

		//buildResutls();

		try {
			String mailContents = showResults();
		} catch (RuntimeException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			throw new RuntimeException("show results error");
		}

		//		sendResults(mailContents);

	}

	/**
	 * @param mailContents
	 */
	private void sendResults(String mailContents) {
		try {
			MailUtil.send(25, "TestResult@XDE.com", "juanchen@sybase.com",
					"Test Result", mailContents);
		} catch (AddressException e) {
			System.out.println("Sending mail failed");
			e.printStackTrace();
		} catch (MessagingException e) {
			System.out.println("Sending mail failed");
			e.printStackTrace();
		}

	}

	//***********************************other methods**************/
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
			Assert.assertTrue(seconds >= 0);
			String hours = Integer.toString(seconds / 3600);
			String mins = Integer.toString(seconds / 60 % 60);
			String secs = Integer.toString(seconds % 60);
			return hours + ":" + mins + ":" + secs;
		}

	}

	//	private void buildResutls() {
	//		File htmlResultFile = waitForHtmlResult();
	//		String resultFile = FileUtil.readFromFile(htmlResultFile);
	//		for (Iterator iter = testResults.iterator(); iter.hasNext();) {
	//			TestResult result = (TestResult) iter.next();
	//			String line = findLine(resultFile, result.get_scriptName());
	//			if (line.indexOf("FAIL") != -1)
	//				result.setResult("FAIL");
	//			else
	//				result.setResult("PASS");
	//		}
	//	}

	//	private File waitForHtmlResult() {
	//		int total = 0;
	//		File htmlResult = new File(getLogPath());
	//		while (!htmlResult.exists()) {
	//			sleep(1);
	//		}
	//		return htmlResult;
	//	}
	/**
	 * Method showResults.
	 */
	private String showResults() {
		String allTime = Timer.getFormattedElapsedTime(timeCount);
		Object[] testResult_array = testResults.toArray();
		/* no sort */
		//Arrays.sort(testResult_array);
		StringBuffer results = new StringBuffer();
		results.append("\n");
		results.append(getNotice("Test Results"));
		int failCount = 0;
		int totalCount = testResults.size();
		System.out.println("total count is:" + totalCount);
		//
		for (int i = 0; i < testResult_array.length; i++) {
			TestResult element = (TestResult) testResult_array[i];
			results.append(element).append("\n");
			if (element.getResult().equals("FAIL"))
				failCount++;
		} // summerize the info to display and send
		if (totalCount>0){
		results.append(getNotice(" Cases Failed:" + failCount + "/"
				+ totalCount + "  **" + "  Pass Ratio:"
				+ (100 - failCount * 100 / totalCount) + "%  **"
				+ "  Time Consumed:" + allTime));
		}else{
			results.append(getNotice(" No Test Case is running"));
		}
		System.out.println(results.toString());
		return results.toString();
//		return "ignore";
	}

	//	private String findLine(String str, String scirptName) {
	//
	//		String[] contents = str.split("\n");
	//		for (int i = 0; i < contents.length; i++) {
	//			if (contents[i].indexOf("Script end") != -1
	//					&& contents[i].indexOf(scirptName) != -1)
	//				return contents[i - 2];
	//		}
	//		return null;
	//	}

	public class TestResult implements Comparable {
		String _scriptName;

		String _result;

		String _elapseTime;

		/**
		 * Constructor for TestResult.
		 */
		public TestResult(String scirptName, String elapseTime, String result) {
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
}
