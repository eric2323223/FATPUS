package com.sybase.automation.framework.core.parser;

import java.io.FileNotFoundException;
import java.io.IOException;

import com.sybase.automation.framework.core.TestCase;

/**
 * The Parser interface provides for reading Testcases from various sources.
 * 
 * @author xfu
 *  
 */
public interface ITestCaseParser 
{

    TestCase parse(String source) throws FileNotFoundException, IOException;

}
