package com.librarymanagementsystem;

import javax.swing.*;
import javax.swing.border.EtchedBorder;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Home extends JFrame implements ActionListener {

    private JPanel homePanel;
    private JButton addBookBtn, issueBookBtn, returnBookBtn, addStuBtn, staticBtn;
    private JLabel addBookLbl, addStuLbl, staticLbl, issueBookLbl, returnBookLbl;

    public Home() {
        Font normalFont = new Font("Helvetica", Font.PLAIN, 14);
        Cursor handCursor = new Cursor(Cursor.HAND_CURSOR);

        setTitle("Home");
        setBackground(new Color(242, 242, 247));
        setSize(800, 600);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setResizable(false);
        setLocationRelativeTo(null);

        // panel settings
        homePanel = new JPanel(new GridLayout(2, 1));
        homePanel.setBackground(new Color(242, 242, 247));
        setContentPane(homePanel);
        homePanel.setLayout(null);

        // menu
        JMenuBar menuBar = new JMenuBar();
        menuBar.setBorder(new EtchedBorder(EtchedBorder.LOWERED, new Color(148, 187, 209), new Color(143, 181, 203)));
        menuBar.setBackground(new Color(61, 89, 125));
        menuBar.setBounds(0, 0, 800, 25);
        homePanel.add(menuBar);

        // details menu
        JMenu detailsMenu = new JMenu("View");
        menuBar.add(detailsMenu);

        JMenuItem bookdetails = new JMenuItem("Book details");
//        bookdetails.addActionListener(this);
        detailsMenu.add(bookdetails);

        JMenuItem studentdetails = new JMenuItem("Student details");
//        logout.addActionListener(this);
        detailsMenu.add(studentdetails);

        // exit and logout menu
        JMenu exitMenu = new JMenu("Exit");
        menuBar.add(exitMenu);

        JMenuItem logout = new JMenuItem("Logout");
//        logout.addActionListener(this);
        exitMenu.add(logout);

        JMenuItem exit = new JMenuItem("Exit");
//        logout.addActionListener(this);
        exitMenu.add(exit);

        JLabel label = new JLabel("Home");
        label.setForeground(new Color(162, 132, 94));
        label.setBounds(360,50, 100, 25);
        label.setFont(new Font("Helvetica", Font.BOLD, 25));
        homePanel.add(label);

        // add book
        ImageIcon bookImg = new ImageIcon(ClassLoader.getSystemResource("com/librarymanagementsystem/images/book.png"));

        addBookLbl = new JLabel("");
        addBookLbl.setVerticalAlignment(SwingConstants.CENTER);
        addBookLbl = new JLabel(bookImg);
        addBookLbl.setBounds(100, 100, 150, 150);
        homePanel.add(addBookLbl);

        addBookBtn = new JButton("Add Books");
        addBookBtn.setBackground(new Color(52, 199, 89));
        addBookBtn.setForeground(Color.white);
        addBookBtn.setBounds(100, 260, 150, 25);
        addBookBtn.setOpaque(true);
        addBookBtn.setBorderPainted(false);
        addBookBtn.setCursor(handCursor);
        addBookBtn.addActionListener(this);
        homePanel.add(addBookBtn);

        // add students
        ImageIcon stuImg = new ImageIcon(ClassLoader.getSystemResource("com/librarymanagementsystem/images/student.png"));

        addStuLbl = new JLabel("");
        addStuLbl.setVerticalAlignment(SwingConstants.CENTER);
        addStuLbl = new JLabel(stuImg);
        addStuLbl.setBounds(320, 100, 150,150);
        homePanel.add(addStuLbl);

        addStuBtn = new JButton("Add Students");
        addStuBtn.setBackground(new Color(52, 199, 89));
        addStuBtn.setForeground(Color.white);
        addStuBtn.setBounds(320, 260, 150, 25);
        addStuBtn.setOpaque(true);
        addStuBtn.setBorderPainted(false);
        addStuBtn.setCursor(handCursor);
        addStuBtn.addActionListener(this);
        homePanel.add(addStuBtn);

        // statics
        ImageIcon statImg = new ImageIcon(ClassLoader.getSystemResource("com/librarymanagementsystem/images/analytics.png"));

        staticLbl = new JLabel("");
        staticLbl.setVerticalAlignment(SwingConstants.CENTER);
        staticLbl = new JLabel(statImg);
        staticLbl.setBounds(540, 100, 150,150);
        homePanel.add(staticLbl);

        staticBtn = new JButton("View Statics");
        staticBtn.setBackground(new Color(52, 199, 89));
        staticBtn.setForeground(Color.white);
        staticBtn.setBounds(540, 260, 150, 25);
        staticBtn.setOpaque(true);
        staticBtn.setBorderPainted(false);
        staticBtn.setCursor(handCursor);
        staticBtn.addActionListener(this);
        homePanel.add(staticBtn);

        // issue books
        ImageIcon issueImg = new ImageIcon(ClassLoader.getSystemResource("com/librarymanagementsystem/images/library.png"));

        issueBookLbl = new JLabel("");
        issueBookLbl.setVerticalAlignment(SwingConstants.CENTER);
        issueBookLbl = new JLabel(issueImg);
        issueBookLbl.setBounds(200, 340, 150,150);
        homePanel.add(issueBookLbl);

        issueBookBtn = new JButton("Book Issue");
        issueBookBtn.setBackground(new Color(52, 199, 89));
        issueBookBtn.setForeground(Color.white);
        issueBookBtn.setBounds(200, 500, 150, 25);
        issueBookBtn.setOpaque(true);
        issueBookBtn.setBorderPainted(false);
        issueBookBtn.setCursor(handCursor);
        issueBookBtn.addActionListener(this);
        homePanel.add(issueBookBtn);

        // return books
        ImageIcon returnImg = new ImageIcon(ClassLoader.getSystemResource("com/librarymanagementsystem/images/clock.png"));

        returnBookLbl = new JLabel("");
        returnBookLbl.setVerticalAlignment(SwingConstants.CENTER);
        returnBookLbl = new JLabel(returnImg);
        returnBookLbl.setBounds(400, 340, 150,150);
        homePanel.add(returnBookLbl);

        returnBookBtn = new JButton("Book Return");
        returnBookBtn.setBackground(new Color(52, 199, 89));
        returnBookBtn.setForeground(Color.white);
        returnBookBtn.setBounds(400, 500, 150, 25);
        returnBookBtn.setOpaque(true);
        returnBookBtn.setBorderPainted(false);
        returnBookBtn.setCursor(handCursor);
        returnBookBtn.addActionListener(this);
        homePanel.add(returnBookBtn);

    }

    @Override
    public void actionPerformed(ActionEvent actionEvent) {

    }

    public static void main(String[] args) {
        new Home().setVisible(true);
    }

}
