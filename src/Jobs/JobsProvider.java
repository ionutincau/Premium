package Jobs;

import Departments.Department;
import database.DatabaseConnection;

import java.sql.*;

import java.util.ArrayList;

/**
 * Created by ASUS on 09.Apr.2017.
 */
public class JobsProvider {


    public JobsProvider() {

    }

    public Job getJob(int id) {
        Job job = null;
        try {
            String querry = "SELECT * FROM `jobs` WHERE `id_job`='" + id + "'";
            ResultSet result = DatabaseConnection.getStatement().executeQuery(querry);
            if (result.next()) {
                int id_job = result.getInt("id_job");
                String name=result.getString("name");
                String min_salary=result.getString("min_salary");
                String number=result.getString("number");
                int id_document = result.getInt("id_document");
                job = new Job(id_job, name, min_salary, number, id_document);
            }
        }
        catch (Exception e) {
            e.printStackTrace();
        }
        return job;
    }

    public ArrayList getJobs() {
        ArrayList<Job> list = new ArrayList<Job>();
        try {
            String querry = "SELECT * FROM `jobs` ;";
            ResultSet result = DatabaseConnection.getStatement().executeQuery(querry);
            while (result.next()) {
                int id_job = result.getInt("id_job");
                String name=result.getString("name");
                String min_salary=result.getString("min_salary");
                String number=result.getString("number");
                int id_document = result.getInt("id_document");

                Job job = new Job(id_job,name,min_salary,number,id_document);
                list.add(0,job);
            }
        }
        catch (Exception e) {
            System.out.println(e);
            e.printStackTrace();
        }
        return list;
    }

//    public static int getAvaliableId(){
//        int id=0;
//        try{
//            String querry = "SELECT MAX(`id_job`) FROM `jobs`";
//            ResultSet result = DatabaseConnection.getStatement().executeQuery(querry);
//            id=result.getInt("id_job");
//        }
//        catch (Exception e) {
//            System.out.println(e);
//            e.printStackTrace();
//        }
//        return (id+1);
//    }

    public void insertJob(Job j) {

        try {
            String querry = "INSERT INTO jobs(id_job,name,min_salary,number,id_document) VALUES (?,?,?,?,?)";
            PreparedStatement pstmt = DatabaseConnection.getConnection().prepareStatement(querry);

            pstmt.setInt(1, j.getId());
            pstmt.setString(2, j.getName());
            pstmt.setString(3, j.getMin_salary());
            pstmt.setString(4, j.getNumber());
            pstmt.setInt(5, j.getId_document());
            pstmt.executeUpdate();
            pstmt.close();
        } catch (Exception e) {
            System.out.println(e);
            e.printStackTrace();

        }
    }

    public void updateJob(Job j) {
        try {
            String querry = "UPDATE jobs SET name=?,min_salary=?,number=?,id_document=? WHERE id_job=? ;";
            PreparedStatement pstmt = DatabaseConnection.getConnection().prepareStatement(querry);
            pstmt.setString(1, j.getName());
            pstmt.setString(2, j.getMin_salary());
            pstmt.setString(3, j.getNumber());
            pstmt.setInt(4, j.getId_document());
            pstmt.setInt(5, j.getId());
            pstmt.executeUpdate();
            pstmt.close();
        }
        catch(Exception e){
            System.out.println(e);
            e.printStackTrace();

        }
    }
    public void deleteJob(Job j){
        try {
            String querry = "DELETE FROM `jobs` WHERE `id_job`="+j.getId()+";";
            DatabaseConnection.getStatement().executeUpdate(querry);
        }
        catch(Exception e){
            System.out.println(e);
            e.printStackTrace();

        }
    }
    public  int numberOfEmployeePerJob(int  id_job)
    {
        int nr_employee=0;
        try {
            String querry = "SELECT COUNT(*) FROM `employees` WHERE `id_job`='"+id_job+"'";
            ResultSet result = DatabaseConnection.getStatement().executeQuery(querry);
            result.next();
            nr_employee=result.getInt(1);
            result.close();
        }
        catch (SQLException e)
        {
            e.printStackTrace();
        }
        return nr_employee;
    }
}
