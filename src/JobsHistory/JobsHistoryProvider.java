package JobsHistory;

import Jobs.Job;

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
public class JobsHistoryProvider {
    private Connection con;
    private Statement statement;
    private ResultSet result;

    public JobsHistoryProvider() {
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

    public ArrayList getJobsHistory(int id_employee) {
        ArrayList<JobHistory> list = new ArrayList<JobHistory>();
        try {
            String querry = "SELECT * FROM `jobs_history` WHERE `id_employee`="+id_employee+";";
            result = statement.executeQuery(querry);
            while (result.next()) {
                int id_job = result.getInt("id_job");
                Date date = result.getDate("start_date");
                Calendar start_date = new GregorianCalendar();
                start_date.setTime(date);
                Date date1 = result.getDate("end_date");
                Calendar end_date = new GregorianCalendar();
                end_date.setTime(date);
                int id_department = result.getInt("id_department");
                String status=result.getString("status");

                JobHistory jobHistory = new JobHistory(id_job,start_date,end_date,id_department,status);
                list.add(0,jobHistory);
            }
        }
        catch (Exception e) {
            System.out.println(e);
            e.printStackTrace();
        }
        return list;
    }
    public void insertJobHistory(JobHistory jh,int id_employee) {

        try {
            String querry = "INSERT INTO `jobs_history`(`start_date`,`end_date`,`id_employee`,`id_department`,`status`) VALUES (" + jh.getStart_date() + "," + jh.getEnd_date() + "," + jh.getId_department() + "," + jh.getStatus()+")";
            statement.executeUpdate(querry);
        } catch (Exception e) {
            System.out.println(e);
            e.printStackTrace();

        }
    }
    public void updateJobHistory(JobHistory jh,int id_employee)
    {

        try {
            String querry = "UPDATE `jobs_history` SET `start_date`="+jh.getStart_date()+",`end_date`="+jh.getEnd_date()+",`id_department`="+jh.getId_department()+",`status`="+jh.getStatus()+" WHERE `id_employee`="+id_employee+";";
            statement.executeUpdate(querry);
        }
        catch(Exception e){
            System.out.println(e);
            e.printStackTrace();

        }
    }
    public void deleteJobHistory(JobHistory jh)
    {
        try {
            String querry="DELETE FROM `jobs_history` WRITE `id_job`="+jh.getId_job()+";";
            statement.executeUpdate(querry);
        }
        catch(Exception e){
            System.out.print(e);
            e.printStackTrace();
        }
    }

}
