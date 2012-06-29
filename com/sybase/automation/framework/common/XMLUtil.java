package com.sybase.automation.framework.common;

import java.io.*;
import java.util.Map;

import org.dom4j.Document;
import org.dom4j.DocumentException;
import org.dom4j.Element;
import org.dom4j.Node;
import org.dom4j.io.OutputFormat;
import org.dom4j.io.SAXReader;
import org.dom4j.io.XMLWriter;

/**
 * A collection of utilities on XML
 * 
 * @author xfu
 */
public class XMLUtil
{

    /**
     * Save a XML doc to file with pretty format
     * 
     * @param file
     * @param doc
     * @throws IOException
     */
    public static void SaveFile(File file, Document doc) throws IOException
    {
        XMLWriter writer = new XMLWriter(new FileWriter(file), OutputFormat.createPrettyPrint());
        writer.write(doc);
        writer.flush();
        writer.close();
    }

    /**
     * Save a XML doc to file with pretty format
     * 
     * @param file
     * @param doc
     * @throws IOException
     */
    public static void SaveFile(String filePath, Document doc) throws IOException
    {
        SaveFile(new File(filePath), doc);
    }

    /**
     * Read a xml contents to Document from file
     * 
     * @param file
     * @param doc
     * @throws IOException
     */
    public static Document readFile(File file) throws DocumentException
    {
        SAXReader reader = new SAXReader();
        return reader.read(file);

    }

    /**
     * Read a xml contents to Document from file
     * 
     * @param file
     * @param doc
     * @throws IOException
     */
    public static Document readFile(String filePath) throws DocumentException
    {
        SAXReader reader = new SAXReader();
        return reader.read(new File(filePath));

    }

}
