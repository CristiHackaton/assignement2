package com.controller.dataAccess;

import com.model.book.BookListRoot;
import com.model.sell.SellListRoot;

public class SellXMLHandler extends AbstractXMLHandler {
	private static String XSD_PATH = "D://master//PS//As//BookStore//src//com//resources//sellSchema.xsd";
    private static String XML_FILE_PATH = "D://master//PS//As//BookStore//src//com//resources//data//sells.xml";

    public SellXMLHandler(){
    	setXmlDataFile(XML_FILE_PATH);
    	setXsdPath(XSD_PATH);
    	this.classToHandle = SellListRoot.class;
    }

	@Override
	public void setXsdPath(String xsdPath) {
	this.xsdPath= XSD_PATH;

	}

	@Override
	public void setXmlDataFile(String xmlDataFile) {
		this.xmlDataFile=XML_FILE_PATH;

	}

	@Override
	public void setClassToHandle(Class classToWrite) {
		this.classToHandle=classToWrite;

	}

}
