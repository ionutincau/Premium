package Login;

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
            System.out.println(e);
            e.printStackTrace();
        }
    }

    public void Login(String username, String password)
    {
        try
        {
            String querry = "SELECT * FROM `employees` WHERE `username`='" + username + "' AND `password`='" + password + "'";
            result = statement.executeQuery(querry);

            if (result.next())
            {
                if (result.getInt("id_job") == 1 )
                {
                    // admin? logged in
                    authenticated = true;

                }
                else if (result.getInt("id_job") == 2)
                {
                    // hr? logged in
                    authenticated = true;

                }
                else if (result.getInt("id_job") == 3)
                {
                    // user? logged in
                    authenticated = true;

                }
                else
                {
                    // invalid username/password
                }
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

}
