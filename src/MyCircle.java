import javafx.scene.canvas.GraphicsContext;

public class MyCircle extends MyOval {
    private double r;
    public MyCircle(MyPoint ref, MyColor color, double r){
        super(ref, color, r+r, r+r);
        this.r = r;
    }
    
    public double getRadius(){ return r; }
    public double getArea() { return (Math.PI * (r * r)); }
    public double getPerimeter() { return (2*Math.PI*this.r); }
    @Override
    public String toString(){
        return String.format("----- Circle Properties -----\n%15s %s\n%15s %f\n%15s %f\n%15s %f\n%15s %s",
                "Reference point:",super.getRef(), "Radius:",getRadius(),"Perimeter:",getPerimeter(),"Area:",getArea(),"Color:",super.getMyColor());
    }
    @Override
    public void draw(GraphicsContext GC) {
        GC.setFill(super.getMyColor().getColor());
        GC.fillOval(super.getRef().getX(), super.getRef().getY(), 2*this.r, 2*this.r);
    }
    public void border(GraphicsContext GC) {
        GC.setStroke(super.getMyColor().getColor());
        GC.strokeOval(super.getRef().getX(), super.getRef().getY(), 2*this.r, 2*this.r);
    }
}
