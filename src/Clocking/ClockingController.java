package Clocking;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.Observable;

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
        //TODO: send id_angajat
        return provider.getClockings(1);
    }

    public int get_status()
    {
        //list.add(new Clocking(new GregorianCalendar(), 0));
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

    public void clockin() {
        Calendar now = Calendar.getInstance();
        int hours = now.get(Calendar.HOUR_OF_DAY);
        int minutes = now.get(Calendar.MINUTE);
        int calc_minutes = hours * 60 + minutes;

        list.add(new Clocking(1, new GregorianCalendar(), calc_minutes, 0, 0, 0));

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

                list.set(i,current_time);
            }
        }
        setChanged();
        notifyObservers();
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

                list.set(i,current_time);
            }
        }
        setChanged();
        notifyObservers();
    }

    public void edit(Calendar date, int hour_in, int hour_break, int hour_work, int hour_out,int id)
    {
        ///sql.update(c)
    }

    public void delete(int id)
    {
        ///sql.delete
    }
}
