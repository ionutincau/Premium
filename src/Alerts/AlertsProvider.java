package Alerts;

import Employees.Employee;
import database.DatabaseConnection;

import java.sql.*;
import java.text.SimpleDateFormat;
import java.util.*;

/**
 * Created by Incau Ionut on 03-Apr-17.
 * Contact: ionut.incau@gmail.com
 */

public class AlertsProvider {

    public AlertsProvider() {

    }

    /**
     * Scoate toate "notificarile"(nu alertele din tabelul `alerts`) ale unui employee
     *
     */
    public ArrayList getAlerts(int id_employee) {
        ArrayList<Alert> list = new ArrayList();
        try {
            String querry = "SELECT `alerts_employees`.`id_alert_employee`,`alerts`.`id_alert`,`alerts`.`text`,`alerts_employees`.`delivery_date`,`alerts`.`deadline`,`alerts_employees`.`status`  FROM `alerts` JOIN `alerts_employees` ON `alerts`.`id_alert` = `alerts_employees`.`id_alert` WHERE `alerts_employees`.`id_employee` =" + id_employee;
            ResultSet result = DatabaseConnection.getStatement().executeQuery(querry);
            while (result.next()) {
                int id_alert = result.getInt("id_alert");
                String text = result.getString("text");
                java.util.Date deadline = result.getDate("deadline");
                Calendar cal1 = new GregorianCalendar();
                cal1.setTime(deadline);
                java.util.Date delivary_date = result.getDate("delivery_date");
                Calendar cal = new GregorianCalendar();
                cal.setTime(delivary_date);
                String status = result.getString("status");

                Alert alert = new Alert(id_alert, id_employee, text, cal1, cal , status);
                list.add(0, alert);
            }
        }
        catch (Exception e) {
            System.out.println(e);
            e.printStackTrace();
        }
        return list;
    }

    public ArrayList getAllAlerts() {
        ArrayList<Alert> list = new ArrayList();
        try {
            String querry = "SELECT * FROM `alerts`";
            ResultSet result = DatabaseConnection.getStatement().executeQuery(querry);
            while (result.next()) {
                int id_alert = result.getInt("id_alert");
                String text = result.getString("text");
                java.util.Date deadline = result.getDate("deadline");
                Calendar cal = new GregorianCalendar();
                cal.setTime(deadline);
                Alert alert = new Alert(id_alert, 0, text, cal, null , null);
                list.add(0, alert);
            }
        }
        catch (Exception e) {
            System.out.println(e);
            e.printStackTrace();
        }
        return list;
    }

