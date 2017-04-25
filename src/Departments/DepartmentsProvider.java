package Departments;

import database.DatabaseConnection;

import java.sql.ResultSet;

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


    public static int getAvaliableId(){
        int id=0;
        try{
            String querry = "SELECT MAX(`id_department`) FROM `departments`";
            ResultSet result = DatabaseConnection.getStatement().executeQuery(querry);
            id=result.getInt("id_department");
        }
        catch (Exception e) {
            System.out.println(e);
            e.printStackTrace();
        }
        return (id+1);
    }

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
            DatabaseConnection.getStatement().executeUpdate(querry);
        } catch (Exception e) {
            System.out.println(e);
            e.printStackTrace();

        }

    }
    public void updateDepartment(Department d) {
        try {
            String querry = "UPDATE `departments` SET `name`="+d.getName()+",`id_manager`="+d.getId_manager()+" WHERE `id_department`="+d.getId()+";";
            DatabaseConnection.getStatement().executeUpdate(querry);
        }
        catch(Exception e){
            System.out.println(e);
            e.printStackTrace();

        }
    }
    public void deleteDepartment(Department d){
        try {
            String querry = "DELETE FROM `departments` WHERE `id_departmenr`="+d.getId()+";";
            DatabaseConnection.getStatement().executeUpdate(querry);
        }
        catch(Exception e){
            System.out.println(e);
            e.printStackTrace();

        }
    }
}
