package Employees;

import java.util.ArrayList;

/**
 * Created by Incau Ionut on 15-Apr-17.
 * Contact: ionut.incau@gmail.com
 */

public class EmployeesController {

    private EmployeesProvider provider;
    ArrayList<Employee> list = new ArrayList<Employee>();

    public EmployeesController() {
        provider = new EmployeesProvider();
    }

    public ArrayList getEmployees() {
        return provider.getEmployees();
    }

    public void addEmployee(String last_name,String first_name,String username, String password,String cnp,int id_job,int id_department,String email,String phone,String role)
    {
        Employee e = new Employee(1,last_name,first_name,username,password,cnp,id_job,id_department,email,phone,role);
        provider.insertEmployee(e);
        list.add(e);
    }
    public void editEmployee(String last_name,String first_name,String username, String password,String cnp,int id_job,int id_department,String email,String phone,String role)
    {
        Employee e = new Employee(1,last_name,first_name,username,password,cnp,id_job,id_department,email,phone,role);
        provider.updateEmployee(e);
    }
    public void deleteEmployee(Employee e)
    {
        provider.deleteEmployee(e);
    }
    public ArrayList searchByName(String name)
    {
        String[] listName =name.split(" ");
        ArrayList<Employee> listE=new ArrayList<>();
        String last_name=listName[0];
        String first_name=listName[1];
        for (Employee o:list)
        {
            if ((last_name.equals(o.getLast_name()))&&(first_name.equals(o.getFirst_name())))
            {
                listE.add(o);
            }
        }
        return listE;
    }
}
