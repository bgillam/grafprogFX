package GrafProg.GrafTable;

import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class DataGenUI {

    //UI for column data generator
    private static Stage dataGenStage = new Stage();
    private static Scene dataGenScene;
    private static TableColumnGeneratorController dataGenController;
    private static Parent dataGenRoot;

    public DataGenUI() {

    }

    public static Stage getDataGenStage() {
        return dataGenStage;
    }

    public static void setDataGenStage(Stage dataGenStage) {
        DataGenUI.dataGenStage = dataGenStage;
    }

    public static Scene getDataGenScene() {
        return dataGenScene;
    }

    public static void setDataGenScene(Scene dataGenScene) {
        DataGenUI.dataGenScene = dataGenScene;
    }

    public static TableColumnGeneratorController getDataGenController() {
        return dataGenController;
    }

    public static void setDataGenController(TableColumnGeneratorController dataGenController) {
        DataGenUI.dataGenController = dataGenController;
    }

    public static Parent getDataGenRoot() {
        return dataGenRoot;
    }

    public static void setDataGenRoot(Parent dataGenRoot) {
        DataGenUI.dataGenRoot = dataGenRoot;
    }
}
