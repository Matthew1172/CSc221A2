import javafx.scene.canvas.GraphicsContext;

public class MyOval extends MyShape {
    private double h, w;
    private MyPoint c;

    public MyOval(MyPoint ref, MyColor color, double h, double w){
        super(ref, color);
        this.h = h;
        this.w = w;
        setCenter(w, h);
    }

    @Override
    public MyRectangle getMyBoundingRectangle(MyColor c) {
        return new MyRectangle(super.getRef(), c, this.h, this.w);
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
}
