import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;

import java.util.ArrayList;

public class Engine extends Application {
    private static byte r, g, b;

    public static void main(String args[]) {
        launch(args);
    }

    @Override
    public void start(Stage PS) {
        try {
            PS.setTitle("CSc221 Lab 3");
            Pane P = new Pane();
            Canvas CV = addCanvas(1900, 400);
            P.getChildren().add(CV);
            Scene SC = new Scene(P, 1900, 400, MyColor.WHITE.getColor());
            PS.setScene(SC);
            PS.show();
        } catch (Exception e) {
            e.printStackTrace();
            System.out.println(e.getMessage());
        }
    }

    public Canvas addCanvas(int cWidth, int cHeight) {
        Canvas CV = new Canvas(cWidth, cHeight);
        GraphicsContext GC = CV.getGraphicsContext2D();
        drawConfig(GC, cWidth, cHeight);
        return CV;
    }

    public void drawConfig(GraphicsContext GC, double x, double y){

    }

}
