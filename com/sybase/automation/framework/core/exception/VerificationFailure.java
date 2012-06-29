package com.sybase.automation.framework.core.exception;


/**
 * Thrown when a verification failed during the execution of testcase.
 * @author xfu
 *
 * 
 */
public class VerificationFailure extends Error 
{

    private String fExpected;
    private String fActual;

    public VerificationFailure() 
    {
    }
    public VerificationFailure(String message) 
    {
        super(message);
    }

    public VerificationFailure(
        String message,
        String expected,
        String actual) 
    {
        super(message);
        fExpected = expected;
        fActual = actual;
    }

    public VerificationFailure(String expected, String actual) 
    {

        fExpected = expected;
        fActual = actual;
    }

    public String getMessage() 
    {
        if (fExpected == null && fActual == null)
        return super.getMessage();
        return format(super.getMessage(), fExpected, fActual);
    }

    private String format(String message, Object expected, Object actual) 
    {
        String formatted = "";
        if (message != null)
        formatted = message + " ";
        return formatted
            + "expected:{"
            + expected
            + "} but was:{"
            + actual
            + "}";
    }

}
