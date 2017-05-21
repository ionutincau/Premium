package DocumentTypes;

import Employees.Employee;
import JobsHistory.JobsHistoryController;
import Login.LoginController;

import com.itextpdf.text.Chunk;
import com.itextpdf.text.Document;
import com.itextpdf.text.Element;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.pdf.PdfWriter;
import com.itextpdf.text.pdf.draw.LineSeparator;
import com.itextpdf.text.pdf.draw.VerticalPositionMark;

import java.io.FileOutputStream;
import java.io.Serializable;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

/**
 * Created by Aurelian on 5/3/2017.
 */

public class Income implements Serializable {
    private final String company = "Evozon";
    private final String title = "Income Statement";
    private final String documentTitle = "Adeverinta de venit";
    private final String t1 = "Prin prezenta se adevereste ca domnul/doamna ";
    private final String t2 = ", este incadrat(a) in firma ";
    private final String t3 = ", cu contract individual de munca pe perioada determinata/nedeterminata de la data de ";
    private final String t4 = ", in functia de ";
    private final String t5 = ", departamentul ";
    private final String t6 = ", cu un salariu brut, pe ultimele 3 luni, de: ";
    private final String t8 = "Prezenta s-a eliberat pentru a-i servi la ";
    private final String dots = "................................................................";

    private String name;
    private String job;
    private String department;
    private Date startDate;
    private Calendar date;
    private String purpose;

    private Integer salary1 = 0;
    private Integer salary2 = 0;
    private Integer salary3 = 0;
    private String month1;
    private String month2;
    private String month3;

    public Income(String purpose) throws Exception {
        int userID = LoginController.getInstance().getLoggedUser().getId();
        JobsHistoryController jobsHistoryController = new JobsHistoryController();
        List<Integer> salaries = jobsHistoryController.getLastThreeSalaries(userID);
        if (salaries.size() > 0) salary1 = salaries.get(0);
        if (salaries.size() > 1) salary2 = salaries.get(1);
        if (salaries.size() > 2) salary3 = salaries.get(2);

        Employee user = LoginController.getInstance().getLoggedUser();
        name = user.getLast_name() + " " + user.getFirst_name();
        job = user.getJob();
        department = user.getDepartment();
        this.purpose = purpose;

        java.sql.Date sqlDate = jobsHistoryController.getStartDate(userID);
        startDate = new Date(sqlDate.getYear(),sqlDate.getMonth(),sqlDate.getDay());
        date = Calendar.getInstance();

        int monthNumber = Calendar.getInstance().get(Calendar.MONTH);
        String[] monthName = { "Ianuarie", "Februarie", "Martie", "Aprilie", "Mai", "Iunie", "Iulie", "August", "Septembrie", "Octombrie", "Noiembrie", "Decembrie" };

        month1 = monthName[monthNumber - 1];
        month2 = monthName[monthNumber - 2];
        month3 = monthName[monthNumber - 3];
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

            Paragraph para = new Paragraph("\n\t" + t1 + name + t2 + company + t3 + startDate + t4 + job + t5 + department + t6 + "\n\n");
            document.add(para);

            Paragraph monthlySalary = new Paragraph(month1 + dots + salary1 + "\n" + month2 + dots + salary2 + "\n" + month3 + dots + salary3 + "\n");
            document.add(monthlySalary);

            Paragraph fin = new Paragraph("\n" + t8 + purpose + "\n\n");
            document.add(fin);

            String dateFormat = new SimpleDateFormat("dd.MM.yyyy").format(date.getTime());

            Chunk separator = new Chunk(new VerticalPositionMark());
            Paragraph footer1 = new Paragraph("Data: " );
            footer1.add(new Chunk(separator));
            footer1.add("Semnatura");
            document.add(footer1);

            Paragraph footer2 = new Paragraph(dateFormat);
            footer2.add(new Chunk(separator));
            footer2.add(".....................");
            document.add(footer2);
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
    public Calendar getDate() {
        return date;
    }

}
