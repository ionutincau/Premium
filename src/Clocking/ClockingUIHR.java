package Clocking;

import Clocking.ClockingController;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.ListView;

import java.net.URL;
import java.text.ParseException;
import java.util.Observable;
import java.util.Observer;
import java.util.ResourceBundle;

/**
 * Created by Aurelian on 4/3/2017.
 */
public class ClockingUIHR implements Initializable,Observer {

    private ClockingController controller;

    @FXML private ListView pontajListView;
    @FXML private Button clockInButton;
    @FXML private Button clockBreakButton;
    @FXML private Button clockWorkButton;
    @FXML private Button clockOutButton;
    public ClockingUIHR() {
        this.controller=new ClockingController();
        this.controller.addObserver(this);
    }

    @Override
    public void initialize(URL fxmlFileLocation, ResourceBundle resources) {
        pontajListView.setFixedCellSize(48);
        pontajListView.getItems().addAll(0, controller.getClocking());
        get_buttons();
    }

    private void get_buttons() {
        clockInButton.setVisible(false);
        clockBreakButton.setVisible(false);
        clockWorkButton.setVisible(false);
        clockOutButton.setVisible(false);
        clockInButton.setOnAction(e -> {
            try {
                controller.clockin();
            } catch (ParseException e1) {
                e1.printStackTrace();
            }
        });
        clockBreakButton.setOnAction(e -> controller.clockbreak());
        clockWorkButton.setOnAction(e -> controller.clockwork());
        clockOutButton.setOnAction(e -> controller.clockout());

        int status = controller.get_status();
        if (status == 0){
            return;
        }
        if (status == 1) {
            clockInButton.setVisible(true);
        }
        else if (status == 2) {
            clockBreakButton.setVisible(true);
            clockOutButton.setVisible(true);
        }
        else if (status == 3) {
            clockWorkButton.setVisible(true);
        }
        else {
            clockOutButton.setVisible(true);
        }
    }

    @Override
    public void update(Observable o, Object arg) {
        get_buttons();
        pontajListView.getItems().clear();
        pontajListView.getItems().addAll(0, controller.getClocking());
    }
}
