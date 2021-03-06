package DocumentTypes;

import Employees.Employee;
import JobsHistory.JobsHistoryController;
import Login.LoginController;

import Utils.UtilFunctions;
import com.itextpdf.text.Chunk;
import com.itextpdf.text.Document;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.pdf.PdfWriter;
import com.itextpdf.text.pdf.draw.LineSeparator;

import java.io.FileOutputStream;
import java.io.Serializable;
import java.text.SimpleDateFormat;
import java.util.Calendar;

/**
 * Created by Incau Ionut on 29-Apr-17.
 * Contact: ionut.incau@gmail.com
 */

public class Vacation implements Serializable {
    private final String company = "Evozon";
    private final String title = "Vacation Request";
    private final String documentTitle = "Cerere pentru concediu de odihna";
    private final String t1 = "Subsemnatul(a) ";
    private final String t2 = ", angajat(a) al ";
    private final String t3 = " in functia de ";
    private final String t4 = ", departamentul ";
    private final String t5 = ", va rog sa-mi aprobati concediul de odihna in perioada cuprinsa intre ";
    private final String t6 = ". \nDin totalul de 21 zile lucratoare la care am dreptul, am mai primit ";

    private String start_vacation;
    private String end_vacation;
    private String name;
    private String job;
    private String department;
    private int used_days;
    private Calendar date;

    public Vacation(String start_vacation, String end_vacation) {
        this.start_vacation = start_vacation;
        this.end_vacation = end_vacation;

        Employee user = LoginController.getInstance().getLoggedUser();
        name = user.getLast_name() + " " + user.getFirst_name();
        job = user.getJob();
        department = user.getDepartment();
        used_days = new JobsHistoryController().getUsedVacationDays(user.getId());
        date = Calendar.getInstance();
    }

    public void generatePDF() {
        Document document = new Document();
        document.addAuthor(name);
        document.addTitle(title);

        try {
            PdfWriter.getInstance(document, new FileOutputStream(getFilenameName()));
            document.open();

            Paragraph titleParagraph = new Paragraph(documentTitle);
            document.add(titleParagraph);
            LineSeparator ls = new LineSeparator();
            document.add(new Chunk(ls));

            Paragraph para = new Paragraph("\n" + t1 + name + t2 + company + t3 + job + t4 + department + t5 + start_vacation + " si " + end_vacation + t6 + used_days + ".");
            document.add(para);

            Paragraph fin = new Paragraph("\nVa multumesc,");
            document.add(fin);

            String dateFormat = new SimpleDateFormat("dd.MM.yyyy").format(date.getTime());
            Paragraph endDate = new Paragraph("\nData: " + dateFormat);
            document.add(endDate);
        }
        catch (Exception e) {
            e.printStackTrace();
        }

        document.close();
    }

    public String getFilenameName() {
        String filanameDateFormat = new SimpleDateFormat("ddMMyyyyHHmmss").format(date.getTime());
        return title + " - " + name + " - " + filanameDateFormat + ".pdf";
    }

    public Calendar getDate() {
        return date;
    }
}
