package Employees;

import Utils.UtilFunctions;
import javafx.collections.FXCollections;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import java.net.URL;
import java.util.ResourceBundle;

/**
 * Created by MariusDK on 29.04.2017.
 */

public class EmployeeUIEdit implements Initializable{

    private Employee employee;
    private EmployeesController controller;

    @FXML private TextField firstNameTextField;
    @FXML private TextField lastNameTextField;
    @FXML private TextField userNameTextField;
    @FXML private TextField passwordTextField;
    @FXML private TextField cnpTextField;
    @FXML private ChoiceBox jobChoiceBox;
    @FXML private ChoiceBox departmentChoiceBox;
    @FXML private TextField emailTextField;
    @FXML private TextField phoneTextField;
    @FXML private ChoiceBox roleChoiceBox;
    @FXML private Button addUserButton;
    @FXML private Button cancelButton;

    public int current_id;

    @Override
    public void initialize(URL fxmlFileLocation, ResourceBundle resources) {}

    public void initData(String name,Employee employee,EmployeesController controller) {
        this.employee=employee;
        this.controller=controller;
        addUserButton.setText(name);
        jobChoiceBox.setItems(FXCollections.observableArrayList(controller.GetAllJobs()));
        departmentChoiceBox.setItems(FXCollections.observableArrayList(controller.GetAllDepartments()));
        roleChoiceBox.setItems(FXCollections.observableArrayList(controller.GetAllRole()));
        cancelButton.setVisible(false);

        if (name=="Adauga") {
            addUserButton.setOnAction(event -> add());
        }
        else {
            current_id=employee.getId();
            firstNameTextField.setText(employee.getLast_name());
            lastNameTextField.setText(employee.getFirst_name());
            userNameTextField.setText(employee.getUsername());
            passwordTextField.setText(employee.getPassword());
            cnpTextField.setText(employee.getCnp());
            emailTextField.setText(employee.getEmail());
            phoneTextField.setText(employee.getPhone());
            jobChoiceBox.setValue(controller.GetJobName(employee.getId_job()));
            departmentChoiceBox.setValue(controller.GetDepartment(employee.getId_department()));
            roleChoiceBox.setValue(employee.getRole());
            addUserButton.setOnAction(event -> edit());
        }
    }

    public void add() {
        try {
            int idJob = controller.idJob(jobChoiceBox.getSelectionModel().getSelectedItem().toString());
            int idDepartment = controller.idDepartment(departmentChoiceBox.getSelectionModel().getSelectedItem().toString());
            controller.addEmployee(firstNameTextField.getText(), lastNameTextField.getText(), userNameTextField.getText(), passwordTextField.getText(), cnpTextField.getText(), idJob, idDepartment, emailTextField.getText(), phoneTextField.getText(), roleChoiceBox.getSelectionModel().getSelectedItem().toString());
            Stage stage = (Stage) addUserButton.getScene().getWindow();
            stage.close();
        }
        catch (NumberFormatException e) {
            UtilFunctions.showInfo("Format invalid!\n");
        }
        catch (Exception e) {
            UtilFunctions.showInfo(e.getMessage());
        }
    }

    public  void edit() {
        try {
            int idJob = controller.idJob(jobChoiceBox.getSelectionModel().getSelectedItem().toString());
            int idDepartment = controller.idDepartment(departmentChoiceBox.getSelectionModel().getSelectedItem().toString());
            controller.editEmployee(current_id,firstNameTextField.getText(), lastNameTextField.getText(), userNameTextField.getText(), passwordTextField.getText(), cnpTextField.getText(), idJob, idDepartment, emailTextField.getText(), phoneTextField.getText(), roleChoiceBox.getSelectionModel().getSelectedItem().toString());
            Stage stage = (Stage) addUserButton.getScene().getWindow();
            stage.close();
        }
        catch (NumberFormatException e) {
            UtilFunctions.showInfo("Format invalid!\n");
        }
        catch (Exception e) {
            UtilFunctions.showInfo(e.getMessage());
        }
    }
}
