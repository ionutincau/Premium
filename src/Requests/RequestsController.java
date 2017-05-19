package Requests;

import Documents.Document;
import Documents.DocumentsController;
import Documents.DocumentsProvider;
import Login.LoginController;
import Utils.UtilFunctions;

import java.awt.*;
import java.lang.reflect.Array;
import java.util.ArrayList;

/**
 * Created by Incau Ionut on 03-Apr-17.
 * Contact: ionut.incau@gmail.com
 */

public class RequestsController {

    private RequestsProvider provider;
    private DocumentsController documentsController;
    private ArrayList<String> requestType;
    private ArrayList<Request> requests;

    public ArrayList getRequestsListForUser()
    {
        requests=provider.getRequestsList(LoginController.getInstance().getLoggedUser().getId());
        return requests;
    }
    public ArrayList getRequestType()
    {
        requestType=new ArrayList<>();
        requestType.add("Adeverinta de vechime in munca");
        requestType.add("Adeverinta de venit");
        requestType.add("Cerere de concediu");
        return requestType;
    }
    public RequestsController() {
        this.provider = new RequestsProvider();
        this.documentsController = new DocumentsController();
    }

    public void addVacationRequest(String start_date, String end_date) {
        int id_document = documentsController.addVacationRequest(start_date, end_date);
        Request request = new Request(RequestsProvider.getAvaliableId(), id_document, "pending", null);
        provider.insertRequest(request);
    }
    public void saveRequestUserAndDoc(String docType)
    {
        int id_docType=provider.getDocTypeIdbyName(docType);
        int id_document=0;
        if (docType=="Adeverinta de vechime in munca")
        {
            id_document = documentsController.addWorkedPeriodRequest();
        }
        if (docType=="Adeverinta de venit")
        {
            id_document = documentsController.addIncomeRequest(UtilFunctions.dialog("Prezenta serveste la: "));
        }
        if (docType=="Cerere de concediu")
        {
            id_document = documentsController.addVacationRequest(UtilFunctions.dialog("Data inceput: "), UtilFunctions.dialog("Data sfarsit: "));
        }
        Request request = new Request(RequestsProvider.getAvaliableId(), id_document, "pending", null);
        provider.insertRequest(request);
        requests.add(request);
    }

}


