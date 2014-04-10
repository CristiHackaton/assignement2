package com.controller.dataAccess;

import java.io.File;

import javax.xml.XMLConstants;
import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;
import javax.xml.bind.Unmarshaller;
import javax.xml.validation.Schema;
import javax.xml.validation.SchemaFactory;

import org.xml.sax.SAXException;

import com.model.user.UserList;

public abstract class AbstractXMLHandler {
    protected String xsdPath;
    protected String xmlDataFile;
    protected Class classToHandle;

    public void writeToFile(final Object objToWrite) throws JAXBException, SAXException {

        SchemaFactory sf = SchemaFactory.newInstance(XMLConstants.W3C_XML_SCHEMA_NS_URI);
        Schema schema = sf.newSchema(new File(xsdPath));

        JAXBContext jc = JAXBContext.newInstance(UserList.class);

        Marshaller marshaller = jc.createMarshaller();
        marshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, true);
        marshaller.setSchema(schema);
        marshaller.marshal(objToWrite, System.out);
    }

    public Object readFromFile(final Object objToWrite) throws JAXBException, SAXException {

        SchemaFactory sf = SchemaFactory.newInstance(XMLConstants.W3C_XML_SCHEMA_NS_URI);
        Schema schema = sf.newSchema(new File(xsdPath));

        JAXBContext jc = JAXBContext.newInstance(classToHandle);

        Unmarshaller unmarshaller = jc.createUnmarshaller();
        unmarshaller.setSchema(schema);
        Object result = unmarshaller.unmarshal(new File(xmlDataFile));
        return result;
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
