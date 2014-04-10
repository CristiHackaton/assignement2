package com.controller.dataAccess;

import javax.xml.bind.JAXBException;

import org.xml.sax.SAXException;

import com.model.user.User;
import com.model.user.UserList;

public class UserXMLHandler extends AbstractXMLHandler {
    private static String XSD_PATH = "src/com/resources/userSchema.xsd";
    private static String XML_FILE_PATH = "src/com/resoruces/data/users.xml";

    public UserXMLHandler(final Class classToWrite) {
        setXmlDataFile(XML_FILE_PATH);
        setXsdPath(XSD_PATH);
        setClassToHandle(classToWrite);
    }

    public static void main(final String args[]) {
        UserList list = new UserList();
        User u = new User();
        u.setName("danny");
        u.setPassword("danny");
        u.setPhoneNumber("123243");
        u.setType(0);

        list.getUser().add(u);
        UserXMLHandler usHandler = new UserXMLHandler(UserList.class);
        try {
            usHandler.writeToFile(list);
        } catch (JAXBException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        } catch (SAXException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }

    @Override
    public void setXsdPath(final String xsdPath) {
        this.xsdPath = xsdPath;

    }

    @Override
    public void setXmlDataFile(final String xmlDataFile) {
        this.xmlDataFile = xmlDataFile;

    }

    @Override
    public void setClassToHandle(final Class classToWrite) {
        this.classToHandle = classToWrite;

    }

}
