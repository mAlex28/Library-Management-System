package com.librarymanagementsystem;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.Locale;

public class AddBooks extends JFrame implements ActionListener {

    private JPanel addBooksPanel;
    private JLabel isbn, name, publisher, version, pages, price, back;
    private JTextField isbnField, nameField, publisherField, versionField, pagesField, priceField;

    public AddBooks() {
        Font normalFont = new Font("Helvetica", Font.PLAIN, 14);
        Cursor handCursor = new Cursor(Cursor.HAND_CURSOR);

        setTitle("Library Management System");
        setBackground(new Color(242, 242, 247));
        setSize(800, 600);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setResizable(false);
        setLocationRelativeTo(null);

        // panel settings
        addBooksPanel = new JPanel();
        addBooksPanel.setBackground(new Color(242, 242, 247));
        setContentPane(addBooksPanel);
        addBooksPanel.setLayout(null);

        // panel name
        JLabel panelName = new JLabel("Add Books");
        panelName.setForeground(new Color(162, 132, 94));
        panelName.setBounds(300,40, 400, 25);
        panelName.setFont(new Font("Helvetica", Font.BOLD, 25));

        // isbn
        isbn = new JLabel("Book ISBN :");
        isbn.setForeground(new Color(23, 29, 28));
        isbn.setBounds(250,130, 100, 30);
        isbn.setHorizontalAlignment(SwingConstants.RIGHT);
        isbn.setFont(normalFont);

        isbnField = new JTextField();
        isbnField.setBounds(360, 130, 200, 30);
        isbnField.setFont(normalFont);

        // name
        name = new JLabel("Name :");
        name.setForeground(new Color(23, 29, 28));
        name.setBounds(250,165, 100, 30);
        name.setHorizontalAlignment(SwingConstants.RIGHT);
        name.setFont(normalFont);

        nameField = new JTextField();
        nameField.setBounds(360, 165, 200, 30);
        nameField.setFont(normalFont);

        // publisher
        publisher = new JLabel("Publisher :");
        publisher.setForeground(new Color(23, 29, 28));
        publisher.setBounds(250,200, 100, 30);
        publisher.setHorizontalAlignment(SwingConstants.RIGHT);
        publisher.setFont(normalFont);

        publisherField = new JTextField();
        publisherField.setBounds(360, 200, 200, 30);
        publisherField.setFont(normalFont);

        // version
        version = new JLabel("Version :");
        version.setForeground(new Color(23,29,28));
        version.setBounds(250,235,100,30);
        version.setHorizontalAlignment(SwingConstants.RIGHT);
        version.setFont(normalFont);

        versionField = new JTextField();
        versionField.setBounds(360, 235, 200, 30);
        versionField.setFont(normalFont);

        // pages
        pages = new JLabel("No. of Pages :");
        pages.setForeground(new Color(23,29,28));
        pages.setBounds(250,270,100,30);
        pages.setHorizontalAlignment(SwingConstants.RIGHT);
        pages.setFont(normalFont);

        pagesField = new JTextField();
        pagesField.setBounds(360, 270, 200, 30);
        pagesField.setFont(normalFont);

        // price
        price = new JLabel("Price :");
        price.setForeground(new Color(23,29,28));
        price.setBounds(250,305,100,30);
        price.setHorizontalAlignment(SwingConstants.RIGHT);
        price.setFont(normalFont);

        priceField = new JTextField();
        priceField.setBounds(360, 305, 200, 30);
        priceField.setFont(normalFont);

        // add book button
        JButton addBookBtn = new JButton("Register");
        addBookBtn.setForeground(new Color(239, 233, 244));
        addBookBtn.setBackground(new Color(10, 132, 255));
        addBookBtn.setOpaque(true);
        addBookBtn.setBorderPainted(false);
        addBookBtn.setBounds(340, 360, 100, 28);
        addBookBtn.setFont(normalFont);
        addBookBtn.addActionListener(this);
        addBookBtn.setCursor(handCursor);

        // back label
        back = new JLabel("Go back");
        back.setForeground(new Color(23, 29, 28));
        back.setBounds(350, 400, 200, 20);
        back.setFont(normalFont);
        back.setCursor(handCursor);
        back.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                setVisible(false);
                Home home = new Home();
                home.setVisible(true);
            }

            @Override
            public void mouseEntered(MouseEvent e) {
                back.setForeground(new Color(19, 123, 255));
            }

            @Override
            public void mouseExited(MouseEvent e) {
                back.setForeground(new Color(23, 29, 28));
            }
        });

        // add to panel
        addBooksPanel.add(panelName);
        addBooksPanel.add(isbn);
        addBooksPanel.add(isbnField);
        addBooksPanel.add(name);
        addBooksPanel.add(nameField);
        addBooksPanel.add(publisher);
        addBooksPanel.add(publisherField);
        addBooksPanel.add(version);
        addBooksPanel.add(versionField);
        addBooksPanel.add(pages);
        addBooksPanel.add(pagesField);
        addBooksPanel.add(price);
        addBooksPanel.add(priceField);
        addBooksPanel.add(addBookBtn);
        addBooksPanel.add(back);

    }

    public static void main(String[] args) {
        new AddBooks().setVisible(true);
    }

    @Override
    public void actionPerformed(ActionEvent actionEvent) {
        try{
            DbCon con = new DbCon();

                String bookName = nameField.getText().toLowerCase();

                String selectQuery = "SELECT bookname FROM book where bookname=?";
                PreparedStatement pstselect = con.con.prepareStatement(selectQuery);
                pstselect.setString(1, bookName);

                ResultSet rs = pstselect.executeQuery();

                if(rs.next()) {
                    JOptionPane.showMessageDialog(null, "Book already exists");

                }  else {

                    String insertQuery = "INSERT INTO book (isbn, bookname, publisher, version, nopages, price) values(?, ?, ?, ?, ?, ?)";
                    PreparedStatement pst = con.con.prepareStatement(insertQuery);

                    pst.setString(1, isbnField.getText());
                    pst.setString(2, nameField.getText().toLowerCase());
                    pst.setString(3, publisherField.getText().toLowerCase());
                    pst.setString(4, versionField.getText().toLowerCase());
                    pst.setString(5, pagesField.getText().toLowerCase());
                    pst.setString(6, priceField.getText().toLowerCase());

                    int i = pst.executeUpdate();

                    if (i > 0){
                        JOptionPane.showMessageDialog(null, "Book Added");
                    }
                }

                isbnField.setText("");
                nameField.setText("");
                publisherField.setText("");
                versionField.setText("");
                pagesField.setText("");
                priceField.setText("");


        }catch(Exception e){
            JOptionPane.showMessageDialog(null, "Error occurred! Try again later");
            e.printStackTrace();
        }
    }
}
