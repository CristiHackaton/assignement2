package com.controller;

import com.model.user.ObjectFactory;
import com.model.user.User;
import com.model.user.UserList;


public class AdminService extends UserService{
	private UserList userList;
	private ObjectFactory userFactory;
	
	
	public User createUser(String name, String pass, String phone, int type){
		User us=userFactory.createUser();
		us.setName(name);
		us.setPassword(pass);
		us.setPhoneNumber(phone);
		us.setType(type);
		userList.getUser().add(us);
		return us;
		
	}
	public boolean deleteUserByName(String name){
		User userToDelete=null;
		for(User u:userList.getUser()){
			if(name.equals(u.getName())){
				userToDelete=u;
			}
		}
		if(userToDelete!=null){
			userList.getUser().remove(userToDelete);
			return true;
		}
		return false;
		
	}
	public boolean updateUserbyUserName(String name, String pass, String phone,int type){
		
		for(User u:userList.getUser()){
			if(name.equals(u.getName())){
				u.setPassword(pass);
				u.setPhoneNumber(phone);
				u.setType(type);
				return true;
			}
		}return false;
	}
}
