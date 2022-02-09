package com.sparta.employeecsv.view;

import com.sparta.employeecsv.controller.CSVController;

import java.awt.*;

public class FactoryMain {

    /**
     * Launch the application.
     */
    public static void main(String[] args) {
        EventQueue.invokeLater(new Runnable() {
            public void run() {
                try {

                    CSVController controller = new CSVController();

                    DisplayManager window = new DisplayManager(controller);

                    //get back records from controller

                    window.initialize();
                    window.frame.setVisible(true);

                    // get the file path from displayManager
                    // System.out.println(window.buttonFunction());

                    //pass into controller read file in controller
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });
    }
}
