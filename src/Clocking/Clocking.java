package Clocking;

import java.util.Calendar;
import java.util.GregorianCalendar;

/**
 * Created by MariusDK on 13.03.2017.
 */

public class Clocking {

    private int id;
    private Calendar calendar = new GregorianCalendar(); // date
    private int hour_in; // number of minutes of day - clock-in start work
    private int hour_break; // number of minutes of day - clock-out for break
    private int hour_work; // number of minutes of day  - clock-in after break
    private int hour_out; // number of minutes of day - clock-out

    public Clocking (int id, Calendar date, int hour_in, int hour_break, int hour_work, int hour_out) {
        this.id = id;
        this.calendar = date;
        this.hour_in = hour_in;
        this.hour_break = hour_break;
        this.hour_work = hour_work;
        this.hour_out = hour_out;
    }

    public Calendar get_date() {
        return this.calendar;
    }

    public int get_hour_in() {
        return this.hour_in;
    }

    public int get_hour_break() {
        return this.hour_break;
    }

    public int get_hour_work() {
        return this.hour_work;
    }

    public int get_hour_out() {
        return this.hour_out;
    }

    public void set_date(Calendar calendar) {
        this.calendar = calendar;
    }

    public void set_hour_in(int hour_in) {
        this.hour_in = hour_in;
    }

    public void set_hour_break(int hour_break) {
        this.hour_break = hour_break;
    }

    public void set_hour_work(int hour_work) {
        this.hour_work = hour_work;
    }

    public void set_hour_out(int hour_out) {
        this.hour_out = hour_out;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    /**
     * @return number of worked minutes in a day
     */
    public int get_time() {
        if (hour_out == 0) {
            if (hour_break == 0) return 0;
            return hour_break - hour_in;
        }
        return hour_break - hour_in + hour_out - hour_work;
    }

    private String get_time_format(int n) {
        if (n == 0) return "00:00";
        int hours = n / 60;
        int minutes = n % 60;
        String h = "" + hours;
        String m = "" + minutes;
        if (hours < 10) h = "0" + h;
        if (minutes < 10) m = "0" + m;
        return h + ":" + m;
    }

    @Override
    public String toString() {
        String date = calendar.get(Calendar.DAY_OF_MONTH) + "." +
                calendar.get(Calendar.MONTH) + "." +
                calendar.get(Calendar.YEAR);
        String hours = get_time_format(hour_in) + " - " +
                get_time_format(hour_break) + " - " +
                get_time_format(hour_work) + " - " +
                get_time_format(hour_out);
        String time = get_time_format(get_time());
        return "          " + date + "                    " + hours + "                    " + time;
    }
}
