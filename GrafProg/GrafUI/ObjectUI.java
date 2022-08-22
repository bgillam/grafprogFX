package GrafProg.GrafUI;

import GrafProg.GrafObjects.Dialog.GrafDialogController;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class ObjectUI {

    //UI for creating GrafProg.GrafProg.GrafObjects
    private static Stage dialogStage = new Stage();
    private static Scene dialogScene;
    private static GrafDialogController dialogController;
    private static Parent dialogRoot;

    public ObjectUI() {


    }

    public static Stage getDialogStage() {
        return dialogStage;
    }

    public static void setDialogStage(Stage dialogStage) {
        ObjectUI.dialogStage = dialogStage;
    }

    public static Scene getDialogScene() {
        return dialogScene;
    }

    public static void setDialogScene(Scene dialogScene) {
        ObjectUI.dialogScene = dialogScene;
    }

    public static GrafDialogController getDialogController() {
        return dialogController;
    }

    public static void setDialogController(GrafDialogController dialogController) {
        ObjectUI.dialogController = dialogController;
    }

    public static Parent getDialogRoot() {
        return dialogRoot;
    }

    public static void setDialogRoot(Parent dialogRoot) {
        ObjectUI.dialogRoot = dialogRoot;
    }
}
