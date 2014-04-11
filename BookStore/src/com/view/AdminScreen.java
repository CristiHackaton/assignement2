package com.view;

import java.awt.Component;
import java.awt.Container;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.ButtonGroup;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.JScrollPane;
import javax.swing.JTextField;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;

import com.controller.service.AdminService;
import com.model.book.Book;

public class AdminScreen extends JFrame {
    private JTextField title;
    private JTextField author;
    private JTextField genre;
    private JTextField quantity;
    private JTextField price;
    private JPanel panelCreateBook;
    private JList list;
    private JScrollPane listPanel;
    private JPanel panelVizualizare;
    private JTextField titleView;
    private JTextField authorView;
    private JTextField genreView;
    private JTextField quantityView;
    private JTextField priceView;
    private JButton btnEdit;
    private JButton btnSave;
    private JButton btnDelete;
    private JTextField userNameCreateField;
    private JTextField passwordCreateField;
    private JTextField phoneCreateField;
    private JPanel panelUserAdd;
    private JRadioButton rdbtnAdminCreate;
    private JRadioButton rdbtnEmployeeCreate;
    private final ButtonGroup buttonGroup = new ButtonGroup();

    public AdminScreen() {
        getContentPane().setLayout(null);
        panelVizualizare = new JPanel();
        panelUserAdd = new JPanel();
        panelCreateBook = new JPanel();
        JMenuBar menuBar = new JMenuBar();
        menuBar.setBounds(0, 0, 806, 21);
        getContentPane().add(menuBar);

        JMenu mnBooks = new JMenu("Books");
        menuBar.add(mnBooks);

        JMenuItem itemCreateBook = new JMenuItem("Create book");
        mnBooks.add(itemCreateBook);
        itemCreateBook.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(final ActionEvent arg0) {
                createBook();
                panelVizualizare.removeAll();
                panelUserAdd.removeAll();
                changePanelVisibility(panelCreateBook, true);
                changePanelVisibility(panelVizualizare, false);
                changePanelVisibility(panelUserAdd, false);

            }
        });

        JMenuItem itemViewBooks = new JMenuItem("View books");
        mnBooks.add(itemViewBooks);
        itemViewBooks.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(final ActionEvent arg0) {
                viewBooks();
                panelCreateBook.removeAll();
                panelUserAdd.removeAll();
                changePanelVisibility(panelVizualizare, true);
                changePanelVisibility(panelCreateBook, false);
                changePanelVisibility(panelUserAdd, false);

            }
        });

        JMenu mnUsers = new JMenu("Users");
        menuBar.add(mnUsers);

        JMenuItem mntmCreateUser = new JMenuItem("Create User");
        mnUsers.add(mntmCreateUser);
        mntmCreateUser.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(final ActionEvent e) {
                createUser();
                panelCreateBook.removeAll();
                panelVizualizare.removeAll();
                changePanelVisibility(panelUserAdd, true);
                changePanelVisibility(panelCreateBook, false);
                changePanelVisibility(panelVizualizare, false);
            }
        });

        JMenuItem mntmViewUsers = new JMenuItem("View Users");
        mnUsers.add(mntmViewUsers);

        JMenu mnReport = new JMenu("Report");
        menuBar.add(mnReport);

        JMenuItem mntmGenerateReport = new JMenuItem("Generate report");
        mnReport.add(mntmGenerateReport);

        //createBook();
        //viewBooks();
        //createUser();

    }

    private void createUser() {
        panelUserAdd = new JPanel();
        panelUserAdd.setBounds(10, 32, 614, 313);
        getContentPane().add(panelUserAdd);
        panelUserAdd.setLayout(null);

        JLabel lblUsername = new JLabel("Username:");
        lblUsername.setBounds(10, 11, 77, 14);
        panelUserAdd.add(lblUsername);

        JLabel lblPassword = new JLabel("Password:");
        lblPassword.setBounds(10, 36, 77, 14);
        panelUserAdd.add(lblPassword);

        JLabel lblPhone = new JLabel("Phone:");
        lblPhone.setBounds(10, 61, 77, 14);
        panelUserAdd.add(lblPhone);

        rdbtnAdminCreate = new JRadioButton("Admin");
        buttonGroup.add(rdbtnAdminCreate);
        rdbtnAdminCreate.setBounds(10, 93, 109, 23);
        rdbtnAdminCreate.setSelected(false);
        panelUserAdd.add(rdbtnAdminCreate);

        rdbtnEmployeeCreate = new JRadioButton("Employee");
        buttonGroup.add(rdbtnEmployeeCreate);
        rdbtnEmployeeCreate.setBounds(146, 93, 109, 23);
        rdbtnEmployeeCreate.setSelected(true);
        panelUserAdd.add(rdbtnEmployeeCreate);

        userNameCreateField = new JTextField();
        userNameCreateField.setBounds(136, 8, 86, 20);
        panelUserAdd.add(userNameCreateField);
        userNameCreateField.setColumns(10);

        passwordCreateField = new JTextField();
        passwordCreateField.setBounds(136, 33, 86, 20);
        panelUserAdd.add(passwordCreateField);
        passwordCreateField.setColumns(10);

        phoneCreateField = new JTextField();
        phoneCreateField.setBounds(136, 58, 86, 20);
        panelUserAdd.add(phoneCreateField);
        phoneCreateField.setColumns(10);

        JButton btnAddUser = new JButton("Add User");
        btnAddUser.setBounds(10, 140, 89, 23);
        panelUserAdd.add(btnAddUser);
        btnAddUser.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(final ActionEvent e) {
                AdminService admService = new AdminService();
                int type = 1;
                if (rdbtnAdminCreate.isSelected()) {
                    type = 0;
                } else if (rdbtnEmployeeCreate.isSelected()) {
                    type = 1;
                }
                admService.createUser(userNameCreateField.getText(), passwordCreateField.getText(), phoneCreateField.getText(), type);
                userNameCreateField.setText("");
                passwordCreateField.setText("");
                phoneCreateField.setText("");
                rdbtnEmployeeCreate.setSelected(true);
                rdbtnAdminCreate.setSelected(true);
            }
        });

    }

    private void viewBooks() {
        AdminService admService = new AdminService();
        panelVizualizare = new JPanel();
        list = new JList(new BooksListModel(admService.getBookList()));
        listPanel = new JScrollPane(list);
        listPanel.setBounds(10, 10, 320, 500);
        panelVizualizare.add(listPanel);
        getContentPane().add(panelVizualizare);
        panelVizualizare.setLayout(null);
        panelVizualizare.setBounds(10, 32, 720, 750);

        JLabel lblTitle_1 = new JLabel("Title");
        lblTitle_1.setBounds(389, 12, 46, 14);
        panelVizualizare.add(lblTitle_1);

        JLabel lblAuthor_1 = new JLabel("Author");
        lblAuthor_1.setBounds(389, 37, 46, 14);
        panelVizualizare.add(lblAuthor_1);

        JLabel lblGenre_1 = new JLabel("Genre");
        lblGenre_1.setBounds(389, 62, 46, 14);
        panelVizualizare.add(lblGenre_1);

        JLabel lblQuantity_1 = new JLabel("Quantity");
        lblQuantity_1.setBounds(389, 87, 46, 14);
        panelVizualizare.add(lblQuantity_1);

        JLabel lblPrice_1 = new JLabel("Price");
        lblPrice_1.setBounds(389, 112, 46, 14);
        panelVizualizare.add(lblPrice_1);

        titleView = new JTextField();
        titleView.setBounds(445, 9, 86, 20);
        panelVizualizare.add(titleView);
        titleView.setColumns(10);
        titleView.setEditable(false);

        authorView = new JTextField();
        authorView.setBounds(445, 34, 86, 20);
        panelVizualizare.add(authorView);
        authorView.setColumns(10);
        authorView.setEditable(false);

        genreView = new JTextField();
        genreView.setBounds(445, 59, 86, 20);
        panelVizualizare.add(genreView);
        genreView.setColumns(10);
        genreView.setEditable(false);

        quantityView = new JTextField();
        quantityView.setBounds(445, 84, 86, 20);
        panelVizualizare.add(quantityView);
        quantityView.setColumns(10);
        quantityView.setEditable(false);

        priceView = new JTextField();
        priceView.setBounds(445, 109, 86, 20);
        panelVizualizare.add(priceView);
        priceView.setColumns(10);
        priceView.setEditable(false);

        btnEdit = new JButton("Edit");
        btnEdit.setBounds(603, 58, 89, 23);
        panelVizualizare.add(btnEdit);
        btnEdit.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(final ActionEvent e) {
                titleView.setEditable(true);
                authorView.setEditable(true);
                genreView.setEditable(true);
                quantityView.setEditable(true);
                priceView.setEditable(true);
                btnSave.setVisible(true);
            }
        });

        btnSave = new JButton("Save");
        btnSave.setBounds(603, 108, 89, 23);
        panelVizualizare.add(btnSave);
        btnSave.setVisible(false);
        btnSave.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(final ActionEvent e) {
                AdminService admService = new AdminService();
                Book oldBook = (Book)list.getSelectedValue();
                admService.updateBookByTitle(oldBook.getTitle(), titleView.getText(), authorView.getText(), genreView.getText(),
                        Integer.parseInt(quantityView.getText()), Double.parseDouble(priceView.getText()));
                int index = list.getSelectedIndex();
                panelVizualizare.removeAll();
                viewBooks();
                list.setSelectedIndex(index);
            }
        });

        btnDelete = new JButton("Delete");
        btnDelete.setBounds(603, 8, 89, 23);
        panelVizualizare.add(btnDelete);
        btnDelete.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(final ActionEvent e) {
                AdminService admService = new AdminService();
                admService.deleteBookByTitle(((Book)list.getSelectedValue()).getTitle());
                panelVizualizare.removeAll();
                viewBooks();
            }
        });

        list.addListSelectionListener(new ListSelectionListener() {

            @Override
            public void valueChanged(final ListSelectionEvent e) {
                Book bookSelected = (Book)list.getSelectedValue();
                titleView.setText(bookSelected.getTitle());
                authorView.setText(bookSelected.getAuthor());
                genreView.setText(bookSelected.getGenre());
                priceView.setText("" + bookSelected.getPrice());
                quantityView.setText("" + bookSelected.getQuantity());
            }
        });

    }

    private void changePanelVisibility(final Container panel, final boolean b) {
        panel.setVisible(b);
        for (Component c : panel.getComponents()) {
            c.setVisible(b);
            c.revalidate();
            c.repaint();
        }
        panel.revalidate();
        panel.repaint();
        getContentPane().revalidate();
        getContentPane().repaint();
    }

    public void createBook() {
        panelCreateBook = new JPanel();
        panelCreateBook.setVisible(false);
        panelCreateBook.setBounds(10, 32, 414, 207);
        getContentPane().add(panelCreateBook);
        panelCreateBook.setLayout(null);

        JLabel lblTitle = new JLabel("Title");
        lblTitle.setBounds(10, 11, 46, 14);
        lblTitle.setVisible(true);
        panelCreateBook.add(lblTitle);

        JLabel lblAuthor = new JLabel("Author");
        lblAuthor.setBounds(10, 36, 46, 14);
        lblAuthor.setVisible(true);
        panelCreateBook.add(lblAuthor);

        JLabel lblGenre = new JLabel("Genre");
        lblGenre.setBounds(10, 61, 46, 14);
        lblTitle.setVisible(true);
        panelCreateBook.add(lblGenre);

        JLabel lblQuantity = new JLabel("Quantity");
        lblQuantity.setBounds(10, 86, 46, 14);
        lblQuantity.setVisible(true);
        panelCreateBook.add(lblQuantity);

        JLabel lblPrice = new JLabel("Price");
        lblPrice.setBounds(10, 114, 46, 14);
        lblPrice.setVisible(true);
        panelCreateBook.add(lblPrice);

        JButton btnAddBook = new JButton("Add Book");
        btnAddBook.setBounds(10, 137, 89, 23);
        btnAddBook.setVisible(true);
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
            public void actionPerformed(final ActionEvent arg0) {
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
