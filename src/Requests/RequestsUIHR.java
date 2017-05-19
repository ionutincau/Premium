package Requests;

import Login.LoginController;
import javafx.collections.FXCollections;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.ListView;

import java.net.URL;
import java.util.Observable;
import java.util.Observer;
import java.util.ResourceBundle;

/**
 * Created by Aurelian on 4/3/2017.
 */
public class RequestsUIHR implements Initializable,Observer{
        private RequestsController controller;

    @FXML
    private ListView cereriListView;

    @FXML private Button acceptButton;

    @FXML private Button rejectButton;

    public RequestsUIHR()
    {
        this.controller=new RequestsController();
        this.controller.addObserver(this);
        LoginController.getInstance().addObserver(this);
    }
    @Override
    public void initialize(URL fxmlFileLocation, ResourceBundle resources) {
        cereriListView.setFixedCellSize(48);
        cereriListView.getItems().addAll(0,controller.getRequestsListForHR());
        //cereriListView.getSelectionModel().getSelectedIndex();
        acceptButton.setOnAction(e->{
            RequestsUser requestsUser = (RequestsUser) cereriListView.getSelectionModel().getSelectedItem();
            controller.updateRequests(requestsUser, "approved");});
        rejectButton.setOnAction(e->{
            RequestsUser requestsUser = (RequestsUser) cereriListView.getSelectionModel().getSelectedItem();
            controller.updateRequests(requestsUser, "rejected");});
    }
    @Override
    public void update(Observable o, Object arg) {
        cereriListView.getItems().clear();
        cereriListView.getItems().addAll(0,controller.getRequestsListForHR());
    }

}
