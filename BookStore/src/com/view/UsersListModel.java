package com.view;

import javax.swing.ListModel;
import javax.swing.event.ListDataListener;

import com.model.user.UserListRoot;

public class UsersListModel implements ListModel {
	private UserListRoot usersList;
	
	
	
	public UsersListModel(UserListRoot usersList) {
		super();
		this.usersList = usersList;
	}

	/**
	 * @return the usersList
	 */
	public UserListRoot getUsersList() {
		return usersList;
	}

	/**
	 * @param usersList the usersList to set
	 */
	public void setUsersList(UserListRoot usersList) {
		this.usersList = usersList;
	}

	@Override
	public int getSize() {
		return usersList.getUserList().size();
	}

	@Override
	public Object getElementAt(int index) {
		return usersList.getUserList().get(index);
	}

	@Override
	public void addListDataListener(ListDataListener l) {
		// TODO Auto-generated method stub

	}

	@Override
	public void removeListDataListener(ListDataListener l) {
		// TODO Auto-generated method stub

	}

}
