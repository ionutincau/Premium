package database;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.Statement;

/**
 * Created by ionut on 09-Apr-17.
 */

public class DatabaseConnection {

    private static DatabaseConnection instance;
    private static Connection connection;

    private DatabaseConnection() {
        try {
            //connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/premium", "root", "");
            connection = DriverManager.getConnection("jdbc:mysql://sql11.freesqldatabase.com:3306/sql11164406", "sql11164406", "ytcWkGRh58");
        }
        catch (Exception e) {
            System.out.println("Database connection error");
            System.out.println("Check internet connection");
        }
    }

    public static Statement getStatement(){
        if (instance == null){
            instance = new DatabaseConnection();
        }
        Statement statement = null;
        try {
            statement = connection.createStatement();
        }
        catch (Exception e) {
            System.out.println("Statement error");
        }
        return statement;
    }
}
