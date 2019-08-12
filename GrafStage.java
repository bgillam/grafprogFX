//package sample;

import javafx.application.Application;
import javafx.application.Platform;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Menu;
import javafx.scene.control.MenuBar;
import javafx.stage.Modality;
import javafx.stage.Stage;


public class GrafStage extends Application {

    static Stage dialogStage = new Stage();
    static Stage spreadStage = new Stage();
    static DialogController dialogController;


    @Override
    public void start(Stage primaryStage) throws Exception{
        //Set up Stage for Main Window
        Parent grafRoot = FXMLLoader.load(getClass().getResource("grafMain.fxml"));
        Scene grafScene = new Scene (grafRoot, 400, 400);
        primaryStage.setScene(grafScene);
        primaryStage.setTitle("GrafProg: A Simple Graphing Program");
        //MenuBar mainMenu = new MenuBar();
        //Menu fileMenu = new Menu("File");
        //mainMenu.getMenus().add(fileMenu);

        primaryStage.show();
        //primaryStage.hide();


        //Set up Stage for Dialogs
        //Parent dialogRoot = FXMLLoader.load(getClass().getResource("GrafDialog.fxml"));
        FXMLLoader loader = new FXMLLoader(getClass().getResource("GrafDialog.fxml"));
        Parent dialogRoot = (Parent)loader.load();
        dialogController = (DialogController)loader.getController();

        Scene dialogScene = new Scene (dialogRoot, 400, 400);
        dialogStage.setScene(dialogScene);
        dialogStage.hide();
        dialogStage.initModality(Modality.APPLICATION_MODAL);
        dialogController.hideAll();


        //new GrafProg();


    }


    public static void main(String[] args) {
        //new GrafProg();
        launch(args);


    }
}
