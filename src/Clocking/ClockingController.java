package Clocking;

import Employees.Employee;
import Login.LoginController;
import Utils.UIAlerts;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;

/**
 * Created by MariusDK on 13.03.2017.
 */

public class ClockingController extends Observable {

    private ArrayList<Clocking> list = new ArrayList<Clocking>();
    private ClockingProvider provider;

    public ClockingController() {
        provider = new ClockingProvider();
    }

    public ArrayList getClocking() {
        Employee selectedUser = LoginController.getInstance().getSelectedUser();
        if (selectedUser != null) list = provider.getClockings(selectedUser.getId());
        return list;
    }

    public int get_status() {
        Calendar now = Calendar.getInstance();
        System.out.print(now.getCalendarType());
        //todo: change calendar tye cu data
        // se verifica doar la utimu, lista e ordonata ?
        if (list.size() == 0) return 1;
        else {
            if (list.get(list.size() - 1).get_date().getCalendarType().equals(now.getCalendarType())) {
                if ((list.get(list.size() - 1).get_hour_break() == 0) && (list.get(list.size() - 1).get_hour_work() == 0) && (list.get(list.size() - 1).get_hour_out() == 0)) {
                    return 2;
                }
                if ((list.get(list.size() - 1).get_hour_break() != 0) && (list.get(list.size() - 1).get_hour_work() == 0) && (list.get(list.size() - 1).get_hour_out() == 0)) {
                    return 3;
                }
                if ((list.get(list.size() - 1).get_hour_break() != 0) && (list.get(list.size() - 1).get_hour_work() != 0) && (list.get(list.size() - 1).get_hour_out() == 0)) {
                    return 4;
                }
            }
            else {
                return 1;
            }
        }
        return 0;
    }

    public void clockin() {
        Clocking c = new Clocking(1, new GregorianCalendar(), getMinutes(Calendar.HOUR_OF_DAY, Calendar.MINUTE), 0, 0, 0);
        provider.insertClocking(c, LoginController.getInstance().getLoggedUser().getId());

        setChanged();
        notifyObservers();
    }

    public void clockbreak() {
        Calendar now = Calendar.getInstance();
        for (int i=0; i<list.size(); i++) {
            if (list.get(i).get_date().getCalendarType().equals(now.getCalendarType())) {
                Clocking current_time = list.get(i);
                current_time.set_hour_break(getMinutes(Calendar.HOUR_OF_DAY, Calendar.MINUTE));
                provider.updateClocking(current_time, LoginController.getInstance().getLoggedUser().getId());
            }
        }

        setChanged();
        notifyObservers();
    }

    public void clockwork() {
        Calendar now = Calendar.getInstance();
        for (int i=0; i < list.size(); i++) {
            if (list.get(i).get_date().getCalendarType().equals(now.getCalendarType())) {
                Clocking current_time = list.get(i);
                current_time.set_hour_work(getMinutes(Calendar.HOUR_OF_DAY, Calendar.MINUTE));
                provider.updateClocking(current_time, LoginController.getInstance().getLoggedUser().getId());
            }
        }

        setChanged();
        notifyObservers();
    }

    public void clockout() {
        Calendar now = Calendar.getInstance();
        for (int i=0; i < list.size(); i++) {
            if (list.get(i).get_date().getCalendarType().equals(now.getCalendarType())) {
                Clocking current_time = list.get(i);
                current_time.set_hour_out(getMinutes(Calendar.HOUR_OF_DAY, Calendar.MINUTE));
                provider.updateClocking(current_time, LoginController.getInstance().getLoggedUser().getId());
            }
        }

        setChanged();
        notifyObservers();
    }

    public void add(String date, String hour_in, String hour_break, String hour_work, String  hour_out) throws ParseException {
        Calendar calendar = formatDate(date);
        int h_in = getMinutes(hour_in);
        int h_break = getMinutes(hour_break);
        int h_work = getMinutes(hour_work);
        int h_out = getMinutes(hour_out);
        Clocking clocking = new Clocking(1, calendar, h_in, h_break, h_work, h_out);

        provider.insertClocking(clocking, LoginController.getInstance().getSelectedUser().getId());

        setChanged();
        notifyObservers();
    }

    public void edit(int id, String date, String hour_in, String hour_break, String hour_work, String  hour_out) throws ParseException {
        Calendar calendar = formatDate(date);
        int h_in = getMinutes(hour_in);
        int h_break = getMinutes(hour_break);
        int h_work = getMinutes(hour_work);
        int h_out = getMinutes(hour_out);
        Clocking clocking = new Clocking(id, calendar, h_in, h_break, h_work, h_out);

        provider.updateClocking(clocking, LoginController.getInstance().getSelectedUser().getId());

        setChanged();
        notifyObservers();
    }

    public void delete(Clocking clocking) {
        provider.deleteClocking(clocking);

        setChanged();
        notifyObservers();
    }

    public  ArrayList search(String Sdate1, String Sdate2) {
        if (Sdate1.equals("") && Sdate2.equals("")) return list;
        ArrayList<Clocking> FilterList = new ArrayList<Clocking>();
        DateFormat format = new SimpleDateFormat("dd.MM.yyyy", Locale.ENGLISH);

        try {
            Date date1 = format.parse(Sdate1);
            Date date2 = format.parse(Sdate2);
            for (Clocking o : list) {
                if ((o.get_date().getTime().after(date1)) && (o.get_date().getTime().before(date2))) {
                    FilterList.add(o);
                }
            }
            return FilterList;
        }
        catch (ParseException e) {
            e.printStackTrace();
        }

        return list;
    }

    private Calendar formatDate(String d) throws ParseException {
        Calendar cal = new GregorianCalendar();
        DateFormat df = new SimpleDateFormat("dd.MM.yyyy");
        Date date = df.parse(d);
        cal.setTime(date);
        return cal;
    }

    private int getMinutes(String time) {
        String splitted[] = time.split(":");
        return getMinutes(Integer.parseInt(splitted[0]), Integer.parseInt(splitted[1]));
    }

    private int getMinutes(int hours, int minutes) {
        return hours * 60 + minutes;
    }
}
