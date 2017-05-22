package Reports;

import Clocking.Clocking;
import DocumentTypes.Income;
import DocumentTypes.TimeSheet;
import DocumentTypes.Vacation;
import DocumentTypes.WorkedPeriod;
import Documents.Document;
import Documents.DocumentsProvider;
import Employees.Employee;
import Employees.EmployeesProvider;
import Requests.RequestsUser;
import Utils.UtilFunctions;
import Clocking.ClockingProvider;

import java.awt.*;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Observable;

/**
 * Created by Incau Ionut on 21-May-17.
 * Contact: ionut.incau@gmail.com
 */

public class ReportsController extends Observable {

    public ReportsController() {

    }

    public ArrayList getAllReports() {
        return new ArrayList(); //todo
    }

    public void generateTime() {
        ArrayList<String> time = new ArrayList();
        EmployeesProvider employeesProvider = new EmployeesProvider();
        ArrayList<Employee> employeesList = employeesProvider.getEmployees();
        ClockingProvider clockingProvider = new ClockingProvider();
        for (Employee employee : employeesList) {
            ArrayList<Clocking> clockings = clockingProvider.getClockings(employee.getId());
            int total_time = 0;
            for (Clocking clocking : clockings) {
                total_time += clocking.get_time();
            }
            String totalTime = UtilFunctions.get_time_format(total_time);
            String dots = "......................................";
            String t = employee.getLast_name() + " " + employee.getFirst_name() + dots + totalTime;
            time.add(t);
        }
        TimeSheet timeSheet = new TimeSheet("22.04.2017", UtilFunctions.get_date_format(Calendar.getInstance()), time);
        timeSheet.generatePDF();
        seeFile(timeSheet);
    }

    public void seeFile (TimeSheet timeSheet) {
        if (Desktop.isDesktopSupported()) {
            try {
                File myFile = new File(timeSheet.getFilenameName());
                Desktop.getDesktop().open(myFile);
            }
            catch (IOException ex) {
                UtilFunctions.showInfo("No application registered for PDFs");
            }
        }
    }
}
