package Utils;

import javafx.scene.control.Alert;

/**
 * Created by Incau Ionut on 15-Apr-17.
 * Contact: ionut.incau@gmail.com
 */

public class UIAlerts {

    public static void showInfo(String info) {
        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.setTitle("Error");
        alert.setHeaderText(info);
        alert.show();
    }
}
