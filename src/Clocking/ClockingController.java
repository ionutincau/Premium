package Clocking;

import java.util.ArrayList;
import java.util.GregorianCalendar;

/**
 * Created by MariusDK on 13.03.2017.
 */

public class ClockingController {

    public ArrayList getClocking() {
        //TODO: return a list of clockings

        ArrayList list = new ArrayList();
        list.add(new Clocking(new GregorianCalendar(), 480));
        list.add(new Clocking(new GregorianCalendar(), 480, 500, 600, 700));
        list.add(new Clocking(new GregorianCalendar(), 600, 800, 900, 950));
        list.add(new Clocking(new GregorianCalendar(), 80, 100, 120, 121));
        list.add(new Clocking(new GregorianCalendar(), 164, 254, 351, 456));
        return list;
    }


    public int get_status() {
        return 3;
    }

    public void clockin() {

    }

    public void clockbreak() {

    }

    public void clockwork() {

    }

    public void clockout() {

    }
}
