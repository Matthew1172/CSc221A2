import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;

public class Engine extends Application {
    public static void main(String args[]){
        launch(args);
    }

    @Override
    public void start(Stage PS) {
        try{
            PS.setTitle("CSc221 Lab 2");
            Pane P = new Pane();
            Canvas CV = addCanvas(900, 500);
            P.getChildren().add(CV);
            Scene SC = new Scene(P, 900, 500, MyColor.BUSINESS.getColor());
            PS.setScene(SC);
            PS.show();
        } catch (Exception e){
            e.printStackTrace();
            System.out.println(e.getMessage());
        }
    }

    public Canvas addCanvas(int cWidth, int cHeight){
        Canvas CV = new Canvas(cWidth, cHeight);
        GraphicsContext GC = CV.getGraphicsContext2D();
        drawOvalConfig(GC, cWidth, cHeight);
        drawPolyConfig(GC, cWidth, cHeight);
        drawRectConfig(GC, cWidth, cHeight);
        drawLineConfig(GC, cWidth, cHeight);
        return CV;
    }

    public void drawOvalConfig(GraphicsContext GC, double x, double y) {
        MyShape c = new MyCircle(new MyPoint(0, 0), MyColor.YELLOW, 100);
        MyRectangle r = c.getMyBoundingRectangle(MyColor.GREEN);
        r.draw(GC);
        c.draw(GC);
        System.out.println(c);

        MyShape o = new MyOval(new MyPoint(0, 0), MyColor.CYAN, 100, 200);
        MyRectangle r1 = o.getMyBoundingRectangle(MyColor.PURPLE);
        r1.draw(GC);
        o.draw(GC);
        System.out.println(o);

    }

    public void drawPolyConfig(GraphicsContext GC, double x, double y){
        MyPolygon p = new MyPolygon(new MyPoint(300, 300), MyColor.HOTPINK, 100, 3);
        System.out.println(p);
        p.draw(GC);
        MyRectangle r = p.getMyBoundingRectangle(MyColor.YELLOW);
        r.draw(GC);
    }

    public void drawRectConfig(GraphicsContext GC, double x, double y){
        MyRectangle r = new MyRectangle(new MyPoint(100, 100), MyColor.HOTPINK, 100, 100);
        r.draw(GC);
        System.out.println(r);
    }

    public void drawLineConfig(GraphicsContext GC, double x, double y){
        MyLine m1 = new MyLine(new MyPoint(100,100), MyColor.BLACK, 400, 100);
        System.out.println(m1);
        m1.draw(GC);

        MyLine m2 = new MyLine(new MyPoint(100,100), MyColor.BLACK, 100, 400);
        System.out.println(m2);
        m2.draw(GC);

        MyLine m3 = new MyLine(new MyPoint(100,100), MyColor.BLACK, 400, 0);
        System.out.println(m3);
        m3.draw(GC);

        MyRectangle lr1 = m1.getMyBoundingRectangle(MyColor.PURPLE);
        System.out.println(lr1);
        lr1.draw(GC);

        MyRectangle lr2 = m2.getMyBoundingRectangle(MyColor.PURPLE);
        System.out.println(lr2);
        lr2.draw(GC);

        MyRectangle lr3 = m3.getMyBoundingRectangle(MyColor.PURPLE);
        System.out.println(lr3);
        lr3.draw(GC);
    }
}
