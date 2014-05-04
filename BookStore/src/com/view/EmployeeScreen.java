package com.view;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextField;
import javax.swing.JButton;

import com.controller.service.EmployeeService;
import com.model.book.Book;
import java.awt.Rectangle;

public class EmployeeScreen extends JFrame {
	private JTextField buyer;
	private JTextField sellQuantity;
	private JPanel panelVizualizare;
	private JList list;
	private JScrollPane listPanel;
	public EmployeeScreen() {
		setBounds(new Rectangle(0, 0, 1365, 799));
		EmployeeService empService = new EmployeeService();
		getContentPane().setLayout(null);
		
		panelVizualizare = new JPanel();
		//list= new JList<>();
		list = new JList(new BooksListModel(empService.getBookList()));
		listPanel = new JScrollPane(list);
		listPanel.setBounds(10, 10, 320, 500);
		panelVizualizare.add(listPanel);
		getContentPane().add(panelVizualizare);
		panelVizualizare.setLayout(null);
		panelVizualizare.setBounds(10, 32, 720, 750);
		
		JLabel lblBuyer = new JLabel("Buyer: ");
		lblBuyer.setBounds(399, 3, 69, 14);
		panelVizualizare.add(lblBuyer);
		
		JLabel lblQuantity = new JLabel("Quantity:");
		lblQuantity.setBounds(399, 34, 46, 14);
		panelVizualizare.add(lblQuantity);
		
		buyer = new JTextField();
		buyer.setBounds(478, 0, 86, 20);
		panelVizualizare.add(buyer);
		buyer.setColumns(10);
		
		sellQuantity = new JTextField();
		sellQuantity.setBounds(478, 31, 86, 20);
		panelVizualizare.add(sellQuantity);
		sellQuantity.setColumns(10);
		
		JButton btnSale = new JButton("Sale");
		btnSale.setBounds(399, 94, 89, 23);
		panelVizualizare.add(btnSale);
		
		JButton btnLogout = new JButton("Logout");
		btnLogout.setBounds(1250, 11, 89, 23);
		getContentPane().add(btnLogout);
		
		
		btnLogout.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent arg0) {
				// TODO Auto-generated method stub
				closeScreen();
				UserScreen us=new UserScreen();
				us.setVisible(true);
			}
		});
		btnSale.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				if (Integer.parseInt(sellQuantity.getText()) <= 0){
					JOptionPane.showMessageDialog(null, "You must set the quantity of the sale!");
				}else {
					if (((Book)list.getSelectedValue()).getQuantity() < Integer.parseInt(sellQuantity.getText())){
						JOptionPane.showMessageDialog(null, "Not enough books!");
					}else {
						EmployeeService empService = new EmployeeService();
						empService.sellBook((Book)list.getSelectedValue(), Integer.parseInt(sellQuantity.getText()), buyer.getText());
					}
					
					buyer.setText("");
					sellQuantity.setText("");
				}
				
			}
		});
	}
	
	public void closeScreen(){
		this.setVisible(false);
		this.dispose();
	}
}
