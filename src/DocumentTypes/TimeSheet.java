package DocumentTypes;

import Employees.Employee;
import Login.LoginController;

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
    private final String t4 = "Raportul zilnic al orelor lucrate in perioada ";

    private String name;
    private String job;
    private String department;
    private Integer salary;
    private String startDate;
    private String endDate;
    private Calendar date;

    // TODO replace lines below with data selected in GUI
    LocalDate start = LocalDate.parse("2017-02-28");
    LocalDate end = LocalDate.parse("2017-03-21");

    DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd.MM.yyyy");

    public TimeSheet() {
        Employee user = LoginController.getInstance().getLoggedUser();
        name = user.getLast_name() + " " + user.getFirst_name();
        job = user.getJob();
        department = user.getDepartment();


        // TODO replace hard-coded variables below
        salary = 1950;
        startDate = new SimpleDateFormat("dd.MM.yyyy").format(date.getTime());
        date.set(2017, 2, 27);
        endDate = new SimpleDateFormat("dd.MM.yyyy").format(date.getTime());
    }

    public void generatePDF() throws Exception {
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

            Paragraph para = new Paragraph(t0 + company + "\n" +t1 + name + "\n" + t2 + job + "\n" + t3 + department + "\n" + t4 + start.format(formatter) + " - " + end.format(formatter) + ":\n");
            document.add(para);

            Paragraph dailyReport = new Paragraph();

            for (LocalDate date = start; !start.isAfter(end) && !date.isAfter(end); date = date.plusDays(1)) {
                dailyReport.add(date.format(formatter) + " - 8:00" + "\n");
                System.out.println(date);
            }
            document.add(dailyReport);
        } catch (Exception e) {
            throw new Exception("Cererea nu a putut fi creata");
        }
        document.close();
    }

    public String getFilenameName() {
        String filanameDateFormat = new SimpleDateFormat("dd-MM-yyyy-HH-mm-ss").format(date.getTime());
        return title + " - " + name + " - " + filanameDateFormat + ".pdf";
    }
}