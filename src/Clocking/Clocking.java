package Clocking;

import java.util.Calendar;
import java.util.GregorianCalendar;

/**
 * Created by MariusDK on 13.03.2017.
 */

public class Clocking {
    
    private Calendar hour_in = new GregorianCalendar(); // clock-in start work
    private Calendar hour_break = new GregorianCalendar(); // clock-out for break
    private Calendar hour_work = new GregorianCalendar(); // clock-in after break
    private Calendar hour_out = new GregorianCalendar(); // clock-out

    public Clocking (GregorianCalendar hour_in, GregorianCalendar hour_break, GregorianCalendar hour_work, GregorianCalendar hour_out) {
        this.hour_in = hour_in;
        this.hour_break = hour_break;
        this.hour_work = hour_work;
        this.hour_out = hour_out;
    }

    public Calendar get_date() {
        return this.hour_in;
    }

    public Calendar get_hour_in() {
        return this.hour_in;
    }

    public Calendar get_hour_break() {
    return this.hour_break;
    }

    public Calendar get_hour_work() {
        return this.hour_work;
    }

    public Calendar get_hour_out() {
        return this.hour_out;
    }

    public void set_hour_in(Calendar calendar) {
        this.hour_in = calendar;
    }

    public void set_hour_break(Calendar calendar) {
        this.hour_break = calendar;
    }

    public void set_hour_work(Calendar calendar) {
        this.hour_work = calendar;
    }

    public void set_hour_out(Calendar calendar) {
        this.hour_out = calendar;
    }
}
