package Documents;

import DocumentTypes.Income;
import DocumentTypes.Vacation;
import DocumentTypes.WorkedPeriod;
import Login.LoginController;
import com.sun.xml.internal.ws.api.streaming.XMLStreamReaderFactory;

import java.util.ArrayList;

/**
 * Created by Incau Ionut on 29-Apr-17.
 * Contact: ionut.incau@gmail.com
 */

public class DocumentsController {

    private DocumentsProvider provider;

    public DocumentsController() {
        this.provider = new DocumentsProvider();
    }

    public int addVacationRequest(String start_date, String end_date) {
        int id_document = DocumentsProvider.getAvaliableId();
        Vacation v = new Vacation(start_date, end_date);
        Document d = new Document(id_document, LoginController.getInstance().getLoggedUser().getId(), v.getFilenameName(), v.getDate(), 1, v);
        provider.insertDocument(d);

        // todo: rem this
        ArrayList<Document> list = provider.getDocuments(LoginController.getInstance().getLoggedUser().getId());
        for (Document doc : list) {
            try {
                if (doc.getId() == 1) {
                    System.out.println("doc: " + doc.getName());
                    Object o = doc.getDoc();
                    Vacation vac = (Vacation) o;
                    vac.generatePDF();
                }
            }
            catch (Exception e) {
                e.printStackTrace();
            }
        }

        return id_document;
    }

    // TODO: 19.05.2017  
    public int addWorkedPeriodRequest() {
        int id_document = DocumentsProvider.getAvaliableId();
        WorkedPeriod v = new WorkedPeriod();
        Document d = new Document(id_document, LoginController.getInstance().getLoggedUser().getId(), v.getFilenameName(), v.getDate(), 1, v);
        provider.insertDocument(d);

        // todo: rem this
        ArrayList<Document> list = provider.getDocuments(LoginController.getInstance().getLoggedUser().getId());
        for (Document doc : list) {
            try {
                if (doc.getId() == 1) {
                    System.out.println("doc: " + doc.getName());
                    Object o = doc.getDoc();
                    WorkedPeriod vac = (WorkedPeriod) o;
                    vac.generatePDF();
                }
            }
            catch (Exception e) {
                e.printStackTrace();
            }
        }

        return id_document;
    }

    // TODO: 19.05.2017  
    public int addIncomeRequest(String value) {
        int id_document = DocumentsProvider.getAvaliableId();
        Income v = new Income(value);
        Document d = new Document(id_document, LoginController.getInstance().getLoggedUser().getId(), v.getFilenameName(), v.getDate(), 1, v);
        provider.insertDocument(d);

        // todo: rem this
        ArrayList<Document> list = provider.getDocuments(LoginController.getInstance().getLoggedUser().getId());
        for (Document doc : list) {
            try {
                if (doc.getId() == 1) {
                    System.out.println("doc: " + doc.getName());
                    Object o = doc.getDoc();
                    Income vac = (Income) o;
                    vac.generatePDF();
                }
            }
            catch (Exception e) {
                e.printStackTrace();
            }
        }

        return id_document;
    }

}
