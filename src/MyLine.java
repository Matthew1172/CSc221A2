import javafx.scene.canvas.GraphicsContext;

public class MyLine extends MyShape {
    private double x2, y2;
    private MyColor color;
    public MyLine(MyPoint ref, MyColor color, double x2, double y2){
        super(ref, color);
        this.x2 = x2;
        this.y2 = y2;
        this.color = color;
    }
    public double getLength(){ return (Math.sqrt(Math.pow(super.getRef().getX() - x2, 2) + Math.pow(super.getRef().getY() - y2, 2))); }

    public double get_xAngle(){
        return (double) (this.y2 - super.getRef().getY()) == 0 ? 0 : (double) (this.x2 - super.getRef().getX()) == 0 ? 0 : Math.toDegrees(Math.tan((double) (this.x2 - super.getRef().getX())/(double) (this.y2 - super.getRef().getY())));
    }

    public double getSlope(){
        return (this.y2 - super.getRef().getY())/(this.x2 - super.getRef().getX());
    }

    @Override
    public String toString(){
        return String.format("----- Line Properties -----\n%15s %s\n%15s %s\n%15s %f\n%15s %f\n%15s %f\n%15s %s",
                "Start point:",super.getRef(),"End point:","("+this.x2+", "+this.y2+")","Length:",getLength(),"Slope:",get_xAngle(), "Slope2:",getSlope(), "Color:", super.getMyColor());
    }
    @Override
    public void draw(GraphicsContext GC) {
        GC.setStroke(color.getColor());
        GC.strokeLine(super.getRef().getX(), super.getRef().getY(), x2, y2);
    }

    @Override
    public MyRectangle getMyBoundingRectangle(MyColor c) {
        if(super.getRef().getY() > this.y2) return new MyRectangle(new MyPoint(super.getRef().getX(), this.y2), c, Math.abs(this.y2 - super.getRef().getY()), this.x2 - super.getRef().getX() == 0 ? 1 : this.x2 - super.getRef().getX());
        else return new MyRectangle(super.getRef(), c, this.y2 - super.getRef().getY() == 0 ? 1 : this.y2 - super.getRef().getY(), this.x2 - super.getRef().getX() == 0 ? 1 : this.x2 - super.getRef().getX());
    }
}
