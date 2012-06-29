package com.sybase.automation.framework.widget.eclipse;

import com.rational.test.ft.*;
import com.rational.test.ft.object.interfaces.*;
import com.sybase.automation.framework.widget.interfaces.*;

/**
 * widget to encapsulate the behavior the StyledText Test Object
 * @author chuang2
 *
 */
public class StyledText implements ITextBox
{
    private TextScrollTestObject _StyledText;
    //private ScrollTestObject 	p_obEditor=null;
    private TopLevelTestObject p_obWin=null;
    private int 				p_lineHeight=0;
    private String				p_delimiter=null;

    public StyledText(TextScrollTestObject styletext)
    {
    	_StyledText = styletext;
    }
    
    public StyledText(TextScrollTestObject sOb, TopLevelTestObject winOb)
    {
    	_StyledText = sOb;
        p_obWin = winOb;

        p_lineHeight=Integer.parseInt(_StyledText.getProperty("lineHeight").toString());
        if(p_lineHeight==0)
        {
            throw new UnsupportedSubitemException("get line height failed");
        }
        p_delimiter = _StyledText.getProperty("lineDelimiter").toString();
    }

    public String getAllText()
    {
        return _StyledText.getTestData("text").toString();
    }

    public String[] getLines()
    {
        String[] 	buf;
        String[] 	tmpBuf;
        String 		allText;
        int		lineCount=0;
        int		start=0;

        allText = getAllText();
        while(start>=0)
        {
            start = allText.indexOf(p_delimiter, start+1);
            lineCount++;
        }

        buf = new String[lineCount];
        tmpBuf = allText.split(p_delimiter);
        for(int i=0; i<tmpBuf.length; i++)
        {
            buf[i]=tmpBuf[i];
        }
        for(int i=tmpBuf.length; i<buf.length; i++)
        {
            buf[i]="";//add ending blank lines
        }

        return buf;
    }

    public String getLines(int start, int end)
    {
        String 		lines="";
        String[] 	buf;

        buf = getLines();

        start = start < 1 ? 1: start;
        end = end > buf.length ? buf.length : end;

        for(int i=start-1; i<end; i++)
        {
            if(i>start-1)
            {
                lines += p_delimiter;
            }
            lines += buf[i];
        }

        return lines;
    }

    public void insertLines(int line, String txt)
    {
        int lineCount=0;
        int visibleLineCount=0;
        int topIndex=0;
        int topPixel;
        int i;
        java.awt.Rectangle size;
        java.awt.Point pos = new java.awt.Point();

        lineCount=Integer.parseInt(_StyledText.getProperty("lineCount").toString());
        line = line < 1 ? 1 : line;
        line = line > lineCount ? lineCount : line;

        size = (java.awt.Rectangle)_StyledText.getProperty("clientArea");
        visibleLineCount=size.height / p_lineHeight;		

        topIndex=Integer.parseInt(_StyledText.getProperty("topIndex").toString());
        for(i=0; i<topIndex-line+1; i++)
        {
        	_StyledText.scrollLineUp();
        }
        topIndex=Integer.parseInt(_StyledText.getProperty("topIndex").toString());
        for(i=0; i<line-(topIndex+visibleLineCount)+1; i++)
        {
        	_StyledText.scrollLineDown();
        }

        topIndex=Integer.parseInt(_StyledText.getProperty("topIndex").toString());
        topPixel=Integer.parseInt(_StyledText.getProperty("topPixel").toString());
        if(topIndex == (topPixel / p_lineHeight))
        {
            topIndex ++;//if the top edge is the line bottom, I need adjust it
        }

        pos.x = 0;
        if(topIndex == 0)
        {
            pos.y = 0;	//when it is top most, I need adjust it
        }
        else
        {
            pos.y = (p_lineHeight - (topPixel % p_lineHeight)) + 
                (line - topIndex - 1) * p_lineHeight;
        }

        _StyledText.click(pos);
        p_obWin.inputKeys("{ExtHome}{ENTER}{ExtUp}{ExtHome}");
        p_obWin.inputChars(txt);
    }

    
	/* (non-Javadoc)
	 * @see framework.widget.interfaces.ITestBox#setText(java.lang.String)
	 */
	public void setText(String s) {
		throw new IllegalStateException("This function is not implemented");
	}

	/* (non-Javadoc)
	 * @see framework.widget.interfaces.ITestBox#getText()
	 */
	public String getText() {
		throw new IllegalStateException("This function is not implemented");
	}

	/* (non-Javadoc)
	 * @see framework.widget.interfaces.ITestBox#pasteText()
	 */
	public void pasteText() {
		throw new IllegalStateException("This function is not implemented");
	}

	/* (non-Javadoc)
	 * @see framework.widget.interfaces.ITestBox#clearText()
	 */
	public void clearText() {
		throw new IllegalStateException("This function is not implemented");
	}

	/* (non-Javadoc)
	 * @see com.sybase.automation.framework.widget.interfaces.ITextBox#pasteText(java.lang.String)
	 */
	public void pasteText(String txt) {
		// TODO Auto-generated method stub
		
	}

	public void createVPFile(String actual, String vpPath) {
		// TODO Auto-generated method stub
		
	}

	public void verifyText(String expected) {
		// TODO Auto-generated method stub
		
	}

	public void verifyText(String expected, boolean fromFile) {
		// TODO Auto-generated method stub
		
	}
}
