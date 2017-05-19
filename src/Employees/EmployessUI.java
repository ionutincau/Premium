package Employees;

import Login.LoginController;
import Utils.UtilFunctions;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.util.Observable;
import java.util.Observer;
import java.util.ResourceBundle;

/**
 * Created by Incau Ionut on 15-Apr-17.
 * Contact: ionut.incau@gmail.com
 */

public class EmployessUI implements Initializable,Observer {

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
        this.controller.addObserver(this);
        LoginController.getInstance().addObserver(this);
    }

    @Override
    public void initialize(URL fxmlFileLocation, ResourceBundle resources) {
        employeesList.setFixedCellSize(48);
        employeesList.getItems().addAll(0, controller.getEmployees());
        userAdd();
        userEdit();
        userDelete();
        userSelection();
        cautareDupaNume();
    }

    private void userAdd()
    {
     addButton.setOnAction(e->loadWindow("Adauga", null));
    }

    private void userEdit() {
        editButton.setOnAction(event -> {
        employeesList.getSelectionModel().getSelectedIndex();
        Employee employee = (Employee) employeesList.getSelectionModel().getSelectedItem();
        if (employee!=null) {
            loadWindow("Editare", employee);
        }
        else UtilFunctions.showInfo("Selectati un angajat");});
    }

    private void userDelete() {
        deleteButton.setOnAction(e->{
            employeesList.getSelectionModel().getSelectedIndex();
            Employee selected = (Employee) employeesList.getSelectionModel().getSelectedItem();
            if (selected!=null) {
                controller.deleteEmployee(selected);
            }
            else UtilFunctions.showInfo("Selectati un angajat");});
    }

    @Override
    public void update(Observable o, Object arg) {
        employeesList.getItems().clear();
        employeesList.getItems().addAll(0, controller.getEmployees());
    }

    private void loadWindow(String name, Employee employee) {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("employee_add.fxml"));
            AnchorPane root = loader.load();
            EmployeeUIEdit editController = loader.<EmployeeUIEdit>getController();
            editController.initData(name,employee,controller);
            Stage stage = new Stage();
            stage.setTitle(name);
            stage.setScene(new Scene(root, 300, 250));
            stage.show();
        }
        catch (IOException e)
        {
            e.printStackTrace();
        }
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
        cautaPontajButton.setOnAction(event -> {
            employeesList.getItems().clear();
            employeesList.getItems().addAll(0, controller.searchByNameOREmail(numeAngajatPontajField.getText()));
        });
    }
}
