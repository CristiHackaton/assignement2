package com.controller.service;

import java.util.ArrayList;
import java.util.GregorianCalendar;
import javax.xml.datatype.DatatypeConfigurationException;
import javax.xml.datatype.DatatypeFactory;
import javax.xml.datatype.XMLGregorianCalendar;

import com.model.book.Book;
import com.model.book.BookList;
import com.model.sell.ObjectFactory;
import com.model.sell.Sell;
import com.model.sell.SellList;

public class EmployeeService extends UserService {
	private SellList sellList;
	private BookList bookList;
	private ObjectFactory sellFactory;

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
		for (Book book : bookList.getBook()) {
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
		int index = bookList.getBook().indexOf(bookTOsell);
		int remainingBook = bookList.getBook().get(index).getQuantity()
				- nrSellBooks;
		bookList.getBook().get(index).setQuantity(remainingBook);

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

		sellList.getSell().add(sale);
		return true;
	}
}