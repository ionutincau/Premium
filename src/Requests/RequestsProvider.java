package Requests;

import database.DatabaseConnection;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.text.SimpleDateFormat;
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
        ArrayList<Request> list = new ArrayList();
        try {
            String querry = "SELECT * FROM `requests`;";
            ResultSet result = DatabaseConnection.getStatement().executeQuery(querry);
            while (result.next()) {
                int id = result.getInt("id_request");
                int id_document=result.getInt("id_document");
                String status = result.getString("status");
                Date date_approval = result.getDate("date_approval");
                Calendar cal = new GregorianCalendar();
                cal.setTime(date_approval);
                Request request = new Request(id, id_document, status, cal);
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
        int id = 0;
        try{
            String querry = "SELECT MAX(`id_request`) FROM `requests`";
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

    public void insertRequest(Request r) {

        String date_approval="0000-00-00";
        String querry = "INSERT INTO `requests`(`id_request`,`id_document`,`status`,`date_approval`) VALUES (?,?,?,?);";
        SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
        if (r.getDate_approval()!=null)
        {
            date_approval = formatter.format(r.getDate_approval().getTime());
        }
        try {

            PreparedStatement pstmt = DatabaseConnection.getConnection().prepareStatement(querry);

            pstmt.setInt(1, r.getId());
            pstmt.setInt(2, r.getId_document());
            pstmt.setString(3, r.getStatus());
            pstmt.setString(4, date_approval);

            pstmt.executeUpdate();
            pstmt.close();
        }
        catch (Exception e) {
            System.out.println(e);
            e.printStackTrace();
        }
    }
    public ArrayList<String> getRequestDocTypeName()
    {
        ArrayList<String> list=new ArrayList<>();
        try {
            String querry = "SELECT `doctype_name` FROM `document_types`;";
            ResultSet result = DatabaseConnection.getStatement().executeQuery(querry);
            while (result.next()) {
                String docName = result.getString("doctype_name");
                list.add(0, docName);
            }
        }
        catch (Exception e) {
            System.out.println(e);
            e.printStackTrace();
        }
        return list;

    }

    public void updateRequest(Request r) {
        String querry = "UPDATE `requests` SET `id_document`= ?,`status`= ?,`date_approval`= ? WHERE `id_request`= ?;";
        SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
        String date_approval = formatter.format(r.getDate_approval().getTime());
        try {
            PreparedStatement pstmt = DatabaseConnection.getConnection().prepareStatement(querry);

            pstmt.setInt(1, r.getId_document());
            pstmt.setString(2, r.getStatus());
            pstmt.setString(3, date_approval);
            pstmt.setInt(4, r.getId());

            pstmt.executeUpdate();
            pstmt.close();
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
    public int getDocTypeIdbyName(String name)
    {
        int id=0;

        try {
            String querry = "SELECT `id_doctype` FROM `document_types` WHERE `doctype_name`='"+name+"';";
            ResultSet result = DatabaseConnection.getStatement().executeQuery(querry);
            if (result.next()) {
                id = result.getInt(1);
            }
        }
        catch (Exception e) {
            System.out.println(e);
            e.printStackTrace();
        }
        return id;
    }
    public ArrayList<Request> getRequestsList(int id_user)
    {
        ArrayList<Request> list = new ArrayList();
        try {
            String query ="SELECT requests.id_request,requests.id_document,requests.Status,requests.date_approval from requests " +
                    "inner join documents  on requests.id_document = documents.id_document " +
                    "inner join document_types on documents.id_doctype = document_types.id_doctype " +
                    "where documents.id_employee = "+id_user+";";
            ResultSet result = DatabaseConnection.getStatement().executeQuery(query);
            while (result.next()) {

                int id = result.getInt("id_request");
                int id_document=result.getInt("id_document");
                String status = result.getString("status");
                Date date_approval = result.getDate("date_approval");
                Calendar cal = new GregorianCalendar();
                cal.setTime(date_approval);

                Request request = new Request(id, id_document, status, cal);
                System.out.println(request);
                list.add(request);
            }
        }
        catch (Exception e) {
            System.out.println(e);
            e.printStackTrace();
        }
        return list;
    }

}
