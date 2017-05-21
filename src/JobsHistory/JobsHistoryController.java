package JobsHistory;

import Utils.UtilFunctions;

import java.text.ParseException;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Observable;
import java.util.List;

/**
 * Created by Incau Ionut on 29-Apr-17.
 * Contact: ionut.incau@gmail.com
 */

public class JobsHistoryController extends Observable{

    private JobsHistoryProvider provider;
    private ArrayList<JobHistory> list;
    public JobsHistoryController() {
        this.provider = new JobsHistoryProvider();
    }
    public ArrayList getJobHistoryList()
    {
        list=provider.getAllJobsHistory();
        return list;
    }

    public ArrayList<String> getEmployeeNameList()
    {
        return provider.getEmployeeName();
    }
    public ArrayList<String> getDepartmentNameList()
    {
        return provider.getDepartmentList();
    }
    public ArrayList<String> getJobNameList()
    {
        return provider.getJobList();
    }
    public ArrayList<String> getTypeList()
    {
        ArrayList<String> listType=new ArrayList<>();
        listType.add("vacation");
        listType.add("working");
        listType.add("trainer");
        listType.add("fired");
        return listType;
    }
    public String getNameJH(int id_employee)
    {
        return provider.GetNameInJBH(id_employee);
    }
    public int get_next_id()
    {
        int id=0;
        for (JobHistory e:list)
        {
            if (id<e.getIdJobHistory())
            {
                id=e.getIdJobHistory();
            }
        }
        id=id+1;
        return id;
    }
    public void add(String s_date, String e_date, String name_employee,String name_job, String department_job, String status) throws ParseException{
        Calendar start_date = UtilFunctions.formatDate(s_date);
        Calendar end_date = UtilFunctions.formatDate(e_date);
        int id_job=provider.idSelectedJob(name_job);
        int id_employee=provider.GetIdEmployee(name_employee);
        int id_department=provider.idSelectedDepartment(department_job);
        JobHistory jb = new JobHistory(JobsHistoryProvider.getAvaliableId(),id_job,start_date, end_date, id_employee, id_department, status);
        provider.insertJobHistory(jb);
        setChanged();
        notifyObservers();
    }

    public void edit(int id,String s_date, String e_date, String name_employee,String name_job, String department_job, String status) throws ParseException{
        Calendar start_date = UtilFunctions.formatDate(s_date);
        Calendar end_date = UtilFunctions.formatDate(e_date);
        int id_job=provider.idSelectedJob(name_job);
        int id_employee=provider.GetIdEmployee(name_employee);
        int id_department=provider.idSelectedDepartment(department_job);
        JobHistory jb = new JobHistory( id, id_job, start_date, end_date, id_employee, id_department, status);
        provider.updateJobHistory(jb);
        setChanged();
        notifyObservers();
    }

    public void remove(JobHistory jb) {
        provider.deleteJobHistory(jb);
        setChanged();
        notifyObservers();
    }

    public List<Integer> getLastThreeSalaries(int employeeID)
    {
        return provider.getLastThreeSalaries(employeeID);
    }

    public java.sql.Date getStartDate(int employeeID) throws Exception {
        return provider.getStartDate(employeeID);
    }

    public String getDepartmentName(int id)
    {
        return provider.GetNameDepartment(id);
    }
    public String getJobName(int id)
    {
        return provider.GetNameJob(id);
    }

    public int getUsedVacationDays(int employeeID)
    {
        return provider.getUsedVacationDays(employeeID);
    }
}
