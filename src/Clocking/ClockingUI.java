package Clocking;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.StackPane;

/**
 * Created by MariusDK on 13.03.2017.
 */

public class ClockingUI implements EventHandler<ActionEvent> {

    Button button;
    private StackPane layout;
    private Scene scene;

    public ClockingUI() {
        this.layout = new StackPane();
        this.scene = new Scene(layout, 300, 250);
    }

    @Override
    public void handle(ActionEvent event) {
        if (event.getSource() == button) {
        }
    }

    public Scene getScene() {
        button = new Button("Clockin");
        layout.getChildren().add(button);

        return scene;
    }
}
