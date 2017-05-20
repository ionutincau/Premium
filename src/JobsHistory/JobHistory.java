package JobsHistory;

import java.util.Calendar;

/**
 * Created by ASUS on 09.Apr.2017.
 */

public class JobHistory {
    private int idJobHistory;
    private int id_job;
    private Calendar start_date;
    private Calendar end_date;
    private int id_employee;
    private int id_department;
    private String status;
    public JobHistory(int idJobHistory, int id_job, Calendar start_date, Calendar end_date, int id_employee, int id_department, String status){
        this.idJobHistory=idJobHistory;

        this.id_job=id_job;
        this.start_date=start_date;
        this.end_date=end_date;
        this.id_employee=id_employee;
        this.id_department=id_department;
        this.status=status;
    }

    public int getIdJobHistory() {
        return idJobHistory;
    }

    public void setIdJobHistory(int idJobHistory) {
        this.idJobHistory = idJobHistory;
    }

    public int getId_job() {
        return id_job;
    }

    public void setId_job(int id_job) {
        this.id_job = id_job;
    }

    public Calendar getStart_date() {
        return start_date;
    }

    public void setStart_date(Calendar start_date) {
        this.start_date = start_date;
    }

    public Calendar getEnd_date() {
        return end_date;
    }

    public void setEnd_date(Calendar end_date) {
        this.end_date = end_date;
    }

    public int getId_employee() {
        return id_employee;
    }

    public void setId_employee(int id_employee) {
        this.id_employee = id_employee;
    }

    public int getId_department() {
        return id_department;
    }

    public void setId_department(int id_department) {
        this.id_department = id_department;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    @Override
    public String toString() {
        return "JobHistory{" +
                "id_job=" + id_job +
                ", start_date=" + start_date +
                ", end_date=" + end_date +
                ", id_employee=" + id_employee +
                ", id_department=" + id_department +
                ", status='" + status + '\'' +
                '}';
    }
}
