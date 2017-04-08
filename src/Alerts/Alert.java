package Alerts;

import java.util.Calendar;
import java.util.GregorianCalendar;

/**
 * Created by Incau Ionut on 03-Apr-17.
 * Contact: ionut.incau@gmail.com
 */
/**
 *
 * Clasa Alert simbolizeaza o notificare ,aceasta fiind formata printr-o relatie M-N in BD
 *
 */
public class Alert {
    private int id_alert;
    private String text;
    private Calendar deadline = new GregorianCalendar(); // date
    private Calendar delivery_date = new GregorianCalendar(); // date
    private String status;

    public Alert (int id_alert,String text, Calendar deadline,Calendar delivery_date,String status) {
        this.id_alert = id_alert;
        this.text=text;
        this.deadline = deadline;
        this.delivery_date = delivery_date;
        this.status = status;
    }

    public int getId_alert() {
        return id_alert;
    }
    public String getText() { return text; };
    public Calendar getDeadline() { return deadline; }
    public Calendar getDelivery_date() { return delivery_date; }
    public String getStatus() { return status; }

    public void setId(int id_alert) {
        this.id_alert = id_alert;
    }
    public void setText(String text){
        this.text=text;
    }
    public void setDeadline(Calendar deadline){
        this.deadline=deadline;
    }
    public void setText(Calendar delivery_date){
        this.delivery_date=delivery_date;
    }





}
