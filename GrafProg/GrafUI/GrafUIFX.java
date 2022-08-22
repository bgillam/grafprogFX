package GrafProg.GrafUI;

import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.shape.Line;
import javafx.stage.Stage;

public class GrafUIFX {



    private static Scene grafScene2;
    private static Group grafGroup;
    private static Stage grafStage2;


    public GrafUIFX() {
        grafGroup = new Group();
        grafScene2 = new Scene(grafGroup, 750,750);
        grafStage2 = new Stage();
        Line line = new Line(10,20,25, 50);
        GrafUIFX.getGrafGroup().getChildren().add(line);
    }

    //=======================================================
    public static Stage getGrafStage2() {
        return grafStage2;
    }

    public static void setGrafStage2(Stage grafStage2) {
        GrafUIFX.grafStage2 = grafStage2;
    }
    //=======================================================
    public static Scene getGrafScene2() {
        return grafScene2;
    }

    public static void setGrafScene2(Scene grafScene2) {
        GrafUIFX.grafScene2 = grafScene2;
    }
    //=======================================================
    public static Group getGrafGroup() {
        return grafGroup;
    }

    public static void setGrafGroup(Group grafGroup) {
        GrafUIFX.grafGroup = grafGroup;
    }
    //=================================================================
}
