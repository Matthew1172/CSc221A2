import java.util.Set;

public interface MyShapeInterface {
    MyRectangle getMyBoundingRectangle(MyColor c);
    /*
    Should this be a set of all doubles? how is that even possible?
     */
    Set<MyPoint> getMyArea();
    /*
Should this be a set of all doubles? how is that even possible?
 */
    Set<MyPoint> overlapMyShapes();
}
