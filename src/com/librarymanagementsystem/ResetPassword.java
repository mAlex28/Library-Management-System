package com.librarymanagementsystem;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

public class ResetPassword extends JFrame implements ActionListener, MouseListener {

    private JPanel forgotPanel;
    private JTextField username, password, secQ, secA ;
    private JButton resetBtn, searchBtn;
    private JLabel login, usernameLbl, passwordLbl, secQLbl, secALbl, panelName;

    public static void main(String[] args) {
        new ResetPassword().setVisible(true);
    }

    public ResetPassword() {
        Font normalFont = new Font("Helvetica", Font.PLAIN, 14);
        Cursor handCursor = new Cursor(Cursor.HAND_CURSOR);

        setTitle("Reset Password");
        setBackground(new Color(239, 233, 244));
        setSize(800, 600);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setResizable(false);
        setLocationRelativeTo(null);

        forgotPanel = new JPanel();
        forgotPanel.setBackground(new Color(242, 242, 247));
        setContentPane(forgotPanel);
        forgotPanel.setLayout(null);

        panelName = new JLabel("Reset password");
        panelName.setForeground(new Color(162, 132, 94));
        panelName.setBounds(300,40, 400, 25);
        panelName.setFont(new Font("Helvetica", Font.BOLD, 25));

        // username
        usernameLbl = new JLabel("Username :");
        usernameLbl.setForeground(new Color(23, 29, 28));
        usernameLbl.setBounds(250,130, 95, 30);
        usernameLbl.setFont(normalFont);

        username = new JTextField();
        username.setBounds(360, 130, 200, 30);
        username.setFont(normalFont);

        // password
        passwordLbl = new JLabel("Password :");
        passwordLbl.setForeground(new Color(23, 29, 28)) ;
        passwordLbl.setBounds(250,165, 95, 30);
        passwordLbl.setFont(normalFont);

        password = new JPasswordField();
        password.setBounds(360, 165, 200, 30);
        password.setFont(normalFont);

        // security question
        secQLbl = new JLabel("Security Question :");
        secQLbl.setForeground(new Color(23, 29, 28));
        secQLbl.setBounds(200,200, 135, 30);
        secQLbl.setFont(normalFont);

        secQ = new JTextField();
        secQ.setBounds(360, 200, 200, 30);
        secQ.setFont(normalFont);

        // security answer
        secALbl = new JLabel("Security Answer :");
        secALbl.setForeground(new Color(23, 29, 28));
        secALbl.setBounds(210,235, 135, 30);
        secALbl.setFont(normalFont);

        secA = new JTextField();
        secA.setBounds(360, 235, 200, 30);
        secA.setFont(normalFont);

        // reset button
        resetBtn = new JButton("Reset");
        resetBtn.setForeground(new Color(239, 233, 244));
        resetBtn.setBackground(new Color(10, 132, 255));
        resetBtn.setOpaque(true);
        resetBtn.setBorderPainted(false);
        resetBtn.setBounds(340, 290, 100, 28);
        resetBtn.setFont(normalFont);
        resetBtn.addActionListener(this);
        resetBtn.setCursor(handCursor);

        // search button
        searchBtn = new JButton("Search");
        searchBtn.setForeground(new Color(239, 233, 244));
        searchBtn.setBackground(new Color(10, 132, 255));
        searchBtn.setOpaque(true);
        searchBtn.setBorderPainted(false);
        searchBtn.setBounds(570,130, 100, 28);
        searchBtn.setFont(normalFont);
        searchBtn.addActionListener(this);
        searchBtn.setCursor(handCursor);

        // redirect to log in
        login = new JLabel("Go to login");
        login.setForeground(new Color(23, 29, 28));
        login.setBounds(350, 340, 200, 20);
        login.setFont(normalFont);
        login.setCursor(handCursor);
        login.addMouseListener(this);

        // add to panel
        forgotPanel.add(panelName);
        forgotPanel.add(usernameLbl);
        forgotPanel.add(username);
        forgotPanel.add(secQLbl);
        forgotPanel.add(secQ);
        forgotPanel.add(secALbl);
        forgotPanel.add(secA);
        forgotPanel.add(passwordLbl);
        forgotPanel.add(password);
        forgotPanel.add(searchBtn);
        forgotPanel.add(resetBtn);
        forgotPanel.add(login);

    }

    @Override
    public void actionPerformed(ActionEvent event) {
        try {
            DbCon con = new DbCon();

            if (event.getSource() == searchBtn) {
                String searchQuery = "SELECT * FROM `admin` WHERE username=?";
                PreparedStatement pst = con.con.prepareStatement(searchQuery);

                pst.setString(1, username.getText());
                ResultSet rs = pst.executeQuery();

                while (rs.next()) {
                    secQ.setText(rs.getString("securityQ"));
                }
            }

            if (event.getSource() == resetBtn) {
                String resetQuery = "UPDATE `admin` SET password = ? WHERE username = ? AND securityA=?";
                PreparedStatement pst = con.con.prepareStatement(resetQuery);

                pst.setString(1, password.getText());
                pst.setString(2, username.getText());
                pst.setString(3, secA.getText().toLowerCase());

                pst.executeUpdate();

                JOptionPane.showMessageDialog(null, "Password reset successful. Login again");
                setVisible(false);
                Login login = new Login();
                login.setVisible(true);
            }

        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Error occurred! try again later");
            e.printStackTrace();
        }
    }

    @Override
    public void mouseClicked(MouseEvent mouseEvent) {
        if (mouseEvent.getSource() == login) {
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
        if (mouseEvent.getSource() == login) {
            login.setForeground(new Color(19, 123, 255));
        }

        if (mouseEvent.getSource() == login) {
            login.setForeground(new Color(19, 123, 255));
        }
    }

    @Override
    public void mouseExited(MouseEvent mouseEvent) {
        if (mouseEvent.getSource() == login) {
            login.setForeground(new Color(23, 29, 28));
        }

        if (mouseEvent.getSource() == login) {
            login.setForeground(new Color(23, 29, 28));
        }
    }
}