    public static int getAEAvaliableId(){
        int id = 0;
        try {
            String querry = "SELECT MAX(`id_alert_employee`) FROM `alerts_employees`";
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

    public static int getAvaliableId(){
        int id = 0;
        try {
            String querry = "SELECT MAX(`id_alert`) FROM `alerts`";
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

    /**
     * Insert notificare dupa id_employee
     *
     * Foloseste 2 queriuri :
     * querry1 : -face insert in tabelul alerts din a de tip alert
     * querry2 : -face insert in tabelul alerts_employees adaugand `id-ul alertei dupa conditia (`alerts`.`text`= a.getText() ) `
     *
     * Mai pe scurt adaugi o "notificare noua" facand insert in ambele tabele (`alerts`,`alerts_employees`)
     *
     */
    public void insertNotification(Alert a, int id_user){
        String querry1 = "INSERT INTO `alerts`(`id_alert`, `deadline`, `text`) VALUES (?,?,?);";
        String querry2 = "INSERT INTO `alerts_employees`(`id_alert_employee`,`id_employee`,`id_alert`,`delivery_date`,`status`) VALUES (?,?,?,?,?);";
        SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
        String deadline = formatter.format(a.getDeadline().getTime());
        String delivery_date = formatter.format(a.getDelivery_date().getTime());

        try {
            PreparedStatement pstmt = DatabaseConnection.getConnection().prepareStatement(querry1);
            pstmt.setInt(1, a.getId_alert());
            pstmt.setString(2, deadline);
            pstmt.setString(3, a.getText());
            pstmt.executeUpdate();
            pstmt.close();

            if (id_user == -1) {
                ArrayList ids = getEmployees();
                for (int i = 0; i < ids.size(); i++) {
                    PreparedStatement pstmt2 = DatabaseConnection.getConnection().prepareStatement(querry2);
                    pstmt2.setInt(1, getAEAvaliableId());
                    pstmt2.setInt(2, (int) ids.get(i));
                    pstmt2.setInt(3, a.getId_alert());
                    pstmt2.setString(4, delivery_date);
                    pstmt2.setString(5, a.getStatus());
                    pstmt2.executeUpdate();
                    pstmt2.close();
                }
            }
            else {
                PreparedStatement pstmt2 = DatabaseConnection.getConnection().prepareStatement(querry2);
                pstmt2.setInt(1, getAEAvaliableId());
                pstmt2.setInt(2, id_user);
                pstmt2.setInt(3, a.getId_alert());
                pstmt2.setString(4, delivery_date);
                pstmt2.setString(5, a.getStatus());
                pstmt2.executeUpdate();
                pstmt2.close();
            }
        }
        catch (Exception e) {
            System.out.println(e);
            e.printStackTrace();
        }
    }

    /**
     * Face update in ambele tabele dupa id-ul notificarii :D
     */
    public void updateNotification(Alert a){
        String querry1 = "UPDATE `alerts`,`alerts_employees` SET `alerts`.`deadline`= ?,`alerts`.`text`= ?,`alerts_employees`.`delivery_date`= ? ,`alerts_employees`.`status`=? WHERE `alerts_employees`.`id_alert_employee`=? AND `alerts_employees`.`id_alert`=`alerts`.`id_alert`;";
        SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
        String deadline = formatter.format(a.getDeadline().getTime());
        String delivery_date = formatter.format(a.getDelivery_date().getTime());

        try {
            PreparedStatement pstmt = DatabaseConnection.getConnection().prepareStatement(querry1);

            pstmt.setString(1, deadline);
            pstmt.setString(2, a.getText());
            pstmt.setString(3, delivery_date);
            pstmt.setString(4, a.getStatus());
            pstmt.setInt(5, a.getId_alert());

            pstmt.executeUpdate();
            pstmt.close();
        }
        catch (Exception e) {
            System.out.println(e);
            e.printStackTrace();
        }
    }

    public void deleteNotification(Alert a){
        try {
            String querry = "DELETE FROM `alerts` WHERE `alerts`.`id_alert`=" + a.getId_alert() + ";";
            DatabaseConnection.getStatement().executeUpdate(querry);
            String querry1 = "DELETE FROM `alerts_employees`  WHERE `alerts_employees`.`id_alert`=" + a.getId_alert() + ";";
            DatabaseConnection.getStatement().executeUpdate(querry1);
        }
        catch (Exception e) {
            System.out.println(e);
            e.printStackTrace();
        }
    }

    public ArrayList getEmployeeName() {
        ArrayList<String> listEmployeesName = new ArrayList<>();
        try {
            String querry = "SELECT * FROM `employees` ;";
            ResultSet result = DatabaseConnection.getStatement().executeQuery(querry);
            while (result.next()) {
                String last_name = result.getString("last_name");
                String first_name = result.getString("first_name");
                String name = last_name + " " + first_name;
                listEmployeesName.add(name);
            }
            result.close();
        }
        catch (Exception e) {
            System.out.println(e);
            e.printStackTrace();
        }
        return listEmployeesName;
    }

    public int id_user(String name) {
        String[] Name = name.split(" ");
        int id_user = 0;
        try {
            String querry = "SELECT id_employee FROM `employees` WHERE `last_name`='"+Name[0]+"' AND `first_name`='"+Name[1]+"' ;";
            ResultSet result = DatabaseConnection.getStatement().executeQuery(querry);
            while (result.next()) {
                id_user = result.getInt("id_employee");
            }
        }
        catch (Exception e) {
            System.out.println(e);
            e.printStackTrace();
        }
        return id_user;
    }

    private ArrayList getEmployees() {
        ArrayList list = new ArrayList();
        try {
            String querry = "SELECT `id_employee` FROM `employees` WHERE `role`='" + "user" + "'";
            ResultSet result = DatabaseConnection.getStatement().executeQuery(querry);
            while (result.next()) {
                int id_employee = result.getInt("id_employee");
                list.add(id_employee);
            }
            result.close();
        }
        catch (Exception e) {
            e.printStackTrace();
        }
        return list;
    }

    public static ArrayList unreadAlerts(int id_employee) {
        ArrayList list = new ArrayList();
        try {
            String querry = "SELECT `id_alert` FROM `alerts_employees` WHERE `id_employee`='" + id_employee + "'" + "AND `status`='" + "send" + "'";
            ResultSet result = DatabaseConnection.getStatement().executeQuery(querry);
            while (result.next()) {
                int id_alert = result.getInt("id_alert");
                list.add(id_alert);
            }
            result.close();
        }
        catch (Exception e) {
            e.printStackTrace();
        }
        return list;
    }

    public void setRead(int id_employee, int id_alert) {
        try {
            String querry1 = "UPDATE `alerts_employees` SET `status`='" + "read" + "' WHERE `alerts_employees`.`id_alert`=" + id_alert + " AND `alerts_employees`.`id_employee`=" + id_employee;
            DatabaseConnection.getStatement().executeUpdate(querry1);
        }
        catch (Exception e) {
            System.out.println(e);
            e.printStackTrace();
        }
    }
}
