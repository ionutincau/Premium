package Login;

import javafx.scene.control.Alert;

import java.sql.*;

/**
 * Created by Incau Ionut on 14-Mar-17.
 * Contact: ionut.incau@gmail.com
 */

public class LoginController {

    private Connection connection;
    private Statement statement;
    private ResultSet result;

    private boolean authenticated;
    private int jobId;
    private int departamentId;
    private String userType;

    public LoginController() {
        connect();

    }

    public void connect() {
        try {
            Class.forName("com.mysql.jdbc.Driver");
            connection = DriverManager.getConnection("jdbc:mysql://sql11.freesqldatabase.com:3306/sql11164406", "sql11164406", "ytcWkGRh58");
            statement = connection.createStatement();
        }
        catch (Exception e) {
            System.out.println("Connection with database failed");
            System.out.println("Check internet connection!");
        }
    }

    public void Login(String username, String password)
    {
        try
        {
            String querry = "SELECT * FROM `employees` WHERE `username`='" + username + "' AND `password`='" + password + "'";
            result = statement.executeQuery(querry);

            // TODO get all employee details from query result
            if (result.next())
            {
                if (result.getString("role").toLowerCase().equals("admin") )
                {
                    authenticated = true;
                    this.userType = "admin";

                }
                else if (result.getString("role").toLowerCase().equals("hr") )
                {
                    authenticated = true;
                    this.userType = "hr";


                }
                else if (result.getString("role").toLowerCase().equals("user") )
                {
                    authenticated = true;
                    this.userType = "user";


                }
            }
            else
            {
                // invalid username/password
                Alert alert = new Alert(Alert.AlertType.ERROR);
                alert.setTitle("Eroare");
                alert.setHeaderText("Cont invalid!");
                alert.setContentText("Username/parola gresite!");
                alert.show();
            }
        } catch (Exception e) {
            System.out.println(e);
            e.printStackTrace();
        }


    }

    public boolean isAuthenticated()
    {
        return authenticated;
    }
    public String getUserType()
    {
        return this.userType;
    }

}
