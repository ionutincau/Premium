package Clocking;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;

/**
 * Created by Incau Ionut on 14-Mar-17.
 * Contact: ionut.incau@gmail.com
 */

public class ClockingProvider {

    private Connection con;
    private Statement statement;
    private ResultSet result;

    public ClockingProvider() {
        connect();
    }

    public void connect() {
        try {
            Class.forName("com.mysql.jdbc.Driver");
            //con = DriverManager.getConnection("jdbc:mysql://localhost:3306/premium", "root", "");
            con = DriverManager.getConnection("jdbc:mysql://sql11.freesqldatabase.com:3306/sql11164406", "sql11164406", "ytcWkGRh58");
            statement = con.createStatement();
        }
        catch (Exception e) {
            System.out.println("Database connection error");
            System.out.println("Check internet connection");
        }
    }

    public ArrayList getClockings(int id_employee) {
        ArrayList<Clocking> list = new ArrayList<Clocking>();
        try {
            String querry = "SELECT * FROM `clockings` WHERE `id_employee`=" + id_employee;
            result = statement.executeQuery(querry);
            while (result.next()) {
                int id = result.getInt("id_clocking");
                Date date = result.getDate("date");
                Calendar cal = new GregorianCalendar();
                cal.setTime(date);
                int hour_in = result.getInt("hour_in");
                int hour_break = result.getInt("hour_break");
                int hour_work = result.getInt("hour_work");
                int hour_out = result.getInt("hour_out");
                Clocking clocking = new Clocking(id, cal, hour_in, hour_break, hour_work, hour_out);
                list.add(0, clocking);
            }
        }
        catch (Exception e) {
            System.out.println(e);
            e.printStackTrace();
        }
        return list;
    }
    public void insertClocking(Clocking c,int id_employee) {

        try {
            String querry = "INSERT INTO `clockings`(`id_employee`,`date`,`hour_in`,`hour_out`,`hour_break`,`hour_work`) VALUES (" + id_employee + "," + c.get_date() + "," + c.get_hour_in() + "," + c.get_hour_out() + "," + c.get_hour_break() + "," + c.get_hour_work() + ")";
            statement.executeUpdate(querry);
        } catch (Exception e) {
            System.out.println(e);
            e.printStackTrace();

        }
    }


    public void updateClocking(Clocking c,int id_employee)
    {

        try {
            String querry = "UPDATE `clockings` SET `date`="+c.get_date()+",`hour_in`="+c.get_hour_in()+",`hour_out`="+c.get_hour_out()+",`hour_break`="+c.get_hour_break()+",`hour_work`="+c.get_hour_work()+" WHERE `id_employee`="+id_employee+";";
            statement.executeUpdate(querry);
        }
        catch(Exception e){
            System.out.println(e);
            e.printStackTrace();

        }
    }


    public void deleteClocking (Clocking c)
    {

        try {
            String querry = "DELETE FROM `clockings` WHERE `id_clocking`="+c.getId()+";";
            statement.executeUpdate(querry);
        }
        catch(Exception e){
            System.out.println(e);
            e.printStackTrace();

        }
    }

}