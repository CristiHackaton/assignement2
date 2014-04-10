package com.controller.service;

import java.util.ArrayList;

import com.controller.Reports;
import com.model.book.Book;
import com.model.book.BookList;
import com.model.user.ObjectFactory;
import com.model.user.User;
import com.model.user.UserList;

public class AdminService extends UserService {
	private UserList userList;
	private ObjectFactory userFactory;
	private com.model.book.ObjectFactory bookFactory;
	private BookList bookList;

	public User createUser(String name, String pass, String phone, int type) {
		User us = userFactory.createUser();
		us.setName(name);
		us.setPassword(pass);
		us.setPhoneNumber(phone);
		us.setType(type);
		userList.getUser().add(us);
		return us;
  
	}

	public boolean deleteUserByName(String name) {
		User userToDelete = null;
		for (User u : userList.getUser()) {
			if (name.equals(u.getName())) {
				userToDelete = u;
			}
		}
		if (userToDelete != null) {
			userList.getUser().remove(userToDelete);
			return true;
		}
		return false;

	}

	public boolean updateUserbyUserName(String name, String pass, String phone,
			int type) {

		for (User u : userList.getUser()) {
			if (name.equals(u.getName())) {
				u.setPassword(pass);
				u.setPhoneNumber(phone);
				u.setType(type);
				return true;
			}
		}
		return false;
	}

	public User viewUserbyName(String name) {

		for (User u : userList.getUser()) {
			if (name.equals(u.getName())) {

				return u;
			}
		}
		return null;
	}

	public Book createBook(String title, String author, String genre,
			int quantity, double price) {
		Book book = bookFactory.createBook();
		book.setTitle(title);
		book.setAuthor(author);
		book.setGenre(genre);
		book.setQuantity(quantity);
		book.setPrice(price);
		bookList.getBook().add(book);
		return book;
	}

	public boolean deleteBookByTitle(String title) {
		Book bookToDelete = null;
		for (Book book : bookList.getBook()) {
			if (title.equals(book.getTitle())) {
				bookToDelete = book;
			}
		}
		if (bookToDelete != null) {

			bookList.getBook().remove(bookToDelete);
			return true;
		}
		return false;
	}
	public boolean updateBookByTitle(String oldtitle, String newTitle,String author, String genre,
			int quantity, double price){
		
		for (Book book : bookList.getBook()){
			if(oldtitle.equals(book.getTitle())){
				
			
			book.setTitle(newTitle);
			book.setAuthor(author);
			book.setGenre(genre);
			book.setQuantity(quantity);
			book.setPrice(price);
			return true;
			}
		}
		return false;	
	}
	
	public Book viewBookbyTitle(String title){
		for(Book book:bookList.getBook()){
			if(title.equals(book.getTitle())){
				return book;
			}
		}
		return null;
	}
	
	public void report(){
		ArrayList<Book> outOfStock=new ArrayList<>();
		for(Book book:bookList.getBook()){
			if(book.getQuantity()==0){
				outOfStock.add(book);
			}
		}
		Reports.makeReports(outOfStock);
	}
}