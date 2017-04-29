package Documents;

import database.DatabaseConnection;

import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;

/**
 * Created by ASUS on 08.Apr.2017.
 */

public class DocumentsProvider {

    public ArrayList getDocuments(int id_employee) {
        ArrayList<Document> list = new ArrayList<Document>();
        try {
            String querry = "SELECT * FROM `documents` WHERE `id_employee`=" + id_employee;
            ResultSet result = DatabaseConnection.getStatement().executeQuery(querry);
            while (result.next()) {
                int id_document = result.getInt("id_document");
                String name=result.getString("name");
                Date date = result.getDate("date");
                Calendar cal = new GregorianCalendar();
                cal.setTime(date);
                int id_doctype = result.getInt("id_doctype");
                Object doc = result.getObject("document");
                Document d = new Document(id_document, id_employee, name, cal, id_doctype, doc);
                list.add(0, d);
            }
        }
        catch (Exception e) {
            System.out.println(e);
            e.printStackTrace();
        }
        return list;
    }

    public static int getAvaliableId(){
        int id=0;
        try{
            String querry = "SELECT MAX(`id_document`) FROM `documents`";
            ResultSet result = DatabaseConnection.getStatement().executeQuery(querry);
            id=result.getInt("id_document");
        }
        catch (Exception e) {
            System.out.println(e);
            e.printStackTrace();
        }
        return (id+1);
    }

    //insereaza document nou dupa id_employee
    public void insertDocument(Document d, int id_employee){
        try {
            String querry = "INSERT INTO `documents`(`id_employee`,`name`,`date`,`id_doctype`,`document`) VALUES (" + id_employee + ","+d.getName()+"," + d.getDate() + "," + d.getId_doctype() + "," + d.getDoc() + ");";
            DatabaseConnection.getStatement().executeUpdate(querry);
        }
        catch (Exception e) {
            System.out.println(e);
            e.printStackTrace();
        }
    }

    //actualizeaza Documentul dupa id_employee
    public void updateDocument(Document d,int id_employee) {
        try {
            String querry = "UPDATE `documents` SET `id_employee`="+d.getEmployeeId()+",`name`="+d.getName()+",`date`="+d.getDate()+",`id_doctype`="+d.getId_doctype()+",`document`="+d.getDoc()+" WHERE `id_employee`="+id_employee+";";
            DatabaseConnection.getStatement().executeUpdate(querry);
        }
        catch(Exception e){
            System.out.println(e);
            e.printStackTrace();
        }
    }

    public void deleteDocument(Document d) {
        try {
            String querry="DELETE FROM `documents` WRITE `id_document`="+d.getId()+";";
            DatabaseConnection.getStatement().executeUpdate(querry);
        }
        catch(Exception e){
            System.out.print(e);
            e.printStackTrace();
        }
    }
}
