package com.controller.dataAccess;

import javax.xml.bind.JAXBException;

import org.xml.sax.SAXException;

import com.model.user.User;
import com.model.user.UserListRoot;

public class UserXMLHandler extends AbstractXMLHandler {
    private static String XSD_PATH = "src//com//resources//userSchema.xsd";
    private static String XML_FILE_PATH = "src//com//resources//data//users.xml";

    public UserXMLHandler() {
        setXmlDataFile(XML_FILE_PATH);
        setXsdPath(XSD_PATH);
        classToHandle = UserListRoot.class;
    }

    public static void main(final String args[]) {
        UserListRoot list = new UserListRoot();
        User u = new User();
        u.setName("danny");
        u.setPassword("danny");
        u.setPhoneNumber("123243");
        u.setType(0);

        User u2 = new User();
        u2.setName("aly");
        u2.setPassword("aly");
        u2.setPhoneNumber("123243");
        u2.setType(0);
        list.getUserList().add(u);
        list.getUserList().add(u2);

        UserXMLHandler usHandler = new UserXMLHandler();
        try {
            //            usHandler.writeToFile(list);
            UserListRoot list2 = (UserListRoot)usHandler.readFromFile();
            System.out.println(list2.getUserList().get(0).getName());
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
