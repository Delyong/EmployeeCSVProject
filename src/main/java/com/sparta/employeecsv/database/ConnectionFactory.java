package com.sparta.employeecsv.database;

import java.io.FileReader;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;

import static com.sparta.employeecsv.CSVMain.logger;

public class ConnectionFactory {

    private static Connection connection = null;

    private ConnectionFactory() {}

    public static Connection getConnection() throws IOException, SQLException {

        if (connection == null) {

            logger.debug("Database connection was null attempting to create one");

            Properties props = new Properties();
            props.load(new FileReader("mysql.properties"));

            connection = DriverManager.getConnection(
                    props.getProperty("dburl"),
                    props.getProperty("dbuserid"),
                    props.getProperty("dbpassword")
            );

        }

        logger.info("Getting database connection");

        return connection;

    }

    public static void closeConnection() throws SQLException {
        if (connection != null) {
            connection.close();
            logger.debug("Closed database connection");
        }
    }

}
