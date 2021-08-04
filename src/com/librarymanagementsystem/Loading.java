package com.librarymanagementsystem;

import javax.swing.*;

public class Loading extends JFrame {

    private JPanel loadingPanel;
    private JProgressBar progressBar;
    DbCon conn;
    int s;
    Thread thread;

    public Loading() {
        super("Loading....");
        thread = new Thread((Runnable) this);

        setSize(800, 600);

        loadingPanel = new JPanel();
        setContentPane(loadingPanel);

    }

    public static void main(String[] args) {
        new Loading().setVisible(true);
    }


}
