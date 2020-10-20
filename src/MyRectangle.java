import javafx.scene.canvas.GraphicsContext;

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
}
