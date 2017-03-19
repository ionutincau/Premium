package Login;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

/**
 * Created by Incau Ionut on 14-Mar-17.
 * Contact: ionut.incau@gmail.com
 */

public class LoginUI implements EventHandler<ActionEvent>
{
    private Stage loginStage;
    private LoginController controller;
    private Scene loginScene;
    private Button loginButton;
    private TextField usernameField;
    private PasswordField passwordField;
    private VBox box;

    public LoginUI()
    {
        loginStage = new Stage();
        this.controller = new LoginController(loginStage);
        createView();
    }

    private void createView()
    {
        loginButton  = new Button("Login");
        loginButton.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                String password = passwordField.getText();
                String username = usernameField.getText();
                controller.login(username, password);
            }
        });
        usernameField = new TextField();
        passwordField = new PasswordField();
        usernameField.setPromptText("Username");
        passwordField.setPromptText("Password");
        usernameField.setMaxWidth(130);
        passwordField.setMaxWidth(130);
        box = new VBox();
        box.setSpacing(10);
        box.setPadding( new Insets(100, 10, 20, 180));
        box.getChildren().addAll(usernameField, passwordField, loginButton);
        loginScene = new Scene(box, 500, 400);
    }
    public Scene getScene() {
        return loginScene;
    }

    @Override
    public void handle(ActionEvent event) {
        if (event.getSource() == loginButton) {
            String password = passwordField.getText();
            String username = usernameField.getText();

            controller.login(username, password);
        }

    }
}
