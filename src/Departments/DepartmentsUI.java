package Departments;

import Employees.Employee;
import Employees.EmployeeUIEdit;
import Login.LoginController;
import Utils.UIAlerts;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ListView;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.util.Observable;
import java.util.Observer;
import java.util.ResourceBundle;

/**
 * Created by MariusDK on 01.05.2017.
 */
public class DepartmentsUI implements Initializable,Observer{
    private DepartmentsController controller;

    @FXML private ListView departmentsListView;
    @FXML private Button addDepartmentButton;
    @FXML private Button editDepartmentButton;
    @FXML private Button deleteDepartmentButton;

    public DepartmentsUI()
    {
        this.controller=new DepartmentsController();
        this.controller.addObserver(this);
        LoginController.getInstance().addObserver(this);
    }
    @Override
    public void initialize(URL fxmlFileLocation, ResourceBundle resources)
    {
        departmentsListView.setFixedCellSize(48);
        departmentsListView.getItems().addAll(0, controller.getDepartments());
        departmentAdd();
        departmentEdit();
        departmentDelete();
    }
    private void loadWindow(String name, Department department)
    {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("add_department.fxml"));
            AnchorPane root = loader.load();
            DepartmentsUIEdit editController = loader.<DepartmentsUIEdit>getController();
            editController.initData(name,department,controller);
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
    public void departmentAdd()
    {
        addDepartmentButton.setOnAction(event ->loadWindow("Adauga", null));
    }

    public void departmentEdit()
    {
        editDepartmentButton.setOnAction(event -> {
            departmentsListView.getSelectionModel().getSelectedIndex();
            Department department = (Department) departmentsListView.getSelectionModel().getSelectedItem();
            if (department!=null) {
                loadWindow("Editare", department);
            }
            else UIAlerts.showInfo("Selectati un departament");
        });
    }
    public void departmentDelete()
    {
        deleteDepartmentButton.setOnAction(e->{
            departmentsListView.getSelectionModel().getSelectedIndex();
            Department department = (Department) departmentsListView.getSelectionModel().getSelectedItem();
            if (department!=null)
            {
                controller.removeDepartment(department);
            }
            else UIAlerts.showInfo("Selectati un departament");
        });
    }
    @Override
    public void update(Observable o, Object arg) {
        departmentsListView.getItems().clear();
        departmentsListView.getItems().addAll(0, controller.getDepartments());
    }

}
