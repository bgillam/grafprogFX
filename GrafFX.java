import GrafProg.GrafUI.GrafUIFX;
import javafx.application.Application;
import javafx.scene.shape.Line;
import javafx.stage.Stage;

public class GrafFX extends Application {

    public static void main(String[] args){
        launch(args);
    }
    GrafUIFX g = new GrafUIFX();
    @Override

    public void start(Stage gs){
        drawLineFX(10,20,25,50);

    }

    public static void drawLineFX(double x1, double y1, double x2, double y2){
             Line line = new Line(x1,y1,x2,y2);;
             GrafUIFX.getGrafGroup().getChildren().add(line);
             GrafUIFX.getGrafStage2().setScene(GrafUIFX.getGrafScene2());
             GrafUIFX.getGrafStage2().show();
    }

   /* private FXMLLoader createScene(Stage stage, int width, int height, String path, String title) throws IOException {
        Stage stage = new Stage();

        FXMLLoader loader = new FXMLLoader(getClass().getResource(path));
        Parent root = loader.load();
        Scene scene = new Scene(root, width, height);
        stage.setScene(scene);
        stage.setTitle(title);
        return loader;
    }*/


}
