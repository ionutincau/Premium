package Clocking;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ListView;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextField;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;

/**
 * Created by MariusDK on 13.03.2017.
 */

public class ClockingUI implements EventHandler<ActionEvent> {

    private ClockingController controller;

    private Scene scene;
    private VBox mainView;
    private HBox topView;
    private ScrollPane middleView;
    private ListView clockingView;

    Button button_clockin;
    Button button_clockout;

    public ClockingUI() {
        this.controller = new ClockingController();

        this.mainView = new VBox();
        this.topView = new HBox();
        this.middleView = new ScrollPane();
        this.clockingView = new ListView();
        createView();
    }

    private void createView() {
        button_clockin = new Button("Clockin");
        button_clockout = new Button("Clockout");
        topView.getChildren().addAll(button_clockin, button_clockout);

        // middle view
        clockingView.getItems().addAll(0, controller.getClocking());
        clockingView.setFixedCellSize(48);
        middleView.setFitToWidth(true);
        middleView.setHbarPolicy(ScrollPane.ScrollBarPolicy.NEVER);
        this.middleView.setContent(clockingView);

        // main view
        this.mainView.getChildren().addAll(topView, middleView);
        this.scene = new Scene(mainView, 500, 400);
    }

    @Override
    public void handle(ActionEvent event) {
        if (event.getSource() == button_clockin) {
        }
    }

    public Scene getScene() {
        return scene;
    }
}
