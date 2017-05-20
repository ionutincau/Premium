package Requests;

import Documents.Document;
import Documents.DocumentsController;
import Documents.DocumentsProvider;
import Login.LoginController;
import Utils.UtilFunctions;

import java.awt.*;
import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.Observable;

/**
 * Created by Incau Ionut on 03-Apr-17.
 * Contact: ionut.incau@gmail.com
 */

public class RequestsController extends Observable {

    private RequestsProvider provider;
    private DocumentsController documentsController;
    private ArrayList<String> requestType;
    private ArrayList<Request> requests;
    private ArrayList<RequestsUser> requestsHrList;
    private ArrayList<RequestsUser> AllRequests;

    public ArrayList getRequestsListForAdmin()
    {
        AllRequests=provider.getAllRequestsList();
        return AllRequests;
    }
    public int getMaxAcc()
    {

        return AllRequests.size();

    }
    public int getNrRes()
    {
        return provider.getDenNum();
    }
    public int getNrAcc()
    {

        return provider.getAccNum();

    }
    public int getNrPending()
    {

        return provider.getPendNum();
    }

    public ArrayList getRequestsListForUser() {
        requests = provider.getRequestsList(LoginController.getInstance().getLoggedUser().getId());
        return requests;
    }

    public ArrayList getRequestsListForHR()
    {
        requestsHrList=provider.getRequestsListOnlyPending();
        return requestsHrList;
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

    public void saveRequestUserAndDoc(String docType) {
        int id_document = 0;
        if (docType.equals("Adeverinta de vechime in munca")) {
            id_document = documentsController.addWorkedPeriodRequest();
        }
        if (docType.equals("Adeverinta de venit")) {
            id_document = documentsController.addIncomeRequest(UtilFunctions.dialog("Prezenta serveste la: "));
        }
        if (docType.equals("Cerere de concediu")) {
            id_document = documentsController.addVacationRequest(UtilFunctions.dialog("Data inceput: "), UtilFunctions.dialog("Data sfarsit: "));
        }
        Request request = new Request(RequestsProvider.getAvaliableId(), id_document, "pending", null);
        provider.insertRequest(request);
        requests.add(request);

        setChanged();
        notifyObservers();
    }

    public void updateRequests(RequestsUser requestsUser,String status)
    {
        Calendar cal=new GregorianCalendar();
        requestsUser.setDate_approval(cal);
        requestsUser.setStatus(status);
        Request request=new Request(requestsUser.getId(),requestsUser.getId_document(),requestsUser.getStatus(),requestsUser.getDate_approval());
        provider.updateRequest(request);
        setChanged();
        notifyObservers();
    }

}


