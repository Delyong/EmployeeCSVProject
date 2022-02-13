package com.sparta.employeecsv.database;

import java.io.FileReader;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;

import static com.sparta.employeecsv.CSVMain.logger;

/**
 * A class that is in charge of creating and closing the connection between the running program
 * and the database
 */
public class ConnectionFactory {

    private static Connection connection = null;

    /**
     * default constructor
     */
    private ConnectionFactory() {}

    /**
     * Initialises the connection
     *
     * @return the database connection to this program
     * @throws IOException - Exceptions for reading the mysql.properties
     * @throws SQLException - Exceptions for issues on getting the database connection
     */
    public static Connection getConnection() throws IOException, SQLException {

        if (connection == null) {

            logger.debug("Database connection was null attempting to create one");

            Properties props = new Properties();
            // loads the details of the database
            props.load(new FileReader("mysql.properties"));

            // Initialises the connection to the link provided in the properties file
            connection = DriverManager.getConnection(
                    props.getProperty("dburl"),
                    props.getProperty("dbuserid"),
                    props.getProperty("dbpassword")
            );

        }
        logger.info("Getting database connection");
        return connection;
    }

    /**
     * Closes the connection between the running program and the database
     *
     * @throws SQLException - Exceptions for issues on closing the database connection
     */
    public static void closeConnection() throws SQLException {
        if (connection != null) {
            connection.close();
            logger.debug("Closed database connection");
        }
    }
}
