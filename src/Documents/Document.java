package Documents;

import java.util.Calendar;

/**
 * Created by SkReaper on 08.Apr.2017.
 */

public class Document {
    private int id_document;
    private int id_employee;
    private String name;
    private Calendar date;
    private int id_doctype;
    private Object doc;

    public Document(int id_document, int id_employee, String name, Calendar cal, int id_doctype, Object doc) {
        this.id_document = id_document;
        this.id_employee = id_employee;
        this.name = name;
        this.date = cal;
        this.id_doctype = id_doctype;
        this.doc = doc;
    }

    public int getId(){ return this.id_document; }
    public int getEmployeeId(){ return this.id_employee; }
    public String getName(){ return this.name; }
    public Calendar getDate(){ return this.date; }
    public int getId_doctype(){ return this.id_doctype; }
    public Object getDoc(){ return this.doc; }

    public void setId(int id_document) { this.id_document = id_document; }
    public void setEmployeeId(int id_employee) { this.id_employee = id_employee; }
    public void setName(int id_employee){ this.id_employee = id_employee; }
    public void setDate(Calendar cal){ this.date = cal; }
    public void setId_doctype(int id_doctype) { this.id_doctype = id_doctype; }
    public void setDoc(Object doc) { this.doc = doc; }
}
