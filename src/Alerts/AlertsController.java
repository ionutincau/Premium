package Alerts;

import Employees.Employee;
import Login.LoginController;
import Utils.*;

import java.text.ParseException;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collections;
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

    public ArrayList getAlert(){
        Employee selectedUser = LoginController.getInstance().getSelectedUser();
        if (selectedUser != null) list = provider.getAlerts(selectedUser.getId());
        Collections.sort(list, Collections.reverseOrder());
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
}
