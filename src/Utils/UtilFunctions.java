package Utils;

import javafx.scene.control.Alert;

import javax.swing.*;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;

/**
 * Created by Incau Ionut on 15-Apr-17.
 * Contact: ionut.incau@gmail.com
 */

public class UtilFunctions {

    public static void showInfo(String info) {
        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.setTitle("Error");
        alert.setHeaderText(info);
        alert.show();
    }

    public static String dialog(String mesaj) {
        return JOptionPane.showInputDialog(mesaj);
    }

    public static Calendar formatDate(String d) throws ParseException {
        Calendar cal = new GregorianCalendar();
        DateFormat df = new SimpleDateFormat("dd.MM.yyyy");
        Date date = df.parse(d);
        cal.setTime(date);
        return cal;
    }

    public static String get_date_format(Calendar calendar) {
        int d = calendar.get(Calendar.DAY_OF_MONTH);
        int m = calendar.get(Calendar.MONTH) + 1;
        String day;
        String month;
        if (d < 10) day = "0" + d;
        else day = "" + d;
        if (m < 10) month = "0" + m;
        else month = "" + d;
        return day + "." + month + "." + calendar.get(Calendar.YEAR);
    }

    public static String get_time_format(int n) {
        if (n == 0) return "00:00";
        int hours = n / 60;
        int minutes = n % 60;
        String h = "" + hours;
        String m = "" + minutes;
        if (hours < 10) h = "0" + h;
        if (minutes < 10) m = "0" + m;
        return h + ":" + m;
    }
}
