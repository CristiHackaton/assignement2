package com.view;



import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JFrame;
import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.JMenuBar;
import javax.swing.JMenu;
import javax.swing.JMenuItem;
import javax.swing.JLabel;
import javax.swing.JButton;
import javax.swing.JTextField;

import com.controller.service.AdminService;
import com.model.book.Book;

import javax.swing.JLayeredPane;
import javax.swing.JScrollPane;

public class AdminScreen extends JFrame {
	private JTextField title;
	private JTextField author;
	private JTextField genre;
	private JTextField quantity;
	private JTextField price;
	private JPanel panelCreateBook;
	private JList list;

	public AdminScreen() {
		getContentPane().setLayout(null);

		JMenuBar menuBar = new JMenuBar();
		menuBar.setBounds(0, 0, 424, 21);
		getContentPane().add(menuBar);

		JMenu mnBooks = new JMenu("Books");
		menuBar.add(mnBooks);

		JMenuItem itemCreateBook = new JMenuItem("Create book");
		mnBooks.add(itemCreateBook);
		itemCreateBook.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent arg0) {
				panelCreateBook.setVisible(true);

			}
		});

		JMenuItem itemViewBooks = new JMenuItem("View books");
		mnBooks.add(itemViewBooks);

		JMenu mnUsers = new JMenu("Users");
		menuBar.add(mnUsers);

		JMenuItem mntmCreateUser = new JMenuItem("Create User");
		mnUsers.add(mntmCreateUser);

		JMenuItem mntmViewUsers = new JMenuItem("View Users");
		mnUsers.add(mntmViewUsers);

		JMenu mnReport = new JMenu("Report");
		menuBar.add(mnReport);

		JMenuItem mntmGenerateReport = new JMenuItem("Generate report");
		mnReport.add(mntmGenerateReport);
		
		JPanel panel = new JPanel();
		panel.setBounds(10, 32, 347, 230);
		getContentPane().add(panel);
		panel.setLayout(null);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(125, 151, -121, -147);
		panel.add(scrollPane);
		//createBook();
		viewBooks();

	}
	private void viewBooks() {
//		list = new JList(new UserListModel());
//		listPanel=new JScrollPane(list);
//		listPanel.setBounds(10, 10, 320,500);
//		panelVizualizare.add(listPanel);
//		getContentPane().add(panelVizualizare);
//		panelVizualizare.setLayout(null);
//		panelVizualizare.setBounds(10, 32, 1700, 750);
		
	}
	public void createBook(){
		panelCreateBook = new JPanel();
		panelCreateBook.setVisible(false);
		panelCreateBook.setBounds(10, 32, 414, 207);
		getContentPane().add(panelCreateBook);
		panelCreateBook.setLayout(null);
		
		JLabel lblTitle = new JLabel("Title");
		lblTitle.setBounds(10, 11, 46, 14);
		panelCreateBook.add(lblTitle);
		
		JLabel lblAuthor = new JLabel("Author");
		lblAuthor.setBounds(10, 36, 46, 14);
		panelCreateBook.add(lblAuthor);
		
		JLabel lblGenre = new JLabel("Genre");
		lblGenre.setBounds(10, 61, 46, 14);
		panelCreateBook.add(lblGenre);
		
		JLabel lblQuantity = new JLabel("Quantity");
		lblQuantity.setBounds(10, 86, 46, 14);
		panelCreateBook.add(lblQuantity);
		
		JLabel lblPrice = new JLabel("Price");
		lblPrice.setBounds(10, 114, 46, 14);
		panelCreateBook.add(lblPrice);
		
		JButton btnAddBook = new JButton("Add Book");
		btnAddBook.setBounds(10, 137, 89, 23);
		panelCreateBook.add(btnAddBook);
		
		title = new JTextField();
		title.setBounds(66, 8, 86, 20);
		panelCreateBook.add(title);
		title.setColumns(10);
		
		author = new JTextField();
		author.setBounds(66, 33, 86, 20);
		panelCreateBook.add(author);
		author.setColumns(10);
		
		genre = new JTextField();
		genre.setBounds(66, 58, 86, 20);
		panelCreateBook.add(genre);
		genre.setColumns(10);
		
		quantity = new JTextField();
		quantity.setBounds(66, 83, 86, 20);
		panelCreateBook.add(quantity);
		quantity.setColumns(10);
		
		price = new JTextField();
		price.setBounds(66, 111, 86, 20);
		panelCreateBook.add(price);
		price.setColumns(10);
		
		btnAddBook.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent arg0) {
				AdminService adminService = new AdminService();
				adminService.createBook(title.getText(), author.getText(),
						genre.getText(), Integer.parseInt(quantity.getText()),
						Double.parseDouble(price.getText()));
				title.setText("");
				author.setText("");
				genre.setText("");
				quantity.setText("");
				price.setText("");
			}
		});
		
	}
}
