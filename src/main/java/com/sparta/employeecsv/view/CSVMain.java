package com.sparta.employeecsv.view;

import com.sparta.employeecsv.controller.CSVController;
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
                        CSVController controller = new CSVController();
                        controller.setupDatabase();
                        DisplayManager window = new DisplayManager(controller);

        try {
            CSVController controller = new CSVController();
            DisplayManager window = new DisplayManager();
                    } catch (Exception e) {
                    e.printStackTrace();

            controller.setupDatabase();
    
            ActionListener buttonPress = new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    String filename = window.getFilename();
    
                    controller.getFile(filename);
    
                    window.setDuplicateNumber(controller.getDuplicateCount());
                    window.setUniqueNumber(controller.getUniqueCount());
                    window.setCorruptedNumber(controller.getCorruptedCount());
    
                    window.listDuplicates(controller.getDuplicatesString());
    
                    controller.insertRecordsToDatabase();
            window.initialize(buttonPress);
            window.frame.setVisible(true);
    
        } catch (Exception e) {
            e.printStackTrace();
        }
    
    }
}
