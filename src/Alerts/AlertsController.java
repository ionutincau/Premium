package Alerts;

import Employees.Employee;
import Login.LoginController;
import Utils.*;

import java.text.ParseException;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Observable;

/**
 * Created by Incau Ionut on 03-Apr-17.
 * Contact: ionut.incau@gmail.com
 */

public class AlertsController extends Observable {
    private ArrayList<Alert> list = new ArrayList();
    private AlertsProvider provider;

    public AlertsController() {
        provider = new AlertsProvider();
    }

    public ArrayList getAlerts(){
        Employee selectedUser = LoginController.getInstance().getSelectedUser();
        if (selectedUser != null) list = provider.getAlerts(selectedUser.getId());
        return list;
    }

    public ArrayList getAllAlerts() {
        list = provider.getAllAlerts();
        return list;
    }

    public void add(String name, boolean add_all, String text, String deadline, Calendar calendar2, String status) throws ParseException {
        Calendar calendar1 = UtilFunctions.formatDate(deadline);
        int id_alert = provider.getAvaliableId();
        int id_user = -1;
        if (add_all == false) {
            id_user = provider.id_user(name);
        }
        Alert alert = new Alert(id_alert, LoginController.getInstance().getSelectedUser().getId(), text, calendar1, calendar2, status);
        list.add(alert);
        provider.insertNotification(alert, id_user);

        setChanged();
        notifyObservers();
    }

    public void removeAlert(Alert a) {
        list.remove(a);
        provider.deleteNotification(a);
        setChanged();
        notifyObservers();
    }

    public ArrayList<String> getEmployeeName() {
        return provider.getEmployeeName();
    }

    public ArrayList unreadAlerts(){
        return provider.unreadAlerts(LoginController.getInstance().getLoggedUser().getId());
    }

    public void setRead(Alert alert) {
        provider.setRead(LoginController.getInstance().getLoggedUser().getId(), alert.getId_alert());
    }
}
