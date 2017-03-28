package Clocking;

import org.junit.Test;

import java.util.GregorianCalendar;

import static org.junit.Assert.*;
import static org.junit.jupiter.api.Assertions.assertTrue;

/**
 * Created by Incau Ionut on 14-Mar-17.
 * Contact: ionut.incau@gmail.com
 */

public class ClockingTest {

    @Test
    public void get_time() throws Exception {
        GregorianCalendar date = new GregorianCalendar();

        Clocking clocking = new Clocking(1, date, 480, 0, 0, 0);
        assertTrue(clocking.get_time() == 0);
        clocking.set_hour_break(500);
        assertTrue(clocking.get_time() == 20);
        clocking.set_hour_work(600);
        assertTrue(clocking.get_time() == 20);
        clocking.set_hour_out(620);
        assertTrue(clocking.get_time() == 40);

        Clocking clocking2 = new Clocking(1, date, 200, 0, 0, 0);
        assertTrue(clocking2.get_time() == 0);
        clocking2.set_hour_out(300);
        assertTrue(clocking2.get_time() == 100);
    }

    @Test
    public void get_time_format() throws Exception {
        //TODO:
    }

}