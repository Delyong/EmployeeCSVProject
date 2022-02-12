package com.sparta.employeecsv;

import com.sparta.employeecsv.controller.CSVController;
import com.sparta.employeecsv.view.DisplayManager;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.awt.event.ActionListener;
import java.util.Scanner;
import java.awt.event.WindowAdapter;

public class CSVMain {

    public static Logger logger = LogManager.getLogger("CSV-Logger");

    /**
     * Launch the application.
     */
    public static void main(String[] args) {

        CSVController controller = new CSVController();
        logger.debug("Successfully created controller");
        DisplayManager displayManager = new DisplayManager();
        logger.debug("Successfully created view");

        controller.setupDatabase();

        String threadCountStr = displayManager.getThreadCount();
        boolean isValidThreadCount = controller.checkThreadCount(threadCountStr);

        while (!isValidThreadCount) {
            logger.warn("Invalid thread count input retrying");
            displayManager.displayInvalidThreadMsg();
            threadCountStr = displayManager.getThreadCount();
            isValidThreadCount = controller.checkThreadCount(threadCountStr);
        }

        int threadCount = controller.parseThreadCount(threadCountStr);

        // truncate database before click
        ActionListener buttonEvent = e -> {

            String filename = displayManager.getFilename();

            long readStartTime = System.nanoTime();
            controller.readFile(filename);
            displayManager.displayReadingTime(readStartTime, System.nanoTime());

            displayManager.setDuplicateNumber(controller.getDuplicateCount());
            displayManager.setUniqueNumber(controller.getUniqueCount());
            displayManager.setCorruptedNumber(controller.getCorruptedCount());

            displayManager.listDuplicates(controller.getDuplicatesString());

            long writeStartTime = System.nanoTime();
            long writeEndTime = controller.insertRecordsToDatabaseThreads(threadCount);
            displayManager.displayWritingTime(writeStartTime, writeEndTime);


            // Put into a method
            Scanner sc = new Scanner(System.in);
            System.out.print("\nSelect all employees?(ALL) or select a single employee?(SINGLE): ");
            String allEmployeeOrSingle = sc.next();
            if (allEmployeeOrSingle.equals("ALL"))
                System.out.println(controller.getEmployees());
            else if (allEmployeeOrSingle.equals("SINGLE")) {
                System.out.print("Enter id: ");
                int empID = sc.nextInt();
                System.out.println(controller.getEmployeeById(empID));
                ;
            }
        };

        WindowAdapter closeEvent = new WindowAdapter() {
            @Override
            public void windowClosing(java.awt.event.WindowEvent windowEvent) {
                controller.cleanUpDatabase();
                System.exit(0);
            }
        };

        displayManager.initialize(buttonEvent, closeEvent);
        logger.debug("JFrame was initialized");
        displayManager.frame.setVisible(true);
    }
}