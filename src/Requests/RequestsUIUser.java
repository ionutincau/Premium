package Requests;

import Login.LoginController;
import javafx.collections.FXCollections;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.ListView;

import java.net.URL;
import java.util.ResourceBundle;

/**
 * Created by Aurelian on 4/3/2017.
 */
public class RequestsUIUser implements Initializable{
    private RequestsController controller;

    @FXML private ListView cereriListView;

    @FXML private Button creeazaCerereButton;

    @FXML private ChoiceBox cereriChoiceBox;

    public RequestsUIUser()
    {
        this.controller=new RequestsController();
//        this.controller.addObserver(this);
//        LoginController.getInstance().addObserver(this);
    }
    @Override
    public void initialize(URL fxmlFileLocation, ResourceBundle resources) {
        cereriListView.setFixedCellSize(48);
        cereriListView.getItems().addAll(0,controller.getRequestsListForUser());
        cereriChoiceBox.setItems(FXCollections.observableArrayList(controller.getRequestType()));
        creeazaCerereButton.setOnAction(e->controller.saveRequestUserAndDoc((String) cereriChoiceBox.getSelectionModel().getSelectedItem()));
    }
}
