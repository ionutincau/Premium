package JobsHistory;

import java.util.Calendar;

/**
 * Created by ASUS on 09.Apr.2017.
 */
public class JobHistory {
    private int id_job;
    private Calendar start_date;
    private Calendar end_date;
    private int id_department;
    private String status;

    public JobHistory(int id_job,Calendar start_date,Calendar end_date,int id_department,String status){
        this.id_job=id_job;
        this.start_date=start_date;
        this.end_date=end_date;
        this.id_department=id_department;
        this.status=status;
    }

    public int getId_job(){return this.id_job;}
    public Calendar getStart_date(){return this.start_date;}
    public Calendar getEnd_date(){return this.end_date;}
    public int getId_department(){return this.id_department;}
    public String getStatus(){return this.status;}

    public void  setId(int id_job){
        this.id_job=id_job;
    }
    public void setStart_date(Calendar start_date){
        this.start_date=start_date;
    }
    public void setEnd_date(Calendar end_date){
        this.end_date=end_date;
    }
    public void setId_department(int id_department){
        this.id_department=id_department;
    }
    public void setStatus(String status){
        this.status=status;
    }


}
