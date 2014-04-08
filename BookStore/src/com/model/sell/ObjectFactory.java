package com.model.sell;




import javax.xml.bind.annotation.XmlRegistry;

import com.model.book.Book;
import com.model.book.BookList;


/**
 * This object contains factory methods for each 
 * Java content interface and Java element interface 
 * generated in the generated package. 
 * <p>An ObjectFactory allows you to programatically 
 * construct new instances of the Java representation 
 * for XML content. The Java representation of XML 
 * content can consist of schema derived interfaces 
 * and classes representing the binding of schema 
 * type definitions, element declarations and model 
 * groups.  Factory methods for each of these are 
 * provided in this class.
 * 
 */
@XmlRegistry
public class ObjectFactory {


    /**
     * Create a new ObjectFactory that can be used to create new instances of schema derived classes for package: generated
     * 
     */
    public ObjectFactory() {
    }

    /**
     * Create an instance of {@link SellList }
     * 
     */
    public SellList createSellList() {
        return new SellList();
    }

    /**
     * Create an instance of {@link Sell }
     * 
     */
    public Sell createSell() {
        return new Sell();
    }

    /**
     * Create an instance of {@link BookList }
     * 
     */
    public BookList createBookList() {
        return new BookList();
    }

    /**
     * Create an instance of {@link Book }
     * 
     */
    public Book createBook() {
        return new Book();
    }

}
