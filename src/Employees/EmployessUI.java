package Employees;

import Login.LoginController;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;

import java.net.URL;
import java.util.ResourceBundle;

/**
 * Created by Incau Ionut on 15-Apr-17.
 * Contact: ionut.incau@gmail.com
 */

public class EmployessUI implements Initializable {

    private EmployeesController controller;

    @FXML private Label selectedUser;
    @FXML private TextField numeAngajatPontajField;
    @FXML private Button cautaPontajButton;
    @FXML private ListView employeesList;
    @FXML private Button addButton;
    @FXML private Button editButton;
    @FXML private Button deleteButton;
    @FXML private Button selectButton;

    public EmployessUI() {
        this.controller = new EmployeesController();
    }

    @Override
    public void initialize(URL fxmlFileLocation, ResourceBundle resources) {
        employeesList.setFixedCellSize(48);
        employeesList.getItems().addAll(0, controller.getEmployees());
    }

    private void userSelection() {
        selectButton.setOnAction(e -> {
            Employee selected = (Employee) employeesList.getSelectionModel().getSelectedItem();
            LoginController.getInstance().setSelectedUser(selected);
            selectedUser.setText(selected.toString());
            LoginController.getInstance().setSelectedUserChanged();
        });
    }

    public void cautareDupaNume() {
        employeesList.getItems().clear();
        employeesList.getItems().addAll(0, controller.searchByName(numeAngajatPontajField.getText()));
    }
}
