package com.controller.service;

import java.util.ArrayList;

import javax.xml.bind.JAXBException;

import org.xml.sax.SAXException;

import com.controller.Reports;
import com.controller.dataAccess.BookXMLHandler;
import com.controller.dataAccess.UserXMLHandler;
import com.model.book.Book;
import com.model.book.BookListRoot;
import com.model.user.ObjectFactory;
import com.model.user.User;
import com.model.user.UserListRoot;

public class AdminService extends UserService {
	private UserListRoot userList;
	private ObjectFactory userFactory;
	private com.model.book.ObjectFactory bookFactory;
	private BookListRoot bookList;

	public AdminService(){
		
		UserXMLHandler usXMLHandler= new UserXMLHandler();
		BookXMLHandler bkXMLHandler= new BookXMLHandler();
		userFactory=new ObjectFactory();
		bookFactory=new com.model.book.ObjectFactory();
		try {
			userList=(UserListRoot) usXMLHandler.readFromFile();
			bookList=(BookListRoot ) bkXMLHandler.readFromFile();
			if(userList==null){
				userList=userFactory.createUserListRoot();
			}
			if(bookList==null){
				bookList=bookFactory.createBookListRoot();
			}
		} catch (JAXBException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (SAXException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	
			
		
	
	}
	public User createUser(String name, String pass, String phone, int type) {
		User us = userFactory.createUser();
		us.setName(name);
		us.setPassword(pass);
		us.setPhoneNumber(phone);
		us.setType(type);
		userList.getUserList().add(us);
		return us;
  
	}

	public boolean deleteUserByName(String name) {
		User userToDelete = null;
		for (User u : userList.getUserList()) {
			if (name.equals(u.getName())) {
				userToDelete = u;
			}
		}
		if (userToDelete != null) {
			userList.getUserList().remove(userToDelete);
			return true;
		}
		return false;

	}

	public boolean updateUserbyUserName(String name, String pass, String phone,
			int type) {

		for (User u : userList.getUserList()) {
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

		for (User u : userList.getUserList()) {
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
		bookList.getBookList().add(book);
		BookXMLHandler addBook=new BookXMLHandler();
		try {
			addBook.writeToFile(bookList);
		} catch (JAXBException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (SAXException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return book;
	}

	public boolean deleteBookByTitle(String title) {
		Book bookToDelete = null;
		for (Book book : bookList.getBookList()) {
			if (title.equals(book.getTitle())) {
				bookToDelete = book;
			}
		}
		if (bookToDelete != null) {

			bookList.getBookList().remove(bookToDelete);
			return true;
		}
		return false;
	}
	public boolean updateBookByTitle(String oldtitle, String newTitle,String author, String genre,
			int quantity, double price){
		
		for (Book book : bookList.getBookList()){
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
		for(Book book:bookList.getBookList()){
			if(title.equals(book.getTitle())){
				return book;
			}
		}
		return null;
	}
	
	public void report(){
		ArrayList<Book> outOfStock=new ArrayList<>();
		for(Book book:bookList.getBookList()){
			if(book.getQuantity()==0){
				outOfStock.add(book);
			}
		}
		Reports.makeReports(outOfStock);
	}
}
