package Requests;

import Clocking.Clocking;
import database.DatabaseConnection;


import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;

/**
 * Created by Incau Ionut on 03-Apr-17.
 * Contact: ionut.incau@gmail.com
 */

public class RequestsProvider {
    
    public RequestsProvider() {

    }



    public ArrayList getRequests() {
        ArrayList<Request> list = new ArrayList<Request>();
        try {
            String querry = "SELECT * FROM `requests`;";
            ResultSet result = DatabaseConnection.getStatement().executeQuery(querry);
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

    public static int getAvaliableId(){
        int id=0;
        try{
            String querry = "SELECT MAX(`id_request`) FROM `requests`";
            ResultSet result = DatabaseConnection.getStatement().executeQuery(querry);
            id=result.getInt("id_requests");
        }
        catch (Exception e) {
            System.out.println(e);
            e.printStackTrace();
        }
        return (id+1);
    }


    public void insertRequest(Request r) {

        try {
            String querry = "INSERT INTO `requests`(`id_document`,`status`,`deadline`,`date_approval`) VALUES (" +r.getId_document()+ "," +r.getStatus()+ ","+r.getDeadline()+","+r.getDate_approval()+");";
            DatabaseConnection.getStatement().executeUpdate(querry);
        } catch (Exception e) {
            System.out.println(e);
            e.printStackTrace();
        }
    }

    public void updateRequest(Request r) {
        try {
            String querry = "UPDATE `requests` SET `id_document`="+r.getId_document()+",`status`="+r.getStatus()+",`deadline`="+r.getDeadline()+",`date_approval`="+r.getDate_approval()+" WHERE `id_request`="+r.getId()+";";
            DatabaseConnection.getStatement().executeUpdate(querry);
        }
        catch(Exception e){
            System.out.println(e);
            e.printStackTrace();

        }
    }
    public void deleteRequest(Request r){
        try {
            String querry = "DELETE FROM `requests` WHERE `id_request`="+r.getId()+";";
            DatabaseConnection.getStatement().executeUpdate(querry);
        }
        catch(Exception e){
            System.out.println(e);
            e.printStackTrace();

        }
    }
}
