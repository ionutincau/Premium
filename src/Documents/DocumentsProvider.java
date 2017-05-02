package Documents;

import database.DatabaseConnection;

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

    //insereaza document nou
    public void insertDocument(Document d){
        SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
        String date = formatter.format(d.getDate().getTime());

        String querry = "INSERT INTO `documents`(`id_document`,`id_employee`,`name`,`date`,`id_doctype`,`document`) VALUES (?,?,?,?,?,?);";
        try {
            PreparedStatement pstmt = DatabaseConnection.getConnection().prepareStatement(querry);

            pstmt.setInt(1, d.getId());
            pstmt.setInt(2, d.getEmployeeId());
            pstmt.setString(3, d.getName());
            pstmt.setString(4, date);
            pstmt.setInt(5, d.getId_doctype());
            pstmt.setObject(6, d.getDoc());


            pstmt.executeUpdate();
            pstmt.close();
        }
        catch (Exception e) {
            System.out.println(e);
            e.printStackTrace();
        }
    }

    //actualizeaza Documentul
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
}
