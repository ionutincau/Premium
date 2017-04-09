package Departments;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;

/**
 * Created by ASUS on 09.Apr.2017.
 */
public class DepartmentsProvider {
    private Connection con;
    private Statement statement;
    private ResultSet result;

    public DepartmentsProvider() {
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

    public ArrayList getDepartments() {
        ArrayList<Department> list = new ArrayList<Department>();
        try {
            String querry = "SELECT * FROM `departments` ;";
            result = statement.executeQuery(querry);
            while (result.next()) {
                int id_department = result.getInt("id_department");
                String name=result.getString("name");
                int id_manager = result.getInt("id_manager");

                Department department = new Department(id_department,name,id_manager);
                list.add(0, department);
            }
        }
        catch (Exception e) {
            System.out.println(e);
            e.printStackTrace();
        }
        return list;
    }

    public void insertDepartment(Department d) {

        try {
            String querry = "INSERT INTO `departments`(`name`,`id_manager`) VALUES (" + d.getName() + "," + d.getId_manager() + ")";
            statement.executeUpdate(querry);
        } catch (Exception e) {
            System.out.println(e);
            e.printStackTrace();

        }

    }
    public void updateDepartment(Department d) {
        try {
            String querry = "UPDATE `departments` SET `name`="+d.getName()+",`id_manager`="+d.getId_manager()+" WHERE `id_department`="+d.getId()+";";
            statement.executeUpdate(querry);
        }
        catch(Exception e){
            System.out.println(e);
            e.printStackTrace();

        }
    }
    public void deleteDepartment(Department d){
        try {
            String querry = "DELETE FROM `departments` WHERE `id_departmenr`="+d.getId()+";";
            statement.executeUpdate(querry);
        }
        catch(Exception e){
            System.out.println(e);
            e.printStackTrace();

        }
    }
}
