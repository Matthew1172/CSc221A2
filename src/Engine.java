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
            PS.setTitle("CSc221 Lab 2");
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
        //drawOvalConfig(GC, cWidth, cHeight);
        //drawPolyConfig(GC, cWidth, cHeight);
        drawRectConfig(GC, cWidth, cHeight);
        //drawLineConfig(GC, cWidth, cHeight);
        //drawPixelConfig(GC, cWidth, cHeight);
        //drawConfig(GC, cWidth, cHeight);
        return CV;
    }

    public void drawConfig(GraphicsContext GC, double x, double y){

        int a = 200;
        int b = 100;
        MyPoint c = new MyPoint(x/2 - a/2, y/2 - b/2);

        MyOval o1 = new MyOval(c, MyColor.RED, b, a);
        MyRectangle r1 = o1.getMyBoundingRectangle(MyColor.PURPLE);
        MyOval o2 = new MyOval(new MyPoint(o1.getCenter().getX() - r1.getWidth()/Math.sqrt(2), o1.getCenter().getY() - r1.getHeight()/Math.sqrt(2)), MyColor.YELLOW, 2*r1.getHeight()/Math.sqrt(2), 2*r1.getWidth()/Math.sqrt(2));
        MyRectangle r2 = o2.getMyBoundingRectangle(MyColor.CYAN);
        MyOval o3 = new MyOval(new MyPoint(o2.getCenter().getX() - r2.getWidth()/Math.sqrt(2), o2.getCenter().getY() - r2.getHeight()/Math.sqrt(2)), MyColor.BLUE, 2*r2.getHeight()/Math.sqrt(2), 2*r2.getWidth()/Math.sqrt(2));
        MyRectangle r3 = o3.getMyBoundingRectangle(MyColor.GREEN);

        r3.draw(GC);
        o3.draw(GC);
        r2.draw(GC);
        o2.draw(GC);
        r1.draw(GC);
        o1.draw(GC);

        MyLine l0 = new MyLine(new MyPoint(0, 0), MyColor.BLACK, x, 0);
        MyLine l1 = new MyLine(new MyPoint(x, 0), MyColor.BLACK, x, y);
        MyLine l2 = new MyLine(new MyPoint(x, y), MyColor.BLACK, 0, y);
        MyLine l3 = new MyLine(new MyPoint(0, y), MyColor.BLACK, 0, 0);
        MyLine l4 = new MyLine(new MyPoint(0, 0), MyColor.BLACK, x, y);

        l0.draw(GC);
        l1.draw(GC);
        l2.draw(GC);
        l3.draw(GC);
        l4.draw(GC);
    }

    public void drawOvalConfig(GraphicsContext GC, double x, double y) {
        //MyShape c = new MyCircle(new MyPoint(200, 200), MyColor.YELLOW, 100);
        //MyRectangle r = c.getMyBoundingRectangle(MyColor.GREEN);
        //r.draw(GC);
        //c.draw(GC);
        //System.out.println(c);

        MyOval o = new MyOval(new MyPoint(200, 200), MyColor.RED, 200, 200);
        MyRectangle r1 = o.getMyBoundingRectangle(MyColor.PURPLE);
        //r1.draw(GC);
        //o.draw(GC);
        //System.out.println(o);

        MyOval o2 = new MyOval(new MyPoint(300, 200), MyColor.RED, 100, 300);
        MyRectangle r2 = o.getMyBoundingRectangle(MyColor.PURPLE);
        //r1.draw(GC);
        //o.draw(GC);
        //o2.draw(GC);
        //System.out.println(o);


/*
        ArrayList<MyPoint> a = o.getMyOvalPoints();
        for(MyPoint p : a){
            System.out.println(p);
            MyRectangle z = new MyRectangle(p, MyColor.CYAN, 1, 1);
            z.draw(GC);
        }
 */
        ArrayList<MyPoint> v = o.overlapMyShapes(o, o2);
        for(MyPoint p : v){
            //System.out.println(p);
            MyRectangle z = new MyRectangle(p, MyColor.BLACK, 1, 1);
            z.draw(GC);
        }

        MyOval o3 = new MyOval(new MyPoint(700, 200), MyColor.RED, 200, 200);
        MyRectangle r3 = o3.getMyBoundingRectangle(MyColor.PURPLE);
        MyOval o4 = new MyOval(new MyPoint(800, 200), MyColor.RED, 200, 200);
        MyRectangle r4 = o4.getMyBoundingRectangle(MyColor.PURPLE);
        //r1.draw(GC);
        //o4.draw(GC);
        //o3.draw(GC);
        //System.out.println(o);

        ArrayList<MyPoint> v2 = o3.overlapMyShapes(o3, o4);
        for(MyPoint p : v2){
            //System.out.println(p);
            MyRectangle z = new MyRectangle(p, MyColor.BLACK, 1, 1);
            z.draw(GC);
        }
    }

    public void drawPolyConfig(GraphicsContext GC, double x, double y) {
        MyPolygon p3 = new MyPolygon(new MyPoint(200, 200), MyColor.HOTPINK, 100, 7);
        //System.out.println(p3);
        MyRectangle r3 = p3.getMyBoundingRectangle(MyColor.YELLOW);
        //r3.draw(GC);
        //p3.draw(GC);

        ArrayList<MyPoint> a = p3.getMyLinePoints();
        for(MyPoint p : a){
            //System.out.println(p);
            MyRectangle z = new MyRectangle(p, MyColor.CYAN, 1, 1);
            //z.draw(GC);
        }

        ArrayList<MyPoint> ar = p3.getMyArea();
        for(MyPoint p : ar){
            //System.out.println(p);
            MyRectangle z = new MyRectangle(p, MyColor.GREEN, 1, 1);
            //z.draw(GC);
        }


        MyPolygon p4 = new MyPolygon(new MyPoint(360, 200), MyColor.HOTPINK, 100, 4);
        MyRectangle r4 = p4.getMyBoundingRectangle(MyColor.YELLOW);
        //r4.draw(GC);
        //p4.draw(GC);

        ArrayList<MyPoint> ar2 = p4.getMyArea();
        for(MyPoint p : ar2){
            //System.out.println(p);
            MyRectangle z = new MyRectangle(p, MyColor.GREEN, 1, 1);
            //z.draw(GC);
        }

        ArrayList<MyPoint> v = p3.overlapMyShapes(p3, p4);
        for(MyPoint p : v){
            //System.out.println(p);
            MyRectangle z = new MyRectangle(p, MyColor.BLACK, 1, 1);
            z.draw(GC);
        }

        MyPolygon p5 = new MyPolygon(new MyPoint(700, 200), MyColor.HOTPINK, 100, 5);
        MyRectangle r5 = p5.getMyBoundingRectangle(MyColor.YELLOW);
        //r5.draw(GC);
        //p5.draw(GC);


        ArrayList<MyPoint> s5 = p5.getMyArea();
        for(MyPoint l : s5) {
            MyRectangle z = new MyRectangle(l, MyColor.BLACK, 1, 1);
            //z.draw(GC);
            System.out.println(l);
        }
        System.out.println(s5.size());


        MyPolygon p6 = new MyPolygon(new MyPoint(800, 300), MyColor.HOTPINK, 100, 6);
        MyRectangle r6 = p6.getMyBoundingRectangle(MyColor.YELLOW);
        //r6.draw(GC);
        //p6.draw(GC);


        ArrayList<MyPoint> s6 = p6.getMyArea();
        for(MyPoint l : s6){
            MyRectangle z = new MyRectangle(l, MyColor.BLACK, 1, 1);
            //z.draw(GC);
        }
        System.out.println(s6.size());

        ArrayList<MyPoint> v2 = p5.overlapMyShapes(p5, p6);
        for(MyPoint p : v2){
            //System.out.println(p);
            MyRectangle z = new MyRectangle(p, MyColor.BLACK, 1, 1);
            z.draw(GC);
        }



        /*
        MyPolygon p7 = new MyPolygon(new MyPoint(1050, 300), MyColor.HOTPINK, 100, 7);
        MyRectangle r7 = p7.getMyBoundingRectangle(MyColor.YELLOW);
        r7.draw(GC);
        p7.draw(GC);


        MyPolygon p8 = new MyPolygon(new MyPoint(1300, 300), MyColor.HOTPINK, 100, 8);
        MyRectangle r8 = p8.getMyBoundingRectangle(MyColor.YELLOW);
        r8.draw(GC);
        p8.draw(GC);


        MyPolygon p9 = new MyPolygon(new MyPoint(1580, 300), MyColor.HOTPINK, 100, 9);
        MyRectangle r9 = p9.getMyBoundingRectangle(MyColor.YELLOW);
        r9.draw(GC);
        p9.draw(GC);

        MyPolygon p10 = new MyPolygon(new MyPoint(1780, 300), MyColor.HOTPINK, 100, 10);
        MyRectangle r10 = p10.getMyBoundingRectangle(MyColor.YELLOW);
        //r10.draw(GC);
        //p10.draw(GC);

         */

    }

    public void drawRectConfig(GraphicsContext GC, double x, double y) {
        MyRectangle r = new MyRectangle(new MyPoint(100, 100), MyColor.HOTPINK, 100, 100);
        r.draw(GC);
        System.out.println(r);

/*
        ArrayList<MyPoint> s5 = r.getMyArea();
        for(MyPoint l : s5) {
            MyRectangle z = new MyRectangle(l, MyColor.BLACK, 1, 1);
            z.draw(GC);
            //System.out.println(l);
        }

 */
        MyRectangle r2 = new MyRectangle(new MyPoint(150, 150), MyColor.RED, 100, 100);
        r2.draw(GC);
        System.out.println(r);

        ArrayList<MyPoint> v = r.overlapMyShapes(r, r2);
        for(MyPoint p : v){
            System.out.println(p);
            MyRectangle z = new MyRectangle(p, MyColor.BLACK, 1, 1);
            z.draw(GC);
        }

    }

    public void drawLineConfig(GraphicsContext GC, double x, double y) {
        MyLine m1 = new MyLine(new MyPoint(80, 100), MyColor.BLACK, 400, 150);
        MyLine m2 = new MyLine(new MyPoint(80, 100), MyColor.BLACK, 300, 95);
        //MyLine m3 = new MyLine(new MyPoint(100, 100), MyColor.BLACK, 410, 210);
        MyLine m3 = new MyLine(new MyPoint(80, 100), MyColor.CYAN, 128, 300);
        MyLine m4 = new MyLine(new MyPoint(128, 300), MyColor.CYAN, 128, 300);
        MyLine m5 = new MyLine(new MyPoint(300, 95), MyColor.GREY, 400, 145);
        MyRectangle lr1 = m1.getMyBoundingRectangle(MyColor.PURPLE);
        MyRectangle lr2 = m2.getMyBoundingRectangle(MyColor.PURPLE);
        MyRectangle lr3 = m3.getMyBoundingRectangle(MyColor.BLACK);

        //System.out.println(m1);
        System.out.println(m2);
        //System.out.println(m3);
        //System.out.println(lr1);
        System.out.println(lr2);
        //System.out.println(lr3);

        //lr1.draw(GC);
        //m1.draw(GC);
        //lr2.draw(GC);
        //m2.draw(GC);
        //lr3.draw(GC);
        //m3.draw(GC);
        //m4.draw(GC);
        //m5.draw(GC);

    }

}
