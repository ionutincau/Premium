package Documents;

import DocumentTypes.Vacation;
import Login.LoginController;

import java.util.ArrayList;

/**
 * Created by Incau Ionut on 29-Apr-17.
 * Contact: ionut.incau@gmail.com
 */

public class DocumentsController {

    private DocumentsProvider provider;

    public DocumentsController() {
        this.provider = new DocumentsProvider();
        Vacation v = new Vacation("x", "y");
        Document d = new Document(1, LoginController.getInstance().getLoggedUser().getId(), v.getFilenameName(), v.getDate(), 1, v);
        provider.insertDocument(d, LoginController.getInstance().getLoggedUser().getId());
        try {
            v.generatePDF();
        }
        catch (Exception e) {
            e.printStackTrace();
        }
        ArrayList<Document> list = provider.getDocuments(LoginController.getInstance().getLoggedUser().getId());
        for (Document doc : list) {
            try {
                if (doc.getId() == 1) ((Vacation) doc.getDoc()).generatePDF();
            }
            catch (Exception e) {
                e.printStackTrace();
            }
        }
    }
}
