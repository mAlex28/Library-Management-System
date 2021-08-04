package com.librarymanagementsystem;

import javax.swing.*;
import javax.xml.transform.Result;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class Login extends JFrame implements ActionListener, MouseListener {

    private JPanel loginPanel;
    private JTextField usernameField;
    private JPasswordField passwordField;
    private JButton loginBtn;
    private JLabel register, resetPass, usernameLbl, passwordLbl, name;

    public Login() {
        Font normalFont = new Font("Helvetica", Font.PLAIN, 14);
        Cursor handCursor = new Cursor(Cursor.HAND_CURSOR);

        setTitle("Login");
        setBackground(new Color(239, 233, 244));
        setSize(800, 600);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setResizable(false);
        setLocationRelativeTo(null);

        // login panel
        loginPanel = new JPanel(new GridLayout(2, 1));
        loginPanel.setBackground(new Color(239, 233, 244));
        setContentPane(loginPanel);
        loginPanel.setLayout(null);

        // system name
        name = new JLabel("Library Management System");
        name.setForeground(new Color(22, 186, 197));
        name.setBounds(230,40, 400, 25);
        name.setFont(new Font("Helvetica", Font.BOLD, 25));

        // username
        usernameLbl = new JLabel("Username :");
        usernameLbl.setForeground(new Color(23, 29, 28));
        usernameLbl.setBounds(250,130, 95, 30);
        usernameLbl.setFont(normalFont);

        usernameField = new JTextField();
        usernameField.setBounds(360, 130, 200, 30);
        usernameField.setFont(normalFont);

        // password
        passwordLbl = new JLabel("Password :");
        passwordLbl.setForeground(new Color(23, 29, 28));
        passwordLbl.setBounds(250,180, 95, 30);
        passwordLbl.setFont(normalFont);

        passwordField = new JPasswordField();
        passwordField.setBounds(360, 180, 200, 30);
        passwordField.setFont(normalFont);

        // loginBtn
        loginBtn = new JButton("Login");
        loginBtn.setForeground(new Color(239, 233, 244));
        loginBtn.setBackground(new Color(88, 99, 248));
        loginBtn.setOpaque(true);
        loginBtn.setBorderPainted(false);
        loginBtn.setBounds(340, 240, 100, 35);
        loginBtn.setFont(normalFont);
        loginBtn.addActionListener(this);
        loginBtn.setCursor(handCursor);

        // forgot password, signup label
        resetPass = new JLabel("Forgot Password");
        resetPass.setForeground(new Color(23, 29, 28));
        resetPass.setBounds(330, 340, 200, 20);
        resetPass.setFont(normalFont);
        resetPass.setCursor(handCursor);
        resetPass.addMouseListener(this);

        register = new JLabel("Register User");
        register.setForeground(new Color(23, 29, 28));
        register.setBounds(340, 360, 200, 20);
        register.setFont(normalFont);
        register.setCursor(handCursor);
        register.addMouseListener(this);

        // add items to the panel
        loginPanel.add(name);
        loginPanel.add(usernameLbl);
        loginPanel.add(usernameField);
        loginPanel.add(passwordLbl);
        loginPanel.add(passwordField);
        loginPanel.add(loginBtn);
        loginPanel.add(resetPass);
        loginPanel.add(register);

    }

    public void actionPerformed(ActionEvent event) {
        if (event.getSource() == loginBtn) {

            String username = usernameField.getText();
            String password = String.valueOf(passwordField.getPassword());

            String loginQuery = "SELECT * FROM `admin` WHERE `username`=? AND `password`=?";

            if (username.trim().equals("") || password.trim().equals("")) {
                JOptionPane.showMessageDialog(null, "Please fill all the fields", "Empty Field", 2);
            } else {

                try {

                    DbCon conn = new DbCon();
                    PreparedStatement pst = conn.con.prepareStatement(loginQuery);

                    pst.setString(1, username);
                    pst.setString(2, password);

                    ResultSet rs = pst.executeQuery();
                    if (rs.next()) {
                        this.setVisible(false);
                        new Loading().setVisible(true);
                    } else {
                        JOptionPane.showMessageDialog(null, "Incorrect username or password.");
                    }
                } catch (Exception ex) {
                    ex.printStackTrace();
                }
            }
        }
    }

    public void mouseClicked(MouseEvent e) {
        if (e.getSource() == resetPass) {
            setVisible(false);
            ResetPassword resetPassword = new ResetPassword();
//            resetPassword.setVisible(true);
        }

        if (e.getSource() == register) {
            setVisible(false);
            Register register = new Register();
//            register.setVisible(true);
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
        if (mouseEvent.getSource() == register) {
            register.setForeground(new Color(19, 123, 255));
        }

        if (mouseEvent.getSource() == resetPass) {
            resetPass.setForeground(new Color(19, 123, 255));
        }
    }

    @Override
    public void mouseExited(MouseEvent mouseEvent) {
        if (mouseEvent.getSource() == register) {
            register.setForeground(new Color(23, 29, 28));
        }

        if (mouseEvent.getSource() == resetPass) {
            resetPass.setForeground(new Color(23, 29, 28));
        }
    }

    public static void main(String[] args) {
        new Login().setVisible(true);
    }
}
