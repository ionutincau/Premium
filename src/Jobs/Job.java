package Jobs;

/**
 * Created by ASUS on 09.Apr.2017.
 */
public class Job {
    private int id_job;
    private String name;
    private String min_salary;
    private String number;
    private int id_document;

    public Job(int id_job,String name,String min_salary,String number,int id_document){
        this.id_job=id_job;
        this.name=name;
        this.min_salary=min_salary;
        this.number=number;
        this.id_document=id_document;
    }

    public int getId() { return this.id_job; }
    public String getName() { return this.name; }
    public String getMin_salary() { return this.min_salary; }
    public String getNumber() { return  this.number; }
    public int getId_document() { return this.id_document; }

    public void setId(int id_job) { this.id_job=id_job; }
    public void setName(String name) { this.name=name; }
    public void setMin_salary(String min_salary) { this.min_salary=min_salary; }
    public void setNumber(String number) { this.number=number; }
    public void setId_document(int id_document) { this.id_document=id_document; }

    @Override
    public String toString() {
        return name + " - " + min_salary + " - " + number;
    }
}
