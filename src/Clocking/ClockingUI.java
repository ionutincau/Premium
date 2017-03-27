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
    Button button_clockbreak;
    Button button_clockwork;
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
        // top view
        get_buttons();

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

    private void get_buttons() {
        if (controller.get_status() == 1) {
            button_clockin = new Button("Clock In");
            button_clockin.setOnAction(new EventHandler<ActionEvent>() {
                @Override public void handle(ActionEvent e) {
                    System.out.println("Pas 1");
                    controller.clockin();
                }
            });
            topView.getChildren().addAll(button_clockin);
        }
        else if (controller.get_status() == 2) {
            button_clockbreak = new Button("Clock Break");
            button_clockout = new Button("Clock Out");
            topView.getChildren().addAll(button_clockbreak, button_clockout);
            button_clockbreak.setOnAction(new EventHandler<ActionEvent>() {
                @Override public void handle(ActionEvent e) {
                    System.out.println("Pas 2");
                    controller.clockbreak();
                }
            });
        }
        else if (controller.get_status() == 3) {
            button_clockwork = new Button("Clock Work");
            topView.getChildren().addAll(button_clockwork);
        }
        else {
            button_clockout = new Button("Clock Out");
            topView.getChildren().addAll(button_clockout);
        }
    }

    @Override
    public void handle(ActionEvent event) {
        if (event.getSource() == button_clockin) {
            System.out.println("Pas 1");
            controller.clockin();
        }
        else if (event.getSource() == button_clockbreak) {
            controller.clockbreak();
        }
        else if (event.getSource() == button_clockwork) {
            controller.clockwork();
        }
        else {
            controller.clockout();
        }
    }

    public Scene getScene() {
        return scene;
    }
}
