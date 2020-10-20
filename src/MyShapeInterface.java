import java.util.ArrayList;

public interface MyShapeInterface {
    MyRectangle getMyBoundingRectangle(MyColor c);
    ArrayList<MyPoint> getMyArea();
    ArrayList<MyPoint> overlapMyShapes(MyShape s1, MyShape s2);
}
