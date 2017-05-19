package Requests;

import java.util.Calendar;

/**
 * Created by MariusDK on 19.05.2017.
 */
public class RequestsUser extends Request{
    private String last_name;
    private String first_name;

    public RequestsUser(int id_request, int id_document, String status, Calendar date_approval,String last_name,String first_name) {
        super(id_request, id_document, status, date_approval);
        this.last_name=last_name;
        this.first_name=first_name;
    }

    @Override
    public String toString() {
        return "RequestsUser{" +
                "last_name='" + last_name + '\'' +
                ", first_name='" + first_name + '\'' +
                '}';
    }

    public String getLast_name() {
        return last_name;
    }

    public void setLast_name(String last_name) {
        this.last_name = last_name;
    }

    public String getFirst_name() {
        return first_name;
    }

    public void setFirst_name(String first_name) {
        this.first_name = first_name;
    }
}
