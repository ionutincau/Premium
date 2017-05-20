package Alerts;

import Utils.UtilFunctions;

import java.util.Calendar;
import java.util.GregorianCalendar;

/**
 * Created by Incau Ionut on 03-Apr-17.
 * Contact: ionut.incau@gmail.com
 */

public class Alert {
    private int id_alert;
    private int id_employee;
    private String text;
    private Calendar deadline = new GregorianCalendar();
    private Calendar delivery_date = new GregorianCalendar();
    private String status;

    public Alert (int id_alert, int id_employee, String text, Calendar deadline, Calendar delivery_date, String status) {
        this.id_alert = id_alert;
        this.id_employee = id_employee;
        this.text = text;
        this.deadline = deadline;
        this.delivery_date = delivery_date;
        this.status = status;
    }

    public int getId_alert() {
        return id_alert;
    }

    public int getId_employee() {
        return id_employee;
    }

    public String getText() {
        return text;
    }

    public Calendar getDeadline() {
        return deadline;
    }

    public Calendar getDelivery_date() {
        return delivery_date;
    }

    public String getStatus() {
        return status;
    }

    public void setId_alert(int id_alert) {
        this.id_alert = id_alert;
    }

    public void setId_employee(int id_employee) {
        this.id_employee = id_employee;
    }

    public void setText(String text) {
        this.text = text;
    }

    public void setDeadline(Calendar deadline) {
        this.deadline = deadline;
    }

    public void setDelivery_date(Calendar delivery_date) {
        this.delivery_date = delivery_date;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    @Override
    public String toString() {
        return  " deadline: " + UtilFunctions.get_date_format(deadline) + " mesaj: " + text;
    }
}
