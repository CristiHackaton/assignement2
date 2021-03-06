package com.controller.dataAccess;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;

import javax.xml.XMLConstants;
import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;
import javax.xml.bind.Unmarshaller;
import javax.xml.validation.Schema;
import javax.xml.validation.SchemaFactory;

import org.xml.sax.SAXException;


public abstract class AbstractXMLHandler {
    protected String xsdPath;
    protected String xmlDataFile;
    protected Class classToHandle;

    public void writeToFile(final Object objToWrite) throws JAXBException, SAXException {

        SchemaFactory sf = SchemaFactory.newInstance(XMLConstants.W3C_XML_SCHEMA_NS_URI);
        File file = new File(xsdPath);
        
		Schema schema = sf.newSchema(file);
        
        JAXBContext jc = JAXBContext.newInstance(classToHandle);

        Marshaller marshaller = jc.createMarshaller();
        marshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, true);
        marshaller.setSchema(schema);
        try {
			marshaller.marshal(objToWrite, new FileOutputStream(new File(xmlDataFile)));
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
    }

    public Object readFromFile() throws JAXBException, SAXException {

        SchemaFactory sf = SchemaFactory.newInstance(XMLConstants.W3C_XML_SCHEMA_NS_URI);
        Schema schema = sf.newSchema(new File(xsdPath));

        JAXBContext jc = JAXBContext.newInstance(classToHandle);

        Unmarshaller unmarshaller = jc.createUnmarshaller();
        unmarshaller.setSchema(schema);
        File file = new File(xmlDataFile);
        if(file.length()!=0){
        	
        	Object result = unmarshaller.unmarshal(file);
        	return result;
        }return null;
    }

    public String getXsdPath() {
        return xsdPath;
    }

    public String getXmlDataFile() {
        return xmlDataFile;
    }

    public abstract void setXsdPath(String xsdPath);

    public abstract void setXmlDataFile(String xmlDataFile);

    public abstract void setClassToHandle(Class classToWrite);

}
