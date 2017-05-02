package JobsHistory;

import Utils.UtilFunctions;

import java.text.ParseException;
import java.util.Calendar;

/**
 * Created by Incau Ionut on 29-Apr-17.
 * Contact: ionut.incau@gmail.com
 */

public class JobsHistoryController {

    private JobsHistoryProvider provider;

    public JobsHistoryController() {
        this.provider = new JobsHistoryProvider();
    }

    public void add(String s_date, String e_date, int id_employee, int id_department, String status) throws ParseException{
        Calendar start_date = UtilFunctions.formatDate(s_date);
        Calendar end_date = UtilFunctions.formatDate(e_date);
        JobHistory jb = new JobHistory(JobsHistoryProvider.getAvaliableId(), start_date, end_date, id_employee, id_department, status);
        provider.insertJobHistory(jb);
    }

    public void edit(String s_date, String e_date, int id_employee, int id_department, String status) throws ParseException{
        Calendar start_date = UtilFunctions.formatDate(s_date);
        Calendar end_date = UtilFunctions.formatDate(e_date);
        JobHistory jb = new JobHistory(JobsHistoryProvider.getAvaliableId(), start_date, end_date, id_employee, id_department, status);
        provider.updateJobHistory(jb);
    }

    public void remove(JobHistory jb) {
        provider.deleteJobHistory(jb);
    }

    public int getVacationDays(int id_employee) {
        return provider.getVacationDays(id_employee);
    }
}
