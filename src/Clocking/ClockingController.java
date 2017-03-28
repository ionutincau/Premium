package Clocking;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.Observable;

/**
 * Created by MariusDK on 13.03.2017.
 */

public class ClockingController extends Observable{


    public ArrayList<Clocking> list = new ArrayList<Clocking>();
    public ArrayList getClocking()
    {
        //TODO: return a list of clockings
        //list.add(new Clocking(new GregorianCalendar(), 0,80,70,0));
        return list;
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
        //Ora si minutele curente
        Calendar now = Calendar.getInstance();
        Clocking exist_time = new Clocking(new GregorianCalendar(),0);
        int hours = now.get(Calendar.HOUR_OF_DAY);
        int minutes = now.get(Calendar.MINUTE);
//        if (list.size()!=0) {
//            Clocking last = list.get(list.size() - 1);
//            //verificam daca ultimul element al listei este ziua curenta
//            //daca este ziua curenta nu il mai adaugam in lista
//            if (!last.get_date().getCalendarType().equals(now.getCalendarType())) {
//                int calc_minutes = hours * 60 + minutes;
//                Clocking current_time = new Clocking(new GregorianCalendar(), calc_minutes);
//                list.add(new Clocking(new GregorianCalendar(), calc_minutes));
//            }
//        }
            int calc_minutes = hours * 60 + minutes;
            Clocking current_time = new Clocking(new GregorianCalendar(), calc_minutes);
            list.add(new Clocking(new GregorianCalendar(), calc_minutes));
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
    public void Edit()
    {

    }
    public void Delete()
    {

    }
}
