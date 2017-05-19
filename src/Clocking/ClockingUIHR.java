package Clocking;

import Login.LoginController;
import database.DatabaseConnection;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;

import java.net.URL;
import java.util.Observable;
import java.util.Observer;
import java.util.ResourceBundle;

/**
 * Created by Aurelian on 4/3/2017.
 */

public class ClockingUIHR implements Initializable,Observer {

    private ClockingController controller;

    @FXML private Button clockInButton;
    @FXML private Button clockBreakButton;
    @FXML private Button clockWorkButton;
    @FXML private Button clockOutButton;
    @FXML private ListView pontajListView;
    @FXML private TextField deLaPontajField;
    @FXML private TextField panaLaPontajField;
    @FXML private Button filtreazaButton;

    public ClockingUIHR() {
        this.controller = new ClockingController();
        this.controller.addObserver(this);
        LoginController.getInstance().addObserver(this);
    }

    @Override
    public void initialize(URL fxmlFileLocation, ResourceBundle resources) {
        pontajListView.setFixedCellSize(48);
        pontajListView.getItems().addAll(0, controller.getClocking());
        get_buttons();
    }

    private void get_buttons() {
        filtreazaButton.setVisible(true);
        clockInButton.setVisible(false);
        clockBreakButton.setVisible(false);
        clockWorkButton.setVisible(false);
        clockOutButton.setVisible(false);

        int status = controller.get_status();

        if (status != 0) {
            clockInButton.setOnAction(e -> controller.clockin());
            clockBreakButton.setOnAction(e -> controller.clockbreak());
            clockWorkButton.setOnAction(e -> controller.clockwork());
            clockOutButton.setOnAction(e -> controller.clockout());
            filtreazaButton.setOnAction(e -> filtrare());

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
    }

    public void filtrare() {
        pontajListView.getItems().clear();
        pontajListView.getItems().addAll(0, controller.search(deLaPontajField.getText(),panaLaPontajField.getText()));
    }

    @Override
    public void update(Observable o, Object arg) {
        pontajListView.getItems().clear();
        pontajListView.getItems().addAll(0, controller.getClocking());
        get_buttons();
    }

}
