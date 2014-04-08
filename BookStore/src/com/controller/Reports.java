package com.controller;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;

import com.model.book.Book;

public class Reports {
	
	public static void makeReports(ArrayList<Book> bookL){
		StringBuilder data=new StringBuilder();
		for(Book b:bookL){
			data.append(b.toString());
			data.append("\n");
		}
		try {
			FileOutputStream file=new FileOutputStream(new File("report.txt"));
			file.write(data.toString().getBytes());
			file.flush();
			file.close();
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		
	}
	
}
