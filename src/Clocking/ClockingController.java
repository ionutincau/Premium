package Clocking;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.GregorianCalendar;

/**
 * Created by MariusDK on 13.03.2017.
 */

public class ClockingController {


    public ArrayList<Clocking> list = new ArrayList<Clocking>();

    public ArrayList getClocking()
    {
        //TODO: return a list of clockings
        list.add(new Clocking(new GregorianCalendar(), 0,80,70,90));
        System.out.println(list.size());
        return list;
    }
    public int get_status()
    {
        //list.add(new Clocking(new GregorianCalendar(), 0));
        Calendar now = Calendar.getInstance();
        if (list.size() == 0) {
            System.out.println("ok");
            return 1;
        } else {
            System.out.println(list.get(list.size() - 1).get_date().getCalendarType().equals(now.getCalendarType()));
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
        System.out.println("ok");
        Calendar now = Calendar.getInstance();
        Clocking exist_time = new Clocking(new GregorianCalendar(),0);
        int hours = now.get(Calendar.HOUR_OF_DAY);
        int minutes = now.get(Calendar.MINUTE);
//        if (list.size()!=0) {
//            System.out.println("ok");
//            Clocking last = list.get(list.size() - 1);
//            //System.out.println(last.get_date().getCalendarType().equals(now.getCalendarType()));
//            //verificam daca ultimul element al listei este ziua curenta
//            //daca este ziua curenta nu il mai adaugam in lista
//            if (!last.get_date().getCalendarType().equals(now.getCalendarType())) {
//                int calc_minutes = hours * 60 + minutes;
//                System.out.println(calc_minutes);
//                Clocking current_time = new Clocking(new GregorianCalendar(), calc_minutes);
//                list.add(new Clocking(new GregorianCalendar(), calc_minutes));
//                System.out.println(list);
//            }
//        }

            int calc_minutes = hours * 60 + minutes;
            System.out.println(calc_minutes);
            Clocking current_time = new Clocking(new GregorianCalendar(), calc_minutes);
            list.add(new Clocking(new GregorianCalendar(), calc_minutes));
            System.out.println(list);

    }
    public void clockbreak()
    {

        Calendar now = Calendar.getInstance();
        int hours = now.get(Calendar.HOUR_OF_DAY);
        int minutes = now.get(Calendar.MINUTE);
        int calc_minutes = hours * 60 + minutes;
        Clocking current_time;
        System.out.println(calc_minutes);
        for (int i=0;i<list.size();i++)
        {
            System.out.println("ok 2");
            System.out.println(list.get(i).get_date().getCalendarType().equals(now.getCalendarType()));
            if (list.get(i).get_date().getCalendarType().equals(now.getCalendarType()))
            {
                current_time=list.get(i);
                current_time.set_hour_break(calc_minutes);

                list.set(i,current_time);
            }
        }
        System.out.println(list);
    }

    public void clockwork()
    {

        Calendar now = Calendar.getInstance();
        int hours = now.get(Calendar.HOUR_OF_DAY);
        int minutes = now.get(Calendar.MINUTE);
        int calc_minutes = hours * 60 + minutes;
        Clocking current_time;
        System.out.println(calc_minutes);
        for (int i=0;i<list.size();i++)
        {
            System.out.println("ok 2");
            System.out.println(list.get(i).get_date().getCalendarType().equals(now.getCalendarType()));
            if (list.get(i).get_date().getCalendarType().equals(now.getCalendarType()))
            {
                current_time=list.get(i);
                current_time.set_hour_work(calc_minutes);

                list.set(i,current_time);
            }
        }
        System.out.println(list);
    }
    public void clockout()
    {
        Calendar now = Calendar.getInstance();
        int hours = now.get(Calendar.HOUR_OF_DAY);
        int minutes = now.get(Calendar.MINUTE);
        int calc_minutes = hours * 60 + minutes;
        Clocking current_time;
        System.out.println(calc_minutes);
        for (int i=0;i<list.size();i++)
        {
            System.out.println("ok 2");
            System.out.println(list.get(i).get_date().getCalendarType().equals(now.getCalendarType()));
            if (list.get(i).get_date().getCalendarType().equals(now.getCalendarType()))
            {
                current_time=list.get(i);
                current_time.set_hour_out(calc_minutes);

                list.set(i,current_time);
            }
        }
        System.out.println(list);
    }
}
