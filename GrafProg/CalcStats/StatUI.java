package GrafProg.CalcStats;

import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class StatUI {

    //UI for one variable stats
    private static Stage statStage = new Stage();
    private static Scene statScene;
    private static OneVarStatsController statController;
    private static Parent statRoot;

    public StatUI() {
    }

    public static Stage getStatStage() {
        return statStage;
    }

    public static void setStatStage(Stage statStage) {
        StatUI.statStage = statStage;
    }

    public static Scene getStatScene() {
        return statScene;
    }

    public static void setStatScene(Scene statScene) {
        StatUI.statScene = statScene;
    }

    public static OneVarStatsController getStatController() {
        return statController;
    }

    public static void setStatController(OneVarStatsController statController) {
        StatUI.statController = statController;
    }

    public static Parent getStatRoot() {
        return statRoot;
    }

    public static void setStatRoot(Parent statRoot) {
        StatUI.statRoot = statRoot;
    }
}
