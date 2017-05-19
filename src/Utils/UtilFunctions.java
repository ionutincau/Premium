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
}
