package Clocking;

import Utils.UIAlerts;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import java.net.URL;
import java.text.ParseException;
import java.util.ResourceBundle;

/**
 * Created by Incau Ionut on 15-Apr-17.
 * Contact: ionut.incau@gmail.com
 */

public class ClockingUIEdit implements Initializable {

    private ClockingController controller;
    private Clocking clocking;

    @FXML private Button okButton;
    @FXML private TextField dateField;
    @FXML private TextField inField;
    @FXML private TextField breakField;
    @FXML private TextField workField;
    @FXML private TextField outField;

    @Override
    public void initialize(URL fxmlFileLocation, ResourceBundle resources) {}

    public void initData(String name, Clocking clocking, ClockingController controller) {
        this.clocking = clocking;
        this.controller = controller;
        okButton.setText(name);
        if (name.equals("Adauga")) {
            okButton.setOnAction(e -> add());
        }
        else {
            dateField.setText(clocking.get_date_format());
            inField.setText(clocking.get_time_format(clocking.get_hour_in()));
            breakField.setText(clocking.get_time_format(clocking.get_hour_break()));
            workField.setText(clocking.get_time_format(clocking.get_hour_work()));
            outField.setText(clocking.get_time_format(clocking.get_hour_out()));
            okButton.setOnAction(e -> edit());
        }
    }

    private void add() {
        try {
            controller.add(dateField.getText(), inField.getText(), breakField.getText(), workField.getText(), outField.getText());
            Stage stage = (Stage) okButton.getScene().getWindow();
            stage.close();
        }
        catch (ParseException e) {
            UIAlerts.showInfo("Format invalid!\nData trebuie sa fie de forma: zz.ll.aaaa");
        }
        catch (NumberFormatException e) {
            UIAlerts.showInfo("Format invalid!\nTimpul trebuie introdus sub forma: hh:mm");
        }
        catch (Exception e) {
            UIAlerts.showInfo(e.getMessage());
        }
    }

    private void edit() {
        try {
            controller.edit(clocking.getId(), dateField.getText(), inField.getText(), breakField.getText(), workField.getText(), outField.getText());
            Stage stage = (Stage) okButton.getScene().getWindow();
            stage.close();
        }
        catch (NumberFormatException e) {
            UIAlerts.showInfo("Format invalid!\nTimpul trebuie introdus sub forma: hh:mm");
        }
        catch (ParseException e) {
            UIAlerts.showInfo("Format invalid!\nData trebuie sa fie de forma: zz.ll.aaaa");
        }
        catch (Exception e) {
            UIAlerts.showInfo(e.getMessage());
        }
    }
}
