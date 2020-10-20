import javafx.scene.canvas.GraphicsContext;
import java.util.ArrayList;

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
    public ArrayList<MyPoint> overlapMyShapes(MyShape s1, MyShape s2){
        if (s1 instanceof MyLine || s2 instanceof MyLine) return null;
        ArrayList<MyPoint> a1 = s1.getMyArea();
        ArrayList<MyPoint> a2 = s2.getMyArea();
        ArrayList<MyPoint> in = new ArrayList<MyPoint>();
        int i = 0, j = 0;
        while (i < a1.size() && j < a2.size()) {
            if (a1.get(i).getX() > a2.get(j).getX() || a1.get(i).getY() > a2.get(j).getY()) j++;
            else if (a1.get(i).getX() < a2.get(j).getX() || a1.get(i).getY() < a2.get(j).getY()) i++;
            else {
                in.add(a1.get(i));
                i++;
                j++;
            }
        }
        return in;
    }
}
