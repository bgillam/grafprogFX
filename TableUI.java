import javafx.embed.swing.SwingNode;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import javax.swing.*;

public class TableUI {

    private static Stage tableStage = new Stage();
    private static Scene tableScene;
    private static TableController tableController;
    private static Parent tableRoot;
    private static SwingNode swingTableNode = new SwingNode();
    private static GrafTable data = new GrafTable(100,10);  //Swing table for data
    private static JPanel dataPanel = data.getDataPanel(); //Graphics Panel in swing



    public TableUI() {

    }

    public static Stage getTableStage() {
        return tableStage;
    }

    public static void setTableStage(Stage tableStage) {
        TableUI.tableStage = tableStage;
    }

    public static Scene getTableScene() {
        return tableScene;
    }

    public static void setTableScene(Scene tableScene) {
        TableUI.tableScene = tableScene;
    }

    public static TableController getTableController() {
        return tableController;
    }

    public static void setTableController(TableController tableController) {
        TableUI.tableController = tableController;
    }

    public static Parent getTableRoot() {
        return tableRoot;
    }

    public static void setTableRoot(Parent tableRoot) {
        TableUI.tableRoot = tableRoot;
    }

    public static SwingNode getSwingTableNode() {
        return swingTableNode;
    }

    public static void setSwingTableNode(SwingNode swingTableNode) {
        TableUI.swingTableNode = swingTableNode;
    }

    public static GrafTable getData() {
        return data;
    }

    public static void setData(GrafTable data) {
        TableUI.data = data;
    }

    public static JPanel getDataPanel() {
        return dataPanel;
    }

    public static void setDataPanel(JPanel dataPanel) {
        TableUI.dataPanel = dataPanel;
    }
}
