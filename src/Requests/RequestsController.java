package Requests;

import Documents.DocumentsController;

/**
 * Created by Incau Ionut on 03-Apr-17.
 * Contact: ionut.incau@gmail.com
 */

public class RequestsController {

    private RequestsProvider provider;
    private DocumentsController documentsController;

    public RequestsController() {
        this.provider = new RequestsProvider();
        this.documentsController = new DocumentsController();
    }

    public void addVacationRequest(String start_date, String end_date) {
        int id_document = documentsController.addVacationRequest(start_date, end_date);
        Request request = new Request(RequestsProvider.getAvaliableId(), id_document, "pending", null);
        provider.insertRequest(request);
    }
}


