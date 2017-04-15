package Clocking;

import Clocking.ClockingController;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;

import java.net.URL;
import java.text.ParseException;
import java.util.Observable;
import java.util.Observer;
import java.util.ResourceBundle;

/**
 * Created by Aurelian on 4/3/2017.
 */

public class ClockingUIAdmin implements Initializable,Observer{


    private ClockingController controller;

    @FXML private ListView pontajListView;
    @FXML private TextField numeAngajatPontajField;
    @FXML private Button cautaPontajButton;
    @FXML private Button adaugaPontajButton;
    @FXML private Button editeazaPontajButton;
    @FXML private Button stergePontajButton;
    @FXML private TextField deLaPontajField;
    @FXML private TextField panaLaPontajField;
    @FXML private Button filtreazaButton;
    public ClockingUIAdmin() {
        this.controller = new ClockingController();
        this.controller.addObserver(this);
    }
    @Override
    public void initialize(URL fxmlFileLocation, ResourceBundle resources) {
        pontajListView.setFixedCellSize(48);
        pontajListView.getItems().addAll(0, controller.getClocking());
        get_buttons();
    }

    private void get_buttons() {

        filtreazaButton.setVisible(true);
        filtreazaButton.setOnAction(e -> filtrare());
        cautaPontajButton.setVisible(true);
        cautaPontajButton.setOnAction(e -> cautareDupaNume());
        stergePontajButton.setOnAction(e->stergere());
    }
    public void cautareDupaNume()
    {
        pontajListView.getItems().clear();
        pontajListView.getItems().addAll(0, controller.searchByName(numeAngajatPontajField.getText()));
    }
    public void filtrare()
    {
        pontajListView.getItems().clear();
        pontajListView.getItems().addAll(0, controller.search(deLaPontajField.getText(),panaLaPontajField.getText()));
    }
    public void stergere()
    {

        pontajListView.getSelectionModel().getSelectedIndex();
        Clocking c=(Clocking) pontajListView.getSelectionModel().getSelectedItem();
        controller.delete(c);
    }
    @Override
    public void update(Observable o, Object arg) {
        get_buttons();
        pontajListView.getItems().clear();
        pontajListView.getItems().addAll(0, controller.getClocking());
    }
}
