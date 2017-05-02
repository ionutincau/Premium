package Alerts;

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
        ArrayList<Alert> list = new ArrayList<Alert>();
        try {
            String querry = "SELECT `alerts_employees`.`id_alert_employee`,`alerts`.`id_alert`,`alerts`.`text`,`alerts_employees`.`delivery_date`,`alerts`.`deadline`,`alerts_employees`.`status`  FROM `alerts` JOIN `alerts_employees` ON `alerts`.`id_alert` = `alerts_employees`.`id_alert` WHERE`alerts_employees`.`id_employee` ="+id_employee;
            ResultSet result = DatabaseConnection.getStatement().executeQuery(querry);
            while (result.next()) {
                int id_alert = result.getInt("id_alert");
                String text = result.getString("text");
                java.util.Date delivary_date = result.getDate("delivery_date");
                Calendar cal = new GregorianCalendar();
                cal.setTime(delivary_date);
                java.util.Date deadline = result.getDate("deadline");
                Calendar cal1 = new GregorianCalendar();
                cal.setTime(deadline);
                String status = result.getString("status");

                Alert alert = new Alert(id_alert, id_employee, text, cal, cal1, status);
                list.add(0, alert);
            }
        }
        catch (Exception e) {
            System.out.println(e);
            e.printStackTrace();
        }
        return list;
    }

    public static int getAvaliableId(){
        int id=0;
        try{
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
        return (id+1);
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
    public void insertNotification(Alert a){
        String querry1 = "INSERT INTO `alerts`(`deadline`, `text`) VALUES (?,?);";
        String querry2 = "INSERT INTO `alerts_employees`(`id_alert_employee`,`id_employee`,`id_alert`,`delivery_date`,`status`) VALUES (?,?,(SELECT `id_alert` FROM `alerts` WHERE `alerts`.`text`= ? ),?,?);";
        SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
        String deadline = formatter.format(a.getDeadline().getTime());
        String delivery_date = formatter.format(a.getDelivery_date().getTime());

        try {
            PreparedStatement pstmt = DatabaseConnection.getConnection().prepareStatement(querry1);

            pstmt.setString(1, deadline);
            pstmt.setString(2, a.getText());

            pstmt.executeUpdate();
            pstmt.close();

            PreparedStatement pstmt2 = DatabaseConnection.getConnection().prepareStatement(querry2);

            pstmt2.setInt(1, a.getId_alert());
            pstmt2.setInt(2, a.getId_employee());
            pstmt2.setString(3, a.getText());
            pstmt2.setString(4, delivery_date);
            pstmt2.setString(5, a.getStatus());

            pstmt2.executeUpdate();
            pstmt2.close();

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

    /**
     * Sterge doar din tabelul `alerts_employees` pentru ca dupa tabelul respectiv se afiseaza notificarile
     * Din tabelul `alerts` nu se sterg iteme (doar forcefully din DB daca e nevoie) ,dar acest factor nu schimba cu nimic Notificarile ca total :D
     *
     */
    public void deleteNotification(Alert a){
        try {
            String querry1 = "DELETE FROM `alerts_employees`  WHERE `alerts_employees`.`id_alert_employee`="+a.getId_alert()+";";
            DatabaseConnection.getStatement().executeUpdate(querry1);
        }
        catch (Exception e) {
            System.out.println(e);
            e.printStackTrace();
        }
    }
}
