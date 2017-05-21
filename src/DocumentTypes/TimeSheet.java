package DocumentTypes;

import Employees.Employee;
import Login.LoginController;

import Utils.UtilFunctions;
import com.itextpdf.text.Chunk;
import com.itextpdf.text.Document;
import com.itextpdf.text.Element;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.pdf.PdfWriter;
import com.itextpdf.text.pdf.draw.LineSeparator;

import java.io.FileOutputStream;
import java.io.Serializable;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Calendar;

/**
 * Created by Aurelian on 5/4/2017.
 */

public class TimeSheet implements Serializable {
    private final String company = "Evozon";
    private final String title = "Timesheet Report";
    private final String documentTitle = "Raport ore lucrate";
    private final String t0 = "Compania: ";
    private final String t1 = "Nume: ";
    private final String t2 = "Job: ";
    private final String t3 = "Departament: ";
    private final String t4 = "Ore lucrate in perioada ";

    private String name;
    private String job;
    private String department;
    private String startDate;
    private String endDate;
    private ArrayList<String> time;
    private Calendar date;

    DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd.MM.yyyy");

    public TimeSheet(String startDate, String endDate, ArrayList<String> time) {
        Employee user = LoginController.getInstance().getLoggedUser();
        this.name = user.getLast_name() + " " + user.getFirst_name();
        this.job = user.getJob();
        this.department = user.getDepartment();
        this.startDate = startDate;
        this.endDate = endDate;
        this.time = time;
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
            titleParagraph.setAlignment(Element.ALIGN_CENTER);
            document.add(titleParagraph);
            LineSeparator ls = new LineSeparator();
            document.add(new Chunk(ls));

            Paragraph para = new Paragraph(t0 + company + "\n" +t1 + name + "\n" + t2 + job + "\n" + t3 + department + "\n\n" + t4 + startDate + " - " + endDate + ":\n\n");
            document.add(para);

            Paragraph dailyReport = new Paragraph();

            for (String t: time) {
                dailyReport.add(t + "\n");
            }
            document.add(dailyReport);
        }
        catch (Exception e) {
            e.printStackTrace();
        }
        document.close();
    }

    public String getFilenameName() {
        String filanameDateFormat = new SimpleDateFormat("dd-MM-yyyy-HH-mm-ss").format(date.getTime());
        return title + " - " + name + " - " + filanameDateFormat + ".pdf";
    }
}