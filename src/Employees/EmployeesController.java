package Employees;

import java.util.ArrayList;

/**
 * Created by Incau Ionut on 15-Apr-17.
 * Contact: ionut.incau@gmail.com
 */

public class EmployeesController {

    private EmployeesProvider provider;

    public EmployeesController() {
        provider = new EmployeesProvider();
    }

    public ArrayList getEmployees() {
        return provider.getEmployees();
    }

    public void addEmployee() {

    }

    public void editEmployee() {

    }

    public void deleteEmployee() {

    }

    public ArrayList searchByName(String name) {
        //todo:
        return null;
    }
}
