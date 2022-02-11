package com.sparta.employeecsv.view;

import com.sparta.employeecsv.controller.CSVController;
import com.sparta.employeecsv.model.ReadFile;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Scanner;

public class DisplayManager {

    public JFrame frame;
    private JTextField filenameFld;
    private JLabel lblUniqueNumber;
    private JLabel lblDupNumber;
    private JLabel lblCurNumber;
    private JTextArea duplicateListFld;

    /**
     * Initialize the contents of the frame.
     */
    public void initialize(ActionListener buttonPress) {
        frame = new JFrame();
        frame.setResizable(false);
        frame.setBounds(100, 100, 673, 507);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.getContentPane().setLayout(null);

        JLabel lblNewLabel = new JLabel("Enter file name:");
        lblNewLabel.setBounds(46, 77, 106, 14);
        frame.getContentPane().add(lblNewLabel);

        filenameFld = new JTextField();
        filenameFld.setBounds(141, 74, 348, 20);
        frame.getContentPane().add(filenameFld);
        filenameFld.setColumns(10);

        JLabel lblNewLabel_1 = new JLabel("Number of unique records:");
        lblNewLabel_1.setBounds(46, 150, 250, 14);
        frame.getContentPane().add(lblNewLabel_1);

        JLabel lblNewLabel_2 = new JLabel("Number of duplicate records:");
        lblNewLabel_2.setBounds(46, 180, 250, 14);
        frame.getContentPane().add(lblNewLabel_2);

        JLabel lblNewLabel_3 = new JLabel("Number of Corrupted records:");
        lblNewLabel_3.setBounds(46, 120, 250, 14);
        frame.getContentPane().add(lblNewLabel_3);

        lblUniqueNumber = new JLabel("N/A");
        lblUniqueNumber.setBounds(240, 150, 78, 14);
        frame.getContentPane().add(lblUniqueNumber);

        lblDupNumber = new JLabel("N/A");
        lblDupNumber.setBounds(240, 180, 78, 14);
        frame.getContentPane().add(lblDupNumber);

        lblCurNumber = new JLabel("N/A");
        lblCurNumber.setBounds(240, 120, 78, 14);
        frame.getContentPane().add(lblCurNumber);

        JLabel lblNewLabel_4 = new JLabel("Duplicated records listed:");
        lblNewLabel_4.setBounds(46, 214, 200, 14);
        frame.getContentPane().add(lblNewLabel_4);

        JScrollPane scrollPane = new JScrollPane();
        scrollPane.setBounds(46, 239, 570, 218);
        frame.getContentPane().add(scrollPane);

        duplicateListFld = new JTextArea();
        scrollPane.setViewportView(duplicateListFld);

        JLabel lblNewLabel_5 = new JLabel("Employees CSV");
        lblNewLabel_5.setFont(new Font("Tahoma", Font.PLAIN, 28));
        lblNewLabel_5.setHorizontalAlignment(SwingConstants.CENTER);
        lblNewLabel_5.setBounds(10, 11, 637, 40);
        frame.getContentPane().add(lblNewLabel_5);

        JButton btnStart = new JButton("Start");
        btnStart.setBounds(500, 73, 89, 23);
        frame.getContentPane().add(btnStart);
        btnStart.addActionListener(buttonPress);
    }


    /**
     * display number of duplicate records on the GUI
     *
     * @param numberOfDuplicates Integer value of number of duplicate record
     */
    public void setDuplicateNumber(int numberOfDuplicates) {
        lblDupNumber.setText("" + numberOfDuplicates);
    }

    /**
     * display number of unique records on the GUI
     *
     * @param numberOfUnique Integer value of number of unique record
     */
    public void setUniqueNumber(int numberOfUnique) {
        lblUniqueNumber.setText("" + numberOfUnique);
    }

    public void setCorruptedNumber(int numberOfCorrupted) {
        lblCurNumber.setText("" + numberOfCorrupted);
    }

    /**
     * Gets a list of object with duplicate records and toString Function. Prints it on the Text Area in
     * GUI for duplicate record list
     */
    public void listDuplicates(String duplicates) {
        duplicateListFld.setText(duplicates);
    }

    public String getFilename() {
        return filenameFld.getText();
    }

    public String getThreadCount() {

        System.out.println("Please enter the desired number of threads: ");

        Scanner scanner = new Scanner(System.in);
        String input = scanner.next();

        return input;

    }

    public void displayInvalidThreadMsg() {
        System.out.println("Invalid thread count please input a number between 1 and 100!");
    }
}

