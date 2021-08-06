package com.librarymanagementsystem;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import net.proteanit.sql.DbUtils;

public class Statistics extends JFrame {

    private JPanel staticPanel;
    private JTable issueTbl, returnTbl;
    PreparedStatement pst;
    ResultSet rs;

    public Statistics () {
        Font normalFont = new Font("Helvetica", Font.PLAIN, 14);
        Cursor handCursor = new Cursor(Cursor.HAND_CURSOR);

        setTitle("Library Management System");
        setBackground(new Color(242, 242, 247));
        setSize(800, 600);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setResizable(false);
        setLocationRelativeTo(null);

        // panel settings
        staticPanel = new JPanel(new GridLayout(2, 1));
        staticPanel.setBackground(new Color(242, 242, 247));
        setContentPane(staticPanel);
        staticPanel.setLayout(null);

        // back label
        JLabel backLbl = new JLabel("Home");
        backLbl.setFont(normalFont);
        backLbl.setBounds(50,10, 100, 25);
        backLbl.setCursor(handCursor);
        backLbl.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                setVisible(false);
                Home home = new Home();
                home.setVisible(true);
            }
        });
        staticPanel.add(backLbl);

        // book issue table
        JScrollPane scrollPane = new JScrollPane();
        scrollPane.setBounds(40,51,708,217);
        staticPanel.add(scrollPane);

        issueTbl = new JTable();
        issueTbl.setBackground(new Color(98, 137, 187));
        issueTbl.setForeground(new Color(128,128,0));
        issueTbl.setFont(normalFont);
        scrollPane.setViewportView(issueTbl);


        // book return table
        JScrollPane scrollPaner = new JScrollPane();
        scrollPaner.setBounds(40, 316, 717, 217);
        staticPanel.add(scrollPaner);

        returnTbl = new JTable();
        returnTbl.setBackground(new Color(98, 137, 187));
        returnTbl.setForeground(new Color(128,128,0));
        returnTbl.setFont(normalFont);
        scrollPaner.setViewportView(returnTbl);

        issueBook();
        retrunBook();
    }

    public void issueBook() {
        try {
            DbCon con = new DbCon();
            String issueBookQuery = "SELECT * FROM `bookIssue`";
            pst = con.con.prepareStatement(issueBookQuery);

            rs = pst.executeQuery();

            issueTbl.setModel(DbUtils.resultSetToTableModel(rs));

        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }

    public void retrunBook() {
        try {
            DbCon con = new DbCon();
            String returnBookQuery = "SELECT * FROM `bookReturn`";
            pst = con.con.prepareStatement(returnBookQuery);

            rs = pst.executeQuery();

            returnTbl.setModel(DbUtils.resultSetToTableModel(rs));

        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }

    public static void main(String[] args) {
        new Statistics().setVisible(true);
    }
}
