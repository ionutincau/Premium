package Departments;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Observable;

/**
 * Created by MariusDK on 01.05.2017.
 */
public class DepartmentsController extends Observable{
    ArrayList<Department> list=new ArrayList<>();
    DepartmentsProvider provider=new DepartmentsProvider();
    public ArrayList getDepartments()
    {
        list=provider.getDepartments();
        return list;
    }
    public int id_department()
    {
        int id_department=0;
        if (list.size()==0)
        {
            return 1;
        }
        else {
            for (Department o:list)
            {
                if (o.getId()>id_department)
                {
                    id_department=o.getId();
                }
            }
            id_department++;
        }
        return id_department;
    }
    public void addDepartment(String name,String managerName)
    {
        int id_manager=provider.id_manager(managerName);
        int id_department=id_department();
        Department d=new Department(id_department,name,id_manager);

        provider.insertDepartment(d);
        list.add(d);
        setChanged();
        notifyObservers();
    }
    public void editDepartment(int id_department,String name,String managerName)
    {
        int id_manager=provider.id_manager(managerName);
        Department d=new Department(id_department,name,id_manager);
        provider.updateDepartment(d);
        int nr=0;
        for (Department o:list)
        {
            if (o.getId()!=d.getId())
            {
                nr++;
            }
        }
        list.remove(nr);
        list.add(nr,d);
        setChanged();
        notifyObservers();
    }
    public void removeDepartment(Department d)
    {
        list.remove(d);
        provider.deleteDepartment(d);
        setChanged();
        notifyObservers();
    }
    public int getIdManager(String ManagerName)
    {
        int id=0;
        id=provider.GetIdManager(ManagerName);
        return id;
    }
    public ArrayList<String> getEmployeeName()
    {
        ArrayList<String> listEmployees=provider.getEmployeeName();
        return listEmployees;
    }
    public String getManagerName(int id_manager)
    {
        String name=provider.GetNameManager(id_manager);
        return name;
    }
}
