package com.sybase.automation.framework.core.parser;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStreamReader;

import org.apache.log4j.Logger;

import com.sybase.automation.framework.common.ResourceUtil;
import com.sybase.automation.framework.common.Utilities;
import com.sybase.automation.framework.core.TestCase;

import au.com.bytecode.opencsv.CSVReader;

/**
 * The CSVParser class provides for reading Testcases from CSV file
 * 
 * @author xfu
 */
public class CSVTestCaseParser implements ITestCaseParser {
	/**
	 * @see util.action.parser.Parser#parse(String)
	 */
	public TestCase parse(String source) throws FileNotFoundException,
			IOException {
		TestCase testcase = new TestCase(source);
		//        CSVReader reader = new CSVReader(new
		// FileReader(Utilities.getProjectRoot() + source));
		CSVReader reader = new CSVReader(new InputStreamReader(
				new FileInputStream(Utilities.getProjectRoot() + source),
				"UTF-8"));
		testcase.setActions(reader.readAll());
		return testcase;
	}

}
