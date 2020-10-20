import javafx.scene.canvas.GraphicsContext;

import java.util.ArrayList;

public class MyRectangle extends MyShape {
    private double h, w;
    public MyRectangle(MyPoint ref, MyColor color, double h, double w){
        super(ref, color);
        this.h = h;
        this.w = w;
    }

    public double getWidth(){ return this.w; }
    public double getHeight(){ return this.h; }
    public void setWidth(double w){ this.w = w; }
    public void setHeight(double h){ this.h = h; }
    public double getPerimeter(){ return this.w + this.w + this.h + this.h; }
    public double getArea(){ return this.w * this.h; }

    @Override
    public MyRectangle getMyBoundingRectangle(MyColor c) {
        return this;
    }

    @Override
    public void draw(GraphicsContext gc) {
        gc.setFill(super.getMyColor().getColor());
        gc.setStroke(super.getMyColor().getColor());
        gc.setLineWidth(3);
        gc.fillRect(super.getRef().getX(), super.getRef().getY(), this.w, this.h);
    }

    @Override
    public String toString() {
        return String.format("----- Polygon Properties -----\n%15s %s\n%15s %f\n%15s %f\n%15s %f\n%15s %f\n%15s %s",
                "Reference point:",super.getRef(),"Area:",getArea(),"Perimeter:",getPerimeter(),
                "Width:",getWidth(),"Height:",getHeight(),"Color:", super.getMyColor());
    }

    public ArrayList<MyLine> getMyLines(){
        ArrayList<MyLine> s = new ArrayList<MyLine>();
        s.add(new MyLine(super.getRef(), MyColor.BLACK, super.getRef().getX() + w, super.getRef().getY()));
        s.add(new MyLine(super.getRef(), MyColor.BLACK, super.getRef().getX(), super.getRef().getY() + h));
        s.add(new MyLine(new MyPoint(super.getRef().getX(), super.getRef().getY() + h), MyColor.BLACK, super.getRef().getX() + w, super.getRef().getY() + h));
        s.add(new MyLine(new MyPoint(super.getRef().getX() + w, super.getRef().getY()), MyColor.BLACK, super.getRef().getX() + w, super.getRef().getY() + h));
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
    public ArrayList<MyPoint> getMyArea() {
        ArrayList<MyPoint> o = new ArrayList<MyPoint>();
        MyRectangle b = getMyBoundingRectangle(MyColor.CYAN);
        for(int y = (int) b.getRef().getY(); y <= b.getRef().getY() + b.getHeight(); y += 1){
            for(int x = (int) b.getRef().getX(); x <= b.getRef().getX() + b.getWidth(); x += 1){
                for(MyPoint p : getMyLinePoints()){
                    if(p.getX() == x && p.getY() == y){
                        for(int k = x + 1; k <= super.getRef().getX() + w; ++k){
                            o.add(new MyPoint(k, y));
                        }
                    }
                }
            }
        }
        return o;
    }
}
