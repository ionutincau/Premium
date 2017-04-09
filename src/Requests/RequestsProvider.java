package Requests;

import Clocking.Clocking;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;

/**
 * Created by Incau Ionut on 03-Apr-17.
 * Contact: ionut.incau@gmail.com
 */

public class RequestsProvider {
    private Connection con;
    private Statement statement;
    private ResultSet result;

    public RequestsProvider() {
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

    public ArrayList getRequests() {
        ArrayList<Request> list = new ArrayList<Request>();
        try {
            String querry = "SELECT * FROM `requests`;";
            result = statement.executeQuery(querry);
            while (result.next()) {
                int id = result.getInt("id_request");
                int id_document=result.getInt("id_document");
                String status = result.getString("status");
                Date deadline = result.getDate("deadline");
                Calendar cal = new GregorianCalendar();
                cal.setTime(deadline);
                Date date_approval = result.getDate("date_approval");
                Calendar cal1 = new GregorianCalendar();
                cal.setTime(date_approval);
                Request request = new Request(id,id_document,status, cal,cal1);
                list.add(0, request);
            }
        }
        catch (Exception e) {
            System.out.println(e);
            e.printStackTrace();
        }
        return list;
    }

    public void insertRequest(Request r) {

        try {
            String querry = "INSERT INTO `requests`(`id_document`,`status`,`deadline`,`date_approval`) VALUES (" +r.getId_document()+ "," +r.getStatus()+ ","+r.getDeadline()+","+r.getDate_approval()+");";
            statement.executeUpdate(querry);
        } catch (Exception e) {
            System.out.println(e);
            e.printStackTrace();
        }
    }

    public void updateRequest(Request r) {
        try {
            String querry = "UPDATE `requests` SET `id_document`="+r.getId_document()+",`status`="+r.getStatus()+",`deadline`="+r.getDeadline()+",`date_approval`="+r.getDate_approval()+" WHERE `id_request`="+r.getId()+";";
            statement.executeUpdate(querry);
        }
        catch(Exception e){
            System.out.println(e);
            e.printStackTrace();

        }
    }
    public void deleteRequest(Request r){
        try {
            String querry = "DELETE FROM `requests` WHERE `id_request`="+r.getId()+";";
            statement.executeUpdate(querry);
        }
        catch(Exception e){
            System.out.println(e);
            e.printStackTrace();

        }
    }
}
