package Alerts;

import Clocking.Clocking;

import java.sql.*;
import java.util.*;

/**
 * Created by Incau Ionut on 03-Apr-17.
 * Contact: ionut.incau@gmail.com
 */

public class AlertsProvider {
    private Connection con;
    private Statement statement;
    private ResultSet result;

    public AlertsProvider() {
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
    /**
     * Scoate toate "notificarile"(nu alertele din tabelul `alerts`) ale unui employee
     *
     */
    public ArrayList getAlerts(int id_employee) {
        ArrayList<Alert> list = new ArrayList<Alert>();
        try {
            String querry = "SELECT `alerts`.`id_alert`,`alerts`.`text`,`alerts_employees`.`delivery_date`,`alerts`.`deadline`,`alerts_employees`.`status`  FROM `alerts` JOIN `alerts_employees` ON `alerts`.`id_alert` = `alerts_employees`.`id_alert` WHERE`alerts_employees`.`id_employee` ="+id_employee;
            result = statement.executeQuery(querry);
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

                Alert alert = new Alert(id_alert,text,cal,cal1,status);
                list.add(0,alert );
            }
        }
        catch (Exception e) {
            System.out.println(e);
            e.printStackTrace();
        }
        return list;
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

    public void insertNotification(Alert a,int id_employee){
        try {
            String querry1 = "INSERT INTO `alerts`(`deadline`, `text`) VALUES (" + a.getDeadline() + "," + a.getText() + ";";
            statement.executeUpdate(querry1);
            String querry2 = "INSERT INTO `alerts_employees`(`id_employee`,`id_alert`,`delivery_date`,`status`) VALUES ("+id_employee+",(SELECT `id_alert` FROM `alerts` WHERE `alerts`.`text`="+a.getText()+"),"+a.getDelivery_date()+","+a.getStatus()+");";
            statement.executeUpdate(querry2);
        } catch (Exception e) {
            System.out.println(e);
            e.printStackTrace();
        }
    }

    /**
     * Face update in ambele tabele dupa id-ul notificarii :D
     *
     */
    public void updateNotification(Alert a){
        try {
            String querry1 = "UPDATE `alerts`,`alerts_employees` SET `alerts`.`deadline`="+a.getDeadline()+",`alerts`.`text`="+a.getText()+",`alerts_employees`.`delivery_date`="+a.getDelivery_date()+",`alerts_employees`.`status`="+a.getStatus()+" WHERE `alerts_employees`.`id_alert_employee`="+a.getId_alert()+" AND `alerts_employees`.`id_alert`=`alerts`.`id_alert`;";
            statement.executeUpdate(querry1);

        } catch (Exception e) {
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
            statement.executeUpdate(querry1);

        } catch (Exception e) {
            System.out.println(e);
            e.printStackTrace();
        }
    }
}
