package com.librarymanagementsystem;

import com.toedter.calendar.JDateChooser;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

public class ReturnBook extends JFrame implements ActionListener {

    private JButton returnBtn, bookSearchBtn, stuSearchBtn;
    private JLabel bookIsbnLbl, bookNameLbl, studIdLbl,stuNameLbl, courseLbl, branchLbl, dOILbl, doRLbl;
    private JTextField bookIdField, bookIsbnField, bookNameField, stuIdField, stuNameField, courseField, branchField, doIField;
    private JDateChooser returnDate;

    public ReturnBook() {
        Font normalFont = new Font("Helvetica", Font.PLAIN, 14);
        Cursor handCursor = new Cursor(Cursor.HAND_CURSOR);

        setTitle("Library Management System");
        setBackground(new Color(242, 242, 247));
        setSize(800, 600);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setResizable(false);
        setLocationRelativeTo(null);

        // issue books panel
        JPanel returnBooksPanel = new JPanel();
        returnBooksPanel.setBackground(new Color(242,242,247));
        setContentPane(returnBooksPanel);
        returnBooksPanel.setLayout(null);

        // panel name
        JLabel panelName = new JLabel("Return Books");
        panelName.setForeground(new Color(162, 132, 94));
        panelName.setBounds(300,40, 400, 25);
        panelName.setFont(new Font("Helvetica", Font.BOLD, 25));

        // hidden book id field
        bookIdField = new JTextField();
        bookIdField.setBounds(100, 100, 100, 30);
        bookIdField.setFont(normalFont);
        bookIdField.setVisible(false);

        // book isbn
        bookIsbnLbl = new JLabel("Book ISBN :");
        bookIsbnLbl.setForeground(new Color(23, 29, 28));
        bookIsbnLbl.setBounds(250,130, 100, 30);
        bookIsbnLbl.setHorizontalAlignment(SwingConstants.RIGHT);
        bookIsbnLbl.setFont(normalFont);

        bookIsbnField = new JTextField();
        bookIsbnField.setBounds(360, 130, 200, 30);
        bookIsbnField.setFont(normalFont);

        // book name
        bookNameLbl = new JLabel("Book Name :");
        bookNameLbl.setForeground(new Color(23, 29, 28));
        bookNameLbl.setBounds(250,165, 100, 30);
        bookNameLbl.setHorizontalAlignment(SwingConstants.RIGHT);
        bookNameLbl.setFont(normalFont);

        bookNameField = new JTextField();
        bookNameField.setBounds(360, 165, 200, 30);
        bookNameField.setFont(normalFont);

        // student id
        studIdLbl = new JLabel("Student Id :");
        studIdLbl.setForeground(new Color(23, 29, 28));
        studIdLbl.setBounds(250,200, 100, 30);
        studIdLbl.setHorizontalAlignment(SwingConstants.RIGHT);
        studIdLbl.setFont(normalFont);

        stuIdField = new JTextField();
        stuIdField.setBounds(360, 200, 200, 30);
        stuIdField.setFont(normalFont);

        // student name
        stuNameLbl = new JLabel("Student Name :");
        stuNameLbl.setForeground(new Color(23,29,28));
        stuNameLbl.setBounds(250,235,100,30);
        stuNameLbl.setHorizontalAlignment(SwingConstants.RIGHT);
        stuNameLbl.setFont(normalFont);

        stuNameField = new JTextField();
        stuNameField.setBounds(360, 235, 200,30);
        stuNameField.setFont(normalFont);

        // course
        courseLbl = new JLabel("Course :");
        courseLbl.setForeground(new Color(23,29,28));
        courseLbl.setBounds(250,270,100,30);
        courseLbl.setHorizontalAlignment(SwingConstants.RIGHT);
        courseLbl.setFont(normalFont);

        courseField = new JTextField();
        courseField.setBounds(360, 270, 200, 30);
        courseField.setFont(normalFont);

        // branch
        branchLbl = new JLabel("Branch :");
        branchLbl.setForeground(new Color(23,29,28));
        branchLbl.setBounds(250,305,100,30);
        branchLbl.setHorizontalAlignment(SwingConstants.RIGHT);
        branchLbl.setFont(normalFont);

        branchField = new JTextField();
        branchField.setBounds(360, 305, 200, 30);
        branchField.setFont(normalFont);

        // date of Issue
        dOILbl = new JLabel("Issue Date :");
        dOILbl.setForeground(new Color(23,29,28));
        dOILbl.setBounds(250,340,100,30);
        dOILbl.setHorizontalAlignment(SwingConstants.RIGHT);
        dOILbl.setFont(normalFont);

        doIField = new JTextField();
        doIField.setBounds(360, 340, 200, 30);
        doIField.setFont(normalFont);

        // date of return
        doRLbl = new JLabel("Return Date :");
        doRLbl.setForeground(new Color(23,29,28));
        doRLbl.setBounds(250,375,100,30);
        doRLbl.setHorizontalAlignment(SwingConstants.RIGHT);
        doRLbl.setFont(normalFont);

        returnDate = new JDateChooser();
        returnDate.setBounds(360, 375, 200, 30);

        // search book button
        bookSearchBtn = new JButton("Search Book");
        bookSearchBtn.setForeground(new Color(239, 233, 244));
        bookSearchBtn.setBackground(new Color(10, 132, 255));
        bookSearchBtn.setOpaque(true);
        bookSearchBtn.setBorderPainted(false);
        bookSearchBtn.setBounds(560, 130, 150, 30);
        bookSearchBtn.setFont(normalFont);
        bookSearchBtn.addActionListener(this);
        bookSearchBtn.setCursor(handCursor);

        // search student button
        stuSearchBtn = new JButton("Search Student");
        stuSearchBtn.setForeground(new Color(239, 233, 244));
        stuSearchBtn.setBackground(new Color(10, 132, 255));
        stuSearchBtn.setOpaque(true);
        stuSearchBtn.setBorderPainted(false);
        stuSearchBtn.setBounds(560, 200, 150, 30);
        stuSearchBtn.setFont(normalFont);
        stuSearchBtn.addActionListener(this);
        stuSearchBtn.setCursor(handCursor);

        // return book button
        returnBtn = new JButton("Return Book");
        returnBtn.setForeground(new Color(239, 233, 244));
        returnBtn.setBackground(new Color(10, 132, 255));
        returnBtn.setOpaque(true);
        returnBtn.setBorderPainted(false);
        returnBtn.setBounds(340, 430, 150, 28);
        returnBtn.setFont(normalFont);
        returnBtn.addActionListener(this);
        returnBtn.setCursor(handCursor);

        // go back
        JLabel back = new JLabel("Go back");
        back.setForeground(new Color(23, 29, 28));
        back.setBounds(350, 460, 200, 20);
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
        returnBooksPanel.add(panelName);
        returnBooksPanel.add(bookIsbnLbl);
        returnBooksPanel.add(bookIsbnField);
        returnBooksPanel.add(bookNameLbl);
        returnBooksPanel.add(bookNameField);
        returnBooksPanel.add(studIdLbl);
        returnBooksPanel.add(stuIdField);
        returnBooksPanel.add(stuNameLbl);
        returnBooksPanel.add(stuNameField);
        returnBooksPanel.add(courseLbl);
        returnBooksPanel.add(courseField);
        returnBooksPanel.add(branchLbl);
        returnBooksPanel.add(branchField);
        returnBooksPanel.add(dOILbl);
        returnBooksPanel.add(doIField);
        returnBooksPanel.add(doRLbl);
        returnBooksPanel.add(returnDate);
        returnBooksPanel.add(bookSearchBtn);
        returnBooksPanel.add(stuSearchBtn);
        returnBooksPanel.add(returnBtn);
        returnBooksPanel.add(back);
    }

