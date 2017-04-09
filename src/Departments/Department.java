package Departments;

/**
 * Created by ASUS on 09.Apr.2017.
 */
public class Department {
    private int id_department;
    private String name;
    private int id_manager;

    public Department(int id_department,String name,int id_manager){
        this.id_department=id_department;
        this.name=name;
        this.id_manager=id_manager;
    }

    public int getId(){ return this.id_department; }
    public String getName(){ return this.name; }
    public int getId_manager(){ return this.id_manager; }

    public void setId(int id_department){ this.id_department=id_department; }
    public void setName(String name) { this.name=name; }
    public void setId_manager(int id_manager) { this.id_manager=id_manager; }


}
