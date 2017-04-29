package Documents;

import DocumentTypes.Vacation;

/**
 * Created by Incau Ionut on 29-Apr-17.
 * Contact: ionut.incau@gmail.com
 */

public class DocumentsController {

    private DocumentsProvider provider;

    public DocumentsController() {
        this.provider = new DocumentsProvider();
        Vacation v = new Vacation("x", "y");
        try {
            v.generatePDF();
        }
        catch (Exception e) {
            e.printStackTrace();
        }
    }
}
