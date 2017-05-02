package JobsHistory;

import Jobs.Job;
import database.DatabaseConnection;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
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


    public ArrayList getJobsHistory(int id_employee) {
        ArrayList<JobHistory> list = new ArrayList<JobHistory>();
        try {
            String querry = "SELECT * FROM `jobs_history` WHERE `id_employee`="+id_employee+";";
            ResultSet result = DatabaseConnection.getStatement().executeQuery(querry);
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

                JobHistory jobHistory = new JobHistory(id_job,start_date,end_date,id_employee,id_department,status);
                list.add(0,jobHistory);
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
            String querry = "SELECT MAX(`id_job`) FROM `jobs_history`";
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
        String querry = "INSERT INTO `jobs_history`(`id_job`,`start_date`,`end_date`,`id_employee`,`id_department`,`status`) VALUES (?,?,?,?,?,?)";
        SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
        String start_date = formatter.format(jh.getStart_date().getTime());
        String end_date = formatter.format(jh.getEnd_date().getTime());

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
        } catch (Exception e) {
            System.out.println(e);
            e.printStackTrace();

        }
    }
    public void updateJobHistory(JobHistory jh)
    {
        String querry = "UPDATE `jobs_history` SET `start_date`= ? ,`end_date`= ? ,`id_department`= ? ,`status`= ?  WHERE `id_employee`= ? ;";
        SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
        String start_date = formatter.format(jh.getStart_date().getTime());
        String end_date = formatter.format(jh.getEnd_date().getTime());
        try {
            PreparedStatement pstmt = DatabaseConnection.getConnection().prepareStatement(querry);

            pstmt.setString(1, start_date);
            pstmt.setString(2, end_date);
            pstmt.setInt(3, jh.getId_employee());
            pstmt.setInt(4, jh.getId_department());
            pstmt.setString(5, jh.getStatus());
            pstmt.setInt(6, jh.getId_job());

            pstmt.executeUpdate();
            pstmt.close();

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
            DatabaseConnection.getStatement().executeUpdate(querry);
        }
        catch(Exception e){
            System.out.print(e);
            e.printStackTrace();
        }
    }

}
