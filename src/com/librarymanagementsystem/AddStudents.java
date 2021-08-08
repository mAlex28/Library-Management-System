package com.librarymanagementsystem;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.sql.PreparedStatement;

public class AddStudents extends JFrame implements ActionListener {

    private final JLabel fnameLbl, lnameLbl, courseLbl, branchLbl, yearLbl, phoneLbl, back;
    private final JTextField fnameField, lnameField, yearField, phoneField;
    private final JComboBox branchBx, courseBx;
    private final JPanel addStudentsPanel;

    public AddStudents() {
        Font normalFont = new Font("Helvetica", Font.PLAIN, 14);
        Cursor handCursor = new Cursor(Cursor.HAND_CURSOR);

        setTitle("Library Management System");
        setBackground(new Color(242, 242, 247));
        setSize(800, 600);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setResizable(false);
        setLocationRelativeTo(null);

        // panel settings
        addStudentsPanel = new JPanel();
        addStudentsPanel.setBackground(new Color(242, 242, 247));
        setContentPane(addStudentsPanel);
        addStudentsPanel.setLayout(null);

        // panel name
        JLabel panelName = new JLabel("Add Students");
        panelName.setForeground(new Color(162, 132, 94));
        panelName.setBounds(300,40, 400, 25);
        panelName.setFont(new Font("Helvetica", Font.BOLD, 25));

        // first name
        fnameLbl= new JLabel("First Name:");
        fnameLbl.setForeground(new Color(23, 29, 28));
        fnameLbl.setBounds(250,130, 100, 30);
        fnameLbl.setHorizontalAlignment(SwingConstants.RIGHT);
        fnameLbl.setFont(normalFont);

        fnameField = new JTextField();
        fnameField.setBounds(360, 130, 200, 30);
        fnameField.setFont(normalFont);

        // last name
        lnameLbl = new JLabel("Last Name :");
        lnameLbl.setForeground(new Color(23, 29, 28));
        lnameLbl.setBounds(250,165, 100, 30);
        lnameLbl.setHorizontalAlignment(SwingConstants.RIGHT);
        lnameLbl.setFont(normalFont);

        lnameField = new JTextField();
        lnameField.setBounds(360, 165, 200, 30);
        lnameField.setFont(normalFont);

        // course
        courseLbl = new JLabel("Course :");
        courseLbl.setForeground(new Color(23, 29, 28));
        courseLbl.setBounds(250,200, 100, 30);
        courseLbl.setHorizontalAlignment(SwingConstants.RIGHT);
        courseLbl.setFont(normalFont);

        courseBx = new JComboBox();
        courseBx.setModel(new DefaultComboBoxModel(new String[] { "COM102", "COM104", "COM106", "BS1002", "BS1004", "BS1006",
                "ENG2020", "ENG2050", "ENG2040" }));
        courseBx.setBounds(360, 200, 200, 30);

        // branch
        branchLbl = new JLabel("Faculty :");
        branchLbl.setForeground(new Color(23,29,28));
        branchLbl.setBounds(250,235,100,30);
        branchLbl.setHorizontalAlignment(SwingConstants.RIGHT);
        branchLbl.setFont(normalFont);

        branchBx = new JComboBox();
        branchBx.setModel(new DefaultComboBoxModel(new String[] { "School of Computing", "School of Business",
                "School of Engineering" }));
        branchBx.setBounds(360, 235, 200, 30);

        // year of studying
        yearLbl = new JLabel("Year :");
        yearLbl.setForeground(new Color(23,29,28));
        yearLbl.setBounds(250,270,100,30);
        yearLbl.setHorizontalAlignment(SwingConstants.RIGHT);
        yearLbl.setFont(normalFont);

        yearField = new JTextField();
        yearField.setBounds(360, 270, 200, 30);
        yearField.setFont(normalFont);

        // phone number
        phoneLbl = new JLabel("Phone :");
        phoneLbl.setForeground(new Color(23,29,28));
        phoneLbl.setBounds(250,305,100,30);
        phoneLbl.setHorizontalAlignment(SwingConstants.RIGHT);
        phoneLbl.setFont(normalFont);

        phoneField = new JTextField();
        phoneField.setBounds(360, 305, 200, 30);
        phoneField.setFont(normalFont);

        // add student button
        JButton addStudBtn = new JButton("Register");
        addStudBtn.setForeground(new Color(239, 233, 244));
        addStudBtn.setBackground(new Color(10, 132, 255));
        addStudBtn.setOpaque(true);
        addStudBtn.setBorderPainted(false);
        addStudBtn.setBounds(340, 360, 100, 28);
        addStudBtn.setFont(normalFont);
        addStudBtn.addActionListener(this);
        addStudBtn.setCursor(handCursor);

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
        addStudentsPanel.add(panelName);
        addStudentsPanel.add(fnameLbl);
        addStudentsPanel.add(fnameField);
        addStudentsPanel.add(lnameLbl);
        addStudentsPanel.add(lnameField);
        addStudentsPanel.add(courseLbl);
        addStudentsPanel.add(courseBx);
        addStudentsPanel.add(branchLbl);
        addStudentsPanel.add(branchBx);
        addStudentsPanel.add(yearLbl);
        addStudentsPanel.add(yearField);
        addStudentsPanel.add(phoneLbl);
        addStudentsPanel.add(phoneField);
        addStudentsPanel.add(addStudBtn);
        addStudentsPanel.add(back);
    }

    public void actionPerformed(ActionEvent event) {
        try{
            DbCon con = new DbCon();

                String insertQuery = "INSERT INTO student (stufname, stulname, course, branch, year, phone) values(?, ?, ?, ?, ?, ?)";
                PreparedStatement pst = con.con.prepareStatement(insertQuery);

                pst.setString(1, fnameField.getText());
                pst.setString(2, lnameField.getText().toLowerCase());
                pst.setString(3, courseBx.getSelectedItem().toString());
                pst.setString(4, branchBx.getSelectedItem().toString());
                pst.setString(5, yearField.getText().toLowerCase());
                pst.setString(6, phoneField.getText().toLowerCase());

                int i = pst.executeUpdate();

                if (i > 0){
                    JOptionPane.showMessageDialog(null, "Student Added");
                }

            fnameField.setText("");
            lnameField.setText("");
            yearField.setText("");
            phoneField.setText("");


        }catch(Exception e){
            JOptionPane.showMessageDialog(null, "Error occurred! Try again later");
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        new AddStudents().setVisible(true);
    }
}
