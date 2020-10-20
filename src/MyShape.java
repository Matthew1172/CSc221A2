import javafx.scene.canvas.GraphicsContext;

public abstract class MyShape implements MyShapeInterface {
    private MyPoint ref;
    private MyColor color;
    public MyShape(MyPoint ref, MyColor color){
        this.ref = ref;
        this.color = color;
    }
    public abstract void draw(GraphicsContext GC);
    public abstract String toString();
    public MyPoint getRef(){ return ref; }
    public void setRef(MyPoint ref){ this.ref = ref; }
    public MyColor getMyColor() {
        return color;
    }
    public void setColor(MyColor color) {
        this.color = color;
    }

    @Override
    public MyRectangle overlapMyRectangles(MyRectangle r1, MyRectangle r2){
        double x1 = r1.getRef().getX();
        double y1 = r1.getRef().getY();
        double w1 = r1.getWidth();
        double h1 = r1.getHeight();
        double x2 = r2.getRef().getX();
        double y2 = r2.getRef().getY();
        double w2 = r2.getWidth();
        double h2 = r2.getHeight();
        if(y1 + h1 < y2 || y1 > y2 + h2) return null;
        if(x1 + w1 < x2 || x1 > x2 + w2) return null;
        double xmax = Math.max(x1, x2);
        double ymax = Math.max(y1, y2);
        double xmin = Math.min(x1 + w1, x2 + w2);
        double ymin = Math.min(y1 + h1, y2 + h2);
        MyPoint p = new MyPoint(xmax, ymax);
        return new MyRectangle(p, MyColor.CYAN, Math.abs(xmin - xmax), Math.abs(ymin - ymax));
    }

    @Override
    public MyRectangle overlapMyShapes(MyShape s1, MyShape s2){
        if (s1 instanceof MyLine || s2 instanceof MyLine) return null;
        MyRectangle r1 = s1.getMyBoundingRectangle(MyColor.BLACK);
        MyRectangle r2 = s2.getMyBoundingRectangle(MyColor.BLACK);
        return overlapMyRectangles(r1, r2);
    }

}
