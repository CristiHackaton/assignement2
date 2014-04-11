package com.controller.dataAccess;

import com.model.book.BookListRoot;

public class BookXMLHandler extends AbstractXMLHandler {
    private static String XSD_PATH = "src//com//resources//bookSchema.xsd";
    private static String XML_FILE_PATH = "src//com//resources//data//books.xml";

    public BookXMLHandler() {
        setXmlDataFile(XML_FILE_PATH);
        setXsdPath(XSD_PATH);
        this.classToHandle = BookListRoot.class;
    }

    @Override
    public void setXsdPath(final String xsdPath) {
        this.xsdPath = XSD_PATH;

    }

    @Override
    public void setXmlDataFile(final String xmlDataFile) {
        this.xmlDataFile = XML_FILE_PATH;

    }

    @Override
    public void setClassToHandle(final Class classToWrite) {
        this.classToHandle = classToWrite;

    }

}
