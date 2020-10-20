import javafx.scene.canvas.GraphicsContext;

import java.util.ArrayList;

public class MyOval extends MyShape {
    private double h, w;
    private MyPoint c;

    public MyOval(MyPoint ref, MyColor color, double h, double w){
        super(ref, color);
        this.h = h;
        this.w = w;
        setCenter(w, h);
    }

    public double getArea(){
        return Math.PI * this.h * this.w;
    }

    public double getPerimeter(){
        return (Math.PI + Math.PI) * (Math.sqrt(((this.w * this.w) + (this.h * this.h))/2));
    }

    public MyPoint getCenter(){
        return this.c;
    }

    public double getW(){ return this.w; }
    public double getH(){ return this.h; }

    public void setCenter(double w, double h){
        this.c = new MyPoint(super.getRef().getX() + w/2,super.getRef().getY() + (h/2));
    }

    public void setAxes(double w, double h){
        this.w = w;
        this.h = h;
    }

    public ArrayList<MyPoint> getMyOvalPoints(){
        ArrayList<MyPoint> o = new ArrayList<MyPoint>();
        double x, y;
        for(double i = 0; i <= 2*Math.PI; i += 0.01) {
            x = (this.getW()/2) + super.getRef().getX() + (this.getW()/2) * Math.cos(i);
            y = (this.getH()/2) + super.getRef().getY() + (this.getH()/2) * Math.sin(i);
            o.add(new MyPoint(Math.rint(x), Math.rint(y)));
        }
        return o;
    }

    @Override
    public void draw(GraphicsContext gc) {
        gc.setFill(super.getMyColor().getColor());
        gc.setStroke(super.getMyColor().getColor());
        gc.setLineWidth(3);
        gc.fillOval(super.getRef().getX(), super.getRef().getY(), this.w, this.h);
    }

    @Override
    public String toString() {
        return String.format("----- Polygon Properties -----\n%15s %s\n%15s %f\n%15s %f\n%15s %f\n%15s %f\n%15s %s",
                "Reference point:",super.getRef(),"Area:",getArea(),"Perimeter:",getPerimeter(),
                "Width:",this.w,"Height:",this.h,"Color:", super.getMyColor());
    }

    @Override
    public MyRectangle getMyBoundingRectangle(MyColor c) {
        return new MyRectangle(super.getRef(), c, this.h, this.w);
    }

    @Override
    public ArrayList<MyPoint> getMyArea(){
        ArrayList<MyPoint> o = new ArrayList<MyPoint>();
        MyRectangle b = getMyBoundingRectangle(MyColor.CYAN);
        for(double y = (double) b.getRef().getY(); y <= b.getRef().getY() + b.getHeight(); y += 1){
            for(double x = (double) b.getRef().getX(); x <= b.getRef().getX() + b.getWidth(); x += 1){
                for(MyPoint p : getMyOvalPoints()){
                    if(p.getX() == x && p.getY() == y){
                        int trans = (int) ((this.c.getX() - p.getX()) + this.c.getX());
                        for(double k = x + 1; k <= trans; ++k){
                            o.add(new MyPoint(k, y));
                        }
                    }
                }
            }
        }
        return o;
    }
}
