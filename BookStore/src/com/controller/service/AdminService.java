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
    private final ObjectFactory userFactory;
    private final com.model.book.ObjectFactory bookFactory;
    private BookListRoot bookList;

    public AdminService() {

        UserXMLHandler usXMLHandler = new UserXMLHandler();
        BookXMLHandler bkXMLHandler = new BookXMLHandler();
        userFactory = new ObjectFactory();
        bookFactory = new com.model.book.ObjectFactory();
        try {
            userList = (UserListRoot)usXMLHandler.readFromFile();
            bookList = (BookListRoot)bkXMLHandler.readFromFile();
            if (userList == null) {
                userList = userFactory.createUserListRoot();
            }
            if (bookList == null) {
                bookList = bookFactory.createBookListRoot();
            }
        } catch (JAXBException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        } catch (SAXException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }

    }

    public User createUser(final String name, final String pass, final String phone, final int type) {
        User us = userFactory.createUser();
        us.setName(name);
        us.setPassword(pass);
        us.setPhoneNumber(phone);
        us.setType(type);
        userList.getUserList().add(us);
        writeUserList();
        return us;

    }

    public void deleteUserByName(final String name) {
        User userToDelete = null;
        for (User u : userList.getUserList()) {
            if (name.equals(u.getName())) {
                userToDelete = u;
            }
        }
        if (userToDelete != null) {
            userList.getUserList().remove(userToDelete);

        }

        writeUserList();

    }

    public void updateUserbyUserName(final String name, final String pass, final String phone,
            final int type) {

        for (User u : userList.getUserList()) {
            if (name.equals(u.getName())) {
                u.setPassword(pass);
                u.setPhoneNumber(phone);
                u.setType(type);
            }
        }
        writeUserList();
    }

    public User viewUserbyName(final String name) {

        for (User u : userList.getUserList()) {
            if (name.equals(u.getName())) {

                return u;
            }
        }
        return null;
    }

    public Book createBook(final String title, final String author, final String genre,
            final int quantity, final double price) {
        Book book = bookFactory.createBook();
        book.setTitle(title);
        book.setAuthor(author);
        book.setGenre(genre);
        book.setQuantity(quantity);
        book.setPrice(price);
        bookList.getBookList().add(book);
        writeBookList();
        return book;
    }

    public void deleteBookByTitle(final String title) {
        Book bookToDelete = null;
        for (Book book : bookList.getBookList()) {
            if (title.equals(book.getTitle())) {
                bookToDelete = book;
            }
        }
        if (bookToDelete != null) {

            bookList.getBookList().remove(bookToDelete);
        }

        writeBookList();
    }

    public void updateBookByTitle(final String oldtitle, final String newTitle, final String author, final String genre,
            final int quantity, final double price) {

        for (Book book : bookList.getBookList()) {
            if (oldtitle.equals(book.getTitle())) {

                book.setTitle(newTitle);
                book.setAuthor(author);
                book.setGenre(genre);
                book.setQuantity(quantity);
                book.setPrice(price);
            }
        }

        writeBookList();
    }

    private void writeBookList() {
        BookXMLHandler addBook = new BookXMLHandler();
        try {
            addBook.writeToFile(bookList);
        } catch (JAXBException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        } catch (SAXException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }

    private void writeUserList() {
        UserXMLHandler writer = new UserXMLHandler();
        try {
            writer.writeToFile(userList);
        } catch (JAXBException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        } catch (SAXException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }

    public Book viewBookbyTitle(final String title) {
        for (Book book : bookList.getBookList()) {
            if (title.equals(book.getTitle())) {
                return book;
            }
        }
        return null;
    }

    public void report() {
        ArrayList<Book> outOfStock = new ArrayList<>();
        for (Book book : bookList.getBookList()) {
            if (book.getQuantity() == 0) {
                outOfStock.add(book);
            }
        }
        Reports.makeReports(outOfStock);
    }

    public UserListRoot getUserList() {
        return userList;
    }

    public void setUserList(final UserListRoot userList) {
        this.userList = userList;
    }

    public BookListRoot getBookList() {
        return bookList;
    }

    public void setBookList(final BookListRoot bookList) {
        this.bookList = bookList;
    }

}
