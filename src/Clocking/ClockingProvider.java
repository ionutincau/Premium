package Clocking;

import database.DatabaseConnection;

import java.sql.*;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;

/**
 * Created by Incau Ionut on 14-Mar-17.
 * Contact: ionut.incau@gmail.com
 */

public class ClockingProvider {

    public ClockingProvider() {

    }

    public ArrayList getClockings(int id_employee) {
        ArrayList<Clocking> list = new ArrayList<Clocking>();
        try {
            String querry = "SELECT * FROM `clockings` WHERE `id_employee`=" + id_employee;
            ResultSet result = DatabaseConnection.getStatement().executeQuery(querry);
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

    public int getAvaliableId() {
        int id = 0;
        try {
            String querry = "SELECT MAX(`id_clocking`) FROM `clockings`";
            ResultSet result = DatabaseConnection.getStatement().executeQuery(querry);
            if (result.next()) {
                id = result.getInt(1);
            }
        }
        catch (Exception e) {
            System.out.println(e);
            e.printStackTrace();
        }
        return (id + 1);
    }

    public int getIDByName(String lastname,String firstname) {
        try {
            String querry = "SELECT * FROM `employees` WHERE `last_name`="+"'"+lastname+"'"+" AND `first_name`="+"'" + firstname+"'";
            ResultSet result = DatabaseConnection.getStatement().executeQuery(querry);
            result.next();
            int id = result.getInt("id_employee");
            return id;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return 0;
    }


    public void insertClocking(Clocking c, int id_employee) {
        SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
        String date = formatter.format(c.get_date().getTime());

        String querry = "INSERT INTO clockings(id_employee, date, hour_in, hour_out, hour_break, hour_work) VALUES (?, ?, ?, ?, ?, ?)";

        try {
            PreparedStatement pstmt = DatabaseConnection.getConnection().prepareStatement(querry);

            pstmt.setInt(1, id_employee);
            pstmt.setString(2, date);
            pstmt.setInt(3, c.get_hour_in());
            pstmt.setInt(4, c.get_hour_out());
            pstmt.setInt(5, c.get_hour_break());
            pstmt.setInt(6, c.get_hour_work());

            pstmt.executeUpdate();
            pstmt.close();
        }
        catch (SQLException e) {
            e.printStackTrace();
        }
    }


    public void updateClocking(Clocking c, int id_employee) {
        SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd"); // your template here
        String date=formatter.format(c.get_date().getTime());

        String querry = "UPDATE clockings SET id_employee = ? ,date = ? ,hour_in = ?, hour_out = ?, hour_break = ?, hour_work = ? WHERE id_clocking=?";

        try {
            PreparedStatement pstmt = DatabaseConnection.getConnection().prepareStatement(querry);

            pstmt.setInt(1, id_employee);
            pstmt.setString(2, date);
            pstmt.setInt(3, c.get_hour_in());
            pstmt.setInt(4, c.get_hour_out());
            pstmt.setInt(5, c.get_hour_break());
            pstmt.setInt(6, c.get_hour_work());
            pstmt.setInt(7, c.getId());
            pstmt.executeUpdate();
            pstmt.close();
        }
        catch (SQLException e) {
            e.printStackTrace();
        }
    }


    public void deleteClocking (Clocking c) {
        try {
            String querry = "DELETE FROM `clockings` WHERE `id_clocking`="+c.getId()+";";
            DatabaseConnection.getStatement().executeUpdate(querry);
        }
        catch (Exception e){
            System.out.println(e);
            e.printStackTrace();
        }
    }

}