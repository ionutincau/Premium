package DocumentTypes;

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
import java.util.Calendar;

/**
 * Created by Aurelian on 5/19/2017.
 */

public class DepartmentTimeSheet implements Serializable {

    private final String company = "Evozon";
    private final String title = "Department Worked Hours";
    private final String documentTitle = "Raport ore lucrate pe departament";
    private final String t1 = "Compania: ";
    private final String t2 = "Departament: ";
    private Calendar date;
    private final String department;

    public DepartmentTimeSheet() {
        department = " manele";
        date = Calendar.getInstance();
    }

    public void generatePDF() {
        Document document = new Document();
        document.addAuthor("Premium");
        document.addTitle(title);

        try {
            PdfWriter.getInstance(document, new FileOutputStream(getFilenameName()));
            document.open();

            Paragraph titleParagraph = new Paragraph(documentTitle);
            titleParagraph.setAlignment(Element.ALIGN_CENTER);
            document.add(titleParagraph);
            LineSeparator ls = new LineSeparator();
            document.add(new Chunk(ls));

            Paragraph para = new Paragraph(t1 + company + "\n" + t2 + department + "\n");
            document.add(para);

            para = new Paragraph("Costel ............................ 150" );
            document.add(para);

            para = new Paragraph("Gigel ............................ 148" );
            document.add(para);

            para = new Paragraph("Gonzales ............................ 162" );
            document.add(para);
            // TODO add a new paragraph for each user from the department


        }
        catch (Exception e) {
            e.printStackTrace();
        }
        document.close();
    }

    public String getFilenameName() {
        String filanameDateFormat = new SimpleDateFormat("dd-MM-yyyy-HH-mm-ss").format(date.getTime());
        return title + " - " + " - " + filanameDateFormat + ".pdf";
    }
}
