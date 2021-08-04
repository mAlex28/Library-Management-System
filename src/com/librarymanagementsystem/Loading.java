package com.librarymanagementsystem;

import javax.swing.*;
import java.awt.*;

public class Loading extends JFrame implements Runnable {

    private JPanel loadingPanel;
    private JProgressBar progressBar;
    int s;
    Thread thread;

    public static void main(String[] args) {
        new Loading().setVisible(true);
    }

    public void setUpLoading() {
        setVisible(false);
        thread.start();
    }

    public void run() {
        try {
            for (int i = 0; i < 200; i++) {
                s = s + 1;
                int max = progressBar.getMaximum();
                int value = progressBar.getValue();
                if (value < max) {
                    progressBar.setValue(progressBar.getValue() + 1);
                } else {
                    i = 201;
                    setVisible(false);
                    new Home().setVisible(true);
                }
                Thread.sleep(50);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }


    public Loading() {
        super("Loading....");
        thread = new Thread((Runnable) this);

        Font normalFont = new Font("Helvetica", Font.PLAIN, 14);

        setTitle("Loading");
        setBackground(new Color(239, 233, 244));
        setSize(800, 600);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setResizable(false);
        setLocationRelativeTo(null);

        loadingPanel = new JPanel();
        setContentPane(loadingPanel);
        loadingPanel.setBackground(new Color(239, 233, 244));
        loadingPanel.setLayout(null);

        JLabel label = new JLabel("Library Management System");
        label.setForeground(new Color(23, 29, 28));
        label.setFont(normalFont);
        label.setBounds(300, 200, 300, 25);
        loadingPanel.add(label);

        progressBar = new JProgressBar();
        progressBar.setFont(normalFont);
        progressBar.setStringPainted(true);
        progressBar.setBounds(250, 250, 300, 25);
        loadingPanel.add(progressBar);

        JLabel waitLabel = new JLabel("Loading ....");
        waitLabel.setForeground(new Color(23, 29, 28));
        waitLabel.setFont(normalFont);
        waitLabel.setBounds(350, 280, 300, 25);
        loadingPanel.add(waitLabel);

        setUpLoading();
    }




}
