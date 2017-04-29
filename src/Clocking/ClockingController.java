package Clocking;

import Employees.Employee;
import Login.LoginController;

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
        Collections.sort(list, Collections.reverseOrder());
        return list;
    }

    public int get_status() {
        String logged_user = LoginController.getInstance().getLoggedUser().getUsername();
        String selected_user = LoginController.getInstance().getSelectedUser().getUsername();
        if (!logged_user.equals(selected_user)) return 0;

        Date date = new Date();
        Calendar now = Calendar.getInstance();
        now.setTime(date);
        int year = now.get(Calendar.YEAR);
        int month = now.get(Calendar.MONTH);
        int day = now.get(Calendar.DAY_OF_MONTH);
        if (list.size() == 0) return 1;
        else {
            if ((year==list.get(0).get_date().get(Calendar.YEAR))&&(month==list.get(0).get_date().get(Calendar.MONTH))&&(day==list.get(0).get_date().get(Calendar.DAY_OF_MONTH))) {

                if ((list.get(0).get_hour_break() == 0) && (list.get(0).get_hour_work() == 0) && (list.get(0).get_hour_out() == 0)) {
                    return 2;
                }
                if ((list.get(0).get_hour_break() != 0) && (list.get(0).get_hour_work() == 0) && (list.get(0).get_hour_out() == 0)) {
                    return 3;
                }
                if ((list.get(0).get_hour_break() != 0) && (list.get(0).get_hour_work() != 0) && (list.get(0).get_hour_out() == 0)) {
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
        int id = provider.getAvaliableId();
        Clocking c = new Clocking(id, new GregorianCalendar(), getMinutes(Calendar.HOUR_OF_DAY, Calendar.MINUTE), 0, 0, 0);
        provider.insertClocking(c, LoginController.getInstance().getLoggedUser().getId());
        list.add(c);
        Collections.sort(list, Collections.reverseOrder());
        setChanged();
        notifyObservers();
    }

    public void clockbreak() {
        Clocking current_time = list.get(0);
        current_time.set_hour_break(getMinutes(Calendar.HOUR_OF_DAY, Calendar.MINUTE));
        provider.updateClocking(current_time, LoginController.getInstance().getLoggedUser().getId());
        setChanged();
        notifyObservers();
    }

    public void clockwork() {
        Clocking current_time = list.get(0);
        current_time.set_hour_work(getMinutes(Calendar.HOUR_OF_DAY, Calendar.MINUTE));
        provider.updateClocking(current_time, LoginController.getInstance().getLoggedUser().getId());
        setChanged();
        notifyObservers();
    }

    public void clockout() {
        Clocking current_time = list.get(0);
        current_time.set_hour_out(getMinutes(Calendar.HOUR_OF_DAY, Calendar.MINUTE));
        provider.updateClocking(current_time, LoginController.getInstance().getLoggedUser().getId());
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

        list.add(clocking);
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

        for (int i = 0; i < list.size(); i++) {
            if (list.get(i).getId() == id) list.set(i, clocking);
        }
        provider.updateClocking(clocking, LoginController.getInstance().getSelectedUser().getId());

        setChanged();
        notifyObservers();
    }

    public void delete(Clocking clocking) {
        list.remove(clocking);
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
