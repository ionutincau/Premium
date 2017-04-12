package Requests;

import java.util.Calendar;

/**
 * Created by Incau Ionut on 03-Apr-17.
 * Contact: ionut.incau@gmail.com
 */

public class Request {
    private int id_request;
    private int id_document;
    private String status;
    private Calendar deadline;
    private Calendar date_approval;

    public Request(int id_request,int id_document,String status,Calendar deadline,Calendar date_approval){
        this.id_request=id_request;
        this.id_document=id_document;
        this.status=status;
        this.deadline=deadline;
        this.date_approval=date_approval;
    }

    public int getId() { return this.id_request; }
    public int getId_document() { return this.id_request; }
    public String getStatus() { return this.status; }
    public Calendar getDeadline() { return this.deadline; }
    public Calendar getDate_approval() { return this.date_approval; }

    public void setId(int id_request) { this.id_request=id_request; }
    public void setId_document(int id_document) { this.id_document=id_document; }
    public void setStatus(String status){ this.status=status; }
    public void setDeadline(Calendar deadline){ this.deadline=deadline; }
    public void setDate_approval(Calendar date_approval){ this.date_approval=date_approval; }



}