    public void actionPerformed(ActionEvent event) {

        try {

            DbCon con = new DbCon();

            if (event.getSource() == bookSearchBtn) {
                String bookSearchQuery = "SELECT bookid, isbn, bookname FROM book WHERE isbn=?";
                PreparedStatement pst = con.con.prepareStatement(bookSearchQuery);
                pst.setString(1, bookIsbnField.getText());
                ResultSet rs = pst.executeQuery();

                while (rs.next()) {
                    bookIdField.setText(rs.getString("bookid"));
                    bookIsbnField.setText(rs.getString("isbn"));
                    bookNameField.setText(rs.getString("bookname"));
                }

                pst.close();
                rs.close();
            }

            if (event.getSource() == stuSearchBtn) {
                String stuSearchQuery = "SELECT stuid, stufname, stulname, course, branch FROM student WHERE stuid=?";

                PreparedStatement pst = con.con.prepareStatement(stuSearchQuery);
                pst.setString(1, stuIdField.getText());
                ResultSet rs = pst.executeQuery();

                while (rs.next()) {
                    stuIdField.setText(rs.getString("stuid"));
                    stuNameField.setText(rs.getString("stufname"));
                    courseField.setText(rs.getString("course"));
                    branchField.setText(rs.getString("branch"));
                }

                pst.close();
                rs.close();
            }

            if (event.getSource() == returnBtn) {

                String issueQuery = "INSERT INTO bookReturn(bookid, isbn, stuid, bookname, stuname, course, branch, dateOfIssue, dateOfReturn) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?)";

                PreparedStatement pst = con.con.prepareStatement(issueQuery);
                pst.setString(1, bookIdField.getText());
                pst.setString(2, bookIsbnField.getText());
                pst.setString(3, stuIdField.getText());
                pst.setString(4, bookNameField.getText());
                pst.setString(5, stuNameField.getText());
                pst.setString(6, courseField.getText());
                pst.setString(7, branchField.getText());
                pst.setString(8, doIField.getText());
                pst.setString(9, ((JTextField) returnDate.getDateEditor().getUiComponent()).getText());

                int i = pst.executeUpdate();

                if (i > 0) {
                    JOptionPane.showMessageDialog(null, "Book Returned");
                } else {
                    JOptionPane.showMessageDialog(null, "Error occurred! Try again");
                }
            }

            con.con.close();
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Error Occurred! Please try again later");
            e.printStackTrace();
        }

    }
}
