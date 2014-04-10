package com.controller.dataAccess;

import com.model.book.BookListRoot;

public class BookXMLHandler extends AbstractXMLHandler {
	private static String XSD_PATH = "D://master//PS//As//BookStore//src//com//resources//bookSchema.xsd";
    private static String XML_FILE_PATH = "D://master//PS//As//BookStore//src//com//resources//data//books.xml";

    public BookXMLHandler(){
    	setXmlDataFile(XML_FILE_PATH);
    	setXsdPath(XSD_PATH);
    	this.classToHandle = BookListRoot.class;
    }
	@Override
	public void setXsdPath(String xsdPath) {
		this.xsdPath = XSD_PATH;

	}

	@Override
	public void setXmlDataFile(String xmlDataFile) {
		this.xmlDataFile = XML_FILE_PATH;

	}

	@Override
	public void setClassToHandle(Class classToWrite) {
		this.classToHandle = classToWrite;

	}

}
