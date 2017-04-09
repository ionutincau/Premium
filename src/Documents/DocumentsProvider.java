package Documents;



import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;

/**
 * Created by ASUS on 08.Apr.2017.
 */
public class DocumentsProvider {
    private Connection con;
    private Statement statement;
    private ResultSet result;

    public DocumentsProvider() {
        connect();
    }

    public void connect() {
        try {
            Class.forName("com.mysql.jdbc.Driver");
            //con = DriverManager.getConnection("jdbc:mysql://localhost:3306/premium", "root", "");
            con = DriverManager.getConnection("jdbc:mysql://sql11.freesqldatabase.com:3306/sql11164406", "sql11164406", "ytcWkGRh58");
            statement = con.createStatement();
        }
        catch (Exception e) {
            System.out.println("Database connection error");
            System.out.println("Check internet connection");
        }
    }

    public ArrayList getDocuments(int id_employee) {
        ArrayList<Document> list = new ArrayList<Document>();
        try {
            String querry = "SELECT * FROM `documents` WHERE `id_employee`=" + id_employee;
            result = statement.executeQuery(querry);
            while (result.next()) {
                int id_document = result.getInt("id_document");
                String name=result.getString("name");
                Date date = result.getDate("date");
                Calendar cal = new GregorianCalendar();
                cal.setTime(date);
                int id_doctype = result.getInt("id_doctype");
                String doc_path= result.getString("document_path");
                Document doc = new Document(id_document, name,cal,id_doctype,doc_path);
                list.add(0, doc);
            }
        }
        catch (Exception e) {
            System.out.println(e);
            e.printStackTrace();
        }
        return list;
    }
    //insereaza document nou dupa id_employee
    public void insertDocument(Document d,int id_employee){
        try {
            String querry = "INSERT INTO `documents`(`id_employee`,`name`,`date`,`id_doctype`,`document_path`) VALUES (" + id_employee + ","+d.getName()+"," + d.getDate() + "," + d.getId_doctype() + "," + d.getDoc_path() + ");";
            statement.executeUpdate(querry);
        } catch (Exception e) {
            System.out.println(e);
            e.printStackTrace();

        }
    }

    //actualizeaza Documentul dupa id_employee
    public void updateDocument(Document d,int id_employee)
    {

        try {
            String querry = "UPDATE `documents` SET `name`="+d.getName()+",`date`="+d.getDate()+",`id_doctype`="+d.getId_doctype()+",`document_path`="+d.getDoc_path()+" WHERE `id_employee`="+id_employee+";";
            statement.executeUpdate(querry);
        }
        catch(Exception e){
            System.out.println(e);
            e.printStackTrace();

        }
    }
    public void deleteDocument(Document d)
    {
        try {
            String querry="DELETE FROM `documents` WRITE `id_document`="+d.getId()+";";
            statement.executeUpdate(querry);
        }
        catch(Exception e){
            System.out.print(e);
            e.printStackTrace();
        }
    }
}
