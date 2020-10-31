import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;

public class Engine extends Application {
    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage PS) {
        try {
            PS.setTitle("CSc221 Lab 3");
            Pane P = new Pane();
            Canvas CV = addCanvas(1200, 900);
            P.getChildren().add(CV);
            Scene SC = new Scene(P, 1200, 900, MyColor.WHITE.getColor());
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
        HistogramAlphaBet h = new HistogramAlphaBet(GC, cWidth, cHeight, 6);
        h.drawConfig();

        MyPieChart p = new MyPieChart(GC,cWidth,cHeight);
        //p.drawConfig();
        return CV;
    }
}
