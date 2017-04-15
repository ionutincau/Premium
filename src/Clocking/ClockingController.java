package Clocking;

import Login.LoginController;
import javafx.collections.transformation.FilteredList;

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

    public ArrayList getClocking()
    {
        list=provider.getClockings(LoginController.getInstance().getUser().getId());

        return list;
    }
    public int get_status()
    {

        Calendar now = Calendar.getInstance();

        if (list.size() == 0) {
            return 1;
        } else {
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

    public void clockin() throws ParseException {
        Calendar now = Calendar.getInstance();
        int hours = now.get(Calendar.HOUR_OF_DAY);
        int minutes = now.get(Calendar.MINUTE);
        int calc_minutes = hours * 60 + minutes;
        Clocking c=new Clocking(1, new GregorianCalendar(), calc_minutes, 0, 0, 0);
        list.add(c);
        provider.insertClocking(c,LoginController.getInstance().getUser().getId());
        setChanged();
        notifyObservers();
    }

    public void clockbreak()
    {
        Calendar now = Calendar.getInstance();
        int hours = now.get(Calendar.HOUR_OF_DAY);
        int minutes = now.get(Calendar.MINUTE);
        int calc_minutes = hours * 60 + minutes;
        Clocking current_time;

        for (int i=0;i<list.size();i++)
        {
            if (list.get(i).get_date().getCalendarType().equals(now.getCalendarType()))
            {
                current_time=list.get(i);
                current_time.set_hour_break(calc_minutes);
                provider.updateClocking(current_time,LoginController.getInstance().getUser().getId());
                list.set(i,current_time);
            }
        }
        setChanged();
        notifyObservers();
    }
    public  ArrayList searchByName(String name)
    {

        try {
            String[] Name = name.split(" ");
            int id = provider.getIDByName(Name[0], Name[1]);
            return provider.getClockings(id);
        }
    catch (Exception e)
    {}
    return list;
    }
    public  ArrayList search(String Sdate1,String Sdate2)
    {

        ArrayList<Clocking> FilterList=new ArrayList<Clocking>();
        DateFormat format = new SimpleDateFormat("dd.MM.yyyy",Locale.ENGLISH);

            try {
                Date date1 = format.parse(Sdate1);
                Date date2 = format.parse(Sdate2);
                for (Clocking o : list) {
                    if ((o.get_date().getTime().after(date1)) && (o.get_date().getTime().before(date2))) {
                        FilterList.add(o);
                    }
                }
                return FilterList;
            } catch (ParseException e) {
                //e.printStackTrace();
            }

        return list;
    }
    public void clockwork()
    {
        Calendar now = Calendar.getInstance();
        int hours = now.get(Calendar.HOUR_OF_DAY);
        int minutes = now.get(Calendar.MINUTE);
        int calc_minutes = hours * 60 + minutes;
        Clocking current_time;
        for (int i=0;i<list.size();i++)
        {
            if (list.get(i).get_date().getCalendarType().equals(now.getCalendarType()))
            {
                current_time=list.get(i);
                current_time.set_hour_work(calc_minutes);
                provider.updateClocking(current_time,LoginController.getInstance().getUser().getId());
                list.set(i,current_time);
            }
        }
        setChanged();
        notifyObservers();
    }

    public void clockout()
    {
        Calendar now = Calendar.getInstance();
        int hours = now.get(Calendar.HOUR_OF_DAY);
        int minutes = now.get(Calendar.MINUTE);
        int calc_minutes = hours * 60 + minutes;
        Clocking current_time;
        for (int i=0;i<list.size();i++)
        {
            if (list.get(i).get_date().getCalendarType().equals(now.getCalendarType()))
            {
                current_time=list.get(i);
                current_time.set_hour_out(calc_minutes);
                provider.updateClocking(current_time,LoginController.getInstance().getUser().getId());
                list.set(i,current_time);
            }
        }
        setChanged();
        notifyObservers();
    }


    public void edit(Calendar date, int hour_in, int hour_break, int hour_work, int hour_out,int id,int id_employee)
    {
        Clocking clocking=new Clocking(id,date,hour_in,hour_break,hour_work,hour_out);
        provider.updateClocking(clocking,id_employee);
        setChanged();
        notifyObservers();
    }

    public void delete(Clocking clocking)
    {

        provider.deleteClocking(clocking);
        setChanged();
        notifyObservers();
    }

}
