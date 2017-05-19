package Employees;

import java.util.ArrayList;
import java.util.Observable;

/**
 * Created by Incau Ionut on 15-Apr-17.
 * Contact: ionut.incau@gmail.com
 */

public class EmployeesController extends Observable{

    private EmployeesProvider provider;
    ArrayList<Employee> list = new ArrayList<Employee>();

    public EmployeesController() {
        provider = new EmployeesProvider();
    }

    public ArrayList getEmployees() {
        list=provider.getEmployees();
        return provider.getEmployees();

    }

    public void addEmployee(String last_name,String first_name,String username, String password,String cnp,int id_job,int id_department,String email,String phone,String role)
    {
        Employee e = new Employee(get_next_id(),last_name,first_name,username,password,cnp,id_job,id_department,email,phone,role);
        provider.insertEmployee(e);
        list.add(e);
        setChanged();
        notifyObservers();
    }
    public ArrayList<String> GetAllRole()
    {
        ArrayList<String> roleList=new ArrayList<>();

        for (Employee o:list)
        {
            if (!exist(roleList,o.getRole()))
            {
                roleList.add(o.getRole());
            }
        }
        return roleList;
    }
    public boolean exist(ArrayList<String> roleString,String role)
    {
        int check=0;
        for (String o:roleString)
        {
            if (o.equals(role))
            {
                check++;
            }
        }
        if (check==1)
        {
            return true;
        }
        return false;
    }
    public int get_next_id()
    {
        int id=0;
        for (Employee e:list)
        {
            if (id<e.getId())
            {
                id=e.getId();
            }
        }
        id=id+1;
        return id;
    }
    public void editEmployee(int id,String last_name,String first_name,String username, String password,String cnp,int id_job,int id_department,String email,String phone,String role)
    {
        Employee e = new Employee(id,last_name,first_name,username,password,cnp,id_job,id_department,email,phone,role);
        provider.updateEmployee(e);
        int nr=0;
        for (Employee o:list)
        {
            if (o.getId()!=e.getId())
            {
             nr++;
            }
        }
        list.remove(nr);
        list.add(nr,e);
        setChanged();
        notifyObservers();
    }
    public void deleteEmployee(Employee e)
    {
        list.remove(e);
        provider.deleteEmployee(e);
        setChanged();
        notifyObservers();
    }
    public ArrayList searchByName(String name) {
        ArrayList<Employee> listE = new ArrayList<>();
        if (name.contains("@"))
        {
            for (Employee o : list) {
                if (o.getEmail().equals(name)) {
                    listE.add(o);
                }
            }
            return listE;
        }
        else if (!name.equals("")) {
            String[] listName = name.split(" ");
            String last_name = listName[0];
            String first_name = listName[1];
            for (Employee o : list) {
                if ((last_name.equalsIgnoreCase(o.getLast_name())) && (first_name.equalsIgnoreCase(o.getFirst_name()))) {
                    listE.add(o);
                }
            }
            return listE;

        }
        setChanged();
        notifyObservers();
        return list;

    }
    public int idJob(String job)
    {
        int id_job=provider.idSelectedJob(job);
        return id_job;
    }
    public int idDepartment(String department)
    {
        int id_department=provider.idSelectedDepartment(department);
        return id_department;
    }
    public String GetJobName(int id_employee)
    {
        String job=provider.getJob(id_employee);
        return  job;
    }
    public String GetDepartment(int id_department)
    {
        String Department=provider.getDepartment(id_department);
        return Department;
    }
    public  ArrayList<String> GetAllDepartments()
    {
        ArrayList<String> DepartmentList=new ArrayList<>();
        DepartmentList=provider.get_allDepartments();
        return DepartmentList;
    }
    public ArrayList<String> GetAllJobs()
    {
        ArrayList<String> JobList=new ArrayList<>();
        JobList=provider.get_allJobs();
        return JobList;
    }
}
