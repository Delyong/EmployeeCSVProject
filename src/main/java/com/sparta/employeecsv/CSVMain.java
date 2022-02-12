package com.sparta.employeecsv;

import com.sparta.employeecsv.controller.CSVController;
import com.sparta.employeecsv.view.DisplayManager;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;

public class CSVMain {

    public static Logger logger = LogManager.getLogger("CSV-Logger");

    /**
     * Launch the application.
     */
    public static void main(String[] args) {

        CSVController controller = new CSVController();
        logger.debug("Successfully created controller");
        DisplayManager window = new DisplayManager();
        logger.debug("Successfully created view");

        controller.setupDatabase();

        String threadCountStr = window.getThreadCount();
        boolean isValidThreadCount = controller.checkThreadCount(threadCountStr);

        while (!isValidThreadCount) {
            logger.warn("Invalid thread count input retrying");
            window.displayInvalidThreadMsg();
            threadCountStr = window.getThreadCount();
            isValidThreadCount = controller.checkThreadCount(threadCountStr);
        }

        int threadCount = controller.parseThreadCount(threadCountStr);

        ActionListener buttonEvent = e -> {

            String filename = window.getFilename();

            long startTime = System.nanoTime();
            controller.getFile(filename);
            System.out.println("Reading records took: " + (System.nanoTime() - startTime) + " nano seconds");

            window.setDuplicateNumber(controller.getDuplicateCount());
            window.setUniqueNumber(controller.getUniqueCount());
            window.setCorruptedNumber(controller.getCorruptedCount());

            window.listDuplicates(controller.getDuplicatesString());

            controller.insertRecordsToDatabaseThreads(threadCount);

        };

        WindowAdapter closeEvent = new WindowAdapter() {
            @Override
            public void windowClosing(java.awt.event.WindowEvent windowEvent) {
                controller.cleanUpDatabase();
                System.exit(0);
            }
        };

        window.initialize(buttonEvent, closeEvent);
        window.frame.setVisible(true);


    }
}