package com.librarymanagementsystem;

import net.proteanit.sql.DbUtils;

import javax.swing.*;
import javax.swing.border.LineBorder;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

public class StudentDetails extends JFrame implements ActionListener {

    private JTable stuTbl;
    private JTextField search;
    private JButton searchBtn, deleteBtn;


    public StudentDetails() {
        Font normalFont = new Font("Helvetica", Font.PLAIN, 14);
        Cursor handCursor = new Cursor(Cursor.HAND_CURSOR);

        setTitle("Library Management System");
        setBackground(new Color(242, 242, 247));
        setSize(800, 600);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setResizable(false);
        setLocationRelativeTo(null);

        // panel settings
        JPanel stuDetailPanel = new JPanel();
        stuDetailPanel.setBackground(new Color(242, 242, 247));
        setContentPane(stuDetailPanel);
        stuDetailPanel.setLayout(null);

        // panel name
        JLabel panelName = new JLabel("Student Details");
        panelName.setForeground(new Color(162, 132, 94));
        panelName.setBounds(300,40, 400, 25);
        panelName.setFont(new Font("Helvetica", Font.BOLD, 25));

        JScrollPane scrollPane = new JScrollPane();
        scrollPane.setBounds(50, 140, 770, 300);
        stuDetailPanel.add(scrollPane);

        // table
        stuTbl = new JTable();
        stuTbl.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent arg0) {
                int row = stuTbl.getSelectedRow();
                search.setText(stuTbl.getModel().getValueAt(row, 1).toString());
            }
        });
        stuTbl.setBackground(new Color(240, 248, 255));
        stuTbl.setForeground(Color.DARK_GRAY);
        stuTbl.setFont(normalFont);
        scrollPane.setViewportView(stuTbl);

        // search button
        searchBtn = new JButton("Search");
        searchBtn.setForeground(new Color(239, 233, 244));
        searchBtn.setBackground(new Color(10, 132, 255));
        searchBtn.setOpaque(true);
        searchBtn.setBorderPainted(false);

        searchBtn.addActionListener(this);
        searchBtn.setFont(normalFont);
        searchBtn.setBounds(564, 89, 138, 33);
        stuDetailPanel.add(searchBtn);

        // delete button
        deleteBtn = new JButton("Delete");
        deleteBtn.setForeground(new Color(239, 233, 244));
        deleteBtn.setBackground(new Color(10, 132, 255));
        deleteBtn.setOpaque(true);
        deleteBtn.setBorderPainted(false);
        deleteBtn.setCursor(handCursor);
        deleteBtn.addActionListener(this);
        deleteBtn.setFont(normalFont);
        deleteBtn.setBounds(712, 89, 138, 33);
        stuDetailPanel.add(deleteBtn);

        // student details lable
        JLabel l1 = new JLabel("Student Details");
        l1.setForeground(new Color(162, 132, 94));
        l1.setFont(normalFont);
        l1.setBounds(250, 20, 400, 47);
        stuDetailPanel.add(l1);

        // search filed
        search = new JTextField();
        search.setBackground(new Color(239, 233, 244));
        search.setBorder(new LineBorder(new Color(19, 123, 255), 2, true));
        search.setForeground(new Color(44, 44, 44));
        search.setFont(normalFont);
        search.setBounds(189, 89, 357, 33);
        stuDetailPanel.add(search);
        search.setColumns(10);

        // back
        JLabel back = new JLabel("Back");
        back.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                setVisible(false);
                Home home = new Home();
                home.setVisible(true);
            }
        });
        back.setForeground(Color.GRAY);
        back.setFont(normalFont);
        back.setBounds(97, 89, 72, 33);
        stuDetailPanel.add(back);

        stuDetails();

    }

    public void stuDetails() {
        try {
            DbCon con = new DbCon();
            String stuSelectQuery = "SELECT * FROM student";
            PreparedStatement st = con.con.prepareStatement(stuSelectQuery);
            ResultSet rs = st.executeQuery();

            stuTbl.setModel(DbUtils.resultSetToTableModel(rs));

            rs.close();
            st.close();
            con.con.close();

        } catch (Exception e) {

        }
    }


    @Override
    public void actionPerformed(ActionEvent actionEvent) {
        try{

            DbCon con = new DbCon();
            if( actionEvent.getSource() == searchBtn){
                String searchQuery = "select * from student where concat(fname, lname) like ?";
                PreparedStatement st = con.con.prepareStatement(searchQuery);
                st.setString(1, "%" + search.getText() + "%");
                ResultSet rs = st.executeQuery();

                stuTbl.setModel(DbUtils.resultSetToTableModel(rs));
                rs.close();
                st.close();
            }

            if(actionEvent.getSource() == deleteBtn){
                String deleteQuery = "delete from student where stuid = '" + search.getText() + "'";
                PreparedStatement st = con.con.prepareStatement(deleteQuery);

                JDialog.setDefaultLookAndFeelDecorated(true);
                int response = JOptionPane.showConfirmDialog(null, "Do you want to continue?", "Confirm",
                        JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE);
                if (response == JOptionPane.NO_OPTION) {

                } else if (response == JOptionPane.YES_OPTION) {
                    int rs = st.executeUpdate();
                    JOptionPane.showMessageDialog(null, "Deleted !!");
                } else if (response == JOptionPane.CLOSED_OPTION) {

                }
                st.close();

            }
            con.con.close();
        }catch(Exception e){

        }
    }
}
