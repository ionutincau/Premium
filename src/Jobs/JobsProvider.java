package Jobs;

import Departments.Department;
import database.DatabaseConnection;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;

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

    public static int getAvaliableId(){
        int id=0;
        try{
            String querry = "SELECT MAX(`id_job`) FROM `jobs`";
            ResultSet result = DatabaseConnection.getStatement().executeQuery(querry);
            id=result.getInt("id_job");
        }
        catch (Exception e) {
            System.out.println(e);
            e.printStackTrace();
        }
        return (id+1);
    }

    public void insertJob(Job j) {

        try {
            String querry = "INSERT INTO `jobs`(`name`,`min_salary`,`number`,`id_document`) VALUES (" + j.getName() + "," + j.getMin_salary() + "," + j.getNumber() + "," + j.getId_document()+")";
            DatabaseConnection.getStatement().executeUpdate(querry);
        } catch (Exception e) {
            System.out.println(e);
            e.printStackTrace();

        }
    }

    public void updateJob(Job j) {
        try {
            String querry = "UPDATE `jobs` SET `name`="+j.getName()+",`min_salary`="+j.getMin_salary()+",`number`="+j.getNumber()+",`id_document`="+j.getId_document()+" WHERE `id_job`="+j.getId()+";";
            DatabaseConnection.getStatement().executeUpdate(querry);
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
}
