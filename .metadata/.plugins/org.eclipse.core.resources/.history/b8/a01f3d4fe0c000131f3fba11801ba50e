package com.controller;

import java.util.ArrayList;

import com.model.user.User;
import com.model.user.UserList;

public class UserService {
	private UserList userList;

	public User login(String userName, String password) {
		ArrayList<User> lista = (ArrayList<User>) userList.getUser();
		for (User us : lista) {
			if (userName.equals(us.getName())
					&& password.equals(us.getPassword())) {
				return us;
			}
		}
		return null;
	}
}
