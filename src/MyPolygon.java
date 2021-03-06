import javafx.scene.canvas.GraphicsContext;

import java.util.ArrayList;

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
    public void printSides(){
        for(int i = 0; i < this.sides; ++i){
            System.out.println(xp[i] + ", " + yp[i]);
        }
    }

    public double getRadius(){ return radius; }
    public double getApothem(){ return radius * Math.cos(Math.toRadians((180 / (double) sides))); }
    public void border(GraphicsContext gc){
        gc.setStroke(super.getMyColor().getColor());
        for (int i = 1; i < sides; ++i){
            gc.strokeLine(xp[i-1], yp[i-1], xp[i], yp[i]);
        }
        gc.strokeLine(xp[sides-1], yp[sides-1], xp[0], yp[0]);
    }

    public ArrayList<MyLine> getMyLines(){
        ArrayList<MyLine> s = new ArrayList<MyLine>();
        if ((this.sides & 1) != 0){
            for(int i = 0; i < (int) Math.ceil(((double) this.sides)/2); ++i){
                if(xp[i] > xp[i+1]) s.add(new MyLine(new MyPoint(xp[i+1], yp[i+1]), MyColor.BLACK, xp[i], yp[i]));
                else s.add(new MyLine(new MyPoint(xp[i], yp[i]), MyColor.BLACK, xp[i+1], yp[i+1]));
            }
        }else{
            for(int i = 0; i < this.sides/2; ++i){
                if(xp[i] > xp[i+1]) s.add(new MyLine(new MyPoint(xp[i+1], yp[i+1]), MyColor.BLACK, xp[i], yp[i]));
                else s.add(new MyLine(new MyPoint(xp[i], yp[i]), MyColor.BLACK, xp[i+1], yp[i+1]));
            }
        }
        return s;
    }

    public ArrayList<MyPoint> getMyLinePoints(){
        ArrayList<MyLine> s = getMyLines();
        ArrayList<MyPoint> o = new ArrayList<MyPoint>();
        double x, y;
        for(MyLine l : s){
            for(double i = 0; i <= 1; i += 0.01) {
                x = l.getRef().getX() + (l.getX2() - l.getRef().getX()) * i;
                y = l.getRef().getY() + (l.getY2() - l.getRef().getY()) * i;
                o.add(new MyPoint(Math.rint(x), Math.rint(y)));
            }
        }
        return o;
    }

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

    @Override
    public MyRectangle getMyBoundingRectangle(MyColor c) {
        if (this.sides == 3) return new MyRectangle(new MyPoint(super.getRef().getX() - (Math.sqrt(Math.pow(this.radius, 2) - Math.pow(getApothem(), 2))), super.getRef().getY() - this.radius), c, this.radius + getApothem(), (Math.sqrt(Math.pow(this.radius, 2) - Math.pow(getApothem(), 2)))*2);
        if (this.sides == 6) return new MyRectangle(new MyPoint(super.getRef().getX() - getApothem(), super.getRef().getY() - this.radius), c, this.radius + this.radius, getApothem() + getApothem());
        if ((this.sides & 1) != 0) return new MyRectangle(new MyPoint(super.getRef().getX() - this.radius, super.getRef().getY() - this.radius), c, this.radius + getApothem(), this.radius + this.radius);
        else return new MyRectangle(new MyPoint(super.getRef().getX() - this.radius, super.getRef().getY() - this.radius), c, this.radius + this.radius, this.radius + this.radius);
    }

    @Override
    public ArrayList<MyPoint> getMyArea(){
        ArrayList<MyPoint> o = new ArrayList<MyPoint>();
        MyRectangle b = getMyBoundingRectangle(MyColor.CYAN);
        for(int y = (int) b.getRef().getY(); y <= b.getRef().getY() + b.getHeight(); y += 1){
            for(int x = (int) b.getRef().getX(); x <= b.getRef().getX() + b.getWidth(); x += 1){
                for(MyPoint p : getMyLinePoints()){
                    if(p.getX() == x && p.getY() == y){
                        int trans = (int) (super.getRef().getX() - x) + (int) super.getRef().getX();
                        for(int k = x + 1; k <= trans; ++k){
                            o.add(new MyPoint(k, y));
                        }
                    }
                }
            }
        }
        return o;
    }
}