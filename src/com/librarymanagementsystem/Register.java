package com.librarymanagementsystem;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.Locale;

public class Register extends JFrame implements ActionListener, MouseListener {

    private JLabel username, password, secQ, secA, name, back, panelName;
    private JTextField usernameField, passwordField, secAField, nameField;
    private JComboBox secQComboBox;
    private JButton registerBtn;
    private JPanel registerPanel;

    public Register () {
        Font normalFont = new Font("Helvetica", Font.PLAIN, 14);
        Cursor handCursor = new Cursor(Cursor.HAND_CURSOR);

        setTitle("Library Management System");
        setBackground(new Color(242, 242, 247));
        setSize(800, 600);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setResizable(false);
        setLocationRelativeTo(null);

        registerPanel = new JPanel();
        registerPanel.setBackground(new Color(242, 242, 247));
        setContentPane(registerPanel);
        registerPanel.setLayout(null);

        panelName = new JLabel("Register an admin");
        panelName.setForeground(new Color(162, 132, 94));
        panelName.setBounds(300,40, 400, 25);
        panelName.setFont(new Font("Helvetica", Font.BOLD, 25));

        // name
        name = new JLabel("Name :");
        name.setForeground(new Color(23, 29, 28));
        name.setBounds(250,130, 95, 30);
        name.setFont(normalFont);

        nameField = new JTextField();
        nameField.setBounds(360, 130, 200, 30);
        nameField.setFont(normalFont);

        // username
        username = new JLabel("Username :");
        username.setForeground(new Color(23, 29, 28));
        username.setBounds(250,165, 95, 30);
        username.setFont(normalFont);

        usernameField = new JTextField();
        usernameField.setBounds(360, 165, 200, 30);
        usernameField.setFont(normalFont);

        // password
        password = new JLabel("Password :");
        password.setForeground(new Color(23, 29, 28)) ;
        password.setBounds(250,200, 95, 30);
        password.setFont(normalFont);

        passwordField = new JPasswordField();
        passwordField.setBounds(360, 200, 200, 30);
        passwordField.setFont(normalFont);

        // security question
        secQ = new JLabel("Security Question :");
        secQ.setForeground(new Color(23, 29, 28));
        secQ.setBounds(200,235, 135, 30);
        secQ.setFont(normalFont);

        secQComboBox = new JComboBox();
        secQComboBox.setModel(new DefaultComboBoxModel(new String[] { "Your nickName ?", "Your favourite dish ?",
                "Your child superhero ?", "Your first car ?", "Your city name ?" }));
        secQComboBox.setBounds(360, 235, 200, 30);

        // security answer
        secA = new JLabel("Security Answer :");
        secA.setForeground(new Color(23, 29, 28));
        secA.setBounds(210,270, 135, 30);
        secA.setFont(normalFont);

        secAField = new JTextField();
        secAField.setBounds(360, 270, 200, 30);
        secAField.setFont(normalFont);

        // register button
        registerBtn = new JButton("Register");
        registerBtn.setForeground(new Color(239, 233, 244));
        registerBtn.setBackground(new Color(10, 132, 255));
        registerBtn.setOpaque(true);
        registerBtn.setBorderPainted(false);
        registerBtn.setBounds(340, 340, 100, 28);
        registerBtn.setFont(normalFont);
        registerBtn.addActionListener(this);
        registerBtn.setCursor(handCursor);

        // back label
        back = new JLabel("Go back");
        back.setForeground(new Color(23, 29, 28));
        back.setBounds(350, 380, 200, 20);
        back.setFont(normalFont);
        back.setCursor(handCursor);
        back.addMouseListener(this);

        // add to panel
        registerPanel.add(panelName);
        registerPanel.add(name);
        registerPanel.add(nameField);
        registerPanel.add(username);
        registerPanel.add(usernameField);
        registerPanel.add(password);
        registerPanel.add(passwordField);
        registerPanel.add(secQ);
        registerPanel.add(secQComboBox);
        registerPanel.add(secA);
        registerPanel.add(secAField);
        registerPanel.add(registerBtn);
        registerPanel.add(back);

    }

    public static void main(String[] args) {
        new Register().setVisible(true);
    }

    @Override
    public void actionPerformed(ActionEvent event) {
        try{
            DbCon con = new DbCon();

            if(event.getSource() == registerBtn){

                String user = usernameField.getText();

                String selectQuery = "SELECT username FROM admin where username=?";
                PreparedStatement pstselect = con.con.prepareStatement(selectQuery);
                pstselect.setString(1, user);

                ResultSet rs = pstselect.executeQuery();

                if(rs.next()) {
                    JOptionPane.showMessageDialog(null, "Username already exists");

                }  else {

                    String insertQuery = "INSERT INTO admin (username, name, password, securityQ, securityA) values(?, ?, ?, ?, ?)";
                    PreparedStatement pst = con.con.prepareStatement(insertQuery);

                    pst.setString(1, nameField.getText());
                    pst.setString(2, usernameField.getText());
                    pst.setString(3, passwordField.getText());
                    pst.setString(4, (String) secQComboBox.getSelectedItem());
                    pst.setString(5, secAField.getText().toLowerCase());

                    int i = pst.executeUpdate();

                    if (i > 0){
                        JOptionPane.showMessageDialog(null, "Admin registered successfully");
                    }
                }

                nameField.setText("");
                usernameField.setText("");
                passwordField.setText("");
                secAField.setText("");
            }

        }catch(Exception e){
            JOptionPane.showMessageDialog(null, "Error occurred! Try again later");
            e.printStackTrace();
        }

    }

    @Override
    public void mouseClicked(MouseEvent mouseEvent) {
        if (mouseEvent.getSource() == back) {
            setVisible(false);
            Login login = new Login();
            login.setVisible(true);
        }
    }

    @Override
    public void mousePressed(MouseEvent mouseEvent) {

    }

    @Override
    public void mouseReleased(MouseEvent mouseEvent) {

    }

    @Override
    public void mouseEntered(MouseEvent mouseEvent) {
        if (mouseEvent.getSource() == back) {
            back.setForeground(new Color(19, 123, 255));
        }

    }

    @Override
    public void mouseExited(MouseEvent mouseEvent) {
        if (mouseEvent.getSource() == back) {
            back.setForeground(new Color(23, 29, 28));
        }


    }
}
