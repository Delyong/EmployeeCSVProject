package com.sparta.employeecsv;

import com.sparta.employeecsv.controller.CSVController;
import com.sparta.employeecsv.view.DisplayManager;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class CSVMain {

    public static Logger logger = LogManager.getLogger("CSV-Logger");

    /**
     * Launch the application.
     */
    public static void main(String[] args) {

        try {

            CSVController controller = new CSVController();
            DisplayManager window = new DisplayManager();

            controller.setupDatabase();

            ActionListener buttonPress = new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    String filename = window.getFilename();

                    long startTime = System.nanoTime();
                    controller.getFile(filename);
                    System.out.println("Reading records took: " + (System.nanoTime() - startTime) + " nano seconds");

                    window.setDuplicateNumber(controller.getDuplicateCount());
                    window.setUniqueNumber(controller.getUniqueCount());
                    window.setCorruptedNumber(controller.getCorruptedCount());

                    window.listDuplicates(controller.getDuplicatesString());


                    //controller.insertRecordsToDatabase();
                    Thread th1 = new Thread(controller);
                    Thread th2 = new Thread(controller);
                    th1.start();
                    th2.start();
                    try {
                        th1.join();
                        th2.join();
                    } catch (InterruptedException ie) {
                        ie.printStackTrace();
                    }

                }
            };

            window.initialize(buttonPress);
            window.frame.setVisible(true);

        } catch (Exception e) {
            e.printStackTrace();
        }

    }
}