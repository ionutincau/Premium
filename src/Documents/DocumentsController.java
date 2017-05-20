package Documents;

import DocumentTypes.Income;
import DocumentTypes.Vacation;
import DocumentTypes.WorkedPeriod;
import Login.LoginController;

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
        return id_document;
    }

    public int addWorkedPeriodRequest() {
        int id_document = DocumentsProvider.getAvaliableId();
        WorkedPeriod v = new WorkedPeriod();
        Document d = new Document(id_document, LoginController.getInstance().getLoggedUser().getId(), v.getFilenameName(), v.getDate(), 2, v);
        provider.insertDocument(d);
        return id_document;
    }

    public int addIncomeRequest(String value) {
        int id_document = DocumentsProvider.getAvaliableId();
        Income v = new Income(value);
        Document d = new Document(id_document, LoginController.getInstance().getLoggedUser().getId(), v.getFilenameName(), v.getDate(), 3, v);
        provider.insertDocument(d);
        return id_document;
    }
}
