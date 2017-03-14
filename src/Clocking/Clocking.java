package Clocking;

import java.util.Calendar;
import java.util.GregorianCalendar;

/**
 * Created by MariusDK on 13.03.2017.
 */

public class Clocking {

    private Calendar date = new GregorianCalendar(); // date
    private int hour_in; // number of minutes of day - clock-in start work
    private int hour_break; // number of minutes of day - clock-out for break
    private int hour_work; // number of minutes of day  - clock-in after break
    private int hour_out; // number of minutes of day - clock-out

    public Clocking(Calendar date, int hour_in) {
        this.date = date;
        this.hour_in = hour_in;
        this.hour_break = 0;
        this.hour_work = 0;
        this.hour_out = 0;
    }

    public Clocking (Calendar date, int hour_in, int hour_break, int hour_work, int hour_out) {
        this.date = date;
        this.hour_in = hour_in;
        this.hour_break = hour_break;
        this.hour_work = hour_work;
        this.hour_out = hour_out;
    }

    public Calendar get_date() {
        return this.date;
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

    public void set_date(Calendar date) {
        this.date = date;
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
}
