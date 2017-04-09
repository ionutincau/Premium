package Employees;

import Departments.Department;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;

/**
 * Created by ASUS on 09.Apr.2017.
 */
public class EmployeesProvider {
    private Connection con;
    private Statement statement;
    private ResultSet result;

    public EmployeesProvider() {
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

    public ArrayList getEmployees() {
        ArrayList<Employee> list = new ArrayList<Employee>();
        try {
            String querry = "SELECT * FROM `employees` ;";
            result = statement.executeQuery(querry);
            while (result.next()) {
                int id_employee = result.getInt("id_employee");
                String last_name=result.getString("last_name");
                String first_name=result.getString("first_name");
                String username=result.getString("username");
                String password=result.getString("password");
                String cnp=result.getString("cnp");
                int id_job = result.getInt("id_job");
                int id_department = result.getInt("id_department");
                String email=result.getString("email");
                String phone=result.getString("phone");
                String role=result.getString("role");


                Employee employee = new Employee(id_employee,last_name,first_name,username,password,cnp,id_job,id_department,email,phone,role);
                list.add(0, employee);
            }
        }
        catch (Exception e) {
            System.out.println(e);
            e.printStackTrace();
        }
        return list;
    }
    public void insertEmployee(Employee em) {

        try {
            String querry = "INSERT INTO `employees`(`last_name`,`first_name`,`username`,`password`,`cnp`,`id_job`,`id_department`,`email`,`phone`,`role`) VALUES (" + em.getLast_name() + "," + em.getFirst_name() + ","+em.getUsername()+","+em.getPassword()+","+em.getCnp()+","+em.getId_job()+","+em.getId_departament()+","+em.getEmail()+","+em.getPhone()+","+em.getRole()+")";
            statement.executeUpdate(querry);
        } catch (Exception e) {
            System.out.println(e);
            e.printStackTrace();

        }

    }
    public void updateEmployee(Employee em) {
        try {
            String querry = "UPDATE `employees` SET `last_name`="+em.getLast_name()+",`first_name`="+em.getFirst_name()+",`username`="+em.getUsername()+",`password`="+em.getPassword()+",`cnp`="+em.getCnp()+",`id_job`="+em.getId_job()+",`id_department`="+em.getId_departament()+",`email`="+em.getEmail()+",`phone`="+em.getPhone()+",`role`="+em.getRole()+" WHERE `id_employee`="+em.getId()+";";
            statement.executeUpdate(querry);
        }
        catch(Exception e){
            System.out.println(e);
            e.printStackTrace();

        }
    }
    public void deleteEmployee(Employee em){
        try {
            String querry = "DELETE FROM `employees` WHERE `id_employee`="+em.getId()+";";
            statement.executeUpdate(querry);
        }
        catch(Exception e){
            System.out.println(e);
            e.printStackTrace();

        }
    }
}
