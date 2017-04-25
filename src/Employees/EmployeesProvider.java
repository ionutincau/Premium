package Employees;

import database.DatabaseConnection;

import java.sql.ResultSet;
import java.util.ArrayList;

/**
 * Created by ASUS on 09.Apr.2017.
 */

public class EmployeesProvider {

    public EmployeesProvider() {

    }

    public ArrayList getEmployees() {
        ArrayList<Employee> list = new ArrayList<Employee>();
        try {
            String querry = "SELECT * FROM `employees` ;";
            ResultSet result = DatabaseConnection.getStatement().executeQuery(querry);
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
            e.printStackTrace();
        }
        return list;
    }

    public Employee getUser(String username) {
        Employee user = null;
        try {
            String query = "SELECT * FROM `employees` WHERE `username`='" + username + "'";
            ResultSet result = DatabaseConnection.getStatement().executeQuery(query);
            result.next();

            int id_employee = result.getInt("id_employee");
            String last_name = result.getString("last_name");
            String first_name = result.getString("first_name");
            String password = result.getString("password");
            String cnp = result.getString("cnp");
            int id_job = result.getInt("id_job");
            int id_department = result.getInt("id_department");
            String email = result.getString("email");
            String phone = result.getString("phone");
            String role = result.getString("role");

            user = new Employee(id_employee,last_name,first_name,username,password,cnp,id_job,id_department,email,phone,role);
        }
        catch (Exception e) {
            e.printStackTrace();
        }
        return user;
    }

    public static int getAvaliableId(){
        int id=0;
        try{
            String querry = "SELECT MAX(`id_employee`) FROM `employees`";
            ResultSet result = DatabaseConnection.getStatement().executeQuery(querry);
            id=result.getInt("id_employee");
        }
        catch (Exception e) {
            System.out.println(e);
            e.printStackTrace();
        }
        return (id+1);
    }

    public void insertEmployee(Employee em) {
        try {
            String querry = "INSERT INTO `employees`(`last_name`,`first_name`,`username`,`password`,`cnp`,`id_job`,`id_department`,`email`,`phone`,`role`) VALUES (" + em.getLast_name() + "," + em.getFirst_name() + ","+em.getUsername()+","+em.getPassword()+","+em.getCnp()+","+em.getId_job()+","+em.getId_department()+","+em.getEmail()+","+em.getPhone()+","+em.getRole()+")";
            DatabaseConnection.getStatement().executeUpdate(querry);
        }
        catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void updateEmployee(Employee em) {
        try {
            String querry = "UPDATE `employees` SET `last_name`="+em.getLast_name()+",`first_name`="+em.getFirst_name()+",`username`="+em.getUsername()+",`password`="+em.getPassword()+",`cnp`="+em.getCnp()+",`id_job`="+em.getId_job()+",`id_department`="+em.getId_department()+",`email`="+em.getEmail()+",`phone`="+em.getPhone()+",`role`="+em.getRole()+" WHERE `id_employee`="+em.getId()+";";
            DatabaseConnection.getStatement().executeUpdate(querry);
        }
        catch(Exception e){
            e.printStackTrace();
        }
    }

    public void deleteEmployee(Employee em) {
        try {
            String querry = "DELETE FROM `employees` WHERE `id_employee`="+em.getId()+";";
            DatabaseConnection.getStatement().executeUpdate(querry);
        }
        catch(Exception e) {
            e.printStackTrace();
        }
    }
}
