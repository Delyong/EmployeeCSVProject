package com.sparta.employeecsv.view;

import com.sparta.employeecsv.controller.CSVController;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

public class DisplayManager {

    public JFrame frame;
    private JTextField filenameFld;
    private JLabel lblUniqueNumber;
    private JLabel lblDupNumber;
    private JTextArea duplicateListFld;

    private boolean isButtonPressed = false;
    private String filename;

    private CSVController controller;

    public DisplayManager(CSVController controller) {

        this.controller = controller;

    }

    /**
     * Initialize the contents of the frame.
     */
    public void initialize() {
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
        lblNewLabel_1.setBounds(46, 128, 141, 14);
        frame.getContentPane().add(lblNewLabel_1);

        JLabel lblNewLabel_1_1 = new JLabel("Number of duplicate records:");
        lblNewLabel_1_1.setBounds(46, 164, 141, 14);
        frame.getContentPane().add(lblNewLabel_1_1);

        lblUniqueNumber = new JLabel(String.valueOf(controller.getUniqueCount()));
        lblUniqueNumber.setBounds(197, 128, 78, 14);
        frame.getContentPane().add(lblUniqueNumber);

        lblDupNumber = new JLabel(String.valueOf(controller.getDuplicateCount()));
        lblDupNumber.setBounds(197, 164, 78, 14);
        frame.getContentPane().add(lblDupNumber);

        JLabel lblNewLabel_4 = new JLabel("Duplicated records listed:");
        lblNewLabel_4.setBounds(46, 214, 141, 14);
        frame.getContentPane().add(lblNewLabel_4);

        JScrollPane scrollPane = new JScrollPane();
        scrollPane.setBounds(46, 239, 570, 218);
        frame.getContentPane().add(scrollPane);

        duplicateListFld = new JTextArea(controller.getDuplicatesString());
        scrollPane.setViewportView(duplicateListFld);

        JLabel lblNewLabel_5 = new JLabel("Employees CSV");
        lblNewLabel_5.setFont(new Font("Tahoma", Font.PLAIN, 28));
        lblNewLabel_5.setHorizontalAlignment(SwingConstants.CENTER);
        lblNewLabel_5.setBounds(10, 11, 637, 40);
        frame.getContentPane().add(lblNewLabel_5);

        JButton btnStart = new JButton("Start");
        btnStart.setBounds(500, 73, 89, 23);
        frame.getContentPane().add(btnStart);
        btnStart.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                buttonFunction();
            }
        });
    }

    /**
     * This function runs when the start button is pressed
     */
    private void buttonFunction() {
        filename = filenameFld.getText();
        isButtonPressed = true;

    }

    public boolean isButtonPressed() {
        return isButtonPressed;
    }

    public String getFilename() {
        return filename;
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

    /**
     * Gets a list of object with duplicate records and toString Function. Prints it on the Text Area in
     * GUI for duplicate record list
     *
     * @param list list of duplicate records
     */
    public void listDuplicates(ArrayList<Object> list) {
        for (int i = 0; i < list.size(); i++) {
            duplicateListFld.append(list.get(i).toString());
        }
    }
}
