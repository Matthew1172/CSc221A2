import javafx.scene.canvas.GraphicsContext;
import java.util.HashSet;
import java.util.Set;

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
    public abstract Set<MyPoint> overlapMyShapes();

    @Override
    public abstract Set<MyPoint> getMyArea();

    /*
    @Override
    public Set<MyPoint> overlapMyShapes() {
        Set<MyPoint> out = new HashSet<MyPoint>();

        return out;
    }
     */

}
