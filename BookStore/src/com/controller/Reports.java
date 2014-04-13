package com.controller;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import com.model.book.Book;
import com.model.sell.Sell;

public class Reports {
	
	public static void makeReports(List<Book> bookL){
		StringBuilder data=new StringBuilder();
		for(Book b:bookL){
			if (b.getQuantity() == 0){
				data.append(b.toReport());
				data.append("\n");
			}
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
	
	public static void makeSaleReport(List<Sell> sellList){
		StringBuilder data=new StringBuilder();
		for(Sell b:sellList){
				data.append(b.toReport());
				data.append("\n");
		}
		try {
			FileOutputStream file=new FileOutputStream(new File("reportSale.txt"));
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
