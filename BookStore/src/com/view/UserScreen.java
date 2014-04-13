package com.view;

import javax.swing.JFrame;

import java.awt.Font;
import java.awt.Color;

import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JLabel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;
import javax.swing.JButton;

import com.controller.service.UserService;
import com.model.user.User;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class UserScreen extends JFrame{
	private JPasswordField passwordField;
	private JTextField userName;
	public UserScreen() {
		getContentPane().setFont(new Font("Times New Roman", Font.PLAIN, 12));
		getContentPane().setForeground(new Color(255, 255, 204));
		getContentPane().setLayout(null);
		
		JPanel panel = new JPanel();
		panel.setForeground(new Color(255, 255, 153));
		panel.setBounds(10, 11, 321, 213);
		getContentPane().add(panel);
		panel.setLayout(null);
		
		JLabel lblPassword = new JLabel("Password");
		lblPassword.setFont(new Font("Segoe Print", Font.BOLD, 12));
		lblPassword.setBounds(10, 36, 101, 16);
		panel.add(lblPassword);
		
		JLabel lblUserName = new JLabel("User name");
		lblUserName.setFont(new Font("Segoe Print", Font.BOLD, 12));
		lblUserName.setBounds(10, 11, 85, 14);
		panel.add(lblUserName);
		
		passwordField = new JPasswordField();
		passwordField.setBounds(105, 35, 101, 20);
		panel.add(passwordField);
		
		userName = new JTextField();
		userName.setBounds(105, 5, 101, 20);
		panel.add(userName);
		userName.setColumns(10);
		
		JButton butonLogin = new JButton("  Login");
		butonLogin.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				UserService us=new UserService();
				
				User user=us.login(userName.getText(), passwordField.getText());
				if(user!=null){
					if(user.getType()==0){
						//0 is admin
						AdminScreen adm=new AdminScreen();
						adm.setVisible(true);
						closeWindow();
						
					}else if(user.getType()==1){
						EmployeeScreen emp=new EmployeeScreen();
						emp.setVisible(true);
						closeWindow();
					}
				}else {
					JOptionPane.showMessageDialog(null, "The user doesn`t exist!");
				}
			}

		});
		butonLogin.setBounds(10, 80, 89, 23);
		panel.add(butonLogin);
		setFont(new Font("Cooper Black", Font.PLAIN, 12));
		setTitle("Hello User!");
	}
	private void closeWindow() {
		this.setVisible(false);
		this.dispose();
		
	}
}
