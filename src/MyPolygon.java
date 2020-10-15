import javafx.scene.canvas.GraphicsContext;

import java.util.Set;

public class MyPolygon extends MyShape {
    private double radius;
    private int sides;
    private double xp[];
    private double yp[];
    MyPolygon(MyPoint r, MyColor color, double radius, int sides){
        super(r, color);
        this.radius = radius;
        this.sides = sides;
        xp = new double[sides];
        yp = new double[sides];
        double ang = (2 * Math.PI)/sides;
        for (int i = 0; i < sides; ++i){
            xp[i] = super.getRef().getX() + (radius*(-1 * Math.sin(i*ang)));
            yp[i] = super.getRef().getY() + (radius*(-1 * Math.cos(i*ang)));
        }
    }
    public double getArea(){
        return Math.pow(radius,2) * sides * Math.sin(getAngle()) * 0.5;
    }
    public double getPerimeter(){ return sides * getSide(); }
    public double getAngle(){ return Math.PI/sides; }
    public double getSide(){ return 2 * radius * Math.sin(Math.PI/sides); }
    @Override
    public String toString(){
        return String.format("----- Polygon Properties -----\n%15s %s\n%15s %f\n%15s %f\n%15s %f\n%15s %f\n%15s %f\n%15s %s",
                "Reference:",super.getRef(), "Radius:",getRadius(),"Area:",getArea(),"Perimeter:",getPerimeter(),
                "Angle:",getAngle(),"Apothem:",getApothem(),"Color:", super.getMyColor());
    }
    @Override
    public void draw(GraphicsContext gc){
        gc.setFill(super.getMyColor().getColor());
        gc.setStroke(super.getMyColor().getColor());
        gc.setLineWidth(3);
        gc.strokePolygon(xp, yp, sides);
        gc.fillPolygon(xp, yp, sides);
    }
    public double getRadius(){ return radius; }
    public double getApothem(){ return radius * Math.cos(Math.toRadians((180 / sides))); }
    public void border(GraphicsContext gc){
        gc.setStroke(super.getMyColor().getColor());
        for (int i = 1; i < sides; ++i){
            gc.strokeLine(xp[i-1], yp[i-1], xp[i], yp[i]);
        }
        gc.strokeLine(xp[sides-1], yp[sides-1], xp[0], yp[0]);
    }

    @Override
    public MyRectangle getMyBoundingRectangle(MyColor c) {
        return new MyRectangle(new MyPoint(super.getRef().getX() - this.radius, super.getRef().getY() - this.radius), c, this.radius + this.radius, this.radius + this.radius);
    }

    @Override
    public Set<MyPoint> overlapMyShapes() {
        return null;
    }

    @Override
    public Set<MyPoint> getMyArea() {
        return null;
    }
}