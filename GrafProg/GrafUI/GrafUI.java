package GrafProg.GrafUI;

import javafx.application.Platform;
import javafx.embed.swing.SwingNode;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class GrafUI {

    //UI for x-y axes and graphs
    private static Stage grafStage = new Stage();
    private static Scene grafScene;
    private static GrafController grafController;
    private static Parent grafRoot;
    private static SwingNode swingGrafNode = new SwingNode();   //swing holder for grafPanel
    private static GrafPanel grafPanel = new GrafPanel(); //Graphics Panel in swing



    public GrafUI() {

    }



    public static void repaintGraf() {
        Platform.runLater(() -> {
            //getGrafStage().hide();
            //getGrafStage().show();
            getGrafPanel().repaint();

        });
    }


    public static Stage getGrafStage() {
        return grafStage;
    }

    public static void setGrafStage(Stage grafStage) {
        GrafUI.grafStage = grafStage;
    }

    public static Scene getGrafScene() {
        return grafScene;
    }

    public static void setGrafScene(Scene grafScene) {
        GrafUI.grafScene = grafScene;
    }

    public static GrafController getGrafController() {
        return grafController;
    }

    public static void setGrafController(GrafController grafController) {
        GrafUI.grafController = grafController;
    }

    public static Parent getGrafRoot() {
        return grafRoot;
    }

    public static void setGrafRoot(Parent grafRoot) {
        GrafUI.grafRoot = grafRoot;
    }

    public static SwingNode getSwingGrafNode() {
        return swingGrafNode;
    }

    public static void setSwingGrafNode(SwingNode swingGrafNode) {
        GrafUI.swingGrafNode = swingGrafNode;
    }

    public static GrafPanel getGrafPanel() {
        return grafPanel;
    }

    public static void setGrafPanel(GrafPanel grafPanel) {
        GrafUI.grafPanel = grafPanel;
    }



}
