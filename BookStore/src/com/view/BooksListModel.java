package com.view;

import javax.swing.ListModel;
import javax.swing.event.ListDataListener;

import com.model.book.BookListRoot;

public class BooksListModel implements ListModel {
    private BookListRoot bookList;

    public BooksListModel(final BookListRoot bookList) {
        super();
        this.bookList = bookList;
    }

    public BookListRoot getBookList() {
        return bookList;
    }

    public void setBookList(final BookListRoot bookList) {
        this.bookList = bookList;
    }

    @Override
    public int getSize() {
        return bookList.getBookList().size();
    }

    @Override
    public Object getElementAt(final int index) {
        return bookList.getBookList().get(index);
    }

    @Override
    public void addListDataListener(final ListDataListener l) {
        // TODO Auto-generated method stub

    }

    @Override
    public void removeListDataListener(final ListDataListener l) {
        // TODO Auto-generated method stub

    }

}
