package Departments;

import database.DatabaseConnection;

import java.sql.PreparedStatement;
import java.sql.ResultSet;

import java.sql.SQLException;
import java.util.ArrayList;

/**
 * Created by ASUS on 09.Apr.2017.
 */

public class DepartmentsProvider {
   
    public DepartmentsProvider() {
        
    }

    public Department getDepartament(int id) {
        Department department = null;
        try {
            String querry = "SELECT * FROM `departments` WHERE `id_department`='" + id + "'";
            ResultSet result = DatabaseConnection.getStatement().executeQuery(querry);
            if (result.next()) {
                int id_department = result.getInt("id_department");
                String name=result.getString("name");
                int id_manager = result.getInt("id_manager");
                department = new Department(id_department,name,id_manager);
            }
        }
        catch (Exception e) {
            e.printStackTrace();
        }
        return department;
    }


//    public static int getAvaliableId(){
//        int id=0;
//        try{
//            String querry = "SELECT MAX(`id_department`) FROM `departments`";
//            ResultSet result = DatabaseConnection.getStatement().executeQuery(querry);
//            id=result.getInt("id_department");
//        }
//        catch (Exception e) {
//            System.out.println(e);
//            e.printStackTrace();
//        }
//        return (id+1);
//    }

    public ArrayList getDepartments() {
        ArrayList<Department> list = new ArrayList<Department>();
        try {
            String querry = "SELECT * FROM `departments` ;";
            ResultSet result = DatabaseConnection.getStatement().executeQuery(querry);
            while (result.next()) {
                int id_department = result.getInt("id_department");
                String name=result.getString("name");
                int id_manager = result.getInt("id_manager");

                Department department = new Department(id_department,name,id_manager);
                list.add(0, department);
            }
            result.close();
        }
        catch (Exception e) {
            System.out.println(e);
            e.printStackTrace();
        }
        return list;
    }

    public int id_manager(String name) {
        String[] Name=name.split(" ");
        int id_manager=0;
        try {
            String querry = "SELECT id_employee FROM `employees` WHERE `last_name`='"+Name[0]+"' AND `first_name`='"+Name[1]+"' ;";
            ResultSet result = DatabaseConnection.getStatement().executeQuery(querry);
            while (result.next()) {
                id_manager = result.getInt("id_employee");
            }
        }
        catch (Exception e) {
            System.out.println(e);
            e.printStackTrace();
        }
        return id_manager;
    }

    public void insertDepartment(Department d) {
        try {
            String querry = "INSERT INTO departments(id_department,name,id_manager) VALUES (?,?,?)";
            PreparedStatement pstmt = DatabaseConnection.getConnection().prepareStatement(querry);

            pstmt.setInt(1, d.getId());
            pstmt.setString(2, d.getName());
            pstmt.setInt(3, d.getId_manager());
            pstmt.executeUpdate();
            pstmt.close();
        }
        catch (Exception e) {
            System.out.println(e);
            e.printStackTrace();
        }
    }

    public void updateDepartment(Department d) {
        try {
            String querry = "UPDATE departments SET name=?,id_manager=? WHERE id_department=? ;";
            PreparedStatement pstmt = DatabaseConnection.getConnection().prepareStatement(querry);

            pstmt.setString(1, d.getName());
            pstmt.setInt(2, d.getId_manager());
            pstmt.setInt(3, d.getId());
            pstmt.executeUpdate();
            pstmt.close();
        }
        catch(Exception e){
            System.out.println(e);
            e.printStackTrace();
        }
    }

    public void deleteDepartment(Department d) {
        try {
            String querry = "DELETE FROM `departments` WHERE `id_department`="+d.getId()+";";
            DatabaseConnection.getStatement().executeUpdate(querry);
        }
        catch(Exception e){
            System.out.println(e);
            e.printStackTrace();
        }
    }

    public ArrayList getEmployeeName() {
        ArrayList<String> listEmployeesName=new ArrayList<>();
        try {
            String querry = "SELECT * FROM `employees` ;";
            ResultSet result = DatabaseConnection.getStatement().executeQuery(querry);
            while (result.next()) {
                String last_name=result.getString("last_name");
                String first_name=result.getString("first_name");
                String name=last_name+" "+first_name;
                listEmployeesName.add(name);
            }
            result.close();
        }
        catch (Exception e) {
            System.out.println(e);
            e.printStackTrace();
        }
        return listEmployeesName;
    }

    public  int GetIdManager(String ManagerName) {
        int id_manager=0;
        String[] managerName=ManagerName.split(" ",2);
        try {
            String querry = "SELECT * FROM `employees` WHERE `last_name`=" + managerName[0] + " AND `first_name`=" + managerName[1] + ";";
            ResultSet result = DatabaseConnection.getStatement().executeQuery(querry);
            id_manager=result.getInt("id_employee");
            result.close();
        }
        catch (SQLException e) {
            e.printStackTrace();
        }
        return id_manager;
    }

    public  String GetNameManager(int id_manger) {
        String name=null;
        try {
            String querry = "SELECT * FROM `employees` WHERE `id_employee`='"+id_manger+"'";
            ResultSet result = DatabaseConnection.getStatement().executeQuery(querry);
            result.next();
            String last_name=result.getString("last_name");
            String first_name=result.getString("first_name");
            name=last_name+" "+first_name;
            result.close();
        }
        catch (SQLException e) {
            e.printStackTrace();
        }
        return name;
    }
}
