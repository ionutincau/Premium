package Documents;

import database.DatabaseConnection;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;

/**
 * Created by ASUS on 08.Apr.2017.
 */

public class DocumentsProvider {

    public ArrayList<Document> getDocuments(int id_employee) {
        ArrayList<Document> list = new ArrayList();
        try {
            String querry = "SELECT * FROM `documents` WHERE `id_employee`=?";
            PreparedStatement pstmt = DatabaseConnection.getConnection().prepareStatement(querry);
            pstmt.setInt(1, id_employee);
            ResultSet result = pstmt.executeQuery();

            while (result.next()) {
                int id_document = result.getInt("id_document");
                String name = result.getString("name");
                Date date = result.getDate("date");
                Calendar cal = new GregorianCalendar();
                cal.setTime(date);
                int id_doctype = result.getInt("id_doctype");

                byte[] st = (byte[]) result.getObject("document");
                ByteArrayInputStream baip = new ByteArrayInputStream(st);
                ObjectInputStream ois = new ObjectInputStream(baip);
                Object emp = ois.readObject();

                Document d = new Document(id_document, id_employee, name, cal, id_doctype, emp);
                list.add(0, d);
            }
            result.close();
            pstmt.close();
        }
        catch (Exception e) {
            System.out.println(e);
            e.printStackTrace();
        }
        return list;
    }

    public static int getAvaliableId(){
        int id = 0;
        try{
            String querry = "SELECT MAX(`id_document`) FROM `documents`";
            ResultSet result = DatabaseConnection.getStatement().executeQuery(querry);
            if (result.next()) {
                id = result.getInt(1);
            }
        }
        catch (Exception e) {
            System.out.println(e);
            e.printStackTrace();
        }
        return (id+1);
    }

    public void insertDocument(Document d){
        SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
        String date = formatter.format(d.getDate().getTime());

        String querry = "INSERT INTO `documents`(`id_document`,`id_employee`,`name`,`date`,`id_doctype`,`document`) VALUES (?,?,?,?,?,?);";
        try {
            ByteArrayOutputStream baos = new ByteArrayOutputStream();
            ObjectOutputStream oos = new ObjectOutputStream(baos);
            oos.writeObject(d.getDoc());
            byte[] objectAsBytes = baos.toByteArray();
            ByteArrayInputStream bais = new ByteArrayInputStream(objectAsBytes);

            PreparedStatement pstmt = DatabaseConnection.getConnection().prepareStatement(querry);

            pstmt.setInt(1, d.getId());
            pstmt.setInt(2, d.getEmployeeId());
            pstmt.setString(3, d.getName());
            pstmt.setString(4, date);
            pstmt.setInt(5, d.getId_doctype());
            pstmt.setBinaryStream(6, bais, objectAsBytes.length);

            pstmt.executeUpdate();
            pstmt.close();
        }
        catch (Exception e) {
            System.out.println(e);
            e.printStackTrace();
        }
    }

    public void updateDocument(Document d) {
        String querry = "UPDATE `documents` SET `name`= ? ,`date`= ? ,`id_doctype`= ? ,`document`= ?  WHERE `id_employee`= ?;";
        SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
        String date = formatter.format(d.getDate().getTime());

        try{
            PreparedStatement pstmt = DatabaseConnection.getConnection().prepareStatement(querry);

            pstmt.setString(1, d.getName());
            pstmt.setString(2, date);
            pstmt.setInt(3, d.getId_doctype());
            pstmt.setObject(4, d.getDoc());
            pstmt.setInt(5, d.getEmployeeId());

            pstmt.executeUpdate();
            pstmt.close();

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

    public static Document getDocumentById(int id_document) {
        Document document = null;
        try {
            String querry = "SELECT * FROM `documents` WHERE `id_document`=" + id_document + ";";
            ResultSet result = DatabaseConnection.getStatement().executeQuery(querry);
            while (result.next()) {
                int id_employee = result.getInt("id_employee");
                String name = result.getString("name");
                Date date = result.getDate("date");
                Calendar cal = new GregorianCalendar();
                cal.setTime(date);
                int id_doctype = result.getInt("id_doctype");

                byte[] st = (byte[]) result.getObject("document");
                ByteArrayInputStream baip = new ByteArrayInputStream(st);
                ObjectInputStream ois = new ObjectInputStream(baip);
                Object emp = ois.readObject();

                document = new Document(id_document, id_employee, name, cal, id_doctype, emp);
            }
            result.close();
        }
        catch (Exception e) {
            e.printStackTrace();
        }
        return document;
    }
}
