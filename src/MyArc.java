import javafx.scene.canvas.GraphicsContext;
import javafx.scene.shape.Arc;
import javafx.scene.shape.ArcType;

import java.util.ArrayList;

public class MyArc extends MyShape {
    //private MyPoint start;
    //private MyPoint end;
    private double height;
    private double width;
    private double startAngle;
    private double length;

    MyArc(MyPoint ref, MyColor c, double height, double width, double startAngle, double length){
        super(ref, c);
        //this.start = start;
        //this.end = end;
        this.height = height;
        this.width = width;
        this.startAngle = startAngle;
        this.length = length;
    }

    @Override
    public void draw(GraphicsContext gc){
        gc.setFill(super.getMyColor().getColor());
        gc.fillArc(super.getRef().getX(), super.getRef().getY(), getWidth(), getHeight(), getStartAngle(), getLength(), ArcType.ROUND);
    }

    @Override
    public String toString() {
        return String.format("----- Arc Properties -----\n%15s %s\n%15s %s\n%15s %s\n%15s %s",
                "Arc reference point:",super.getRef(),"Arc start angle:",getStartAngle(),"Arc length:",getLength(),"Color:", super.getMyColor());
    }

    @Override
    public MyRectangle getMyBoundingRectangle(MyColor c) {
        return null;
    }

    @Override
    public ArrayList<MyPoint> getMyArea() {
        return null;
    }

/*
    public MyPoint getStart() {
        return start;
    }

    public void setStart(MyPoint start) {
        this.start = start;
    }

    public MyPoint getEnd() {
        return end;
    }

    public void setEnd(MyPoint end) {
        this.end = end;
    }

 */
    public double getHeight() {
        return height;
    }

    public void setHeight(double height) {
        this.height = height;
    }

    public double getWidth() {
        return width;
    }

    public void setWidth(double width) {
        this.width = width;
    }

    public double getStartAngle() {
        return startAngle;
    }

    public void setStartAngle(double startAngle) {
        this.startAngle = startAngle;
    }

    public double getLength() {
        return length;
    }

    public void setLength(double length) {
        this.length = length;
    }

}
