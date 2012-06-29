package com.sybase.automation.framework.core.parser;

import java.io.FileNotFoundException;

import com.sybase.automation.framework.core.TestCase;



/**
 *  The DBParser class provides for reading Testcases from databases such as
 *  ASE ,Oracle and so on. not implemented .
 * @author xfu
 *
 */
public class DBTestCaseParser implements ITestCaseParser
{

    /**
	 * @see util.action.parser.Parser#parse(String)
	 */
    public TestCase parse(String source) throws FileNotFoundException
    {
        return null;
    }

}
