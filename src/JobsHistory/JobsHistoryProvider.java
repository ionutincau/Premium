package JobsHistory;

import database.DatabaseConnection;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;

/**
 * Created by ASUS on 09.Apr.2017.
 */
public class JobsHistoryProvider {

    public JobsHistoryProvider() {

    }
    public ArrayList getAllJobsHistory() {
        ArrayList<JobHistory> list = new ArrayList<JobHistory>();
        try {
            String querry = "SELECT * FROM `jobs_history`";
            ResultSet result = DatabaseConnection.getStatement().executeQuery(querry);
            while (result.next()) {
                int id_job_history = result.getInt("id_job_history");
                int id_job = result.getInt("id_job");
                int id_employee=result.getInt("id_employee");
                Date date = result.getDate("start_date");
                Calendar start_date = new GregorianCalendar();
                start_date.setTime(date);
                Date date1 = result.getDate("end_date");
                Calendar end_date = new GregorianCalendar();
                end_date.setTime(date1);
                int id_department = result.getInt("id_department");
                String status=result.getString("status");
                JobHistory jobHistory = new JobHistory(id_job_history,id_job,start_date,end_date,id_employee,id_department,status);
                list.add(0,jobHistory);
            }
        }
        catch (Exception e) {
            System.out.println(e);
            e.printStackTrace();
        }
        return list;
    }
    public ArrayList getEmployeeName()
    {
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
    public ArrayList getDepartmentList() {
        ArrayList<String> listDepartmentName = new ArrayList<>();
        try {
            String querry = "SELECT * FROM `departments` ;";
            ResultSet result = DatabaseConnection.getStatement().executeQuery(querry);
            while (result.next()) {
                String name = result.getString("name");

                listDepartmentName.add(name);
            }
            result.close();
        } catch (Exception e) {
            System.out.println(e);
            e.printStackTrace();
        }
        return listDepartmentName;
    }
    public ArrayList getJobList()
    {
        ArrayList<String> listJobsName=new ArrayList<>();
        try {
            String querry = "SELECT * FROM `jobs` ;";
            ResultSet result = DatabaseConnection.getStatement().executeQuery(querry);
            while (result.next()) {
                String name=result.getString("name");
                listJobsName.add(name);
            }
            result.close();
        }
        catch (Exception e) {
            System.out.println(e);
            e.printStackTrace();
        }
        return listJobsName;
    }
    public  String GetNameInJBH(int id_employee)
    {
        String name=null;
        try {
            String querry = "SELECT * FROM `employees` WHERE `id_employee`='"+id_employee+"'";
            ResultSet result = DatabaseConnection.getStatement().executeQuery(querry);
            result.next();
            String last_name=result.getString("last_name");
            String first_name=result.getString("first_name");
            name=last_name+" "+first_name;
            result.close();
        }
        catch (SQLException e)
        {
            e.printStackTrace();
        }
        return name;
    }
    public  String GetNameDepartment(int id_department)
    {
        String name=null;
        try {
            String querry = "SELECT * FROM `departments` WHERE `id_department`='"+id_department+"'";
            ResultSet result = DatabaseConnection.getStatement().executeQuery(querry);
            result.next();
            name=result.getString("name");
            result.close();
        }
        catch (SQLException e)
        {
            e.printStackTrace();
        }
        return name;
    }
    public  String GetNameJob(int id_job)
    {
        String name=null;
        try {
            String querry = "SELECT * FROM `jobs` WHERE `id_job`='"+id_job+"'";
            ResultSet result = DatabaseConnection.getStatement().executeQuery(querry);
            result.next();
            name=result.getString("name");
            result.close();
        }
        catch (SQLException e)
        {
            e.printStackTrace();
        }
        return name;
    }
//    public ArrayList getJobsHistory(int id_employee) {
//        ArrayList<JobHistory> list = new ArrayList<JobHistory>();
//        try {
//            String querry = "SELECT * FROM `jobs_history` WHERE `id_employee`="+id_employee+";";
//            ResultSet result = DatabaseConnection.getStatement().executeQuery(querry);
//            while (result.next()) {
//                int id_job = result.getInt("id_job");
//                Date date = result.getDate("start_date");
//                Calendar start_date = new GregorianCalendar();
//                start_date.setTime(date);
//                Date date1 = result.getDate("end_date");
//                Calendar end_date = new GregorianCalendar();
//                end_date.setTime(date);
//                int id_department = result.getInt("id_department");
//                String status=result.getString("status");
//
//                JobHistory jobHistory = new JobHistory(id_job,start_date,end_date,id_employee,id_department,status);
//                list.add(0,jobHistory);
//            }
//        }
//        catch (Exception e) {
//            System.out.println(e);
//            e.printStackTrace();
//        }
//        return list;
//    }

    public static int getAvaliableId(){
        int id = 0;
        try{
            String querry = "SELECT MAX(`id_job_history`) FROM `jobs_history`";
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

    public void insertJobHistory(JobHistory jh) {
        String end_date="2000-00-00";
        String querry = "INSERT INTO `jobs_history`(`id_job`,`start_date`,`end_date`,`id_employee`,`id_department`,`status`) VALUES (?,?,?,?,?,?)";
        SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
        if (jh.getEnd_date()!=null) {
            end_date = formatter.format(jh.getEnd_date().getTime());
        }
        end_date = formatter.format(jh.getEnd_date().getTime());
        String start_date = formatter.format(jh.getStart_date().getTime());

        try {
            PreparedStatement pstmt = DatabaseConnection.getConnection().prepareStatement(querry);

            pstmt.setInt(1, jh.getId_job());
            pstmt.setString(2, start_date);
            pstmt.setString(3, end_date);
            pstmt.setInt(4, jh.getId_employee());
            pstmt.setInt(5, jh.getId_department());
            pstmt.setString(6, jh.getStatus());

            pstmt.executeUpdate();
            pstmt.close();
        }
        catch (Exception e) {
            System.out.println(e);
            e.printStackTrace();
        }
    }

    public void updateJobHistory(JobHistory jh) {
        String querry = "UPDATE `jobs_history` SET `end_date`= ? ,`id_department`= ? ,`status`= ?  WHERE `id_employee`= ? AND `id_job`= ?;";
        SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
        String end_date = formatter.format(jh.getEnd_date().getTime());
        try {
            PreparedStatement pstmt = DatabaseConnection.getConnection().prepareStatement(querry);

            pstmt.setString(1, end_date);
            pstmt.setInt(2, jh.getId_department());
            pstmt.setString(3, jh.getStatus());
            pstmt.setInt(4, jh.getId_employee());
            pstmt.setInt(5, jh.getId_job());

            pstmt.executeUpdate();
            pstmt.close();
        }
        catch(Exception e){
            System.out.println(e);
            e.printStackTrace();
        }
    }
    public void updateJobHistoryStatus(JobHistory jh) {
        String querry = "UPDATE `jobs_history` SET `status`= ?  WHERE `id_employee`= ? AND `id_job`= ?;";
        try {
            PreparedStatement pstmt = DatabaseConnection.getConnection().prepareStatement(querry);

            pstmt.setString(1, jh.getStatus());
            pstmt.setInt(2, jh.getId_employee());
            pstmt.setInt(3, jh.getId_job());

            pstmt.executeUpdate();
            pstmt.close();
        }
        catch(Exception e){
            System.out.println(e);
            e.printStackTrace();
        }
    }
    public void deleteJobHistory(JobHistory jh) {
        try {
            String querry="DELETE FROM `jobs_history` WRITE `id_job`="+jh.getId_job()+";";
            DatabaseConnection.getStatement().executeUpdate(querry);
        }
        catch(Exception e){
            System.out.print(e);
            e.printStackTrace();
        }
    }

    public int getVacationDays(int id_employee) {
        int nr_days = 0;
        try {
            String querry = "SELECT COUNT(*) FROM `jobs_history` WHERE `id_employee`='" + id_employee + "'";
            ResultSet result = DatabaseConnection.getStatement().executeQuery(querry);
            result.next();
            nr_days = result.getInt(1);
            result.close();
        }
        catch (SQLException e) {
            e.printStackTrace();
        }
        return nr_days;
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
    public  int GetIdEmployee(String employeeName)
    {
        int id_manager=0;
        String[] managerName=employeeName.split(" ",2);
        try {
            String querry = "SELECT * FROM `employees` WHERE `last_name`=" + managerName[0] + " AND `first_name`=" + managerName[1] + ";";
            ResultSet result = DatabaseConnection.getStatement().executeQuery(querry);
            id_manager=result.getInt("id_employee");
            result.close();
        }
        catch (SQLException e)
        {
            e.printStackTrace();
        }
        return id_manager;
    }
}
