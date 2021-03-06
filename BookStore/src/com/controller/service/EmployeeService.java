package com.controller.service;

import java.util.ArrayList;
import java.util.GregorianCalendar;

import javax.xml.bind.JAXBException;
import javax.xml.datatype.DatatypeConfigurationException;
import javax.xml.datatype.DatatypeFactory;
import javax.xml.datatype.XMLGregorianCalendar;

import org.xml.sax.SAXException;

import com.controller.dataAccess.BookXMLHandler;
import com.controller.dataAccess.SellXMLHandler;
import com.controller.dataAccess.UserXMLHandler;
import com.model.book.Book;
import com.model.book.BookListRoot;
import com.model.sell.ObjectFactory;
import com.model.sell.Sell;
import com.model.sell.SellListRoot;
import com.model.user.UserListRoot;


public class EmployeeService extends UserService {
	private SellListRoot sellList;
	private BookListRoot bookList;
	private ObjectFactory sellFactory;

	public EmployeeService(){
		SellXMLHandler sl=new SellXMLHandler();
		BookXMLHandler bk=new BookXMLHandler();
		sellFactory = new ObjectFactory();
		try {
			sellList=(SellListRoot)sl.readFromFile();
			bookList=(BookListRoot)bk.readFromFile();
		} catch (JAXBException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (SAXException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		if (sellList == null){
			sellList = sellFactory.createSellListRoot();
		}
	
	}
	/**
	 * 
	 * @param searchType
	 *            0 =title; 1=author; 2=genre;
	 * @param criterion
	 *            =what to search;
	 * @return
	 */
	public ArrayList<Book> searchBook(int searchType, String criterion) {
		ArrayList<Book> searchList = new ArrayList<>();
		for (Book book : bookList.getBookList()) {
			if (searchType == 0) {
				if (book.getTitle().contains(criterion)) {
					searchList.add(book);
				}
			} else if (searchType == 1) {
				if (book.getAuthor().contains(criterion)) {
					searchList.add(book);
				}
			} else if (searchType == 2) {
				if (book.getGenre().contains(criterion)) {
					searchList.add(book);
				}
			}
		}

		return searchList;
	}

	public boolean sellBook(Book bookTOsell, int nrSellBooks, String buyer) {
		int index = bookList.getBookList().indexOf(bookTOsell);
		
		int remainingBook = bookList.getBookList().get(index).getQuantity()
				- nrSellBooks;
		bookList.getBookList().get(index).setQuantity(remainingBook);

		Sell sale = sellFactory.createSell();
		Book sellBook = sellFactory.createBook();
		sellBook.setAuthor(bookTOsell.getAuthor());
		sellBook.setTitle(bookTOsell.getTitle());
		sellBook.setGenre(bookTOsell.getGenre());
		sellBook.setQuantity(nrSellBooks);
		sellBook.setPrice(bookTOsell.getPrice());

		sale.setBook(sellBook);
		sale.setBuyer(buyer);
		GregorianCalendar gcal = new GregorianCalendar();
		XMLGregorianCalendar xgcal;
		try {
			xgcal = DatatypeFactory.newInstance().newXMLGregorianCalendar(gcal);
			sale.setDate(xgcal);
		} catch (DatatypeConfigurationException e) {
			e.printStackTrace();
			return false;
		}

		sellList.getSellList().add(sale);
		
		SellXMLHandler sl=new SellXMLHandler();
		try {
			sl.writeToFile(sellList);
		} catch (JAXBException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (SAXException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return true;
	}
	/**
	 * @return the sellList
	 */
	public SellListRoot getSellList() {
		return sellList;
	}
	/**
	 * @param sellList the sellList to set
	 */
	public void setSellList(SellListRoot sellList) {
		this.sellList = sellList;
	}
	/**
	 * @return the bookList
	 */
	public BookListRoot getBookList() {
		return bookList;
	}
	/**
	 * @param bookList the bookList to set
	 */
	public void setBookList(BookListRoot bookList) {
		this.bookList = bookList;
	}
	
	
}
