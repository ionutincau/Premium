package Documents;

import java.util.Calendar;

/**
 * Created by SkReaper on 08.Apr.2017.
 */

public class Document {
    private int id_document;
    private String name;
    private Calendar date;
    private int id_doctype;
    private Object doc;

    public Document(int id_document, String name, Calendar cal, int id_doctype, Object doc) {
        this.id_document = id_document;
        this.name = name;
        this.date = date;
        this.id_doctype = id_doctype;
        this.doc = doc;
    }

    public int getId(){ return this.id_document; }
    public String getName(){ return this.name; }
    public Calendar getDate(){ return this.date; }
    public int getId_doctype(){ return this.id_doctype; }
    public Object getDoc(){ return this.doc; }

    public void setId(int id_document) { this.id_document = id_document; }
    public void setName(String name){ this.name = name; }
    public void setDate(){ this.date = date; }
    public void setId_doctype(int id_doctype) { this.id_doctype = id_doctype; }
    public void setDoc(String doc_path) { this.doc = doc_path; }
}
