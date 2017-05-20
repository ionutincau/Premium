package Employees;

import database.DatabaseConnection;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
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
            result.close();
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
    public int  idSelectedJob(String jobName) {
        int id_job=0;
        try {
            String query = "SELECT * FROM `jobs` WHERE `name`='" + jobName + "'";
            ResultSet result = DatabaseConnection.getStatement().executeQuery(query);
            result.next();
            id_job = result.getInt("id_job");

        }
        catch (Exception e) {
            e.printStackTrace();
        }
        return id_job;
    }
    public int  idSelectedDepartment(String DepartmentName) {
        int id_department=0;

        try {
            String query = "SELECT * FROM `departments` WHERE `name`='" + DepartmentName + "'";
            ResultSet result = DatabaseConnection.getStatement().executeQuery(query);
            result.next();
            id_department = result.getInt("id_department");
            result.close();
        }
        catch (Exception e) {
            e.printStackTrace();
        }
        return id_department;
    }
    public ArrayList<String> get_allJobs()
    {
        ArrayList<String> jobList=new ArrayList<String>();
        try {
            String query = "SELECT * FROM `jobs` ;";
            ResultSet result = DatabaseConnection.getStatement().executeQuery(query);
            while (result.next())
            {
                String job = result.getString("name");
                jobList.add(job);
            }
            result.close();

        }
        catch (Exception e)
        {
            e.printStackTrace();
        }
        return jobList;
    }
    public String getJob(int id_job)
    {
        String job=null;
        try {
            String query = "SELECT * FROM `jobs` WHERE `id_job`='"+id_job+"'";
            ResultSet result = DatabaseConnection.getStatement().executeQuery(query);
            result.next();
            job=result.getString("name");
            result.close();
        }
        catch (Exception e)
        {
            e.printStackTrace();
        }
        return job;
    }
    public ArrayList<String> get_allDepartments()
    {
        ArrayList<String> departmentList=new ArrayList<String>();
        try {
            String query = "SELECT * FROM `departments`; ";
            ResultSet result = DatabaseConnection.getStatement().executeQuery(query);
            while (result.next()) {
                String department = result.getString("name");
                departmentList.add(department);
            }
            result.close();
        }
        catch (Exception e)
        {
            e.printStackTrace();
        }
        return departmentList;
    }
    public String getDepartment(int id_department)
    {
        String department=null;
        try {
            String query = "SELECT * FROM `departments` WHERE `id_department`='"+id_department+"'";
            ResultSet result = DatabaseConnection.getStatement().executeQuery(query);
            result.next();
            department=result.getString("name");
            result.close();
        }
        catch (Exception e)
        {
            e.printStackTrace();
        }
        return department;
    }
//    public static int getAvaliableId(){
//        int id=0;
//        try{
//            String querry = "SELECT MAX(`id_employee`) FROM `employees`";
//            ResultSet result = DatabaseConnection.getStatement().executeQuery(querry);
//            id=result.getInt("id_employee");
//        }
//        catch (Exception e) {
//            System.out.println(e);
//            e.printStackTrace();
//        }
//        return (id+1);
//    }

    public void insertEmployee(Employee em) {
        try {
            String querry = "INSERT INTO employees(id_employee,last_name,first_name,username,password,cnp,id_job,id_department,email,phone,role) VALUES (?,?,?,?,?,?,?,?,?,?,?)";
            //DatabaseConnection.getStatement().executeUpdate(querry);
            PreparedStatement pstmt = DatabaseConnection.getConnection().prepareStatement(querry);

            pstmt.setInt(1, em.getId());
            pstmt.setString(2, em.getLast_name());
            pstmt.setString(3, em.getFirst_name());
            pstmt.setString(4, em.getUsername());
            pstmt.setString(5, em.getPassword());
            pstmt.setString(6, em.getCnp());
            pstmt.setInt(7, em.getId_job());
            pstmt.setInt(8, em.getId_department());
            pstmt.setString(9, em.getEmail());
            pstmt.setString(10, em.getPhone());
            pstmt.setString(11, em.getRole());

            pstmt.executeUpdate();
            pstmt.close();
        }
        catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void updateEmployee(Employee em) {
        try {
            String querry = "UPDATE employees SET last_name=?,first_name=?,username=?,password=?,cnp=?,id_job=?,id_department=?,email=?,phone=?,role=? WHERE id_employee=?;";
            PreparedStatement pstmt = DatabaseConnection.getConnection().prepareStatement(querry);


            pstmt.setString(1, em.getLast_name());
            pstmt.setString(2, em.getFirst_name());
            pstmt.setString(3, em.getUsername());
            pstmt.setString(4, em.getPassword());
            pstmt.setString(5, em.getCnp());
            pstmt.setInt(6, em.getId_job());
            pstmt.setInt(7, em.getId_department());
            pstmt.setString(8, em.getEmail());
            pstmt.setString(9, em.getPhone());
            pstmt.setString(10, em.getRole());
            pstmt.setInt(11, em.getId());

            pstmt.executeUpdate();
            pstmt.close();
        }
        catch(SQLException e){
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
