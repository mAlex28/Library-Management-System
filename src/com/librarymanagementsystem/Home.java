package com.librarymanagementsystem;

import javax.swing.*;
import javax.swing.border.EtchedBorder;
import java.awt.*;

public class Home extends JFrame {

    private JPanel homePanel;
    private JButton addBook, issueBook, returnBook;

    public Home() {
        Font normalFont = new Font("Helvetica", Font.PLAIN, 14);
        Cursor handCursor = new Cursor(Cursor.HAND_CURSOR);

        setTitle("Home");
        setBackground(new Color(239, 233, 244));
        setSize(800, 600);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setResizable(false);
        setLocationRelativeTo(null);

        // panel settings
        homePanel = new JPanel(new GridLayout(2, 1));
        homePanel.setBackground(new Color(239, 233, 244));
        setContentPane(homePanel);
        homePanel.setLayout(null);

        // menu
        JMenuBar menuBar = new JMenuBar();
        menuBar.setBorder(new EtchedBorder(EtchedBorder.LOWERED, new Color(148, 187, 209), new Color(143, 181, 203)));
        menuBar.setBackground(new Color(61, 89, 125));
        menuBar.setBounds(0, 0, 800, 35);
        homePanel.add(menuBar);

        // details menu
        JMenu detailsMenu = new JMenu("View");
//        record.setFont(normalFont);
        menuBar.add(detailsMenu);



        // exit and logout menu
        JMenu exitMenu = new JMenu("Exit");
//        exit.setFont(normalFont);
        menuBar.add(exitMenu);

        JMenuItem logout = new JMenuItem("Logout");
        logout.setBackground(new Color(211, 211, 211));
//        logout.addActionListener(this);
        exitMenu.add(logout);

        JMenuItem exit = new JMenuItem("Exit");
        exit.setBackground(new Color(211, 211, 211));
//        logout.addActionListener(this);
        exitMenu.add(exit);









    }
    public static void main(String[] args) {
        new Home().setVisible(true);
    }

}
