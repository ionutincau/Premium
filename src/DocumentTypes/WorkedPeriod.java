package DocumentTypes;

import Employees.Employee;
import Login.LoginController;
import Utils.UtilFunctions;
import com.itextpdf.text.Chunk;
import com.itextpdf.text.Document;
import com.itextpdf.text.Element;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.pdf.PdfPCell;
import com.itextpdf.text.pdf.PdfPTable;
import com.itextpdf.text.pdf.PdfWriter;
import com.itextpdf.text.pdf.draw.LineSeparator;
import com.itextpdf.text.pdf.draw.VerticalPositionMark;

import java.io.FileOutputStream;
import java.text.SimpleDateFormat;
import java.util.Calendar;

/**
 * Created by Aurelian on 5/5/2017.
 */

public class WorkedPeriod {

    private final String company = "Evozon";
    private final String title = "Worked Period Statement";
    private final String documentTitle = "Adeverinta de vechime in munca";
    private final String t1 = ", a fost angajat al companiei ";
    private final String t2 = ", job ";
    private final String t3 = ", departament ";
    private final String t4 = "Prin prezenta se atesta faptul ca domnul/doamna ";
    private final String t5 = ", CNP ";
    private final String t6 = " in baza contractului individual de munca cu norma intreaga / cu timp partial de ……………. .ore / zi, incheiat pe durata determinata / nedeterminata, inregistrat la Inspectoratul Teritorial de Munca";
    private final String t7 = " ……………… cu nr. ………../…………., in functia / meseria de ";
    private final String t8 = " Pe durata executarii contractului individual de munca au intervenit urmatoarele mutatii (incheierea, modificarea, suspendarea si incetarea contractului individual de munca): \n\n";

    private String name;
    private String job;
    private String department;
    private String CNP;
    private Calendar date = Calendar.getInstance();

    public WorkedPeriod() {
        Employee user = LoginController.getInstance().getLoggedUser();
        name = user.getLast_name() + " " + user.getFirst_name();
        job = user.getJob();
        department = user.getDepartment();
        CNP = user.getCnp();
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

            Paragraph para = new Paragraph(t4 + name + t5 + CNP + t3 + department + t2 + job + t1 + company + t6 + t7 + job + ".");
            document.add(para);

            PdfPTable table = new PdfPTable(6);
            table.setWidthPercentage(100);
            table.setWidths(new float[] { 0.7f, 3, 2, 3, 2, 4});
            table.setSpacingBefore(25);
            table.setSpacingAfter(25);

            table.addCell("Nr. crt.");
            table.addCell("Mutatia intervenita");
            table.addCell("Data");
            table.addCell("Departamentul/\nFunctia");
            table.addCell("Salariul de baza,\n" + "inclusiv sporurile");
            table.addCell("Nr.si data actului pe baza caruia se face inscrierea si temeiul legal");

            // example row
            table.addCell("1");
            table.addCell("Marire salariu");
            table.addCell("12.02.2017");
            table.addCell(department + "/\n" + job);
            table.addCell("2500");
            table.addCell("157/12.02.2017");

            for (int rowNr = 0 ; rowNr < table.getRows().size(); rowNr++) {
                for (PdfPCell tc: table.getRow(rowNr).getCells()) {
                    tc.setVerticalAlignment(Element.ALIGN_CENTER);
                    tc.setHorizontalAlignment(Element.ALIGN_CENTER);
                }
            }
            document.add(table);

            String dateFormat = new SimpleDateFormat("dd.MM.yyyy").format(date.getTime());
            Chunk separator = new Chunk(new VerticalPositionMark());
            Paragraph footer1 = new Paragraph("Reprezentant legal: " );
            footer1.add(new Chunk(separator));
            footer1.add("Intocmit:");
            document.add(footer1);

            Paragraph footer2 = new Paragraph("...............................");
            footer2.add(new Chunk(separator));
            footer2.add(dateFormat);
            document.add(footer2);
        }
        catch (Exception e) {
            UtilFunctions.showInfo("Adeverinta nu a putut fi creata!");
        }
        document.close();
    }

    public String getFilenameName() {
        String filanameDateFormat = new SimpleDateFormat("dd-MM-yyyy-HH-mm-ss").format(date.getTime());
        return title + " - " + name + " - " + filanameDateFormat + ".pdf";
    }
}