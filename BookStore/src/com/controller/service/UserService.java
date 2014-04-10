package com.controller.service;

import java.util.ArrayList;

import javax.xml.bind.JAXBException;

import org.xml.sax.SAXException;

import com.controller.dataAccess.UserXMLHandler;
import com.model.user.User;
import com.model.user.UserListRoot;

public class UserService {
	private UserListRoot userList;

	public UserService(){
		UserXMLHandler usXMLHandler= new UserXMLHandler();
		try {
			userList=(UserListRoot) usXMLHandler.readFromFile();
		} catch (JAXBException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (SAXException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	public User login(String userName, String password) {
		ArrayList<User> lista = (ArrayList<User>) userList.getUserList();
		for (User us : lista) {
			if (userName.equals(us.getName())
					&& password.equals(us.getPassword())) {
				return us;
			}
		}
		return null;
	}
}
